package com.pruebatecnica.apibackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.pruebatecnica.apibackend.model.dto.TechnologyDto;
import com.pruebatecnica.apibackend.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technology")
@Tag(name="Technology controller")
public class TechnologyController {
    @Autowired
    private TechnologyService technologyService;

    @GetMapping
    @Operation(summary = "findAll")
    public ResponseEntity<List<TechnologyDto>> findAll(){
        return new ResponseEntity<>(technologyService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "getTechnologyById")
    public ResponseEntity<TechnologyDto> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(technologyService.getById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    @Operation(summary = "createTechnology")
    public ResponseEntity<TechnologyDto> create(@RequestBody TechnologyDto technology){
        return new ResponseEntity<>(technologyService.create(technology),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "updateTechnology")
    public ResponseEntity<HttpStatus> update(@RequestBody TechnologyDto technology, @PathVariable("id") Long id){
        technologyService.update(id,technology);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "deleteTechnology")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id){
        technologyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
