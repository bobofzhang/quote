function ApplicationModel(stompClient) {
  var self = this;

  self.username = ko.observable();
  self.portfolio = ko.observable(new PortfolioModel());
  self.trend = ko.observable(new TrendModel());

  self.notifications = ko.observableArray();

  self.connect = function() {
    stompClient.connect('', '', function(frame) {

      console.log('Connected ' + frame);
      var userName = frame.headers['user-name'];
      var queueSuffix = frame.headers['queue-suffix'];

      self.username(userName);

      stompClient.subscribe("/app/positions", function(message) {
        self.portfolio().loadPositions(JSON.parse(message.body));
      });
      stompClient.subscribe("/topic/price.stock.*", function(message) {
        self.portfolio().processQuote(JSON.parse(message.body));
        self.trend().processQuote(JSON.parse(message.body));
      });
      stompClient.subscribe("/queue/position-updates" + queueSuffix, function(message) {
        self.pushNotification("Position update " + message.body);
        self.portfolio().updatePosition(JSON.parse(message.body));
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

  self.logout = function() {
    stompClient.disconnect();
    window.location.href = "./logout.html";
  };

}

function TrendModel() {
  var self = this;
  self.currentRow = ko.observable({});
  self.showTrend = function(row) {
    self.currentRow(row);
    $('#trend-dialog').modal();
  };
  
  self.processQuote = function(quote) {
    if (self.currentRow().ticker == quote.ticker ) {
      console.log("quote.ticker " + quote.ticker);
      
      var series = self.chart.series[0];
      var x = (new Date()).getTime(),
      y = quote.price;
      series.addPoint([x, y], true, true);

    }
  };

  $('#trend-dialog').on('shown.bs.modal', function() {
    self.chart = new Highcharts.StockChart({
      chart: {
        renderTo: 'chart'
      },
      credits : {
        enabled : false
      },
      rangeSelector : {
        enabled : false
      },

      exporting : {
        enabled : false
      },

      series : [{
        name : 'Random data',
        data : (function() {
            // generate an array of random data
            var data = [], time = (new Date()).getTime(), i;

            for( i = -999; i <= 0; i++) {
                data.push([
                    time + i * 1000,
                    Math.round(Math.random() * 100)
                ]);
            }
            return data;
        })()
    }]
    });
  });

  $('#trend-dialog').on('hide.bs.modal', function() {
    self.currentRow({});

  });
}

function PortfolioModel() {
  var self = this;

  self.rows = ko.observableArray();

  self.totalShares = ko.computed(function() {
    var result = 0;
    for ( var i = 0; i < self.rows().length; i++) {
      result += self.rows()[i].shares();
    }
    return result;
  });

  self.totalValue = ko.computed(function() {
    var result = 0;
    for ( var i = 0; i < self.rows().length; i++) {
      result += self.rows()[i].value();
    }
    return "" + result.toFixed(2);
  });

  var rowLookup = {};

  self.loadPositions = function(positions) {
    for ( var i = 0; i < positions.length; i++) {
      var row = new PortfolioRow(positions[i]);
      self.rows.push(row);
      rowLookup[row.ticker] = row;
    }
  };

  self.processQuote = function(quote) {
    if (rowLookup.hasOwnProperty(quote.ticker)) {
      rowLookup[quote.ticker].updatePrice(quote.price);
    }
  };

  self.updatePosition = function(position) {
    rowLookup[position.ticker].shares(position.shares);
  };
};

function PortfolioRow(data) {
  var self = this;

  self.company = data.company;
  self.ticker = data.ticker;
  self.price = ko.observable(data.price);
  self.formattedPrice = ko.computed(function() {
    return "" + self.price().toFixed(2);
  });
  self.change = ko.observable(0);
  self.arrow = ko.observable();
  self.shares = ko.observable(data.shares);
  self.value = ko.computed(function() {
    return (self.price() * self.shares());
  });
  self.formattedValue = ko.computed(function() {
    return "" + self.value().toFixed(2);
  });

  self.updatePrice = function(newPrice) {
    var delta = (newPrice - self.price()).toFixed(2);
    self.arrow((delta < 0) ? '<i class="glyphicon glyphicon-arrow-down"></i>'
        : '<i class="glyphicon glyphicon-arrow-up"></i>');
    self.change((delta / self.price() * 100).toFixed(2));
    self.price(newPrice);
  };
};

