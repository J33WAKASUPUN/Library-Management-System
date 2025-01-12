package view;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import javax.imageio.ImageIO;
import controller.students_controller;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

public class students_view extends javax.swing.JFrame {

    public JTextField getStudentID() {
        return StudentID;
    }

    public void setStudentID(JTextField studentID) {
        this.StudentID = studentID;
    }

    public JTextField getStudentName() {
        return StudentName;
    }

    public void setStudentName(JTextField studentName) {
        this.StudentName = studentName;
    }

    public JTextField getAddress() {
        return Address;
    }

    public void setAddress(JTextField address) {
        this.Address = address;
    }

    public JTextField getTPNumber() {
        return TPNumber;
    }

    public void setTPNumber(JTextField tpNumber) {
        this.TPNumber = tpNumber;
    }

    public JComboBox<String> getCourse() {
        return Course;
    }

    public void setCourse(JComboBox<String> course) {
        this.Course = course;
    }

    public JDateChooser getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(JDateChooser expirationDate) {
        this.ExpirationDate = expirationDate;
    }

    public JButton getSaveButton() {
        return save;
    }

    public void setSaveButton(JButton saveButton) {
        this.save = saveButton;
    }

    public JTable getStudentsTable() {
        return StudentsTable;
    }

    public void setStudentsTable(JTable studentsTable) {
        this.StudentsTable = studentsTable;
    }

    public JTextField getSearchField() {
        return SearchField;
    }

    public void setSearchField(JTextField searchField) {
        this.SearchField = searchField;
    }

    public students_view() {
        initComponents();

        students_controller controller = new students_controller(this);
        controller.setupTable();
        controller.fetchAndDisplayStudents("active");
        controller.setupTableSelectionListener();

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
        //    LibraryDashboardNav.addMouseListener(mouseListener);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        StudentID = new javax.swing.JTextField();
        StudentName = new javax.swing.JTextField();
        Address = new javax.swing.JTextField();
        TPNumber = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        save = new javax.swing.JButton();
        close = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        StudentsTable = new javax.swing.JTable();
        SearchField = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        SyncSate = new javax.swing.JButton();
        Inactive = new javax.swing.JButton();
        AddNewMemberNav = new javax.swing.JLabel();
        AddNewBookNav = new javax.swing.JLabel();
        IssuedBooksNav = new javax.swing.JLabel();
        ReturnedBooksNav = new javax.swing.JLabel();
        LibraryDashboardNav = new javax.swing.JLabel();
        Active = new javax.swing.JButton();
        Course = new javax.swing.JComboBox<>();
        ExpirationDate = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        StudentID.setBackground(new java.awt.Color(255, 255, 255));
        StudentID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        StudentID.setForeground(new java.awt.Color(0, 102, 255));
        StudentID.setCaretColor(new java.awt.Color(0, 102, 255));
        getContentPane().add(StudentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 234, 31));

