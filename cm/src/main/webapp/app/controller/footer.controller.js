(function() {

	/**
	 * 
	 * Representa o controlador do rodape
	 * 
	 */
	function FooterController(Versao) {

		var vm = this;

		init();

		function init() {
			vm.manter = {};
			vm.manter.versao = Versao;
		}
	}

	FooterController.$inject = [ 'Versao' ];

	angular.module('app.controllers').controller('FooterController',
			FooterController);

})();
