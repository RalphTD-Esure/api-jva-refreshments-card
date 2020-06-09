DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS ACCOUNTS;
DROP TABLE IF EXISTS TOP_UPS;
DROP TABLE IF EXISTS PURCHASES;
DROP TABLE IF EXISTS STOCK;

CREATE TABLE USERS (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  employee_id BIGINT,
  username VARCHAR(16),
  account_id BIGINT,
  first_name VARCHAR(250),
  last_name VARCHAR(250),
  email VARCHAR(250),
  password VARCHAR(250),
  creation_date TIMESTAMP,
  credentials_non_expired BOOLEAN
);

CREATE TABLE ACCOUNTS (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT,
  username VARCHAR(250),
  balance DECIMAL,
  creation_date TIMESTAMP,
  is_active BOOLEAN
);

CREATE TABLE TOP_UPS (
  top_up_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  account_id BIGINT NOT NULL,
  amount DECIMAL,
  top_up_date DATE
);

CREATE TABLE PURCHASES (
  purchase_id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  account_id BIGINT NOT NULL,
  price DECIMAL,
  itemCode VARCHAR(250),
  purchase_date DATE
);

CREATE TABLE STOCK (
    item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(250),
    item_price DECIMAL,
    quantity INT
);

INSERT INTO STOCK (item_name, item_price, quantity)
VALUES ('crisps', 1.00, 25),
       ('sandwich', 3.00, 10);

