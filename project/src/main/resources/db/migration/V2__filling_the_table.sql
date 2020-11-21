INSERT INTO userrole.users VALUES
(1, "Vasya", "SureVasya", "password1"),
(2, "Petya", "Petruha", "passWord2");

INSERT INTO userrole.roles VALUES
(1, "Admin"),
(2, "Operator"),
(3, "Analyst"),
(4, "Moderator");

INSERT INTO userrole.role2user VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 4),
(4, 2, 2),
(5, 2, 3);