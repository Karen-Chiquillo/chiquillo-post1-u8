package com.example.auditoria.adapter.out.persistence;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_cambios_estado")
public class HistorialCambioEstadoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String hallazgoId;

    private String estadoAnterior;

    @Column(nullable = false)
    private String estadoNuevo;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    private String motivo;

    private String usuario;

    public HistorialCambioEstadoJpaEntity() {
    }

    public HistorialCambioEstadoJpaEntity(String hallazgoId, String estadoAnterior, String estadoNuevo,
                                         LocalDateTime fechaHora, String motivo, String usuario) {
        this.hallazgoId = hallazgoId;
        this.estadoAnterior = estadoAnterior;
        this.estadoNuevo = estadoNuevo;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.usuario = usuario;
    }

    public Long getId() { return id; }
    public String getHallazgoId() { return hallazgoId; }
    public String getEstadoAnterior() { return estadoAnterior; }
    public String getEstadoNuevo() { return estadoNuevo; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public String getMotivo() { return motivo; }
    public String getUsuario() { return usuario; }
}