package com.pruebatecnica.usersapi.service;

import com.pruebatecnica.usersapi.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    // Crea un nuevo usuario
    User crearUsuario(User user);

    // Lista todos los usuarios
    List<User> listarUsuarios();

    // Actualiza parcialmente un usuario por ID con los campos que se reciban
    User updatePartial(Long id, Map<String, Object> updates);

    User getUsuarioPorId(Long id);

    void eliminarUsuario(Long id);

}
