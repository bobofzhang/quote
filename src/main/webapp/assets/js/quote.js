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
  
  //self.instrument = ko.observable(new InstrumentModel());
  //self.trade = ko.observable(new TradeModel(stompClient));

  self.connect = function() {
    stompClient.connect('', '', function(frame) {

      console.log('Connected ' + frame);
      var userName = frame.headers['user-name'];
      var queueSuffix = frame.headers['queue-suffix'];
      
      self.username(userName);

//      stompClient.subscribe("/app/instrument", function(message) {
//        self.instrument().loadInstrument(JSON.parse(message.body));
//      });
//      stompClient.subscribe("/topic/price.stock.*", function(message) {
//        self.instrument().processQuote(JSON.parse(message.body));
//      });
//
//      stompClient.subscribe("/queue/errors" + queueSuffix, function(message) {
//        self.pushNotification("Error " + message.body);
//      });
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
  
  self.show = function(formElement) {
    var symbol = formElement.elements["symbol"].value; 
    self.symbol(symbol);
    
  };

}