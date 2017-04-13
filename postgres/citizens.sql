-- Sequence: public.user_id_seq

-- DROP SEQUENCE public.user_id_seq;

CREATE SEQUENCE public.user_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.user_id_seq
  OWNER TO postgres;

  
-- Table: public."user"

-- DROP TABLE public."user";

CREATE TABLE public."user"
(
  id integer NOT NULL DEFAULT nextval('user_id_seq'::regclass),
  dni text NOT NULL,
  nombre text,
  apellidos text,
  password text,
  email text,
  nacimiento date,
  direccion text,
  nacionalidad text,
  polling integer,
  CONSTRAINT user_pkey PRIMARY KEY (dni),
  CONSTRAINT user_id_key UNIQUE (id)
)
WITH (
  OIDS=FALSE
);

ALTER TABLE public."user"
  OWNER TO postgres;

-- Sequence: public.category_id_seq

-- DROP SEQUENCE public.category_id_seq;

CREATE SEQUENCE public.category_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.category_id_seq
  OWNER TO postgres;

-- Table: public.category

-- DROP TABLE public.category;

CREATE TABLE public.category
(
  id integer NOT NULL DEFAULT nextval('category_id_seq'::regclass),
  name text,
  CONSTRAINT category_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.category
  OWNER TO postgres;


  

  
  
  
-- Sequence: public.proposal_id_seq

-- DROP SEQUENCE public.proposal_id_seq;

CREATE SEQUENCE public.proposal_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.proposal_id_seq
  OWNER TO postgres;
  
  
-- Table: public.proposal

-- DROP TABLE public.proposal;

CREATE TABLE public.proposal
(
  id integer NOT NULL DEFAULT nextval('proposal_id_seq'::regclass),
  content text,
  votes integer,
  user_id integer,
  category_id integer,
  CONSTRAINT proposal_pkey PRIMARY KEY (id),
  CONSTRAINT "foreignKey_Category" FOREIGN KEY (category_id)
      REFERENCES public.category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT proposal_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES public."user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.proposal
  OWNER TO postgres;

-- Index: public."fki_foreignKey_Category"

-- DROP INDEX public."fki_foreignKey_Category";

CREATE INDEX "fki_foreignKey_Category"
  ON public.proposal
  USING btree
  (category_id);



  -- Sequence: public.commentary_id_seq

-- DROP SEQUENCE public.commentary_id_seq;

CREATE SEQUENCE public.commentary_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.commentary_id_seq
  OWNER TO postgres;

CREATE TABLE public.commentary
(
  id integer NOT NULL DEFAULT nextval('commentary_id_seq'::regclass),
  content text,
  votes integer,
  fecha date,
  user_id integer,
  proposal_id integer,
  CONSTRAINT commentary_pkey PRIMARY KEY (id),
  CONSTRAINT commentary_proposal_id_fkey FOREIGN KEY (proposal_id)
      REFERENCES public.proposal (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT commentary_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES public."user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.commentary
  OWNER TO postgres;

  
-- Table: public.words

-- DROP TABLE public.words;

-- Sequence: public.words_id_seq

-- DROP SEQUENCE public.words_id_seq;

CREATE SEQUENCE public.words_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 4
  CACHE 1;
ALTER TABLE public.words_id_seq
  OWNER TO postgres;

CREATE TABLE public.words
(
  word text,
  id integer NOT NULL DEFAULT nextval('words_id_seq'::regclass),
  CONSTRAINT words_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.words
  OWNER TO postgres;


INSERT INTO public.user(
            id, dni, nombre, apellidos, email, password, nacimiento, direccion, polling, 
            nacionalidad)
    VALUES (1, '12345678A', 'Pepe', 'Calleja', 'calleja@email.com', 'password', 
      '1950-03-25', 'Oviedo', 2, 'Spanish');

INSERT INTO public.user(
            id, dni, nombre, apellidos, email, password, nacimiento, direccion, polling, 
            nacionalidad)
    VALUES (2, '798431467A', 'Carlos', 'Fernandez', 'carlos@email.com', 'password', 
      '1967-05-13', 'Oviedo', 3, 'Spanish');
INSERT INTO public.user(
            id, dni, nombre, apellidos, email, password, nacimiento, direccion, polling, 
            nacionalidad)
    VALUES (3, '9172457631Y', 'Maria', 'Perez', 'maria@email.com', 'password', 
      '1984-12-26', 'Oviedo', 1, 'Spanish');
INSERT INTO public.user(
            id, dni, nombre, apellidos, email, password, nacimiento, direccion, polling, 
            nacionalidad)
    VALUES (4, '718346981R', 'Simon', 'Neil', 'simon@email.com', 'password', 
      '1979-08-31', 'Edinburgh', 2, 'British');
INSERT INTO public.user(
            id, dni, nombre, apellidos, email, password, nacimiento, direccion, polling, 
            nacionalidad)
    VALUES (5, '76756556E', 'admin', 'admin', 'admin@email.com', 'password', 
      '1967-05-13', 'Oviedo', 0, 'Spanish');

ALTER SEQUENCE public.user_id_seq RESTART WITH 6;	 
	  
INSERT INTO public.category(
            id, name)
    VALUES (1, 'General');
INSERT INTO public.category(
            id, name)
    VALUES (2, 'Entertainment');

ALTER SEQUENCE public.category_id_seq RESTART WITH 3;

INSERT INTO public.proposal(
            id, content, votes, user_id, category_id)
    VALUES (1, 'Build a park', 1, 2, 1);

INSERT INTO public.proposal(
            id, content, votes, user_id, category_id)
    VALUES (2, 'Open shopping mall', 0, 3, 2);

INSERT INTO public.proposal(
            id, content, votes, user_id, category_id)
    VALUES (3, 'Metallica concert', 4, 4, 2);

INSERT INTO public.proposal(
            id, content, votes, user_id, category_id)
    VALUES (4, 'Host a literature convention', 2, 3, 2);

ALTER SEQUENCE public.proposal_id_seq RESTART WITH 5;	
	
INSERT INTO public.commentary(
            id, content, votes, fecha, user_id, proposal_id)
    VALUES (1, 'Yeah! This would rock :D', 0, '2017-04-02', 3, 3);

INSERT INTO public.commentary(
            id, content, votes, fecha, user_id, proposal_id)
    VALUES (2, 'And bring Foo Fighters!', 0, '2017-04-01', 2, 3);

INSERT INTO public.commentary(
            id, content, votes, fecha, user_id, proposal_id)
    VALUES (3, 'Great for children and pets', 0, '2017-03-24', 2, 1);

INSERT INTO public.commentary(
            id, content, votes, fecha, user_id, proposal_id)
    VALUES (4, 'With a cinema maybe?', 0, '2017-04-07', 4, 2);

INSERT INTO public.commentary(
            id, content, votes, fecha, user_id, proposal_id)
    VALUES (5, 'Yes! With a daycarer for children', 0, '2017-04-08', 3, 2);

INSERT INTO public.commentary(
            id, content, votes, fecha, user_id, proposal_id)
    VALUES (6, 'Bring George RR Martin :3', 0, '2017-04-01', 2, 4);

ALTER SEQUENCE public.commentary_id_seq RESTART WITH 7;


