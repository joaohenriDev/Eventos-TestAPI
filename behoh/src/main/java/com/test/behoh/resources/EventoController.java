package com.test.behoh.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.behoh.models.Eventos;
import com.test.behoh.repository.EventoRepository;

@RestController
@RequestMapping("/api")
public class EventoController {
	
	@Autowired
	private EventoRepository eventoRespository;
	

	@GetMapping("/eventos")
	public List<Eventos> listaEventos(){
		return eventoRespository.findAll();
	};
	
	@GetMapping("/evento/{id}")
	public Eventos listaEvento(@PathVariable(value="id") long id){
		return eventoRespository.findById(id);
	};
	
	@PostMapping("/evento")
	public Eventos salvaEvento(@RequestBody Eventos evento) {
		return  eventoRespository.save(evento);
	}
}
