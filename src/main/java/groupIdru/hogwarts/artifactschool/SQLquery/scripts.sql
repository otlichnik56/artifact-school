
select * from students;

select * from students where age > 10 and age < 20;

select name from students;

select * from students where name like '%o%';

select * from students where students.age < students.id;

select * from students order by age;

SELECT COUNT(*) FROM students;

SELECT AVG(age) FROM students;

SELECT * FROM students ORDER BY id OFFSET((SELECT COUNT(*) FROM students) - 5);

