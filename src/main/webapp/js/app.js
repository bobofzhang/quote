
'use strict';

/**
 * WebSocket Quote :: Application
 * @author  Lu Huiguo <luhuiguo@gmail.com>
 * @version $Id$
 */
angular.module('app', [
	'ngRoute',
	'ui.bootstrap',
	'app.filters',
	'app.services',
	'app.directives',
	'app.controllers'
]).
config(['$routeProvider',
	function($routeProvider) {
		$routeProvider
			.when('/:symbol', {
				templateUrl: 'partials/main.html',
				controller: 'quoteMainCtrl',
				resolve: {
					quoteServiceInited: function(quoteService) {
						quoteService.init();
						return quoteService.inited;
					}
				}
			})
			.otherwise({
				redirectTo: '/600570.SH'
			});
	}
]);