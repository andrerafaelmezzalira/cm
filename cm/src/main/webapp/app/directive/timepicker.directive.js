(function() {

	/**
	 * Inicializa o componente timepicker do materializecss
	 */

	var TimepickerDirective = function() {
		return {
			restrict : 'C',
			link : function($scope, $element) {
				$element.timepicker({
					autoClose : true,
					twelveHour : false,
					i18n : {
						cancel : 'Cancelar',
						done : 'Ok'
					}
				});
			}
		};
	};

	angular.module('app.directives').directive('timepicker',
			TimepickerDirective);

})();
