package thalissondev.com.encryptapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import thalissondev.com.encryptapi.domain.operation.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
