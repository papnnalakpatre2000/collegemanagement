package com.itm.college.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "library_members")
public class LibraryMember {

    @Id
    private String referenceNo;
    
    private String memberType;
    private String firstName;
    private String surname;
    private String address;
    private String postCode;
    private String mobileNo;
    
    // Book Details
    private String bookId;
    private String bookTitle;
    private String author;
    private LocalDate dateBorrowed;
    private LocalDate dateDue;
    private Integer daysInLoan;
    
    // Constructors
    public LibraryMember() {}
    
    public LibraryMember(String referenceNo, String memberType, String firstName, String surname, 
                         String address, String postCode, String mobileNo) {
        this.referenceNo = referenceNo;
        this.memberType = memberType;
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.postCode = postCode;
        this.mobileNo = mobileNo;
    }
    
    // Getters and Setters
    public String getReferenceNo() {
        return referenceNo;
    }
    
    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }
    
    public String getMemberType() {
        return memberType;
    }
    
    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPostCode() {
        return postCode;
    }
    
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
    
    public String getMobileNo() {
        return mobileNo;
    }
    
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
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
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public LocalDate getDateBorrowed() {
        return dateBorrowed;
    }
    
    public void setDateBorrowed(LocalDate dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }
    
    public LocalDate getDateDue() {
        return dateDue;
    }
    
    public void setDateDue(LocalDate dateDue) {
        this.dateDue = dateDue;
    }
    
    public Integer getDaysInLoan() {
        return daysInLoan;
    }
    
    public void setDaysInLoan(Integer daysInLoan) {
        this.daysInLoan = daysInLoan;
    }
}
