package com.pruebatecnica.usersapi.repository;

import com.pruebatecnica.usersapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Extiende JpaRepository para heredar métodos CRUD para la entidad User
// JpaRepository<T, ID> recibe la clase de la entidad y el tipo de su clave primaria
public interface UserRepository extends JpaRepository<User, Long> {
    // No es necesario agregar métodos si solo usas las operaciones básicas (save, findAll, etc.)
}
