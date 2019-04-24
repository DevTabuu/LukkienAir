CREATE TABLE IF NOT EXISTS routes (
    id              BIGINT  PRIMARY KEY AUTO_INCREMENT,
    duration        INTEGER NOT NULL,
    origin_id       BIGINT  NOT NULL,
    destination_id  BIGINT  NOT NULL
);

CREATE TABLE IF NOT EXISTS flights (
    id              BIGINT      PRIMARY KEY AUTO_INCREMENT,
    cost            FLOAT       NOT NULL,
    departure_time  TIMESTAMP   NOT NULL,
    route_id        BIGINT      NOT NULL
);

CREATE TABLE IF NOT EXISTS destinations (
    id      BIGINT      PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(50) NOT NULL
);