create user if not exists sa password '123';
alter user sa admin true;
CREATE TABLE USER
(
  ID int AUTO_INCREMENT PRIMARY KEY NOT NULL,
  ACCOUNT_ID VARCHAR(100),
  NAME VARCHAR (50),
  TOKEN CHAR(36),
  GMT_CREATE BIGINT,
  GMT_MODIFY BIGINT
);