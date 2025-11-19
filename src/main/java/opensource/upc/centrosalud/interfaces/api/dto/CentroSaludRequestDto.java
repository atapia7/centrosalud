package opensource.upc.centrosalud.interfaces.api.dto;

import lombok.Data;

@Data
public class CentroSaludRequestDto {
    private String nombre;
    private String tipo; // "HOSPITAL" o "CLINICA"
    private int calificacionInfraestructura;
    private int calificacionServicios;
    private boolean tieneAmbulanciasPropias;
}
