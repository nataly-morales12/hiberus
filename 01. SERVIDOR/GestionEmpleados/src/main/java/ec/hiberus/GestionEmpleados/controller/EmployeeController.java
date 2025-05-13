package ec.hiberus.GestionEmpleados.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.hiberus.GestionEmpleados.dto.messageApi;
import ec.hiberus.GestionEmpleados.model.EmployeeEntity;
import ec.hiberus.GestionEmpleados.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServices services;

    @RequestMapping(value = "/create/{departmentId}", method = RequestMethod.POST)
    public ResponseEntity create(@PathVariable int departmentId, @RequestBody EmployeeEntity data) {
        try {
            String mensaje = services.addEmployee(departmentId, data);
            ObjectMapper mapper = new ObjectMapper();
            messageApi object = mapper.readValue(mensaje, messageApi.class);

            System.out.println(mensaje);
            return new ResponseEntity(mensaje, HttpStatusCode.valueOf(object.getStatus()));
        } catch (Exception e) {
            System.out.println("Error interno: " + e.toString());
            return new ResponseEntity("Error interno: " + e.toString(), HttpStatusCode.valueOf(500));
        }
    }

    @RequestMapping(value = "/delete/{employeeId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable int employeeId){
        try {
            String mensaje = services.deleteEmployee(employeeId);
            ObjectMapper mapper = new ObjectMapper();
            messageApi object = mapper.readValue(mensaje, messageApi.class);
            return new ResponseEntity(mensaje, HttpStatusCode.valueOf(object.getStatus()));
        } catch (Exception e) {
            return new ResponseEntity("Error interno: " + e.toString(), HttpStatusCode.valueOf(500));
        }
    }

    @RequestMapping(value = "/highestSalary", method = RequestMethod.GET)
    public ResponseEntity highestSalary(){
        try {
            EmployeeEntity employee = services.getHighestSalary();
            int status = 200;
            if(employee.getId() == null){
                status = 404;
            }
            return new ResponseEntity(employee, HttpStatusCode.valueOf(status));
        } catch (Exception e) {
            return new ResponseEntity("Error interno: " + e.toString(), HttpStatusCode.valueOf(500));
        }
    }

    @RequestMapping(value = "/lowerAge", method = RequestMethod.GET)
    public ResponseEntity lowerAge(){
        try {
            EmployeeEntity employee = services.getLowerAge();
            int status = 200;
            if(employee.getId() == null){
                status = 404;
            }
            return new ResponseEntity(employee, HttpStatusCode.valueOf(status));
        } catch (Exception e) {
            return new ResponseEntity("Error interno: " + e.toString(), HttpStatusCode.valueOf(500));
        }
    }

    @RequestMapping(value = "/countLastMonth", method = RequestMethod.GET)
    public ResponseEntity countLastMonth(){
        try {
            String mensaje = services.getNumberLastMount();
            System.out.println(mensaje);
            ObjectMapper mapper = new ObjectMapper();
            messageApi object = mapper.readValue(mensaje, messageApi.class);
            return new ResponseEntity(mensaje, HttpStatusCode.valueOf(object.getStatus()));
        } catch (Exception e) {
            return new ResponseEntity("Error interno: " + e.toString(), HttpStatusCode.valueOf(500));
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity gelAll(){
        try {
            List<EmployeeEntity> mensaje = services.getAll();
            return new ResponseEntity(mensaje, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity("Error interno: " + e.toString(), HttpStatusCode.valueOf(500));
        }
    }

}
