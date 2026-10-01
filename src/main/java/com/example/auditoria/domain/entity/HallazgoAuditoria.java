package com.example.auditoria.domain.entity;

import com.example.auditoria.domain.valueobject.EstadoHallazgo;
import com.example.auditoria.domain.valueobject.HallazgoId;
import com.example.auditoria.domain.valueobject.PlanRemediacion;
import com.example.auditoria.domain.valueobject.Severidad;
import com.example.auditoria.domain.valueobject.TransicionInvalidaException;

import java.time.LocalDate;
import java.util.Objects;

public class HallazgoAuditoria {
    private final HallazgoId id;
    private final String titulo;
    private final String descripcion;
    private final String areaResponsable;
    private final Severidad severidad;
    private final LocalDate fechaDeteccion;
    private EstadoHallazgo estado;
    private PlanRemediacion planRemediacion;
    private LocalDate fechaCierre;

    public HallazgoAuditoria(HallazgoId id, String titulo, String descripcion,
                             String areaResponsable, Severidad severidad, LocalDate fechaDeteccion) {
        this.id = Objects.requireNonNull(id, "El id no puede ser nulo");
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El titulo es obligatorio");
        }
        if (areaResponsable == null || areaResponsable.isBlank()) {
            throw new IllegalArgumentException("El area responsable es obligatoria");
        }
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.areaResponsable = areaResponsable;
        this.severidad = Objects.requireNonNull(severidad, "La severidad es obligatoria");
        this.fechaDeteccion = Objects.requireNonNull(fechaDeteccion, "La fecha de deteccion es obligatoria");
        this.estado = EstadoHallazgo.ABIERTO;
    }

    public EstadoHallazgo iniciarRemediacion(PlanRemediacion plan) {
        EstadoHallazgo anterior = transicionar(EstadoHallazgo.EN_REMEDIACION);
        this.planRemediacion = Objects.requireNonNull(plan, "El plan de remediacion es obligatorio");
        return anterior;
    }

    public EstadoHallazgo cerrar() {
        if (this.planRemediacion == null) {
            throw new IllegalStateException("No se puede cerrar un hallazgo sin plan de remediacion");
        }
        EstadoHallazgo anterior = transicionar(EstadoHallazgo.CERRADO);
        this.fechaCierre = LocalDate.now();
        return anterior;
    }

    public EstadoHallazgo reabrir() {
        EstadoHallazgo anterior = transicionar(EstadoHallazgo.REABIERTO);
        this.fechaCierre = null;
        return anterior;
    }

    private EstadoHallazgo transicionar(EstadoHallazgo destino) {
        if (!this.estado.puedeTransicionarA(destino)) {
            throw new TransicionInvalidaException(this.estado, destino);
        }
        EstadoHallazgo anterior = this.estado;
        this.estado = destino;
        return anterior;
    }

    // Getters de lectura (sin setters arbitrarios: las mutaciones se hacen por métodos de negocio)
    public HallazgoId getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getAreaResponsable() {
        return areaResponsable;
    }

    public Severidad getSeveridad() {
        return severidad;
    }

    public LocalDate getFechaDeteccion() {
        return fechaDeteccion;
    }

    public EstadoHallazgo getEstado() {
        return estado;
    }

    public PlanRemediacion getPlanRemediacion() {
        return planRemediacion;
    }

    public LocalDate getFechaCierre() {
        return fechaCierre;
    }
}