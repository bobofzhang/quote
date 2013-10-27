function getURLParameter(name, defaultValue) {
  return decodeURI((RegExp(name + '=' + '(.+?)(&|$)').exec(location.search) || [ , defaultValue ])[1]);
}

function colorOfValue(value, base) {
  if (value > base) {
    return "upColor";
  } else if (value < base) {
    return "downColor";
  } else {
    return "";
  }
};

(function() {

  $('input.tapeahead').typeahead({
    name : 'instruments',
    valueKey : 'symbol',
    prefetch : 'instruments',
    limit : 10,
    template : '<p><strong>{{name}}</strong> {{symbol}}</p>',
    engine : Hogan
  });

  var socket = new SockJS('/quote');
  var stompClient = Stomp.over(socket);
  var symbol = getURLParameter('symbol', null);

  var appModel = new ApplicationModel(stompClient);
  ko.applyBindings(appModel);

  appModel.connect(symbol);

})();

function ApplicationModel(stompClient) {
  var self = this;

  self.username = ko.observable();
  self.quote = ko.observable(new QuoteModel());
  self.notifications = ko.observableArray();

  self.connect = function(symbol) {
    stompClient.connect('guest', 'guest', function(frame) {

      console.log('Connected ' + frame);
      var userName = frame.headers['user-name'];
      var queueSuffix = frame.headers['queue-suffix'];

      self.username(userName);

      stompClient.subscribe("/app/ticker/" + symbol, function(message) {
        // console.log("ticker message: " + message);
        self.quote().loadTicker(JSON.parse(message.body));
      });
      stompClient.subscribe("/topic/quote/" + symbol, function(message) {
        // console.log("quote message: " + message);
        self.quote().processQuote(JSON.parse(message.body));
      });

      stompClient.subscribe("/queue/errors" + queueSuffix, function(message) {
        self.pushNotification("Error " + message.body);
      });
    }, function(error) {
      console.log("STOMP protocol error " + error);
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

function QuoteModel() {
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

  self.formattedVolumn = ko.computed(function() {
    return (self.vol() / 1000000).toFixed(2) + "万手";
  });

  self.formattedAmount = ko.computed(function() {
    return (self.amount() / 100000000).toFixed(2) + "亿元";
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

  self.loadTicker = function(ticker) {
    self.ticker(ticker.symbol);
    self.company(ticker.name);
    self.prevClose(ticker.prevClose);

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