package com.pruebatecnica.usersapi.controller;


import com.pruebatecnica.usersapi.model.User;
import com.pruebatecnica.usersapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    private final UserService userService;

    //Constructor para inyectar el UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Endpoint para crear un usuario (POST /api/usuarios)
    @PostMapping
    public User crearUsuario(@RequestBody User user) {
        return userService.crearUsuario(user);
    }

    //Endpoint para listar a todos los usuarios (GET /api/usuarios)
    @GetMapping
    public List<User> listarUsuarios() {
        return userService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUsuarioPorId(@PathVariable long id) {
        User user = userService.getUsuarioPorId(id);
        return ResponseEntity.ok(user);
    }

    // Endpoint para actualizar parcialmente un usuario (PATCH /api/usuarios/{id})
    @PatchMapping("/{id}")
    public User actualizarUsuario(@PathVariable long id, @RequestBody Map<String, Object> updates) {
        return userService.actualizarParcial(id, updates);
    }


}
