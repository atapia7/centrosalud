package opensource.upc.centrosalud.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "revision_centro_salud")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RevisionCentroSaludEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_revision")
    private Integer idRevision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_centro", nullable = false)
    private CentroSaludEntity centroSalud;

    @Column(name = "fecha_revision", nullable = false)
    private LocalDate fechaRevision;

    @Column(name = "calif_infraestructura", nullable = false)
    private int califInfraestructura;

    @Column(name = "calif_servicios", nullable = false)
    private int califServicios;

    @Column(name = "calificacion_final")
    private Double calificacionFinal;

    @Column(name = "esta_aprobado", nullable = false)
    private boolean estaAprobado;

    @Column(name = "observaciones", length = 255)
    private String observaciones;
}

