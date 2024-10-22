package com.pruebatecnica.apibackend.repository;

import com.pruebatecnica.apibackend.model.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
