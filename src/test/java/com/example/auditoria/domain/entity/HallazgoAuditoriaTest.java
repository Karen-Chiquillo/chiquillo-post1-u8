package com.example.auditoria.domain.entity;

import com.example.auditoria.domain.valueobject.EstadoHallazgo;
import com.example.auditoria.domain.valueobject.HallazgoId;
import com.example.auditoria.domain.valueobject.PlanRemediacion;
import com.example.auditoria.domain.valueobject.Severidad;
import com.example.auditoria.domain.valueobject.TransicionInvalidaException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HallazgoAuditoriaTest {

    private HallazgoAuditoria crearHallazgoBase() {
        return new HallazgoAuditoria(
                HallazgoId.nuevo(),
                "Falta autenticacion de dos factores",
                "El portal de administracion no exige 2FA",
                "Seguridad",
                Severidad.ALTA,
                LocalDate.now()
        );
    }

    @Test
    @DisplayName("Un hallazgo nuevo debe crearse en estado ABIERTO")
    void debeIniciarEnEstadoAbierto() {
        HallazgoAuditoria hallazgo = crearHallazgoBase();
        assertEquals(EstadoHallazgo.ABIERTO, hallazgo.getEstado());
        assertNull(hallazgo.getPlanRemediacion());
        assertNull(hallazgo.getFechaCierre());
    }

    @Test
    @DisplayName("Debe permitir transicionar a EN_REMEDIACION al asignar un plan valido")
    void debeTransicionarAEnRemediacionConPlan() {
        HallazgoAuditoria hallazgo = crearHallazgoBase();
        PlanRemediacion plan = new PlanRemediacion("Equipo DevOps", LocalDate.now().plusDays(15), "Configurar TOTP");

        EstadoHallazgo estadoAnterior = hallazgo.iniciarRemediacion(plan);

        assertEquals(EstadoHallazgo.ABIERTO, estadoAnterior);
        assertEquals(EstadoHallazgo.EN_REMEDIACION, hallazgo.getEstado());
        assertNotNull(hallazgo.getPlanRemediacion());
    }

    @Test
    @DisplayName("Debe fallar al intentar cerrar un hallazgo sin haber iniciado remediacion")
    void debeFallarCierreSinPlan() {
        HallazgoAuditoria hallazgo = crearHallazgoBase();

        assertThrows(IllegalStateException.class, hallazgo::cerrar);
    }

    @Test
    @DisplayName("Debe permitir cerrar tras remediacion y posteriormente reabrir el hallazgo")
    void debeCerrarYReabrirExitosamente() {
        HallazgoAuditoria hallazgo = crearHallazgoBase();
        PlanRemediacion plan = new PlanRemediacion("Infraestructura", LocalDate.now().plusDays(5), "Aplicar parche");
        hallazgo.iniciarRemediacion(plan);

        EstadoHallazgo previoAlCierre = hallazgo.cerrar();
        assertEquals(EstadoHallazgo.EN_REMEDIACION, previoAlCierre);
        assertEquals(EstadoHallazgo.CERRADO, hallazgo.getEstado());
        assertNotNull(hallazgo.getFechaCierre());

        EstadoHallazgo previoAReabrir = hallazgo.reabrir();
        assertEquals(EstadoHallazgo.CERRADO, previoAReabrir);
        assertEquals(EstadoHallazgo.REABIERTO, hallazgo.getEstado());
        assertNull(hallazgo.getFechaCierre());
    }

    @Test
    @DisplayName("Debe lanzar TransicionInvalidaException si se intenta una transicion ilegal")
    void debeRechazarTransicionInvalida() {
        HallazgoAuditoria hallazgo = crearHallazgoBase();

        assertThrows(TransicionInvalidaException.class, hallazgo::reabrir);
    }
}