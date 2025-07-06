# Installation Guide

This guide will help you set up and run the Cafe Ordering System on your local machine.

## 📋 Prerequisites

Before installing the Cafe Ordering System, ensure you have the following software installed:

### Required Software
- **Java Development Kit (JDK) 8 or higher**
  - Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
  - Verify installation: `java -version` and `javac -version`

- **MySQL Server 5.7 or higher**
  - Download from [MySQL Official Website](https://dev.mysql.com/downloads/mysql/)
  - Alternative: [XAMPP](https://www.apachefriends.org/) (includes MySQL)

- **NetBeans IDE (Recommended)**
  - Download from [Apache NetBeans](https://netbeans.apache.org/)
  - Alternative: Any Java IDE (Eclipse, IntelliJ IDEA)

### Optional Tools
- **MySQL Workbench** - For database management
- **Git** - For version control

## 🗄️ Database Setup

### Step 1: Start MySQL Server
```bash
# On Windows (if using XAMPP)
Start XAMPP Control Panel and start MySQL

# On Linux/Mac
sudo systemctl start mysql
# or
mysql.server start
```

### Step 2: Create Database
1. Open MySQL command line or MySQL Workbench
2. Execute the following commands:

```sql
-- Create the database
CREATE DATABASE cafe;

-- Use the database
USE cafe;

-- Create customers table
CREATE TABLE customers (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL
);

-- Create employees table
CREATE TABLE employees (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    salary VARCHAR(20),
    status VARCHAR(10)
);

-- Create managers table
CREATE TABLE managers (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL
);

-- Create products table
CREATE TABLE addedproducts (
    name VARCHAR(100) PRIMARY KEY,
    price VARCHAR(20) NOT NULL,
    stock INT DEFAULT 0,
    image VARCHAR(255)
);

-- Create removed products table
CREATE TABLE removedproducts (
    name VARCHAR(100) PRIMARY KEY
);

-- Create feedbacks table
CREATE TABLE feedbacks (
    service_rating INT,
    product_name VARCHAR(100),
    product_rating INT,
    comments TEXT,
    customer_username VARCHAR(50)
);

-- Create code table for role verification
CREATE TABLE code (
    manager VARCHAR(20),
    employee VARCHAR(20)
);

-- Insert default verification codes
INSERT INTO code (manager, employee) VALUES ('MANAGER123', 'EMP123');

-- Insert sample data (optional)
INSERT INTO managers (username, password) VALUES 
('admin', 'admin123'),
('manager1', 'pass123');

INSERT INTO employees (username, password, salary, status) VALUES 
('emp1', 'emp123', '25000', '1'),
('emp2', 'emp456', '23000', '1');

INSERT INTO customers (username, password) VALUES 
('customer1', 'cust123'),
('customer2', 'cust456');

-- Sample menu items
INSERT INTO addedproducts (name, price, stock, image) VALUES 
('Coffee', '150', 50, 'coffee.png'),
('Tea', '100', 30, 'tea.png'),
('Sandwich', '200', 25, 'sandwich.png'),
('Pastry', '120', 40, 'pastry.png');
```

### Step 3: Verify Database Setup
```sql
-- Check if all tables are created
SHOW TABLES;

-- Verify data
SELECT * FROM code;
SELECT * FROM managers;
SELECT * FROM employees;
```

## 🚀 Application Setup

### Step 1: Download/Clone the Project
```bash
# If using Git
git clone <repository-url>
cd Cafe_Ordering_System

# Or download and extract the ZIP file
```

### Step 2: Configure Database Connection
1. Open the project in NetBeans IDE
2. Navigate to each Java file that contains database connections
3. Update the connection parameters if needed:

```java
// Default configuration (update if your setup is different)
String url = "jdbc:mysql://localhost:3306/cafe";
String username = "root";
String password = ""; // Enter your MySQL root password
```

**Files to check:**
- `src/Code.java`
- `src/Login.java`
- `src/Signup.java`
- `src/Manager/Manager.java`
- `src/Employee/Employee.java`
- `src/Customer/Menu.java`
- `src/Customer/Feedback.java`

### Step 3: Add MySQL Connector
1. Download MySQL Connector/J from [MySQL Official Site](https://dev.mysql.com/downloads/connector/j/)
2. In NetBeans:
   - Right-click on your project
   - Select "Properties"
   - Go to "Libraries"
   - Click "Add JAR/Folder"
   - Browse and select the MySQL Connector JAR file

### Step 4: Build the Project
1. In NetBeans:
   - Right-click on the project
   - Select "Clean and Build"
2. Or use command line:
```bash
ant clean
ant compile
ant jar
```

### Step 5: Run the Application
1. In NetBeans:
   - Right-click on the project
   - Select "Run"
2. Or use command line:
```bash
java -cp "dist/Cafe_Ordering_System.jar:lib/mysql-connector-java-*.jar" Code
```

## 🔧 Troubleshooting

### Common Issues

#### Database Connection Failed
**Error:** `SQLException: Access denied for user 'root'@'localhost'`

**Solution:**
1. Check MySQL is running
2. Verify username/password in connection strings
3. Grant privileges to the user:
```sql
GRANT ALL PRIVILEGES ON cafe.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
```

#### MySQL Driver Not Found
**Error:** `ClassNotFoundException: com.mysql.cj.jdbc.Driver`

**Solution:**
1. Ensure MySQL Connector/J is added to project libraries
2. Check the JAR file is in the classpath

#### Port Already in Use
**Error:** MySQL cannot start on port 3306

**Solution:**
1. Change MySQL port in my.cnf/my.ini
2. Update connection URL in Java files:
```java
String url = "jdbc:mysql://localhost:3307/cafe"; // Use new port
```

#### NetBeans Project Issues
**Solution:**
1. Delete `nbproject/private` folder
2. Clean and rebuild project
3. Refresh project in IDE

## 🔐 Default Login Credentials

### Manager Access
- **Username:** admin
- **Password:** admin123
- **Manager Code:** MANAGER123

### Employee Access
- **Username:** emp1
- **Password:** emp123
- **Employee Code:** EMP123

### Customer Access
- **Username:** customer1
- **Password:** cust123

## 🔄 Updates and Maintenance

### Updating Database Schema
If you need to modify the database structure:

1. Create backup:
```sql
mysqldump -u root -p cafe > cafe_backup.sql
```

2. Apply changes to the database
3. Update corresponding Java model classes

### Adding New Dependencies
1. Place JAR files in `lib/` directory
2. Update `build.xml` if necessary
3. Add to NetBeans project libraries

## 📞 Support

If you encounter any issues during installation:

1. Check the troubleshooting section above
2. Verify all prerequisites are installed correctly
3. Contact the development team members listed in README.md

## 🎯 Next Steps

After successful installation:
1. Read the [User Guide](USER_GUIDE.md) to understand system features
2. Check [Developer Guide](DEVELOPER_GUIDE.md) for customization
3. Review [Database Schema](DATABASE_SCHEMA.md) for data structure details