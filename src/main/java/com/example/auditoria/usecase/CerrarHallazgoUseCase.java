package com.example.auditoria.usecase;

import com.example.auditoria.domain.valueobject.HallazgoId;

public interface CerrarHallazgoUseCase {
    void ejecutar(HallazgoId id);
}