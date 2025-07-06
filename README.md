# Cafe Ordering System

**Leader:** Guisijan, Rizaleda Gabriela C.

**Members:**
1. Alinas, Lindzy Shakira
2. Alinas, Toni Shamiah
3. Morva, Nathaniel Carlo
4. Vista, Valentino

## 🏪 Project Overview

A comprehensive Java-based cafe ordering system designed to streamline operations for coffee shops. The system features a role-based architecture supporting three user types: Customers, Employees, and Managers, each with tailored interfaces and functionalities.

## 🛠️ Technology Stack

- **Frontend**: Java Swing (JFrame) GUI
- **Backend**: Java SE
- **Database**: MySQL
- **IDE**: NetBeans
- **Build Tool**: Apache Ant (build.xml)

## ✨ Key Features

### 👥 Multi-User Architecture
- **Customer Portal**: Browse menu, place orders, provide feedback
- **Employee Interface**: Manage menu items, process orders, update inventory
- **Manager Dashboard**: Employee management, system analytics, access control

### 🔐 Security Features
- Role-based authentication system
- Secure code verification for employees and managers
- Password-protected user accounts

### 📱 User Interface
- Modern Java Swing GUI with custom styling
- Responsive layout design
- Intuitive navigation between modules

### 🗄️ Database Integration
- MySQL database connectivity
- Real-time data synchronization
- Persistent storage for all transactions

## 📁 Project Structure

```
Cafe_Ordering_System/
├── src/
│   ├── Login.java              # Main login interface
│   ├── Signup.java            # User registration
│   ├── Code.java              # Role verification system
│   ├── CartItem.java          # Order item model
│   ├── Manager/
│   │   ├── Manager.java       # Manager dashboard
│   │   └── Manager.form       # Manager GUI layout
│   ├── Employee/
│   │   ├── Employee.java      # Employee interface
│   │   └── Employee.form      # Employee GUI layout
│   ├── Customer/
│   │   ├── Menu.java          # Customer menu interface
│   │   ├── Menu.form          # Menu GUI layout
│   │   ├── Feedback.java      # Customer feedback system
│   │   └── Feedback.form      # Feedback GUI layout
│   ├── Options/
│   │   ├── LoginOption.java   # Login type selection
│   │   ├── LoginOption.form   # Login option GUI
│   │   ├── SignupOption.java  # Signup type selection
│   │   └── SignupOption.form  # Signup option GUI
│   └── images/                # Application assets
├── nbproject/                 # NetBeans project files
├── build/                     # Compiled classes
├── build.xml                  # Build configuration
├── manifest.mf               # JAR manifest
└── README.md                 # Project documentation
```

## Description of the Proposed Software/System:
Café ordering system is used for maintaining the details, products, billing, and maintains an inventory for the Café. This project is developed using Java, MySQL, and JFrame. The project has 3 accounts: Cashier, Employee, and Manager.

## Objectives:
### General Objective:
The general objective is to design and develop a Café Ordering System to automate the work and make it easier for the manager and employees to manage the coffee shop.

### Specific Objectives: 
1. To design and develop a system that is easy to use.
2. To design and develop a system that simplifies performing tasks such as Managing Employee, Cashier details, Menu details and Placing Order.
3. To design and develop a system that provides convenience for cashier, employees, and owner himself.
4. To design and develop a system that makes managing menu easier.
5. To design and develop a system that makes it easier for the customers to order.
6. To design and develop a system that keeps track of the stocks of the Café easier.

## Scopes: 
1. The system will display the available menu for a customer.
2. The system will allow employees to add or remove new menu items and update its prices.
3. The system will allow the customers to rate the service of the program, product, and may provide feedback after they ordered.
4. The system will allow the manager to add, remove or view all the employees and update their salary.
5. The system will allow the manager to check the rating of the system, of a certain product, and feedbacks provided by the customers.

## Delimitations:
1. The system's menu is limited to pastries and hot serving drinks.
2. The system does not cover cash on deliveries services.
3. The system does not provide information about managing stocks and inventory to the customers.
4. The system is limited only to customers ordering online.

## 🚀 Quick Start

1. **Prerequisites**: Java 8+, MySQL Server, NetBeans IDE
2. **Database Setup**: Import the cafe database schema
3. **Configuration**: Update database connection settings
4. **Build**: Open project in NetBeans and build
5. **Run**: Execute the main application

For detailed setup instructions, see [INSTALLATION.md](INSTALLATION.md)

## 📚 Documentation

- [Installation Guide](INSTALLATION.md) - Complete setup instructions
- [User Guide](USER_GUIDE.md) - How to use the system
- [Developer Guide](DEVELOPER_GUIDE.md) - Technical documentation
- [Database Schema](DATABASE_SCHEMA.md) - Database structure and setup

## 🤝 Contributing

This project is part of an academic assignment. For contributions or questions, please contact the project team members listed above.

## 📄 License

This project is developed for educational purposes as part of coursework.
