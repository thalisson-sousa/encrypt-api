package thalissondev.com.encryptapi.domain.operation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "operations")
public class Operation {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userdocument", nullable = false)
    private String userDocument;

    @Column(name = "creditcardtoken", nullable = false)
    private String creditCardToken;

    @Column(name = "operationvalue", nullable = false)
    private Long value;

}
