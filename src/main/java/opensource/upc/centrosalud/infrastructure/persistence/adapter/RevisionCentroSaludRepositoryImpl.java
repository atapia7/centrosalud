package opensource.upc.centrosalud.infrastructure.persistence.adapter;
import lombok.RequiredArgsConstructor;
import opensource.upc.centrosalud.domain.model.RevisionCentroSalud;
import opensource.upc.centrosalud.domain.repository.RevisionCentroSaludRepository;
import opensource.upc.centrosalud.infrastructure.persistence.entity.CentroSaludEntity;
import opensource.upc.centrosalud.infrastructure.persistence.entity.RevisionCentroSaludEntity;
import opensource.upc.centrosalud.infrastructure.persistence.jpa.SpringDataCentroSaludJpaRepository;
import opensource.upc.centrosalud.infrastructure.persistence.jpa.SpringDataRevisionCentroSaludJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RevisionCentroSaludRepositoryImpl implements RevisionCentroSaludRepository {

    private final SpringDataRevisionCentroSaludJpaRepository revisionJpa;
    private final SpringDataCentroSaludJpaRepository centroJpa;

    @Override
    public void guardar(RevisionCentroSalud revision) {
        CentroSaludEntity centro = centroJpa.findById(revision.getCodigoCentro())
                .orElseThrow(() -> new IllegalArgumentException("Centro no encontrado: " + revision.getCodigoCentro()));

        RevisionCentroSaludEntity entity = toEntity(revision, centro);
        revisionJpa.save(entity);
    }

    @Override
    public List<RevisionCentroSalud> listarPorCentro(int codigoCentro) {
        return revisionJpa.findByCentroSalud_IdCentro(codigoCentro)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<RevisionCentroSalud> listarTodas() {
        return revisionJpa.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }
    //mapers

    private RevisionCentroSalud toDomain(RevisionCentroSaludEntity entity) {
        return new RevisionCentroSalud(
                entity.getIdRevision(),
                entity.getCentroSalud().getIdCentro(),
                entity.getFechaRevision(),
                entity.getCalifInfraestructura(),
                entity.getCalifServicios(),
                entity.getCalificacionFinal(),
                entity.isEstaAprobado(),
                entity.getObservaciones()
        );
    }

    private RevisionCentroSaludEntity toEntity(RevisionCentroSalud revision, CentroSaludEntity centro) {
        return RevisionCentroSaludEntity.builder()
                .idRevision(revision.getId())
                .centroSalud(centro)
                .fechaRevision(revision.getFechaRevision())
                .califInfraestructura(revision.getCalifInfraestructura())
                .califServicios(revision.getCalifServicios())
                .calificacionFinal(revision.getCalificacionFinal())
                .estaAprobado(revision.isEstaAprobado())
                .observaciones(revision.getObservaciones())
                .build();
    }
}

