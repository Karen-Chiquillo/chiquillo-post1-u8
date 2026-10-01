package com.example.auditoria.adapter.in.web.dto;

import com.example.auditoria.domain.valueobject.Severidad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RegistrarHallazgoRequest(
        @NotBlank(message = "El titulo es obligatorio")
        String titulo,

        String descripcion,

        @NotBlank(message = "El area responsable es obligatoria")
        String areaResponsable,

        @NotNull(message = "La severidad es obligatoria")
        Severidad severidad,

        @NotNull(message = "La fecha de deteccion es obligatoria")
        LocalDate fechaDeteccion
) {}