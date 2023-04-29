package com.example.chymv2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Rutina implements Serializable {
    String nombre;
    ArrayList<Ejercicio> ejercicios;

    Set<String> materiales;

    Set<String> musculos;
    String descripcion;
    boolean subida;
    String color;

    public Rutina(String color,String nombre){
        this.nombre = nombre;
        this.color = color;
    }
    public Rutina(String nombre, ArrayList<Ejercicio> ejercicios, String descripcion, boolean subida) {
        this.nombre = nombre;
        this.ejercicios = ejercicios;
        this.materiales = new HashSet<String>();
        this.musculos = new HashSet<String>();
        this.descripcion = descripcion;
        this.subida = subida;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(ArrayList<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public Set<String> getMateriales() {
        return materiales;
    }

    public void setMateriales(Set<String> materiales) {
        this.materiales = materiales;
    }

    public Set<String> getMusculos() {
        return musculos;
    }

    public void setMusculos(Set<String> musculos) {
        this.musculos = musculos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isSubida() {
        return subida;
    }

    public void setSubida(boolean subida) {
        this.subida = subida;
    }

    public void añadir_ejercicio(Ejercicio ejercicio){
        this.ejercicios.add(ejercicio);
    }

    public void eliminar_ejercicio(Ejercicio ejercicio){
        this.ejercicios.remove(ejercicio);
    }

    public void material_necesario(){
        this.materiales.clear();
        for(Ejercicio ejercicio : this.ejercicios){
            for (String material: ejercicio.getMateriales()) {
                this.materiales.add(material);
            }
        }
    }

    public void zona_muscular(){
        this.musculos.clear();
        for(Ejercicio ejercicio : this.ejercicios){
            for (String musculo: ejercicio.getMusculos()) {
                this.materiales.add(musculo);
            }
        }
    }

}
