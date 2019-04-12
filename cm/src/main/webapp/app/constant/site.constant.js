(function() {

	/**
	 * Representa os sites e os serviços
	 */
	var Site = [
			{
				posicao : 1,
				nome : 'BOMBEIRO_PERNAMBUCO',
				descricao : 'Bombeiros de Pernambuco - TPEI',
				link : 'https://tpei.bombeiros.pe.gov.br/tpeinet/intranet/dwl_ctudo-gerenc.asp?build=1',
				servico : 'DAM',
				descricaoServico : 'Conferência de DAM',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Sequencial',
					field : 'sequencial'
				} ],
				header : [ {
					label : 'Parcela(s)',
					field : 'parcelas'
				} ],
				documentacao : 'documentacao/bombeiroPernambuco/damBombeiroPernambuco.html'
			},
			{
				posicao : 2,
				nome : 'CELESC',
				descricao : 'Celesc',
				link : 'http://agenciaweb.celesc.com.br:8080/AgenciaWeb/autenticar/selecionarUC.do',
				servico : 'DEBITO',
				descricaoServico : 'Conferência de Débito',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Unidade Consumidora',
					field : 'unidadeConsumidora'
				}, {
					label : 'Cpf ou Cnpj',
					field : 'cpfCnpj'
				} ],
				header : [ {
					label : 'Login',
					field : 'login'
				}, {
					label : 'Senha',
					field : 'senha'
				} ],
				documentacao : 'documentacao/celesc/debitoCelesc.html'
			},
			{
				posicao : 3,
				nome : 'CELESC',
				descricao : 'Celesc',
				link : 'http://agenciaweb.celesc.com.br:8080/AgenciaWeb/autenticar/selecionarUC.do',
				servico : 'TITULARIDADE',
				descricaoServico : 'Conferência de Titularidade',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Unidade Consumidora',
					field : 'unidadeConsumidora'
				}, {
					label : 'Cpf ou Cnpj',
					field : 'cpfCnpj'
				} ],
				header : [ {
					label : 'Login',
					field : 'login'
				}, {
					label : 'Senha',
					field : 'senha'
				} ],
				documentacao : 'documentacao/celesc/titularidadeCelesc.html'
			},
			{
				posicao : 4,
				nome : 'CELPE',
				descricao : 'Celpe',
				link : 'http://servicos.celpe.com.br/servicos-ao-cliente/Pages/login-av.aspx?UrlUc=http://servicos.celpe.com.br/servicos-ao-cliente/Pages/2-via-conta_anti.aspx',
				servico : 'DEBITO',
				descricaoServico : 'Conferência de Débito',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Conta Contrato',
					field : 'contaContrato'
				}, {
					label : 'CPF/CNPJ',
					field : 'cpfCnpj'
				} ],
				documentacao : 'documentacao/celpe/debitoCelpe.html'
			},
			{
				posicao : 5,
				nome : 'COMPESA',
				descricao : 'Compesa',
				link : 'https://lojavirtual.compesa.com.br:8443/gsan/exibirServicosPortalCompesaAction.do?method=emitirSegundaViaConta&matriculaObrigatoria=true',
				servico : 'DEBITO',
				descricaoServico : 'Conferência de Débito',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Matrícula',
					field : 'matricula'
				} ],
				documentacao : 'documentacao/compesa/debitoCompesa.html'
			},
			{
				posicao : 6,
				nome : 'ENEL',
				descricao : 'Enel',
				link : 'https://www.eneldistribuicao.com.br/go/AgenciaVirtual.aspx',
				servico : 'DEBITO',
				descricaoServico : 'Conferência de Débito',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Unidade Consumidora',
					field : 'unidadeConsumidora'
				}, {
					label : 'Cpf ou Cnpj',
					field : 'cpfCnpj'
				} ],
				documentacao : 'documentacao/enel/debitoEnel.html'
			},
			{
				posicao : 7,
				nome : 'ENEL',
				descricao : 'Enel',
				link : 'https://www.eneldistribuicao.com.br/go/AgenciaVirtual.aspx',
				servico : 'TITULARIDADE',
				descricaoServico : 'Conferência de Titularidade',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Unidade Consumidora',
					field : 'unidadeConsumidora'
				}, {
					label : 'Cpf ou Cnpj',
					field : 'cpfCnpj'
				} ],
				documentacao : 'documentacao/enel/titularidadeEnel.html'
			},
			{
				posicao : 8,
				nome : 'FAZENDA_BRASILIA',
				descricao : 'Fazenda Brasília',
				link : 'http://www.fazenda.df.gov.br/area.cfm?id_area=54',
				servico : 'DAM',
				descricaoServico : 'Conferência de DAM',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Inscrição',
					field : 'inscricao'
				} ],
				header : [ {
					label : 'Parcela(s)',
					field : 'parcelas'
				} ],
				documentacao : 'documentacao/fazendaBrasilia/damFazendaBrasilia.html'
			},
			{
				posicao : 9,
				nome : 'FAZENDA_BRASILIA',
				descricao : 'Fazenda Brasília',
				link : 'http://www.fazenda.df.gov.br/area.cfm?id_area=449',
				servico : 'CND',
				descricaoServico : 'Conferência de CND',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Inscrição',
					field : 'inscricao'
				} ],
				documentacao : 'documentacao/fazendaBrasilia/cndFazendaBrasilia.html'
			},
			{
				posicao : 10,
				nome : 'GOIANIA',
				descricao : 'Prefeitura de Goiânia - IPTU',
				link : 'http://www.goiania.go.gov.br/sistemas/scarr/asp/scarr33000f0.asp',
				servico : 'DAM',
				descricaoServico : 'Conferência de DAM',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Inscrição',
					field : 'inscricao'
				} ],
				header : [ {
					label : 'Parcela(s)',
					field : 'parcelas'
				} ],
				documentacao : 'documentacao/goiania/damGoiania.html'
			},
			{
				posicao : 11,
				nome : 'PATRIMONIO_DE_TODOS',
				descricao : 'SPU',
				link : 'http://atendimentovirtual2.spu.planejamento.gov.br/Consultas/Fin_Pedido.asp',
				servico : 'DEBITO',
				descricaoServico : 'Conferência de Débito',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Rip',
					field : 'rip'
				} ],
				documentacao : 'documentacao/patrimonioDeTodos/debitoPatrimonioDeTodos.html'
			},
			{
				posicao : 12,
				nome : 'PATRIMONIO_DE_TODOS',
				descricao : 'SPU',
				link : 'http://atendimentovirtual2.spu.planejamento.gov.br/Consultas/Fin_Pedido.asp',
				servico : 'DAM',
				descricaoServico : 'Conferência de DAM',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Rip',
					field : 'rip'
				} ],
				documentacao : 'documentacao/patrimonioDeTodos/damPatrimonioDeTodos.html'
			},
			{
				posicao : 13,
				nome : 'PORTAL_FINANCAS',
				descricao : 'Portal Finanças',
				link : 'https://portalfinancas.recife.pe.gov.br/extratoDebitos/1',
				servico : 'DEBITO',
				descricaoServico : 'Conferência de Débito',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Sequencial',
					field : 'sequencial'
				} ],
				documentacao : 'documentacao/portalFinancas/debitoPortalFinancas.html'
			},
			{
				posicao : 14,
				nome : 'PORTAL_FINANCAS',
				descricao : 'Portal Finanças',
				link : 'https://portalfinancas.recife.pe.gov.br/certidaoNegativaDebitos',
				servico : 'CND',
				descricaoServico : 'Conferência de CND',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Matricula',
					field : 'matricula'
				} ]

			},
			{
				posicao : 15,
				nome : 'SANEAGO',
				descricao : 'Saneago',
				link : 'https://www.saneago.com.br/site/agencia/sonline.php?sems=via2ss',
				servico : 'DEBITO',
				descricaoServico : 'Conferência de Débito',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Matricula',
					field : 'matricula'
				} ],
				documentacao : 'documentacao/saneago/debitoSaneago.html'
			},
			{
				posicao : 16,
				nome : 'SANEAGO',
				descricao : 'Saneago',
				link : 'https://www.saneago.com.br/site/agencia/sonline.php?sems=via2ss',
				servico : 'DAM',
				descricaoServico : 'Conferência de Dam',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Matricula',
					field : 'matricula'
				} ],
				documentacao : 'documentacao/saneago/damSaneago.html'
			},
			{
				posicao : 17,
				nome : 'CAESB',
				descricao : 'Caesb',
				link : 'https://www.caesb.df.gov.br/segunda-via-de-conta-agencia-virtual.html',
				servico : 'DEBITO',
				descricaoServico : 'Conferência de Débito',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Inscrição',
					field : 'inscricao'
				} ],
				documentacao : 'documentacao/caesb/debitoCaesb.html'
			},
			{
				posicao : 18,
				nome : 'CAESB',
				descricao : 'Caesb',
				link : 'https://www.caesb.df.gov.br/segunda-via-de-conta-agencia-virtual.html',
				servico : 'DAM',
				descricaoServico : 'Conferência de DAM',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Inscrição',
					field : 'inscricao'
				} ],
				documentacao : 'documentacao/caesb/damCaesb.html'
			},
			{
				posicao : 19,
				nome : 'PORTAL_FINANCAS',
				descricao : 'Portal Finanças',
				link : 'https://portalfinancas.recife.pe.gov.br/extratoDebitos/1',
				servico : 'DAM',
				descricaoServico : 'Conferência de DAM',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Sequencial',
					field : 'sequencial'
				} ],
			},
			{
				posicao : 20,
				nome : 'ENEL',
				descricao : 'Enel',
				link : 'https://www.eneldistribuicao.com.br/go/AgenciaVirtual.aspx',
				servico : 'DAM',
				descricaoServico : 'Conferência de Dam',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Unidade Consumidora',
					field : 'unidadeConsumidora'
				}, {
					label : 'Cpf ou Cnpj',
					field : 'cpfCnpj'
				} ],
				documentacao : 'documentacao/enel/damEnel.html'
			},
			{
				posicao : 21,
				nome : 'CASAN',
				descricao : 'Casan',
				link : 'https://e.casan.com.br/',
				servico : 'TITULARIDADE',
				descricaoServico : 'Conferência de Titularidade',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Matrícula',
					field : 'matricula'
				}, {
					label : 'CpfCnpj',
					field : 'cpfCnpj'
				} ],
				documentacao : 'documentacao/casan/titularidadeCasan.html'
			},
			{
				posicao : 35,
				nome : 'FLORIPA',
				descricao : 'Prefeitura de Florianópolis',
				link : 'http://iptu2019.pmf.sc.gov.br/iptu-virtual/main-iptu/',
				servico : 'DAM',
				descricaoServico : 'Conferência de Dam',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Inscricao',
					field : 'inscricao'
				} ],
				header : [ {
					label : 'Parcela(s)',
					field : 'parcelas'
				} ],
				documentacao : 'documentacao/floripa/damFloripa.html'
			},
			{
				posicao : 22,
				nome : 'FLORIPA',
				descricao : 'Prefeitura de Florianópolis',
				link : 'http://www.pmf.sc.gov.br/servicos/sistema.php?servicoid=3687',
				servico : 'CND',
				descricaoServico : 'Conferência de CND',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Inscricao',
					field : 'inscricao'
				}, {
					label : 'Cpf ou Cnpj',
					field : 'cpfCnpj'
				} ],
				documentacao : 'documentacao/floripa/cndFloripa.html'
			},
			{
				posicao : 23,
				nome : 'BOMBEIRO_PERNAMBUCO',
				descricao : 'Bombeiros de Pernambuco - TPEI',
				link : 'https://tpei.bombeiros.pe.gov.br/tpeinet/intranet/dwl_ctudo-gerenc.asp?build=1',
				servico : 'DEBITO',
				descricaoServico : 'Conferência de Débito',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Sequencial',
					field : 'sequencial'
				} ],
				documentacao : 'documentacao/bombeiroPernambuco/debitoBombeiroPernambuco.html'
			},
			{
				posicao : 24,
				nome : 'COMPESA',
				descricao : 'Compesa',
				link : 'https://lojavirtual.compesa.com.br:8443/gsan/exibirServicosPortalCompesaAction.do?method=emitirSegundaViaConta&matriculaObrigatoria=true',
				servico : 'DAM',
				descricaoServico : 'Conferência de Dam',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Matrícula',
					field : 'matricula'
				} ],
				documentacao : 'documentacao/compesa/damCompesa.html'
			},
			{
				posicao : 25,
				nome : 'CASAN',
				descricao : 'Casan',
				link : 'https://e.casan.com.br/',
				servico : 'DEBITO',
				descricaoServico : 'Conferência de Débito',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Matrícula',
					field : 'matricula'
				}, {
					label : 'CpfCnpj',
					field : 'cpfCnpj'
				} ],
				documentacao : 'documentacao/casan/debitoCasan.html'
			},
			{
				posicao : 26,
				nome : 'GOIANIA',
				descricao : 'Prefeitura de Goiânia - CND IPTU',
				link : 'http://www.goiania.go.gov.br/sistemas/sccer/asp/sccer00201f0.asp',
				servico : 'CND',
				descricaoServico : 'Conferência de CND',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Inscrição',
					field : 'inscricao'
				} ],
				documentacao : 'documentacao/goiania/cndGoiania.html'
			},
			{
				posicao : 27,
				nome : 'CASAN',
				descricao : 'Casan',
				link : 'https://e.casan.com.br/',
				servico : 'DAM',
				descricaoServico : 'Conferência de Dam',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Matrícula',
					field : 'matricula'
				}, {
					label : 'CpfCnpj',
					field : 'cpfCnpj'
				} ],
				documentacao : 'documentacao/casan/damCasan.html'
			},
			{
				posicao : 28,
				nome : 'CELESC',
				descricao : 'Celesc',
				link : 'http://agenciaweb.celesc.com.br:8080/AgenciaWeb/autenticar/selecionarUC.do',
				servico : 'DAM',
				descricaoServico : 'Conferência de Dam',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Unidade Consumidora',
					field : 'unidadeConsumidora'
				}, {
					label : 'Cpf ou Cnpj',
					field : 'cpfCnpj'
				} ],
				header : [ {
					label : 'Login',
					field : 'login'
				}, {
					label : 'Senha',
					field : 'senha'
				} ],
				documentacao : 'documentacao/celesc/damCelesc.html'
			}, 
			{
				posicao : 29,
				nome : 'CELPE',
				descricao : 'Celpe',
				link : 'http://servicos.celpe.com.br/servicos-ao-cliente/Pages/login-av.aspx?UrlUc=http://servicos.celpe.com.br/servicos-ao-cliente/Pages/2-via-conta_anti.aspx',
				servico : 'DAM',
				descricaoServico : 'Conferência de DAM',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Conta Contrato',
					field : 'contaContrato'
				}, {
					label : 'CPF/CNPJ',
					field : 'cpfCnpj'
				} ],
				documentacao : 'documentacao/celpe/damCelpe.html'
			}, 
			{
				posicao : 30,
				nome : 'FLORIPA',
				descricao : 'Prefeitura de Florianópolis',
				link : 'http://www.pmf.sc.gov.br/servicos/sistema.php?servicoid=3687',
				servico : 'DEBITO',
				descricaoServico : 'Conferência de Débito',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {

					label : 'Inscricao',
					field : 'inscricao'
				} ],
				documentacao : 'documentacao/floripa/debitoFloripa.html'
			}, 
			{
				posicao : 33,
				nome : 'CRICIUMA',
				descricao : 'Prefeitura de Criciuma',
				link : 'https://e-gov.betha.com.br/cdweb/03114-012/main.faces',
				servico : 'DAM',
				descricaoServico : 'Conferência de DAM',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {

					label : 'Codigo Imovel Betha',
					field : 'codigoImovelBetha'
				} ],

				header : [ {
					label : 'Parcela(s)',
					field : 'parcelas'
				} ],
				documentacao : 'documentacao/criciuma/damCriciuma.html'

			}, 
			{
				posicao : 31,
				nome : 'CRICIUMA',
				descricao : 'Prefeitura de Criciuma',
				link : 'https://e-gov.betha.com.br/cdweb/03114-012/main.faces',
				servico : 'CND',
				descricaoServico : 'Conferência de CND',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {

					label : 'Codigo Imovel Betha',
					field : 'codigoImovelBetha'
				} ],
				documentacao : 'documentacao/criciuma/cndCriciuma.html'

			}, 
			{
				posicao : 35,
				nome : 'SAO_JOSE',
				descricao : 'Prefeitura de São José',
				link : 'https://e-gov.betha.com.br/cdweb/03114-012/main.faces',
				servico : 'DAM',
				descricaoServico : 'Conferência de DAM',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Inscricão Imobiliária',
					field : 'inscImobiliaria'
				} ],
				header : [ {
					label : 'Parcela(s)',
					field : 'parcelas'
				} ],
				documentacao : 'documentacao/saoJose/cndSaoJose.html'
			}, 
			{
				posicao : 32,
				nome : 'SAO_JOSE',
				descricao : 'Prefeitura de São José',
				link : 'https://e-gov.betha.com.br/cdweb/03114-012/main.faces',
				servico : 'CND',
				descricaoServico : 'Conferência de CND',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Inscricão Imobiliária',
					field : 'inscImobiliaria'
				} ],
				documentacao : 'documentacao/saoJose/cndSaoJose.html'
			}, 
			{
				posicao : 34,
				nome : 'SAO_JOSE',
				descricao : 'Prefeitura de São José',
				link : 'https://e-gov.betha.com.br/cdweb/03114-012/main.faces',
				servico : 'DEBITO',
				descricaoServico : 'Conferência de Débito',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {
					label : 'Inscricão Imobiliária',
					field : 'inscImobiliaria'
				} ],
				documentacao : 'documentacao/saoJose/cndSaoJose.html'
			}, 
			{
				posicao : 36,
				nome : 'CRICIUMA',
				descricao : 'Prefeitura de Criciuma',
				link : 'https://e-gov.betha.com.br/cdweb/03114-012/main.faces',
				servico : 'DEBITO',
				descricaoServico : 'Conferência de DEBITO',
				campos : [ {
					label : 'Código do Imóvel',
					field : 'codigoImovel'
				}, {
					label : 'Número do Contrato',
					field : 'numeroContrato'
				}, {

					label : 'Codigo Imovel Betha',
					field : 'codigoImovelBetha'
				} ],
				documentacao : 'documentacao/criciuma/debitoCriciuma.html'

			} ];

	angular.module('app.constants').constant('Site', Site);

})();