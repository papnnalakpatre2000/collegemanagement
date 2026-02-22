package com.itm.college.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itm.college.model.Book;
import com.itm.college.model.BookIssueHistory;
import com.itm.college.model.Student;
import com.itm.college.dto.StudentBookHistoryDTO;
import com.itm.college.repository.BookRepository;
import com.itm.college.repository.BookIssueHistoryRepository;
import com.itm.college.repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookIssueHistoryRepository bookIssueHistoryRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    // Add new book
    public Book addBook(Book book) {
        if (bookRepository.existsById(book.getBookId())) {
            throw new RuntimeException("Book with ID already exists");
        }
        if (book.getAvailableCopies() == null) {
            book.setAvailableCopies(book.getTotalCopies());
        }
        return bookRepository.save(book);
    }
    
    // Get book by ID
    public Book getBookById(String bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));
    }
    
    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    // Update book
    public Book updateBook(String bookId, Book bookDetails) {
        Book book = getBookById(bookId);
        
        if (bookDetails.getTitle() != null) {
            book.setTitle(bookDetails.getTitle());
        }
        if (bookDetails.getAuthor() != null) {
            book.setAuthor(bookDetails.getAuthor());
        }
        if (bookDetails.getTotalCopies() != null) {
            book.setTotalCopies(bookDetails.getTotalCopies());
        }
        if (bookDetails.getAvailableCopies() != null) {
            book.setAvailableCopies(bookDetails.getAvailableCopies());
        }
        if (bookDetails.getIsbn() != null) {
            book.setIsbn(bookDetails.getIsbn());
        }
        if (bookDetails.getCategory() != null) {
            book.setCategory(bookDetails.getCategory());
        }
        
        return bookRepository.save(book);
    }
    
    // Delete book
    public void deleteBook(String bookId) {
        Book book = getBookById(bookId);
        bookRepository.delete(book);
    }
    
    // Search books
    public List<Book> searchBooks(String query) {
        if (query == null || query.isBlank()) {
            return getAllBooks();
        }
        return bookRepository.searchBooks(query);
    }
    
    // Issue book
    public BookIssueHistory issueBook(String studentId, String bookId, Integer daysAllowed) {
        // Validate student exists
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        
        // Check if student is eligible (has valid enrollment)
        if (studentId == null || studentId.isBlank()) {
            throw new RuntimeException("Student enrollment ID is required to issue a book");
        }
        
        // Get book
        Book book = getBookById(bookId);
        
        // Check available copies
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No copies available for this book");
        }
        
        // Check if student already has this book issued (not returned)
        List<BookIssueHistory> activeIssues = bookIssueHistoryRepository
                .findByStudentIdAndIsReturnedFalse(studentId);
        for (BookIssueHistory issue : activeIssues) {
            if (issue.getBookId().equals(bookId)) {
                throw new RuntimeException("Student already has this book issued. Cannot issue again until returned.");
            }
        }
        
        // Set default days if not provided
        if (daysAllowed == null || daysAllowed <= 0) {
            daysAllowed = 14; // Default 14 days
        }
        
        // Create issue history record
        LocalDate issueDate = LocalDate.now();
        BookIssueHistory issueHistory = new BookIssueHistory(studentId, bookId, book.getTitle(), issueDate, daysAllowed);
        bookIssueHistoryRepository.save(issueHistory);
        
        // Reduce available copies
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
        
        return issueHistory;
    }
    
    // Return book
    public BookIssueHistory returnBook(Long issueId) {
        BookIssueHistory issueHistory = bookIssueHistoryRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue record not found with ID: " + issueId));
        
        if (issueHistory.getIsReturned()) {
            throw new RuntimeException("Book already returned");
        }
        
        // Mark as returned
        LocalDate returnDate = LocalDate.now();
        issueHistory.setReturnDate(returnDate);
        issueHistory.setIsReturned(true);
        bookIssueHistoryRepository.save(issueHistory);
        
        // Increase available copies
        Book book = getBookById(issueHistory.getBookId());
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
        
        return issueHistory;
    }
    
    // Get student details and book history
    public StudentBookHistoryDTO getStudentDetailsAndHistory(String studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        
        List<BookIssueHistory> issueHistory = bookIssueHistoryRepository.findByStudentId(studentId);
        
        StudentBookHistoryDTO dto = new StudentBookHistoryDTO();
        dto.setStudentId(student.getId());
        dto.setStudentName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setPhone(student.getPhone());
        dto.setCourse(student.getCourse());
        dto.setAddress(student.getAddress());
        dto.setIssueHistory(issueHistory);
        dto.setEligibleToBorrow(isStudentEligible(studentId));
        
        return dto;
    }
    
    // Check if student is eligible to borrow
    public boolean isStudentEligible(String studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        
        // Student must exist with valid enrollment
        if (studentId == null || studentId.isBlank()) {
            return false;
        }
        
        // Check if student has overdue books
        List<BookIssueHistory> activeIssues = bookIssueHistoryRepository
                .findByStudentIdAndIsReturnedFalse(studentId);
        
        LocalDate today = LocalDate.now();
        for (BookIssueHistory issue : activeIssues) {
            if (issue.getDueDate().isBefore(today)) {
                return false; // Has overdue book
            }
        }
        
        // Can borrow max 3 books at a time
        if (activeIssues.size() >= 3) {
            return false;
        }
        
        return true;
    }
    
    // Get book history for a student
    public List<BookIssueHistory> getStudentBookHistory(String studentId) {
        // Validate student exists
        studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        
        return bookIssueHistoryRepository.findByStudentId(studentId);
    }
}
