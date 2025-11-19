package opensource.upc.centrosalud.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RevisionCentroSalud {

    private int id;                 // id_revision
    private int codigoCentro;       // id_centro (o puedes usar CentroSalud directamente)
    private LocalDate fechaRevision;
    private int califInfraestructura;
    private int califServicios;
    private double calificacionFinal;
    private boolean estaAprobado;
    private String observaciones;

    public double calcularCalificacion() {
        return califInfraestructura * 0.35 + califServicios * 0.65;
    }

    public boolean esAprobada() {
        return calcularCalificacion() >= 80;
    }
}

