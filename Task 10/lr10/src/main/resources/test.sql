-- create database java;
create table student
(
    id serial not null primary key,
    name text not null,
    passport_series smallint not null,
    passport_number smallint not null
);
create table subject
(
    id serial not null primary key,
    name_subject text not null
);
create table progress
(
    id serial not null primary key,
    estimation smallint not null,
    student smallint not null references student(id) on delete cascade,
    subject smallint not null references subject(id)
);

INSERT into student (id, name, passport_series, passport_number)
values
    (1, 'Вася', 3311, 435678),
    (2, 'Коля', 3322, 342345),
    (3, 'Петя', 3333, 123456),
    (4, 'Маша', 3344, 234567),
    (5, 'Даша', 3355, 345678),
    (6, 'Саша', 3366, 456789),
    (7, 'Лена', 3377, 567890),
    (8, 'Катя', 3388, 678901),
    (9, 'Оля', 3399, 789012),
    (10, 'Юля', 3300, 890123);

INSERT into subject (id, name_subject)
values
    (1, 'Математика'),
    (2, 'Русский язык'),
    (3, 'Физика'),
    (4, 'Химия'),
    (5, 'Биология'),
    (6, 'Информатика');

INSERT into progress (id, student, subject, estimation)
values
    (1, 1, 1, 5),
    (2, 1, 2, 4),
    (3, 1, 3, 5),
    (4, 1, 4, 4),
    (5, 1, 5, 5),
    (6, 1, 6, 4),
    (7, 2, 1, 3),
    (8, 2, 2, 4),
    (9, 2, 3, 3),
    (10, 2, 4, 4),
    (11, 2, 5, 3),
    (12, 2, 6, 4),
    (13, 3, 1, 5),
    (14, 3, 2, 5),
    (15, 3, 3, 5),
    (16, 3, 4, 5),
    (17, 3, 5, 5),
    (18, 3, 6, 5),
    (19, 4, 1, 4),
    (20, 4, 2, 4),
    (21, 4, 3, 4),
    (22, 4, 4, 4),
    (23, 4, 5, 4),
    (24, 4, 6, 4),
    (25, 5, 1, 3),
    (26, 5, 2, 3),
    (27, 5, 3, 3),
    (28, 5, 4, 3),
    (29, 5, 5, 3),
    (30, 5, 6, 3),
    (31, 6, 1, 2),
    (32, 6, 2, 2),
    (33, 6, 3, 2),
    (34, 6, 4, 2),
    (35, 6, 5, 2),
    (36, 6, 6, 2);

alter  table student add constraint unicue_passport unique (passport_number,passport_series);

alter table progress add constraint check_estimation check (estimation<=5 and estimation>=2);

select s.name, p.estimation, ss.name_subject from student s
                                                      inner join progress p on s.id = p.id
                                                      inner join subject ss on ss.id = p.id
where p.estimation > 3 and ss.name_subject = 'Химия';

select avg(p.estimation) as "Средний балл" from progress p
                                                    inner join subject s on s.id = p.subject
where s.name_subject = 'Физика';

select avg(p.estimation) as "Средний балл" from progress p
                                                    inner join subject s on  p.subject = s.id
                                                    inner join student s2 on p.student = s2.id
where s2.name = 'Петя';

SELECT count(*), s.name_subject from progress p
                                         inner join subject s on s.id = p.subject
where p.estimation > 2
group by s.name_subject
order by count(*) desc limit 3;


SELECT s.name
FROM student s
LEFT JOIN progress p ON s.id = p.student
GROUP BY s.name
HAVING COUNT(p.estimation) > 0 AND MAX(p.estimation) = 4 AND MIN(p.estimation) = 4;