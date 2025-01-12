package model;

import java.util.Date;

public class issueBooks_model {
    private String issuedId;
    private String studentId;
    private String studentName;
    private String bookId;
    private String bookTitle;
    private Date issuedDate;
    private Date dueDate;

    // Constructor
    public issueBooks_model() {}

    public issueBooks_model(String studentId, String studentName, 
                             String bookId, String bookTitle, 
                             Date issuedDate, Date dueDate)
    {
        this.studentId = studentId;
        this.studentName = studentName;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.issuedDate = issuedDate;
        this.dueDate = dueDate;
    }

    // Getters and Setters
    public String getIssuedId() {
        return issuedId;
    }

    public void setIssuedId(String issuedId) {
        this.issuedId = issuedId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
