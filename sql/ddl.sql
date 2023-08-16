CREATE TABLE interview (
    id INT not null,
    created_date TIMESTAMP not null,
    user_id INT,
    primary key (id),
    foreign key
);