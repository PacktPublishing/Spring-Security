DROP SCHEMA IF EXISTS `sec_dev`;
CREATE SCHEMA `sec_dev`;

CREATE TABLE sec_dev.users(
   username varchar(50) not null primary key,
   password varchar(50) not null,
   enabled boolean not null);
CREATE TABLE sec_dev.authorities (
  username varchar(50) not null,
  authority varchar(50) not null,
  constraint fk_authorities_users foreign key(username) references users(username));
  create unique index ix_auth_username on sec_dev.authorities (username,authority);


INSERT INTO sec_dev.users (username, password, enabled)
VALUES
  ('user1', 'user1Pass', TRUE),
  ('admin1', 'admin1Pass', TRUE),
  ('admin2', 'admin2Pass', TRUE);

INSERT INTO sec_dev.authorities (username, authority)
VALUES
 ('user1', 'ROLE_USER'),
 ('admin1', 'ROLE_ADMIN'),
 ('admin2', 'ROLE_ADMIN');