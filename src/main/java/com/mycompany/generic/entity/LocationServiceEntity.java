package com.mycompany.generic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "locationservices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long locationId;
    private Long serviceId;
}
