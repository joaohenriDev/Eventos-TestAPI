package com.test.behoh.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.behoh.models.Inscricao;
import com.test.behoh.models.Usuario;
import com.test.behoh.services.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @PostMapping("/usuario")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }
    
    @GetMapping("/{usuarioId}/inscricoes")
    public ResponseEntity<List<Inscricao>> listarInscricoes(@PathVariable Long usuarioId) {
        List<Inscricao> inscricoes = usuarioService.listarInscricoes(usuarioId);
        return new ResponseEntity<>(inscricoes, HttpStatus.OK);
    }
}
