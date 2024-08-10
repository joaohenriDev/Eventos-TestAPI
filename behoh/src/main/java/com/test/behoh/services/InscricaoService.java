package com.test.behoh.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.behoh.models.Eventos;
import com.test.behoh.models.Inscricao;
import com.test.behoh.models.Usuario;
import com.test.behoh.repository.EventoRepository;
import com.test.behoh.repository.InscricaoRepository;
import com.test.behoh.repository.UsuarioRepository;

@Service
public class InscricaoService {
	
	@Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private InscricaoRepository inscricaoRepository;

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

        Inscricao inscricao = new Inscricao();
        inscricao.setUsuario(usuario);
        inscricao.setEvento(evento);
        inscricao.setInscricaoEm(LocalDateTime.now());

        inscricaoRepository.save(inscricao);

        return "Inscrição realizada com sucesso";
    }

    public String realizarEntradaUsuario(Long usuarioId, Long eventoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Eventos evento = eventoRepository.findById(eventoId)
            .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        Inscricao inscricao = inscricaoRepository.findByUsuarioAndEvento(usuario, evento)
            .orElseThrow(() -> new RuntimeException("Usuário não está inscrito no evento"));

        LocalDateTime agora = LocalDateTime.now();
        if (agora.isBefore(evento.getDataHoraInicio().minusHours(1)) || agora.isAfter(evento.getDataHoraFim())) {
            return "Entrada não permitida fora do período do evento";
        }

        if (inscricao.getEntradaEm() != null) {
            return "Usuário já registrou entrada no evento";
        }

        inscricao.setEntradaEm(agora);
        inscricaoRepository.save(inscricao);

        return "Entrada realizada com sucesso";
    }

    public String cancelarInscricao(Long usuarioId, Long eventoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Eventos evento = eventoRepository.findById(eventoId)
            .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        Inscricao inscricao = inscricaoRepository.findByUsuarioAndEvento(usuario, evento)
            .orElseThrow(() -> new RuntimeException("Inscrição não encontrada"));

        if (inscricao.getEntradaEm() != null) {
            return "Não é possível cancelar a inscrição após a entrada no evento";
        }

        inscricaoRepository.delete(inscricao);

        return "Inscrição cancelada com sucesso";
    }

}
