package com.pruebatecnica.apibackend.service;

import com.pruebatecnica.apibackend.model.dto.TechnologyDto;

import java.util.List;

public interface TechnologyService {
    TechnologyDto create(TechnologyDto technology);
    List<TechnologyDto> findAll();
    void delete(Long id);
    TechnologyDto getById(Long id);
    void update(Long id, TechnologyDto technology);
}
