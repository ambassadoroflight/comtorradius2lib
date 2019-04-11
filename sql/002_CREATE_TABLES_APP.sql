-- -----------------------------------------------------------------------------
-- COMTOR RADIUS 2 LIB
-- TABLES APP
-- Version 1.0.0
-- Jan 24, 2019
-- -----------------------------------------------------------------------------

-- -----------------------------------------------------------------------------
-- Tabla customer_hotspot
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS customer_hotspot;

CREATE TABLE customer_hotspot (
    id                  INT(11)         NOT NULL        AUTO_INCREMENT,
    hotspot             INT(11)         DEFAULT 0,
    login               VARCHAR(64)     DEFAULT '',
    creation_date       DATE            DEFAULT NULL,
    created_by          VARCHAR(64)     DEFAULT '',
    active              TINYINT(1)      DEFAULT 1,
    
    PRIMARY KEY (id)
);

-- -----------------------------------------------------------------------------
-- Tabla customer_mac_address
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS customer_mac_address;

CREATE TABLE customer_mac_address (
    id                  INT(11)         NOT NULL        AUTO_INCREMENT,
    hotspot             INT(11)         DEFAULT 0,
    mac_address         VARCHAR(32)     DEFAULT '',
    description         VARCHAR(64)     DEFAULT '',
    creation_date       DATETIME        DEFAULT NULL,
    created_by          VARCHAR(64)     DEFAULT '',
    first_use_date      DATETIME        DEFAULT NULL,
    active              TINYINT(1)      DEFAULT 1,
    hotspot_joined      INT(11)         DEFAULT 0,

    PRIMARY KEY (id)
);

CREATE INDEX customer_mac_address_mac_idx ON customer_mac_address(mac_address);
CREATE INDEX customer_mac_address_hotspot_idx ON customer_mac_address(hotspot);

-- -----------------------------------------------------------------------------
-- Tabla happy_hour
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS happy_hour;

CREATE TABLE happy_hour (
    id                  INT(11)         NOT NULL        AUTO_INCREMENT,
    hotspot             INT(11)         DEFAULT 0,
    login               VARCHAR(16)     DEFAULT '',
    start_time          INT(11)         DEFAULT 0,
    end_time            INT(11)         DEFAULT 0,
    sunday              TINYINT(1)      DEFAULT 0,
    monday              TINYINT(1)      DEFAULT 0,
    tuesday             TINYINT(1)      DEFAULT 0,
    wednesday           TINYINT(1)      DEFAULT 0,
    thursday            TINYINT(1)      DEFAULT 0,
    friday              TINYINT(1)      DEFAULT 0,
    saturday            TINYINT(1)      DEFAULT 0,
    creation_date       DATETIME        DEFAULT NULL,
    created_by          VARCHAR(64)     DEFAULT '',

    PRIMARY KEY (id)
);

CREATE INDEX happy_hour_hotspot_idx ON happy_hour(hotspot);
CREATE INDEX happy_hour_login_idx ON happy_hour(login);

INSERT INTO 
    privilege (code, description, category)
VALUES
    ('ADD_HAPPY_HOUR', 'Agregar Hora Libre', 'Kioscos'), 
    ('EDIT_HAPPY_HOUR', 'Editar Hora Libre', 'Kioscos'),
    ('VIEW_HAPPY_HOUR', 'Ver Hora Libre', 'Kioscos'),
    ('DELETE_HAPPY_HOUR', 'Eliminar Hora Libre', 'Kioscos');


-- -----------------------------------------------------------------------------
-- Tabla sponsor
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS sponsor;

CREATE TABLE sponsor (
    id          INT             NOT NULL        AUTO_INCREMENT,
    name        VARCHAR(128)    NOT NULL,
    contact     VARCHAR(256)    DEFAULT '',
    phone       VARCHAR(32)     DEFAULT '',
    email       VARCHAR(128)    DEFAULT ''

    PRIMARY KEY(id)
);

INSERT INTO 
    privilege (code, description, category)
VALUES
    ('ADD_SPONSOR', 'Agregar patrocinador y publicidad', 'Publicidad'), 
    ('EDIT_SPONSOR', 'Editar patrocinador y publicidad', 'Publicidad'),
    ('VIEW_SPONSOR', 'Ver patrocinador y publicidad', 'Publicidad'),
    ('DELETE_SPONSOR', 'Eliminar patrocinador y publicidad', 'Publicidad');

-- -----------------------------------------------------------------------------
-- Tabla sponsor
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS advertising_campaign;

CREATE TABLE advertising_campaign (
    id          INT             NOT NULL        AUTO_INCREMENT,
    sponsor     INT             NOT NULL,
    banner_1    VARCHAR(512)    NOT NULL,
    banner_2    VARCHAR(512)    NOT NULL,
    survey      INT             DEFAULT 0,
    start_date  DATE            DEFAULT NULL,
    end_date    DATE            DEFAULT NULL,
    active      TINYINT(1)      DEFAULT 1,

    PRIMARY KEY(id)
);

