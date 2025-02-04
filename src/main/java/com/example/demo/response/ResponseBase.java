package com.example.demo.response;

import java.util.Optional;

public class ResponseBase <T>{
    private int codigo;
    private String mensaje;
    private Optional<T> data;

    public ResponseBase(int codigo, String mensaje, Optional<T> data) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.data = data;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Optional<T> getData() {
        return data;
    }

    public void setData(Optional<T> data) {
        this.data = data;
    }
}
