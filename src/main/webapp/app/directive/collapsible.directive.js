(function() {

	/**
	 * Inicializa o componente collapsible do materializecss
	 */

	var CollapsibleDirective = function() {
		return {
			restrict : 'C',
			link : function($scope, $element) {
				$element.collapsible();
			}
		};
	};

	angular.module('app.directives').directive('collapsible', CollapsibleDirective);

})();
