package com.mycompany.generic.entity;

import com.mycompany.generic.constant.ESType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servicetypes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ESType name;
    private String description;
}
