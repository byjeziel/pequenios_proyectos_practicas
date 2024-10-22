package com.pruebatecnica.apibackend.service;

import com.pruebatecnica.apibackend.model.dto.CandidateDto;

import java.util.List;

public interface CandidateService {
    CandidateDto create(CandidateDto candidate);
    List<CandidateDto> findAll();
    void delete(Long id);
    CandidateDto getById(Long id);
    void update(Long id, CandidateDto candidate);
}
