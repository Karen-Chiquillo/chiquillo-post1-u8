package com.example.auditoria.usecase.impl;

import com.example.auditoria.domain.entity.HallazgoAuditoria;
import com.example.auditoria.domain.valueobject.EstadoHallazgo;
import com.example.auditoria.domain.valueobject.HallazgoId;
import com.example.auditoria.usecase.CerrarHallazgoUseCase;
import com.example.auditoria.usecase.HallazgoNotFoundException;
import com.example.auditoria.usecase.port.HallazgoRepositoryPort;
import com.example.auditoria.usecase.port.HistorialAuditoriaPort;

public class CerrarHallazgoService implements CerrarHallazgoUseCase {
    private final HallazgoRepositoryPort repo;
    private final HistorialAuditoriaPort historial;

    public CerrarHallazgoService(HallazgoRepositoryPort repo, HistorialAuditoriaPort historial) {
        this.repo = repo;
        this.historial = historial;
    }

    @Override
    public void ejecutar(HallazgoId id) {
        HallazgoAuditoria hallazgo = repo.buscarPorId(id)
                .orElseThrow(() -> new HallazgoNotFoundException(id));
        EstadoHallazgo anterior = hallazgo.cerrar();
        repo.guardar(hallazgo);
        historial.registrarCambio(id.toString(), anterior.name(), EstadoHallazgo.CERRADO.name(),
                "Remediacion completada y validada", "AUDITOR");
    }
}