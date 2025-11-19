package opensource.upc.centrosalud.application.service;

import opensource.upc.centrosalud.domain.model.CentroSalud;
import opensource.upc.centrosalud.domain.model.TipoCentroSalud;
import opensource.upc.centrosalud.domain.repository.CentroSaludRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
    public class CentroSaludApplicationService {

    private final CentroSaludRepository centroSaludRepository;

    public CentroSaludApplicationService(CentroSaludRepository centroSaludRepository) {
        this.centroSaludRepository = centroSaludRepository;
    }

    public void registrarCentro(CentroSalud centroSalud) {
        centroSaludRepository.guardar(centroSalud);
    }

    public List<CentroSalud> listarTodos() {
        return centroSaludRepository.encontrarTodos();
    }

    public List<CentroSalud> listarPorTipo(TipoCentroSalud tipo) {
        return centroSaludRepository.encontrarPorTipo(tipo);
    }

    public String obtenerEstadoCentro(int codigo) {
        Optional<CentroSalud> centro = centroSaludRepository.encontrarPorCodigo(codigo);
        return centro.map(value -> value.estaAprobado() ? "APROBADO" : "RECHAZADO")
                .orElse("Centro no encontrado");
    }

    public void actualizarNombre(int codigo, String nuevoNombre) {
        centroSaludRepository.actualizarNombre(codigo, nuevoNombre);
    }
}

