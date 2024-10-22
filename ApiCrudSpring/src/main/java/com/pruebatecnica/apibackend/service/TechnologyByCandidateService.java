package com.pruebatecnica.apibackend.service;

import com.pruebatecnica.apibackend.model.dto.TechnologyByCandidateDto;

import java.util.List;

public interface TechnologyByCandidateService {
    TechnologyByCandidateDto create(TechnologyByCandidateDto technologyByCandidateDto);
    List<TechnologyByCandidateDto> findAll(Long idCandidate);
    void delete(Long id);
    TechnologyByCandidateDto getTechnologyById(Long id);
    void update(Long id, TechnologyByCandidateDto technologyByCandidateDto);
    List<TechnologyByCandidateDto> getCandidatesByTechnology(String technologyName);
}
