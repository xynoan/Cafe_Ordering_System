# Database Schema Documentation

This document describes the database structure for the Cafe Ordering System.

## 📊 Database Overview

**Database Name:** `cafe`  
**Database Engine:** MySQL 5.7+  
**Character Set:** UTF-8  
**Collation:** utf8_general_ci  

## 🗂️ Table Structure

### 1. customers
Stores customer account information.

```sql
CREATE TABLE customers (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL
);
```

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| username | VARCHAR(50) | PRIMARY KEY | Unique customer identifier |
| password | VARCHAR(100) | NOT NULL | Customer password (consider hashing) |

**Sample Data:**
```sql
INSERT INTO customers (username, password) VALUES 
('customer1', 'cust123'),
('customer2', 'cust456'),
('john_doe', 'password123');
```

### 2. employees
Stores employee account and employment information.

```sql
CREATE TABLE employees (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    salary VARCHAR(20),
    status VARCHAR(10)
);
```

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| username | VARCHAR(50) | PRIMARY KEY | Unique employee identifier |
| password | VARCHAR(100) | NOT NULL | Employee password |
| salary | VARCHAR(20) | NULL | Employee salary amount |
| status | VARCHAR(10) | NULL | Employment status (1=Active, 0=Inactive) |

**Sample Data:**
```sql
INSERT INTO employees (username, password, salary, status) VALUES 
('emp1', 'emp123', '25000', '1'),
('emp2', 'emp456', '23000', '1'),
('emp3', 'emp789', '27000', '0');
```

### 3. managers
Stores manager account information.

```sql
CREATE TABLE managers (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL
);
```

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| username | VARCHAR(50) | PRIMARY KEY | Unique manager identifier |
| password | VARCHAR(100) | NOT NULL | Manager password |

**Sample Data:**
```sql
INSERT INTO managers (username, password) VALUES 
('admin', 'admin123'),
('manager1', 'pass123'),
('supervisor', 'super456');
```

### 4. addedproducts
Stores available products in the cafe menu.

```sql
CREATE TABLE addedproducts (
    name VARCHAR(100) PRIMARY KEY,
    price VARCHAR(20) NOT NULL,
    stock INT DEFAULT 0,
    image VARCHAR(255)
);
```

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| name | VARCHAR(100) | PRIMARY KEY | Unique product name |
| price | VARCHAR(20) | NOT NULL | Product price |
| stock | INT | DEFAULT 0 | Available quantity |
| image | VARCHAR(255) | NULL | Product image file path |

**Sample Data:**
```sql
INSERT INTO addedproducts (name, price, stock, image) VALUES 
('Espresso', '120', 50, 'espresso.png'),
('Cappuccino', '150', 30, 'cappuccino.png'),
('Latte', '160', 25, 'latte.png'),
('Black Coffee', '100', 40, 'black_coffee.png'),
('Green Tea', '80', 35, 'green_tea.png'),
('Croissant', '200', 20, 'croissant.png'),
('Muffin', '180', 15, 'muffin.png'),
('Sandwich', '250', 10, 'sandwich.png');
```

### 5. removedproducts
Tracks products that have been removed from the menu.

```sql
CREATE TABLE removedproducts (
    name VARCHAR(100) PRIMARY KEY
);
```

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| name | VARCHAR(100) | PRIMARY KEY | Name of removed product |

**Sample Data:**
```sql
INSERT INTO removedproducts (name) VALUES 
('Old Coffee Blend'),
('Discontinued Pastry');
```

### 6. feedbacks
Stores customer feedback and ratings.

