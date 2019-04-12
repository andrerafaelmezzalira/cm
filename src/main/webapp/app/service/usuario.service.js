(function() {

	/**
	 * Representa o serviço de acesso ao servidor para usuario
	 */
	function UsuarioService($http) {

		var url = 'api/validarUsuario';

		return ({
			validar : validar
		});

		/**
		 * Valida se eh um usuario valido
		 */
		function validar(usuario) {
			return $http.get(url + '?usuario='
					+ encodeURIComponent(JSON.stringify(usuario)));
		}
	}

	UsuarioService.$inject = [ '$http' ];

	angular.module('app.services').service('UsuarioService', UsuarioService);
})();
