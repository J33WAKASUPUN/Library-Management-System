package view;

import com.toedter.calendar.JDateChooser;
import controller.returnBooks_controller;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class returnBooks_view extends javax.swing.JFrame {
    private returnBooks_controller controller;
    
   public JTextField getStudentID() {
        return StudentID;
    }

    public JTextField getStudentName() {
        return StudentName;
    }

    public JTextField getBookID() {
        return BookID;
    }

    public JTextField getBookName() {
        return BookName;
    }

    public JTextField getIssueDate() {
        return IssueDate;
    }

    public JTextField getDueDate() {
        return DueDate;
    }

    public JDateChooser getReturnDate() {
        return ReturnDate;
    }
    
    

    public JComboBox<String> getConditionOnReturn() {
        return ConditionOnReturn;
    }

    public JComboBox<String> getDamegeFees() {
        return DamegeFees;
    }

    // Setter methods for components
    
    public void setStudentID(String name) {
        StudentID.setText(name);
    }
    
    public void setBookID(String name) {
        BookID.setText(name);
    }
    
    public void setStudentName(String name) {
        StudentName.setText(name);
    }

    public void setBookName(String name) {
        BookName.setText(name);
    }

    public void setIssueDate(String date) {
        IssueDate.setText(date);
    }

    public void setDueDate(String date) {
        DueDate.setText(date);
    }

    public void clearReturnDateField() {
        ReturnDate.setDate(null);
    }

    public void setConditionOnReturnIndex(int index) {
        ConditionOnReturn.setSelectedIndex(index);
    }

    public void setDamageFeesIndex(int index) {
        DamegeFees.setSelectedIndex(index);
    }

    public returnBooks_view() {
        initComponents();
        controller = new returnBooks_controller(this);
        setupReturnedBookListener();
        fetchAndDisplayReturnedBooks();
        setupSearchFunctionality();

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
//     LibraryDashboardNav.addMouseListener(mouseListener);
    }
    
    
    private void setupReturnedBookListener() {
        StudentID.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                controller.fetchReturnedBook(StudentID.getText().trim(), BookID.getText().trim());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                controller.fetchReturnedBook(StudentID.getText().trim(), BookID.getText().trim());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                controller.fetchReturnedBook(StudentID.getText().trim(), BookID.getText().trim());
            }
        });

        BookID.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                controller.fetchReturnedBook(StudentID.getText().trim(), BookID.getText().trim());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                controller.fetchReturnedBook(StudentID.getText().trim(), BookID.getText().trim());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                controller.fetchReturnedBook(StudentID.getText().trim(), BookID.getText().trim());
            }
        });
    }
    // In returnBooks_view class, modify fetchAndDisplayReturnedBooks method


  private void saveReturnedBook() {
    String studentID = StudentID.getText().trim();
    String studentName = StudentName.getText();
    String bookID = BookID.getText().trim();
    String bookName = BookName.getText();
    String issueDate = IssueDate.getText();
    String dueDate = DueDate.getText();

    // Assuming "ReturnDate" is a JDateChooser component
    java.util.Date returnDateObject = ((JDateChooser) ReturnDate).getDate();  
    java.sql.Date returnDateSql = new java.sql.Date(returnDateObject.getTime());

    String condition = (String) ConditionOnReturn.getSelectedItem();
    String damageFees = (String) DamegeFees.getSelectedItem();

    if (controller.saveReturnedBook(studentID, studentName, bookID, bookName, issueDate, dueDate, returnDateSql, condition, damageFees)) {
        javax.swing.JOptionPane.showMessageDialog(this, "Book return recorded successfully!");
        clearFields();
        fetchAndDisplayReturnedBooks();
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Error saving returned book");
    }
}
    
    private void setupSearchFunctionality() {
        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchReturnedBooks();
            }
        });
    }

    private void searchReturnedBooks() {
        String searchTerm = SearchField1.getText().trim().toLowerCase();
        
        if (searchTerm.isEmpty()) {
            fetchAndDisplayReturnedBooks();
            return;
        }

        try {
            DefaultTableModel model = (DefaultTableModel) ReturnedBooksTable.getModel();
            model.setRowCount(0);

            ResultSet rs = controller.fetchAllReturnedBooks();
            
            boolean resultsFound = false;

            while (rs != null && rs.next()) {
                boolean matchFound = 
                    rs.getString("student_id").toLowerCase().contains(searchTerm) ||
                    rs.getString("student_name").toLowerCase().contains(searchTerm) ||
                    rs.getString("book_id").toLowerCase().contains(searchTerm) ||
                    rs.getString("book_name").toLowerCase().contains(searchTerm);

                if (matchFound) {
                    model.addRow(new Object[]{
                        rs.getString("student_id"),
                        rs.getString("student_name"),
                        rs.getString("book_id"),
                        rs.getString("book_name"),
                        rs.getString("issue_date"),
                        rs.getString("due_date"),
                        rs.getString("return_date"),
                        rs.getString("condition_on_return"),
                        rs.getString("damage_fees")
                    });
                    resultsFound = true;
                }
            }

            if (!resultsFound) {
                JOptionPane.showMessageDialog(this, 
                    "No results found for: " + searchTerm, 
                    "Search Results", 
                    JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Error searching returned books: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fetchAndDisplayReturnedBooks() {
        try {
            DefaultTableModel model = (DefaultTableModel) ReturnedBooksTable.getModel();
            model.setRowCount(0);
            
            ResultSet rs = controller.fetchAllReturnedBooks();
            
            while (rs != null && rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("student_id"),
                    rs.getString("student_name"),
                    rs.getString("book_id"),
                    rs.getString("book_name"),
                    rs.getString("issue_date"),
                    rs.getString("due_date"),
                    rs.getString("return_date"),
                    rs.getString("condition_on_return"),
                    rs.getString("damage_fees")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading returned books: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        setStudentID("");
        setBookID("");
        setStudentName("");
        setBookName("");
        setIssueDate("");
        setDueDate("");
        clearReturnDateField();
        setConditionOnReturnIndex(0);
        setDamageFeesIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        StudentID = new javax.swing.JTextField();
        StudentName = new javax.swing.JTextField();
        BookID = new javax.swing.JTextField();
        BookName = new javax.swing.JTextField();
        IssueDate = new javax.swing.JTextField();
        DueDate = new javax.swing.JTextField();
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
        ReturnDate = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        ReturnedBooksNav.setText("      Library Statistics ");
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

        ReturnDate.setBackground(new java.awt.Color(255, 255, 255));
        ReturnDate.setForeground(new java.awt.Color(0, 102, 255));
        getContentPane().add(ReturnDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 392, 230, 30));

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
        jLabel3.setText("Book Title");
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

        jLabel12.setIcon(new javax.swing.ImageIcon("H:\\JAVA Projects\\Jeewaka Java assignment\\LMS_new\\public\\large screen.jpg")); // NOI18N
        jLabel12.setText("jLabel12");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StudentIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StudentIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StudentIDActionPerformed

    private void BookIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BookIDActionPerformed

    private void ConditionOnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConditionOnReturnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ConditionOnReturnActionPerformed

    private void DamegeFeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DamegeFeesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DamegeFeesActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
       saveReturnedBook();
    }//GEN-LAST:event_SaveActionPerformed

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        setVisible(false);
        new home_view().setVisible(true);
    }//GEN-LAST:event_CloseActionPerformed

    private void ReturnedBooksTableAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_ReturnedBooksTableAncestorAdded

    }//GEN-LAST:event_ReturnedBooksTableAncestorAdded

    private void SearchField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchField1ActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        searchReturnedBooks();
    }//GEN-LAST:event_SearchActionPerformed

    private void ShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllActionPerformed
        fetchAndDisplayReturnedBooks();
    }//GEN-LAST:event_ShowAllActionPerformed

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
        new statistics_view().setVisible(true);
    }//GEN-LAST:event_ReturnedBooksNavMouseClicked

    private void LibraryDashboardNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LibraryDashboardNavMouseClicked
//        setVisible(false);
//        new statistics_view().setVisible(true);
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
            java.util.logging.Logger.getLogger(returnBooks_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(returnBooks_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(returnBooks_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(returnBooks_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new returnBooks_view().setVisible(true);
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
    private javax.swing.JLabel jLabel12;
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
