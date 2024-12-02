package com.mycompany.generic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "serviceandservicetypes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceAndServiceTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long serviceTypeId;
    private Long serviceId;
}
