-- Database: wailer_db

-- DROP DATABASE wailer_db;
CREATE DATABASE wailer_db
 WITH OWNER = wailer
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.UTF-8'
       LC_CTYPE = 'en_US.UTF-8'
       CONNECTION LIMIT = -1;


CREATE TABLE wails(id SERIAL PRIMARY KEY NOT NULL,
	name VARCHAR(125),
	message TEXT NOT NULL,
	timestamp TIMESTAMP NOT NULL,
	offensive boolean default false,
	upvotes int DEFAULT 0,
	downvotes int DEFAULT 0);

-- TODO Add ipaddress of type inet, to log wail ip. Add timed hash to disallow a certain amount of posts.

/*CREATE TABLE wails(id INT PRIMARY KEY NOT NULL,
			wail_id INT REFERENCES wail(id));*/

-- for admin users
create table users(id SERIAL primary key not null,
	username varchar(125) not null,
	password char(60) not null,
	role char(25),
	);

alter table users
	add constraint check_roles
	check (role = 'admin' or role = 'user');

--insert into wails (name, message, timestamp) values('TestUser', 'First message', '2015-01-01 04:05:01')