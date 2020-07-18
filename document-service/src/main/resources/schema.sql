drop table if exists document;
create table IF NOT EXISTS document (

	id bigint not null,
	doc_id varchar(50) UNIQUE,
	loan_num varchar(50),
	doc_title varchar(100),
	doc_desc varchar(100),
	doc_location varchar(100) UNIQUE,
	apprvl_status varchar(15),
	user_id bigint not null,
	primary key (id)
);
