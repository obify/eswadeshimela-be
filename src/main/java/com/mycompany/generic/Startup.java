package com.mycompany.generic;

import com.mycompany.generic.constant.ERole;
import com.mycompany.generic.constant.ESType;
import com.mycompany.generic.entity.RoleEntity;
import com.mycompany.generic.entity.ServiceTypes;
import com.mycompany.generic.repository.RoleRepository;
import com.mycompany.generic.repository.ServiceTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Startup implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ServiceTypesRepository serviceTypesRepository;

    @Override
    public void run(String... args) throws Exception {
        Optional<RoleEntity> optRoleAdmin = roleRepository.findByName(ERole.ROLE_ADMIN);
        if(optRoleAdmin.isEmpty()){
            RoleEntity role = new RoleEntity();
            role.setName(ERole.ROLE_ADMIN);
            roleRepository.save(role);
        }

        Optional<RoleEntity> optRoleOwn = roleRepository.findByName(ERole.ROLE_PROVIDER);
        if(optRoleOwn.isEmpty()){
            RoleEntity role = new RoleEntity();
            role.setName(ERole.ROLE_PROVIDER);
            roleRepository.save(role);
        }

        Optional<RoleEntity> optRoleMe = roleRepository.findByName(ERole.ROLE_USER);
        if(optRoleMe.isEmpty()){
            RoleEntity role = new RoleEntity();
            role.setName(ERole.ROLE_USER);
            roleRepository.save(role);
        }

        Optional<ServiceTypes> optStM = serviceTypesRepository.findByName(ESType.MEDICARE);
        if(optStM.isEmpty()){
            ServiceTypes st = new ServiceTypes();
            st.setName(ESType.MEDICARE);
            serviceTypesRepository.save(st);
        }

        Optional<ServiceTypes> optStT = serviceTypesRepository.findByName(ESType.TOURISM);
        if(optStT.isEmpty()){
            ServiceTypes st = new ServiceTypes();
            st.setName(ESType.TOURISM);
            serviceTypesRepository.save(st);
        }

    }
}
