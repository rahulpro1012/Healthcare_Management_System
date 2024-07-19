# Healthcare Management System

## Overview

The Healthcare Management System is a console-based Java application that allows users to manage patients, doctors, and appointments. It uses Core Java, MySQL, and JDBC for database interactions.

## Features

### Patient Management
- Register a new patient
- View patient details
- Update patient information
- Delete a patient

### Doctor Management
- Add a new doctor
- View doctor details
- Update doctor information
- Delete a doctor

### Appointment Management
- Schedule a new appointment
- View appointment details
- Update appointment information
- Cancel an appointment

## Database Schema

### Patient Table
- `patient_id` (Primary Key)
- `name`
- `date_of_birth`
- `gender`
- `address`

### Doctor Table
- `doctor_id` (Primary Key)
- `name`
- `specialization`
- `contact_number`
- `email`

### Appointment Table
- `appointment_id` (Primary Key)
- `patient_id` (Foreign Key references Patient Table)
- `doctor_id` (Foreign Key references Doctor Table)
- `appointment_date`
- `appointment_time`
- `status` (scheduled/cancelled)

## Requirements

- Java Development Kit (JDK) 17
- MySQL Database
- MySQL JDBC Driver

## Setup

### MySQL Database

1. Install MySQL on your system if it's not already installed.
2. Open the MySQL command line client or any MySQL GUI tool (e.g., MySQL Workbench).
3. Create the `healthcare` database and tables by running the following SQL script:

```sql
CREATE DATABASE healthcare;

USE healthcare;

CREATE TABLE Patient (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender VARCHAR(10) NOT NULL,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE Doctor (
    doctor_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    contact_number VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE Appointment (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT,
    doctor_id INT,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES Patient(patient_id),
    FOREIGN KEY (doctor_id) REFERENCES Doctor(doctor_id)
);
```

## Download MySQL JDBC Driver
Download the MySQL JDBC driver from the official MySQL website. You can download it <a href="https://dev.mysql.com/downloads/connector/j/">here.</a>

## Java Application
Ensure you have the Java Development Kit (JDK) installed. You can download it from <a href="https://www.oracle.com/in/java/technologies/downloads/">here.</a>

Add the MySQL JDBC driver to your project's classpath:

If you're using an IDE like IntelliJ IDEA or Eclipse, you can add the JDBC driver jar file to the project's libraries.
<ul><li>For IntelliJ IDEA: Go to File -> Project Structure -> Libraries and add the JDBC driver jar file.</li>
<li>For Eclipse: Right-click on the project -> Build Path -> Configure Build Path -> Add External JARs and select the JDBC driver jar file.</li></ul>

## Sample Screenshot of the application

<img src="https://github.com/rahulpro1012/Healthcare_Management_System/blob/main/image.png"></img>

