package com.itm.college.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "book_issue_history")
public class BookIssueHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;
    
    private String studentId;
    private String bookId;
    private String bookTitle;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private LocalDate dueDate;
    private Integer daysAllowedToBorrow; // Default 14 days
    private Boolean isReturned; // Default false
    
    // Constructors
    public BookIssueHistory() {}
    
    public BookIssueHistory(String studentId, String bookId, String bookTitle, LocalDate issueDate, Integer daysAllowedToBorrow) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.issueDate = issueDate;
        this.daysAllowedToBorrow = daysAllowedToBorrow;
        this.dueDate = issueDate.plusDays(daysAllowedToBorrow);
        this.isReturned = false;
    }
    
    // Getters and Setters
    public Long getIssueId() {
        return issueId;
    }
    
    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
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
    
    public LocalDate getIssueDate() {
        return issueDate;
    }
    
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
    
    public LocalDate getReturnDate() {
        return returnDate;
    }
    
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    
    public LocalDate getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    
    public Integer getDaysAllowedToBorrow() {
        return daysAllowedToBorrow;
    }
    
    public void setDaysAllowedToBorrow(Integer daysAllowedToBorrow) {
        this.daysAllowedToBorrow = daysAllowedToBorrow;
    }
    
    public Boolean getIsReturned() {
        return isReturned;
    }
    
    public void setIsReturned(Boolean isReturned) {
        this.isReturned = isReturned;
    }
}
