(function() {

	angular.module('app.services', [])
			.service('SessionService', SessionService);

	SessionService.$inject = [ '$window' ];

	function SessionService($window) {

		var getToken = function() {
			var token = $window.sessionStorage.getItem('access_token');
			return token;
		};

		var setToken = function(data) {
			$window.sessionStorage.setItem('access_token', data);
		};

		var removeToken = function() {
			$window.sessionStorage.removeItem('access_token');
		};

		var getUser = function() {
			var token = getToken();
			return token;
		};

		return {
			getUser : getUser,
			getToken : getToken,
			setToken : setToken,
			removeToken : removeToken
		};

	}

})();
