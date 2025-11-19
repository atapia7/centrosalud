package opensource.upc.centrosalud.interfaces.api;

import opensource.upc.centrosalud.application.service.RevisionCentroSaludApplicationService;
import opensource.upc.centrosalud.domain.model.RevisionCentroSalud;
import opensource.upc.centrosalud.interfaces.api.dto.RevisionCentroSaludRequestDto;
import opensource.upc.centrosalud.interfaces.api.dto.RevisionCentroSaludResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/revisiones")
public class RevisionCentroSaludController {

    private final RevisionCentroSaludApplicationService service;

    public RevisionCentroSaludController(RevisionCentroSaludApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> registrarRevision(@RequestBody RevisionCentroSaludRequestDto dto) {
        RevisionCentroSalud revision = new RevisionCentroSalud(
                0,
                dto.getCodigoCentro(),
                dto.getFechaRevision(),
                dto.getCalifInfraestructura(),
                dto.getCalifServicios(),
                0,       // calificación final la calcula el service
                false,      // estaAprobado también lo seta el service
                dto.getObservaciones()
        );

        service.registrarRevision(revision);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<RevisionCentroSaludResponseDto>> listarTodas() {
        List<RevisionCentroSaludResponseDto> respuesta = service.listarTodas().stream()
                .map(r -> new RevisionCentroSaludResponseDto(
                        r.getId(),
                        r.getCodigoCentro(),
                        r.getFechaRevision(),
                        r.getCalifInfraestructura(),
                        r.getCalifServicios(),
                        r.getCalificacionFinal(),
                        r.isEstaAprobado(),
                        r.getObservaciones()
                ))
                .toList();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/centro/{codigoCentro}")
    public ResponseEntity<List<RevisionCentroSaludResponseDto>> listarPorCentro(@PathVariable int codigoCentro) {
        List<RevisionCentroSaludResponseDto> respuesta = service.listarPorCentro(codigoCentro).stream()
                .map(r -> new RevisionCentroSaludResponseDto(
                        r.getId(),
                        r.getCodigoCentro(),
                        r.getFechaRevision(),
                        r.getCalifInfraestructura(),
                        r.getCalifServicios(),
                        r.getCalificacionFinal(),
                        r.isEstaAprobado(),
                        r.getObservaciones()
                ))
                .toList();

        return ResponseEntity.ok(respuesta);
    }
}

