package com.pruebatecnica.apibackend.model.dto;

import com.pruebatecnica.apibackend.model.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CandidateDto {
    private String name;
    private String lastName;
    private DocumentType documentType;
    private Integer documentNumber;
    private LocalDate birthdate;
}
