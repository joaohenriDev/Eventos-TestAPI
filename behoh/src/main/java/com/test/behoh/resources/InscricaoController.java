package com.test.behoh.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.behoh.services.InscricaoService;

@RestController
@RequestMapping("/api/inscricoes")
public class InscricaoController {
	
	 @Autowired
	    private InscricaoService inscricaoService;

	    @PostMapping("/usuario/{usuarioId}/evento/{eventoId}")
	    public ResponseEntity<String> inscreverUsuarioEmEvento(@PathVariable Long usuarioId, @PathVariable Long eventoId) {
	        String resultado = inscricaoService.inscreverUsuarioEmEvento(usuarioId, eventoId);
	        if (resultado.equals("Inscrição realizada com sucesso")) {
	            return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	        } else {
	            return new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
	        }
	    }
}
