package com.example.auditoria.usecase;

import com.example.auditoria.domain.valueobject.HallazgoId;
import com.example.auditoria.domain.valueobject.Severidad;

import java.time.LocalDate;

public interface RegistrarHallazgoUseCase {
    HallazgoId ejecutar(String titulo, String descripcion, String areaResponsable,
                        Severidad severidad, LocalDate fechaDeteccion);
}