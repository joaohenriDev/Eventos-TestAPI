package com.test.behoh.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.behoh.models.Eventos;
import com.test.behoh.models.Inscricao;
import com.test.behoh.models.Usuario;
import com.test.behoh.repository.EventoRepository;
import com.test.behoh.repository.InscricaoRepository;
import com.test.behoh.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class InscricaoService {
	
	@Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private InscricaoRepository inscricaoRepository;
    
    public InscricaoService(InscricaoRepository inscricaoRepository,
            UsuarioRepository usuarioRepository,
            EventoRepository eventoRepository) {
    	this.inscricaoRepository = inscricaoRepository;
    	this.usuarioRepository = usuarioRepository;
    	this.eventoRepository = eventoRepository;
    }

    public String inscreverUsuario(Long usuarioId, Long eventoId) {
        // Verificar se o usuário e o evento existem
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Eventos evento = eventoRepository.findById(eventoId)
            .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado"));

        // Verificar se o usuário já está inscrito no evento
        if (inscricaoRepository.existsByEventoAndUsuario(evento, usuario)) {
            return "Usuário já inscrito neste evento";
        }

        // Verificar se o evento tem vagas disponíveis
        if (evento.getVagas() <= 0) {
            return "Não há vagas disponíveis para este evento";
        }

        // Verificar se o evento ainda não começou
        if (evento.getDataHoraInicio().isBefore(LocalDateTime.now())) {
            return "O evento já começou, não é possível se inscrever";
        }

        // Criar e salvar a nova inscrição
        Inscricao inscricao = new Inscricao();
        inscricao.setUsuario(usuario);
        inscricao.setEvento(evento);
        inscricao.setDataInscricao(LocalDateTime.now()); // Definindo o valor de dataInscricao
        inscricaoRepository.save(inscricao);

        // Atualizar o número de vagas disponíveis
        evento.setVagas(evento.getVagas() - 1);
        eventoRepository.save(evento);

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

    @Transactional
    public String cancelarInscricao(Long usuarioId, Long eventoId) {
        Inscricao inscricao = inscricaoRepository.findByUsuarioIdAndEventoId(usuarioId, eventoId)
            .orElseThrow(() -> new RuntimeException("Inscrição não encontrada"));

        Eventos evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        LocalDateTime agora = LocalDateTime.now();
        if (agora.isBefore(evento.getDataHoraInicio()) && agora.isBefore(evento.getDataHoraFim())) {
            return "Não é possível cancelar a inscrição durante o evento.";
        }
        if (evento.getDataHoraInicio().isBefore(agora)) {
            return "Não é possível cancelar a inscrição após o início do evento.";
        }

        evento.setVagas(evento.getVagas() + 1);
        eventoRepository.save(evento);

        inscricaoRepository.delete(inscricao);

        return "Inscrição cancelada com sucesso";
    }

}
