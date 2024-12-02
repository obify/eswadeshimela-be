package com.mycompany.generic.controller;

import com.mycompany.generic.dto.LocationDTO;
import com.mycompany.generic.dto.ServiceRequestDTO;
import com.mycompany.generic.dto.ServiceResponseDTO;
import com.mycompany.generic.entity.*;
import com.mycompany.generic.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/services")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private LocationServiceRepository locationServiceRepository;
    @Autowired
    private ServiceAndServiceTypesRepository serviceAndServiceTypesRepository;
    @Autowired
    private ServiceTypesRepository serviceTypesRepository;

    @PreAuthorize("hasRole('PROVIDER') or hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ServiceEntity> addService(@RequestBody ServiceRequestDTO serviceRequestDTO) {

        ServiceEntity serviceEntity = new ServiceEntity();
        BeanUtils.copyProperties(serviceRequestDTO, serviceEntity);
        serviceEntity.setActive(true);
        serviceEntity.setUserId(serviceRequestDTO.getUserId());
        serviceEntity.setRating(getRandomNumber(2, 5));
        serviceEntity.setCreatedAt(LocalDateTime.now());
        serviceEntity.setUpdatedAt(LocalDateTime.now());
        serviceEntity = serviceRepository.save(serviceEntity);

        for (LocationDTO loc : serviceRequestDTO.getLocations()) {
            LocationServiceEntity lse = new LocationServiceEntity();
            lse.setServiceId(serviceEntity.getId());
            lse.setLocationId(loc.getId());
            locationServiceRepository.save(lse);
        }

        for (ServiceTypes st : serviceRequestDTO.getServiceTypes()) {
            ServiceAndServiceTypes lse = new ServiceAndServiceTypes();
            lse.setServiceId(serviceEntity.getId());
            lse.setServiceTypeId(st.getId());
            serviceAndServiceTypesRepository.save(lse);
        }

        return new ResponseEntity<>(serviceEntity, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServiceEntity>> allServices() {
        return new ResponseEntity<>(serviceRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/users/all/{userId}")
    public ResponseEntity<List<ServiceEntity>> allServicesForUser(@PathVariable Long userId) {
        return new ResponseEntity<>(serviceRepository.findAllByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/service-detail/{serviceId}/{pinCode}")
    public ResponseEntity<ServiceResponseDTO> serviceDetail(@PathVariable Long serviceId, @PathVariable String pinCode) {
        Optional<ServiceEntity> optSe = serviceRepository.findById(serviceId);
        ServiceResponseDTO dto = null;
        if(optSe.isPresent()){
            dto = new ServiceResponseDTO();
            dto.setService(optSe.get());
            List<LocationServiceEntity> listLse = locationServiceRepository.findAllByServiceId(serviceId);
            List<LocationEntity> listLe = new ArrayList<>();
            for(LocationServiceEntity lse: listLse){
               Optional<LocationEntity> optLe =  locationRepository.findById(lse.getLocationId());
               if(optLe.isPresent()){
                   listLe.add(optLe.get());
               }
            }
            dto.setLocationList(listLe);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/matching-services/{serviceTypeId}/{pinCode}")
    public ResponseEntity<List<ServiceResponseDTO>> matchingServices(@PathVariable Long serviceTypeId, @PathVariable String pinCode) {
        List<ServiceAndServiceTypes> listSST = serviceAndServiceTypesRepository.findAllByServiceTypeId(serviceTypeId);
        List<ServiceResponseDTO> listServices = new ArrayList<>();
        ServiceResponseDTO srdto = null;
        for (ServiceAndServiceTypes sst : listSST) {
            Optional<ServiceEntity> optSe = serviceRepository.findByIdAndPinCodeContains(sst.getServiceId(), pinCode);
            if (optSe.isPresent()) {
                srdto = new ServiceResponseDTO();
                srdto.setService(optSe.get());
                Optional<ServiceTypes> optSts = serviceTypesRepository.findById(sst.getServiceTypeId());
                if (optSts.isPresent()) {
                    srdto.setServiceType(optSts.get());
                }
                List<LocationServiceEntity> listLse = locationServiceRepository.findAllByServiceId(sst.getServiceId());
                List<LocationEntity> listLe = new ArrayList<>();
                for(LocationServiceEntity lse: listLse){
                    Optional<LocationEntity> optLe =  locationRepository.findById(lse.getLocationId());
                    if(optLe.isPresent()){
                        listLe.add(optLe.get());
                    }
                }
                if(!listLe.isEmpty()) {
                    LocationEntity le = listLe.get(getRandomNumberInt(0, listLe.size() - 1));
                    srdto.setImg(le.getImage());
                }
                listServices.add(srdto);
            }
        }
        return new ResponseEntity<>(listServices, HttpStatus.OK);
    }

    public Double getRandomNumber(int min, int max) {
        return (Double) ((Math.random() * (max - min)) + min);
    }
    public int getRandomNumberInt(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
