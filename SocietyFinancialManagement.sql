create database SocietyFinancialManagement;

use SocietyFinancialManagement;


create table plotdetails(
	plotid integer not null unique auto_increment,
    plottype varchar(35) not null
);


create table plot(
	pid integer not null unique auto_increment,
    id integer,
    plotnumber varchar(35) primary key,
    rent double ,
    sqft integer,
    cust_id integer,
    maintainance double default (sqft*3),
    status boolean  not null default 0
);


create table user(
	id integer unique auto_increment,
    name varchar(250) not null,
    email varchar(250) primary key not null,
    password varchar(35) not null default '12345678',
    country varchar(250),
    state varchar(250),
    district varchar(250),
    checkin timestamp default current_timestamp,
    plottype varchar(50) not null,
    phone varchar(10) not null,
    rent double not null,
    plotno varchar(30) not null,
    familycount integer not null
);


create table userRecords(
	id integer unique not null auto_increment,
    name varchar(250) not null,
    plotno varchar(30),
    email varchar(250),
    country varchar(250),
    state varchar(250),
    district varchar(250),
    familycount integer,
    phone varchar(10),
    checkin timestamp,
    checkout timestamp default current_timestamp
);

insert into plotdetails(plottype) values('1BHK'),('2BHK'),('3BHK'),('Delux'),('Independet');

insert into plot (id,plotnumber,rent,sqft) values
(1,'A-101',15000,250),
(1,'A-102',15000,250),
(2,'A-103',15000,250),
(2,'B-101',20000,300),
(3,'B-102',20000,300),
(4,'B-103',20000,300);

create table employee(
	eid integer not null auto_increment,
    ename varchar(250) not null,
    emailid varchar(250) not null,
    dob date not null default '1998-12-12',
    password varchar(35) default '00000000',
    address varchar(250) not null,
    phone varchar(10) not null,
    gender varchar(1) not null,
    salary double not null,
    doj date not null,
    dol date,
    primary key(emailid),unique(eid)

);



create table event(
	eventid integer not null auto_increment,
    eventname varchar(150) not null ,
    eveantlocation  varchar(100) not null default 'society hall',
    chiefguest varchar(250) not null,
    amount double not null,
    contact varchar(250) default '-',
    eventdate date not null,
    unique(eventid)
);

create table admin(
	id integer unique not null auto_increment,
    email varchar(250) primary key not null,
    password varchar(35) not null,
    name varchar(250) not null
);

insert into admin(email,password,name) values('admin@gmail.com','admin123','admin');


create table employeerecords(
	id integer not null unique auto_increment,
    ename varchar(250) not null,
    emailid varchar(250) not null,
    dob date not null default '1998-12-12',
    address varchar(250) not null,
    phone varchar(10) not null,
    gender varchar(1) not null,
    salary double not null,
    doj date not null,
    dol date,
    primary key(emailid)
);


create table contact(
	id integer unique auto_increment,
    name varchar(250) not null,
    emailid varchar(250) not null,
    message varchar(250) not null,
    RequestedTime timestamp default current_timestamp
);


create table maintainance(
	id integer unique not null auto_increment,
	name varchar(250) not null,
    email varchar(250) not null,
    plotno varchar(30) not null,
    maintainance double,
    extra double default 0,
    total double,
    paymentstatus boolean default false
);

create table receipts(
	id integer unique not null auto_increment,
    name varchar(250) not null,
    email varchar(250) not null,
    plotno varchar(30) not null,
    maintainance double not null,
    fine double default 0,
    total double default(maintainance+fine),
    paidtime timestamp default current_timestamp
);
