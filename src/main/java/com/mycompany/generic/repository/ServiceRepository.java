package com.mycompany.generic.repository;

import com.mycompany.generic.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    Optional<ServiceEntity> findByIdAndPinCodeContains(Long serviceId, String pinCode);
    List<ServiceEntity> findAllByUserId(Long userId);
}
