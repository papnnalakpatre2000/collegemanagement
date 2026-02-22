package com.itm.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.itm.college.model.BookIssueHistory;
import java.util.List;

@Repository
public interface BookIssueHistoryRepository extends JpaRepository<BookIssueHistory, Long> {
    List<BookIssueHistory> findByStudentId(String studentId);
    List<BookIssueHistory> findByStudentIdAndIsReturnedFalse(String studentId);
    List<BookIssueHistory> findByBookId(String bookId);
}
