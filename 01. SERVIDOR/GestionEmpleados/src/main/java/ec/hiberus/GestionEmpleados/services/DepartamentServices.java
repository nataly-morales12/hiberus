package ec.hiberus.GestionEmpleados.services;

import ec.hiberus.GestionEmpleados.dto.Mensaje;
import ec.hiberus.GestionEmpleados.model.DepartamentEntity;
import ec.hiberus.GestionEmpleados.model.EmployeeEntity;
import ec.hiberus.GestionEmpleados.repository.DepartamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartamentServices {
    @Autowired
    private DepartamentRepository departamentRepository;
    private Mensaje msg = new Mensaje();

    public List<DepartamentEntity> getAll(){
        List<DepartamentEntity> datos = new ArrayList<>();
        try{
            datos = departamentRepository.findAll();
            return datos;
        }catch (Exception e){
            System.out.println("Error interno: "+ e.toString());
            return datos;
        }
    }

    public DepartamentEntity getById(Integer id){
        try{
            Optional<DepartamentEntity> departament = departamentRepository.findById(id);
            if(departament.isEmpty()){
                return new DepartamentEntity();
            }else{
                return departament.get();
            }
        }catch (Exception e){
            System.out.println("Error interno: "+ e.toString());
            return new DepartamentEntity();
            //return msg.mensajeEnviar(e.toString());
        }
    }
    public String addDepartament(DepartamentEntity data){
        try{
            DepartamentEntity saved = departamentRepository.save(data);
            if (saved != null) {
                return msg.add();
            } else {
                return msg.mensajeEnviar(500, "No se pudo guardar el empleado.");
            }

        }catch (DataIntegrityViolationException e) {
            return msg.mensajeEnviar(400, "Error de integridad: " + e.getMessage());
        } catch (Exception e) {
            return msg.mensajeEnviar(500, "Error interno al guardar: " + e.getMessage());
        }
    }

    public String deleteDepartament(Integer id){
        try{
            Optional<DepartamentEntity> departament = departamentRepository.findById(id);
            if(departament.isEmpty()){
                return msg.mensajeEnviar(404, "No encontrado");
            }else{
                departament.get().setStatus('I');
                DepartamentEntity borrado = departamentRepository.save(departament.get());
                if (borrado != null && borrado.getId() != null) {
                    return msg.delete();
                } else {
                    return msg.mensajeEnviar(500, "No se pudo eliminar el empleado.");
                }

            }
        }catch (Exception e){
            return msg.mensajeError(500, e.toString());
        }
    }

}
