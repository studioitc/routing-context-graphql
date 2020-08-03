CREATE TABLE PUBLIC.person
(
    id   INT,
    tenant VARCHAR(90),
    first_name VARCHAR(90),
    last_name VARCHAR(90)
);
INSERT INTO PUBLIC.person(id, tenant, first_name, last_name) VALUES (1, 'tenant2', 'Mario', 'Neri');
INSERT INTO PUBLIC.person(id, tenant, first_name, last_name) VALUES (2, 'tenant2', 'Marco', 'Rssi');
INSERT INTO PUBLIC.person(id, tenant, first_name, last_name) VALUES (3, 'tenant2', 'Luca', 'Verdi');
