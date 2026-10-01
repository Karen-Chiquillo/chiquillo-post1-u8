package com.example.auditoria.usecase.impl;

import com.example.auditoria.domain.entity.HallazgoAuditoria;
import com.example.auditoria.domain.valueobject.HallazgoId;
import com.example.auditoria.domain.valueobject.Severidad;
import com.example.auditoria.usecase.RegistrarHallazgoUseCase;
import com.example.auditoria.usecase.port.HallazgoRepositoryPort;

import java.time.LocalDate;

public class RegistrarHallazgoService implements RegistrarHallazgoUseCase {
    private final HallazgoRepositoryPort repo;

    public RegistrarHallazgoService(HallazgoRepositoryPort repo) {
        this.repo = repo;
    }

    @Override
    public HallazgoId ejecutar(String titulo, String descripcion, String areaResponsable,
                               Severidad severidad, LocalDate fechaDeteccion) {
        HallazgoAuditoria hallazgo = new HallazgoAuditoria(
                HallazgoId.nuevo(), titulo, descripcion, areaResponsable, severidad, fechaDeteccion);
        repo.guardar(hallazgo);
        return hallazgo.getId();
    }
}