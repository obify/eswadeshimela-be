package com.mycompany.generic.repository;

import com.mycompany.generic.constant.ERole;
import com.mycompany.generic.constant.ESType;
import com.mycompany.generic.entity.RoleEntity;
import com.mycompany.generic.entity.ServiceTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceTypesRepository extends JpaRepository<ServiceTypes, Long> {
    Optional<ServiceTypes> findByName(ESType name);
}
