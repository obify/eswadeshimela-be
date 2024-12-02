package com.mycompany.generic.controller;

import com.mycompany.generic.dto.LocationDTO;
import com.mycompany.generic.entity.LocationEntity;
import com.mycompany.generic.entity.ServiceEntity;
import com.mycompany.generic.entity.ServiceTypes;
import com.mycompany.generic.repository.LocationRepository;
import com.mycompany.generic.repository.ServiceTypesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/common")
public class CommonController {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ServiceTypesRepository serviceTypesRepository;

    //@PreAuthorize("hasRole('PROVIDER') or hasRole('ADMIN')")
    @PostMapping("/locations/add")
    public ResponseEntity<LocationEntity> addLocation(@RequestBody LocationDTO locationDTO){
        LocationEntity locationEntity = new LocationEntity();
        BeanUtils.copyProperties(locationDTO, locationEntity);
        locationEntity = locationRepository.save(locationEntity);
        return new ResponseEntity<>(locationEntity, HttpStatus.CREATED);
    }

    @GetMapping("/locations/all")
    public ResponseEntity<List<LocationDTO>> allLocations(){
        List<LocationEntity> locs = locationRepository.findAll();
        List<LocationDTO> locsDto = locs.stream().map((loc)->{
                LocationDTO dto = new LocationDTO();
                dto.setId(loc.getId());
                dto.setName(loc.getName());
                return dto;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(locsDto, HttpStatus.OK);
    }
    @GetMapping("/serviceTypes/all")
    public ResponseEntity<List<ServiceTypes>> allServiceTypes(){
        return new ResponseEntity<>(serviceTypesRepository.findAll(), HttpStatus.OK);
    }
}
