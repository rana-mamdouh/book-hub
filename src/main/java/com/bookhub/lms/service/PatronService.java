package com.bookhub.lms.service;

import com.bookhub.lms.dto.PatronDTO;
import com.bookhub.lms.entity.Patron;

import com.bookhub.lms.exception.ResourceNotFoundException;
import com.bookhub.lms.repository.PatronRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;


    @Autowired
    private ModelMapper modelMapper;

    public List<PatronDTO> findAll() {
        List<Patron> patrons = patronRepository.findAll();
        if (patrons.isEmpty()) {
            throw new ResourceNotFoundException("No patrons found");
        }
        return patrons.stream()
                .map(this::mapToPatronDTO)
                .collect(Collectors.toList());
    }

    public PatronDTO findById(Long id) {
        Patron patron = patronRepository.findById(id).orElse(null);
        if (patron != null) {
            return mapToPatronDTO(patron);
        }
        throw new ResourceNotFoundException("Patron not found with ID: " + id);
    }

    public Patron save(Long id, PatronDTO patronDTO) {
        Patron patron = mapToPatron(patronDTO);
        if (id != null) {
            Patron existingPatron = patronRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Patron not found with ID: " + id));
            patron.setId(existingPatron.getId());
        }
        return patronRepository.save(patron);
    }

    public void deleteById(Long id) {
        if (!patronRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patron not found with ID: " + id);
        }
        patronRepository.deleteById(id);
    }

    private Patron mapToPatron(PatronDTO patronDTO) {
        return modelMapper.map(patronDTO, Patron.class);
    }

    private PatronDTO mapToPatronDTO(Patron patron) {
        return modelMapper.map(patron, PatronDTO.class);
    }
}
