(function() {

	/**
	 * Directive para input type file onchange
	 */

	var FileOnChangeDirective = function() {
		return {
			restrict : 'A',
			link : function(scope, element, attrs) {
				var list = scope.$eval(attrs.file);
				element
						.on(
								'change',
								function(event) {
									let file = event.target.files[0];
									let reader = new FileReader();
									reader.onload = function(e) {
										let wb = XLS.parse_xlscfb(XLS.CFB.read(
												e.target.result, {
													type : 'binary'
												}));
										wb.SheetNames
												.forEach(function(sheetName) {
													let oJS = XLS.utils
															.sheet_to_row_object_array(wb.Sheets[sheetName]);
													for ( let i in oJS) {
														let obj = oJS[i];
														let objAngular = {};
														for ( let j in obj) {
															objAngular[j] = obj[j];
														}
														scope
																.$apply(function() {
																	list
																			.push(objAngular);
																});
													}
												});
									};
									reader.readAsBinaryString(file);
								});
				element.on('$destroy', function() {
					element.off();
				});
			}
		};
	};

	angular.module('app.directives').directive('file', FileOnChangeDirective);

})();
