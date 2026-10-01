package com.example.auditoria.usecase.impl;

import com.example.auditoria.usecase.ConsultarHistorialUseCase;
import com.example.auditoria.usecase.port.CambioEstadoView;
import com.example.auditoria.usecase.port.HistorialAuditoriaPort;

import java.util.List;

public class ConsultarHistorialService implements ConsultarHistorialUseCase {

    private final HistorialAuditoriaPort historialPort;

    public ConsultarHistorialService(HistorialAuditoriaPort historialPort) {
        this.historialPort = historialPort;
    }

    @Override
    public List<CambioEstadoView> ejecutar(String hallazgoId) {
        return historialPort.buscarPorHallazgoId(hallazgoId);
    }
}