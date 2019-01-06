DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
COMMENT ON SCHEMA public IS 'standard public schema';

-- create sequence hibernate_sequence;

CREATE TABLE product
(
  id    SERIAL8,
  name  VARCHAR          NOT NULL,
  price DOUBLE PRECISION NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE usr
(
  id              VARCHAR   NOT NULL,
  name            VARCHAR   NOT NULL,
  surname         VARCHAR   NOT NULL,
  email           VARCHAR   NOT NULL,
  gender          VARCHAR,
  userpic         VARCHAR,
  locale          VARCHAR,
  last_visit_date TIMESTAMP NOT NULL,
  create_date     TIMESTAMP NOT NULL,
  PRIMARY KEY (id)
)