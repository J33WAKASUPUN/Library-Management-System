import Project.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author User
 */
public class returnedBooks extends javax.swing.JFrame {

    /**
     * Creates new form returnedBooks
     */
    public returnedBooks() {
        initComponents();
        setupReturnedBookListener();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        StudentID = new javax.swing.JTextField();
        StudentName = new javax.swing.JTextField();
        BookID = new javax.swing.JTextField();
        BookName = new javax.swing.JTextField();
        IssueDate = new javax.swing.JTextField();
        DueDate = new javax.swing.JTextField();
        ReturnDate = new com.toedter.calendar.JDateChooser();
        ConditionOnReturn = new javax.swing.JComboBox<>();
        DamegeFees = new javax.swing.JComboBox<>();
        Save = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ReturnedBooksTable = new javax.swing.JTable();
        SearchField1 = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        ShowAll = new javax.swing.JButton();
        AddNewMemberNav = new javax.swing.JLabel();
        AddNewBookNav = new javax.swing.JLabel();
        IssuedBooksNav = new javax.swing.JLabel();
        ReturnedBooksNav = new javax.swing.JLabel();
        LibraryDashboardNav = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 135));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Student ID");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Book ID");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, 15));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Book Name");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Issue Date");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Due Date");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Return Date");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Condition on Return");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Damage Fees");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 480, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Student Name");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, -1, -1));

        StudentID.setBackground(new java.awt.Color(255, 255, 255));
        StudentID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        StudentID.setForeground(new java.awt.Color(0, 102, 255));
        StudentID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StudentIDActionPerformed(evt);
            }
        });
        getContentPane().add(StudentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 234, 31));

        StudentName.setBackground(new java.awt.Color(255, 255, 255));
        StudentName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        StudentName.setForeground(new java.awt.Color(0, 102, 255));
        getContentPane().add(StudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 234, 31));

        BookID.setBackground(new java.awt.Color(255, 255, 255));
        BookID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BookID.setForeground(new java.awt.Color(0, 102, 255));
        BookID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookIDActionPerformed(evt);
            }
        });
        getContentPane().add(BookID, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 234, 33));

        BookName.setBackground(new java.awt.Color(255, 255, 255));
        BookName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BookName.setForeground(new java.awt.Color(0, 102, 255));
        getContentPane().add(BookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 234, 34));

        IssueDate.setBackground(new java.awt.Color(255, 255, 255));
        IssueDate.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        IssueDate.setForeground(new java.awt.Color(0, 102, 255));
        getContentPane().add(IssueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 234, 32));

        DueDate.setBackground(new java.awt.Color(255, 255, 255));
        DueDate.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        DueDate.setForeground(new java.awt.Color(0, 102, 255));
        getContentPane().add(DueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 234, 32));

        ReturnDate.setBackground(new java.awt.Color(255, 255, 255));
        ReturnDate.setForeground(new java.awt.Color(0, 102, 255));
        getContentPane().add(ReturnDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 390, 234, 30));

        ConditionOnReturn.setBackground(new java.awt.Color(255, 255, 255));
        ConditionOnReturn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ConditionOnReturn.setForeground(new java.awt.Color(0, 102, 255));
        ConditionOnReturn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Good", "Slightly Damaged", "Heavily Damaged", "Missing Pages", "Lost" }));
        ConditionOnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConditionOnReturnActionPerformed(evt);
            }
        });
        getContentPane().add(ConditionOnReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 430, 234, 33));

        DamegeFees.setBackground(new java.awt.Color(255, 255, 255));
        DamegeFees.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        DamegeFees.setForeground(new java.awt.Color(0, 102, 255));
        DamegeFees.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Good – Rs.0", "Slightly Damaged – Rs.500 to Rs.1500", "Heavily Damaged – Rs.2000 to Rs.3000", "Missing Pages – Rs.3500 to Rs.4000", "Lost – Full replacement cost" }));
        DamegeFees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DamegeFeesActionPerformed(evt);
            }
        });
        getContentPane().add(DamegeFees, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 470, 234, 35));

        Save.setBackground(new java.awt.Color(51, 153, 255));
        Save.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Save.setForeground(new java.awt.Color(255, 255, 255));
        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });
        getContentPane().add(Save, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 530, 234, 32));

        Close.setBackground(new java.awt.Color(255, 51, 51));
        Close.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Close.setForeground(new java.awt.Color(255, 255, 255));
        Close.setText("Close");
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });
        getContentPane().add(Close, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 570, 234, 32));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("BookWise | Library Management System");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 660, -1, -1));

        ReturnedBooksTable.setBackground(new java.awt.Color(51, 153, 255));
        ReturnedBooksTable.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        ReturnedBooksTable.setForeground(new java.awt.Color(255, 255, 255));
        ReturnedBooksTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9"
            }
        ));
        ReturnedBooksTable.setGridColor(new java.awt.Color(255, 255, 255));
        ReturnedBooksTable.setIntercellSpacing(new java.awt.Dimension(2, 2));
        ReturnedBooksTable.setSelectionBackground(new java.awt.Color(255, 51, 51));
        ReturnedBooksTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        ReturnedBooksTable.setShowGrid(true);
        ReturnedBooksTable.setSurrendersFocusOnKeystroke(true);
        ReturnedBooksTable.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                ReturnedBooksTableAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(ReturnedBooksTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, 690, 390));

        SearchField1.setBackground(new java.awt.Color(255, 255, 255));
        SearchField1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        SearchField1.setForeground(new java.awt.Color(0, 102, 255));
        SearchField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchField1ActionPerformed(evt);
            }
        });
        getContentPane().add(SearchField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 160, 230, 30));

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
        getContentPane().add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 160, 220, 30));

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
        getContentPane().add(ShowAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 160, 220, 30));

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
        ReturnedBooksNav.setText("      Library Dashboard");
        ReturnedBooksNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReturnedBooksNavMouseClicked(evt);
            }
        });
        getContentPane().add(ReturnedBooksNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(1012, 56, -1, -1));

        LibraryDashboardNav.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        LibraryDashboardNav.setForeground(new java.awt.Color(255, 255, 255));
        LibraryDashboardNav.setText("  Returned Books");
        LibraryDashboardNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LibraryDashboardNavMouseClicked(evt);
            }
        });
        getContentPane().add(LibraryDashboardNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(503, 36, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon("H:\\JAVA Projects\\LMS_new\\public\\large screen.jpg")); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setupReturnedBookListener() {
    // Add listener for StudentID
    StudentID.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            fetchReturnedBook();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            fetchReturnedBook();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            fetchReturnedBook();
        }
    });

    // Add listener for BookID
    BookID.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            fetchReturnedBook();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            fetchReturnedBook();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            fetchReturnedBook();
        }
    });
}

