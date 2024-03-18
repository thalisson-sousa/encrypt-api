package thalissondev.com.encryptapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import thalissondev.com.encryptapi.domain.operation.Operation;
import thalissondev.com.encryptapi.dto.OperationDTO;
import thalissondev.com.encryptapi.dto.OperationResponseDTO;
import thalissondev.com.encryptapi.service.OperationService;

@RestController
@RequestMapping("/api/operation")
public class OperationController {

    final private OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping
    public ResponseEntity<Operation> create(@RequestBody OperationDTO operationDTO, UriComponentsBuilder uriBuilder) {
        Operation newOperation = operationService.create(operationDTO);

        var uri = uriBuilder.path("/api/operation/{id}").buildAndExpand(newOperation.getId()).toUri();

        return ResponseEntity.created(uri).body(newOperation);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OperationResponseDTO> read(@PathVariable Long id) {
        OperationResponseDTO response = operationService.read(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        operationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OperationResponseDTO> update(@PathVariable Long id, @RequestBody OperationDTO operationDTO) {
        OperationResponseDTO response = operationService.update(id, operationDTO);
        return ResponseEntity.ok(response);
    }
}
