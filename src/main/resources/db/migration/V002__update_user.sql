alter table user_entity add column first_name varchar(255);

update user_entity set first_name=given_name;

alter table user_entity alter column first_name set not null;

alter table user_entity drop column given_name;