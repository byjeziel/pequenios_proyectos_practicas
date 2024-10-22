package com.pruebatecnica.apibackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.pruebatecnica.apibackend.model.dto.CandidateDto;
import com.pruebatecnica.apibackend.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate")
@Tag(name="Candidate controller")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @GetMapping
    @Operation(summary = "findAll")
    public ResponseEntity<List<CandidateDto>> findAll(){
        return new ResponseEntity<>(candidateService.findAll(), HttpStatus.OK);
    }
    @GetMapping(value="/{id}")
    @Operation(summary = "getCandidateById")
    public ResponseEntity<CandidateDto> getCandidateById(@PathVariable("id") Long id){
        return new ResponseEntity<>(candidateService.getById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    @Operation(summary = "createCandidate")
    public ResponseEntity<CandidateDto> create(@RequestBody CandidateDto candidate){
        return new ResponseEntity<>(candidateService.create(candidate),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "deleteCandidate")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id){
        candidateService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "updateCandidate")
    public ResponseEntity<HttpStatus> update(@PathVariable Long id, @RequestBody CandidateDto candidate){
        candidateService.update(id,candidate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
