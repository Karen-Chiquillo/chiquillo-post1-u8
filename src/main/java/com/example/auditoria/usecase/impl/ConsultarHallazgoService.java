package com.example.auditoria.usecase.impl;

import com.example.auditoria.domain.entity.HallazgoAuditoria;
import com.example.auditoria.domain.valueobject.HallazgoId;
import com.example.auditoria.usecase.ConsultarHallazgoUseCase;
import com.example.auditoria.usecase.HallazgoNotFoundException;
import com.example.auditoria.usecase.port.HallazgoRepositoryPort;

import java.util.List;

public class ConsultarHallazgoService implements ConsultarHallazgoUseCase {
    private final HallazgoRepositoryPort repo;

    public ConsultarHallazgoService(HallazgoRepositoryPort repo) {
        this.repo = repo;
    }

    @Override
    public HallazgoAuditoria buscarPorId(HallazgoId id) {
        return repo.buscarPorId(id)
                .orElseThrow(() -> new HallazgoNotFoundException(id));
    }

    @Override
    public List<HallazgoAuditoria> listarTodos() {
        return repo.buscarTodos();
    }
}