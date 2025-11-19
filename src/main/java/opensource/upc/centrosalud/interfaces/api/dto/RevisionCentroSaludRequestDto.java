package opensource.upc.centrosalud.interfaces.api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RevisionCentroSaludRequestDto {
    private Integer codigoCentro;
    private LocalDate fechaRevision;
    private int califInfraestructura;
    private int califServicios;
    private String observaciones;
}

