package com.example.auditoria.usecase.impl;

import com.example.auditoria.domain.entity.HallazgoAuditoria;
import com.example.auditoria.domain.valueobject.HallazgoId;
import com.example.auditoria.domain.valueobject.PlanRemediacion;
import com.example.auditoria.usecase.HallazgoNotFoundException;
import com.example.auditoria.usecase.IniciarRemediacionUseCase;
import com.example.auditoria.usecase.port.HallazgoRepositoryPort;

import java.time.LocalDate;

public class IniciarRemediacionService implements IniciarRemediacionUseCase {
    private final HallazgoRepositoryPort repo;

    public IniciarRemediacionService(HallazgoRepositoryPort repo) {
        this.repo = repo;
    }

    @Override
    public void ejecutar(HallazgoId id, String responsable, LocalDate fechaLimite, String notas) {
        HallazgoAuditoria hallazgo = repo.buscarPorId(id)
                .orElseThrow(() -> new HallazgoNotFoundException(id));
        hallazgo.iniciarRemediacion(new PlanRemediacion(responsable, fechaLimite, notas));
        repo.guardar(hallazgo);
    }
}