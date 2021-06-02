package com.fiares.Models;

public class Temario {
    private int id, unidad_id, orden;
    private String titulo, descripcion;

    public Temario(int id, int unidad_id, int orden, String titulo, String descripcion) {
        this.id = id;
        this.unidad_id = unidad_id;
        this.orden = orden;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public Temario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUnidad_id() {
        return unidad_id;
    }

    public void setUnidad_id(int unidad_id) {
        this.unidad_id = unidad_id;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
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
}
