
 MERGE INTO user USING (VALUES('admin','Administrator','admin@email.com','$2y$10$Yxe35TaNxLErDLmpDHrZxOIMT8P74qQGb5NER2TcRKSEDSb0KUn6G')) 
   AS vals(a,b,c,d) ON user.username = vals.a
   WHEN MATCHED THEN UPDATE SET user.name = vals.b, user.email=vals.c,user.pw=vals.d
   WHEN NOT MATCHED THEN INSERT VALUES null,vals.a, vals.b,vals.c,vals.d;

MERGE INTO user USING (VALUES ('dorde','Dorde Torbica','dorde.torbica@ctbto.org','$2a$10$IHdRfnhNgQesPFD5hrUcMOvyx5RrRcklkpXfs9YX4j1qXvouEeVIa'))
AS vals(a,b,c,d) ON user.username = vals.a
   WHEN MATCHED THEN UPDATE SET user.name = vals.b, user.email=vals.c,user.pw=vals.d
   WHEN NOT MATCHED THEN INSERT VALUES null,vals.a, vals.b,vals.c,vals.d;
MERGE INTO user USING (VALUES ('helmuth','Helmuth Breitenfellner','helmuth.breitenfellner@ctbto.org','$2y$10$uI5YpyM9n0thdAukWiZnJuEKBJu4ilvLcYySGQlkR6abdOYy/jyBi'))
AS vals(a,b,c,d) ON user.username = vals.a
   WHEN MATCHED THEN UPDATE SET user.name = vals.b, user.email=vals.c,user.pw=vals.d
   WHEN NOT MATCHED THEN INSERT VALUES null, vals.a, vals.b,vals.c,vals.d;
   
MERGE INTO user USING (VALUES ('cahya','Cahya Wirawam','cahya.wirawam@ctbto.org','$2y$10$iiwpwY74t6Xyp809hmHLu.9N0WYYdEzny54aoSXNq./hKyA.b9rfy'))
AS vals(a,b,c,d) ON user.username = vals.a
   WHEN MATCHED THEN UPDATE SET user.name = vals.b, user.email=vals.c,user.pw=vals.d
   WHEN NOT MATCHED THEN INSERT VALUES null, vals.a, vals.b,vals.c,vals.d;
MERGE INTO user USING (VALUES ('albert','Albert Brouwer','albert.brouwer@ctbto.org','$2y$10$BHy3WJMQ5WWHISVH66EUR.0fnREqGGqdwHzyWDX7Wa4FTFOwMyRRW'))
AS vals(a,b,c,d) ON user.username = vals.a
   WHEN MATCHED THEN UPDATE SET user.name = vals.b, user.email=vals.c,user.pw=vals.d
   WHEN NOT MATCHED THEN INSERT VALUES null, vals.a, vals.b,vals.c,vals.d;
MERGE INTO user USING (VALUES ('benoit','Benoit Doury','benoit.doury@ctbto.org','$2y$10$l19IZfLnmaqRBCK96QqFJubM7osZcXVjX.1uTe0Ecq2oyfjWc0trC'))
AS vals(a,b,c,d) ON user.username = vals.a
   WHEN MATCHED THEN UPDATE SET user.name = vals.b, user.email=vals.c,user.pw=vals.d
   WHEN NOT MATCHED THEN INSERT VALUES null, vals.a, vals.b,vals.c,vals.d;

MERGE INTO bet USING (VALUES (100, 'Donald Trump bet', 'Donald Trump will remain president until the end of his term.', TIMESTAMP '2017-04-06 02:00:22'))
AS vals(a,b,c,d) ON bet.title = vals.b
   WHEN NOT MATCHED THEN INSERT (initiator_id, title, description, pub_date) VALUES vals.a, vals.b,vals.c,vals.d;

MERGE INTO bet USING (VALUES (100, 101, 100, 'Winning Scrum Team bet', 'Team sushi will win this contest', TIMESTAMP '2017-04-06 02:00:22'))
AS vals(a,b,c,d,e,f) ON bet.title = vals.d   
   WHEN NOT MATCHED THEN INSERT (initiator_id, challenger_id, winner_id, title, description, pub_date)  VALUES vals.a, vals.b,vals.c,vals.d,vals.e,vals.f;

MERGE INTO bet USING (VALUES (101, 102, 'Dorde highest committer', 'By the end of this sprint Dorde will achieve the largest number of commits', TIMESTAMP '2017-04-06 02:00:22'))
AS vals(a,b,c,d,e) ON bet.title = vals.c   
   WHEN NOT MATCHED THEN INSERT (initiator_id, challenger_id, title, description, pub_date)  VALUES vals.a, vals.b,vals.c,vals.d,vals.e;
   
MERGE INTO bet USING (VALUES (104, 103, 'Test coverage','Sushi teams will achieve 80% unit test coverage by the end of sprint4', TIMESTAMP '2017-04-06 02:00:22'))
AS vals(a,b,c,d,e) ON bet.title = vals.c   
   WHEN NOT MATCHED THEN INSERT (initiator_id, challenger_id, title, description, pub_date)  VALUES vals.a, vals.b,vals.c,vals.d,vals.e;

MERGE INTO bet USING (VALUES (105, 'Documentation', 'Sushi Team will have a fully completed user guide documentation by the end of sprint 4', TIMESTAMP '2017-04-06 02:00:22'))
AS vals(a,b,c,d) ON bet.title = vals.b   
   WHEN NOT MATCHED THEN INSERT (initiator_id,  title, description, pub_date)  VALUES vals.a, vals.b,vals.c,vals.d;
