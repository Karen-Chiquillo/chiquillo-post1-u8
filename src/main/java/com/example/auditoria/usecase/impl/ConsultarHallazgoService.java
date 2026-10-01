package com.example.auditoria.usecase.impl;

import com.example.auditoria.adapter.in.web.dto.HallazgoResponse;
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
    public HallazgoResponse buscarPorId(HallazgoId id) {
        return repo.buscarPorId(id)
                .map(HallazgoResponse::fromDomain)
                .orElseThrow(() -> new HallazgoNotFoundException(id));
    }

    @Override
    public List<HallazgoResponse> listarTodos() {
        return repo.buscarTodos().stream()
                .map(HallazgoResponse::fromDomain)
                .toList();
    }
}