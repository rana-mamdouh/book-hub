package com.bookhub.lms.repository;

import com.bookhub.lms.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
    Record findByBookIdAndPatronId(Long bookId, Long patronId);

    boolean existsByBookIdAndReturnDateIsNull(Long bookId);
}
