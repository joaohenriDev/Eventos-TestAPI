package com.test.behoh.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_INSCRICAO")
public class Inscricao {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Eventos evento;

    @Column(nullable = false)
    private LocalDateTime dataInscricao;

    @Column(nullable = true)
    private LocalDateTime entradaEm;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Eventos getEvento() {
		return evento;
	}

	public void setEvento(Eventos evento) {
		this.evento = evento;
	}

	public LocalDateTime getInscricaoEm() {
		return dataInscricao;
	}

	public void setDataInscricao(LocalDateTime inscricaoEm) {
		this.dataInscricao = inscricaoEm;
	}

	public LocalDateTime getEntradaEm() {
		return entradaEm;
	}

	public void setEntradaEm(LocalDateTime entradaEm) {
		this.entradaEm = entradaEm;
	}
    
 
}
