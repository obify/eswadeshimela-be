package com.mycompany.generic.controller;

import com.mycompany.generic.constant.EStatus;
import com.mycompany.generic.dto.BookingResponseDTO;
import com.mycompany.generic.entity.BookingEntity;
import com.mycompany.generic.entity.ServiceEntity;
import com.mycompany.generic.entity.UserEntity;
import com.mycompany.generic.repository.BookingRepository;
import com.mycompany.generic.repository.ServiceRepository;
import com.mycompany.generic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    @PostMapping("/updateTrip")
    public ResponseEntity<BookingEntity> updateTrip(@RequestBody BookingEntity bookingEntity){
        Optional<BookingEntity> optBe = bookingRepository.findByServiceId(bookingEntity.getServiceId());
        if(optBe.isPresent()){
            BookingEntity be = optBe.get();
            be.setAmountPaid(bookingEntity.getAmountPaid());
            be.setRemark(bookingEntity.getRemark());
            be.setStatus(bookingEntity.getStatus());
            be = bookingRepository.save(be);
            return new ResponseEntity<>(be, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<BookingEntity> updateStatus(@RequestBody BookingEntity bookingEntity){
        Optional<BookingEntity> optBe = bookingRepository.findByServiceId(bookingEntity.getServiceId());
        if(optBe.isPresent()){
            BookingEntity be = optBe.get();
            be.setStatus(bookingEntity.getStatus());
            be = bookingRepository.save(be);
            return new ResponseEntity<>(be, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/new")
    public ResponseEntity<BookingEntity> newBooking(@RequestBody BookingEntity entity){
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setStatus(EStatus.NEW);
        entity = bookingRepository.save(entity);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }
    @GetMapping("/all/{serviceId}")
    public ResponseEntity<List<BookingResponseDTO>> getAllBookingForService(@PathVariable Long serviceId){
        List<BookingResponseDTO> bookingsDto = new ArrayList<>();
        BookingResponseDTO bookingResponseDTO = null;
        List<BookingEntity> bookings = bookingRepository.findAllByServiceId(serviceId);
        for(BookingEntity be :bookings){
           Optional<UserEntity> optUe =  userRepository.findById(be.getBookingUserId());
           if(optUe.isPresent()){
               bookingResponseDTO = new BookingResponseDTO();
               bookingResponseDTO.setBooking(be);
               bookingResponseDTO.setUser(optUe.get());
           }
            bookingsDto.add(bookingResponseDTO);
        }
        return new ResponseEntity<>(bookingsDto, HttpStatus.OK);
    }
    @GetMapping("/users/all/{userId}")
    public ResponseEntity<List<BookingResponseDTO>> getAllBookingForUser(@PathVariable Long userId){
        List<BookingResponseDTO> bookingsDto = new ArrayList<>();
        BookingResponseDTO bookingResponseDTO = null;
        List<BookingEntity> bookings = bookingRepository.findAllByBookingUserId(userId);
        for(BookingEntity be :bookings){

            Optional<ServiceEntity> optSe =  serviceRepository.findById(be.getServiceId());
            if(optSe.isPresent()){
                bookingResponseDTO = new BookingResponseDTO();
                bookingResponseDTO.setBooking(be);
                bookingResponseDTO.setService(optSe.get());
                Optional<UserEntity> optUe =  userRepository.findById(optSe.get().getUserId());
                if(optUe.isPresent()){
                    bookingResponseDTO.setServiceProvider(optUe.get());
                }
            }
            bookingsDto.add(bookingResponseDTO);
        }
        return new ResponseEntity<>(bookingsDto, HttpStatus.OK);
    }
}
