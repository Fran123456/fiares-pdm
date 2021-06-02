package com.fiares.Models;

public class Carrera {
    private int id;
    private String carrera, descripcion;

    public Carrera() {
    }

    public Carrera(int id, String carrera, String descripcion) {
        this.id = id;
        this.carrera = carrera;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
