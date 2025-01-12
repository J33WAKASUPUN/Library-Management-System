package view;

import controller.books_controller;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;


public class books_view extends javax.swing.JFrame {
    
    public JTextField getBookTitle() {
        return BookTitle;
    }

    public void setBookTitle(JTextField title) {
        this.BookTitle=title;
    }
    
    public JTextField getAuthor() {
        return Author;
    }

    public void setAuthor(JTextField authorName) {
        this.Author=authorName;
    }

    public JTextField getPublicationYear() {
        return PublicationYear;
    }

    public void setPublicationYear(JTextField year) {
        this.PublicationYear=year;
    }

    public JTextField getLanguage() {
        return Language;
    }

    public void setLanguage(JTextField lang) {
        this.Language=lang;
    }

    public JTextField getCopiesAvailable() {
        return CopiesAvailable;
    }

    public void setCopiesAvailable(JTextField copies) {
        this.CopiesAvailable=copies;
    }

    public JComboBox<String> getGenre() {
        return Genre;
    }

    public void setGenre(JComboBox<String> genreValue) {
        this.Genre=genreValue;
    }

    public JComboBox<String> getFormat() {
        return Formate;
    }

    public void setFormat(JComboBox<String> formatValue) {
       this.Formate=formatValue;
    }

      public JTextField getSearchField() {
        return SearchField;
    }

    public void setSearchField(JTextField searchField) {
        this.SearchField = searchField;
    }
    
    public JButton getSaveButton() {
        return save;
    }

    public void setSaveButton(JButton saveButton) {
        this.save = saveButton;
    }

    public JTable getBooksTable() {
        return BooksTable;
    }

    public void setBooksTable(JTable studentsTable) {
        this.BooksTable = studentsTable;
    }

    public books_view() {
        initComponents();
        
        books_controller controller = new books_controller(this);
        controller.setupTable();
        controller.fetchAndDisplayBooks("active");
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
//     LibraryDashboardNav.addMouseListener(mouseListener);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BookTitle = new javax.swing.JTextField();
        Author = new javax.swing.JTextField();
        PublicationYear = new javax.swing.JTextField();
        Language = new javax.swing.JTextField();
        CopiesAvailable = new javax.swing.JTextField();
        Genre = new javax.swing.JComboBox<>();
        Formate = new javax.swing.JComboBox<>();
        save = new javax.swing.JButton();
        close = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        BooksTable = new javax.swing.JTable();
        SearchField = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        Remove = new javax.swing.JButton();
        ShowAll = new javax.swing.JButton();
        AddNewMemberNav = new javax.swing.JLabel();
        AddNewBookNav = new javax.swing.JLabel();
        IssuedBooksNav = new javax.swing.JLabel();
        ReturnedBooksNav = new javax.swing.JLabel();
        LibraryDashboardNav = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BookTitle.setBackground(new java.awt.Color(255, 255, 255));
        BookTitle.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BookTitle.setForeground(new java.awt.Color(0, 102, 255));
        BookTitle.setCaretColor(new java.awt.Color(0, 102, 255));
        getContentPane().add(BookTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, 234, 30));

