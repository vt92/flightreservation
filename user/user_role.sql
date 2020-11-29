use reservation

create table role(
id int not null auto_increment,
name varchar(20),
primary key (id)
)

create table user_role(
user_id int,
role_id int,
foreign key (user_id)
references user(id),
foreign key (role_id)
references role(id)
)

insert into role values(1,'ADMIN')

insert into user_role values (1,1)
