package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class statistics_view extends javax.swing.JFrame {

    private Connection conn;
    
    public statistics_view() {
        initComponents();
        
        try {
            String path = "H:\\JAVA Projects\\Jeewaka Java assignment\\LMS_new\\src\\main\\java\\icon\\bookWise.png";  // Change .ico to .png
            this.setIconImage(ImageIO.read(new File(path)));
            setTitle("BookWise v1.0");
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        initializeConnection();  // Initialize connection first
        if (conn != null) {  // Only update stats if connection successful
            // Add property change listener to the date chooser
            SelectDate.addPropertyChangeListener("date", evt -> {
                if ("date".equals(evt.getPropertyName())) {
                    updateDashboardStats();  // Update stats when date changes
                }
            });
            updateDashboardStats();
            setupAutoRefresh();          
        }
        
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = 1289;
    int height = 760;
    
    
    // Calculate the position to center the window
    int x = (screenSize.width - width) / 2;
    int y = (screenSize.height - height) / 2;
    
    // Set window bounds (position and size)
    setBounds(x, y, width, height);
    
    // Make window non-resizable (optional)
    setResizable(false);
        
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
            java.util.Date selectedDate = SelectDate.getDate();
            java.util.Date currentDate = new java.util.Date();
            
            // If no date is selected, use current date
            if (selectedDate == null) {
                selectedDate = currentDate;
            }
            
            // Convert dates to SQL date format for comparison
            java.sql.Date sqlSelectedDate = new java.sql.Date(selectedDate.getTime());
            java.sql.Date sqlCurrentDate = new java.sql.Date(currentDate.getTime());
            
            // 1. Total Books - Only count active books
            String totalBooksQuery = "SELECT SUM(copies_available) as total FROM books WHERE status = 'active'";
            try (PreparedStatement pst = conn.prepareStatement(totalBooksQuery)) {
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    int total = rs.getInt("total");
                    TotalBooks.setText(String.valueOf(total > 0 ? total : 0));
                }
            }
        
        // 2. Total Members - Always show current total
        String totalMembersQuery = "SELECT COUNT(*) as total FROM students";
        try (PreparedStatement pst = conn.prepareStatement(totalMembersQuery)) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                TotalMembers.setText(String.valueOf(rs.getInt("total")));
            }
        }
        
        // 3. New Members - Show scheduled registrations for future dates
        String newMembersQuery = "SELECT COUNT(*) as total FROM students WHERE DATE(created_at) = ?";
        try (PreparedStatement pst = conn.prepareStatement(newMembersQuery)) {
            pst.setDate(1, sqlSelectedDate);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                NewMembers.setText(String.valueOf(rs.getInt("total")));
            }
        }
        
        // 4. Issued Books - Show scheduled issues for future dates
        String issuedBooksQuery = "SELECT COUNT(*) as total FROM issued_books WHERE DATE(issued_date) = ?";
        try (PreparedStatement pst = conn.prepareStatement(issuedBooksQuery)) {
            pst.setDate(1, sqlSelectedDate);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Issuedbooks.setText(String.valueOf(rs.getInt("total")));
            }
        }
        
        // 5. Returned Books - Show all returns scheduled for the selected date
        String returnedBooksQuery = "SELECT " +
            "SUM(CASE WHEN DATE(return_date) = ? THEN 1 ELSE 0 END) as returned, " +
            "SUM(CASE WHEN DATE(return_date) = ? AND return_date > due_date THEN 1 ELSE 0 END) as late_returns, " +
            "SUM(CASE WHEN DATE(return_date) = ? AND condition_on_return IN ('Slightly Damaged', 'Heavily Damaged', 'Missing Pages', 'Lost') THEN 1 ELSE 0 END) as damaged " +
            "FROM returned_books " +
            "WHERE DATE(return_date) = ?";
            
        try (PreparedStatement pst = conn.prepareStatement(returnedBooksQuery)) {
            pst.setDate(1, sqlSelectedDate);
            pst.setDate(2, sqlSelectedDate);
            pst.setDate(3, sqlSelectedDate);
            pst.setDate(4, sqlSelectedDate);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Returnedbooks.setText(String.valueOf(rs.getInt("returned")));
                LateReturns.setText(String.valueOf(rs.getInt("late_returns")));
                DamegedBooks.setText(String.valueOf(rs.getInt("damaged")));
            }
        }
    } catch (SQLException ex) {
        showError("Database Error", "Error updating dashboard: " + ex.getMessage());
        System.err.println("SQL Error: " + ex.getMessage());
        ex.printStackTrace();
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AddNewMemberNav = new javax.swing.JLabel();
        AddNewBookNav = new javax.swing.JLabel();
        IssuedBooksNav = new javax.swing.JLabel();
        ReturnedBooksNav = new javax.swing.JLabel();
        LibraryDashboardNav = new javax.swing.JLabel();
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
        SelectDate = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        LibraryDashboardNav.setText("Library Statistics");
        LibraryDashboardNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LibraryDashboardNavMouseClicked(evt);
            }
        });
        getContentPane().add(LibraryDashboardNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 36, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Total Members");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 310, -1, -1));

        issuedBooks.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        issuedBooks.setForeground(new java.awt.Color(255, 255, 255));
        issuedBooks.setText("Issued Books");
        getContentPane().add(issuedBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 420, -1, -1));

        ReturnedBooks.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ReturnedBooks.setForeground(new java.awt.Color(255, 255, 255));
        ReturnedBooks.setText("Returned Books");
        getContentPane().add(ReturnedBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 420, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("New Members");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 310, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Late Returns");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 420, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Damaged Books");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 420, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Total Books");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 210, -1, -1));

        TotalBooks.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        TotalBooks.setForeground(new java.awt.Color(153, 204, 255));
        TotalBooks.setText("26");
        getContentPane().add(TotalBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 240, -1, -1));

        TotalMembers.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        TotalMembers.setForeground(new java.awt.Color(153, 204, 255));
        TotalMembers.setText("26");
        getContentPane().add(TotalMembers, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, -1, -1));

        NewMembers.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        NewMembers.setForeground(new java.awt.Color(153, 204, 255));
        NewMembers.setText("26");
        getContentPane().add(NewMembers, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 340, -1, -1));

        Close.setBackground(new java.awt.Color(128, 155, 46));
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

        Issuedbooks.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        Issuedbooks.setForeground(new java.awt.Color(153, 204, 255));
        Issuedbooks.setText("26");
        getContentPane().add(Issuedbooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 460, -1, -1));

        Returnedbooks.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        Returnedbooks.setForeground(new java.awt.Color(153, 204, 255));
        Returnedbooks.setText("26");
        getContentPane().add(Returnedbooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 460, -1, -1));

        LateReturns.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        LateReturns.setForeground(new java.awt.Color(153, 204, 255));
        LateReturns.setText("26");
        getContentPane().add(LateReturns, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 460, -1, -1));

        DamegedBooks.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        DamegedBooks.setForeground(new java.awt.Color(153, 204, 255));
        DamegedBooks.setText("26");
        getContentPane().add(DamegedBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 460, -1, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("BookWise | Library Management System");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 660, -1, -1));

        SelectDate.setBackground(new java.awt.Color(255, 255, 255));
        SelectDate.setForeground(new java.awt.Color(0, 153, 255));
        getContentPane().add(SelectDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 200, 380, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon("H:\\JAVA Projects\\Jeewaka Java assignment\\LMS_new\\public\\dashboard.png")); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon("H:\\JAVA Projects\\Jeewaka Java assignment\\LMS_new\\public\\large screen.jpg")); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddNewMemberNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddNewMemberNavMouseClicked
        setVisible(false);
        new students_view().setVisible(true);
    }//GEN-LAST:event_AddNewMemberNavMouseClicked

    private void AddNewBookNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddNewBookNavMouseClicked
        setVisible(false);
        new books_view().setVisible(true);
    }//GEN-LAST:event_AddNewBookNavMouseClicked

    private void IssuedBooksNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IssuedBooksNavMouseClicked
        setVisible(false);
        new issueBooks_view().setVisible(true);
    }//GEN-LAST:event_IssuedBooksNavMouseClicked

    private void ReturnedBooksNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReturnedBooksNavMouseClicked
        setVisible(false);
        new returnBooks_view().setVisible(true);
    }//GEN-LAST:event_ReturnedBooksNavMouseClicked

    private void LibraryDashboardNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LibraryDashboardNavMouseClicked
        //       setVisible(false);
        //        new dashBoard().setVisible(true);
    }//GEN-LAST:event_LibraryDashboardNavMouseClicked

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        setVisible(false);
        new home_view().setVisible(true);
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
            java.util.logging.Logger.getLogger(statistics_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(statistics_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(statistics_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(statistics_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new statistics_view().setVisible(true);
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
    private com.toedter.calendar.JDateChooser SelectDate;
    private javax.swing.JLabel TotalBooks;
    private javax.swing.JLabel TotalMembers;
    private javax.swing.JLabel issuedBooks;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
