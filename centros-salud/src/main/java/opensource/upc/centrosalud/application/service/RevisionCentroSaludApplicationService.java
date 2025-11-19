package opensource.upc.centrosalud.application.service;

import opensource.upc.centrosalud.domain.model.RevisionCentroSalud;
import opensource.upc.centrosalud.domain.repository.RevisionCentroSaludRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevisionCentroSaludApplicationService {

    private final RevisionCentroSaludRepository revisionRepository;

    public RevisionCentroSaludApplicationService(RevisionCentroSaludRepository revisionRepository) {
        this.revisionRepository = revisionRepository;
    }

    public void registrarRevision(RevisionCentroSalud revision) {
        double calificacion = revision.calcularCalificacion();
        revision.setCalificacionFinal(calificacion);
        revision.setEstaAprobado(revision.esAprobada());
        revisionRepository.guardar(revision);
    }

    public List<RevisionCentroSalud> listarPorCentro(int codigoCentro) {
        return revisionRepository.listarPorCentro(codigoCentro);
    }

    public List<RevisionCentroSalud> listarTodas() {
        return revisionRepository.listarTodas();
    }
}

