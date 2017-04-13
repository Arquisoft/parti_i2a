
-- Categories Inserts -->

INSERT INTO public.categories (id, name) VALUES (1, 'General');
INSERT INTO public.categories (id, name) VALUES (2, 'Not So General');
INSERT INTO public.categories (id, name) VALUES (3, 'Low energy');
INSERT INTO public.categories (id, name) VALUES (4, 'Space stuff');
INSERT INTO public.categories (id, name) VALUES (5, 'Batshit Crazy');

-- Users Inserts -->

INSERT INTO public.users (id, dni, firstname, lastname, password, email, birthdate, address, nationality, pollingstation) VALUES (1, '12345678A', 'Pepe', 'Calleja', 'password1234', 'calleja@email.com', '1950-03-25', 'Oviedo', 'Spanish', 2);
INSERT INTO public.users (id, dni, firstname, lastname, password, email, birthdate, address, nationality, pollingstation) VALUES (2, '87654321A', 'User', 'User', 'pass', 'user@email.com', '1953-06-21', 'Oviedo', 'Spanish', 1);
INSERT INTO public.users (id, dni, firstname, lastname, password, email, birthdate, address, nationality, pollingstation) VALUES (3, '63179107C', 'Commander', 'Sheppard', 'reapersmakemesick', 'commander@sheppard.com', '2017-04-19', 'Oviedo', 'Spanish', 3);
INSERT INTO public.users (id, dni, firstname, lastname, password, email, birthdate, address, nationality, pollingstation) VALUES (4, '82735d63F', 'Stannis', 'Baratheon', 'kneelbeforeme', 'stannis@yahoo.com', '1928-04-07', 'Oviedo', 'Spanish', 2);
INSERT INTO public.users (id, dni, firstname, lastname, password, email, birthdate, address, nationality, pollingstation) VALUES (5, '75332767R', 'King', 'Wewuz', 'eyooholup', 'realegipcian@hotmail.es', '1992-10-09', 'Oviedo', 'Spanish', 1);
INSERT INTO public.users (id, dni, firstname, lastname, password, email, birthdate, address, nationality, pollingstation) VALUES (6, '12384643G', 'Turk', 'Batal', 'yeyeyeyeyey', 'ttt@erd.tk', '1996-04-28', 'Oviedo', 'Spanish', 3);
INSERT INTO public.users (id, dni, firstname, lastname, password, email, birthdate, address, nationality, pollingstation) VALUES (7, '99997752F', 'Titounete', 'Vera', 'aaaaaahhhhh', 'nuefauf@gggg.es', '2017-04-24', 'Oviedo', 'Spanish', 4);
INSERT INTO public.users (id, dni, firstname, lastname, password, email, birthdate, address, nationality, pollingstation) VALUES (8, '34576347Z', 'Stone Cold', 'Steve Austin', 'stunner', 'coldmail@hotmail.com', '2017-11-09', 'Oviedo', 'Spanish', 2);
INSERT INTO public.users (id, dni, firstname, lastname, password, email, birthdate, address, nationality, pollingstation) VALUES (9, '98423265G', 'Bane', 'Mask', 'firerises', 'bigguy@gmail.com', '1968-05-24', 'Oviedo', 'Spanish', 4);
INSERT INTO public.users (id, dni, firstname, lastname, password, email, birthdate, address, nationality, pollingstation) VALUES (10, '93456311M', 'Franz', 'Kafka', 'pom', 'kek@lmao.com', '2005-02-02', 'Oviedo', 'Spanish', 3);

-- Proposal Inserts -->

