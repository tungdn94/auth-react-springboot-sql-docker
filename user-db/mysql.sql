CREATE TABLE IF NOT EXISTS role_info(
    `id` INT PRIMARY KEY,
    `role_name` VARCHAR(50) NOT NULL,
    `role_code` INT NOT NULL
);

insert into role_info (id, role_name, role_code) values (1, 'UNREGISTERED', -1);
insert into role_info (id, role_name, role_code) values (2, 'ADMIN', 0);
insert into role_info (id, role_name, role_code) values (3, 'MODERATOR', 1);
insert into role_info (id, role_name, role_code) values (4, 'USER', 2);

CREATE TABLE IF NOT EXISTS user_info (
	id INT PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	user_name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL
    );

insert into user_info (id, first_name, last_name, user_name, password, email) values (1, 'Claire', 'Assandri', 'cassandri0', 'qsnLMnxY8', 'cassandri0@walmart.com');
insert into user_info (id, first_name, last_name, user_name, password, email) values (2, 'Sargent', 'Pargent', 'spargent1', 'uLz6hapSe', 'spargent1@live.com');
insert into user_info (id, first_name, last_name, user_name, password, email) values (3, 'Ad', 'Rust', 'arust2', 'nqwlZulIFFt', 'arust2@paginegialle.it');
insert into user_info (id, first_name, last_name, user_name, password, email) values (4, 'Rudy', 'Braxton', 'rbraxton3', '2H4Lc0X', 'rbraxton3@wix.com');
insert into user_info (id, first_name, last_name, user_name, password, email) values (5, 'Lynnett', 'Gath', 'lgath4', 'JziSVQ7ed', 'lgath4@geocities.jp');
insert into user_info (id, first_name, last_name, user_name, password, email) values (6, 'Clifford', 'Viveash', 'cviveash5', 'XsjkCW', 'cviveash5@google.es');
insert into user_info (id, first_name, last_name, user_name, password, email) values (7, 'Robinetta', 'Redmond', 'rredmond6', '35x1w7Ldieoe', 'rredmond6@oaic.gov.au');
insert into user_info (id, first_name, last_name, user_name, password, email) values (8, 'Malina', 'Gyver', 'mgyver7', 'JQVuhpM86ucm', 'mgyver7@slate.com');
insert into user_info (id, first_name, last_name, user_name, password, email) values (9, 'Aggie', 'Mephan', 'amephan8', 'vt8btq9eC', 'amephan8@java.com');
insert into user_info (id, first_name, last_name, user_name, password, email) values (10, 'Sile', 'Baddiley', 'sbaddiley9', 'ne42PQnh6Oq', 'sbaddiley9@cdbaby.com');
insert into user_info (id, first_name, last_name, user_name, password, email) values (11, 'Estrellita', 'Dunderdale', 'edunderdalea', '6Oj1Yw', 'edunderdalea@weibo.com');
insert into user_info (id, first_name, last_name, user_name, password, email) values (12, 'Merrily', 'Doxsey', 'mdoxseyb', 'sF6rAnuGVUN', 'mdoxseyb@java.com');
insert into user_info (id, first_name, last_name, user_name, password, email) values (13, 'Jolene', 'Matityahu', 'jmatityahuc', 'UPz0DsCXEal', 'jmatityahuc@domainmarket.com');
insert into user_info (id, first_name, last_name, user_name, password, email) values (14, 'Consolata', 'Van Ross', 'cvanrossd', 'YybuPAWE9v', 'cvanrossd@ca.gov');
insert into user_info (id, first_name, last_name, user_name, password, email) values (15, 'Maxine', 'Tillot', 'mtillote', 'WV2pFp', 'mtillote@discuz.net');
insert into user_info (id, first_name, last_name, user_name, password, email) values (16, 'Carlotta', 'Moysey', 'cmoyseyf', 'lmvOYa', 'cmoyseyf@spotify.com');
insert into user_info (id, first_name, last_name, user_name, password, email) values (17, 'Fanchette', 'Porritt', 'fporrittg', '2kVRRKl9', 'fporrittg@usda.gov');
insert into user_info (id, first_name, last_name, user_name, password, email) values (18, 'Stacie', 'Baraja', 'sbarajah', 'sKUGPJj4', 'sbarajah@purevolume.com');
insert into user_info (id, first_name, last_name, user_name, password, email) values (19, 'Marta', 'Barber', 'mbarberi', 'IdBEJkV', 'mbarberi@youtube.com');
insert into user_info (id, first_name, last_name, user_name, password, email) values (20, 'Ernesta', 'Michie', 'emichiej', 'eikXxKj4Tyst', 'emichiej@mediafire.com');

CREATE TABLE IF NOT EXISTS users_roles (
	user_id INT,
	role_id INT,
	PRIMARY KEY (user_id, role_id),
	FOREIGN KEY (user_id) REFERENCES user_info(id),
    FOREIGN KEY (role_id) REFERENCES role_info(id)
);

insert into users_roles (user_id, role_id) values (1, 2);
insert into users_roles (user_id, role_id) values (2, 3);
insert into users_roles (user_id, role_id) values (3, 4);
insert into users_roles (user_id, role_id) values (4, 4);
insert into users_roles (user_id, role_id) values (5, 4);
insert into users_roles (user_id, role_id) values (6, 4);
insert into users_roles (user_id, role_id) values (7, 4);
insert into users_roles (user_id, role_id) values (8, 4);
insert into users_roles (user_id, role_id) values (9, 3);
insert into users_roles (user_id, role_id) values (10, 3);
insert into users_roles (user_id, role_id) values (11, 4);
insert into users_roles (user_id, role_id) values (12, 4);
insert into users_roles (user_id, role_id) values (13, 4);
insert into users_roles (user_id, role_id) values (14, 4);
insert into users_roles (user_id, role_id) values (15, 3);
insert into users_roles (user_id, role_id) values (16, 4);
insert into users_roles (user_id, role_id) values (17, 3);
insert into users_roles (user_id, role_id) values (18, 4);
insert into users_roles (user_id, role_id) values (19, 4);
insert into users_roles (user_id, role_id) values (20, 4);