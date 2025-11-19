package opensource.upc.centrosalud.infrastructure.persistence.jpa;


import opensource.upc.centrosalud.infrastructure.persistence.entity.CentroSaludEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataCentroSaludJpaRepository
        extends JpaRepository<CentroSaludEntity, Integer> {
    Optional<CentroSaludEntity> findByIdCentro(Integer idCentro);
    List<CentroSaludEntity> findByTipo_NombreTipo(String nombreTipo);
}

