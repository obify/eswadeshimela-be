package com.mycompany.generic.repository;

import com.mycompany.generic.entity.LocationEntity;
import com.mycompany.generic.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

}
