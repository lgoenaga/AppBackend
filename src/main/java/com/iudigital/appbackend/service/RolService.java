package com.iudigital.appbackend.service;

import com.iudigital.appbackend.model.Rol;
import com.iudigital.appbackend.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class RolService {

    private static final String SUCCESSFULLY = "Process done correctly";
    private static final String NOT_FOUND = "Rol not found!";
    private static final String ALREADY_EXISTS = "Information already exists";
    final
    RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

    public String createRol(Rol rol) {

        Rol newRol = new Rol();

        newRol.setName(rol.getName().toUpperCase());
        newRol.setDescription(rol.getDescription().toLowerCase());

        if (rolRepository.existsByName(rol.getName())) {
            return ALREADY_EXISTS;
        }

        String createDate = new Date().toString();
        String updateDate = new Date().toString();

        newRol.setCreated_at(createDate);
        newRol.setUpdated_at(updateDate);

        rolRepository.save(newRol);
        return SUCCESSFULLY;
    }

    public String updateRol(Long id, Rol rol) {

        Rol exitRol = rolRepository.findById(id).orElse(null);

        if (exitRol == null) {
            return NOT_FOUND;
        }

        exitRol.setName(rol.getName().toUpperCase());
        exitRol.setDescription(rol.getDescription().toLowerCase());

        String updateDate = new Date().toString();


        exitRol.setUpdated_at(updateDate);

        rolRepository.save(exitRol);
        return SUCCESSFULLY;
    }

    public String deleteUser(Long id) {

        Rol exitRol = rolRepository.findById(id).orElse(null);

        if (exitRol == null) {
            return NOT_FOUND;
        }

        rolRepository.deleteById(id);
        return SUCCESSFULLY;
    }

}
