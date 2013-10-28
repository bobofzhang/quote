function getURLParameter(name, defaultValue) {
  return decodeURI((RegExp(name + '=' + '(.+?)(&|$)').exec(location.search) || [ , defaultValue ])[1]);
}

function colorOfValue(value, base) {
  if (value > base) {
    return 'upColor';
  } else if (value < base) {
    return 'downColor';
  } else {
    return '';
  }
}

function formatAmount(amount) {
  if (amount > 100000000) {
    return (amount / 100000000).toFixed(4) + '亿元';

  } else if (amount > 10000) {
    return (amount / 10000).toFixed(2) + '万元';
  } else {
    return amount + '元';
  }
}

function formatVolume(volume) {
  if (volume > 1000000) {
    return (volume / 1000000).toFixed(2) + '万手';
  } else if (volume > 100) {
    return (volume / 100).toFixed(0) + '手';
  } else {
    return volume + '股';
  }
}

(function() {
  var socket = new SockJS('/quote');
  var stompClient = Stomp.over(socket);
  var symbol = getURLParameter('symbol', '600570.SH');

  var appModel = new ApplicationModel(stompClient, symbol);
  ko.applyBindings(appModel);
  appModel.connect();

  $('[data-toggle=offcanvas]').click(function() {
    $('.row-offcanvas').toggleClass('active');
  });

  $('input.tapeahead').typeahead({
    name : 'tickers',
    valueKey : 'symbol',
    prefetch : 'tickers',
    limit : 10,
    template : '<p><strong>{{name}}</strong> {{symbol}}</p>',
    engine : Hogan
  });

  $('#chartTab a[href="#trend"]').on('shown.bs.tab', function(e) {
    appModel.trend().prepareTrend();
  });

  $('#chartTab a[href="#kline"]').on('shown.bs.tab', function(e) {
    appModel.kline().prepareKline();
  });

})();

function ApplicationModel(stompClient, symbol) {

  var self = this;

  self.username = ko.observable();
  self.quote = ko.observable(new QuoteModel(stompClient, symbol));
  self.trend = ko.observable(new TrendModel(stompClient, symbol));
  self.kline = ko.observable(new KlineModel(stompClient, symbol));
  self.tick = ko.observable(new TickModel());

  self.notifications = ko.observableArray();

  self.connect = function() {
    stompClient.connect('guest', 'guest', function(frame) {
      var userName = frame.headers['user-name'];
      var queueSuffix = frame.headers['queue-suffix'];
      self.username(userName);

      stompClient.subscribe("/app/info/" + symbol, function(message) {
        var data = JSON.parse(message.body);
        self.quote().loadTicker(data);
        self.trend().initTimes(data);
        self.tick().initTimes(data);
        self.init();
      });
      stompClient.subscribe("/queue/errors/" + queueSuffix, function(message) {
        self.pushNotification("Error " + message.body);
      });
    }, function(error) {
      console.log("STOMP protocol error " + error);
    });
  };

  self.init = function() {

    stompClient.subscribe("/topic/quote/" + symbol, function(message) {
      var data = JSON.parse(message.body);
      self.quote().processQuote(data);
      self.trend().processQuote(data);
      self.kline().processQuote(data);
      self.tick().processQuote(data);
    });

    stompClient.subscribe("/queue/trend/" + symbol, function(message) {
      var data = JSON.parse(message.body);
      self.quote().processQuote(data);
      self.trend().processTrend(data);
    });

    stompClient.subscribe("/queue/kline/" + symbol, function(message) {
      self.kline().processKline(JSON.parse(message.body));
    });

    stompClient.subscribe("/queue/tick/" + symbol, function(message) {
      self.tick().loadTicks(JSON.parse(message.body));
    });
  };

  self.pushNotification = function(text) {
    self.notifications.push({
      notification : text
    });
    if (self.notifications().length > 5) {
      self.notifications.shift();
    }
  };

}

function TickModel() {

  var self = this;

  self.ticks = ko.observableArray();

  self.initTimes = function(data) {
    self.date = data.exchange.date;
    self.times = data.exchange.times;
    self.prevClose = data.ticker.prevClose;
  };

  self.loadTicks = function(data) {
    for (var i = 0; i < data.ticks.length; i++) {
      var t = data.ticks[i];

      var m = moment(self.date, 'YYYYMMDD').add('m', self.times[t.time - 1]).add('s', t.second).format('HH:mm:ss');

      var v = t.vol;
      if (i > 1) {
        v = v - data.ticks[i - 1].vol;
      }

      self.addTick(m, t.price, v);
    }

  };

  self.addTick = function(time, price, vol) {
    var tick = new Tick(time, price, vol, self.prevClose);
    self.ticks.unshift(tick);
    if (self.ticks().length > 5) {
      self.ticks.pop();
    }

  };

  self.processQuote = function(quote) {
    
    var data = quote.data;
    var other = quote.otherData;
    var m = moment(self.date, 'YYYYMMDD').add('m', self.times[other.time - 1]).add('s', other.second).format('HH:mm:ss');

    self.addTick(m, data.price, other.current);
  };

}

