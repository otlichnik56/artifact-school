CREATE TABLE students(
    id INTEGER PRIMARY KEY,
    name TEXT,
    age INTEGER,
    faculty_id INTEGER REFERENCES faculties(id)
);

CREATE TABLE faculties(
    id INTEGER PRIMARY KEY,
    name TEXT,
    color TEXT
);

SELECT students.name, students.age, faculties.name
    FROM students
    INNER JOIN faculties ON students.faculty_id = faculties.id;

SELECT students.name, students.age, avatar.file_path
    FROM avatar
    INNER JOIN students ON avatar.student_id = students.id;
