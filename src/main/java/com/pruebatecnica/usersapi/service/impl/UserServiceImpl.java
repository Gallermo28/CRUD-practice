package com.pruebatecnica.usersapi.service.impl;

import com.pruebatecnica.usersapi.model.User;
import com.pruebatecnica.usersapi.repository.UserRepository;
import com.pruebatecnica.usersapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Service  // Esta anotación hace que Spring registre esta clase como un bean
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User crearUsuario(User user){
        return userRepository.save(user);
    }

    @Override
    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    @Override
    public User getUsuarioPorId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }


    @Override
    public User actualizarParcial(Long id, Map<String, Object> updates) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "nombre":
                    user.setNombre((String) value);
                    break;
                case "email":
                    user.setEmail((String) value);
            }
        });
        return userRepository.save(user);
    }

    @Override
    public void eliminarUsuario(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        userRepository.delete(user);
    }



}
