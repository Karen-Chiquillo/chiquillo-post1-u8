package com.example.auditoria.usecase.port;

import java.util.List;

public interface HistorialAuditoriaPort {

    void registrarCambio(String hallazgoId, String estadoAnterior, String estadoNuevo, String motivo, String usuario);

    List<CambioEstadoView> buscarPorHallazgoId(String hallazgoId);
}