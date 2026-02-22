package com.itm.college.dto;

import com.itm.college.model.BookIssueHistory;
import java.util.List;

public class StudentBookHistoryDTO {
    private String studentId;
    private String studentName;
    private String email;
    private String phone;
    private String course;
    private String address;
    private List<BookIssueHistory> issueHistory;
    private Boolean eligibleToBorrow;
    
    // Constructors
    public StudentBookHistoryDTO() {}
    
    // Getters and Setters
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
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getCourse() {
        return course;
    }
    
    public void setCourse(String course) {
        this.course = course;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public List<BookIssueHistory> getIssueHistory() {
        return issueHistory;
    }
    
    public void setIssueHistory(List<BookIssueHistory> issueHistory) {
        this.issueHistory = issueHistory;
    }
    
    public Boolean getEligibleToBorrow() {
        return eligibleToBorrow;
    }
    
    public void setEligibleToBorrow(Boolean eligibleToBorrow) {
        this.eligibleToBorrow = eligibleToBorrow;
    }
}
