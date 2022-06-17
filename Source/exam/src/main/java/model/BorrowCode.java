package model;

public class BorrowCode {
    private String borrowCode;
    private int bookId;
    private int studentId;
    private int status;
    private String borrowStartDate;
    private String borrowEndDate;
    private int currentQuantity;

    public BorrowCode(String borrowCode, int bookId, int studentId, String borrowStartDate, String borrowEndDate, int currentQuantity) {
        this.borrowCode = borrowCode;
        this.bookId = bookId;
        this.studentId = studentId;
        this.borrowStartDate = borrowStartDate;
        this.borrowEndDate = borrowEndDate;
        this.currentQuantity = currentQuantity;
    }

    public BorrowCode() {
    }

    public BorrowCode(String borrowCode, int bookId, int studentId, int status, String borrowStartDate, String borrowEndDate) {
        this.borrowCode = borrowCode;
        this.bookId = bookId;
        this.studentId = studentId;
        this.status = status;
        this.borrowStartDate = borrowStartDate;
        this.borrowEndDate = borrowEndDate;
    }

    public BorrowCode(String borrowCode, int bookId, int studentId, String borrowStartDate, String borrowEndDate) {
        this.borrowCode = borrowCode;
        this.bookId = bookId;
        this.studentId = studentId;
        this.borrowStartDate = borrowStartDate;
        this.borrowEndDate = borrowEndDate;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public String getBorrowCode() {
        return borrowCode;
    }

    public void setBorrowCode(String borrowCode) {
        this.borrowCode = borrowCode;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBorrowStartDate() {
        return borrowStartDate;
    }

    public void setBorrowStartDate(String borrowStartDate) {
        this.borrowStartDate = borrowStartDate;
    }

    public String getBorrowEndDate() {
        return borrowEndDate;
    }

    public void setBorrowEndDate(String borrowEndDate) {
        this.borrowEndDate = borrowEndDate;
    }
}
