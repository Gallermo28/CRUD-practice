package com.pruebatecnica.usersapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.persistence.*;  // Importa las anotaciones para trabajar con JPA (persistencia)
import lombok.*;              // Importa las anotaciones de Lombok para evitar código repetitivo

// Indica que esta clase es una entidad de base de datos
@Entity

// Lombok genera automáticamente los getters, setters, equals, hashCode y toString
@Data

// Lombok genera un constructor sin argumentos
@NoArgsConstructor

// Lombok genera un constructor con todos los argumentos
@AllArgsConstructor

// Lombok crea un constructor tipo builder para facilitar la creación de objetos
@Builder

public class User {

    @Id                                                         // Indica que este campo es la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY)         // Indica que el valor del ID se genera automáticamente con estrategia de auto-incremento
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;                                      // Campo para guardar el nombre del usuario

    @NotBlank(message = "El email no puede estar vacio")
    @Email(message = "El formato del email no es valido")
    private String email;                                       // Campo para guardar el email del usuario

    // Getters y setters
}
