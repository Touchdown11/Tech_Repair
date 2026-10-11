# TechRepair — Repair Shop & Inventory Management System

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.org/projects/jdk/21/)
[![Maven](https://img.shields.io/badge/Maven-4.0.0-C71A36.svg)](https://maven.apache.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-4479A1.svg)](https://www.mysql.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

**TechRepair** is a desktop-based Repair Shop and Inventory Management System built with **Java Swing**, **Apache Maven**, and **MySQL**. It streamlines day-to-day operations for a tech repair business, including managing customers, employees, suppliers, stock inventory, customer repair jobs, automated email notifications, and financial/order reporting.

---

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Architecture](#project-architecture)
- [Prerequisites](#prerequisites)
- [Database Setup](#database-setup)
- [Installation & Running](#installation--running)
- [Default Login Credentials](#default-login-credentials)
- [Email Notification Configuration](#email-notification-configuration)
- [License & Copyright](#license--copyright)

---

## Features

- **User Authentication**: Secure login screen backed by MySQL user verification (`User` table) along with a built-in administrator account.
- **Customer Management**: Add, update, and remove customer records (`Id`, `Name`, `Contact`, `Email`).
- **Employee Management**: Add, update, and delete employee profiles and role assignments (`Id`, `Name`, `Duty`, `Email`).
- **Supplier Management**: Maintain supplier details and stock supply records (`Id`, `Name`, `Contact`, `Quantity`, `Price`, `Address`).
- **Inventory View**: Real-time tabular view of stock items, quantities, and pricing sourced from supplier records.
- **Customer Repair Jobs**: Create and manage repair jobs/orders linking customers and assigned employees (`Id`, `CustomerId`, `EmployeeId`, `Problem`, `Price`).
- **Email Notifications**: Send automated or custom SMTP emails to customers regarding repair job status and order updates using JavaMail.
- **Monthly Reports**: Generate and view order/job summary reports (`Order Id`, `Customer`, `Price`) in a tabular interface.

---

## Tech Stack

| Component | Technology | Version |
| :--- | :--- | :--- |
| **Language** | Java (JDK) | 21 |
| **GUI Framework** | Java Swing (IntelliJ GUI Designer `.form`) | Standard JDK |
| **Build Tool** | Apache Maven | 4.0.0 (`pom.xml`) |
| **Database** | MySQL (`mysql-connector-java`) | 8.0.33 / 8.2.0 |
| **Boilerplate Reduction** | Project Lombok | 1.18.30 |
| **Email Service** | JavaMail API (`com.sun.mail:javax.mail`) | 1.6.2 |
| **Testing** | JUnit Jupiter API | 5.9.1 |

---

## Project Architecture

The application follows a layered **MVC / DTO** structure:

```text
Tech_Repair/
├── pom.xml                                  # Maven project configuration and dependencies
├── TechRepair.iml                           # IntelliJ IDEA module file
├── mysql-connector-j-8.2.0.jar              # Bundled MySQL JDBC connector
└── src/
    └── main/
        ├── resources/
        │   └── Image/                       # UI icons and image assets
        └── techrepair/
            ├── Controller/                  # Swing UI frame controllers and event handlers
            │   ├── LoginForm.java           # Application entry point & login screen
            │   ├── Dashboard.java           # Main navigation dashboard
            │   ├── CustomerUI.java          # Customer management hub (Add/Update/Delete)
            │   ├── EmployeeUI.java          # Employee management hub (Add/Update/Delete)
            │   ├── SuppliresUI.java         # Supplier management hub (Add/Update/Delete)
            │   ├── InventoryView.java       # Inventory table view
            │   ├── CustomerJobs.java        # Repair job creation, deletion & notifications
            │   ├── RepoartGenarate.java     # Order/job report viewer
            │   └── emailSend.java           # Direct customer email sender UI
            ├── DBLayer/
            │   └── DbConnection.java        # Singleton JDBC connection manager
            ├── DTO/                         # Data Transfer Objects (Lombok-enabled)
            │   ├── CustomerDto.java
            │   ├── CustomerJobDto.java
            │   ├── EmployeesDto.java
            │   ├── InventoryViewDto.java
            │   ├── OrderDto.java
            │   ├── SuppliersDto.java
            │   └── UserDto.java
            ├── Model/                       # Data Access / SQL execution layer
            │   ├── CustomerModle.java
            │   ├── CustomerJobModel.java
            │   ├── EmployeeModle.java
            │   ├── InventoryViewModel.java
            │   ├── SuppliresModle.java
            │   └── UserModle.java
            ├── Utils/                       # Utility services
            │   ├── CustomerNotification.java
            │   └── SendEmail.java           # SMTP email sender
            └── View/                        # IntelliJ IDEA Swing GUI Designer (.form) files
```

---

## Prerequisites

Before running the project, ensure you have the following installed:

1. **Java Development Kit (JDK) 21** or higher
2. **MySQL Server 8.0+** running on `127.0.0.1:3306`
3. **Apache Maven** (or bundled Maven inside **IntelliJ IDEA**)
4. **IntelliJ IDEA** (recommended, as the UI forms use IntelliJ's Swing GUI Designer `.form` bindings and the Lombok plugin)

---

## Database Setup

1. Start your local **MySQL Server**.
2. Run the following SQL script to create the `tech_repair` database and required tables:

```sql
CREATE DATABASE IF NOT EXISTS tech_repair;
USE tech_repair;

-- Users table for authentication
CREATE TABLE IF NOT EXISTS `User` (
    UserName VARCHAR(50) PRIMARY KEY,
    Password VARCHAR(100) NOT NULL
);

-- Customers table
CREATE TABLE IF NOT EXISTS Customer (
    Id VARCHAR(20) PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Contact VARCHAR(30),
    Email VARCHAR(100)
);

-- Employees table
CREATE TABLE IF NOT EXISTS Employee (
    Id VARCHAR(20) PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Duty VARCHAR(100),
    Email VARCHAR(100)
);

-- Suppliers / Inventory table
CREATE TABLE IF NOT EXISTS Supplier (
    Id VARCHAR(20) PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Contact VARCHAR(30),
    Quantity VARCHAR(50),
    Price INT,
    Address VARCHAR(255)
);

-- Customer Repair Jobs table
CREATE TABLE IF NOT EXISTS `Job` (
    Id VARCHAR(20) PRIMARY KEY,
    CustomerId VARCHAR(20),
    EmployeeId VARCHAR(20),
    Problem VARCHAR(255),
    Price DOUBLE,
    FOREIGN KEY (CustomerId) REFERENCES Customer(Id) ON DELETE SET NULL,
    FOREIGN KEY (EmployeeId) REFERENCES Employee(Id) ON DELETE SET NULL
);
```

3. **Database Connection Settings**:
   By default, `src/main/techrepair/DBLayer/DbConnection.java` connects using:
   - **URL**: `jdbc:mysql://127.0.0.1:3306/tech_repair`
   - **Username**: `root`
   - **Password**: `""` (empty password)

   If your local MySQL instance uses a different username or password, update `DbConnection.java` accordingly.

---

## Installation & Running

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Touchdown11/Tech_Repair.git
   cd Tech_Repair
   ```

2. **Open in IntelliJ IDEA**:
   - Open the `Tech_Repair` directory as a Maven project in IntelliJ IDEA.
   - Ensure annotation processing is enabled for **Lombok** (*Settings → Build, Execution, Deployment → Compiler → Annotation Processors → Enable annotation processing*).
   - Mark `src/main/techrepair` as the **Sources Root** if not already configured via `TechRepair.iml`.

3. **Run the Application**:
   - Locate `src/main/techrepair/Controller/LoginForm.java`.
   - Run `LoginForm.main()` to launch the Login window.

---

## Default Login Credentials

You can sign in using a user record from the MySQL `User` table, or use the built-in administrator credentials configured in `LoginForm.java`:

- **Username**: `admin`
- **Password**: `password123`

---

## Email Notification Configuration

SMTP email settings are located in `src/main/techrepair/Utils/SendEmail.java` and `src/main/techrepair/Utils/CustomerNotification.java`. To use your own mail server:

1. Open `src/main/techrepair/Utils/SendEmail.java`.
2. Update `host`, `port`, `username`, and `password` with your SMTP provider's credentials.

---

## License & Copyright

Copyright (c) 2026 **Touchdown11**.

This project is licensed under the **MIT License** — all copies or substantial portions of this software must retain the copyright notice and attribution to the original author. See the [LICENSE](LICENSE) file for full details.
