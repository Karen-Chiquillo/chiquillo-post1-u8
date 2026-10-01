package com.example.auditoria.usecase.impl;

import com.example.auditoria.usecase.ObtenerDashboardAuditoriaUseCase;
import com.example.auditoria.usecase.port.DashboardAuditoriaView;
import com.example.auditoria.usecase.port.HallazgoRepositoryPort;

public class ObtenerDashboardAuditoriaService implements ObtenerDashboardAuditoriaUseCase {

    private final HallazgoRepositoryPort repositoryPort;

    public ObtenerDashboardAuditoriaService(HallazgoRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public DashboardAuditoriaView ejecutar() {
        return new DashboardAuditoriaView(
                repositoryPort.contarPorSeveridad(),
                repositoryPort.contarPorEstado(),
                repositoryPort.calcularPromedioDiasCierrePorArea()
        );
    }
}