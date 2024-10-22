package com.pruebatecnica.apibackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TechnologyByCandidateDto {
    private Long id;
    private Long idCandidate;
    private Long idTechnology;
    private Integer yearsOfExperience;
}
