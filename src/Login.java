// checkpoint: sign-up function should have its own GUI as suggested by QA.
// after login is clicked, user should be asked if they want to log-in as customer, employee, or manager.
// proper errors should execute if they want to log-in as manager but is not authorized (not in database)

import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author admin
 */
public class Login extends javax.swing.JFrame {

    public static Hashtable<String, String> customersAcc = new Hashtable<>();
    public static Hashtable<String, String> employeesAcc = new Hashtable<>();
    public static Hashtable<String, String> managersAcc = new Hashtable<>();

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        Color color = new Color(221, 211, 208);
        getContentPane().setBackground(color);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        customersToLocalDB();
        employeesToLocalDB();
        managersToLocalDB();
    }

    public static void customersToLocalDB() {
        // mysql connection
        String url = "jdbc:mysql://localhost:3306/cafe";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, username, password);

            Statement stm = con.createStatement();

            ResultSet result = stm.executeQuery("select * from customers");

            // put all registered accounts in MySQL to the HashTable.
            while (result.next()) {
                customersAcc.put(result.getString(1), result.getString(2));
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void employeesToLocalDB() {
        // mysql connection
        String url = "jdbc:mysql://localhost:3306/cafe";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, username, password);

            Statement stm = con.createStatement();

            ResultSet result = stm.executeQuery("select * from employees");

            // put all registered accounts in MySQL to the HashTable.
            while (result.next()) {
                employeesAcc.put(result.getString(1), result.getString(2));
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void managersToLocalDB() {
        // mysql connection
        String url = "jdbc:mysql://localhost:3306/cafe";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, username, password);

            Statement stm = con.createStatement();

            ResultSet result = stm.executeQuery("select * from managers");

            // put all registered accounts in MySQL to the HashTable.
            while (result.next()) {
                managersAcc.put(result.getString(1), result.getString(2));
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loginFunction() {
        if (edtUsername.getText().isEmpty() && edtPassword.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your credentials!");
        } else {
            LoginOption.username = edtUsername.getText();
            LoginOption.password = edtPassword.getText();
            if (isCustomer(edtUsername.getText(), edtPassword.getText()) || isEmployee(edtUsername.getText(), edtPassword.getText())
                    || isManager(edtUsername.getText(), edtPassword.getText())) {
                LoginOption lo = new LoginOption();
                lo.setVisible(true);
                dispose();
            } else {
                wrongPrompt(edtUsername.getText(), edtPassword.getText());
                edtUsername.setText("");
                edtPassword.setText("");
            }
        }
    }

    public void wrongPrompt(String username, String password) {
        if (customersAcc.get(username) == null || employeesAcc.get(username) == null
                || managersAcc.get(username) == null) {
            JOptionPane.showMessageDialog(this, "Wrong username or password!");
        } else if (customersAcc.get(username) != null && !password.equals(customersAcc.get(username))
                || employeesAcc.get(username) != null && !password.equals(employeesAcc.get(username))
                || managersAcc.get(username) != null && !password.equals(managersAcc.get(username))) {
            JOptionPane.showMessageDialog(this, "Wrong username or password!");
        } 
    }

    public boolean isCustomer(String username, String password) {
        return customersAcc.get(username) != null
                && password.equals(customersAcc.get(username));
    }

    public boolean isEmployee(String username, String password) {
        return employeesAcc.get(username) != null
                && password.equals(employeesAcc.get(username));
    }

    public boolean isManager(String username, String password) {
        return managersAcc.get(username) != null
                && password.equals(managersAcc.get(username));
    }

    public void goToSignUp() {
        SignupOption signup = new SignupOption();
        signup.setVisible(true);
        dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        edtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        edtPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cafe Ordering System");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jLabel1.setText("USERNAME");

        edtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtUsernameActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Brush Script MT", 0, 18)); // NOI18N
        jLabel2.setText("Cafe Ordering System");

        jButton1.setBackground(new java.awt.Color(98, 66, 57));
        jButton1.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("LOG IN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jButton2.setText("Sign Up");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        edtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtPasswordActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Brush Script MT", 0, 48)); // NOI18N
        jLabel4.setText("Welcome!");

        jLabel5.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jLabel5.setText("PASSWORD");

        jLabel6.setFont(new java.awt.Font("Rockwell", 0, 10)); // NOI18N
        jLabel6.setText("Don't have an account?");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/kopii.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2))
                        .addComponent(jLabel5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(edtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(edtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(77, 77, 77))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)))
                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(edtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setForeground(new java.awt.Color(98, 66, 57));
        jLabel2.setForeground(new java.awt.Color(98, 66, 57));
        jLabel4.setForeground(new java.awt.Color(98, 66, 57));
        jLabel5.setForeground(new java.awt.Color(98, 66, 57));
        jLabel5.setForeground(new java.awt.Color(98, 66, 57));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        loginFunction();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentHidden

    private void edtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtPasswordActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        goToSignUp();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void edtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtUsernameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPasswordField edtPassword;
    public javax.swing.JTextField edtUsername;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