        StudentName.setBackground(new java.awt.Color(255, 255, 255));
        StudentName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        StudentName.setForeground(new java.awt.Color(0, 102, 255));
        StudentName.setCaretColor(new java.awt.Color(0, 102, 255));
        getContentPane().add(StudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, 234, 30));

        Address.setBackground(new java.awt.Color(255, 255, 255));
        Address.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Address.setForeground(new java.awt.Color(0, 102, 255));
        Address.setCaretColor(new java.awt.Color(0, 102, 255));
        Address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddressActionPerformed(evt);
            }
        });
        getContentPane().add(Address, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 234, 29));

        TPNumber.setBackground(new java.awt.Color(255, 255, 255));
        TPNumber.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TPNumber.setForeground(new java.awt.Color(0, 102, 255));
        TPNumber.setCaretColor(new java.awt.Color(0, 102, 255));
        getContentPane().add(TPNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, 234, 28));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText(" BookWise | Library Management System");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 660, -1, -1));

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
        getContentPane().add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, 234, 30));

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
        getContentPane().add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 530, 234, 30));

        StudentsTable.setBackground(new java.awt.Color(51, 153, 255));
        StudentsTable.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        StudentsTable.setForeground(new java.awt.Color(255, 255, 255));
        StudentsTable.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        StudentsTable.setGridColor(new java.awt.Color(255, 255, 255));
        StudentsTable.setIntercellSpacing(new java.awt.Dimension(2, 2));
        StudentsTable.setSelectionBackground(new java.awt.Color(255, 51, 51));
        StudentsTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        StudentsTable.setShowGrid(true);
        StudentsTable.setSurrendersFocusOnKeystroke(true);
        StudentsTable.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                StudentsTableAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(StudentsTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 200, 620, 390));

        SearchField.setBackground(new java.awt.Color(255, 255, 255));
        SearchField.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        SearchField.setForeground(new java.awt.Color(0, 102, 255));
        SearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchFieldActionPerformed(evt);
            }
        });
        getContentPane().add(SearchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, 220, 30));

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
        getContentPane().add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 160, 90, 30));

        SyncSate.setBackground(new java.awt.Color(255, 51, 51));
        SyncSate.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        SyncSate.setForeground(new java.awt.Color(255, 255, 255));
        SyncSate.setText("SyncSate");
        SyncSate.setBorder(null);
        SyncSate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SyncSateActionPerformed(evt);
            }
        });
        getContentPane().add(SyncSate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 160, 90, 30));

        Inactive.setBackground(new java.awt.Color(51, 153, 255));
        Inactive.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Inactive.setForeground(new java.awt.Color(255, 255, 255));
        Inactive.setText("Inactive");
        Inactive.setBorder(null);
        Inactive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InactiveActionPerformed(evt);
            }
        });
        getContentPane().add(Inactive, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 160, 90, 30));

        AddNewMemberNav.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        AddNewMemberNav.setForeground(new java.awt.Color(0, 153, 255));
        AddNewMemberNav.setText(" Library Statistics ");
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
        IssuedBooksNav.setText(" Issued Books");
        IssuedBooksNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IssuedBooksNavMouseClicked(evt);
            }
        });
        getContentPane().add(IssuedBooksNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(869, 56, -1, -1));

        ReturnedBooksNav.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ReturnedBooksNav.setForeground(new java.awt.Color(0, 153, 255));
        ReturnedBooksNav.setText("      Returned Books");
        ReturnedBooksNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReturnedBooksNavMouseClicked(evt);
            }
        });
        getContentPane().add(ReturnedBooksNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(1012, 56, -1, -1));

        LibraryDashboardNav.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        LibraryDashboardNav.setForeground(new java.awt.Color(255, 255, 255));
        LibraryDashboardNav.setText(" Add New Member");
        LibraryDashboardNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LibraryDashboardNavMouseClicked(evt);
            }
        });
        getContentPane().add(LibraryDashboardNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(503, 36, -1, -1));

        Active.setBackground(new java.awt.Color(51, 153, 255));
        Active.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Active.setForeground(new java.awt.Color(255, 255, 255));
        Active.setText("Active");
        Active.setBorder(null);
        Active.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActiveActionPerformed(evt);
            }
        });
        getContentPane().add(Active, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 160, 90, 30));

        Course.setBackground(new java.awt.Color(255, 255, 255));
        Course.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Course.setForeground(new java.awt.Color(0, 102, 204));
        Course.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Introduction to Computer Science", "Programming Fundamentals", "Data Structures and Algorithms", "Database Management Systems", "Computer Networks", "Operating Systems", "Web Development", "Software Engineering", "Object-Oriented Programming", "Cloud Computing", "Cybersecurity Fundamentals", "Data Science", "Machine Learning", "Artificial Intelligence", "Mobile App Development", "Big Data Analytics", "Blockchain Technology", "Computer Architecture", "Digital Forensics", "Internet of Things (IoT)", "Human-Computer Interaction", "Information Security", "Network Security", "Cloud Architecture", "Database Administration", "IT Project Management", "Systems Analysis and Design", "Ethical Hacking", "Data Visualization", "Introduction to Data Analytics", "Python Programming", "Java Programming", "Linux Administration", "DevOps", "Cloud Computing with AWS", "Introduction to SQL", "Software Testing and Quality Assurance", "Front-End Web Development", "Back-End Web Development", "Natural Language Processing", "Virtual Reality Development", "Augmented Reality Development", "Digital Marketing Analytics", "Game Development", "Embedded Systems", "Cryptography", "User Experience (UX) Design", "Information Systems Management", "Business Intelligence", "Computer Graphics", "Parallel and Distributed Computing", "IT Governance and Compliance", "Computational Mathematics", "Software Architecture", "Agile Development", "Quantum Computing", "Cloud Security", "Web Technologies", "IT Risk Management", "Advanced Machine Learning", "Information Retrieval", "IT Service Management", "IT Auditing" }));
        Course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CourseActionPerformed(evt);
            }
        });
        getContentPane().add(Course, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, 234, 28));

        ExpirationDate.setBackground(new java.awt.Color(255, 255, 255));
        ExpirationDate.setForeground(new java.awt.Color(51, 102, 255));
        ExpirationDate.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                ExpirationDateAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(ExpirationDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 430, 235, 30));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Student Index");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Student Name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Address");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("T.P Number");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Expiration Date");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Course");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon("H:\\JAVA Projects\\Jeewaka Java assignment\\LMS_new\\public\\large screen.jpg")); // NOI18N
        jLabel8.setText("jLabel8");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddressActionPerformed

    }//GEN-LAST:event_AddressActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        students_controller controller = new students_controller(this);
        controller.handleSaveOrUpdate();
    }//GEN-LAST:event_saveActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        setVisible(false);
        new home_view().setVisible(true);
    }//GEN-LAST:event_closeActionPerformed

    private void StudentsTableAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_StudentsTableAncestorAdded

    }//GEN-LAST:event_StudentsTableAncestorAdded

    private void SearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchFieldActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        students_controller controller = new students_controller(this);
        controller.handleSearch();
    }//GEN-LAST:event_SearchActionPerformed

    private void SyncSateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SyncSateActionPerformed
        students_controller controller = new students_controller(this);
        try {
            controller.handleStatusChange();
        } catch (SQLException ex) {
            Logger.getLogger(students_view.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SyncSateActionPerformed

    private void InactiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InactiveActionPerformed
        SearchField.setText("");
        students_controller controller = new students_controller(this);
        controller.fetchAndDisplayStudents("inactive");
    }//GEN-LAST:event_InactiveActionPerformed

    private void AddNewMemberNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddNewMemberNavMouseClicked
        setVisible(false);
        new statistics_view().setVisible(true);
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
        //        new newStudents().setVisible(true);
    }//GEN-LAST:event_LibraryDashboardNavMouseClicked

    private void ActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActiveActionPerformed
        SearchField.setText("");
        students_controller controller = new students_controller(this);
        controller.fetchAndDisplayStudents("active");
    }//GEN-LAST:event_ActiveActionPerformed

    private void CourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CourseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CourseActionPerformed

    private void ExpirationDateAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_ExpirationDateAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_ExpirationDateAncestorAdded

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
            java.util.logging.Logger.getLogger(students_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(students_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(students_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(students_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new students_view().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Active;
    private javax.swing.JLabel AddNewBookNav;
    private javax.swing.JLabel AddNewMemberNav;
    private javax.swing.JTextField Address;
    private javax.swing.JComboBox<String> Course;
    private com.toedter.calendar.JDateChooser ExpirationDate;
    private javax.swing.JButton Inactive;
    private javax.swing.JLabel IssuedBooksNav;
    private javax.swing.JLabel LibraryDashboardNav;
    private javax.swing.JLabel ReturnedBooksNav;
    private javax.swing.JButton Search;
    private javax.swing.JTextField SearchField;
    private javax.swing.JTextField StudentID;
    private javax.swing.JTextField StudentName;
    private javax.swing.JTable StudentsTable;
    private javax.swing.JButton SyncSate;
    private javax.swing.JTextField TPNumber;
    private javax.swing.JButton close;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton save;
    // End of variables declaration//GEN-END:variables
}