```sql
CREATE TABLE feedbacks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    service_rating INT,
    product_name VARCHAR(100),
    product_rating INT,
    comments TEXT,
    customer_username VARCHAR(50),
    feedback_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | INT | AUTO_INCREMENT PRIMARY KEY | Unique feedback ID |
| service_rating | INT | NULL | Service rating (1-5) |
| product_name | VARCHAR(100) | NULL | Product being reviewed |
| product_rating | INT | NULL | Product rating (1-5) |
| comments | TEXT | NULL | Customer comments |
| customer_username | VARCHAR(50) | NULL | Customer who gave feedback |
| feedback_date | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | When feedback was given |

**Sample Data:**
```sql
INSERT INTO feedbacks (service_rating, product_name, product_rating, comments, customer_username) VALUES 
(5, 'Espresso', 4, 'Great coffee, quick service!', 'customer1'),
(4, 'Cappuccino', 5, 'Perfect foam, loved it!', 'customer2'),
(3, 'Sandwich', 3, 'Good but could be warmer', 'john_doe');
```

### 7. code
Stores verification codes for employee and manager access.

```sql
CREATE TABLE code (
    id INT AUTO_INCREMENT PRIMARY KEY,
    manager VARCHAR(20),
    employee VARCHAR(20),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | INT | AUTO_INCREMENT PRIMARY KEY | Unique record ID |
| manager | VARCHAR(20) | NULL | Manager verification code |
| employee | VARCHAR(20) | NULL | Employee verification code |
| created_date | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | Code creation date |

**Sample Data:**
```sql
INSERT INTO code (manager, employee) VALUES 
('MANAGER123', 'EMP123');
```

## 🔗 Relationships

### Entity Relationship Diagram
```
customers (1) ←→ (0..n) feedbacks
    ↓
feedbacks (n) ←→ (1) addedproducts

employees (managed by) managers
    ↓
addedproducts (managed by) employees
    ↓
removedproducts (tracked from) addedproducts
```

### Foreign Key Relationships
```sql
-- Add foreign key constraints for data integrity
ALTER TABLE feedbacks 
ADD CONSTRAINT fk_feedback_customer 
FOREIGN KEY (customer_username) REFERENCES customers(username) 
ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE feedbacks 
ADD CONSTRAINT fk_feedback_product 
FOREIGN KEY (product_name) REFERENCES addedproducts(name) 
ON DELETE SET NULL ON UPDATE CASCADE;
```

## 📊 Indexes

### Recommended Indexes for Performance
```sql
-- Index on frequently queried columns
CREATE INDEX idx_employee_status ON employees(status);
CREATE INDEX idx_product_stock ON addedproducts(stock);
CREATE INDEX idx_feedback_rating ON feedbacks(service_rating);
CREATE INDEX idx_feedback_date ON feedbacks(feedback_date);
```

## 🔧 Database Setup Scripts

### Complete Database Setup
```sql
-- Create database
CREATE DATABASE IF NOT EXISTS cafe CHARACTER SET utf8 COLLATE utf8_general_ci;
USE cafe;

-- Create all tables
CREATE TABLE customers (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE employees (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    salary VARCHAR(20),
    status VARCHAR(10)
);

CREATE TABLE managers (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE addedproducts (
    name VARCHAR(100) PRIMARY KEY,
    price VARCHAR(20) NOT NULL,
    stock INT DEFAULT 0,
    image VARCHAR(255)
);

CREATE TABLE removedproducts (
    name VARCHAR(100) PRIMARY KEY
);

CREATE TABLE feedbacks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    service_rating INT,
    product_name VARCHAR(100),
    product_rating INT,
    comments TEXT,
    customer_username VARCHAR(50),
    feedback_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE code (
    id INT AUTO_INCREMENT PRIMARY KEY,
    manager VARCHAR(20),
    employee VARCHAR(20),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Add foreign key constraints
ALTER TABLE feedbacks 
ADD CONSTRAINT fk_feedback_customer 
FOREIGN KEY (customer_username) REFERENCES customers(username) 
ON DELETE SET NULL ON UPDATE CASCADE;

-- Add indexes
CREATE INDEX idx_employee_status ON employees(status);
CREATE INDEX idx_product_stock ON addedproducts(stock);
CREATE INDEX idx_feedback_rating ON feedbacks(service_rating);
CREATE INDEX idx_feedback_date ON feedbacks(feedback_date);
```

### Sample Data Population
```sql
-- Insert default verification codes
INSERT INTO code (manager, employee) VALUES ('MANAGER123', 'EMP123');

-- Insert sample managers
INSERT INTO managers (username, password) VALUES 
('admin', 'admin123'),
('manager1', 'pass123');

-- Insert sample employees
INSERT INTO employees (username, password, salary, status) VALUES 
('emp1', 'emp123', '25000', '1'),
('emp2', 'emp456', '23000', '1');

-- Insert sample customers
INSERT INTO customers (username, password) VALUES 
('customer1', 'cust123'),
('customer2', 'cust456');

-- Insert sample products
INSERT INTO addedproducts (name, price, stock, image) VALUES 
('Espresso', '120', 50, 'espresso.png'),
('Cappuccino', '150', 30, 'cappuccino.png'),
('Latte', '160', 25, 'latte.png'),
('Black Coffee', '100', 40, 'black_coffee.png'),
('Green Tea', '80', 35, 'green_tea.png'),
('Croissant', '200', 20, 'croissant.png'),
('Muffin', '180', 15, 'muffin.png'),
('Sandwich', '250', 10, 'sandwich.png');
```

## 🔄 Maintenance Scripts

### Database Backup
```bash
# Create backup
mysqldump -u root -p cafe > cafe_backup_$(date +%Y%m%d_%H%M%S).sql

# Restore from backup
mysql -u root -p cafe < cafe_backup_20231201_120000.sql
```

### Performance Monitoring
```sql
-- Check table sizes
SELECT 
    table_name AS 'Table',
    round(((data_length + index_length) / 1024 / 1024), 2) AS 'Size (MB)'
FROM information_schema.TABLES 
WHERE table_schema = 'cafe';

-- Check slow queries
SHOW PROCESSLIST;

-- Analyze table performance
ANALYZE TABLE customers, employees, managers, addedproducts, feedbacks;
```

### Data Cleanup
```sql
-- Clean old feedback data (older than 1 year)
DELETE FROM feedbacks 
WHERE feedback_date < DATE_SUB(NOW(), INTERVAL 1 YEAR);

-- Remove inactive employees (optional)
-- DELETE FROM employees WHERE status = '0';

-- Clean removed products that are no longer needed
-- DELETE FROM removedproducts WHERE created_date < DATE_SUB(NOW(), INTERVAL 6 MONTH);
```

## 🔐 Security Considerations

### User Privileges
```sql
-- Create dedicated database user
CREATE USER 'cafe_app'@'localhost' IDENTIFIED BY 'secure_password';

-- Grant minimal necessary privileges
GRANT SELECT, INSERT, UPDATE, DELETE ON cafe.* TO 'cafe_app'@'localhost';

-- Revoke unnecessary privileges
REVOKE DROP, ALTER, CREATE ON cafe.* FROM 'cafe_app'@'localhost';

FLUSH PRIVILEGES;
```

### Password Security
```sql
-- Consider adding password hashing
ALTER TABLE customers ADD COLUMN password_hash VARCHAR(255);
ALTER TABLE employees ADD COLUMN password_hash VARCHAR(255);
ALTER TABLE managers ADD COLUMN password_hash VARCHAR(255);

-- Add password salt for additional security
ALTER TABLE customers ADD COLUMN salt VARCHAR(32);
ALTER TABLE employees ADD COLUMN salt VARCHAR(32);
ALTER TABLE managers ADD COLUMN salt VARCHAR(32);
```

## 📈 Performance Optimization

### Query Optimization
```sql
-- Optimize frequent queries
EXPLAIN SELECT * FROM addedproducts WHERE stock > 0;
EXPLAIN SELECT * FROM feedbacks WHERE customer_username = 'customer1';

-- Add covering indexes for common queries
CREATE INDEX idx_product_price_stock ON addedproducts(price, stock);
CREATE INDEX idx_feedback_customer_date ON feedbacks(customer_username, feedback_date);
```

### Table Partitioning (for large datasets)
```sql
-- Partition feedbacks table by date (if needed for large datasets)
ALTER TABLE feedbacks 
PARTITION BY RANGE (YEAR(feedback_date)) (
    PARTITION p2023 VALUES LESS THAN (2024),
    PARTITION p2024 VALUES LESS THAN (2025),
    PARTITION p_future VALUES LESS THAN MAXVALUE
);
```

## 🐛 Troubleshooting

### Common Issues

#### Connection Problems
```sql
-- Check MySQL service status
SHOW VARIABLES LIKE 'port';
SHOW VARIABLES LIKE 'socket';

-- Check user permissions
SHOW GRANTS FOR 'root'@'localhost';
```

#### Data Integrity Issues
```sql
-- Check for orphaned records
SELECT f.* FROM feedbacks f 
LEFT JOIN customers c ON f.customer_username = c.username 
WHERE c.username IS NULL;

-- Check for duplicate entries
SELECT username, COUNT(*) FROM customers GROUP BY username HAVING COUNT(*) > 1;
```

#### Performance Issues
```sql
-- Check table locks
SHOW OPEN TABLES WHERE In_use > 0;

-- Check running queries
SHOW FULL PROCESSLIST;
```

## 📋 Migration Scripts

### Version Upgrades
```sql
-- Example migration script for adding new features
-- Migration v1.1: Add timestamps to all tables

-- Add created_at and updated_at to customers
ALTER TABLE customers 
ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

-- Add created_at and updated_at to employees
ALTER TABLE employees 
ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

-- Update version info
INSERT INTO code (manager, employee) VALUES ('v1.1', 'migration_complete');
```

This database schema provides a solid foundation for the Cafe Ordering System with room for future enhancements and optimizations.