package opensource.upc.centrosalud.interfaces.api;

import opensource.upc.centrosalud.application.service.CentroSaludApplicationService;
import opensource.upc.centrosalud.domain.model.CentroSalud;
import opensource.upc.centrosalud.domain.model.TipoCentroSalud;
import opensource.upc.centrosalud.interfaces.api.dto.CentroSaludRequestDto;
import opensource.upc.centrosalud.interfaces.api.dto.CentroSaludResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centros-salud")
public class CentroSaludController {

    private final CentroSaludApplicationService service;

    public CentroSaludController(CentroSaludApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> registrarCentro(@RequestBody CentroSaludRequestDto dto) {
        TipoCentroSalud tipo = TipoCentroSalud.valueOf(dto.getTipo().toUpperCase());

        CentroSalud centro = new CentroSalud(
                0,
                dto.getNombre(),
                tipo,
                dto.getCalificacionInfraestructura(),
                dto.getCalificacionServicios(),
                dto.isTieneAmbulanciasPropias()
        );

        service.registrarCentro(centro);

        String mensaje = String.format(
                "Centro de salud '%s' de tipo %s registrado correctamente.",
                dto.getNombre(),
                tipo.name()
        );

        return ResponseEntity.ok(mensaje);
    }

    @GetMapping
    public ResponseEntity<List<CentroSaludResponseDto>> listarTodos() {
        List<CentroSaludResponseDto> respuesta = service.listarTodos().stream()
                .map(c -> new CentroSaludResponseDto(
                        c.getCodigo(),
                        c.getNombre(),
                        c.getTipo().name(),
                        c.getCalificacionInfraestructura(),
                        c.getCalificacionServicios(),
                        c.calcularCalificacion(),
                        c.estaAprobado(),
                        c.isTieneAmbulanciasPropias()
                ))
                .toList();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CentroSaludResponseDto>> listarPorTipo(@PathVariable String tipo) {
        TipoCentroSalud enumTipo = TipoCentroSalud.valueOf(tipo.toUpperCase());

        List<CentroSaludResponseDto> respuesta = service.listarPorTipo(enumTipo).stream()
                .map(c -> new CentroSaludResponseDto(
                        c.getCodigo(),
                        c.getNombre(),
                        c.getTipo().name(),
                        c.getCalificacionInfraestructura(),
                        c.getCalificacionServicios(),
                        c.calcularCalificacion(),
                        c.estaAprobado(),
                        c.isTieneAmbulanciasPropias()
                ))
                .toList();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{codigo}/estado")
    public ResponseEntity<String> obtenerEstado(@PathVariable int codigo) {
        String estado = service.obtenerEstadoCentro(codigo); // "APROBADO", "RECHAZADO" o "Centro no encontrado"

        String mensaje = switch (estado) {
            case "APROBADO", "RECHAZADO" ->
                    String.format("El centro de salud con código %d está: %s.", codigo, estado);
            default ->
                    String.format("No se encontró un centro de salud con código %d.", codigo);
        };

        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{codigo}/nombre")
    public ResponseEntity<String> actualizarNombre(
            @PathVariable int codigo,
            @RequestParam String nuevoNombre
    ) {
        service.actualizarNombre(codigo, nuevoNombre);

        String mensaje = String.format(
                "Nombre del centro de salud con código %d actualizado a '%s'.",
                codigo,
                nuevoNombre
        );

        return ResponseEntity.ok(mensaje);
    }
}
