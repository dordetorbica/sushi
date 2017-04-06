INSERT INTO user(user_id, username, email, pw) VALUES (1, 'user001','user001@email.com','$2a$10$IHdRfnhNgQesPFD5hrUcMOvyx5RrRcklkpXfs9YX4j1qXvouEeVIa');
INSERT INTO user(user_id, username, email, pw) VALUES (2, 'user002','user002@email.com','$2a$10$NlU0bdBUiegZWZvl6CGpj.wV5YfbDGZ8lYznxWp2NNE4F9cYJJFOe');
INSERT INTO user(user_id, username, email, pw) VALUES (3, 'user003','user003@email.com','$2a$10$gwEVdI6lSDrkIkGLrsHTIOzLEgnT3gDUDYMOfxvOdnoqvGpf275fm');
INSERT INTO user(user_id, username, email, pw) VALUES (4, 'user004','user004@email.com','$2a$10$W2ZJXI00xhL03vwcy2Y/DeZe.BqMf4dUSP5lxEQFAqV.ocbUAYS4m');

INSERT INTO bet(initiator_id, amount_eur, description, pub_date) VALUES (1, 50, 'Who bets Donald Trump will remain president until the end of his term?', TIMESTAMP '2017-04-06 02:00:22');
INSERT INTO bet(initiator_id, amount_eur, description, pub_date) VALUES (2, 20, 'Who bets Team sushi will win this contest?', TIMESTAMP '2017-04-06 02:00:22');