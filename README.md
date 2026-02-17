# JDBC Project

## Overview

This project demonstrates a basic Java JDBC application
using DAO pattern to connect with MySQL database.

## Technologies Used

- Java
- JDBC
- MySQL

## Features

- Secure DB connection using environment variables
- DAO pattern
- PreparedStatement usage
- Basic CRUD operations

## How to Run

1. Set environment variable:

   setx DB_PASSWORD "your_password"

2. Compile:

   javac -cp ".;lib/mysql-connector-j-9.5.0.jar" src/\*.java

3. Run:

   java -cp ".;lib/mysql-connector-j-9.5.0.jar;src" Main