        Author.setBackground(new java.awt.Color(255, 255, 255));
        Author.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Author.setForeground(new java.awt.Color(0, 102, 255));
        Author.setCaretColor(new java.awt.Color(0, 102, 255));
        getContentPane().add(Author, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 234, 29));

        PublicationYear.setBackground(new java.awt.Color(255, 255, 255));
        PublicationYear.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        PublicationYear.setForeground(new java.awt.Color(0, 102, 255));
        PublicationYear.setCaretColor(new java.awt.Color(0, 102, 255));
        PublicationYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PublicationYearActionPerformed(evt);
            }
        });
        getContentPane().add(PublicationYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 270, 234, 31));

        Language.setBackground(new java.awt.Color(255, 255, 255));
        Language.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Language.setForeground(new java.awt.Color(0, 102, 255));
        Language.setCaretColor(new java.awt.Color(0, 102, 255));
        Language.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LanguageActionPerformed(evt);
            }
        });
        getContentPane().add(Language, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 310, 234, 31));

        CopiesAvailable.setBackground(new java.awt.Color(255, 255, 255));
        CopiesAvailable.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        CopiesAvailable.setForeground(new java.awt.Color(0, 102, 255));
        CopiesAvailable.setCaretColor(new java.awt.Color(0, 102, 255));
        CopiesAvailable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CopiesAvailableActionPerformed(evt);
            }
        });
        getContentPane().add(CopiesAvailable, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 350, 234, 31));

        Genre.setBackground(new java.awt.Color(255, 255, 255));
        Genre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Genre.setForeground(new java.awt.Color(0, 102, 255));
        Genre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Software Development", "Software Architecture", "Computer Science", "Web Development", "Programming", "Database", "Networking", "Artificial Intelligence", "Machine Learning", "DevOps", "Cloud Computing", "Data Science", "Operating Systems", "Cybersecurity", "Version Control", "Computer Architecture", "Java Development", "Computer Graphics", "Big Data", "Fiction", "Fantasy", "Science Fiction", "Mystery", "Thriller", "Romance", "Historical Fiction", "Non-Fiction", "Biography", "Self-Help", "Business", "Science", "Technology", "History", "Philosophy", "Poetry", "Drama", "Children's Literature" }));
        Genre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenreActionPerformed(evt);
            }
        });
        getContentPane().add(Genre, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, 234, 34));

        Formate.setBackground(new java.awt.Color(255, 255, 255));
        Formate.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Formate.setForeground(new java.awt.Color(0, 102, 255));
        Formate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hardcover", "Paperback", "E-Book", "Audio Book", "Digital PDF", "Large Print", "Library Binding" }));
        Formate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FormateActionPerformed(evt);
            }
        });
        getContentPane().add(Formate, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 435, 234, 35));

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
        getContentPane().add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 500, 234, 30));

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
        getContentPane().add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 540, 234, 30));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("BookWise | Library Management System");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 660, -1, -1));

        BooksTable.setBackground(new java.awt.Color(51, 153, 255));
        BooksTable.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        BooksTable.setForeground(new java.awt.Color(255, 255, 255));
        BooksTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        BooksTable.setGridColor(new java.awt.Color(255, 255, 255));
        BooksTable.setIntercellSpacing(new java.awt.Dimension(2, 2));
        BooksTable.setSelectionBackground(new java.awt.Color(255, 51, 51));
        BooksTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        BooksTable.setShowGrid(true);
        BooksTable.setSurrendersFocusOnKeystroke(true);
        BooksTable.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                BooksTableAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(BooksTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, 620, -1));

        SearchField.setBackground(new java.awt.Color(255, 255, 255));
        SearchField.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        SearchField.setForeground(new java.awt.Color(0, 102, 255));
        SearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchFieldActionPerformed(evt);
            }
        });
        getContentPane().add(SearchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 130, 200, 30));

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
        getContentPane().add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 130, 130, 30));

        Remove.setBackground(new java.awt.Color(255, 51, 51));
        Remove.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Remove.setForeground(new java.awt.Color(255, 255, 255));
        Remove.setText("Remove");
        Remove.setBorder(null);
        Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveActionPerformed(evt);
            }
        });
        getContentPane().add(Remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 130, 130, 30));

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
        getContentPane().add(ShowAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 130, 130, 30));

        AddNewMemberNav.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        AddNewMemberNav.setForeground(new java.awt.Color(0, 153, 255));
        AddNewMemberNav.setText("Add New Member");
        AddNewMemberNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddNewMemberNavMouseClicked(evt);
            }
        });
        getContentPane().add(AddNewMemberNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, -1, -1));

        AddNewBookNav.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        AddNewBookNav.setForeground(new java.awt.Color(0, 153, 255));
        AddNewBookNav.setText(" Library Statistics ");
        AddNewBookNav.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                AddNewBookNavAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        AddNewBookNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddNewBookNavMouseClicked(evt);
            }
        });
        getContentPane().add(AddNewBookNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, -1, -1));

        IssuedBooksNav.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        IssuedBooksNav.setForeground(new java.awt.Color(0, 153, 255));
        IssuedBooksNav.setText("Issued Books");
        IssuedBooksNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IssuedBooksNavMouseClicked(evt);
            }
        });
        getContentPane().add(IssuedBooksNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 60, -1, -1));

        ReturnedBooksNav.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ReturnedBooksNav.setForeground(new java.awt.Color(0, 153, 255));
        ReturnedBooksNav.setText("        Returned Books");
        ReturnedBooksNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReturnedBooksNavMouseClicked(evt);
            }
        });
        getContentPane().add(ReturnedBooksNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 60, -1, -1));

        LibraryDashboardNav.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        LibraryDashboardNav.setForeground(new java.awt.Color(255, 255, 255));
        LibraryDashboardNav.setText("  Add New Book");
        LibraryDashboardNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LibraryDashboardNavMouseClicked(evt);
            }
        });
        getContentPane().add(LibraryDashboardNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Book title");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Author");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Publication Year");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Language");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Genre");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 400, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Format");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 440, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Copies Available");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon("H:\\JAVA Projects\\Jeewaka Java assignment\\LMS_new\\public\\large screen.jpg")); // NOI18N
        jLabel11.setText("jLabel11");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PublicationYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PublicationYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PublicationYearActionPerformed

    private void LanguageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LanguageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LanguageActionPerformed

    private void CopiesAvailableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CopiesAvailableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CopiesAvailableActionPerformed

    private void GenreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GenreActionPerformed

    private void FormateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FormateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FormateActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        books_controller controller = new books_controller(this);
        controller.handleSaveOrUpdate();
    }//GEN-LAST:event_saveActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        setVisible(false);
        new home_view().setVisible(true);
    }//GEN-LAST:event_closeActionPerformed

    private void BooksTableAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_BooksTableAncestorAdded

    }//GEN-LAST:event_BooksTableAncestorAdded

    private void SearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchFieldActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        SearchField.setText("");
        books_controller controller = new books_controller(this);
        controller.handleSearch();
    }//GEN-LAST:event_SearchActionPerformed

    private void RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveActionPerformed
        books_controller controller = new books_controller(this);
        controller.removeBooks("inactive");      
    }//GEN-LAST:event_RemoveActionPerformed

    private void ShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllActionPerformed
        books_controller controller = new books_controller(this);
        controller.fetchAndDisplayBooks("active");
    }//GEN-LAST:event_ShowAllActionPerformed

    private void AddNewMemberNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddNewMemberNavMouseClicked
        setVisible(false);
        new students_view().setVisible(true);
    }//GEN-LAST:event_AddNewMemberNavMouseClicked

    private void AddNewBookNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddNewBookNavMouseClicked
        setVisible(false);
        new statistics_view().setVisible(true);
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
//        setVisible(false);
//        new statistics_view().setVisible(true);
    }//GEN-LAST:event_LibraryDashboardNavMouseClicked

    private void AddNewBookNavAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_AddNewBookNavAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_AddNewBookNavAncestorAdded

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
            java.util.logging.Logger.getLogger(books_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(books_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(books_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(books_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new books_view().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddNewBookNav;
    private javax.swing.JLabel AddNewMemberNav;
    private javax.swing.JTextField Author;
    private javax.swing.JTextField BookTitle;
    private javax.swing.JTable BooksTable;
    private javax.swing.JTextField CopiesAvailable;
    private javax.swing.JComboBox<String> Formate;
    private javax.swing.JComboBox<String> Genre;
    private javax.swing.JLabel IssuedBooksNav;
    private javax.swing.JTextField Language;
    private javax.swing.JLabel LibraryDashboardNav;
    private javax.swing.JTextField PublicationYear;
    private javax.swing.JButton Remove;
    private javax.swing.JLabel ReturnedBooksNav;
    private javax.swing.JButton Search;
    private javax.swing.JTextField SearchField;
    private javax.swing.JButton ShowAll;
    private javax.swing.JButton close;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton save;
    // End of variables declaration//GEN-END:variables
}
