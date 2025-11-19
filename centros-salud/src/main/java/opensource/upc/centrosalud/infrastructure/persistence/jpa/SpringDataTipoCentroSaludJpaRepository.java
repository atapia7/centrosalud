package opensource.upc.centrosalud.infrastructure.persistence.jpa;

import opensource.upc.centrosalud.infrastructure.persistence.entity.TipoCentroSaludEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataTipoCentroSaludJpaRepository
        extends JpaRepository<TipoCentroSaludEntity, Integer> {
    Optional<TipoCentroSaludEntity> findByNombreTipo(String nombreTipo);
}

