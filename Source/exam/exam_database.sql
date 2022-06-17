create database if not exists exam_database;
use exam_database;
drop database exam_database;

create table book(
book_id int primary key,
book_name varchar(50),
book_author varchar(50),
description varchar(120),
book_quantity int
);

create table student (
student_id int primary key,
student_name varchar(50),
class varchar(50)
);

create table borrow_code(
borrow_code varchar(50) primary key,
book_id int,
student_id int, 
`status` int,
borrow_start_date date,
borrow_end_date date,
foreign key (book_id) references book(book_id),
foreign key (student_id) references student(student_id)
);
insert into borrow_code(borrow_code) values("(MS-1234");



