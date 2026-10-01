package com.example.auditoria.usecase.port;

import java.time.LocalDateTime;

public record CambioEstadoView(
        Long id,
        String hallazgoId,
        String estadoAnterior,
        String estadoNuevo,
        LocalDateTime fechaHora,
        String motivo,
        String usuario
) {}