package com.example.auditoria.usecase;

import com.example.auditoria.domain.valueobject.HallazgoId;

public class HallazgoNotFoundException extends RuntimeException {
    public HallazgoNotFoundException(HallazgoId id) {
        super("No se encontró el hallazgo con ID: " + id);
    }
}