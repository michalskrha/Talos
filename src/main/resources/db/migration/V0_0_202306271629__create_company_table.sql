create table if not exists company
(
    id                 bigserial not null
        constraint company_pkey
            primary key,
    name               varchar(255),
    catch_phrase       varchar(255),
    bs                 varchar(255),
    created_by         varchar(255),
    created_date       timestamp,
    last_modified_by   varchar(255),
    last_modified_date timestamp
);
