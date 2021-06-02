package com.fiares.Models;

public class Unidad {
    private int id, orden_u, materia_id;
    private String titulo, descripcion;

    public Unidad(int id, int orden_u, int materia_id, String titulo, String descripcion) {
        this.id = id;
        this.orden_u = orden_u;
        this.materia_id = materia_id;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public Unidad() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrden_u() {
        return orden_u;
    }

    public void setOrden_u(int orden_u) {
        this.orden_u = orden_u;
    }

    public int getMateria_id() {
        return materia_id;
    }

    public void setMateria_id(int materia_id) {
        this.materia_id = materia_id;
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
