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

CREATE TABLE public."users"
(
  id integer NOT NULL DEFAULT nextval('user_id_seq'::regclass),
  dni text NOT NULL,
  firstName text,
  lastName text,
  password text,
  email text,
  birthDate date,
  address text,
  nationality text,
  pollingStation integer,
  CONSTRAINT users_pkey PRIMARY KEY (dni),
  CONSTRAINT user_id_key UNIQUE (id)
)
WITH (
OIDS=FALSE
);

ALTER TABLE public."users"
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

-- Table: public.categories

-- DROP TABLE public.categories;

CREATE TABLE public.categories
(
  id integer NOT NULL DEFAULT nextval('category_id_seq'::regclass),
  name text,
  CONSTRAINT category_pkey PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE public.categories
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


-- Table: public.proposals

-- DROP TABLE public.proposals;

CREATE TABLE public.proposals
(
  id integer NOT NULL DEFAULT nextval('proposal_id_seq'::regclass),
  content text,
  votes integer,
  user_id integer,
  category_id integer,
  CONSTRAINT proposal_pkey PRIMARY KEY (id),
  CONSTRAINT "foreignKey_Category" FOREIGN KEY (category_id)
  REFERENCES public.categories (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT proposal_user_id_fkey FOREIGN KEY (user_id)
  REFERENCES public."users" (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
OIDS=FALSE
);
ALTER TABLE public.proposals
  OWNER TO postgres;

-- Index: public."fki_foreignKey_Category"

-- DROP INDEX public."fki_foreignKey_Category";

CREATE INDEX "fki_foreignKey_Category"
  ON public.proposals
  USING btree
  (category_id);



-- Sequence: public.comment_id_seq

-- DROP SEQUENCE public.comment_id_seq;

CREATE SEQUENCE public.comment_id_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE public.comment_id_seq
  OWNER TO postgres;

CREATE TABLE public.comments
(
  id integer NOT NULL DEFAULT nextval('comment_id_seq'::regclass),
  content text,
  votes integer,
  date date,
  user_id integer,
  proposal_id integer,
  CONSTRAINT commentary_pkey PRIMARY KEY (id),
  CONSTRAINT commentary_proposal_id_fkey FOREIGN KEY (proposal_id)
  REFERENCES public.proposals (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT commentary_user_id_fkey FOREIGN KEY (user_id)
  REFERENCES public."users" (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
OIDS=FALSE
);
ALTER TABLE public.comments
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


INSERT INTO public.users(
  id, dni, firstName, lastName, email, password, birthDate, address, pollingStation,
  nationality)
VALUES (1, '12345678A', 'Pepe', 'Calleja', 'calleja@email.com', 'password',
        '1950-03-25', 'Oviedo', 2, 'Spanish');

INSERT INTO public.users(
  id, dni, firstName, lastName, email, password, birthDate, address, pollingStation,
  nationality)
VALUES (2, '798431467A', 'Carlos', 'Fernandez', 'carlos@email.com', 'password',
        '1967-05-13', 'Oviedo', 3, 'Spanish');
INSERT INTO public.users(
  id, dni, firstName, lastName, email, password, birthDate, address, pollingStation,
  nationality)
VALUES (3, '9172457631Y', 'Maria', 'Perez', 'maria@email.com', 'password',
        '1984-12-26', 'Oviedo', 1, 'Spanish');
INSERT INTO public.users(
  id, dni, firstName, lastName, email, password, birthDate, address, pollingStation,
  nationality)
VALUES (4, '718346981R', 'Simon', 'Neil', 'simon@email.com', 'password',
        '1979-08-31', 'Edinburgh', 2, 'British');
INSERT INTO public.users(
  id, dni, firstName, lastName, email, password, birthDate, address, pollingStation,
  nationality)
VALUES (5, '76756556E', 'admin', 'admin', 'admin@email.com', 'password',
        '1967-05-13', 'Oviedo', 0, 'Spanish');
INSERT INTO public.users(
  id, dni, firstName, lastName, email, password, birthDate, address, pollingStation,
  nationality)
VALUES (6, '7173675H', 'user', 'user', 'user@example.com', 'password',
        '1967-05-13', 'Oviedo', 3, 'Spanish');


ALTER SEQUENCE public.user_id_seq RESTART WITH 6;

INSERT INTO public.categories(
  id, name)
VALUES (1, 'General');
INSERT INTO public.categories(
  id, name)
VALUES (2, 'Entertainment');

ALTER SEQUENCE public.category_id_seq RESTART WITH 3;

INSERT INTO public.proposals(
  id, content, votes, user_id, category_id)
VALUES (1, 'Build a park', 1, 2, 1);

INSERT INTO public.proposals(
  id, content, votes, user_id, category_id)
VALUES (2, 'Open shopping mall', 0, 3, 2);

INSERT INTO public.proposals(
  id, content, votes, user_id, category_id)
VALUES (3, 'Metallica concert', 4, 4, 2);

INSERT INTO public.proposals(
  id, content, votes, user_id, category_id)
VALUES (4, 'Host a literature convention', 2, 3, 2);

ALTER SEQUENCE public.proposal_id_seq RESTART WITH 5;

INSERT INTO public.comments(
  id, content, votes, date, user_id, proposal_id)
VALUES (1, 'Yeah! This would rock :D', 0, '2017-04-02', 3, 3);

INSERT INTO public.comments(
  id, content, votes, date, user_id, proposal_id)
VALUES (2, 'And bring Foo Fighters!', 0, '2017-04-01', 2, 3);

INSERT INTO public.comments(
  id, content, votes, date, user_id, proposal_id)
VALUES (3, 'Great for children and pets', 0, '2017-03-24', 2, 1);

INSERT INTO public.comments(
  id, content, votes, date, user_id, proposal_id)
VALUES (4, 'With a cinema maybe?', 0, '2017-04-07', 4, 2);

INSERT INTO public.comments(
  id, content, votes, date, user_id, proposal_id)
VALUES (5, 'Yes! With a daycarer for children', 0, '2017-04-08', 3, 2);

INSERT INTO public.comments(
  id, content, votes, date, user_id, proposal_id)
VALUES (6, 'Bring George RR Martin :3', 0, '2017-04-01', 2, 4);

ALTER SEQUENCE public.comment_id_seq RESTART WITH 7;


