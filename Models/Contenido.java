package com.fiares.Models;

public class Contenido {
    private int id, vistas, temario_id;
    private String titulo, descripcion, pdf, video, url;

    public Contenido(int id, int vistas, int temario_id, String titulo, String descripcion, String pdf, String video, String url) {
        this.id = id;
        this.vistas = vistas;
        this.temario_id = temario_id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.pdf = pdf;
        this.video = video;
        this.url = url;
    }

    public Contenido() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVistas() {
        return vistas;
    }

    public void setVistas(int vistas) {
        this.vistas = vistas;
    }

    public int getTemario_id() {
        return temario_id;
    }

    public void setTemario_id(int temario_id) {
        this.temario_id = temario_id;
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

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
