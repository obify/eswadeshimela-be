package com.mycompany.generic.repository;

import com.mycompany.generic.entity.LocationServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationServiceRepository extends JpaRepository<LocationServiceEntity, Long> {
    List<LocationServiceEntity> findAllByServiceId(Long serviceId);
    List<LocationServiceEntity> findAllByLocationId(Long locationId);
}
