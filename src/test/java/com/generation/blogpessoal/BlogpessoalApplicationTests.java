package com.generation.blogpessoal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.generation.blogpessoal.repository.UsuarioRepository;

@SpringBootTest
class BlogpessoalApplicationTests {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void contextLoads() {
        assertNotNull(usuarioRepository, "O bean UsuarioRepository deve estar carregado no contexto");
    }
}

