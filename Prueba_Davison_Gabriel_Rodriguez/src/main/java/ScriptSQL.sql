CREATE DATABASE RiwiAcademy;
use RiwiAcademy;

create table student(
id_student int auto_increment primary key,
name varchar (50) not null,
last_name varchar(50) not null,
email varchar (100) unique not null,
status enum('ACTIVE','INACTIVE')
);

create table  course(
id_course int auto_increment primary key,
name_course varchar(100) not null unique
);

create table inscription(
id_inscription int auto_increment primary key,
id_student int,
id_course int,
foreign key (id_student) references student (id_student)on delete cascade,
foreign key(id_course) references course (id_course) on delete cascade 
);

create table qualification(
id_qualification int auto_increment primary key,
description text not null,
quialificacion int not null,
id_course int,
foreign key (id_course) references course (id_course) on delete cascade
);





#-------------------------------------------------------
select * from student ;
inner join inscription on student.id_student = inscription.id_student;
select * from course;
select * from inscription;
select * from qualification;

Alter table qualification add qualification int;
ALTER TABLE qualification
ADD FOREIGN KEY (id_student) REFERENCES student(id_student);
alter TABLE qualification drop column quialificacion;


SELECT * FROM inscription INNER JOIN course on inscription.id_course != course.id_course delete id_;


DELETE FROM course WHERE id_course =? 
inner join course on course.id_course = inscription.id_course;
