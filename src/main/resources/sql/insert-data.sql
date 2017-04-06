<<<<<<< HEAD
INSERT INTO user(username, name, email, pw) VALUES ('admin','Administrator','admin@email.com','$2y$10$Yxe35TaNxLErDLmpDHrZxOIMT8P74qQGb5NER2TcRKSEDSb0KUn6G');
INSERT INTO user(username, name, email, pw) VALUES ('dorde','Dorde Torbica','dorde.torbica@ctbto.org','$2y$10$dUFLtk8yqsicQ584aI/ZY.7exvxpmZ4zC/mzDlOyT4X.9pI0tge6C');
INSERT INTO user(username, name, email, pw) VALUES ('helmuth','Helmuth Breitenfellner','helmuth.breitenfellner@ctbto.org','$2y$10$uI5YpyM9n0thdAukWiZnJuEKBJu4ilvLcYySGQlkR6abdOYy/jyBi');
INSERT INTO user(username, name, email, pw) VALUES ('cahya','Cahya Wirawam','cahya.wirawam@ctbto.org','$2y$10$iiwpwY74t6Xyp809hmHLu.9N0WYYdEzny54aoSXNq./hKyA.b9rfy');
INSERT INTO user(username, name, email, pw) VALUES ('albert','Albert Brouwer','albert.brouwer@ctbto.org','$2y$10$BHy3WJMQ5WWHISVH66EUR.0fnREqGGqdwHzyWDX7Wa4FTFOwMyRRW');
INSERT INTO user(username, name, email, pw) VALUES ('benoit','Benoit Doury','benoit.doury@ctbto.org','$2y$10$l19IZfLnmaqRBCK96QqFJubM7osZcXVjX.1uTe0Ecq2oyfjWc0trC');

INSERT INTO bet(initiator_id, title, description, pub_date) VALUES (100, 'Donald Trump bet', 'Donald Trump will remain president until the end of his term.', TIMESTAMP '2017-04-06 02:00:22');
INSERT INTO bet(initiator_id, challenger_id, title, description, pub_date) VALUES (100, 101, 'Winning Scrum Team bet', 'Team sushi will win this contest', TIMESTAMP '2017-04-06 02:00:22');
INSERT INTO bet(initiator_id, challenger_id, title, description, pub_date) VALUES (101, 102, 'Dorde highest committer', 'By the end of this sprint Dorde will achieve the largest number of commits', TIMESTAMP '2017-04-06 02:00:22');
INSERT INTO bet(initiator_id, challenger_id, title, description, pub_date) VALUES (104, 103, 'Test coverage','Sushi teams will achieve 80% unit test coverage by the end of sprint4', TIMESTAMP '2017-04-06 02:00:22');
INSERT INTO bet(initiator_id,  title, description, pub_date) VALUES (105, 'Documentation', 'Sushi Team will have a fully completed user guide documentation by the end of sprint 4', TIMESTAMP '2017-04-06 02:00:22');
