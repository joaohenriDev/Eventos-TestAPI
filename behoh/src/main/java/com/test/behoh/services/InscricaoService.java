package com.test.behoh.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.behoh.models.Eventos;
import com.test.behoh.models.Usuario;
import com.test.behoh.repository.EventoRepository;
import com.test.behoh.repository.UsuarioRepository;

@Service
public class InscricaoService {
	
	@Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    
    public String inscreverUsuarioEmEvento(Long usuarioId, Long eventoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Eventos evento = eventoRepository.findById(eventoId)
            .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        if (evento.getInscritos().size() >= evento.getVagas()) {
            return "Evento lotado";
        }

        if (LocalDateTime.now().isAfter(evento.getDataHoraInicio())) {
            return "Inscrição não permitida após o início do evento";
        }

        evento.getInscritos().add(usuario);
        eventoRepository.save(evento);

        return "Inscrição realizada com sucesso";
    }

}
