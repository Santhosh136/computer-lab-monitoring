insert into course(code, name, semester) values
('16IPC105', 'C Programming Laboratory', 'I'),
('16IPC206', 'Python Programming Laboratory', 'II');

insert into student(reg_no, name, semester) values
('136', 'santhosh', 'I'),
('103', 'adhiban', 'I'),
('122', 'mohan', 'I');

insert into user(user_name, password, roles, active, student_reg_no) values
('136', 'san', 'ROLE_USER', 1, '136'),
('103', 'adhi', 'ROLE_USER', 1, '103'),
('122', 'mohan', 'ROLE_USER', 1, '122');

insert into experiment(id, title, exp_no, description, course_code) values
(1, 'prime number', '1','prime or not', '16IPC105'),
(2, 'star pattern', '2','print start patter', '16IPC105');
