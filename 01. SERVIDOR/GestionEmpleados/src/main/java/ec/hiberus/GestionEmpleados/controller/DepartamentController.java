package ec.hiberus.GestionEmpleados.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.hiberus.GestionEmpleados.dto.messageApi;
import ec.hiberus.GestionEmpleados.model.DepartamentEntity;
import ec.hiberus.GestionEmpleados.model.EmployeeEntity;
import ec.hiberus.GestionEmpleados.services.DepartamentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/departament")
public class DepartamentController {

    @Autowired
    private DepartamentServices services;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody DepartamentEntity data) {
        try {
            String mensaje = services.addDepartament(data);
            ObjectMapper mapper = new ObjectMapper();
            messageApi object = mapper.readValue(mensaje, messageApi.class);

            return new ResponseEntity(mensaje, HttpStatusCode.valueOf(object.getStatus()));
        } catch (Exception e) {
            return new ResponseEntity("Error interno: " + e.toString(), HttpStatusCode.valueOf(500));
        }
    }

    @RequestMapping(value = "/delete/{deparmentId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable int deparmentId){
        try {
            String mensaje = services.deleteDepartament(deparmentId);
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
            List<DepartamentEntity> mensaje = services.getAll();
            return new ResponseEntity(mensaje, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity("Error interno: " + e.toString(), HttpStatusCode.valueOf(500));
        }
    }
}
