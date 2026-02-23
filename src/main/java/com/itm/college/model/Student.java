package com.itm.college.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Year;

@Entity
@Table(name = "students")
public class Student {

	@Id
	@Column(name = "student_id")
	private String id;

	@Column(nullable = false)
	private String name;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private String course;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	
	@Column(name = "address")
	private String address;

	@Column(name = "gender")
	private String gender;

	@Column(name = "profile_photo", columnDefinition = "LONGBLOB")
	private byte[] profilePhoto;

	@Column(name = "admission_date")
	private LocalDate admissionDate;

	@Column(nullable = false)
	private Double fees;
	
	@Column(nullable = false)
	private Double result10;
	
	@Column(nullable = false)
	private Double result12;

	@Column(columnDefinition = "VARCHAR(50) DEFAULT 'PENDING'")
	private String status;
	
	


	public Student() {}

	@PrePersist
	public void generateId() {
		if (this.id == null) {
			int currentYear = Year.now().getValue();
			java.util.Random random = new java.util.Random();
			int randomNumber = random.nextInt(9000) + 1000;
			this.id = currentYear + String.valueOf(randomNumber);
		}
	}

	




	public Student(String name, String email, String phone, String course, LocalDate dateOfBirth, String address,
			String gender, byte[] profilePhoto, LocalDate admissionDate, Double fees, Double result10, Double result12,
			String status) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.course = course;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.gender = gender;
		this.profilePhoto = profilePhoto;
		this.admissionDate = admissionDate;
		this.fees = fees;
		this.result10 = result10;
		this.result12 = result12;
		this.status = status;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Double getResult10() {
		return result10;
	}

	public void setResult10(Double result10) {
		this.result10 = result10;
	}

	public Double getResult12() {
		return result12;
	}

	public void setResult12(Double result12) {
		this.result12 = result12;
	}

	public String getId() { 
		return id; 
	}
	
	public void setId(String id) { 
		this.id = id; 
	}

	public String getName() { 
		return name; 
	}
	
	public void setName(String name) { 
		this.name = name; 
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

	public LocalDate getDateOfBirth() { 
		return dateOfBirth; 
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) { 
		this.dateOfBirth = dateOfBirth; 
	}

	public String getAddress() { 
		return address; 
	}
	
	public void setAddress(String address) { 
		this.address = address; 
	}

	public byte[] getProfilePhoto() { 
		return profilePhoto; 
	}
	
	public void setProfilePhoto(byte[] profilePhoto) { 
		this.profilePhoto = profilePhoto; 
	}

	public LocalDate getAdmissionDate() { 
		return admissionDate; 
	}           
	
	public void setAdmissionDate(LocalDate admissionDate) { 
		this.admissionDate = admissionDate; 
	}

	public Double getFees() { 
		return fees; 
	}
	
	public void setFees(Double fees) { 
		this.fees = fees; 
	}

	public String getStatus() { 
		return status; 
	}
	
	public void setStatus(String status) { 
		this.status = status; 
	}
}