private void fetchReturnedBook() {
    String studentID = StudentID.getText().trim();
    String bookID = BookID.getText().trim();
    
    // Clear all fields if either ID is empty
    if (studentID.isEmpty() || bookID.isEmpty()) {
        clearFields();
        return;
    }

    try {
        Connection con = ConnectionProvider.getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(this, "Database connection failed!");
            return;
        }

        String sql = "SELECT student_name, book_name, issued_date, due_date " +
                    "FROM issued_books " +
                    "WHERE student_id = ? AND book_id = ?";
                    
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, studentID);
        pst.setString(2, bookID);
        
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String studentName = rs.getString("student_name");
            String bookName = rs.getString("book_name");
            
            // Convert the timestamp to date only format
            java.sql.Date issuedDate = rs.getDate("issued_date");
            java.sql.Date dueDate = rs.getDate("due_date");
            
            // Create a date formatter
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            
            // Format the dates
            String formattedIssuedDate = issuedDate != null ? dateFormat.format(issuedDate) : "";
            String formattedDueDate = dueDate != null ? dateFormat.format(dueDate) : "";

            // Set the values to the fields
            StudentName.setText(studentName);
            BookName.setText(bookName);
            IssueDate.setText(formattedIssuedDate);
            DueDate.setText(formattedDueDate);
        } else {
            // Clear fields if no matching record is found
            clearFields();
//            JOptionPane.showMessageDialog(this, 
//                "No issued book found for Student ID: " + studentID + 
//                " and Book ID: " + bookID);
        }

        pst.close();
        rs.close();
        con.close();
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        System.out.println("SQL State: " + e.getSQLState());
        System.out.println("Error Code: " + e.getErrorCode());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        e.printStackTrace();
    }
}

