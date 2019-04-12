CREATE TABLE usuario
(
  id serial,
  cpfcnpj varchar(14) NOT NULL,
  nome varchar(100) NOT NULL,
  CONSTRAINT pk_usuario PRIMARY KEY (id)
);

ALTER TABLE usuario ADD
  CONSTRAINT uq_usuario UNIQUE (cpfcnpj);

CREATE TABLE site
(
  id serial,
  nome character varying(30) NOT NULL,
  servico character varying(30) NOT NULL,
  CONSTRAINT pk_site PRIMARY KEY (id)
);

ALTER TABLE site ADD
  CONSTRAINT uq_site UNIQUE (nome, servico);

CREATE TABLE usuario_site
(
  usuario_id integer NOT NULL,
  sites_id integer NOT NULL
);

ALTER TABLE usuario_site ADD
  CONSTRAINT fk_site_usuario FOREIGN KEY (sites_id)
      REFERENCES site(id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE usuario_site ADD
  CONSTRAINT fk_usuario_site FOREIGN KEY (usuario_id)
      REFERENCES usuario(id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE TABLE header
(
  id serial,
  usuario_id integer NOT NULL,
  site_id integer NOT NULL,
  servico character varying(50) NOT NULL,
  ip character varying(15) NOT NULL,
  datahorainicio timestamp without time zone NOT NULL,
  datahorafim timestamp without time zone,
  parcelas json,	
  usuariocelesc_id integer,
  CONSTRAINT pk_header PRIMARY KEY (id)
);

ALTER TABLE header ADD
  CONSTRAINT fk_header_usuario FOREIGN KEY (usuario_id)
      REFERENCES usuario(id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE header ADD
  CONSTRAINT fk_header_site FOREIGN KEY (site_id)
      REFERENCES site(id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE header ADD
  CONSTRAINT fk_header_usuariocelesc FOREIGN KEY (usuariocelesc_id)
      REFERENCES usuariocelesc(id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE TABLE parametro
(
  id serial,
  codigoimovel character varying(100) NOT NULL,
  numerocontrato character varying(100),
  pdfs json,
  estado json NOT NULL,
  resultado json,
  datahorainicioreprocessamento timestamp without time zone,
  datahorafimreprocessamento timestamp without time zone,
  CONSTRAINT pk_parametro PRIMARY KEY (id)
);

CREATE TABLE enel
(
  id INTEGER NOT NULL,
  unidadeConsumidora character varying(15) NOT NULL,
  cpfcnpj character varying(14) NOT NULL,
  header_id integer NOT NULL,
  CONSTRAINT pk_enel PRIMARY KEY (id)
);

ALTER TABLE enel ADD
  CONSTRAINT fk_enel_header FOREIGN KEY (header_id)
      REFERENCES header(id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE enel ADD
  CONSTRAINT fk_enel_parametro FOREIGN KEY (id)
      REFERENCES parametro(id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT;
      
      
CREATE OR REPLACE VIEW public.reprocessamento AS 
SELECT a.id as headers, b.id as parametro, 'enels' as entity
   FROM header a
     JOIN enel b ON a.id = b.header_id
     JOIN parametro c ON b.id = c.id
  WHERE c.estado ->> 'id' = '2'

