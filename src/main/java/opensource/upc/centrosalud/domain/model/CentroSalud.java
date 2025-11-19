package opensource.upc.centrosalud.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CentroSalud {

    private int codigo;
    private String nombre;
    private TipoCentroSalud tipo;
    private int calificacionInfraestructura;  // 1-100
    private int calificacionServicios;        // 1-100
    private boolean tieneAmbulanciasPropias;

    public double calcularCalificacion() {
        return (calificacionInfraestructura * 0.35) +
                (calificacionServicios * 0.65);
    }

    public boolean estaAprobado() {
        return calcularCalificacion() >= 80;
    }
}
