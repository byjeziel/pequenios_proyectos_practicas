package com.pruebatecnica.apibackend.service.impl;

import com.pruebatecnica.apibackend.model.dto.TechnologyByCandidateDto;
import com.pruebatecnica.apibackend.model.entity.Candidate;
import com.pruebatecnica.apibackend.model.entity.Technology;
import com.pruebatecnica.apibackend.model.entity.TechnologyByCandidate;
import com.pruebatecnica.apibackend.repository.CandidateRepository;
import com.pruebatecnica.apibackend.repository.TechnologyByCandidateRepository;
import com.pruebatecnica.apibackend.repository.TechnologyRepository;
import com.pruebatecnica.apibackend.service.TechnologyByCandidateService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TechnologyByCandidateServiceImpl implements TechnologyByCandidateService {
    @Autowired
    TechnologyByCandidateRepository technologyByCandidateRepository;

    @Autowired
    TechnologyRepository technologyRepository;

    @Autowired
    CandidateRepository candidateRepository;
    private static final String ID_NOT_FOUND = "Technology by Candidate not found -  id:";
    private static final String ID_TECHNOLOGY_NOT_FOUND = "Technology not found - id:";
    private static final String ID_CANDIDATE_NOT_FOUND = "Candidate not found - id:";


    private final ModelMapper modelMapper = new ModelMapper();
    @Override
    public TechnologyByCandidateDto create(TechnologyByCandidateDto technologyByCandidateDto) {
        Candidate candidate = candidateRepository.findById(technologyByCandidateDto.getIdCandidate()).orElseThrow(
                ()->{
                    log.error(ID_CANDIDATE_NOT_FOUND+ technologyByCandidateDto.getIdCandidate(), new EntityNotFoundException(ID_CANDIDATE_NOT_FOUND));
                    return new EntityNotFoundException(ID_CANDIDATE_NOT_FOUND);
                });
        Technology technology = technologyRepository.findById(technologyByCandidateDto.getIdTechnology()).orElseThrow(
                ()->{
                    log.error(ID_TECHNOLOGY_NOT_FOUND+technologyByCandidateDto.getIdTechnology(), new EntityNotFoundException(ID_TECHNOLOGY_NOT_FOUND));
                    return new EntityNotFoundException(ID_TECHNOLOGY_NOT_FOUND);
                });
        TechnologyByCandidate technologyByCandidate = modelMapper.map(technologyByCandidateDto, TechnologyByCandidate.class);
        technologyByCandidate.setCandidate(candidate);
        technologyByCandidate.setTechnology(technology);
        technologyByCandidateRepository.save(technologyByCandidate);
        log.info("Technology by Candidate created successfully");
        return technologyByCandidateDto;
    }

    @Override
    public List<TechnologyByCandidateDto> findAll(Long idCandidate) {
        return technologyByCandidateRepository.findAll().stream()
                .filter(technologyByCandidate -> technologyByCandidate.getCandidate().getId()==(idCandidate))
                .map(technologyByCandidate -> modelMapper.map(technologyByCandidate, TechnologyByCandidateDto.class))
                .toList();
    }

    @Override
    public void delete(Long id) {
        technologyByCandidateRepository.findById(id)
                .ifPresentOrElse(technologyFind->{
                    technologyByCandidateRepository.delete(technologyFind);
                    log.info("Technology by Candidate deleted successfully");
                },()->{
                    log.error(ID_NOT_FOUND+id,new EntityNotFoundException(ID_NOT_FOUND+id));
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
                });
    }

    @Override
    public TechnologyByCandidateDto getTechnologyById(Long id) {
        return technologyByCandidateRepository.findById(id)
                .map(technologyByCandidate -> modelMapper.map(technologyByCandidate, TechnologyByCandidateDto.class))
                .orElseThrow(
                        ()->{
                            log.error(ID_NOT_FOUND+id,new EntityNotFoundException(ID_NOT_FOUND+id));
                            return new EntityNotFoundException(ID_NOT_FOUND + id);
                        }
                );
    }

    @Override
    public void update(Long id, TechnologyByCandidateDto technologyByCandidateDto) {
        technologyByCandidateRepository.findById(id)
                .ifPresentOrElse(technologyFind -> {
                    technologyFind = modelMapper.map(technologyByCandidateDto, TechnologyByCandidate.class);
                    technologyByCandidateRepository.save(technologyFind);
                    log.info("Technology by Candidate created successfully");
                },()->{
                    log.error(ID_NOT_FOUND+id,new EntityNotFoundException(ID_NOT_FOUND+id));
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
                });
    }

    @Override
    public List<TechnologyByCandidateDto> getCandidatesByTechnology(String technologyName) {
        return technologyByCandidateRepository.getCandidatesByTechnology(technologyName);
    }

}
