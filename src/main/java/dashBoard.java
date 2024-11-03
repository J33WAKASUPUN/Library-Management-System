
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Timer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author User
 */
public class dashBoard extends javax.swing.JFrame {

    /**
     * Creates new form dashBoard
     */
    
    private Connection conn;
    
    public dashBoard() {
        initComponents();
        initializeConnection();  // Initialize connection first
        if (conn != null) {  // Only update stats if connection successful
            updateDashboardStats();
            setupAutoRefresh();
        }
    }
    
    public class ConnectionProvider {
    private static Connection con = null;
    
    public static Connection getCon() {
        if (con != null) return con;
        
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Database connection parameters
            String url = "jdbc:mysql://localhost:3306/lms_new"; 
            String username = "root";    
            String password = "";        
            
            // Establish the connection
            con = DriverManager.getConnection(url, username, password);
            return con;
            
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found. Add the MySQL connector JAR to your project.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database. Check your connection parameters and ensure MySQL is running.", e);
        }
    }
    
    // Method to close the connection
    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                con = null;
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
    
    private void initializeConnection() {
        try {
            conn = ConnectionProvider.getCon();  // Uncomment and use your connection provider
            if (conn == null) {
                showError("Database connection failed", "Could not establish database connection");
            }
        } catch (Exception ex) {
            showError("Connection Error", "Failed to initialize database connection: " + ex.getMessage());
        }
    }
    
    private void showError(String title, String message) {
        javax.swing.JOptionPane.showMessageDialog(this,
            message,
            title,
            javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    
    private void updateDashboardStats() {
        if (conn == null) {
            showError("Database Error", "No active database connection");
            return;
        }
        
        try {
            // 1. Update Total Books
            String totalBooksQuery = "SELECT SUM(copies_available) as total FROM books";
            try (PreparedStatement pst = conn.prepareStatement(totalBooksQuery)) {
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    TotalBooks.setText(String.valueOf(rs.getInt("total")));
                }
            }
            
            // 2. Update Total Members (Students)
            String totalMembersQuery = "SELECT COUNT(*) as total FROM students";
            try (PreparedStatement pst = conn.prepareStatement(totalMembersQuery)) {
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    TotalMembers.setText(String.valueOf(rs.getInt("total")));
                }
            }
            
          // 3. Update New Members (assuming there's a created_at or similar field)
            String newMembersQuery = "SELECT COUNT(*) as total FROM students WHERE created_at >= DATE_SUB(CURRENT_DATE, INTERVAL 3 DAY)";
            try (PreparedStatement pst = conn.prepareStatement(newMembersQuery)) {
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    NewMembers.setText(String.valueOf(rs.getInt("total")));
                }
            }
            
            // 4. Update Currently Issued Books
            String issuedBooksQuery = "SELECT COUNT(*) as total FROM issued_books";
            try (PreparedStatement pst = conn.prepareStatement(issuedBooksQuery)) {
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    Issuedbooks.setText(String.valueOf(rs.getInt("total")));
                }
            }
            
