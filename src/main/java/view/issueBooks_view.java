package view;

import controller.issueBooks_controller;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import model.issueBooks_model;

public class issueBooks_view extends javax.swing.JFrame {

    private issueBooks_controller controller;

    public issueBooks_view() {
        initComponents();
        controller = new issueBooks_controller();
        setupListeners();
        refreshIssuedBooksTable();

        try {
            String path = "H:\\JAVA Projects\\Jeewaka Java assignment\\LMS_new\\src\\main\\java\\icon\\bookWise.png";  // Change .ico to .png
            this.setIconImage(ImageIO.read(new File(path)));
            setTitle("BookWise v1.0");
        } catch (Exception e) {
            e.printStackTrace();
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

        java.awt.event.MouseAdapter mouseListener = new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ((javax.swing.JLabel) evt.getSource()).setForeground(new Color(153, 204, 255)); // Hover color
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                ((javax.swing.JLabel) evt.getSource()).setForeground(new Color(0, 153, 255)); // Original color
            }
        };

        // Add the listener to each variable
        AddNewBookNav.addMouseListener(mouseListener);
        ReturnedBooksNav.addMouseListener(mouseListener);
        IssuedBooksNav.addMouseListener(mouseListener);
        AddNewMemberNav.addMouseListener(mouseListener);
//        LibraryDashboardNav.addMouseListener(mouseListener);
    }

    private void setupListeners() {
        // Student ID Listener
        StudentID.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateStudentName();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateStudentName();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateStudentName();
            }
        });

        // Book ID Listener
        BookID.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateBookTitle();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateBookTitle();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateBookTitle();
            }
        });

    }

    private void updateStudentName() {
        String studentId = StudentID.getText();
        String studentName = controller.getStudentName(studentId);
        StudentName.setText(studentName);
    }

    private void updateBookTitle() {
        String bookId = BookID.getText();
        String bookTitle = controller.getBookTitle(bookId);
        BookTitle.setText(bookTitle);
    }

    private void saveIssuedBook() {
        try {
            String studentId = StudentID.getText();
            String studentName = StudentName.getText();
            String bookId = BookID.getText();
            String bookTitle = BookTitle.getText();
            Date issuedDate = IssuedDate.getDate();
            Date dueDate = DueDate.getDate();

            // Basic validation
            if (studentId.isEmpty() || studentName.isEmpty() || bookId.isEmpty()
                    || bookTitle.isEmpty() || issuedDate == null || dueDate == null) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            issueBooks_model book = new issueBooks_model(
                    studentId, studentName, bookId, bookTitle, issuedDate, dueDate
            );

            if (controller.saveIssuedBook(book)) {
                JOptionPane.showMessageDialog(this, "Book Issued Successfully!");
                clearFields();
                refreshIssuedBooksTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to issue book!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearFields() {
        StudentID.setText("");
        StudentName.setText("");
        BookID.setText("");
        BookTitle.setText("");
        IssuedDate.setDate(null);
        DueDate.setDate(null);
    }

    private void searchIssuedBooks() {
        String searchTerm = SearchField1.getText().trim();

        if (searchTerm.isEmpty()) {
            refreshIssuedBooksTable();
            return;
        }

        IssuedBooksTable.setModel(controller.searchIssuedBooks(searchTerm));
    }

    private void refreshIssuedBooksTable() {
        IssuedBooksTable.setModel(controller.fetchIssuedBooks());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        StudentID = new javax.swing.JTextField();
        StudentName = new javax.swing.JTextField();
        BookID = new javax.swing.JTextField();
        BookTitle = new javax.swing.JTextField();
        save = new javax.swing.JButton();
        close = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        IssuedBooksTable = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        SearchField1 = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        ShowAll = new javax.swing.JButton();
        LibraryDashboardNav = new javax.swing.JLabel();
        IssuedBooksNav = new javax.swing.JLabel();
        ReturnedBooksNav = new javax.swing.JLabel();
        AddNewMemberNav = new javax.swing.JLabel();
        AddNewBookNav = new javax.swing.JLabel();
        IssuedDate = new com.toedter.calendar.JDateChooser();
        DueDate = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Student ID");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Student Name ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Book ID");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Book Title");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Issue Date");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Due Date");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, -1, -1));

        StudentID.setBackground(new java.awt.Color(255, 255, 255));
        StudentID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        StudentID.setForeground(new java.awt.Color(0, 102, 255));
        StudentID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StudentIDActionPerformed(evt);
            }
        });
        getContentPane().add(StudentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 234, 27));

        StudentName.setBackground(new java.awt.Color(255, 255, 255));
        StudentName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        StudentName.setForeground(new java.awt.Color(0, 102, 255));
        getContentPane().add(StudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 234, 29));

        BookID.setBackground(new java.awt.Color(255, 255, 255));
        BookID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BookID.setForeground(new java.awt.Color(0, 102, 255));
        BookID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookIDActionPerformed(evt);
            }
        });
        getContentPane().add(BookID, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 234, 29));

        BookTitle.setBackground(new java.awt.Color(255, 255, 255));
        BookTitle.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BookTitle.setForeground(new java.awt.Color(0, 102, 255));
        getContentPane().add(BookTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 234, 31));

        save.setBackground(new java.awt.Color(51, 153, 255));
        save.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        save.setForeground(new java.awt.Color(255, 255, 255));
        save.setText("Save");
        save.setBorder(null);
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        getContentPane().add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 480, 234, 33));

        close.setBackground(new java.awt.Color(255, 51, 51));
        close.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        close.setForeground(new java.awt.Color(255, 255, 255));
        close.setText("Close");
        close.setBorder(null);
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });
        getContentPane().add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 520, 234, 33));

        IssuedBooksTable.setBackground(new java.awt.Color(51, 153, 255));
        IssuedBooksTable.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        IssuedBooksTable.setForeground(new java.awt.Color(255, 255, 255));
        IssuedBooksTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        IssuedBooksTable.setGridColor(new java.awt.Color(255, 255, 255));
        IssuedBooksTable.setIntercellSpacing(new java.awt.Dimension(2, 2));
        IssuedBooksTable.setSelectionBackground(new java.awt.Color(255, 51, 51));
        IssuedBooksTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        IssuedBooksTable.setShowGrid(true);
        IssuedBooksTable.setSurrendersFocusOnKeystroke(true);
        IssuedBooksTable.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                IssuedBooksTableAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(IssuedBooksTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 200, 620, 370));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText(" BookWise | Library Management System");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 660, -1, -1));

        SearchField1.setBackground(new java.awt.Color(255, 255, 255));
        SearchField1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        SearchField1.setForeground(new java.awt.Color(0, 102, 255));
        SearchField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchField1ActionPerformed(evt);
            }
        });
        getContentPane().add(SearchField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 160, 200, 30));

        Search.setBackground(new java.awt.Color(51, 153, 255));
        Search.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Search.setForeground(new java.awt.Color(255, 255, 255));
        Search.setText("Search");
        Search.setBorder(null);
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        getContentPane().add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 160, 200, 30));

        ShowAll.setBackground(new java.awt.Color(51, 153, 255));
        ShowAll.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ShowAll.setForeground(new java.awt.Color(255, 255, 255));
        ShowAll.setText("Show All");
        ShowAll.setBorder(null);
        ShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAllActionPerformed(evt);
            }
        });
        getContentPane().add(ShowAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 160, 200, 30));

        LibraryDashboardNav.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        LibraryDashboardNav.setForeground(new java.awt.Color(255, 255, 255));
        LibraryDashboardNav.setText(" Issued Books");
        LibraryDashboardNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LibraryDashboardNavMouseClicked(evt);
            }
        });
        getContentPane().add(LibraryDashboardNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, -1, -1));

        IssuedBooksNav.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        IssuedBooksNav.setForeground(new java.awt.Color(0, 153, 255));
        IssuedBooksNav.setText("Library Statistics ");
        IssuedBooksNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IssuedBooksNavMouseClicked(evt);
            }
        });
        getContentPane().add(IssuedBooksNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(832, 50, -1, -1));

        ReturnedBooksNav.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ReturnedBooksNav.setForeground(new java.awt.Color(0, 153, 255));
        ReturnedBooksNav.setText("Returned Books");
        ReturnedBooksNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReturnedBooksNavMouseClicked(evt);
            }
        });
        getContentPane().add(ReturnedBooksNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 50, -1, -1));

        AddNewMemberNav.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        AddNewMemberNav.setForeground(new java.awt.Color(0, 153, 255));
        AddNewMemberNav.setText("Add New Member");
        AddNewMemberNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddNewMemberNavMouseClicked(evt);
            }
        });
        getContentPane().add(AddNewMemberNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, -1, -1));

        AddNewBookNav.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        AddNewBookNav.setForeground(new java.awt.Color(0, 153, 255));
        AddNewBookNav.setText("Add New Book");
        AddNewBookNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddNewBookNavMouseClicked(evt);
            }
        });
        getContentPane().add(AddNewBookNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, -1));

        IssuedDate.setBackground(new java.awt.Color(255, 255, 255));
        IssuedDate.setForeground(new java.awt.Color(0, 102, 255));
        getContentPane().add(IssuedDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 382, 230, 30));

        DueDate.setBackground(new java.awt.Color(255, 255, 255));
        DueDate.setForeground(new java.awt.Color(0, 102, 255));
        getContentPane().add(DueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 424, 230, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon("H:\\JAVA Projects\\Jeewaka Java assignment\\LMS_new\\public\\large screen.jpg")); // NOI18N
        jLabel10.setText("jLabel10");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StudentIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StudentIDActionPerformed

    }//GEN-LAST:event_StudentIDActionPerformed

    private void BookIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BookIDActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        saveIssuedBook();
    }//GEN-LAST:event_saveActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        setVisible(false);
        new home_view().setVisible(true);
    }//GEN-LAST:event_closeActionPerformed

    private void IssuedBooksTableAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_IssuedBooksTableAncestorAdded

    }//GEN-LAST:event_IssuedBooksTableAncestorAdded

    private void SearchField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchField1ActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        searchIssuedBooks();
    }//GEN-LAST:event_SearchActionPerformed

    private void ShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllActionPerformed
        SearchField1.setText("");
        refreshIssuedBooksTable();
    }//GEN-LAST:event_ShowAllActionPerformed

    private void LibraryDashboardNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LibraryDashboardNavMouseClicked
