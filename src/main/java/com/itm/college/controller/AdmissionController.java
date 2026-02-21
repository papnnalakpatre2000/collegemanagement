package com.itm.college.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.itm.college.model.Student;
import com.itm.college.service.AdmissionService;
import java.util.List;
import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admission")
public class AdmissionController {

	@Autowired
	private AdmissionService admissionService;

	@PostMapping(value ="/registerStudent",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> registerStudent(@RequestPart("student") Student student,@RequestPart("file") MultipartFile file) throws IOException{
		try {
			student.setProfilePhoto(file.getBytes());
			Student savedStudent = admissionService.registerStudent(student);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Student registered successfully with ID: " + savedStudent.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Registration failed: " + e.getMessage());
		}
	}

	@PostMapping("/uploadProfilePhoto/{id}")
	public ResponseEntity<String> uploadProfilePhoto(@PathVariable String id, @RequestParam("file") MultipartFile file) {
		try {
			if (file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("File is empty");
			}
			admissionService.uploadProfilePhoto(id, file.getBytes());
			return ResponseEntity.ok("Profile photo uploaded successfully for Student ID: " + id);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("File upload failed: " + e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Student not found: " + e.getMessage());
		}
	}

	@GetMapping("/getStudent/{id}")
	public ResponseEntity<?> getStudent(@PathVariable String id) {
		try {
			Student student = admissionService.getStudentById(id);
			return ResponseEntity.ok(student);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Student not found with ID: " + id);
		}
	}

	@GetMapping("/getAllStudents")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> students = admissionService.getAllStudents();
		return ResponseEntity.ok(students);
	}

	@PutMapping("/updateStudent/{id}")
	public ResponseEntity<String> updateStudent(@PathVariable String id, @RequestBody Student student) {
		try {
			admissionService.updateStudent(id, student);
			return ResponseEntity.ok("Student ID: " + id + " updated successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Update failed: " + e.getMessage());
		}
	}

	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable String id) {
		try {
			admissionService.deleteStudent(id);
			return ResponseEntity.ok("Student ID: " + id + " deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Deletion failed: " + e.getMessage());
		}
	}
	
	@GetMapping("/getProfilePhoto/{id}")
	public ResponseEntity<byte[]> getProfilePhoto(@PathVariable String id) {

	    Student student = admissionService.getStudentById(id);

	    return ResponseEntity.ok()
	            .contentType(MediaType.IMAGE_JPEG) // or IMAGE_PNG
	            .body(student.getProfilePhoto());
	}

}