function Tick(time, price, vol, prevClose) {
  var self = this;
  self.prevClose = prevClose;
  self.time = time;
  self.price = price;
  self.vol = vol;
  
  self.priceStyle = ko.computed(function() {
    return colorOfValue(self.price, self.prevClose);
  });
};

function TrendModel(stompClient, symbol) {
  var self = this;

  self.price = [];
  self.volume = [];

  self.initTimes = function(data) {
    self.date = data.exchange.date;
    self.times = data.exchange.times;
  };

  self.prepareTrend = function() {
    stompClient.send("/app/trend/" + symbol, {}, '');
  };

  self.showTrend = function() {
    self.chart = $('#chart').highcharts('StockChart', {
      rangeSelector : {
        enabled : false,
        selected : 1
      },

      yAxis : [ {
        height : 200,
        lineWidth : 1
      }, {
        top : 200,
        height : 100,
        offset : 0,
        lineWidth : 1
      } ],

      series : [ {
        type : 'spline',
        name : '价格',
        data : self.price,
        tooltip : {
          xDateFormat : '%b%e日 %H:%M',
        }
      }, {
        type : 'column',
        name : '成交量',
        data : self.volume,
        yAxis : 1,
        color : 'gray'
      } ]
    });
  };

  self.processQuote = function(data) {

  };

  self.processTrend = function(data) {

    var times = self.times;
    var items = data.items;
    self.price = [];
    self.volume = [];

    for (var i = 0; i < times.length; i++) {

      var m = +moment(self.date, 'YYYYMMDD').add('minutes', times[i]);

      if (items.length > i) {
        self.price.push([ m, items[i].price / 1000 ]);
        if (i > 1) {
          self.volume.push([ m, items[i].vol - items[i - 1].vol ]);
        } else {
          self.volume.push([ m, items[i].vol ]);
        }

      } else {
        self.price.push([ m, null ]);
        self.volume.push([ m, null ]);
      }
    }

    self.showTrend();
  };
}

function KlineModel(stompClient, symbol) {
  var self = this;

  self.ohlc = [];
  self.volume = [];

  self.prepareKline = function() {
    stompClient.send("/app/kline/" + symbol, {}, '');
  };

  self.showKline = function() {
    self.chart = $('#chart').highcharts('StockChart', {
      rangeSelector : {
        enabled : false,
        inputEnabled : false,
        selected : 1
      },

      yAxis : [ {
        height : 200,
        lineWidth : 2
      }, {
        top : 200,
        height : 100,
        offset : 0,
        lineWidth : 2
      } ],

      series : [ {
        type : 'candlestick',
        name : symbol,
        data : self.ohlc
      }, {
        type : 'column',
        name : '成交量',
        data : self.volume,
        yAxis : 1
      } ]
    });

  };

  self.processQuote = function(data) {

  };

  self.processKline = function(data) {
    self.ohlc = [];
    self.volume = [];

    for (var i = 0; i < data.length; i++) {
      var date = +moment(data[i].date, 'YYYYMMDD');

      self.ohlc.push([ date, // the date
      data[i].open / 1000, // open
      data[i].high / 1000, // high
      data[i].low / 1000, // low
      data[i].close / 1000 // close
      ]);

      self.volume.push([ date, // the date
      data[i].volume // the volume
      ]);
    }
    self.showKline();

  };

}

