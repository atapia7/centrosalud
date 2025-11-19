package opensource.upc.centrosalud.infrastructure.persistence.adapter;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import opensource.upc.centrosalud.domain.model.CentroSalud;
import opensource.upc.centrosalud.domain.model.TipoCentroSalud;
import opensource.upc.centrosalud.domain.repository.CentroSaludRepository;
import opensource.upc.centrosalud.infrastructure.persistence.entity.CentroSaludEntity;
import opensource.upc.centrosalud.infrastructure.persistence.entity.TipoCentroSaludEntity;
import opensource.upc.centrosalud.infrastructure.persistence.jpa.SpringDataCentroSaludJpaRepository;
import opensource.upc.centrosalud.infrastructure.persistence.jpa.SpringDataTipoCentroSaludJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CentroSaludRepositoryImpl implements CentroSaludRepository {

    private final SpringDataCentroSaludJpaRepository jpaRepository;
    private final SpringDataTipoCentroSaludJpaRepository tipoJpa;

    @Transactional
    @Override
    public void guardar(CentroSalud centroSalud) {
        CentroSaludEntity entity = toEntity(centroSalud);
        double calificacion = centroSalud.calcularCalificacion();
        entity.setCalificacionFinal(calificacion);
        entity.setEstaAprobado(centroSalud.estaAprobado());
        jpaRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CentroSalud> encontrarTodos() {
        List<CentroSalud> demo = jpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
        return demo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CentroSalud> encontrarPorTipo(TipoCentroSalud tipo) {
        return jpaRepository.findByTipo_NombreTipo(tipo.name())
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CentroSalud> encontrarPorCodigo(int codigo) {
        return jpaRepository.findByIdCentro(codigo)
                .map(this::toDomain);
    }

    @Override
    public void actualizarNombre(int codigo, String nuevoNombre) {
        jpaRepository.findByIdCentro(codigo).ifPresent(entity -> {
            entity.setNombre(nuevoNombre);
            jpaRepository.save(entity);
        });
    }


    private CentroSalud toDomain(CentroSaludEntity entity) {
        TipoCentroSalud tipoEnum = TipoCentroSalud.valueOf(
                entity.getTipo().getNombreTipo().trim().toUpperCase()
        );

        return new CentroSalud(
                entity.getIdCentro(),
                entity.getNombre(),
                tipoEnum,
                entity.getCalifInfraestructura(),
                entity.getCalifServicios(),
                entity.isTieneAmbulancias()
        );
    }

    private CentroSaludEntity toEntity(CentroSalud centro) {
        TipoCentroSaludEntity tipoEntity = tipoJpa.findByNombreTipo(centro.getTipo().name())
                .orElseThrow(() -> new IllegalStateException(
                        "TipoCentroSalud no encontrado en BD: " + centro.getTipo().name()
                ));

        return CentroSaludEntity.builder()
                .idCentro(centro.getCodigo())
                .nombre(centro.getNombre())
                .tipo(tipoEntity)
                .califInfraestructura(centro.getCalificacionInfraestructura())
                .califServicios(centro.getCalificacionServicios())
                .tieneAmbulancias(centro.isTieneAmbulanciasPropias())
                .build();
    }
}
