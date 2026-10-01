package com.example.auditoria.adapter.out.persistence;

import com.example.auditoria.domain.valueobject.EstadoHallazgo;
import com.example.auditoria.domain.valueobject.Severidad;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "hallazgos")
public class HallazgoJpaEntity {

    @Id
    private String id;
    private String titulo;
    private String descripcion;
    private String areaResponsable;

    @Enumerated(EnumType.STRING)
    private Severidad severidad;

    @Enumerated(EnumType.STRING)
    private EstadoHallazgo estado;

    private LocalDate fechaDeteccion;
    private LocalDate fechaCierre;
    private String planResponsable;
    private LocalDate planFechaLimite;
    private String planNotas;

    public HallazgoJpaEntity() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getAreaResponsable() { return areaResponsable; }
    public void setAreaResponsable(String areaResponsable) { this.areaResponsable = areaResponsable; }

    public Severidad getSeveridad() { return severidad; }
    public void setSeveridad(Severidad severidad) { this.severidad = severidad; }

    public EstadoHallazgo getEstado() { return estado; }
    public void setEstado(EstadoHallazgo estado) { this.estado = estado; }

    public LocalDate getFechaDeteccion() { return fechaDeteccion; }
    public void setFechaDeteccion(LocalDate fechaDeteccion) { this.fechaDeteccion = fechaDeteccion; }

    public LocalDate getFechaCierre() { return fechaCierre; }
    public void setFechaCierre(LocalDate fechaCierre) { this.fechaCierre = fechaCierre; }

    public String getPlanResponsable() { return planResponsable; }
    public void setPlanResponsable(String planResponsable) { this.planResponsable = planResponsable; }

    public LocalDate getPlanFechaLimite() { return planFechaLimite; }
    public void setPlanFechaLimite(LocalDate planFechaLimite) { this.planFechaLimite = planFechaLimite; }

    public String getPlanNotas() { return planNotas; }
    public void setPlanNotas(String planNotas) { this.planNotas = planNotas; }
}