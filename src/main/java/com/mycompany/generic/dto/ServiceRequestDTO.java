package com.mycompany.generic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mycompany.generic.entity.ServiceTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceRequestDTO {
    private String title;
    private String description;
    private Double unitPrice;
    private String pinCode;
    private Long userId;
    private List<LocationDTO> locations;
    private List<ServiceTypes> serviceTypes;
}
