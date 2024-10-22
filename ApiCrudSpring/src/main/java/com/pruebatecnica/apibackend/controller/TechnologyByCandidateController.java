package com.pruebatecnica.apibackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.pruebatecnica.apibackend.model.dto.TechnologyByCandidateDto;
import com.pruebatecnica.apibackend.service.TechnologyByCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("technologyByCandidate")
@Tag(name="Technology by Candidate controller")
public class TechnologyByCandidateController {
    @Autowired
    TechnologyByCandidateService technologyByCandidateService;

    @GetMapping("/{id}")
    @Operation(summary = "findAll")
    public ResponseEntity<List<TechnologyByCandidateDto>> findAll(@PathVariable("id") Long id){
        return new ResponseEntity<>(technologyByCandidateService.findAll(id), HttpStatus.OK);
    }
    @GetMapping("/id/{idTechnologyByCandidate}")
    @Operation(summary = "getTechnologyById")
    public ResponseEntity<TechnologyByCandidateDto> getTechnologyById(@PathVariable("idTechnologyByCandidate") Long id){
        return new ResponseEntity<>(technologyByCandidateService.getTechnologyById(id), HttpStatus.OK);
    }
    @GetMapping("/candidatesByTechnology")
    @Operation(summary = "getCandidatesByTechnology")
    public ResponseEntity<List<TechnologyByCandidateDto>> getCandidatesByTechnology(@RequestParam String technologyName){
        return new ResponseEntity<>(technologyByCandidateService.getCandidatesByTechnology(technologyName), HttpStatus.OK);
    }

    @PostMapping("/create")
    @Operation(summary = "createTechnologyByCandidate")
    public ResponseEntity<TechnologyByCandidateDto> create(@RequestBody TechnologyByCandidateDto technologyByCandidateDto){
        return new ResponseEntity<>(technologyByCandidateService.create(technologyByCandidateDto),HttpStatus.CREATED);
    }

    @PutMapping("/update/{idTechnologyByCandidate}")
    @Operation(summary = "updateTechnologyByCandidate")
    public ResponseEntity<HttpStatus> update(@PathVariable("idTechnologyByCandidate") Long id, @RequestBody TechnologyByCandidateDto technologyByCandidateDto){
        technologyByCandidateService.update(id,technologyByCandidateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idTechnologyByCandidate}")
    @Operation(summary = "deleteTechnologyByCandidate")
    public ResponseEntity<HttpStatus> delete(@PathVariable("idTechnologyByCandidate") Long id){
        technologyByCandidateService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
