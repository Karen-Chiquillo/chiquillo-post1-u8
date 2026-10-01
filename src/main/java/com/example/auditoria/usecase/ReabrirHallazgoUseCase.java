package com.example.auditoria.usecase;

import com.example.auditoria.domain.valueobject.HallazgoId;

public interface ReabrirHallazgoUseCase {
    void ejecutar(HallazgoId id, String motivo);
}