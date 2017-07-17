DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS harm_score_rules;
DROP TABLE IF EXISTS removability_score_rules;
DROP TABLE IF EXISTS kow_score_rules;

CREATE TABLE person  (
    id Bigserial PRIMARY KEY NOT NULL,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    harm_score integer,
    removability_score integer,
    kow_score integer,
    harm_category VARCHAR(20) ,
    removability_category VARCHAR(20),
    kow_category VARCHAR(20) ,
    band  VARCHAR(20)
);

CREATE TABLE harm_score_rules  (
    id integer,
    minScore integer,
    maxScore integer,
    previousCategory VARCHAR(20),
    newCategory VARCHAR(20)
);


INSERT INTO harm_score_rules VALUES (1, 0, 19, 'NA', 'LOW');
INSERT INTO harm_score_rules VALUES (2, 20, 99, 'NA', 'MEDIUM');
INSERT INTO harm_score_rules VALUES (3, 100, 1000, 'NA', 'HIGH');



CREATE TABLE removability_score_rules  (
    id integer,
    minScore integer,
    maxScore integer,
    previousCategory VARCHAR(20),
    newCategory VARCHAR(20)
);


INSERT INTO removability_score_rules VALUES (1, 0, 4, 'NA', 'RED');
INSERT INTO removability_score_rules VALUES (2, 5, 6, 'NA', 'AMBER');
INSERT INTO removability_score_rules VALUES (3, 7, 100, 'NA', 'GREEN');



CREATE TABLE kow_score_rules  (
    id integer,
    minScore integer,
    maxScore integer,
    previousCategory VARCHAR(20),
    newCategory VARCHAR(20)
);


INSERT INTO kow_score_rules VALUES (1, 0, 9, 'NA', 'MINIMAL_INFORMATION');
INSERT INTO kow_score_rules VALUES (2, 10, 19, 'NA', 'CONTACTABLE');
INSERT INTO kow_score_rules VALUES (3, 20, 100, 'NA', 'KNOWN');