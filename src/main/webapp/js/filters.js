'use strict';

/**
 * WebSocket Quote :: Filters
 * @author  Lu Huiguo <luhuiguo@gmail.com>
 * @version $Id$
 */
angular.module('app.filters', [])
  .filter('ticker', [
    function() {
      return function(tickers, symbol) {
        if (angular.isArray(tickers)) {
          for (var i = 0; i < tickers.length; i++) {
            var ticker = tickers[i];
            if (angular.equals(ticker.symbol, symbol)) {
              return ticker;
            }

          }

        }
        return null;
      };
    }
  ])
  .filter('price', ['$filter',
    function($filter) {
      return function(price) {
        return $filter('number')(price / 1000, 2);

      };

    }
  ])
  .filter('percent', ['$filter',
    function($filter) {
      return function(percent) {
        return $filter('number')(percent * 100, 2) + '%';

      };

    }
  ])  
  .filter('amount', ['$filter',
    function($filter) {
      return function(amount) {
        if (amount > 100000000) {
          return $filter('number')(amount / 100000000, 4) + '亿元';

        } else if (amount > 10000) {
          return $filter('number')(amount / 10000, 2) + '万元';
        } else {
          return $filter('number')(amount, 2) + '元';
        }

      };

    }
  ])
  .filter('volume', ['$filter',
    function($filter) {
      return function(volume) {
        if (volume > 1000000) {
          return $filter('number')(volume / 1000000, 2) + '万手';
        } else if (volume > 100) {
          return $filter('number')(volume / 100, 0) + '手';
        } else {
          return $filter('number')(volume, 0) + '股';
        }

      };

    }
  ])
  .filter('lot', ['$filter',
    function($filter) {
      return function(volume) {
        return $filter('number')(volume / 100 , 0);

      };

    }
  ]);