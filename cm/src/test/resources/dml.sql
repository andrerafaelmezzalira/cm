--sites/servicos

INSERT INTO public.site (id, nome, servico) VALUES (1, 'ENEL', 'TITULARIDADE');
INSERT INTO public.site (id, nome, servico) VALUES (2, 'ENEL', 'DEBITO');

-- usuarios e vinculacoes de sites/servicos com usuarios

-- usuario default
INSERT INTO public.usuario (id, cpfcnpj, nome) VALUES (1, '66666666666669', 'Samaia');

INSERT INTO public.usuario_site (usuario_id, sites_id) VALUES (1, 1);
INSERT INTO public.usuario_site (usuario_id, sites_id) VALUES (1, 2);

