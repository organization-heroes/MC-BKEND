drop table if exists loan;
create table IF NOT EXISTS loan (

	id bigint not null,
	loan_num varchar(50) UNIQUE not null,
	loan_type varchar(100),
	loan_desc varchar(100),
	loan_status varchar(20),
	version bigint not null,
	lock_id bigint not null,
	user_id bigint not null,
	primary key (id)
);
