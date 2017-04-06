drop table user if exists;
create table user (
  user_id integer primary key GENERATED BY DEFAULT AS IDENTITY(START WITH 100),
  username varchar(50) not null,
  email varchar(50) not null,
  pw varchar(255) not null
);

drop table bet if exists;
create table bet (
  bet_id integer primary key GENERATED BY DEFAULT AS IDENTITY(START WITH 100),
  initiator_id integer not null,
  amount_eur double not null,
  challenger_id integer,
  description varchar(300) not null,
  status varchar(80),
  referee_id integer,
  pub_date timestamp
);