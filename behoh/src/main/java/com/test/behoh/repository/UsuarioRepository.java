package com.test.behoh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.test.behoh.models.Inscricao;
import com.test.behoh.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    @Query("SELECT i FROM Inscricao i WHERE i.usuario.id = :usuarioId")
    List<Inscricao> findInscricoesByUsuarioId(@Param("usuarioId") Long usuarioId);
}
