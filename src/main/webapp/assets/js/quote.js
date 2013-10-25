function getURLParameter(name,defaultValue) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,defaultValue])[1]
    );
}

(function() {
  $('[data-toggle=offcanvas]').click(function() {
    $('.row-offcanvas').toggleClass('active');
  });
  
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

  var appModel = new ApplicationModel(stompClient);
  ko.applyBindings(appModel);

  appModel.connect();
  
  
})();

function ApplicationModel(stompClient) {
  var self = this;

  self.username = ko.observable();
  self.symbol = ko.observable();
  var symbol = getURLParameter('symbol','600570.SH');

  self.symbol(symbol);
  //self.instrument = ko.observable(new InstrumentModel());
  //self.trade = ko.observable(new TradeModel(stompClient));

  self.connect = function() {
    stompClient.connect('guest', 'guest', function(frame) {

      console.log('Connected ' + frame);
      var userName = frame.headers['user-name'];
      var queueSuffix = frame.headers['queue-suffix'];
      
      self.username(userName);

      stompClient.subscribe("/app/instrument/"+symbol, function(message) {
        console.log("message: " + message);
        //self.instrument().loadInstrument(JSON.parse(message.body));
      });
      stompClient.subscribe("/topic/quote/"+symbol, function(message) {
        console.log("message: " + message);
       // self.instrument().processQuote(JSON.parse(message.body));
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