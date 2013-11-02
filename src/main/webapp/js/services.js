'use strict';

/**
 * WebSocket Quote :: Services
 * @author  Lu Huiguo <luhuiguo@gmail.com>
 * @version $Id$
 */
angular.module('app.services', [])
  .value('version', '0.1')
  .value('webSocketUrl', '/quote')
  .factory('quoteService', ['$q', '$cacheFactory', '$filter', 'webSocketUrl',
    function($q, $cacheFactory, $filter, webSocketUrl) {
      var quoteService = {};
      var tickerCache = $cacheFactory('ticker');

      var socket = new SockJS(webSocketUrl);
      var stompClient = Stomp.over(socket);
      quoteService.stompClient = stompClient;

      quoteService.init = function() {
        var deferred = $q.defer();
        if (!quoteService.initData) {

          stompClient.connect('guest', 'guest', function(frame) {
            var userName = frame.headers['user-name'];
            var queueSuffix = frame.headers['queue-suffix'];
            deferred.notify('Init QuoteService');
            stompClient.subscribe('/app/init', function(message) {
              var initData = JSON.parse(message.body);
              quoteService.initData = initData;
              deferred.resolve(initData);
            });
          }, function(error) {
            deferred.reject(error);
            console.log('STOMP protocol error ' + error);
          });
        } else {
          deferred.resolve();

        }
        quoteService.inited = deferred.promise;
      };



      quoteService.getTicker = function(symbol){

        var ticker = tickerCache.get(symbol);
        if (!ticker){
          ticker = $filter('ticker')(quoteService.initData.tickers, symbol);
          if (ticker){
            tickerCache.put(symbol,ticker);
          }

        }

        return ticker;
      };


      quoteService.getExchange = function(symbol){

        var c = symbol.split('.')[1];

        var ex = quoteService.initData.exchanges[c];

        return ex;
      };

      quoteService.getTimes = function(symbol){


        var ex = quoteService.getExchange(symbol);

        if (ex){
          return ex.times;
        }
        return [];
      };

      quoteService.getDate = function(symbol){
        var ex = quoteService.getExchange(symbol);

        if (ex){
          return ex.date;
        }
        return undefined;
      };


      return quoteService;
    }
  ]);