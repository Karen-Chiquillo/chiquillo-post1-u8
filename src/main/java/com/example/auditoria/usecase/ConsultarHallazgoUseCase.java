package com.example.auditoria.usecase;

import com.example.auditoria.domain.entity.HallazgoAuditoria;
import com.example.auditoria.domain.valueobject.HallazgoId;

import java.util.List;

public interface ConsultarHallazgoUseCase {
    HallazgoAuditoria buscarPorId(HallazgoId id);
    List<HallazgoAuditoria> listarTodos();
}