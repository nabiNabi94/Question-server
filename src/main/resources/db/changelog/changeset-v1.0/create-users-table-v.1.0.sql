create table Users (user_id  bigserial not null, user_create_date timestamp, user_email varchar(255) not null, user_name varchar(255) not null, user_password varchar(3000), user_login varchar(255) not null, primary key (user_id))