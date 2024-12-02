package com.mycompany.generic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mycompany.generic.entity.LocationEntity;
import com.mycompany.generic.entity.ServiceEntity;
import com.mycompany.generic.entity.ServiceTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceResponseDTO {
    private ServiceEntity service;
    private List<LocationEntity> locationList;
    private ServiceTypes serviceType;
    private String img;
}
