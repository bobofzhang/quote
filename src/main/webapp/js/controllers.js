'use strict';

/**
 * WebSocket Quote :: Controllers
 * @author  Lu Huiguo <luhuiguo@gmail.com>
 * @version $Id$
 */
angular.module('app.controllers', [])
  .controller('quoteMainCtrl', ['$scope', '$routeParams', '$filter', 'quoteService',
    function($scope, $routeParams, $filter, quoteService) {

      var ticker = quoteService.getTicker($routeParams.symbol);

      if (ticker) {
        $scope.ticker = ticker;

        var symbol = ticker.symbol;
        var stompClient = quoteService.stompClient;
        var subQuote, subTrend, subKline, subTick;


        subQuote = stompClient.subscribe("/topic/quote/" + symbol, function(message) {
          $scope.$broadcast('QUOTE', angular.fromJson(message.body));
        });

        subTrend = stompClient.subscribe("/queue/trend/" + symbol, function(message) {
          $scope.$broadcast('TREND', angular.fromJson(message.body));
        });

        subKline = stompClient.subscribe("/queue/kline/" + symbol, function(message) {
          $scope.$broadcast('KLINE', angular.fromJson(message.body));
        });

        subTick = stompClient.subscribe("/queue/tick/" + symbol, function(message) {
          $scope.$broadcast('TICK', angular.fromJson(message.body));
        });

        stompClient.send("/app/subscribe/" + symbol, {}, '');
        stompClient.send("/app/trend/" + symbol, {}, '');
        stompClient.send("/app/tick/" + symbol, {}, '');


        $scope.$on('$destroy', function() {
          subQuote.unsubscribe();
          subTrend.unsubscribe();
          subKline.unsubscribe();
          subTick.unsubscribe();
        });

      }



      $scope.toggleSideBar = function() {
        $('.row-offcanvas').toggleClass('active');
      };

    }
  ])
  .controller('quoteSearchCtrl', ['$scope', '$location', 'quoteService',
    function($scope, $location, quoteService) {

      $scope.tickers = quoteService.initData.tickers;

      $scope.selected = undefined;

      $scope.go = function() {
        $location.path('/' + $scope.selected);
      };

    }
  ])
  .controller('quotePriceCtrl', ['$scope', '$routeParams', 'quoteService',
    function($scope, $routeParams, quoteService) {
      var ticker = quoteService.getTicker($routeParams.symbol);

      if (ticker) {
        $scope.company = ticker.name;
        $scope.symbol = ticker.symbol;
        $scope.prevClose = ticker.prevClose;
        $scope.updateQuote = function(msg) {
          $scope.$apply(function() {
            var data = msg.data;
            var other = msg.otherData;
            $scope.price = data.price;
            $scope.volume = data.vol;
            $scope.amount = data.amount;
            $scope.high = data.high;
            $scope.low = data.low;
            $scope.open = data.open;

            $scope.change = $scope.price - $scope.prevClose;
            $scope.changeRate = $scope.change / $scope.prevClose;
            $scope.amplitude = ($scope.high - $scope.low) / $scope.prevClose;

          });
        };


        $scope.$on('TREND', function(event, msg) {
          $scope.updateQuote(msg);
        });

        $scope.$on('QUOTE', function(event, msg) {

          $scope.updateQuote(msg);

        });

      }



    }
  ])
  .controller('quoteDetailCtrl', ['$scope', '$routeParams', 'quoteService',
    function($scope, $routeParams, quoteService) {
      var ticker = quoteService.getTicker($routeParams.symbol);

      if (ticker) {
        $scope.prevClose = ticker.prevClose;
        $scope.updateDetail = function(msg) {
          $scope.$apply(function() {
            var data = msg.data;
            var other = msg.otherData;
            $scope.price = data.price;

            $scope.bid1 = data.bid1;
            $scope.bid2 = data.bid2;
            $scope.bid3 = data.bid3;
            $scope.bid4 = data.bid4;
            $scope.bid5 = data.bid5;
            $scope.bidSize1 = data.bidSize1;
            $scope.bidSize2 = data.bidSize2;
            $scope.bidSize3 = data.bidSize3;
            $scope.bidSize4 = data.bidSize4;
            $scope.bidSize5 = data.bidSize5;

            $scope.ask1 = data.ask1;
            $scope.ask2 = data.ask2;
            $scope.ask3 = data.ask3;
            $scope.ask4 = data.ask4;
            $scope.ask5 = data.ask5;
            $scope.askSize1 = data.askSize1;
            $scope.askSize2 = data.askSize2;
            $scope.askSize3 = data.askSize3;
            $scope.askSize4 = data.askSize4;
            $scope.askSize5 = data.askSize5;

            $scope.change = $scope.price - $scope.prevClose;

            $scope.current = other.current;
            $scope.buyVol = other.buyVol;
            $scope.sellVol = other.sellVol;

            var bidSize = data.bidSize1 + data.bidSize2 + data.bidSize3 + data.bidSize4 + data.bidSize5;
            var askSize = data.askSize1 + data.askSize2 + data.askSize3 + data.askSize4 + data.askSize5
            $scope.committee = bidSize - askSize;
            $scope.committeeRate = (bidSize - askSize) / (bidSize + askSize);

          });
        }


        $scope.$on('TREND', function(event, msg) {
          $scope.updateDetail(msg);
        });

        $scope.$on('QUOTE', function(event, msg) {
          $scope.updateDetail(msg);
        });



      }



    }
  ])
  .controller('quoteTickCtrl', ['$scope', '$routeParams', 'quoteService',
    function($scope, $routeParams, quoteService) {

      var symbol = $routeParams.symbol;
      var ticker = quoteService.getTicker(symbol);

      if (ticker) {
        $scope.prevClose = ticker.prevClose;
        var date = quoteService.getDate(symbol);

        var times = quoteService.getTimes(symbol);

        $scope.ticks = [];

        $scope.addTick = function(tick) {
          $scope.$apply(function() {
            $scope.ticks.unshift(tick);
            if ($scope.ticks.length > 5) {
              $scope.ticks.pop();
            }
          });
        };

        $scope.$on('TICK', function(event, msg) {
          for (var i = 0; i < msg.ticks.length; i++) {
            var t = msg.ticks[i];

            var m = moment(date, 'YYYYMMDD').add('m', times[t.time]).add('s', t.second).format('HH:mm:ss');

            var v = t.vol;
            if (i > 1) {
              v = v - msg.ticks[i - 1].vol;
            }

            $scope.addTick({
              time: m,
              price: t.price,
              volume: v
            });
          }

        });

        $scope.$on('QUOTE', function(event, msg) {
          var data = msg.data;
          var other = msg.otherData;
          var m = moment(date, 'YYYYMMDD').add('m', times[other.time - 1]).add('s', other.second)
            .format('HH:mm:ss');

          self.addTick({
            time: m,
            price: data.price,
            volume: other.current
          });

        });

      }



    }
  ])
  .controller('quoteTrendCtrl', ['$scope', '$routeParams', 'quoteService',
    function($scope, $routeParams, quoteService) {

      var symbol = $routeParams.symbol;
      var ticker = quoteService.getTicker(symbol);

      var requestTrend = function() {
        var stompClient = quoteService.stompClient;
        stompClient.send("/app/trend/" + symbol, {}, '');
      };

      $scope.data = [];

      if (ticker) {

        $scope.$on('TREND', function(event, msg) {
          var date = quoteService.getDate(symbol);
          var times = quoteService.getTimes(symbol);
          var items = msg.items;

          var data = [];

          for (var i = 0; i < times.length; i++) {

            var m = +moment(self.date, 'YYYYMMDD').add('minutes', times[i]);
            if (items.length > i) {
              if (i > 1) {
                data.push([m, items[i].price / 1000, items[i].vol - items[i - 1].vol]);
              } else {
                data.push([m, items[i].price / 1000, items[i].vol]);
              }
            } else {
              data.push([m, null, null]);
            }
          }
          $scope.items = items;
          $scope.data = data;

        });

        $scope.$on('QUOTE', function(event, msg) {
          var date = quoteService.getDate(symbol);
          var times = quoteService.getTimes(symbol);
          var data = msg.data;
          var other = msg.otherData;

          var items = $scope.items;

          var t = other.time;

          if (items.length - 1 > t) {
            requestTrend();
          } else {
            if (items.length == 0) {
              var item = {};
              item.price = self.prevClose;
              item.vol = 0;
              items.push(item);
            }

            while (items.length <= t) {
              var last = items[items.length - 1];
              var item = {};
              item.price = last.price;
              item.vol = last.vol;
              items.push(item);
            }

            var last = items[items.length - 1];

            last.price = data.price;
            last.vol = data.vol;

            var data = [];

            for (var i = 0; i < times.length; i++) {

              var m = +moment(self.date, 'YYYYMMDD').add('minutes', times[i]);
              if (items.length > i) {
                if (i > 1) {
                  data.push([m, items[i].price / 1000, items[i].vol - items[i - 1].vol]);
                } else {
                  data.push([m, items[i].price / 1000, items[i].vol]);
                }
              } else {
                data.push([m, null, null]);
              }
            }

            $scope.data = data;


          }


        });


        requestTrend();

      }

    }
  ])
  .controller('quoteKlineCtrl', ['$scope', '$routeParams', 'quoteService',
    function($scope, $routeParams, quoteService) {

      var symbol = $routeParams.symbol;
      var ticker = quoteService.getTicker(symbol);

      var requestKline = function() {
        var stompClient = quoteService.stompClient;
        stompClient.send("/app/kline/" + symbol, {}, '');
      };

      $scope.data = [];

      if (ticker) {

        $scope.$on('KLINE', function(event, msg) {
          var date = quoteService.getDate(symbol);

          var data = [];

          for (var i = 0; i < msg.length; i++) {
            var date = +moment(msg[i].date, 'YYYYMMDD');

            data.push([date, // the date
              msg[i].open / 1000, // open
              msg[i].high / 1000, // high
              msg[i].low / 1000, // low
              msg[i].close / 1000, // close
              msg[i].volume // the volume
            ]);
          }



          $scope.data = data;



        });


        $scope.$on('QUOTE', function(event, msg) {

          var data = msg.data;
          var other = msg.otherData;

          var d = +moment(data.date, 'YYYYMMDD');

          var chartData = $scope.data;
          if (chartData.length > 0) {
            if (chartData[chartData.length - 1][0] > d) {
              requestKline();
            } else if (chartData[chartData.length - 1][0] == d) {
              var p = chartData[chartData.length - 1];
              p[1] = data.open / 1000;
              p[2] = data.high / 1000;
              p[3] = data.low / 1000;
              p[4] = data.price / 1000;
              p[5] = data.vol;
            }

          } else {
            chartData.push([d, // the date
              data.open / 1000, // open
              data.high / 1000, // high
              data.low / 1000, // low
              data.price / 1000, // close
              data.vol
            ]);
          }


        });


        requestKline();

      }

    }
  ]);