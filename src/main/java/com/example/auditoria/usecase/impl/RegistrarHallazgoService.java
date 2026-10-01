package com.example.auditoria.usecase.impl;

import com.example.auditoria.domain.entity.HallazgoAuditoria;
import com.example.auditoria.domain.valueobject.EstadoHallazgo;
import com.example.auditoria.domain.valueobject.HallazgoId;
import com.example.auditoria.domain.valueobject.Severidad;
import com.example.auditoria.usecase.RegistrarHallazgoUseCase;
import com.example.auditoria.usecase.port.HallazgoRepositoryPort;
import com.example.auditoria.usecase.port.HistorialAuditoriaPort;

import java.time.LocalDate;

public class RegistrarHallazgoService implements RegistrarHallazgoUseCase {
    private final HallazgoRepositoryPort repo;
    private final HistorialAuditoriaPort historial;

    public RegistrarHallazgoService(HallazgoRepositoryPort repo, HistorialAuditoriaPort historial) {
        this.repo = repo;
        this.historial = historial;
    }

    @Override
    public HallazgoId ejecutar(String titulo, String descripcion, String areaResponsable,
                               Severidad severidad, LocalDate fechaDeteccion) {
        HallazgoAuditoria hallazgo = new HallazgoAuditoria(
                HallazgoId.nuevo(), titulo, descripcion, areaResponsable, severidad, fechaDeteccion);
        repo.guardar(hallazgo);
        historial.registrarCambio(hallazgo.getId().toString(), null, EstadoHallazgo.ABIERTO.name(),
                "Registro inicial de hallazgo", "AUDITOR");
        return hallazgo.getId();
    }
}