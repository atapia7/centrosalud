package opensource.upc.centrosalud.domain.repository;

import opensource.upc.centrosalud.domain.model.RevisionCentroSalud;

import java.util.List;

public interface RevisionCentroSaludRepository {

    void guardar(RevisionCentroSalud revision);

    List<RevisionCentroSalud> listarPorCentro(int codigoCentro);

    List<RevisionCentroSalud> listarTodas();
}

