package com.mycompany.generic.repository;

import com.mycompany.generic.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> findAllByServiceId(Long serviceId);
    List<BookingEntity> findAllByBookingUserId(Long bookingUserId);
    Optional<BookingEntity> findByServiceId(Long serviceId);
}
