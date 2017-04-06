INSERT INTO user(username, name, email, pw) VALUES ('admin','Administrator','admin@email.com','admin');
INSERT INTO user(username, name, email, pw) VALUES ('dorde','Dorde Torbica','dorde.torbica@ctbto.org','dorde');
INSERT INTO user(username, name, email, pw) VALUES ('helmuth','Helmuth Breitenfellner','helmuth.breitenfellner@ctbto.org','helmuth');
INSERT INTO user(username, name, email, pw) VALUES ('Cahya','Cahya Wirawam','cahya.wirawam@ctbto.org','');

INSERT INTO bet(initiator_id, title, description, pub_date) VALUES (1, 50,'Donald Trump bet', 'Who bets Donald Trump will remain president until the end of his term?', TIMESTAMP '2017-04-06 02:00:22');
INSERT INTO bet(initiator_id, challenger_id, title, description, pub_date) VALUES (1, 2, 20, 'Winning Scrum Team bet', 'Who bets Team sushi will win this contest?', TIMESTAMP '2017-04-06 02:00:22');