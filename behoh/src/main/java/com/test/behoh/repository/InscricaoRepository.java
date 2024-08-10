package com.test.behoh.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.behoh.models.Eventos;
import com.test.behoh.models.Inscricao;
import com.test.behoh.models.Usuario;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    Optional<Inscricao> findByUsuarioAndEvento(Usuario usuario, Eventos evento);
    
    @Query("SELECT i.usuario FROM Inscricao i WHERE i.evento.id = :eventoId")
    List<Usuario> findUsuariosByEventoId(@Param("eventoId") Long eventoId);

    @Query("SELECT i.evento FROM Inscricao i WHERE i.usuario.id = :usuarioId")
    List<Eventos> findEventosByUsuarioId(@Param("usuarioId") Long usuarioId);
}
