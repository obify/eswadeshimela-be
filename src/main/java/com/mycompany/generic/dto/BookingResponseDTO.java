package com.mycompany.generic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mycompany.generic.entity.BookingEntity;
import com.mycompany.generic.entity.ServiceEntity;
import com.mycompany.generic.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponseDTO {
    private ServiceEntity service;
    private BookingEntity booking;
    private UserEntity user;
    private UserEntity serviceProvider;
}
