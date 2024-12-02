package com.mycompany.generic.repository;

import com.mycompany.generic.entity.ServiceAndServiceTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceAndServiceTypesRepository extends JpaRepository<ServiceAndServiceTypes, Long> {
    List<ServiceAndServiceTypes> findAllByServiceId(Long serviceId);
    List<ServiceAndServiceTypes> findAllByServiceTypeId(Long serviceTypeId);
}