function QuoteModel(stompClient, symbol) {
  var self = this;

  self.company = ko.observable();
  self.ticker = ko.observable();
  self.price = ko.observable();

  self.high = ko.observable();
  self.low = ko.observable();
  self.prevClose = ko.observable();
  self.open = ko.observable();
  self.vol = ko.observable();
  self.amount = ko.observable();

  self.bid1 = ko.observable();
  self.bid2 = ko.observable();
  self.bid3 = ko.observable();
  self.bid4 = ko.observable();
  self.bid5 = ko.observable();
  self.bidSize1 = ko.observable();
  self.bidSize2 = ko.observable();
  self.bidSize3 = ko.observable();
  self.bidSize4 = ko.observable();
  self.bidSize5 = ko.observable();
  self.ask1 = ko.observable();
  self.ask2 = ko.observable();
  self.ask3 = ko.observable();
  self.ask4 = ko.observable();
  self.ask5 = ko.observable();
  self.askSize1 = ko.observable();
  self.askSize2 = ko.observable();
  self.askSize3 = ko.observable();
  self.askSize4 = ko.observable();
  self.askSize5 = ko.observable();

  self.current = ko.observable();
  self.sellVol = ko.observable();
  self.buyVol = ko.observable();

  self.change = ko.computed(function() {
    return self.price() - self.prevClose();
  });

  self.changeRate = ko.computed(function() {
    return (self.change() * 100 / self.prevClose()).toFixed(2) + "%";
  });

  self.amplitude = ko.computed(function() {
    return ((self.high() - self.low()) * 100 / self.prevClose()).toFixed(2) + "%";
  });

  self.committee = ko.computed(function() {
    return self.bidSize1() + self.bidSize2() + self.bidSize3() + self.bidSize4() + self.bidSize5() - self.askSize1()
        - self.askSize2() - self.askSize3() - self.askSize4() - self.askSize5();
  });

  self.committeeRate = ko.computed(function() {
    return (self.committee()
        / (self.bidSize1() + self.bidSize2() + self.bidSize3() + self.bidSize4() + self.bidSize5() + self.askSize1()
            + self.askSize2() + self.askSize3() + self.askSize4() + self.askSize5()) * 100).toFixed(2)
        + "%";
  });

  self.formattedVolume = ko.computed(function() {
    return formatVolume(self.vol());
  });

  self.formattedAmount = ko.computed(function() {
    return formatAmount(self.amount());
  });

  self.isEmpty = ko.computed(function() {
    return !self.ticker();
  });

  self.changeStyle = ko.computed(function() {
    return colorOfValue(self.price(), self.prevClose());
  });

  self.highStyle = ko.computed(function() {
    return colorOfValue(self.high(), self.prevClose());
  });

  self.lowStyle = ko.computed(function() {
    return colorOfValue(self.low(), self.prevClose());
  });

  self.openStyle = ko.computed(function() {
    return colorOfValue(self.open(), self.prevClose());
  });

  self.bid1Style = ko.computed(function() {
    return colorOfValue(self.bid1(), self.prevClose());
  });
  self.bid2Style = ko.computed(function() {
    return colorOfValue(self.bid2(), self.prevClose());
  });
  self.bid3Style = ko.computed(function() {
    return colorOfValue(self.bid3(), self.prevClose());
  });
  self.bid4Style = ko.computed(function() {
    return colorOfValue(self.bid4(), self.prevClose());
  });
  self.bid5Style = ko.computed(function() {
    return colorOfValue(self.bid5(), self.prevClose());
  });

  self.ask1Style = ko.computed(function() {
    return colorOfValue(self.ask1(), self.prevClose());
  });
  self.ask2Style = ko.computed(function() {
    return colorOfValue(self.ask2(), self.prevClose());
  });
  self.ask3Style = ko.computed(function() {
    return colorOfValue(self.ask3(), self.prevClose());
  });
  self.ask4Style = ko.computed(function() {
    return colorOfValue(self.ask4(), self.prevClose());
  });
  self.ask5Style = ko.computed(function() {
    return colorOfValue(self.ask5(), self.prevClose());
  });

  self.committeeStyle = ko.computed(function() {
    return colorOfValue(self.committee(), 0);
  });

  self.loadTicker = function(data) {

    // self.times = data.exchange.times;

    self.ticker(data.ticker.symbol);
    self.company(data.ticker.name);
    self.prevClose(data.ticker.prevClose);

  };

  self.processQuote = function(quote) {
    var data = quote.data;
    var other = quote.otherData;

    self.price(data.price);

    self.vol(data.vol);
    self.amount(data.amount);
    self.high(data.high);
    self.low(data.low);
    self.open(data.open);

    self.bid1(data.bid1);
    self.bid2(data.bid2);
    self.bid3(data.bid3);
    self.bid4(data.bid4);
    self.bid5(data.bid5);
    self.bidSize1(data.bidSize1);
    self.bidSize2(data.bidSize2);
    self.bidSize3(data.bidSize3);
    self.bidSize4(data.bidSize4);
    self.bidSize5(data.bidSize5);

    self.ask1(data.ask1);
    self.ask2(data.ask2);
    self.ask3(data.ask3);
    self.ask4(data.ask4);
    self.ask5(data.ask5);
    self.askSize1(data.askSize1);
    self.askSize2(data.askSize2);
    self.askSize3(data.askSize3);
    self.askSize4(data.askSize4);
    self.askSize5(data.askSize5);

    self.current(other.current);
    self.buyVol(other.buyVol);
    self.sellVol(other.sellVol);

  };
}
