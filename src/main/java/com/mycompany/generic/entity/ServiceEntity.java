package com.mycompany.generic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "services")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Double unitPrice;
    private String pinCode;
    private boolean active = false;
    private Long userId;
    private Double rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
