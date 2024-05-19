package com.bookhub.lms.controller;

import com.bookhub.lms.entity.Record;
import com.bookhub.lms.service.RecordService;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated

public class RecordController {
    @Autowired
    private RecordService recordService;

    @PostMapping("borrow/{bookId}/patron/{patronId}")
    public Record borrowBookRecord(@PathVariable Long bookId, @PathVariable Long patronId) {
        return recordService.borrowBookRecord(bookId, patronId);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public Record returnBookRecord(@PathVariable Long bookId, @PathVariable Long patronId) {
        return recordService.returnBookRecord(bookId, patronId);
    }
}