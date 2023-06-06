
-- This script sets up a database and creates two tables to store user account's information and their transaction.
DROP DATABASE IF EXISTS financial_fit_tracker_db;

CREATE DATABASE financial_fit_tracker_db;
USE financial_fit_tracker_db;

-- create user table structure
CREATE TABLE IF NOT EXISTS users (
  id                 INT PRIMARY KEY  AUTO_INCREMENT,
  username           VARCHAR(20) NOT NULL,
  email              VARCHAR(50) NOT NULL,
  password           VARCHAR(50) NOT NULL
) ;

-- create transaction table structure
CREATE TABLE IF NOT EXISTS transactions (
  id                 INT PRIMARY KEY  AUTO_INCREMENT,
  date               DATE NOT NULL,
  description        VARCHAR(255) NOT NULL,
  amount             INT NOT NULL,
  balance            INT NOT NULL,
  user_id            INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id)
) 