# catfish-sharding
sharding-jdbc-5.X demo

## env
springboot jpa
mysql8
shardingjdbc-5.0.0-beta

## run
com.catfish.sharding.OrderServiceTest#testCreate
application-sharding02.properties

## database script

```
use db0 & db1;

create table t_address
(
	id bigint auto_increment
		primary key,
	name varchar(255) null
)
comment '地址表';

create table t_order_0
(
	order_id bigint(11) default 0 not null
		primary key,
	user_id int null,
	status varchar(45) null,
	mobile varchar(60) null,
	mobile_cipher varchar(128) null,
	create_time date null
);

create table t_order_1
(
	order_id bigint(11) default 0 not null
		primary key,
	user_id int null,
	status varchar(45) null,
	mobile varchar(60) null,
	mobile_cipher varchar(128) null,
	create_time date null
);

create table t_order_item_0
(
	order_item_id bigint(11) default 0 not null
		primary key,
	order_id bigint(11) not null,
	user_id int null,
	status varchar(45) null
);

create index FK2y83rerik30vumt2a1mff6606
	on t_order_item_0 (order_id);

create table t_order_item_1
(
	order_item_id bigint(11) default 0 not null
		primary key,
	order_id bigint(11) not null,
	user_id int null,
	status varchar(45) null
);

```

## issue list

- VOLUME_RANGE algorithms exception
- BOUNDARY_RANGE algorithms exception
- INTERVAL algorithms exception


