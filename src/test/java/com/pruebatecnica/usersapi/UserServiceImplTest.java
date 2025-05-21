package com.pruebatecnica.usersapi.service;


import com.pruebatecnica.usersapi.model.User;
import com.pruebatecnica.usersapi.repository.UserRepository;
import com.pruebatecnica.usersapi.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setNombre("Juan");
        user.setEmail("juan@example.com");
    }

    @Test
    void testCrearUsuario(){
        when(userRepository.save(any(User.class))).thenReturn(user);

        User creado = userService.crearUsuario(user);

        assertNotNull(creado);
        assertEquals("Juan", creado.getNombre());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testObtenerPorId_Existente() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User resultado = userService.getUsuarioPorId(1L);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
    }

    @Test
    void testObtenerPorId_NoExistente() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> userService.getUsuarioPorId(1L));

        assertTrue(exception.getMessage().contains("no encontrado"));
    }

    @Test
    void testActualizarParcial() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        Map<String, Object> campos = new HashMap<>();
        campos.put("nombre", "Pedro");

        User actualizado = userService.actualizarParcial(1L, campos);

        assertEquals("Pedro", actualizado.getNombre());
        verify(userRepository).save(user);
    }


    @Test
    void testEliminarUsuario() {
        // Simular que el usuario sí existe
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Ejecutar el método
        userService.eliminarUsuario(1L);

        // Verificar que se llamó a delete con el usuario esperado
        verify(userRepository).delete(user);
    }


    @Test
    void testEliminarUsuario_NoExistente() {
        // Simular que no se encuentra el usuario
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificar que se lanza una excepción con el mensaje adecuado
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> userService.eliminarUsuario(1L));

        assertTrue(exception.getMessage().contains("Usuario no encontrado"));
    }



    // Puedes agregar más tests para eliminar y actualizarParcial...
}
