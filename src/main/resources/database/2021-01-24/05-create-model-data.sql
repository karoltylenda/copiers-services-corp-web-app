--liquibase formatted sql
--changeset mstopyra:4

insert into models(prints_In_Color,production_Year,printing_Speed,name,manufacturer_id ) values (true,2020,0,'C230',1);