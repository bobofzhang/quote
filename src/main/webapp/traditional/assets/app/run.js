(function(curl) {

	var config = {

		baseUrl: '..',

		packages: [
			{ name: 'quote', location: '.', main: 'main' },

			// Add third-party packages here
			{ name: 'curl', location: 'lib/curl/src/curl' },
			{ name: 'msgs', location: 'lib/msgs', main: 'msgs' },
			{ name: 'poly', location: 'lib/poly' },
			{ name: 'knockout', location: 'lib/knockout', main: 'knockout' }
		],

		paths: {
			'css': 'css',
			'jquery': 'lib/jquery/jquery',
			'sockjs': 'lib/sockjs/sockjs',
			'bootstrap': {
				location: 'lib/bootstrap/dist/js/bootstrap.js',
				config: {
					loader: 'curl/loader/legacy',
					exports: 'jQuery',
					requires: ['jquery']
				}
			}
		},

		// Polyfill everything ES5-ish
		preloads: ['poly/all']

	};

	curl(config, ['quote']).then(success, fail);

	// Success! curl.js indicates that your app loaded successfully!
	function success () {
		console.log('Application loaded');
	}

	// Oops. curl.js indicates that your app failed to load correctly.
	function fail (ex) {
		console.log('an error happened during loading :\'(');
		console.log(ex.message);
		if (ex.stack) console.log(ex.stack);
	}

})(curl);
