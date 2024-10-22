package com.pruebatecnica.apibackend.repository;

import com.pruebatecnica.apibackend.model.dto.TechnologyByCandidateDto;
import com.pruebatecnica.apibackend.model.entity.TechnologyByCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TechnologyByCandidateRepository extends JpaRepository<TechnologyByCandidate,Long> {
    @Query("SELECT tc.candidate AS candidate, tc.yearsOfExperience as yearsOfExperience " +
            "FROM TechnologyByCandidate tc " +
            "INNER JOIN tc.candidate " +
            "INNER JOIN tc.technology t " +
            "WHERE CONCAT(t.name,' ',t.version) LIKE CONCAT('%',:technologyName,'%') ")
    List<TechnologyByCandidateDto> getCandidatesByTechnology(@Param("technologyName") String technologyName);

}
