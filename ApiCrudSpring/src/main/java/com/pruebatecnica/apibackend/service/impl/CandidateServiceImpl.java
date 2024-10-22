package com.pruebatecnica.apibackend.service.impl;

import com.pruebatecnica.apibackend.model.dto.CandidateDto;
import com.pruebatecnica.apibackend.model.entity.Candidate;
import com.pruebatecnica.apibackend.repository.CandidateRepository;
import com.pruebatecnica.apibackend.service.CandidateService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final  String ID_NOT_FOUND = "Candidate not found -  id:";
    @Override
    public CandidateDto create(CandidateDto candidateDto) {
        Candidate candidate = modelMapper.map(candidateDto, Candidate.class);
        candidateRepository.save(candidate);
        candidateDto = modelMapper.map(candidate, CandidateDto.class);
        log.info(String.format("Candidate %s created successfully ",candidate.getName()));
        return candidateDto;
    }

    @Override
    public List<CandidateDto> findAll() {
        return candidateRepository.findAll().stream()
                                  .map(candidate -> modelMapper.map(candidate,CandidateDto.class))
                                  .toList();
    }

    @Override
    public void delete(Long id) {
        candidateRepository.findById(id)
                           .ifPresentOrElse(
                                candidate->{candidateRepository.delete(candidate);},
                                ()->{throw new EntityNotFoundException(ID_NOT_FOUND+id);}
                           );
    }

    @Override
    public CandidateDto getById(Long id) {
        return candidateRepository.findById(id)
                                  .map(candidate -> modelMapper.map(candidate, CandidateDto.class))
                                  .orElseThrow(() -> new EntityNotFoundException(ID_NOT_FOUND + id));
    }

    @Override
    public void update(Long id, CandidateDto candidate) {
        candidateRepository.findById(id)
                .ifPresentOrElse(candidateFind->{
                    candidateRepository.save(modelMapper.map(candidateFind, Candidate.class));
                    log.info(String.format("Candidate %s updated successfully ",candidateFind.getName()));
                },()->{
                    log.error(ID_NOT_FOUND+id,new EntityNotFoundException(ID_NOT_FOUND+id));
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
                });
    }
}
