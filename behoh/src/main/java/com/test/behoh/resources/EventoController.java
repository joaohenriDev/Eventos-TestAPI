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

import com.test.behoh.models.Eventos;
import com.test.behoh.models.Usuario;
import com.test.behoh.services.EventoService;

@RestController
@RequestMapping("/api")
public class EventoController {
    
    @Autowired
    private EventoService eventoService;

    @GetMapping("/eventos")
    public List<Eventos> listaEventos() {
        return eventoService.listarEventos(); // Ajustado para usar o serviço
    }

    @GetMapping("/evento/{id}")
    public ResponseEntity<Eventos> listaEvento(@PathVariable(value = "id") long id) {
        Eventos evento = eventoService.buscarEventoPorId(id);
        if (evento != null) {
            return new ResponseEntity<>(evento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/evento")
    public ResponseEntity<Eventos> criarEvento(@RequestBody Eventos evento) {
        Eventos novoEvento = eventoService.criarEvento(evento);
        return new ResponseEntity<>(novoEvento, HttpStatus.CREATED);
    }

    @GetMapping("/{eventoId}/inscritos")
    public ResponseEntity<List<Usuario>> listarInscritos(@PathVariable Long eventoId) {
        List<Usuario> inscritos = eventoService.listarInscritos(eventoId);
        return new ResponseEntity<>(inscritos, HttpStatus.OK);
    }
}
