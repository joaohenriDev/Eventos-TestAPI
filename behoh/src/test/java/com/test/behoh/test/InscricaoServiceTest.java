package com.test.behoh.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.test.behoh.models.Eventos;
import com.test.behoh.models.Usuario;
import com.test.behoh.repository.EventoRepository;
import com.test.behoh.repository.InscricaoRepository;
import com.test.behoh.repository.UsuarioRepository;
import com.test.behoh.services.InscricaoService;

public class InscricaoServiceTest {

    @Mock
    private InscricaoRepository inscricaoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EventoRepository eventoRepository;

    @InjectMocks
    private InscricaoService inscricaoService;

    private Usuario usuario;
    private Eventos evento;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setId(1L);

        evento = new Eventos();
        evento.setId(1L);
        evento.setVagas(10);
        evento.setDataHoraInicio(LocalDateTime.now().plusDays(1));
        evento.setDataHoraFim(LocalDateTime.now().plusDays(2));
    }

    @Test
    public void testInscricaoBemSucedida() {
        when(usuarioRepository.findById(1L)).thenReturn(java.util.Optional.of(usuario));
        when(eventoRepository.findById(1L)).thenReturn(java.util.Optional.of(evento));
        when(inscricaoRepository.existsByEventoAndUsuario(evento, usuario)).thenReturn(false);

        String resultado = inscricaoService.inscreverUsuario(1L, 1L);

        assertEquals("Inscrição realizada com sucesso", resultado);
    }
    
    @Test
    public void testInscricaoSemVagas() {
        evento.setVagas(0);

        when(usuarioRepository.findById(1L)).thenReturn(java.util.Optional.of(usuario));
        when(eventoRepository.findById(1L)).thenReturn(java.util.Optional.of(evento));

        String resultado = inscricaoService.inscreverUsuario(1L, 1L);

        assertEquals("Não há vagas disponíveis para este evento", resultado);
    }
}
