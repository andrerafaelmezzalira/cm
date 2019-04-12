(function() {

	/**
	 * Representa o serviço de acesso ao robo de processamento
	 * 
	 */
	function HeaderService($http) {

		return ({
			processar : processar,
			consultar : consultar
		});

		/**
		 * Processa determinado serviço/site
		 */
		function processar(header) {
			return $http.post('api/processar', header);
		}

		/**
		 * Consulta determinado serviço/site
		 */
		function consultar(header) {
			return $http.get('api/consultar?header='
					+ encodeURIComponent(JSON.stringify(header)));
		}

	}

	HeaderService.$inject = [ '$http' ];

	angular.module('app.services').service('HeaderService', HeaderService);
})();
