package ec.hiberus.GestionEmpleados.dto;

import java.io.Serializable;

public class messageApi implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;
    private int status;
    private boolean error;

    public messageApi() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
