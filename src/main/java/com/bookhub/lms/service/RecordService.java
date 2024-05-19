package com.bookhub.lms.service;

import com.bookhub.lms.dto.RecordDTO;
import com.bookhub.lms.exception.BookAlreadyBorrowedException;
import com.bookhub.lms.exception.ResourceNotFoundException;
import com.bookhub.lms.repository.BookRepository;
import com.bookhub.lms.repository.PatronRepository;
import com.bookhub.lms.repository.RecordRepository;
import com.bookhub.lms.entity.Record;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Record borrowBookRecord(Long bookId, Long patronId) {
        validateBookAndPatronExistence(bookId, patronId);

        if (isBookBorrowed(bookId)) {
            throw new BookAlreadyBorrowedException("Book with ID: " + bookId + " is already borrowed.");
        }

        RecordDTO recordDTO = createRecordDTO(bookId, patronId);
        return save(recordDTO);
    }

    public Record returnBookRecord(Long bookId, Long patronId) {
        validateBookAndPatronExistence(bookId, patronId);

        RecordDTO recordDTO = findByBookIdAndPatronId(bookId, patronId);

        if (recordDTO == null) {
            throw new ResourceNotFoundException("No active borrowing record found for book with ID: " + bookId + " and patron with ID: " + patronId);
        }

        recordDTO.setReturnDate(new Date());
        return save(recordDTO);
    }

    private Record save(RecordDTO recordDTO) {
        Record record = mapToRecord(recordDTO);
        return recordRepository.save(record);
    }

    private RecordDTO findByBookIdAndPatronId(Long bookId, Long patronId) {
        Record record = recordRepository.findByBookIdAndPatronId(bookId, patronId);
        return mapToRecordDTO(record);
    }

    private Record mapToRecord(RecordDTO recordDTO) {
        return modelMapper.map(recordDTO, Record.class);
    }

    private RecordDTO mapToRecordDTO(Record record) {
        return modelMapper.map(record, RecordDTO.class);
    }

    private boolean doesBookExist(Long bookId) {
        return bookRepository.existsById(bookId);
    }

    private boolean doesPatronExist(Long patronId) {
        return patronRepository.existsById(patronId);
    }

    private boolean isBookBorrowed(Long bookId) {
        return recordRepository.existsByBookIdAndReturnDateIsNull(bookId);
    }

    private void validateBookAndPatronExistence(Long bookId, Long patronId) {
        if (!doesBookExist(bookId)) {
            throw new ResourceNotFoundException("Book not found with ID: " + bookId);
        }
        if (!doesPatronExist(patronId)) {
            throw new ResourceNotFoundException("Patron not found with ID: " + patronId);
        }
    }

    private RecordDTO createRecordDTO(Long bookId, Long patronId) {
        RecordDTO recordDTO = new RecordDTO();
        recordDTO.setPatronId(patronId);
        recordDTO.setBookId(bookId);
        recordDTO.setBorrowedDate(new Date());
        return recordDTO;
    }
}

