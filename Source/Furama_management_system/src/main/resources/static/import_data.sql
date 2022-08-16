use furama_management_system_project;

insert into user(password, username) VALUES ('$2a$10$OBTxEe8n90PWGXUureVhi.gdzmZdrDSx/5bykDBLRrp18B2kkQZOy','admin'),('$2a$10$OBTxEe8n90PWGXUureVhi.gdzmZdrDSx/5bykDBLRrp18B2kkQZOy','user');
insert into role(role_name) values ('ROLE_ADMIN'),('ROLE_USER');
insert into user_role(role_id, user_id) VALUES (1,1),(2,2);

insert into facility_type (facility_type_name)
values ('Villa'),('House'),('Room');
insert into rent_type (rent_type_name)
values ('Year'),('Month'),('Day'),('Hour');

insert into facility(area, cost, description_other_convenience, facility_free, facility_name, max_people, number_of_floors, pool_area, standard_room, facility_type_id, rent_type_id) VALUES (25000,100000,'Pool available','free','Villa Beach Front',10,4,500,'vip',1,3),(14000,5000,'BBQ available','free','House Princess 01',7,3,200,'vip',2,2);

insert into customer_type(customer_type_name) values ('VIP'),('Premium'),('Gold'),('Member');

insert into customer(customer_address, customer_birthday, customer_email, customer_gender, customer_id_card, customer_name, customer_phone_number, customer_type_id) VALUES ('HCM','1990-01-01','abc@gmail.com',1,'123123123','Nguyen Van A','0905009008',1),('HN','1990-01-01','abc@gmail.com',0,'123123123','Tran B','0905009008',2),('DN','1990-01-01','abc@gmail.com',0,'123123123','Pham Thi B','0905009008',3),('QN','1990-01-01','abc@gmail.com',1,'123123123','Bui D','0905009008',4);

insert into position(position_name) values ('Reception'),('Supervisor'),('Manager'),('Director');
insert into education_degree(education_degree_name) values ('Intermediate'),('College'),('University'),('Postgraduate Edu');
insert into division(division_name) values ('Sales-Marketing'),('Administration'),('Management');