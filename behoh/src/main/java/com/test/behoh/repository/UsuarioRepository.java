package com.test.behoh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.behoh.models.Eventos;
import com.test.behoh.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
