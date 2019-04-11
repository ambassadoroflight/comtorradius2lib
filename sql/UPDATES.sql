ALTER TABLE radius_log CHANGE called_mac_address called_station_id VARCHAR(32) DEFAULT '';

ALTER TABLE prepaid_customer DROP COLUMN expiration_date;

-- 03/abr/2019
ALTER TABLE hotspot ADD COLUMN zone INT DEFAULT 0 AFTER name;

CREATE TABLE zone (
    id                      INT(11)             NOT NULL        AUTO_INCREMENT,
    name                    VARCHAR(128)        NOT NULL,
    advertising_campaign    INT                 DEFAULT 0,

    PRIMARY KEY(id)
);

CREATE TABLE sponsor (
    id          INT             NOT NULL        AUTO_INCREMENT,
    name        VARCHAR(128)    NOT NULL,
    contact     VARCHAR(256)    DEFAULT '',
    phone       VARCHAR(32)     DEFAULT '',
    email       VARCHAR(128)    DEFAULT '',

    PRIMARY KEY(id)
);

INSERT INTO 
    privilege (code, description, category)
VALUES
    ('ADD_SPONSOR', 'Agregar patrocinador y publicidad', 'Publicidad'), 
    ('EDIT_SPONSOR', 'Editar patrocinador y publicidad', 'Publicidad'),
    ('VIEW_SPONSOR', 'Ver patrocinador y publicidad', 'Publicidad'),
    ('DELETE_SPONSOR', 'Eliminar patrocinador y publicidad', 'Publicidad');

CREATE TABLE advertising_campaign (
    id          INT             NOT NULL        AUTO_INCREMENT,
    sponsor     INT             NOT NULL,
    banner_1    VARCHAR(512)    NOT NULL,
    banner_2    VARCHAR(512)    NOT NULL,
    start_date  DATE            DEFAULT NULL,
    end_date    DATE            DEFAULT NULL,
    active      TINYINT(1)      DEFAULT 1,

    PRIMARY KEY(id)
);