-- -----------------------------------------------------------------------------
-- Tabla zone
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS zone;

CREATE TABLE zone (
    id                      INT(11)             NOT NULL        AUTO_INCREMENT,
    name                    VARCHAR(128)        NOT NULL,
    advertising_campaign    INT                 DEFAULT 0,

    PRIMARY KEY(id)
);

-- -----------------------------------------------------------------------------
-- Tabla hotspot
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS hotspot;

CREATE TABLE hotspot (
    id                  INT(11)         NOT NULL        AUTO_INCREMENT,
    called_station_id   VARCHAR(64)     NOT NULL,
    ip_address          VARCHAR(64)     NOT NULL,
    name                VARCHAR(64)     DEFAULT '',
    zone                INT(11)         DEFAULT 0,
    username            VARCHAR(64)     DEFAULT '',
    password            VARCHAR(128)    DEFAULT '',
    configured          TINYINT(1)      DEFAULT 0,
  
    PRIMARY KEY (id)
);

CREATE INDEX hotspot_called_station_id_idx ON hotspot(called_station_id);
CREATE INDEX hotspot_ip_address_idx ON hotspot(ip_address);
CREATE INDEX hotspot_zone_idx ON hotspot(zone);

INSERT INTO 
    privilege (code, description, category)
VALUES
    ('ADD_HOTSPOT', 'Agregar Hotspot', 'Hotspot'), 
    ('EDIT_HOTSPOT', 'Editar Hotspot', 'Hotspot'),
    ('VIEW_HOTSPOT', 'Ver Hotspot', 'Hotspot'),
    ('DELETE_HOTSPOT', 'Eliminar Hotspot', 'Hotspot');

-- -----------------------------------------------------------------------------
-- Tabla kiosk
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS kiosk;

CREATE TABLE kiosk (
    id          INT(11)             NOT NULL        AUTO_INCREMENT,
    nit         VARCHAR(32)         NOT NULL,
    name        VARCHAR(64)         NOT NULL,
    city        VARCHAR(128)        DEFAULT '',
    address     VARCHAR(128)        DEFAULT '',
    phone       VARCHAR(32)         DEFAULT '',
    email       VARCHAR(128)        DEFAULT '',
    hotspot     INT(11)             DEFAULT 0,
    
    PRIMARY KEY (id)
);

CREATE INDEX kiosk_hotspot__idx ON kiosk(hotspot);

INSERT INTO 
    privilege (code, description, category)
VALUES
    ('ADD_KIOSK', 'Agregar kiosco', 'Kioscos'), 
    ('EDIT_KIOSK', 'Editar kiosco', 'Kioscos'),
    ('VIEW_KIOSK', 'Ver kiosco', 'Kioscos'),
    ('DELETE_KIOSK', 'Eliminar kiosco', 'Kioscos');

-- -----------------------------------------------------------------------------
-- Tabla postpaid_customer
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS postpaid_customer;

CREATE TABLE postpaid_customer (
    id                  INT(11)             NOT NULL        AUTO_INCREMENT,
    login               VARCHAR(64)         NOT NULL,
    password            VARCHAR(128)        NOT NULL,
    type                VARCHAR(32)         DEFAULT '',
    name                VARCHAR(64)         DEFAULT '',
    document            VARCHAR(32)         DEFAULT '',
    address             VARCHAR(128)        DEFAULT '',
    phone               VARCHAR(64)         DEFAULT '',
    phone2              VARCHAR(64)         DEFAULT '',
    email               VARCHAR(128)        DEFAULT '',
    local               VARCHAR(32)         DEFAULT '',
    description         VARCHAR(128)        DEFAULT '',
    creation_date       DATETIME            DEFAULT NULL,
    created_by          VARCHAR(32)         DEFAULT '',
    first_use_date      DATETIME            DEFAULT NULL,
    active              TINYINT(1)          DEFAULT 1,
    
    PRIMARY KEY (id)
);

CREATE INDEX postpaid_customer_login_idx on postpaid_customer(login);

-- -----------------------------------------------------------------------------
-- Tabla prepaid_customer
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS prepaid_customer;

CREATE TABLE prepaid_customer (
    id                  INT(11)             NOT NULL        AUTO_INCREMENT,
    login               VARCHAR(64)         NOT NULL,
    password            VARCHAR(128)        DEFAULT '',
    pin_type            VARCHAR(8)          NOT NULL,
    purchased_time      INT(11)             DEFAULT 0,
    mac_address         VARCHAR(32)         DEFAULT '',
    description         VARCHAR(256)        DEFAULT '',
    creation_date       DATETIME            DEFAULT NULL,
    creation_ddate      DATE                DEFAULT NULL,
    first_use_date      DATETIME            DEFAULT NULL,
    expiration_date     DATETIME            DEFAULT NULL,
    end_session_date    DATETIME            DEFAULT NULL,
    hotspot             INT(11)             DEFAULT 0,
    hotspot_joined      INT(11)             DEFAULT 0,
    created_by          VARCHAR(64)         DEFAULT '',
    attr_1              varchar(64)         DEFAULT '',
    attr_2              varchar(64)         DEFAULT '',
    active              TINYINT(1)          DEFAULT 1,
  
    PRIMARY KEY (id)
);

