create table if not exists post
(
    id                 bigserial not null
        constraint post_pkey
            primary key,

    created_by         varchar(255),
    created_date       timestamp,
    last_modified_by   varchar(255),
    last_modified_date timestamp,
    title              varchar(255),
    body               text,
    user_id            bigint not null
);