INSERT INTO public.proposals (id, content, votes, user_id, category_id) VALUES (1, 'Hacer un parque', 1, 1, 1);
INSERT INTO public.proposals (id, content, votes, user_id, category_id) VALUES (2, 'Hacer un monumento', 2, 1, 1);
INSERT INTO public.proposals (id, content, votes, user_id, category_id) VALUES (3, 'Defeat the reapers', 5, 3, 2);
INSERT INTO public.proposals (id, content, votes, user_id, category_id) VALUES (4, 'Siege castle with cavalry', 10, 6, 4);
INSERT INTO public.proposals (id, content, votes, user_id, category_id) VALUES (5, 'Put on the mask', 4, 9, 5);
INSERT INTO public.proposals (id, content, votes, user_id, category_id) VALUES (6, 'Reclaim Westeros', 12, 4, 3);
INSERT INTO public.proposals (id, content, votes, user_id, category_id) VALUES (7, 'Make your life horrible', 2, 10, 5);
INSERT INTO public.proposals (id, content, votes, user_id, category_id) VALUES (8, 'Buy more beer', 15, 8, 2);
INSERT INTO public.proposals (id, content, votes, user_id, category_id) VALUES (10, 'Nudist beach in Mars', 7, 5, 2);
INSERT INTO public.proposals (id, content, votes, user_id, category_id) VALUES (11, 'Conquer the galaxy', 6, 3, 1);
INSERT INTO public.proposals (id, content, votes, user_id, category_id) VALUES (12, 'Eat more kebab', 3, 6, 3);
INSERT INTO public.proposals (id, content, votes, user_id, category_id) VALUES (13, 'Make flying pyramids', 9, 5, 5);
INSERT INTO public.proposals (id, content, votes, user_id, category_id) VALUES (14, 'Getting cought in the space station', 2, 9, 4);
INSERT INTO public.proposals (id, content, votes, user_id, category_id) VALUES (15, 'Buy more beer, again', 18, 8, 2);
INSERT INTO public.proposals (id, content, votes, user_id, category_id) VALUES (16, 'Make Turk Batal a sultan', 1, 6, 5);
INSERT INTO public.proposals (id, content, votes, user_id, category_id) VALUES (17, 'I''m the best, you bad', 1, 6, 5);
INSERT INTO public.proposals (id, content, votes, user_id, category_id) VALUES (18, 'Nuke the citedel', 11, 3, 5);
INSERT INTO public.proposals (id, content, votes, user_id, category_id) VALUES (19, 'Crash the plane', 2, 9, 5);
INSERT INTO public.proposals (id, content, votes, user_id, category_id) VALUES (20, 'KAFKAAAAAAAAAAAAA', 9999, 10, 3);

-- Comments Inserts --

INSERT INTO public.comments (id, content, votes, date, user_id, proposal_id) VALUES (1, 'Buena idea el parque', 0, '2017-04-02', 2, 1);
INSERT INTO public.comments (id, content, votes, date, user_id, proposal_id) VALUES (2, 'U dumb', 1, '2017-04-05', 6, 6);
INSERT INTO public.comments (id, content, votes, date, user_id, proposal_id) VALUES (3, 'Yes please', 0, '2017-04-03', 5, 8);
INSERT INTO public.comments (id, content, votes, date, user_id, proposal_id) VALUES (4, 'Indeed', 4, '2017-04-02', 4, 15);
INSERT INTO public.comments (id, content, votes, date, user_id, proposal_id) VALUES (5, 'Ayoooooo hol up', 2, '2017-03-30', 3, 13);
INSERT INTO public.comments (id, content, votes, date, user_id, proposal_id) VALUES (6, 'Maybe that''s too much', 3, '2017-02-17', 7, 18);
INSERT INTO public.comments (id, content, votes, date, user_id, proposal_id) VALUES (7, 'Why not', 1, '2017-04-03', 9, 8);
INSERT INTO public.comments (id, content, votes, date, user_id, proposal_id) VALUES (8, 'No', 3, '2017-04-01', 4, 4);
INSERT INTO public.comments (id, content, votes, date, user_id, proposal_id) VALUES (9, ';-)', 10, '2017-01-21', 5, 10);
INSERT INTO public.comments (id, content, votes, date, user_id, proposal_id) VALUES (10, 'I''m best', 1, '2017-04-05', 6, 17);
INSERT INTO public.comments (id, content, votes, date, user_id, proposal_id) VALUES (11, 'Best cavalry yes', 1, '2017-04-05', 6, 17);
INSERT INTO public.comments (id, content, votes, date, user_id, proposal_id) VALUES (12, 'I''m vey handsome', 1, '2017-04-05', 6, 17);
INSERT INTO public.comments (id, content, votes, date, user_id, proposal_id) VALUES (13, 'POMPOMPOMPOMPOMPOMPOM', 23, '2017-04-04', 10, 20);
INSERT INTO public.comments (id, content, votes, date, user_id, proposal_id) VALUES (14, 'TRAAAAAAAAAAAAAVIS', 44, '2017-04-05', 10, 20);
INSERT INTO public.comments (id, content, votes, date, user_id, proposal_id) VALUES (15, 'Oh noes', 8, '2017-04-05', 8, 20);
INSERT INTO public.comments (id, content, votes, date, user_id, proposal_id) VALUES (16, 'RUN', 15, '2017-04-05', 6, 20);