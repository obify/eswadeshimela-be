package com.mycompany.generic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mycompany.generic.constant.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long serviceId;
    private Long bookingUserId;
    private String pickupPincode;
    private String pickupAddress;
    @Enumerated(EnumType.STRING)
    private EStatus status;
    //@JsonFormat(pattern="y-MM-dd h:mm:ss a")
    private LocalDateTime bookingDateTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Double amountPaid;
    private String remark;
}
