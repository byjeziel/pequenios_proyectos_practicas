package com.pruebatecnica.apibackend.service.impl;

import com.pruebatecnica.apibackend.model.dto.TechnologyDto;
import com.pruebatecnica.apibackend.model.entity.Technology;
import com.pruebatecnica.apibackend.repository.TechnologyRepository;
import com.pruebatecnica.apibackend.service.TechnologyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    private static final String ID_NOT_FOUND = "Technology not found -  id:";
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public TechnologyDto create(TechnologyDto technologyDto) {
        Technology technology = modelMapper.map(technologyDto,Technology.class);
        technologyRepository.save(technology);
        log.info(String.format("Technology %s created successfully ",technology.getName()));
        return modelMapper.map(technology,TechnologyDto.class);
    }

    @Override
    public List<TechnologyDto> findAll() {
        return technologyRepository.findAll().stream().map(
                technology -> modelMapper.map(technology,TechnologyDto.class)
        ).toList();
    }

    @Override
    public void delete(Long id) {
        technologyRepository.findById(id)
                .ifPresentOrElse(technologyFind->{
                    technologyRepository.delete(technologyFind);
                    log.info("Technology deleted successfully");
                },()->{
                    log.error(ID_NOT_FOUND+id,new EntityNotFoundException(ID_NOT_FOUND+id));
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
                });
    }

    @Override
    public TechnologyDto getById(Long id) {
        return technologyRepository.findById(id).map(technology -> modelMapper.map(technology,TechnologyDto.class))
                .orElseThrow(
                        ()->{
                            log.error(ID_NOT_FOUND+id,new EntityNotFoundException(ID_NOT_FOUND+id));
                            return new EntityNotFoundException(ID_NOT_FOUND + id);
                        }
                );
    }

    @Override
    public void update(Long id, TechnologyDto technology) {
        technologyRepository.findById(id)
                .ifPresentOrElse(technologyFind -> {
                    technologyRepository.save(modelMapper.map(technologyFind,Technology.class));
                    log.info(String.format("Technology %s updated successfully ",technologyFind.getName()));
                },()->{
                    log.error(ID_NOT_FOUND+id,new EntityNotFoundException(ID_NOT_FOUND+id));
                    throw new EntityNotFoundException(ID_NOT_FOUND+id);
                });
    }
}

