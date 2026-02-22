package com.itm.college.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.itm.college.model.LibraryMember;
import com.itm.college.service.LibraryMemberService;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/library")
public class LibraryMemberController {

    @Autowired
    private LibraryMemberService libraryMemberService;

    @PostMapping("/addMember")
    public ResponseEntity<String> addMember(@RequestBody LibraryMember member) {
        try {
            LibraryMember savedMember = libraryMemberService.addMember(member);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Member added successfully with Reference No.: " + savedMember.getReferenceNo());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to add member: " + e.getMessage());
        }
    }

    @GetMapping("/getMember/{referenceNo}")
    public ResponseEntity<?> getMember(@PathVariable String referenceNo) {
        try {
            LibraryMember member = libraryMemberService.getMemberByReferenceNo(referenceNo);
            return ResponseEntity.ok(member);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Member not found: " + e.getMessage());
        }
    }

    @GetMapping("/getAllMembers")
    public ResponseEntity<List<LibraryMember>> getAllMembers() {
        List<LibraryMember> members = libraryMemberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @PutMapping("/updateMember/{referenceNo}")
    public ResponseEntity<String> updateMember(@PathVariable String referenceNo, @RequestBody LibraryMember memberDetails) {
        try {
            libraryMemberService.updateMember(referenceNo, memberDetails);
            return ResponseEntity.ok("Member with Reference No.: " + referenceNo + " updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Update failed: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteMember/{referenceNo}")
    public ResponseEntity<String> deleteMember(@PathVariable String referenceNo) {
        try {
            libraryMemberService.deleteMember(referenceNo);
            return ResponseEntity.ok("Member with Reference No.: " + referenceNo + " deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Deletion failed: " + e.getMessage());
        }
    }

    @GetMapping("/searchByFirstName")
    public ResponseEntity<List<LibraryMember>> searchByFirstName(@RequestParam String firstName) {
        List<LibraryMember> members = libraryMemberService.searchByFirstName(firstName);
        return ResponseEntity.ok(members);
    }

    @GetMapping("/searchBySurname")
    public ResponseEntity<List<LibraryMember>> searchBySurname(@RequestParam String surname) {
        List<LibraryMember> members = libraryMemberService.searchBySurname(surname);
        return ResponseEntity.ok(members);
    }

    @GetMapping("/searchByMemberType")
    public ResponseEntity<List<LibraryMember>> searchByMemberType(@RequestParam String memberType) {
        List<LibraryMember> members = libraryMemberService.searchByMemberType(memberType);
        return ResponseEntity.ok(members);
    }

    @GetMapping("/searchByBookTitle")
    public ResponseEntity<List<LibraryMember>> searchByBookTitle(@RequestParam String bookTitle) {
        List<LibraryMember> members = libraryMemberService.searchByBookTitle(bookTitle);
        return ResponseEntity.ok(members);
    }
}
