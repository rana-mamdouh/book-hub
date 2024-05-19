package com.bookhub.lms.controller;

import com.bookhub.lms.dto.PatronDTO;
import com.bookhub.lms.entity.Patron;
import com.bookhub.lms.service.PatronService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class PatronControllerTest {

    @Mock
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPatrons() {
        List<PatronDTO> expectedPatrons = Arrays.asList(
                new PatronDTO(),
                new PatronDTO()
        );
        when(patronService.findAll()).thenReturn(expectedPatrons);

        List<PatronDTO> actualPatrons = patronController.getAllPatrons();

        Assert.assertEquals(actualPatrons, expectedPatrons);
    }

    @Test
    public void testGetPatron() {
        Long id = 1L;
        PatronDTO expectedPatron = new PatronDTO();
        when(patronService.findById(id)).thenReturn(expectedPatron);

        PatronDTO actualPatron = patronController.getPatron(id);

        Assert.assertEquals(actualPatron, expectedPatron);
    }

    @Test
    public void testAddPatron() {
        PatronDTO patronDTO = new PatronDTO();
        Patron savedPatron = new Patron();
        when(patronService.save(isNull(), eq(patronDTO))).thenReturn(savedPatron);
        Patron actualPatron = patronController.addPatron(patronDTO);
        Assert.assertEquals(actualPatron, savedPatron);
    }

    @Test
    public void testUpdatePatron() {
        Long id = 1L;
        PatronDTO patronDTO = new PatronDTO();
        Patron updatedPatron = new Patron();
        when(patronService.save(eq(id), eq(patronDTO))).thenReturn(updatedPatron);
        Patron actualPatron = patronController.updatePatron(id, patronDTO);
        Assert.assertEquals(actualPatron, updatedPatron);
    }

    @Test
    public void testDeletePatron() {
        Long id = 1L;
        doNothing().when(patronService).deleteById(eq(id));
        patronController.deletePatron(id);
        verify(patronService, times(1)).deleteById(id);
    }
}
