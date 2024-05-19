package com.bookhub.lms.controller;

import com.bookhub.lms.entity.Record;
import com.bookhub.lms.service.RecordService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class RecordControllerTest {

    @Mock
    private RecordService recordService;

    @InjectMocks
    private RecordController recordController;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBorrowBookRecord() {
        Long bookId = 1L;
        Long patronId = 1L;
        Record expectedRecord = new Record();
        when(recordService.borrowBookRecord(eq(bookId), eq(patronId))).thenReturn(expectedRecord);

        Record actualRecord = recordController.borrowBookRecord(bookId, patronId);
        Assert.assertEquals(actualRecord, expectedRecord);
    }

    @Test
    public void testReturnBookRecord() {
        Long bookId = 1L;
        Long patronId = 1L;
        Record expectedRecord = new Record();
        when(recordService.returnBookRecord(eq(bookId), eq(patronId))).thenReturn(expectedRecord);
        Record actualRecord = recordController.returnBookRecord(bookId, patronId);
        Assert.assertEquals(actualRecord, expectedRecord);
    }
}
