package ec.hiberus.GestionEmpleados.services;

import ec.hiberus.GestionEmpleados.dto.Mensaje;
import ec.hiberus.GestionEmpleados.model.DepartamentEntity;
import ec.hiberus.GestionEmpleados.model.EmployeeEntity;
import ec.hiberus.GestionEmpleados.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServices {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartamentServices departamentServices;

    private Mensaje msg = new Mensaje();
    public String addEmployee(Integer departamentId, EmployeeEntity data){
        try{
            DepartamentEntity departament = departamentServices.getById(departamentId);
            if(departament.getId() == null){
                return msg.mensajeEnviar(404, "No se pudo encontrar departamento");
            }else{
                data.setDepartament(departament);
                data.setId(null);
                EmployeeEntity saved = employeeRepository.save(data);

                if (saved != null && saved.getId() != null) {
                    return msg.add();
                } else {
                    return msg.mensajeEnviar(500, "No se pudo guardar el empleado.");
                }
            }

        }catch (DataIntegrityViolationException e) {
            return msg.mensajeEnviar(400, "Error de integridad: " + e.getMessage());
        } catch (Exception e) {
            return msg.mensajeEnviar(500, "Error interno al guardar: " + e.getMessage());
        }
    }

    public String deleteEmployee(Integer id){
        try{
            Optional<EmployeeEntity> employee = employeeRepository.findById(id);
            employee.get().setStatus('I');

            EmployeeEntity borrado = employeeRepository.save(employee.get());
            if (borrado != null && borrado.getId() != null) {
                return msg.delete();
            } else {
                return msg.mensajeEnviar(500, "No se pudo eliminar el empleado.");
            }
        }catch (Exception e){
            return msg.mensajeError(500, "Error interno al eliminar: " + e.getMessage());
        }
    }

    public List<EmployeeEntity> getAll(){
        List<EmployeeEntity> employee = new ArrayList<>();
        try{
            employee = employeeRepository.findAll();
            return employee;
        }catch (Exception e){
            System.out.println("Error interno: "+ e.toString());
            return employee;
        }
    }
    public EmployeeEntity getHighestSalary(){
        try{
            Optional<EmployeeEntity> employee = employeeRepository.findAll().stream()
                    .max(Comparator.comparingDouble(EmployeeEntity::getSalary));
            if (employee != null && employee.get() != null) {
                return employee.get();
            } else {
                return new EmployeeEntity();
            }
        }catch (Exception e){
            System.out.println("Error interno: "+ e.toString());
            return new EmployeeEntity();
        }
    }

    public EmployeeEntity getLowerAge(){
        try{
            Optional<EmployeeEntity> employee = employeeRepository.findAll().stream()
                    .min(Comparator.comparingInt(EmployeeEntity::getAge));
            if (employee != null && employee.get() != null) {
                return employee.get();
            } else {
                return new EmployeeEntity();
            }
        }catch (Exception e){
            System.out.println("Error interno: "+ e.toString());
            return new EmployeeEntity();
        }
    }

    public String getNumberLastMount(){
        try {
            LocalDate hoy = LocalDate.now();
            LocalDate haceUnMes = hoy.minusMonths(1);
            List<EmployeeEntity> empleados = employeeRepository.findAll();

            int employee = (int) empleados.stream()
                    .filter(emp -> {
                        if (emp.getInit_date() == null) return false;
                        LocalDate fecha = emp.getInit_date();
                        return !fecha.isBefore(haceUnMes) && !fecha.isAfter(hoy);
                    })
                    .count();

            return msg.mensajeEnviar(200, String.valueOf(employee));

        } catch (Exception e) {
            return msg.mensajeError(500, "Error interno: " + e.toString());
        }
    }
}
