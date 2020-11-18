USE sys_hr;

CREATE TABLE Department (
	dep_id int PRIMARY KEY,
    dep_name varchar(20)
);

CREATE TABLE Employee(
	emp_id int PRIMARY KEY AUTO_INCREMENT,
    emp_fname varchar(20),
    emp_lname varchar(20) NOT NULL,
    start_date date,
    dep_id int,
    FOREIGN KEY (dep_id) REFERENCES Department(dep_id)
);
INSERT INTO Employee(emp_fname, emp_lname, start_date, dep_id) VALUES("John", "Doe", "2020-02-09", 1);
INSERT INTO Department VALUES(1, 'Tech');
INSERT INTO Department VALUES(2, 'Marketing');
INSERT INTO Department VALUES(3, 'Sales');