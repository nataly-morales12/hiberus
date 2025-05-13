package ec.hiberus.GestionEmpleados.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "DEPARTAMENT", schema = "HIBERUS", uniqueConstraints = {@UniqueConstraint(columnNames = {"DEPARTAMENT_ID"})})
public class DepartamentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UDEPARTAMENT")
    @SequenceGenerator(name = "UDEPARTAMENT", schema = "UTIC2", sequenceName = "UDEPARTAMENT", allocationSize = 1)
    @Column(name = "DEPARTAMENT_ID")
    private Integer id;

    @Column(name = "DEPARTAMENT_NAME")
    private String name;

    @Column(name = "DEPARTAMENT_STATUS")
    private char status;

}

