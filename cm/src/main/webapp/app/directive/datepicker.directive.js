(function() {

	/**
	 * Inicializa o componente datepicker do materializecss
	 */

	var DatepickerDirective = function() {
		return {
			restrict : 'C',
			link : function($scope, $element, $timeout) {
				$element
						.datepicker({
							autoClose : true,
							format : 'dd/mm/yyyy',
							setDefaultDate : true,
							defaultDate : new Date(),
							i18n : {
								cancel : 'Cancelar',
								done : 'Ok',
								weekdaysAbbrev : [ 'D', 'S', 'T', 'Q', 'Q',
										'S', 'S' ],
								weekdaysShort : [ 'Dom', 'Seg', 'Ter', 'Qua',
										'Qui', 'Sex', 'Sáb' ],
								weekdays : [ 'Domingo', 'Segunda-Feira',
										'Terça-Feira', 'Quarta-Feira',
										'Quinta-Feira', 'Sexta-Feira', 'Sábado' ],
								monthsShort : [ 'Jan', 'Fev', 'Mar', 'Abr',
										'Mai', 'Jun', 'Jul', 'Ago', 'Set',
										'Out', 'Nov', 'Dez' ],
								months : [ 'Janeiro', 'Fevereiro', 'Março',
										'Abril', 'Maio', 'Junho', 'Julho',
										'Agosto', 'Setembro', 'Outubro',
										'Novembro', 'Dezembro' ]
							}
						});
			}
		};
	};

	angular.module('app.directives').directive('datepicker',
			DatepickerDirective);

})();
