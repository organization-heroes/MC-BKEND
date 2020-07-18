drop table if exists user;
create table IF NOT EXISTS user (

	id bigint not null,
	f_name varchar(50),
	l_name varchar(50),
	user_name varchar(100) UNIQUE,
	password varchar(50),
	role varchar(50),
	address varchar(200),
	state varchar(50),
	country varchar(50),
	email varchar(50),
	pan varchar(20),
	contact_no varchar(15),
	dob varchar(15),
	ssn varchar(20),
	primary key (id)
);
