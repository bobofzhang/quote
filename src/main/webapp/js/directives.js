'use strict';

/**
 * WebSocket Quote :: Directives
 * @author  Lu Huiguo <luhuiguo@gmail.com>
 * @version $Id$
 */
angular.module('app.directives', []).
directive('appVersion', ['version',
	function(version) {
		return function(scope, elm, attrs) {
			elm.text(version);
		};
	}
]);