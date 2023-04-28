package com.example.chymv2.model;

import java.io.Serializable;

public class ListExercice implements Serializable {
    String color;
    String ejercicio;
    String grupoMuscular;
    String tipoEjercicio;

    public ListExercice(String color, String ejercicio, String grupoMuscular, String tipoEjercicio) {
        this.color = color;
        this.ejercicio = ejercicio;
        this.grupoMuscular = grupoMuscular;
        this.tipoEjercicio = tipoEjercicio;
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