//        setVisible(false);
//        new issueBooks_view().setVisible(true);
    }//GEN-LAST:event_LibraryDashboardNavMouseClicked

    private void IssuedBooksNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IssuedBooksNavMouseClicked
        setVisible(false);
        new statistics_view().setVisible(true);
    }//GEN-LAST:event_IssuedBooksNavMouseClicked

    private void ReturnedBooksNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReturnedBooksNavMouseClicked
        setVisible(false);
        new returnBooks_view().setVisible(true);
    }//GEN-LAST:event_ReturnedBooksNavMouseClicked

    private void AddNewMemberNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddNewMemberNavMouseClicked
        setVisible(false);
        new students_view().setVisible(true);
    }//GEN-LAST:event_AddNewMemberNavMouseClicked

    private void AddNewBookNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddNewBookNavMouseClicked
        setVisible(false);
        new books_view().setVisible(true);
    }//GEN-LAST:event_AddNewBookNavMouseClicked

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
            java.util.logging.Logger.getLogger(issueBooks_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(issueBooks_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(issueBooks_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(issueBooks_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new issueBooks_view().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddNewBookNav;
    private javax.swing.JLabel AddNewMemberNav;
    private javax.swing.JTextField BookID;
    private javax.swing.JTextField BookTitle;
    private com.toedter.calendar.JDateChooser DueDate;
    private javax.swing.JLabel IssuedBooksNav;
    private javax.swing.JTable IssuedBooksTable;
    private com.toedter.calendar.JDateChooser IssuedDate;
    private javax.swing.JLabel LibraryDashboardNav;
    private javax.swing.JLabel ReturnedBooksNav;
    private javax.swing.JButton Search;
    private javax.swing.JTextField SearchField1;
    private javax.swing.JButton ShowAll;
    private javax.swing.JTextField StudentID;
    private javax.swing.JTextField StudentName;
    private javax.swing.JButton close;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton save;
    // End of variables declaration//GEN-END:variables
}
