package opensource.upc.centrosalud.interfaces.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RevisionCentroSaludResponseDto {
    private Integer idRevision;
    private Integer codigoCentro;
    private LocalDate fechaRevision;
    private int califInfraestructura;
    private int califServicios;
    private double calificacionFinal;
    private boolean estaAprobado;
    private String observaciones;
}

