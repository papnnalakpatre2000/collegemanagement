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

	@Column(name = "profile_photo", columnDefinition = "LONGBLOB")
	private byte[] profilePhoto;

	@Column(name = "admission_date")
	private LocalDate admissionDate;

	@Column(nullable = false)
	private Double fees;

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
			byte[] profilePhoto, LocalDate admissionDate, Double fees, String status) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.course = course;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.profilePhoto = profilePhoto;
		this.admissionDate = admissionDate;
		this.fees = fees;
		this.status = status;
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
