package com.itm.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.itm.college.model.LibraryMember;
import java.util.List;

@Repository
public interface LibraryMemberRepository extends JpaRepository<LibraryMember, String> {
    List<LibraryMember> findByFirstNameContainingIgnoreCase(String firstName);
    List<LibraryMember> findBySurnameContainingIgnoreCase(String surname);
    List<LibraryMember> findByMemberType(String memberType);
    List<LibraryMember> findByBookTitle(String bookTitle);
}
