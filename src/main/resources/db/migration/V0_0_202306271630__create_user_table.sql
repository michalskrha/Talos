create table if not exists users
(
    id                 bigserial not null
        constraint user_pkey
            primary key,
    created_by         varchar(255),
    created_date       timestamp,
    last_modified_by   varchar(255),
    last_modified_date timestamp,
    name               varchar(255),
    user_name          varchar(255),
    phone              varchar(255),
    website            varchar(255),
    email              varchar(255)
        constraint uk_ob8kqyqqgmefl0aco34akdtpe
            unique,
    street             varchar(255),
    suite              varchar(255),
    city               varchar(255),
    zip_code           varchar(100),
    geo_lat            varchar(100),
    geo_lng            varchar(100),
    company_id            bigint not null
        constraint fk859n28172ey2837e7r65w3erf
            references company
);
