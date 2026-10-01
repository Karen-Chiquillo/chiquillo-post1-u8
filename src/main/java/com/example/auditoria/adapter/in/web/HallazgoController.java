package com.example.auditoria.adapter.in.web;

import com.example.auditoria.adapter.in.web.dto.HallazgoResponse;
import com.example.auditoria.adapter.in.web.dto.IniciarRemediacionRequest;
import com.example.auditoria.adapter.in.web.dto.ReabrirRequest;
import com.example.auditoria.adapter.in.web.dto.RegistrarHallazgoRequest;
import com.example.auditoria.domain.valueobject.HallazgoId;
import com.example.auditoria.usecase.CerrarHallazgoUseCase;
import com.example.auditoria.usecase.ConsultarHallazgoUseCase;
import com.example.auditoria.usecase.IniciarRemediacionUseCase;
import com.example.auditoria.usecase.ReabrirHallazgoUseCase;
import com.example.auditoria.usecase.RegistrarHallazgoUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/hallazgos")
public class HallazgoController {

    private final RegistrarHallazgoUseCase registrarUseCase;
    private final IniciarRemediacionUseCase iniciarRemediacionUseCase;
    private final CerrarHallazgoUseCase cerrarUseCase;
    private final ReabrirHallazgoUseCase reabrirUseCase;
    private final ConsultarHallazgoUseCase consultarUseCase;

    public HallazgoController(RegistrarHallazgoUseCase registrarUseCase,
                              IniciarRemediacionUseCase iniciarRemediacionUseCase,
                              CerrarHallazgoUseCase cerrarUseCase,
                              ReabrirHallazgoUseCase reabrirUseCase,
                              ConsultarHallazgoUseCase consultarUseCase) {
        this.registrarUseCase = registrarUseCase;
        this.iniciarRemediacionUseCase = iniciarRemediacionUseCase;
        this.cerrarUseCase = cerrarUseCase;
        this.reabrirUseCase = reabrirUseCase;
        this.consultarUseCase = consultarUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> registrar(@Valid @RequestBody RegistrarHallazgoRequest req) {
        HallazgoId id = registrarUseCase.ejecutar(
                req.titulo(), req.descripcion(), req.areaResponsable(), req.severidad(), req.fechaDeteccion()
        );
        return Map.of("hallazgoId", id.toString());
    }

    @PatchMapping("/{id}/iniciar-remediacion")
    public Map<String, String> iniciarRemediacion(@PathVariable String id,
                                                 @Valid @RequestBody IniciarRemediacionRequest req) {
        iniciarRemediacionUseCase.ejecutar(
                new HallazgoId(UUID.fromString(id)), req.responsable(), req.fechaLimite(), req.notas()
        );
        return Map.of("estado", "EN_REMEDIACION");
    }

    @PatchMapping("/{id}/cerrar")
    public Map<String, String> cerrar(@PathVariable String id) {
        cerrarUseCase.ejecutar(new HallazgoId(UUID.fromString(id)));
        return Map.of("estado", "CERRADO");
    }

    @PatchMapping("/{id}/reabrir")
    public Map<String, String> reabrir(@PathVariable String id, @RequestBody(required = false) ReabrirRequest req) {
        String motivo = (req != null) ? req.motivo() : "Reapertura de hallazgo";
        reabrirUseCase.ejecutar(new HallazgoId(UUID.fromString(id)), motivo);
        return Map.of("estado", "REABIERTO");
    }

    @GetMapping("/{id}")
    public HallazgoResponse buscar(@PathVariable String id) {
        return consultarUseCase.buscarPorId(new HallazgoId(UUID.fromString(id)));
    }

    @GetMapping
    public List<HallazgoResponse> listar() {
        return consultarUseCase.listarTodos();
    }
}