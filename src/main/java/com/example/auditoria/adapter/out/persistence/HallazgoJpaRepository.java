package com.example.auditoria.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HallazgoJpaRepository extends JpaRepository<HallazgoJpaEntity, String> {

    @Query("SELECT h.severidad, COUNT(h) FROM HallazgoJpaEntity h GROUP BY h.severidad")
    List<Object[]> contarPorSeveridad();

    @Query("SELECT h.estado, COUNT(h) FROM HallazgoJpaEntity h GROUP BY h.estado")
    List<Object[]> contarPorEstado();

    @Query("SELECT h.areaResponsable, h.fechaDeteccion, h.fechaCierre FROM HallazgoJpaEntity h WHERE h.fechaCierre IS NOT NULL")
    List<Object[]> obtenerHallazgosCerradosConFechas();
}