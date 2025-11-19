package opensource.upc.centrosalud.interfaces.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CentroSaludResponseDto {
    private Integer codigo;
    private String nombre;
    private String tipo;
    private int calificacionInfraestructura;
    private int calificacionServicios;
    private double calificacionFinal;
    private boolean estaAprobado;
    private boolean tieneAmbulanciasPropias;
}

