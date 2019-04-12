(function() {

	/**
	 * Configura o spinner (loading)
	 */

	function Config(cfpLoadingBarProvider) {
		
		cfpLoadingBarProvider.parentSelector = '.loading';
		cfpLoadingBarProvider.spinnerTemplate = ' <div class="preloader-wrapper big active">'
				+ '<div class="spinner-layer spinner-green-only">'
				+ '<div class="circle-clipper left">'
				+ ' <div class="circle"></div>'
				+ '</div><div class="gap-patch">'
				+ ' <div class="circle"></div>'
				+ '</div><div class="circle-clipper right">'
				+ '<div class="circle"></div>' + '</div>' + '</div>' + '</div>';
	}

	Config.$inject = [ 'cfpLoadingBarProvider' ];

	angular.module('app').config(Config);

})();