CREATE INDEX prepaid_customer_login_idx ON prepaid_customer(login);
CREATE INDEX prepaid_customer_mac_address_idx ON prepaid_customer(mac_address);
CREATE INDEX prepaid_customer_hotspot_idx ON prepaid_customer(hotspot);
CREATE INDEX prepaid_customer_creation_ddate_idx ON prepaid_customer(creation_ddate);
CREATE INDEX prepaid_active_address_idx ON prepaid_customer(active);

-- -----------------------------------------------------------------------------
-- Tabla prepaid_rate
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS prepaid_rate;

CREATE TABLE prepaid_rate (
    id                      INT(11)         NOT NULL        AUTO_INCREMENT,
    description             VARCHAR(64)     NOT NULL,
    duration_in_seconds     INT(11)         DEFAULT 0,
  
    PRIMARY KEY (id)
);

INSERT INTO 
    privilege (code, description, category)
VALUES
    ('ADD_PREPAID_RATE', 'Agregar tarifa prepago', 'Tarifas'), 
    ('EDIT_PREPAID_RATE', 'Editar tarifa prepago', 'Tarifas'),
    ('VIEW_PREPAID_RATE', 'Ver tarifa prepago', 'Tarifas'),
    ('DELETE_PREPAID_RATE', 'Eliminar tarifa prepago', 'Tarifas');

-- -----------------------------------------------------------------------------
-- Tabla radius_log
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS radius_log;

CREATE TABLE radius_log (
    id                      INT(11)             NOT NULL        AUTO_INCREMENT,
    hotspot                 INT(11)             DEFAULT 0,
    user                    VARCHAR(64)         DEFAULT '',
    type                    VARCHAR(32)         DEFAULT '',
    message                 VARCHAR(5096)       DEFAULT '',
    called_station_id       VARCHAR(32)         DEFAULT '',
    calling_mac_address     VARCHAR(32)         DEFAULT '',
    date                    DATE                DEFAULT NULL,
    hour                    TIME                DEFAULT NULL,
    datetime                DATETIME            DEFAULT NULL,
  
    PRIMARY KEY (id)
);

CREATE INDEX radius_log_hotspot_idx ON radius_log(hotspot);
CREATE INDEX radius_log_date_idx ON radius_log(date);

INSERT INTO 
    privilege (code, description, category)
VALUES
    ('VIEW_HISTORY_REPORT', 'Ver Reporte Histórico', 'Reportes'), 
    ('VIEW_SOLD_PIN_X_SELLER_REPORT', 'Ver Reporte de Pines Vendidos por Vendedor', 'Reportes'),
    ('VIEW_SOLD_PIN_X_KIOSK_REPORT', 'Ver Reporte de Pines Vendidos por Kiosco', 'Reportes');

-- -----------------------------------------------------------------------------
-- Tabla seller
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS seller;

CREATE TABLE seller (
    login           VARCHAR(64)         NOT NULL,
    password        VARCHAR(128)        NOT NULL,
    salt            VARCHAR(16)         NOT NULL,
    name            VARCHAR(128)        NOT NULL,
    document_id     VARCHAR(32)         DEFAULT '',
    email           varchar(128)        DEFAULT '',
    phone           VARCHAR(32)         DEFAULT '',
    address         VARCHAR(128)        DEFAULT '',
    kiosk           INT(11)             NOT NULL,
    active          TINYINT(1)          DEFAULT 1,

    PRIMARY KEY (login)
);

INSERT INTO 
    privilege (code, description, category)
VALUES
    ('ADD_SELLER', 'Agregar vendedor/gestor de kiosco', 'Gestores'), 
    ('EDIT_SELLER', 'Editar vendedor/gestor de kiosco', 'Gestores'),
    ('VIEW_SELLER', 'Ver vendedor/gestor de kiosco', 'Gestores'),
    ('DELETE_SELLER', 'Eliminar vendedor/gestor de kiosco', 'Gestores');

-- -----------------------------------------------------------------------------
-- Tabla seller_auth_token
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS seller_auth_token;

CREATE TABLE seller_auth_token (
    seller              VARCHAR(64)     NOT NULL,
    token               VARCHAR(32)     NOT NULL,
    expiration_date     TIMESTAMP,

    PRIMARY KEY (seller)
);

-- -----------------------------------------------------------------------------
-- Tabla seller_stats
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS seller_stats;

CREATE TABLE seller_stats (
    id              INT(11)         NOT NULL        AUTO_INCREMENT,
    seller          VARCHAR(64)     NOT NULL,
    kiosk           INT(11)         NOT NULL,
    date            DATE            DEFAULT NULL,
    time            INT(11)         DEFAULT NULL,
    quantity        INT(11)         DEFAULT 0,

    PRIMARY KEY(id)
);
