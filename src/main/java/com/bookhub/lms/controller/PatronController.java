package com.bookhub.lms.controller;

import com.bookhub.lms.service.PatronService;
import jakarta.validation.Valid;
import com.bookhub.lms.dto.PatronDTO;
import com.bookhub.lms.entity.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
@Validated

public class PatronController {

    @Autowired
    private PatronService patronService;

    @GetMapping
    public List<PatronDTO> getAllPatrons() {
        return patronService.findAll();
    }

    @GetMapping("/{id}")
    public PatronDTO getPatron(@PathVariable Long id) {
        return patronService.findById(id);
    }

    @PostMapping
    public Patron addPatron(@RequestBody @Valid PatronDTO patronDTO) {
        return patronService.save(null, patronDTO);
    }

    @PutMapping("/{id}")
    public Patron updatePatron(@PathVariable Long id, @RequestBody @Valid PatronDTO patronDTO) {
        return patronService.save(id, patronDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatron(@PathVariable Long id) {
        patronService.deleteById(id);
        return ResponseEntity.ok("Patron with ID " + id + " deleted successfully");
    }
}
