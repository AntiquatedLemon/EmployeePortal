-- create my new table for user roles
--primary key to uniquely identify each record in a table
--in order of running,
   -- users depends

DROP TABLE use;

CREATE TABLE user_roles(
		--because I want to assign "employees" and "finance manager" at least 	
		user_roles_id serial PRIMARY KEY,
		--every role should be words, but also every role must hae a title
		user_role_title TEXT NOT NULL
		);
	
CREATE TABLE users(
		user_id serial PRIMARY KEY,
		user_first_name TEXT NOT NULL,
		user_last_name TEXT NOT NULL,
		username TEXT UNIQUE NOT NULL,
		pword TEXT UNIQUE NOT NULL,
		user_roles_id_fk int REFERENCES user_roles(user_roles_id)
		--users get user_roles but a role may have one, many or no users) [association to user_roles]
);

CREATE TABLE reimbursement_type(
		reimb_type_id serial PRIMARY KEY,
		reimb_type TEXT
);

CREATE TABLE reimbursement_status(
		reimb_status_id serial PRIMARY KEY,
		reimb_status TEXT
		--status pk
);

CREATE TABLE reimbursement(
		reimb_id serial PRIMARY KEY,	
		reimb_amt decimal (12, 2), -- 12 digits allowed, 2 decimal points
		reimb_desc TEXT,
		
		--a reimbursement goes to a user but a user may have one, many or no reimbursements 
		reimb_id_fk_users int REFERENCES  users(user_id),
        
		--doesn't relate TO a PRIMARY KEY, already established above
        --not sure if I should do this again because the second line between the USERS and Reimbursement in the ERD
		reimb_id_fk_users2 TEXT  REFERENCES users(username),


		--a reimbursement belongs to a type
		reimb_id_fk_type int REFERENCES reimbursement_type(reimb_type_id),
		
		--a reimbursement belongs a status,
		reimb_id_fk_status int REFERENCES reimbursement_status(reimb_status_id)	
);


--simple value insert blocks to get things going so I can view the tables
--if I can simplify the names or relations between tables then 
INSERT INTO user_roles (user_roles_id, user_role_title)
VALUES (1, 'Finance Manager'), (2, 'Employee');
SELECT * FROM user_roles;

INSERT INTO users (user_first_name, user_last_name, username, pword, user_roles_id_fk)
VALUES ('Monae', 'McKinney', 'm.mckinney', 'abc', 1), ('Bryson', 'Gregory', 'b.gregory', 'def', 2), ('Lota', 'unk', 'Lota.unk', 'ghi', 2);
SELECT * FROM users;

INSERT INTO reimbursement_type (reimb_type_id, reimb_type)
VALUES (1, 'business expenses'), (2, 'auto mileage'), (3, 'travel expenses'), (4, 'employee stipends');
SELECT * FROM reimbursement_type;

INSERT INTO reimbursement_status (reimb_status_id , reimb_status)
VALUES (1, 'pending'), (2, 'approved'), (3, 'denied');
SELECT * FROM reimbursement_status;


INSERT INTO reimbursement (reimb_amt, reimb_desc, reimb_id_fk_users, reimb_id_fk_users2, reimb_id_fk_type, reimb_id_fk_status)
VALUES (25.74, 'travel expenses from Thailand project', 1, 'm.mckinney',  3, 3);
SELECT * FROM reimbursement;





