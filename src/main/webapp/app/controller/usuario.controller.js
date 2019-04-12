(function() {

	/**
	 * 
	 * Representa o controlador da tela de usuario
	 * 
	 */
	function UsuarioController($timeout, Site, UsuarioService, HeaderService, SessionService) {

		var vm = this;
		vm.validar = validar;
		vm.getSite = getSite;
		vm.processar = processar;
		vm.addParametro = addParametro;
		vm.removerParametro = removerParametro;
		vm.orderByPosicao = orderByPosicao;
		vm.consultar = consultar;
		vm.getDocumentacao = getDocumentacao;
		
		init();
		
		function init() {
			vm.manter = {};
			vm.manter.sites = Site;
			vm.manter.usuario = JSON.parse(SessionService.getUser());
		}

		function orderByPosicao(site) {
			let obj = vm.getSite(site.nome,'nome');
			if (obj) {
				return obj.posicao
			}
		}


		/**
		 * Valida se o usuario é valido
		 */
		function validar(form) {
			UsuarioService.validar(vm.manter.usuario).then(function(response) {
				vm.manter.usuario = response.data;
				SessionService.setToken(JSON.stringify(vm.manter.usuario));
			}).catch(function(response) {
				form.cpfCnpj.$setValidity('cpfCnpj', false);
				form.cpfCnpj.$invalid = true;
				form.cpfCnpj.errorMessage = response.data;
			});
		}

		/**
		 * Processa o robo
		 */
		function processar(form) {

			form.file.$setValidity('file', true);
			form.file.$invalid = false;

			if (vm.manter.header.parcelas) {
				if (!Array.isArray(vm.manter.header.parcelas)) {
					let values = vm.manter.header.parcelas;
					let str = values.split(',');
					let strParcelas = [];
					for (let n = 0; n < str.length; n++) {
						strParcelas.push(str[n]);
					}
					vm.manter.header.parcelas = strParcelas;
				}
			}

			HeaderService
					.processar(vm.manter.header)
					.then(
							function(response) {
								vm.manter.parametros = response.data.parametros;
								tree();
							}).catch(function(response) {
								form.file.$setValidity('file', false);
								form.file.$invalid = true;
								form.file.errorMessage = response.data;
							});
			delete vm.manter.parametro; 
			delete vm.manter.opcionalNomeParametro; 
			delete vm.manter.opcionalValorParametro;
		}

		function getDataHora() {
			let dataHora = '';
			let arrDate = vm.manter.datepicker.toString().split('/');
			dataHora += arrDate[2];
			dataHora += '-';
			dataHora += arrDate[1];
			dataHora += '-';
			dataHora += arrDate[0];
			dataHora += 'T';
			if (vm.manter.timepicker) {
				let arrTime = vm.manter.timepicker.toString().split(':');
				dataHora += arrTime[0];
				dataHora += ':';
				dataHora += arrTime[1].split(' ')[0];
			} else {
				dataHora += '00:00';
			}
			dataHora += ':00';
			return dataHora;
		}
		
		/**
		 * Consulta o robo
		 */
		function consultar(form) {
			
			vm.manter.consultaHeader = {
				cpfCnpj : vm.manter.header.cpfCnpj,
				site : vm.manter.header.site,
				servico : vm.manter.header.servico,
				dataHora : getDataHora()
			};
			
			HeaderService
					.consultar(vm.manter.consultaHeader)
					.then(
							function(response) {
								vm.manter.headers = response.data;
								treeConsulta();
							});
		}

		/**
		 * obtem a constant do site pelo nome do site ou nome do servico
		 */
		function getSite(nome, field) {
			for (let i =0; i < vm.manter.sites.length; i++) {
				if (vm.manter.sites[i][field] === nome) {
					return vm.manter.sites[i];
				}
			}
		}

		/**
		 * obtem a constant do site pelo nome do site ou nome do servico
		 */
		function getDocumentacao(site, servico) {
			for (let i =0; i < vm.manter.sites.length; i++) {
				if (vm.manter.sites[i].nome === site && vm.manter.sites[i].servico === servico) {
					return vm.manter.sites[i];
				}
			}
		}

		function addParametro() {
			
			if (!vm.manter.parametro) {
				vm.manter.parametro = {};
				vm.manter.header.parametros.push(vm.manter.parametro)
			}
			
			vm.manter.parametro[vm.manter.opcionalNomeParametro.field] = vm.manter.opcionalValorParametro; 
			delete vm.manter.opcionalNomeParametro; 
			delete vm.manter.opcionalValorParametro;
		}

		function removerParametro(index) {
			$timeout(function(){
				vm.manter.header.parametros.splice(index, 1); 
				delete vm.manter.parametro;
				delete vm.manter.opcionalNomeParametro; 
				delete vm.manter.opcionalValorParametro;
			}, 500);
		}
		
		function tree() {
			
			$('.rootConsulta').html('');
			$('.root').html('');
			let campos = vm.getSite(vm.manter.header.site, 'nome').campos;
			let parametros = vm.manter.parametros; 
			var arrayResultados = [];
			for ( let i in parametros) {
				let estado = JSON.parse(parametros[i]['estado']);
				parametros[i]['estado'] = estado.mensagem;
				let resultado = parametros[i]['resultado'];
				if (resultado) {
					let ars = JSON.parse(resultado);
					parametros[i]['resultado'] = ars;
					if (Array.isArray(ars)) {
						for (let j = 0; j < ars.length; j++) {
							let object = {
							};
							for (let p = 0; p < campos.length; p++) {
								object[campos[p].field] = parametros[i][campos[p].field]
							}
							for (let y in ars[j]) {
								object[y] = ars[j][y];
							}
							arrayResultados.push(object);
						}
					} else {
						let object = {
						};
						for (let p = 0; p < campos.length; p++) {
							object[campos[p].field] = parametros[i][campos[p].field]
						}
						for (let y in ars) {
							object[y] = ars[y];
						}
						arrayResultados.push(object);
					}
				}
			}
			jsonView.format(JSON.stringify(parametros), '.root');
			if (arrayResultados.length !== 0) {
				var download = $(
				"<i title=\"Download\" style=\"cursor: pointer;\" class=\"material-icons\">file_download</i>")
				.bind(
						"click",
						function() {
						    let wb = XLSX.utils.book_new(),
					        ws = XLSX.utils.json_to_sheet(arrayResultados);
						    XLSX.utils.book_append_sheet(wb, ws, vm.manter.header.site);
						    XLSX.writeFile(wb, vm.manter.header.site + ".xlsx");
						});
				$("div[class=json-type]:contains('array')").text('Parâmetros').next().after(download);
			} else {
				$("div[class=json-type]:contains('array')").text('Parâmetros');
			}
			$("div[class=json-key]:contains('pdfs')").text('pdf(s)');
			pdf();

		}

		function treeConsulta() {

			$('.rootConsulta').html('');
			$('.root').html('');
			let campos = vm.getSite(vm.manter.header.site, 'nome').campos;
			let headers = vm.manter.headers; 
			var arrayResultados = [];
			for ( let j in headers) {
				let dataHoraInicio = headers[j]['dataHoraInicio'];
				if (dataHoraInicio) {
					headers[j]['dataHoraInicio'] = (dataHoraInicio.dayOfMonth.toString().length == 1 ? '0' + dataHoraInicio.dayOfMonth : dataHoraInicio.dayOfMonth) + '/' + (dataHoraInicio.monthValue.toString.length === 1 ? '0' + dataHoraInicio.monthValue : dataHoraInicio.monthValue) + '/' + dataHoraInicio.year + ' ' + (dataHoraInicio.hour.toString().length === 1 ? '0' + dataHoraInicio.hour : dataHoraInicio.hour) + ':' + (dataHoraInicio.minute.toString().length === 1 ? '0' + dataHoraInicio.minute : dataHoraInicio.minute);
				}
				let dataHoraFim = headers[j]['dataHoraFim'];
				if (dataHoraFim) {
					headers[j]['dataHoraFim'] = (dataHoraFim.dayOfMonth.toString().length == 1 ? '0' + dataHoraFim.dayOfMonth : dataHoraFim.dayOfMonth) + '/' + (dataHoraFim.monthValue.toString.length === 1 ? '0' + dataHoraFim.monthValue : dataHoraFim.monthValue) + '/' + dataHoraFim.year + ' ' + (dataHoraFim.hour.toString().length === 1 ? '0' + dataHoraFim.hour : dataHoraFim.hour) + ':' + (dataHoraFim.minute.toString().length === 1 ? '0' + dataHoraFim.minute : dataHoraFim.minute);
				}
				let dataHoraInicioReprocessamento = headers[j]['dataHoraInicioReprocessamento'];
				if (dataHoraInicioReprocessamento) {
					headers[j]['dataHoraInicioReprocessamento'] = (dataHoraInicioReprocessamento.dayOfMonth.toString().length == 1 ? '0' + dataHoraInicioReprocessamento.dayOfMonth : dataHoraInicioReprocessamento.dayOfMonth) + '/' + (dataHoraInicioReprocessamento.monthValue.toString.length === 1 ? '0' + dataHoraInicioReprocessamento.monthValue : dataHoraInicioReprocessamento.monthValue) + '/' + dataHoraInicioReprocessamento.year + ' ' + (dataHoraInicioReprocessamento.hour.toString().length === 1 ? '0' + dataHoraInicioReprocessamento.hour : dataHoraInicioReprocessamento.hour) + ':' + (dataHoraInicioReprocessamento.minute.toString().length === 1 ? '0' + dataHoraInicioReprocessamento.minute : dataHoraInicioReprocessamento.minute);
				}
				let dataHoraFimReprocessamento = headers[j]['dataHoraFimReprocessamento'];
				if (dataHoraFimReprocessamento) {
					headers[j]['dataHoraFimReprocessamento'] = (dataHoraFimReprocessamento.dayOfMonth.toString().length == 1 ? '0' + dataHoraFimReprocessamento.dayOfMonth : dataHoraFimReprocessamento.dayOfMonth) + '/' + (dataHoraFimReprocessamento.monthValue.toString.length === 1 ? '0' + dataHoraFimReprocessamento.monthValue : dataHoraFimReprocessamento.monthValue) + '/' + dataHoraFimReprocessamento.year + ' ' + (dataHoraFimReprocessamento.hour.toString().length === 1 ? '0' + dataHoraFimReprocessamento.hour : dataHoraFimReprocessamento.hour) + ':' + (dataHoraFimReprocessamento.minute.toString().length === 1 ? '0' + dataHoraFimReprocessamento.minute : dataHoraFimReprocessamento.minute);
				}
				for ( let k in headers[j]) {
					if (Array.isArray(headers[j][k])) {
						let parametros = headers[j][k];
						for (let n = 0; n < parametros.length; n++) {
							let estado = JSON.parse(parametros[n]['estado']);
							parametros[n]['estado'] = estado.mensagem;
							let resultado = parametros[n]['resultado'];
							if (resultado) {
								let ars = JSON.parse(resultado);
								parametros[n]['resultado'] = ars;
								if (Array.isArray(ars)) {
									for (let x = 0; x < ars.length; x++) {
										let object = {
										};
										for (let p = 0; p < campos.length; p++) {
											object[campos[p].field] = parametros[n][campos[p].field]
										}
										for (let y in ars[x]) {
											object[y] = ars[x][y];
										}
										arrayResultados.push(object);
									}
								} else {
									let object = {
									};
									for (let p = 0; p < campos.length; p++) {
										object[campos[p].field] = parametros[n][campos[p].field]
									}
									for (let y in ars) {
										object[y] = ars[y];
									}
									arrayResultados.push(object);
								}
							} 
						}
					}
				}
			}	
			jsonView.format(JSON.stringify(headers), '.rootConsulta');
			
			if (arrayResultados.length !== 0) {
				var download = $(
				"<i title=\"Download\" style=\"cursor: pointer;\" class=\"material-icons\">file_download</i>")
				.bind(
						"click",
						function() {
						    let wb = XLSX.utils.book_new(),
					        ws = XLSX.utils.json_to_sheet(arrayResultados);
						    XLSX.utils.book_append_sheet(wb, ws, vm.manter.header.site);
						    XLSX.writeFile(wb, vm.manter.header.site + ".xlsx");
						});

				$("div[class=json-type]:contains('array')").text('Header').next().after(download);
			} else {
				$("div[class=json-type]:contains('array')").text('Header');
			}

			$("div[class=json-key]:contains('pdfs')").text('pdf(s)');
			pdf();
		}

		function pdf() {
			$("div[class=json-key]:contains('pdf(s)')")
			.each(
					function(index) {
						var array = JSON.parse($(
								this).next().next()
								.text());
						$(this).next().next().html(
								'');
						for (let j = 0; j < array.length; j++) {
							var ahref = $(
									"<i title=\"Vizualizar PDF\" style=\"cursor: pointer; color: red;\" class=\"material-icons\">picture_as_pdf</i>")
									.bind(
											"click",
											function() {

												var objbuilder = '';
												objbuilder += ('<object width="100%" height="100%" data="data:application/pdf;base64,');
												objbuilder += (encodeURI(array[j]));
												objbuilder += ('" type="application/pdf" class="internal">');
												objbuilder += ('<embed src="data:application/pdf;base64,');
												objbuilder += (encodeURI(array[j]));
												objbuilder += ('" type="application/pdf"  />');
												objbuilder += ('</object>');

												var win = window
														.open(
																"#",
																"_blank");
												var title = "pdf";
												win.document
														.write('<html><title>'
																+ title
																+ '</title><body style="margin-top:0px; margin-left: 0px; margin-right: 0px; margin-bottom: 0px;">');
												win.document
														.write(objbuilder);
												win.document
														.write('</body></html>');
												jQuery(win.document);
											});
							$(this).next().next()
									.prepend(ahref);
						}
					});
		}
	}

	UsuarioController.$inject = [ '$timeout', 'Site', 'UsuarioService', 'HeaderService', 'SessionService' ];

	angular.module('app.controllers').controller('UsuarioController',
			UsuarioController);

})();
