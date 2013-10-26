function getURLParameter(name,defaultValue) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,defaultValue])[1]
    );
}

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
  var symbol = getURLParameter('symbol',null);

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

      stompClient.subscribe("/app/ticker/"+symbol, function(message) {
        //console.log("ticker message: " + message);
        self.quote().loadTicker(JSON.parse(message.body));
      });
      stompClient.subscribe("/topic/quote/"+symbol, function(message) {
        //console.log("quote message: " + message);
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
    self.notifications.push({notification: text});
    if (self.notifications().length > 5) {
      self.notifications.shift();
    }
  };
  

}

function QuoteModel() {
  var self = this;
  self.hasData = ko.observable(false);
  
  self.company = ko.observable();
  self.ticker = ko.observable();
  self.price = ko.observable();
  self.change = ko.observable();
  self.high = ko.observable();
  self.low = ko.observable();
  self.prevClose = ko.observable();
  self.open = ko.observable();  
  
  self.loadTicker = function(ticker) {
    self.hasData(ticker);
    self.ticker(ticker.symbol);
    self.company(ticker.name);
    self.prevClose(ticker.prevClose);

  };
  
  self.processQuote = function(quote) {
    self.price(quote.price);
    self.change((self.price() - self.prevClose()));
    self.high(quote.high);
    self.low(quote.low);

    self.open(quote.open);     

  };
  
  

}