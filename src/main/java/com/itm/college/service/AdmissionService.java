package com.itm.college.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itm.college.model.Student;
import com.itm.college.repository.StudentRepository;
import java.util.List;

@Service
public class AdmissionService {

	@Autowired
	private StudentRepository studentRepository;

	public Student registerStudent(Student student) {
		if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
			throw new RuntimeException("Email already exists");
			
		}
		return studentRepository.save(student);
	}

	public Student getStudentById(String id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Student not found"));
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public List<Student> searchStudentsByName(String name) {
		if (name == null || name.isBlank()) {
			return getAllStudents();
		}
		return studentRepository.findByNameContainingIgnoreCase(name);
	}

	public void updateStudent(String id, Student studentDetails) {
		Student student = getStudentById(id);
		student.setName(studentDetails.getName());
		student.setEmail(studentDetails.getEmail());
		student.setPhone(studentDetails.getPhone());
		student.setCourse(studentDetails.getCourse());
		student.setAddress(studentDetails.getAddress());
		student.setFees(studentDetails.getFees());
		student.setStatus(studentDetails.getStatus());
		if(studentDetails.getProfilePhoto() != null) {
			student.setProfilePhoto(studentDetails.getProfilePhoto());
		}
		studentRepository.save(student);
	}

	public void uploadProfilePhoto(String id, byte[] photoData) {
		Student student = getStudentById(id);
		student.setProfilePhoto(photoData);
		studentRepository.save(student);
	}

	public void deleteStudent(String id) {
		Student student = getStudentById(id);
		studentRepository.delete(student);
	}
}
