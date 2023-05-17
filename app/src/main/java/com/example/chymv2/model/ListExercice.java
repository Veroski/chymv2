package com.example.chymv2.model;

import java.io.Serializable;

public class ListExercice implements Serializable {
    String color;
    String ejercicio;
    String grupoMuscular;
    String tipoEjercicio;
    int id;
    String descripcion;
    String SRK;

    public ListExercice(String color, String ejercicio, String grupoMuscular, String tipoEjercicio,int id,String descripcion) {
        this.color = color;
        this.ejercicio = ejercicio;
        this.grupoMuscular = grupoMuscular;
        this.tipoEjercicio = tipoEjercicio;
        this.id = id;
        this.descripcion = descripcion;
        this.SRK = null;
    }

    public String getSRK() {
        return SRK;
    }

    public void setSRK(String SRK) {
        this.SRK = SRK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public String getTipoEjercicio() {
        return tipoEjercicio;
    }

    public void setTipoEjercicio(String tipoEjercicio) {
        this.tipoEjercicio = tipoEjercicio;
    }
}
