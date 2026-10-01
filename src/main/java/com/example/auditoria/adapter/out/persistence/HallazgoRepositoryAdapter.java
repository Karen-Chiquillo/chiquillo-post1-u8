package com.example.auditoria.adapter.out.persistence;

import com.example.auditoria.domain.entity.HallazgoAuditoria;
import com.example.auditoria.domain.valueobject.EstadoHallazgo;
import com.example.auditoria.domain.valueobject.HallazgoId;
import com.example.auditoria.domain.valueobject.PlanRemediacion;
import com.example.auditoria.usecase.port.HallazgoRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class HallazgoRepositoryAdapter implements HallazgoRepositoryPort {

    private final HallazgoJpaRepository jpa;

    public HallazgoRepositoryAdapter(HallazgoJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public void guardar(HallazgoAuditoria hallazgo) {
        jpa.save(toEntity(hallazgo));
    }

    @Override
    public Optional<HallazgoAuditoria> buscarPorId(HallazgoId id) {
        return jpa.findById(id.toString()).map(this::toDomain);
    }

    @Override
    public List<HallazgoAuditoria> buscarTodos() {
        return jpa.findAll().stream().map(this::toDomain).toList();
    }

    private HallazgoAuditoria toDomain(HallazgoJpaEntity e) {
        HallazgoAuditoria h = new HallazgoAuditoria(
                new HallazgoId(UUID.fromString(e.getId())),
                e.getTitulo(),
                e.getDescripcion(),
                e.getAreaResponsable(),
                e.getSeveridad(),
                e.getFechaDeteccion()
        );

        if (e.getPlanResponsable() != null) {
            h.iniciarRemediacion(new PlanRemediacion(
                    e.getPlanResponsable(),
                    e.getPlanFechaLimite(),
                    e.getPlanNotas()
            ));
        }

        if (e.getEstado() == EstadoHallazgo.CERRADO) {
            h.cerrar();
        } else if (e.getEstado() == EstadoHallazgo.REABIERTO) {
            h.cerrar();
            h.reabrir();
        }

        return h;
    }

    private HallazgoJpaEntity toEntity(HallazgoAuditoria h) {
        HallazgoJpaEntity e = new HallazgoJpaEntity();
        e.setId(h.getId().toString());
        e.setTitulo(h.getTitulo());
        e.setDescripcion(h.getDescripcion());
        e.setAreaResponsable(h.getAreaResponsable());
        e.setSeveridad(h.getSeveridad());
        e.setEstado(h.getEstado());
        e.setFechaDeteccion(h.getFechaDeteccion());
        e.setFechaCierre(h.getFechaCierre());

        if (h.getPlanRemediacion() != null) {
            e.setPlanResponsable(h.getPlanRemediacion().responsable());
            e.setPlanFechaLimite(h.getPlanRemediacion().fechaLimite());
            e.setPlanNotas(h.getPlanRemediacion().notas());
        }

        return e;
    }
}