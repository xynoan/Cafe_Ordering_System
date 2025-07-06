# Developer Guide

This guide provides technical documentation for developers working on the Cafe Ordering System.

## 🏗️ Architecture Overview

### System Architecture
The Cafe Ordering System follows a **3-tier architecture**:

```
┌─────────────────────────────────────┐
│           Presentation Layer        │
│        (Java Swing GUI)            │
├─────────────────────────────────────┤
│           Business Logic Layer      │
│        (Java Application Logic)    │
├─────────────────────────────────────┤
│           Data Access Layer         │
│          (MySQL Database)          │
└─────────────────────────────────────┘
```

### Design Patterns Used
- **MVC Pattern**: Separation of GUI forms (.form) and logic (.java)
- **Factory Pattern**: User role-based object creation
- **Singleton Pattern**: Database connection management
- **Observer Pattern**: Real-time data updates

## 📁 Code Structure

### Main Application Flow
```
Application Start
    ↓
Login/Signup Options
    ↓
Role Verification (Code.java)
    ↓
Role-Based Dashboard
    ├── Customer → Menu.java
    ├── Employee → Employee.java
    └── Manager → Manager.java
```

### Key Classes

#### Core Classes
- **`Code.java`**: Role verification and authentication
- **`Login.java`**: User authentication
- **`Signup.java`**: User registration
- **`CartItem.java`**: Order item model

#### Customer Module
- **`Menu.java`**: Product browsing and ordering
- **`Feedback.java`**: Customer feedback system

#### Employee Module
- **`Employee.java`**: Product and inventory management

#### Manager Module
- **`Manager.java`**: Employee and system management

#### Options Module
- **`LoginOption.java`**: Login type selection
- **`SignupOption.java`**: Registration type selection

## 🗄️ Database Design

### Connection Management
```java
// Standard connection pattern used throughout the application
String url = "jdbc:mysql://localhost:3306/cafe";
String username = "root";
String password = "";

try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection(url, username, password);
    // Database operations
    con.close();
} catch (Exception e) {
    System.out.println(e);
}
```

### Database Tables
See [DATABASE_SCHEMA.md](DATABASE_SCHEMA.md) for complete schema details.

## 🔧 Development Setup

### IDE Configuration
1. **NetBeans IDE** (Recommended)
   - Import existing project
   - Ensure JDK 8+ is configured
   - Add MySQL Connector/J to libraries

2. **Alternative IDEs**
   - Import as Maven/Ant project
   - Configure build path with MySQL connector
   - Set main class as specified in manifest

### Build Configuration
The project uses **Apache Ant** for building:

```xml
<!-- build.xml key configurations -->
<project name="Cafe_Ordering_System" default="default" basedir=".">
    <description>Builds, tests, and runs the project</description>
    <!-- Build targets defined in nbproject/build-impl.xml -->
</project>
```

### Dependencies
- **MySQL Connector/J**: Database connectivity
- **Java Swing**: GUI framework
- **Java AWT**: Additional UI components

## 💻 Coding Standards

### Naming Conventions
```java
// Classes: PascalCase
public class LoginOption

// Variables: camelCase
private String userName;
private int productPrice;

// Constants: UPPER_SNAKE_CASE
public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/cafe";

// Methods: camelCase
public void validateUserInput()
```

### Code Organization
```java
// Standard class structure
public class ClassName extends JFrame {
    // Static variables
    public static boolean classVariable = false;
    
    // Instance variables
    private String instanceVariable;
    
    // Constructor
    public ClassName() {
        initComponents();
        setupCustomConfiguration();
    }
    
    // Business logic methods
    public void businessMethod() {
        // Implementation
    }
    
    // Database methods
    private String getDatabaseData() {
        // Database operations
    }
    
    // GUI event handlers
    private void buttonActionPerformed(ActionEvent evt) {
        // Event handling
    }
    
    // Generated GUI code
    @SuppressWarnings("unchecked")
    private void initComponents() {
        // NetBeans generated code
    }
}
```

## 🔌 API Documentation

### Authentication System

#### Code Verification
```java
// Manager code verification
public String getManagerCode() {
    String code = "";
    // Database query to retrieve manager code
    String query = "SELECT manager FROM code";
    return code;
}

// Employee code verification
public String getEmployeeCode() {
    String code = "";
    // Database query to retrieve employee code
    String query = "SELECT employee FROM code";
    return code;
}
```

#### User Authentication
```java
// User login validation
public boolean validateLogin(String username, String password, String userType) {
    // Query appropriate table based on userType
    // Return true if credentials are valid
}
```

### Product Management API

#### Add Product
```java
public boolean addProduct(String name, String price, int stock, String image) {
    String query = "INSERT INTO addedproducts VALUES (?, ?, ?, ?)";
    // Execute query and return success status
}
```

#### Remove Product
```java
public boolean removeProduct(String productName) {
    String query = "INSERT INTO removedproducts VALUES (?)";
    // Execute query and return success status
}
```

#### Update Product
```java
public boolean updateProduct(String name, String newPrice, int newStock) {
    String query = "UPDATE addedproducts SET price=?, stock=? WHERE name=?";
    // Execute query and return success status
}
```

