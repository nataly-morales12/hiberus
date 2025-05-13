package ec.hiberus.GestionEmpleados.repository;


import ec.hiberus.GestionEmpleados.model.DepartamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentRepository extends JpaRepository<DepartamentEntity, Number> {

}
