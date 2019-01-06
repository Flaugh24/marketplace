CREATE TABLE usr_session (
  primary_id            CHAR(36) NOT NULL
    CONSTRAINT usr_session_pk
    PRIMARY KEY,
  session_id            CHAR(36) NOT NULL,
  creation_time         BIGINT   NOT NULL,
  last_access_time      BIGINT   NOT NULL,
  max_inactive_interval INTEGER  NOT NULL,
  expiry_time           BIGINT   NOT NULL,
  principal_name        VARCHAR(500) -- <= here was 100
);

CREATE UNIQUE INDEX usr_session_ix1
  ON usr_session (session_id);

CREATE INDEX usr_session_ix2
  ON usr_session (expiry_time);

CREATE INDEX usr_session_ix3
  ON usr_session (principal_name);


CREATE TABLE usr_session_attributes (
  session_primary_id CHAR(36)     NOT NULL
    CONSTRAINT usr_session_attributes_fk
    REFERENCES usr_session
    ON DELETE CASCADE,
  attribute_name     VARCHAR(200) NOT NULL,
  attribute_bytes    BYTEA        NOT NULL,
  CONSTRAINT usr_session_attributes_pk
  PRIMARY KEY (session_primary_id, attribute_name)
);
