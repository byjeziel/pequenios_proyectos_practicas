package com.pruebatecnica.apibackend.repository;

import com.pruebatecnica.apibackend.model.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {
}
