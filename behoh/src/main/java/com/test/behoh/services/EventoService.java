package com.test.behoh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.behoh.models.Eventos;
import com.test.behoh.repository.EventoRepository;

@Service
public class EventoService {
	
	 @Autowired
	 private EventoRepository eventoRepository;

	 
	 public Eventos criarEvento(Eventos evento) {
	        return eventoRepository.save(evento);
	    }
}
