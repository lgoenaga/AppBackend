package com.iudigital.appbackend.controller;


import com.iudigital.appbackend.model.Rol;
import com.iudigital.appbackend.service.RolService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    final
    RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("")
    public List<Rol> getAllRoles() {
        return rolService.getAllRoles();
    }

    @PostMapping("")
    public String createRol(@RequestBody Rol rol) {
        return rolService.createRol(rol);
    }

    @PutMapping("/{id}")
    public String updateRol(@PathVariable Long id, @RequestBody Rol rol) {
        return rolService.updateRol(id, rol);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return rolService.deleteUser(id);
    }

}
