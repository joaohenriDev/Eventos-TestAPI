package com.test.behoh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.behoh.models.Eventos;

public interface EventoRepository extends JpaRepository<Eventos, Long>{

	
	Eventos findById(long id);
}
