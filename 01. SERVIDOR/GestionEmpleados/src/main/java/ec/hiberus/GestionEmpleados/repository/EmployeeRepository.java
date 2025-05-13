package ec.hiberus.GestionEmpleados.repository;

import ec.hiberus.GestionEmpleados.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Number> {

}
