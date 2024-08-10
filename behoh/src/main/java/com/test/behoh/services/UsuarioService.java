package com.test.behoh.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.behoh.models.Inscricao;
import com.test.behoh.models.Usuario;
import com.test.behoh.repository.InscricaoRepository;
import com.test.behoh.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private InscricaoRepository inscricaoRepository;

    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    public List<Inscricao> listarInscricoes(Long usuarioId) {
        return usuarioRepository.findInscricoesByUsuarioId(usuarioId);
    }
}
