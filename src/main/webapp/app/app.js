(function() {

	/**
	 * Mapeia os pacotes e suas funcionalidades
	 */

	angular.module('app.constants', []);
	angular.module('app.directives', []);
	angular.module('app.services', []);
	angular.module('app.controllers', []);
	angular.module('app.modules', [ 'app.directives', 'app.constants',
			'app.services', 'app.controllers' ]);
	angular.module('app', [ 'app.modules', 'angular-loading-bar' ]);

})();