### Employee Management API

#### Add Employee
```java
public boolean addEmployee(String username, String password, String salary) {
    String query = "INSERT INTO employees VALUES (?, ?, ?, '1')";
    // Execute query and return success status
}
```

#### Update Employee
```java
public boolean updateEmployee(String username, String newSalary) {
    String query = "UPDATE employees SET salary=? WHERE username=?";
    // Execute query and return success status
}
```

## 🧪 Testing Guidelines

### Unit Testing Structure
```java
// Example test class structure
public class LoginTest {
    @Test
    public void testValidLogin() {
        // Test valid user credentials
    }
    
    @Test
    public void testInvalidLogin() {
        // Test invalid credentials
    }
    
    @Test
    public void testDatabaseConnection() {
        // Test database connectivity
    }
}
```

### Testing Database Operations
1. Use test database for development
2. Create test data before running tests
3. Clean up test data after tests complete

### GUI Testing
1. Test all button click events
2. Validate form input handling
3. Verify navigation between screens
4. Test error message display

## 🔒 Security Considerations

### Password Management
```java
// Avoid storing passwords in plain text
// Consider implementing password hashing
public String hashPassword(String password) {
    // Implement secure hashing algorithm
    // Example: BCrypt, PBKDF2, or Argon2
}
```

### SQL Injection Prevention
```java
// Use PreparedStatement instead of Statement
String query = "SELECT * FROM users WHERE username=? AND password=?";
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, username);
pstmt.setString(2, password);
ResultSet rs = pstmt.executeQuery();
```

### Access Control
- Implement role-based access verification
- Validate user permissions for each operation
- Log security-related events

## 🚀 Deployment

### Building for Production
```bash
# Clean and build
ant clean
ant compile
ant jar

# Create distribution
ant dist
```

### Database Migration
```sql
-- Production database setup
CREATE DATABASE cafe_production;
-- Run schema creation scripts
-- Import initial data
```

### Environment Configuration
```java
// Use environment variables for production
String dbUrl = System.getenv("DATABASE_URL");
String dbUser = System.getenv("DATABASE_USER");
String dbPass = System.getenv("DATABASE_PASSWORD");
```

## 🔄 Version Control

### Git Workflow
```bash
# Feature development
git checkout -b feature/new-feature
git commit -m "Add new feature"
git push origin feature/new-feature

# Code review and merge
git checkout main
git merge feature/new-feature
```

### Branch Strategy
- **main**: Production-ready code
- **develop**: Integration branch
- **feature/***: New features
- **hotfix/***: Critical fixes

## 📈 Performance Optimization

### Database Optimization
```java
// Connection pooling for better performance
public class DatabasePool {
    private static DataSource dataSource;
    
    public static Connection getConnection() {
        // Return pooled connection
    }
}
```

### GUI Performance
- Lazy loading of heavy components
- Background threading for database operations
- Efficient event handling

### Memory Management
- Proper resource cleanup (close connections, statements)
- Avoid memory leaks in event listeners
- Optimize image loading and caching

## 🐛 Debugging

### Common Issues
1. **Database Connection Failures**
   - Check MySQL service status
   - Verify connection parameters
   - Test network connectivity

2. **GUI Rendering Issues**
   - Check Look and Feel settings
   - Verify component initialization order
   - Test on different screen resolutions

3. **Threading Issues**
   - Use SwingUtilities.invokeLater() for GUI updates
   - Avoid blocking EDT with long operations

### Logging
```java
// Implement proper logging
import java.util.logging.Logger;
import java.util.logging.Level;

private static final Logger LOGGER = Logger.getLogger(ClassName.class.getName());

public void someMethod() {
    try {
        // Business logic
        LOGGER.info("Operation completed successfully");
    } catch (Exception e) {
        LOGGER.log(Level.SEVERE, "Operation failed", e);
    }
}
```

## 📚 Additional Resources

### NetBeans GUI Development
- [NetBeans GUI Builder Guide](https://netbeans.apache.org/kb/docs/java/gui-builder.html)
- [Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)

### MySQL Development
- [MySQL Connector/J Documentation](https://dev.mysql.com/doc/connector-j/8.0/en/)
- [MySQL Best Practices](https://dev.mysql.com/doc/refman/8.0/en/optimization.html)

### Java Best Practices
- [Oracle Java Coding Standards](https://www.oracle.com/java/technologies/javase/codeconventions-contents.html)
- [Effective Java by Joshua Bloch](https://www.pearson.com/us/higher-education/program/Bloch-Effective-Java-3rd-Edition/PGM1763855.html)

## 🤝 Contributing

### Code Review Process
1. Create feature branch
2. Implement changes following coding standards
3. Write unit tests for new functionality
4. Submit pull request with detailed description
5. Address review feedback
6. Merge after approval

### Documentation Updates
- Update relevant documentation for code changes
- Include inline code comments for complex logic
- Update API documentation for interface changes

### Release Process
1. Update version numbers
2. Create release notes
3. Test in staging environment
4. Deploy to production
5. Monitor for issues