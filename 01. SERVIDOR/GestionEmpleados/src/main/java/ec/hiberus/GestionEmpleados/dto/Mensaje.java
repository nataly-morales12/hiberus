package ec.hiberus.GestionEmpleados.dto;

import com.google.gson.Gson;

public class Mensaje {
    private final Gson gson = new Gson();
    private final messageApi mensaje = new messageApi();

    public String add() {
        String msj = "Se cre\u00f3 correctamente";
        mensaje.setMessage(msj);
        mensaje.setError(false);
        mensaje.setStatus(200);
        return gson.toJson(mensaje);
    }
    public String delete() {
        String msj = "Se elimin\u00f3 correctamente";
        mensaje.setMessage(msj);
        mensaje.setError(false);
        mensaje.setStatus(200);
        return gson.toJson(mensaje);
    }

    public String update() {
        String msj = "Se actualiz\u00f3 correctamente";
        mensaje.setMessage(msj);
        mensaje.setError(false);
        mensaje.setStatus(200);
        return gson.toJson(mensaje);
    }

    public String notfound() {

        String msj = "No se encontraron resultados";
        mensaje.setMessage(msj);
        mensaje.setError(false);
        mensaje.setStatus(200);
        return gson.toJson(mensaje);
    }

    public String ifexiste() {

        String msj = "Informacion ya Existente";
        mensaje.setMessage(msj);
        mensaje.setError(false);
        mensaje.setStatus(200);
        return gson.toJson(mensaje);
    }

    public String mensajeEnviar(int status, String msj) {
        mensaje.setMessage(msj);
        if(status == 200){
            mensaje.setError(false);
        }else{
            mensaje.setError(false);
        }
        mensaje.setStatus(status);
        return gson.toJson(mensaje);
    }

    public String mensajeError(int status, String detalle) {
        String msj = "Hubo un error, detalle: "+ detalle;
        mensaje.setMessage(msj);
        mensaje.setError(true);
        mensaje.setStatus(status);
        return gson.toJson(mensaje);
    }
}
