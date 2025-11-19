package opensource.upc.centrosalud.domain.repository;

import opensource.upc.centrosalud.domain.model.CentroSalud;
import opensource.upc.centrosalud.domain.model.TipoCentroSalud;

import java.util.List;
import java.util.Optional;
public interface  CentroSaludRepository {
    void guardar(CentroSalud centroSalud);

    List<CentroSalud> encontrarTodos();

    List<CentroSalud> encontrarPorTipo(TipoCentroSalud tipo);

    Optional<CentroSalud> encontrarPorCodigo(int codigo);

    void actualizarNombre(int codigo, String nuevoNombre);
}
