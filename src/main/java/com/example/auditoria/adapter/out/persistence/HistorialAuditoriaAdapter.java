package com.example.auditoria.adapter.out.persistence;

import com.example.auditoria.usecase.port.CambioEstadoView;
import com.example.auditoria.usecase.port.HistorialAuditoriaPort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class HistorialAuditoriaAdapter implements HistorialAuditoriaPort {

    private final HistorialCambioEstadoJpaRepository repository;

    public HistorialAuditoriaAdapter(HistorialCambioEstadoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registrarCambio(String hallazgoId, String estadoAnterior, String estadoNuevo, String motivo, String usuario) {
        HistorialCambioEstadoJpaEntity entity = new HistorialCambioEstadoJpaEntity(
                hallazgoId,
                estadoAnterior,
                estadoNuevo,
                LocalDateTime.now(),
                motivo,
                usuario
        );
        repository.save(entity);
    }

    @Override
    public List<CambioEstadoView> buscarPorHallazgoId(String hallazgoId) {
        return repository.findByHallazgoIdOrderByFechaHoraAsc(hallazgoId).stream()
                .map(e -> new CambioEstadoView(
                        e.getId(),
                        e.getHallazgoId(),
                        e.getEstadoAnterior(),
                        e.getEstadoNuevo(),
                        e.getFechaHora(),
                        e.getMotivo(),
                        e.getUsuario()
                ))
                .toList();
    }
}