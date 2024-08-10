package com.test.behoh.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.test.behoh.models.Eventos;
import com.test.behoh.models.Inscricao;

public interface EventoRepository extends JpaRepository<Eventos, Long> {
	
	Optional<Eventos> findById(Long id);
}
