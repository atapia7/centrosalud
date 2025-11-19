package opensource.upc.centrosalud.infrastructure.persistence.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "centro_salud")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CentroSaludEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_centro")
    private Integer idCentro;  // PK en BD

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoCentroSaludEntity tipo;

    @Column(name = "calif_infraestructura", nullable = false)
    private int califInfraestructura;

    @Column(name = "calif_servicios", nullable = false)
    private int califServicios;

    @Column(name = "tiene_ambulancias", nullable = false)
    private boolean tieneAmbulancias;

    @Column(name = "calificacion_final")
    private Double calificacionFinal;  // lo puedes recalcular o guardar

    @Column(name = "esta_aprobado", nullable = false)
    private boolean estaAprobado;
}
