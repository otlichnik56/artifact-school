CREATE TABLE peoples(
    id INTEGER PRIMARY KEY,
    name TEXT,
    age INTEGER,
    driver_license BOOLEAN,
    car_id INTEGER REFERENCES cars(id)
);

CREATE TABLE cars(
    id INTEGER PRIMARY KEY,
    brand TEXT,
    model TEXT,
    price REAL
);



SELECT students.name, students.age, faculties.name
    FROM students
    INNER JOIN faculties ON students.faculty_id = faculties.id;

SELECT students.name, students.age, avatar.file_path
    FROM avatar
    LEFT JOIN students ON avatar.student_id = students.id;
