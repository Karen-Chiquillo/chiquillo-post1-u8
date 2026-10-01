package com.example.auditoria.usecase;

import com.example.auditoria.domain.valueobject.HallazgoId;

import java.time.LocalDate;

public interface IniciarRemediacionUseCase {
    void ejecutar(HallazgoId id, String responsable, LocalDate fechaLimite, String notas);
}