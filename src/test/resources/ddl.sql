CREATE TABLE person
(
  id serial,
  cpf varchar(11) NOT NULL,
  name varchar(100) NOT NULL,
  CONSTRAINT pk_person PRIMARY KEY (id)
);

ALTER TABLE person ADD
  CONSTRAINT uq_person UNIQUE (cpf);

CREATE TABLE service
(
  id serial,
  name character varying(100) NOT NULL,
  CONSTRAINT pk_service PRIMARY KEY (id)
);

ALTER TABLE service ADD
  CONSTRAINT uq_service UNIQUE (name);

CREATE TABLE header
(
  id serial,
  person_id integer NOT NULL,
  service_id integer NOT NULL,
  login character varying(100) NOT NULL,
  password character varying(100) NOT NULL,
  datetimeinit timestamp without time zone NOT NULL,
  datetimeend timestamp without time zone,
  CONSTRAINT pk_header PRIMARY KEY (id)
);

ALTER TABLE header ADD
  CONSTRAINT fk_header_person FOREIGN KEY (person_id)
      REFERENCES person(id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE header ADD
  CONSTRAINT fk_header_service FOREIGN KEY (service_id)
      REFERENCES service(id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE TABLE parameter
(
  id serial,
  state json NOT NULL,
  result json,
  datetimeinitreprocessing timestamp without time zone,
  datetimeendreprocessing timestamp without time zone,
  CONSTRAINT pk_parameter PRIMARY KEY (id)
);

CREATE TABLE unity
(
  id INTEGER NOT NULL,
  idunity INTEGER,
  name character varying(256),
  hasstorage boolean,
  header_id integer NOT NULL,
  CONSTRAINT pk_unity PRIMARY KEY (id)
);

ALTER TABLE unity ADD
  CONSTRAINT fk_unity_header FOREIGN KEY (header_id)
      REFERENCES header(id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE unity ADD
  CONSTRAINT fk_unity_parameter FOREIGN KEY (id)
      REFERENCES parameter(id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT;
      
      
CREATE OR REPLACE VIEW public.reprocessing AS 
SELECT a.id as headers, b.id as parameter, 'units' as entity
   FROM header a
     JOIN unity b ON a.id = b.header_id
     JOIN parameter c ON b.id = c.id
  WHERE c.state ->> 'id' = '2'
