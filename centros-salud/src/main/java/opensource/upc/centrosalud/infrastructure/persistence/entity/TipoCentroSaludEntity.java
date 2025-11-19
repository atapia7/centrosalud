package opensource.upc.centrosalud.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_centro_salud")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoCentroSaludEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Integer id;

    @Column(name = "nombre_tipo", nullable = false, length = 50)
    private String nombreTipo; // "HOSPITAL", "CLINICA", etc.
}

