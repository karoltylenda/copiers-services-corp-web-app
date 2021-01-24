--liquibase formatted sql
--changeset mstopyra:2

create table copiers.models
(
    id              bigint auto_increment
        primary key,
    name            varchar(255) not null,
    printing_format int          null,
    printing_speed  int          not null,
    prints_in_color bit          not null,
    production_year int          null,
    manufacturer_id bigint       null,
    constraint FK9th2hfj1oai534pcc5ru41xk5
        foreign key (manufacturer_id) references copiers.manufacturers (id)
);

