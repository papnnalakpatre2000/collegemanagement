package com.itm.college.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.itm.college.model.Book;
import com.itm.college.model.BookIssueHistory;
import com.itm.college.dto.StudentBookHistoryDTO;
import com.itm.college.service.BookService;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * POST /api/books - Add new book
     */
    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        try {
            Book savedBook = bookService.addBook(book);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Book added successfully");
            response.put("book", savedBook);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to add book: " + e.getMessage());
        }
    }

    /**
     * GET /api/books/{id} - Get book by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable String id) {
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book not found: " + e.getMessage());
        }
    }

    /**
     * GET /api/books - Get all books
     */
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    /**
     * PUT /api/books/{id} - Update book
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable String id, @RequestBody Book bookDetails) {
        try {
            Book updatedBook = bookService.updateBook(id, bookDetails);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Book updated successfully");
            response.put("book", updatedBook);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Update failed: " + e.getMessage());
        }
    }

    /**
     * DELETE /api/books/{id} - Delete book
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable String id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.ok("Book deleted successfully with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Deletion failed: " + e.getMessage());
        }
    }

    /**
     * GET /api/books/search?query= - Search books
     */
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam(required = false) String query) {
        try {
            List<Book> books = bookService.searchBooks(query);
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(List.of());
        }
    }

    /**
     * POST /api/books/issue - Issue book to student
     */
    @PostMapping("/issue")
    public ResponseEntity<?> issueBook(@RequestBody Map<String, Object> issueRequest) {
        try {
            String studentId = (String) issueRequest.get("studentId");
            String bookId = (String) issueRequest.get("bookId");
            Integer daysAllowed = issueRequest.get("daysAllowed") != null 
                    ? Integer.parseInt(issueRequest.get("daysAllowed").toString()) 
                    : 14;

            if (studentId == null || studentId.isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Student ID is required");
            }
            if (bookId == null || bookId.isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Book ID is required");
            }

            BookIssueHistory issueHistory = bookService.issueBook(studentId, bookId, daysAllowed);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Book issued successfully");
            response.put("issueDetails", issueHistory);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to issue book: " + e.getMessage());
        }
    }

    /**
     * POST /api/books/return - Return book from student
     */
    @PostMapping("/return")
    public ResponseEntity<?> returnBook(@RequestBody Map<String, Object> returnRequest) {
        try {
            Long issueId = Long.parseLong(returnRequest.get("issueId").toString());

            BookIssueHistory returnHistory = bookService.returnBook(issueId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Book returned successfully");
            response.put("returnDetails", returnHistory);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to return book: " + e.getMessage());
        }
    }

    /**
     * GET /api/users/{enrollmentId} - Get student details and book history
     */
    @GetMapping("/users/{enrollmentId}")
    public ResponseEntity<?> getStudentDetailsAndHistory(@PathVariable String enrollmentId) {
        try {
            StudentBookHistoryDTO studentDetails = bookService.getStudentDetailsAndHistory(enrollmentId);
            return ResponseEntity.ok(studentDetails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found: " + e.getMessage());
        }
    }

    /**
     * GET /api/books/history/{studentId} - Get student's book borrowing history
     */
    @GetMapping("/history/{studentId}")
    public ResponseEntity<?> getStudentBookHistory(@PathVariable String studentId) {
        try {
            List<BookIssueHistory> history = bookService.getStudentBookHistory(studentId);
            return ResponseEntity.ok(history);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found: " + e.getMessage());
        }
    }

    /**
     * GET /api/books/eligible/{studentId} - Check if student is eligible to borrow
     */
    @GetMapping("/eligible/{studentId}")
    public ResponseEntity<?> checkEligibility(@PathVariable String studentId) {
        try {
            boolean eligible = bookService.isStudentEligible(studentId);
            Map<String, Object> response = new HashMap<>();
            response.put("studentId", studentId);
            response.put("eligibleToBorrow", eligible);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found: " + e.getMessage());
        }
    }
}