            // 5. Update Returned Books
            String returnedBooksQuery = "SELECT COUNT(*) as total FROM returned_books";
            try (PreparedStatement pst = conn.prepareStatement(returnedBooksQuery)) {
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    Returnedbooks.setText(String.valueOf(rs.getInt("total")));
                }
            }
            
            // 6. Update Late Returns
            String lateReturnsQuery = "SELECT COUNT(*) as total FROM returned_books WHERE return_date > due_date";
            try (PreparedStatement pst = conn.prepareStatement(lateReturnsQuery)) {
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    LateReturns.setText(String.valueOf(rs.getInt("total")));
                }
            }
            
           // 7. Update Late Returns
          String damagedBooksQuery = "SELECT COUNT(*) as total FROM returned_books WHERE condition_on_return IN ('Slightly Damaged', 'Heavily Damaged', 'Missing Pages', 'Lost')";
         try (PreparedStatement pst = conn.prepareStatement(damagedBooksQuery)) {
              ResultSet rs = pst.executeQuery();
             if (rs.next()) {
                 DamegedBooks.setText(String.valueOf(rs.getInt("total")));
              }
          }
            
        } catch (SQLException ex) {
            showError("Database Error", "Error updating dashboard: " + ex.getMessage());
        }
    }
    
    public void refreshDashboard() {
        if (conn != null) {
            updateDashboardStats();
        } else {
            initializeConnection();  // Try to reconnect
            if (conn != null) {
                updateDashboardStats();
            }
        }
    }
    
    private void setupAutoRefresh() {
        new Timer(300000, (ActionEvent e) -> {
            refreshDashboard();
        }).start();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jLabel17 = new javax.swing.JLabel();
        AddNewMemberNav = new javax.swing.JLabel();
        AddNewBookNav = new javax.swing.JLabel();
        IssuedBooksNav = new javax.swing.JLabel();
        ReturnedBooksNav = new javax.swing.JLabel();
        LibraryDashboardNav = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        issuedBooks = new javax.swing.JLabel();
        ReturnedBooks = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TotalBooks = new javax.swing.JLabel();
        TotalMembers = new javax.swing.JLabel();
        NewMembers = new javax.swing.JLabel();
        Close = new javax.swing.JButton();
        Issuedbooks = new javax.swing.JLabel();
        Returnedbooks = new javax.swing.JLabel();
        LateReturns = new javax.swing.JLabel();
        DamegedBooks = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jLabel17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("BookWise | Library Management System");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 135));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AddNewMemberNav.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        AddNewMemberNav.setForeground(new java.awt.Color(0, 153, 255));
        AddNewMemberNav.setText("Add New Member");
        AddNewMemberNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddNewMemberNavMouseClicked(evt);
            }
        });
        getContentPane().add(AddNewMemberNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 56, -1, -1));

        AddNewBookNav.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        AddNewBookNav.setForeground(new java.awt.Color(0, 153, 255));
        AddNewBookNav.setText("Add New Book");
        AddNewBookNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddNewBookNavMouseClicked(evt);
            }
        });
        getContentPane().add(AddNewBookNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 56, -1, -1));

        IssuedBooksNav.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        IssuedBooksNav.setForeground(new java.awt.Color(0, 153, 255));
        IssuedBooksNav.setText("Issued Books");
        IssuedBooksNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IssuedBooksNavMouseClicked(evt);
            }
        });
        getContentPane().add(IssuedBooksNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(869, 56, -1, -1));

        ReturnedBooksNav.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ReturnedBooksNav.setForeground(new java.awt.Color(0, 153, 255));
        ReturnedBooksNav.setText("    Returned Books");
        ReturnedBooksNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReturnedBooksNavMouseClicked(evt);
            }
        });
        getContentPane().add(ReturnedBooksNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(1012, 56, -1, -1));

        LibraryDashboardNav.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        LibraryDashboardNav.setForeground(new java.awt.Color(255, 255, 255));
        LibraryDashboardNav.setText("Library Dashboard");
        LibraryDashboardNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LibraryDashboardNavMouseClicked(evt);
            }
        });
        getContentPane().add(LibraryDashboardNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(503, 36, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon("H:\\JAVA Projects\\LMS_new\\public\\dashboard.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Total Members");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, -1, -1));

        issuedBooks.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        issuedBooks.setForeground(new java.awt.Color(255, 255, 255));
        issuedBooks.setText("Issued Books");
        getContentPane().add(issuedBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 380, -1, -1));

        ReturnedBooks.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ReturnedBooks.setForeground(new java.awt.Color(255, 255, 255));
        ReturnedBooks.setText("Returned Books");
        getContentPane().add(ReturnedBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 380, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("New Members");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 230, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Late Returns");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 380, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Damaged Books");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 380, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Total Books");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 230, -1, -1));

        TotalBooks.setFont(new java.awt.Font("Century Gothic", 0, 72)); // NOI18N
        TotalBooks.setForeground(new java.awt.Color(153, 204, 255));
        TotalBooks.setText("26");
        getContentPane().add(TotalBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, -1, -1));

        TotalMembers.setFont(new java.awt.Font("Century Gothic", 0, 72)); // NOI18N
        TotalMembers.setForeground(new java.awt.Color(153, 204, 255));
        TotalMembers.setText("26");
        getContentPane().add(TotalMembers, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 260, -1, -1));

        NewMembers.setFont(new java.awt.Font("Century Gothic", 0, 72)); // NOI18N
        NewMembers.setForeground(new java.awt.Color(153, 204, 255));
        NewMembers.setText("26");
        getContentPane().add(NewMembers, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 260, -1, -1));

        Close.setBackground(new java.awt.Color(148, 179, 53));
        Close.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Close.setForeground(new java.awt.Color(0, 51, 102));
        Close.setText("Close");
        Close.setBorder(null);
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });
        getContentPane().add(Close, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 530, 170, 25));

        Issuedbooks.setFont(new java.awt.Font("Century Gothic", 0, 72)); // NOI18N
        Issuedbooks.setForeground(new java.awt.Color(153, 204, 255));
        Issuedbooks.setText("26");
        getContentPane().add(Issuedbooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 420, -1, -1));

        Returnedbooks.setFont(new java.awt.Font("Century Gothic", 0, 72)); // NOI18N
        Returnedbooks.setForeground(new java.awt.Color(153, 204, 255));
        Returnedbooks.setText("26");
        getContentPane().add(Returnedbooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 420, -1, -1));

        LateReturns.setFont(new java.awt.Font("Century Gothic", 0, 72)); // NOI18N
        LateReturns.setForeground(new java.awt.Color(153, 204, 255));
        LateReturns.setText("26");
        getContentPane().add(LateReturns, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 420, -1, -1));

        DamegedBooks.setFont(new java.awt.Font("Century Gothic", 0, 72)); // NOI18N
        DamegedBooks.setForeground(new java.awt.Color(153, 204, 255));
        DamegedBooks.setText("26");
        getContentPane().add(DamegedBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 420, -1, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("BookWise | Library Management System");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 660, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon("H:\\JAVA Projects\\LMS_new\\public\\large screen.jpg")); // NOI18N
        jLabel19.setText("jLabel19");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddNewMemberNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddNewMemberNavMouseClicked
       setVisible(false);
        new newStudents().setVisible(true);
    }//GEN-LAST:event_AddNewMemberNavMouseClicked

    private void AddNewBookNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddNewBookNavMouseClicked
       setVisible(false);
        new newBooks().setVisible(true);
    }//GEN-LAST:event_AddNewBookNavMouseClicked

    private void IssuedBooksNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IssuedBooksNavMouseClicked
        setVisible(false);
        new issuedBooks().setVisible(true);
    }//GEN-LAST:event_IssuedBooksNavMouseClicked

    private void ReturnedBooksNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReturnedBooksNavMouseClicked
        setVisible(false);
        new returnedBooks().setVisible(true);
    }//GEN-LAST:event_ReturnedBooksNavMouseClicked

    private void LibraryDashboardNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LibraryDashboardNavMouseClicked
//       setVisible(false);
//        new dashBoard().setVisible(true);
    }//GEN-LAST:event_LibraryDashboardNavMouseClicked

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        setVisible(false);
        new home().setVisible(true);
    }//GEN-LAST:event_CloseActionPerformed

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
            java.util.logging.Logger.getLogger(dashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashBoard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddNewBookNav;
    private javax.swing.JLabel AddNewMemberNav;
    private javax.swing.JButton Close;
    private javax.swing.JLabel DamegedBooks;
    private javax.swing.JLabel IssuedBooksNav;
    private javax.swing.JLabel Issuedbooks;
    private javax.swing.JLabel LateReturns;
    private javax.swing.JLabel LibraryDashboardNav;
    private javax.swing.JLabel NewMembers;
    private javax.swing.JLabel ReturnedBooks;
    private javax.swing.JLabel ReturnedBooksNav;
    private javax.swing.JLabel Returnedbooks;
    private javax.swing.JLabel TotalBooks;
    private javax.swing.JLabel TotalMembers;
    private javax.swing.JLabel issuedBooks;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
