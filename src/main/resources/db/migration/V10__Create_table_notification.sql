create table notification
(
	id bigint auto_increment,
	notifier varchar(50) not null,
	receiver varchar(50) not null,
	outer_id bigint not null,
	type int not null,
	gmt_create bigint not null,
	status int default 0,
	constraint notification_pk
		primary key (id)
);

