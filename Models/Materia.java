package com.fiares.Models;

public class Materia {
    private int id, carrera_id;
    private String siglas, titulo, descripcion, ciclo;

    public Materia(int id, int carrera_id, String siglas, String titulo, String descripcion, String ciclo) {
        this.id = id;
        this.carrera_id = carrera_id;
        this.siglas = siglas;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.ciclo = ciclo;
    }

    public Materia() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarrera_id() {
        return carrera_id;
    }

    public void setCarrera_id(int carrera_id) {
        this.carrera_id = carrera_id;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }
}
