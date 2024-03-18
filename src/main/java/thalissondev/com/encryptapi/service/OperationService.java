package thalissondev.com.encryptapi.service;

import org.springframework.stereotype.Service;
import thalissondev.com.encryptapi.domain.operation.Operation;
import thalissondev.com.encryptapi.domain.operation.exceptions.OperationNotFoundException;
import thalissondev.com.encryptapi.dto.OperationDTO;
import thalissondev.com.encryptapi.dto.OperationResponseDTO;
import thalissondev.com.encryptapi.repositories.OperationRepository;

@Service
public class OperationService {

    private OperationRepository repository;
    private EncryptService encryptService;

    public OperationService(OperationRepository repository, EncryptService encryptService) {
        this.repository = repository;
        this.encryptService= encryptService;
    }

    public Operation create(OperationDTO operationDTO) {
        Operation operation = new Operation();

        String userDocumentHashed = this.encryptService.encryptData(operationDTO.userDocument());
        String creditCardToken = this.encryptService.encryptData(operationDTO.creditCardToken());


        operation.setCreditCardToken(creditCardToken);
        operation.setUserDocument(userDocumentHashed);
        operation.setValue(operationDTO.operationValue());

        this.repository.save(operation);

        return operation;
    }

    public OperationResponseDTO read(Long id) throws OperationNotFoundException {
        Operation operation = repository.findById(id).orElseThrow(() -> new OperationNotFoundException(id));

        String userDocumentHashed = this.encryptService.decryptData(operation.getUserDocument());
        String creditCardToken = this.encryptService.decryptData(operation.getCreditCardToken());

        OperationResponseDTO dto = new OperationResponseDTO(operation.getId(), userDocumentHashed, creditCardToken, operation.getValue());

        return dto;
    }

    public void delete(Long id) throws OperationNotFoundException {
        Operation operation = repository.findById(id).orElseThrow(() -> new OperationNotFoundException(id));
        repository.deleteById(id);
    }

    public OperationResponseDTO update(Long id, OperationDTO operationDTO) throws OperationNotFoundException {
        Operation operation = repository.findById(id).orElseThrow(() -> new OperationNotFoundException(id));

        if (!operationDTO.userDocument().isEmpty()) {
            operation.setUserDocument(this.encryptService.encryptData(operationDTO.userDocument()));
        }

        if (!operationDTO.creditCardToken().isEmpty()) {
            operation.setCreditCardToken(this.encryptService.encryptData(operationDTO.creditCardToken()));
        }

        if (operationDTO.operationValue() != null) {
            operation.setValue(operationDTO.operationValue());
        }

        this.repository.save(operation);

        OperationResponseDTO dto = new OperationResponseDTO(operation.getId(), operation.getUserDocument(), operation.getCreditCardToken(), operation.getValue());

        return dto;
    }
}
