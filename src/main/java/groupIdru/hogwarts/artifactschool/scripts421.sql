ALTER TABLE students
    ADD CONSTRAINT name_constraint UNIQUE (name),
    ALTER COLUMN name SET NOT NULL,
    ADD CONSTRAINT age_constraint CHECK (age > 16),
    ALTER COLUMN age SET DEFAULT 20;

ALTER TABLE faculties
     ADD CONSTRAINT name_color_constraint UNIQUE (color, name);