package com.itm.college.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itm.college.model.LibraryMember;
import com.itm.college.repository.LibraryMemberRepository;
import java.util.List;

@Service
public class LibraryMemberService {

    @Autowired
    private LibraryMemberRepository libraryMemberRepository;

    public LibraryMember addMember(LibraryMember member) {
        if (libraryMemberRepository.existsById(member.getReferenceNo())) {
            throw new RuntimeException("Member with Reference No. already exists");
        }
        return libraryMemberRepository.save(member);
    }

    public LibraryMember getMemberByReferenceNo(String referenceNo) {
        return libraryMemberRepository.findById(referenceNo)
                .orElseThrow(() -> new RuntimeException("Member not found with Reference No.: " + referenceNo));
    }

    public List<LibraryMember> getAllMembers() {
        return libraryMemberRepository.findAll();
    }

    public LibraryMember updateMember(String referenceNo, LibraryMember memberDetails) {
        LibraryMember member = getMemberByReferenceNo(referenceNo);
        
        if (memberDetails.getMemberType() != null) {
            member.setMemberType(memberDetails.getMemberType());
        }
        if (memberDetails.getFirstName() != null) {
            member.setFirstName(memberDetails.getFirstName());
        }
        if (memberDetails.getSurname() != null) {
            member.setSurname(memberDetails.getSurname());
        }
        if (memberDetails.getAddress() != null) {
            member.setAddress(memberDetails.getAddress());
        }
        if (memberDetails.getPostCode() != null) {
            member.setPostCode(memberDetails.getPostCode());
        }
        if (memberDetails.getMobileNo() != null) {
            member.setMobileNo(memberDetails.getMobileNo());
        }
        if (memberDetails.getBookId() != null) {
            member.setBookId(memberDetails.getBookId());
        }
        if (memberDetails.getBookTitle() != null) {
            member.setBookTitle(memberDetails.getBookTitle());
        }
        if (memberDetails.getAuthor() != null) {
            member.setAuthor(memberDetails.getAuthor());
        }
        if (memberDetails.getDateBorrowed() != null) {
            member.setDateBorrowed(memberDetails.getDateBorrowed());
        }
        if (memberDetails.getDateDue() != null) {
            member.setDateDue(memberDetails.getDateDue());
        }
        if (memberDetails.getDaysInLoan() != null) {
            member.setDaysInLoan(memberDetails.getDaysInLoan());
        }
        
        return libraryMemberRepository.save(member);
    }

    public void deleteMember(String referenceNo) {
        LibraryMember member = getMemberByReferenceNo(referenceNo);
        libraryMemberRepository.delete(member);
    }

    public List<LibraryMember> searchByFirstName(String firstName) {
        return libraryMemberRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    public List<LibraryMember> searchBySurname(String surname) {
        return libraryMemberRepository.findBySurnameContainingIgnoreCase(surname);
    }

    public List<LibraryMember> searchByMemberType(String memberType) {
        return libraryMemberRepository.findByMemberType(memberType);
    }

    public List<LibraryMember> searchByBookTitle(String bookTitle) {
        return libraryMemberRepository.findByBookTitle(bookTitle);
    }
}
