create table user_entity
(
    id                   int8         not null,
    birth_date           date         not null,
    given_name           varchar(255) not null,
    last_name            varchar(255) not null,
    primary key (id)
);

create sequence hibernate_sequence start 1 increment 1;
