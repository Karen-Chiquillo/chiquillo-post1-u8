package com.example.auditoria.usecase.impl;

import com.example.auditoria.domain.entity.HallazgoAuditoria;
import com.example.auditoria.domain.valueobject.EstadoHallazgo;
import com.example.auditoria.domain.valueobject.HallazgoId;
import com.example.auditoria.usecase.HallazgoNotFoundException;
import com.example.auditoria.usecase.ReabrirHallazgoUseCase;
import com.example.auditoria.usecase.port.HallazgoRepositoryPort;
import com.example.auditoria.usecase.port.HistorialAuditoriaPort;

public class ReabrirHallazgoService implements ReabrirHallazgoUseCase {
    private final HallazgoRepositoryPort repo;
    private final HistorialAuditoriaPort historial;

    public ReabrirHallazgoService(HallazgoRepositoryPort repo, HistorialAuditoriaPort historial) {
        this.repo = repo;
        this.historial = historial;
    }

    @Override
    public void ejecutar(HallazgoId id, String motivo) {
        HallazgoAuditoria hallazgo = repo.buscarPorId(id)
                .orElseThrow(() -> new HallazgoNotFoundException(id));
        EstadoHallazgo anterior = hallazgo.reabrir();
        repo.guardar(hallazgo);
        historial.registrarCambio(id.toString(), anterior.name(), EstadoHallazgo.REABIERTO.name(),
                motivo, "AUDITOR");
    }
}