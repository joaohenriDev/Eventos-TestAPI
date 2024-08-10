package com.test.behoh.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.behoh.models.Eventos;
import com.test.behoh.models.Usuario;
import com.test.behoh.repository.EventoRepository;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Eventos> listarEventos() {
        return eventoRepository.findAll();
    }

    public Eventos buscarEventoPorId(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public Eventos criarEvento(Eventos evento) {
        return eventoRepository.save(evento);
    }

    public List<Usuario> listarInscritos(Long eventoId) {
        // Implementar a lógica para listar inscritos no evento
        return null; // Substitua pelo código real
    }
}
