package com.itm.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.itm.college.model.Student;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
	Optional<Student> findByEmail(String email);
}
