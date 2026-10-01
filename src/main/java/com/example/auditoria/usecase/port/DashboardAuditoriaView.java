package com.example.auditoria.usecase.port;

import java.util.List;

public record DashboardAuditoriaView(
        List<ConteoCategoria> hallazgosPorSeveridad,
        List<ConteoCategoria> hallazgosPorEstado,
        List<PromedioCategoria> promedioDiasCierrePorArea
) {}