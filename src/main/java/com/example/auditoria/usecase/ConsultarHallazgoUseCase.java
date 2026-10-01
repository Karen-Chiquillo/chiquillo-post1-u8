package com.example.auditoria.usecase;

import com.example.auditoria.adapter.in.web.dto.HallazgoResponse;
import com.example.auditoria.domain.valueobject.HallazgoId;

import java.util.List;

public interface ConsultarHallazgoUseCase {
    HallazgoResponse buscarPorId(HallazgoId id);
    List<HallazgoResponse> listarTodos();
}