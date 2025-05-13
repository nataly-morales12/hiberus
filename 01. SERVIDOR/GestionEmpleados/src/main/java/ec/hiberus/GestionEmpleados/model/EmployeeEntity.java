package ec.hiberus.GestionEmpleados.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode
@Table(name = "EMPLOYEE", schema = "HIBERUS", uniqueConstraints = {@UniqueConstraint(columnNames = {"EMPLOYEE_ID"})})
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UEMPLOYEE")
    @SequenceGenerator(name = "UEMPLOYEE", schema = "HIBERUS", sequenceName = "UEMPLOYEE", allocationSize = 1)
    @Column(name = "EMPLOYEE_ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "DEPARTAMENT_ID", referencedColumnName = "DEPARTAMENT_ID",nullable = false )
    private DepartamentEntity departament;

    @Column(name = "EMPLOYEE_NOMBRE")
    private String name;

    @Column(name = "EMPLOYEE_LAST_NAME")
    private String last_name;

    @Column(name = "AGE")
    private int age;

    @Column(name = "SALARY")
    private Double salary;

    @Column(name = "ROL")
    private String rol;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INIT_DATE")
    private LocalDate init_date;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE")
    private LocalDate end_date;

    @Column(name = "EMPLOYEE_STATUS")
    private char status;

}
