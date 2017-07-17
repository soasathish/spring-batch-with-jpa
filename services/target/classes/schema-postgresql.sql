DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS harm_score_rules;
DROP TABLE IF EXISTS removability_score_rules;
DROP TABLE IF EXISTS kow_score_rules;
DROP TABLE IF EXISTS band_rules;

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


CREATE TABLE band_rules  (
    id integer,
    harm VARCHAR(20),
    removability VARCHAR(20),
    kow VARCHAR(20),
    previousCategory VARCHAR(20),
    newCategory VARCHAR(20)
);



INSERT INTO band_rules VALUES (1, 'HIGH', 'RED', 'KNOWN','NA', 'OP1');
INSERT INTO band_rules VALUES (2, 'HIGH', 'RED', 'CONTACTABLE','NA', 'OP2');
INSERT INTO band_rules VALUES (3, 'HIGH', 'RED', 'MINIMAL_INFORMATION','NA', 'OP3');

INSERT INTO band_rules VALUES (4, 'HIGH', 'AMBER', 'KNOWN','NA', 'OP4');
INSERT INTO band_rules VALUES (5, 'HIGH', 'AMBER', 'CONTACTABLE','NA', 'OP5');
INSERT INTO band_rules VALUES (6, 'HIGH', 'AMBER', 'MINIMAL_INFORMATION','NA', 'OP6');

INSERT INTO band_rules VALUES (7, 'HIGH', 'GREEN', 'KNOWN','NA', 'OP7');
INSERT INTO band_rules VALUES (8, 'HIGH', 'GREEN', 'CONTACTABLE','NA', 'OP8');
INSERT INTO band_rules VALUES (9, 'HIGH', 'GREEN', 'MINIMAL_INFORMATION','NA', 'OP9');



INSERT INTO band_rules VALUES (10, 'MEDIUM', 'RED', 'KNOWN','NA', 'OP28');
INSERT INTO band_rules VALUES (11, 'MEDIUM', 'RED', 'CONTACTABLE','NA', 'OP29');
INSERT INTO band_rules VALUES (12, 'MEDIUM', 'RED', 'MINIMAL_INFORMATION','NA', 'OP30');

INSERT INTO band_rules VALUES (13, 'MEDIUM', 'AMBER', 'KNOWN','NA', 'OP31');
INSERT INTO band_rules VALUES (14, 'MEDIUM', 'AMBER', 'CONTACTABLE','NA', 'OP32');
INSERT INTO band_rules VALUES (15, 'MEDIUM', 'AMBER', 'MINIMAL_INFORMATION','NA', 'OP33');

INSERT INTO band_rules VALUES (16, 'MEDIUM', 'GREEN', 'KNOWN','NA', 'OP34');
INSERT INTO band_rules VALUES (17, 'MEDIUM', 'GREEN', 'CONTACTABLE','NA', 'OP35');
INSERT INTO band_rules VALUES (18, 'MEDIUM', 'GREEN', 'MINIMAL_INFORMATION','NA', 'OP36');


INSERT INTO band_rules VALUES (19, 'LOW', 'RED', 'KNOWN','NA', 'OP55');
INSERT INTO band_rules VALUES (20, 'LOW', 'RED', 'CONTACTABLE','NA', 'OP56');
INSERT INTO band_rules VALUES (21, 'LOW', 'RED', 'MINIMAL_INFORMATION','NA', 'OP57');

INSERT INTO band_rules VALUES (22, 'LOW', 'AMBER', 'KNOWN','NA', 'OP58');
INSERT INTO band_rules VALUES (23, 'LOW', 'AMBER', 'CONTACTABLE','NA', 'OP59');
INSERT INTO band_rules VALUES (24, 'LOW', 'AMBER', 'MINIMAL_INFORMATION','NA', 'OP60');

INSERT INTO band_rules VALUES (25, 'LOW', 'GREEN', 'KNOWN','NA', 'OP61');
INSERT INTO band_rules VALUES (26, 'LOW', 'GREEN', 'CONTACTABLE','NA', 'OP62');
INSERT INTO band_rules VALUES (27, 'LOW', 'GREEN', 'MINIMAL_INFORMATION','NA', 'OP63');