// Helper method to clear all fields
private void clearFields() {
    StudentName.setText("");
    BookName.setText("");
    IssueDate.setText("");
    DueDate.setText("");
    ReturnDate.setDate(null);
    ConditionOnReturn.setSelectedIndex(0);
    DamegeFees.setSelectedIndex(0);
}
    
    private void fetchAndDisplayReturnedBooks() {
    try {
        Connection con = ConnectionProvider.getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(this, "Database connection failed!");
            return;
        }

        String sql = "SELECT  student_id, student_name, book_id, book_name, " +
                    "issue_date, due_date, return_date, condition_on_return, damage_fees " +
                    "FROM returned_books ORDER BY return_date DESC";
        
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        // Create a DefaultTableModel with column names
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Student ID", "Student Name", "Book ID", "Book Name", 
                "Issue Date", "Due Date", "Return Date", "Condition", "Damage Fees"
            }
        ) {
            // Make cells non-editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Set the new model to the table
        ReturnedBooksTable.setModel(model);

        // Format dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Add rows to the table model
        while (rs.next()) {
            // Get values from ResultSet
            String studentId = rs.getString("student_id");
            String studentName = rs.getString("student_name");
            String bookId = rs.getString("book_id");
            String bookName = rs.getString("book_name");
            
            // Format dates
            java.sql.Date issueDate = rs.getDate("issue_date");
            java.sql.Date dueDate = rs.getDate("due_date");
            java.sql.Date returnDate = rs.getDate("return_date");
            
            String formattedIssueDate = issueDate != null ? dateFormat.format(issueDate) : "";
            String formattedDueDate = dueDate != null ? dateFormat.format(dueDate) : "";
            String formattedReturnDate = returnDate != null ? dateFormat.format(returnDate) : "";
            
            String condition = rs.getString("condition_on_return");
            String damageFees = rs.getString("damage_fees");

            // Add row to table model
            model.addRow(new Object[]{
                studentId, studentName, bookId, bookName,
                formattedIssueDate, formattedDueDate, formattedReturnDate,
                condition, damageFees
            });
        }

//        // Set column widths for better visibility
//        IssuedBooksTable.getColumnModel().getColumn(0).setPreferredWidth(80);  // Student ID
//        IssuedBooksTable.getColumnModel().getColumn(1).setPreferredWidth(150); // Student Name
//        IssuedBooksTable.getColumnModel().getColumn(2).setPreferredWidth(80);  // Book ID
//        IssuedBooksTable.getColumnModel().getColumn(3).setPreferredWidth(150); // Book Name
//        IssuedBooksTable.getColumnModel().getColumn(4).setPreferredWidth(100); // Issue Date
//        IssuedBooksTable.getColumnModel().getColumn(5).setPreferredWidth(100); // Due Date
//        IssuedBooksTable.getColumnModel().getColumn(6).setPreferredWidth(100); // Return Date
//        IssuedBooksTable.getColumnModel().getColumn(7).setPreferredWidth(120); // Condition
//        IssuedBooksTable.getColumnModel().getColumn(8).setPreferredWidth(150); // Damage Fees

        // Close resources
        rs.close();
        pst.close();
        con.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        System.out.println("SQL State: " + e.getSQLState());
        System.out.println("Error Code: " + e.getErrorCode());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        e.printStackTrace();
    }
}
    
    private void clearSearch() {
        SearchField1.setText("");
        fetchAndDisplayReturnedBooks();
    }
    
    private void DamegeFeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DamegeFeesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DamegeFeesActionPerformed

    private void ConditionOnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConditionOnReturnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ConditionOnReturnActionPerformed

    private void StudentIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StudentIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StudentIDActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
      try {
        // Get all values from fields
        String studentID = StudentID.getText();
        String studentName = StudentName.getText();
        String bookID = BookID.getText();
        String bookName = BookName.getText();
        String issueDate = IssueDate.getText();
        String dueDate = DueDate.getText();
        
        // Get return date from JDateChooser
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String returnDate = dateFormat.format(ReturnDate.getDate());
        
        // Get selected values from combo boxes
        String condition = ConditionOnReturn.getSelectedItem().toString();
        String damageFees = DamegeFees.getSelectedItem().toString();

        // Check if connection is successful
        Connection con = ConnectionProvider.getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(this, "Database connection failed!");
            return;
        }

        // Prepare SQL statement for insertion
        String sql = "INSERT INTO returned_books (student_id, student_name, book_id, book_name, " +
                    "issue_date, due_date, return_date, condition_on_return, damage_fees) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement pst = con.prepareStatement(sql);
        
        // Set values in prepared statement
        pst.setString(1, studentID);
        pst.setString(2, studentName);
        pst.setString(3, bookID);
        pst.setString(4, bookName);
        pst.setString(5, issueDate);
        pst.setString(6, dueDate);
        pst.setString(7, returnDate);
        pst.setString(8, condition);
        pst.setString(9, damageFees);

        // Execute the insert
        pst.executeUpdate();
        
        // Show success message
        JOptionPane.showMessageDialog(this, "Book return recorded successfully!");
        
        // Clear all fields
        StudentID.setText("");
        StudentName.setText("");
        BookID.setText("");
        BookName.setText("");
        IssueDate.setText("");
        DueDate.setText("");
        ReturnDate.setDate(null);
        ConditionOnReturn.setSelectedIndex(0);
        DamegeFees.setSelectedIndex(0);
        
        
        // Refresh the table
        fetchAndDisplayReturnedBooks();
        
        // Close the prepared statement
        pst.close();
        con.close();
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        System.out.println("SQL State: " + e.getSQLState());
        System.out.println("Error Code: " + e.getErrorCode());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        e.printStackTrace();
    }
    }//GEN-LAST:event_SaveActionPerformed

    private void BookIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BookIDActionPerformed

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        setVisible(false);
        new home().setVisible(true);
    }//GEN-LAST:event_CloseActionPerformed

    private void ReturnedBooksTableAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_ReturnedBooksTableAncestorAdded
        fetchAndDisplayReturnedBooks();
    }//GEN-LAST:event_ReturnedBooksTableAncestorAdded

    private void SearchField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchField1ActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        String searchTerm = SearchField1.getText().trim();

    if (searchTerm.isEmpty()) {
        // If search field is empty, show all records
        fetchAndDisplayReturnedBooks();
        return;
    }

    try {
        Connection con = ConnectionProvider.getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(this, "Database connection failed!");
            return;
        }

        // Fixed SQL query to search in returned_books table and match the table structure
        String sql = "SELECT student_id, student_name, book_id, book_name, " +
                    "issue_date, due_date, return_date, condition_on_return, damage_fees " +
                    "FROM returned_books " +
                    "WHERE student_id LIKE ? OR student_name LIKE ? OR book_id LIKE ? OR book_name LIKE ?";

        PreparedStatement pst = con.prepareStatement(sql);

        // Set search parameters with wildcards for partial matching
        String searchPattern = "%" + searchTerm + "%";
        pst.setString(1, searchPattern);
        pst.setString(2, searchPattern);
        pst.setString(3, searchPattern);
        pst.setString(4, searchPattern);

        ResultSet rs = pst.executeQuery();

        // Get the table model and clear existing rows
        DefaultTableModel model = (DefaultTableModel) ReturnedBooksTable.getModel();
        model.setRowCount(0);

        // Format dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        boolean found = false;
        while (rs.next()) {
            found = true;
            
            // Format dates properly
            java.sql.Date issueDate = rs.getDate("issue_date");
            java.sql.Date dueDate = rs.getDate("due_date");
            java.sql.Date returnDate = rs.getDate("return_date");
            
            String formattedIssueDate = issueDate != null ? dateFormat.format(issueDate) : "";
            String formattedDueDate = dueDate != null ? dateFormat.format(dueDate) : "";
            String formattedReturnDate = returnDate != null ? dateFormat.format(returnDate) : "";

            // Add row with all the correct columns
            Object[] row = {
                rs.getString("student_id"),
                rs.getString("student_name"),
                rs.getString("book_id"),
                rs.getString("book_name"),
                formattedIssueDate,
                formattedDueDate,
                formattedReturnDate,
                rs.getString("condition_on_return"),
                rs.getString("damage_fees")
            };
            model.addRow(row);
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "No records found for: " + searchTerm);
            // Optionally show all records after showing no results message
            fetchAndDisplayReturnedBooks();
        }

        // Close connections
        rs.close();
        pst.close();
        con.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error searching data: " + e.getMessage());
        e.printStackTrace();
    }
    }//GEN-LAST:event_SearchActionPerformed

    private void ShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllActionPerformed
        clearSearch();
    }//GEN-LAST:event_ShowAllActionPerformed

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
        new dashBoard().setVisible(true);
    }//GEN-LAST:event_ReturnedBooksNavMouseClicked

    private void LibraryDashboardNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LibraryDashboardNavMouseClicked
        //       setVisible(false);
        //        new dashBoard().setVisible(true);
    }//GEN-LAST:event_LibraryDashboardNavMouseClicked

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
            java.util.logging.Logger.getLogger(returnedBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(returnedBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(returnedBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(returnedBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new returnedBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddNewBookNav;
    private javax.swing.JLabel AddNewMemberNav;
    private javax.swing.JTextField BookID;
    private javax.swing.JTextField BookName;
    private javax.swing.JButton Close;
    private javax.swing.JComboBox<String> ConditionOnReturn;
    private javax.swing.JComboBox<String> DamegeFees;
    private javax.swing.JTextField DueDate;
    private javax.swing.JTextField IssueDate;
    private javax.swing.JLabel IssuedBooksNav;
    private javax.swing.JLabel LibraryDashboardNav;
    private com.toedter.calendar.JDateChooser ReturnDate;
    private javax.swing.JLabel ReturnedBooksNav;
    private javax.swing.JTable ReturnedBooksTable;
    private javax.swing.JButton Save;
    private javax.swing.JButton Search;
    private javax.swing.JTextField SearchField1;
    private javax.swing.JButton ShowAll;
    private javax.swing.JTextField StudentID;
    private javax.swing.JTextField StudentName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
