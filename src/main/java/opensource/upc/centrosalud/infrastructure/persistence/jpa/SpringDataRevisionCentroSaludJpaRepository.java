package opensource.upc.centrosalud.infrastructure.persistence.jpa;


import opensource.upc.centrosalud.infrastructure.persistence.entity.RevisionCentroSaludEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataRevisionCentroSaludJpaRepository
        extends JpaRepository<RevisionCentroSaludEntity, Integer> {
    List<RevisionCentroSaludEntity> findByCentroSalud_IdCentro(Integer idCentro);
}
