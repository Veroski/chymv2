package com.example.chymv2.model;

import java.util.ArrayList;

public class Usuario {
    String username;
    String correo;
    String contraseña;
    String nombre;
    String imagen;
    float peso;
    float altura;
    float IMC;
    ArrayList<Rutina> rutinas;
    ArrayList<String> materiales;
    boolean premium;

    public Usuario(String username, String correo, String contraseña, float peso, float altura,
                   ArrayList<Rutina> rutinas, ArrayList<String> materiales, boolean premium,
                   String nombre, String imagen) {
        this.nombre = nombre;
        this.username = username;
        this.correo = correo;
        this.contraseña = contraseña;
        this.peso = peso;
        this.IMC = calcularIMC(this.peso, this.altura);
        this.altura = altura;
        this.rutinas = rutinas;
        this.materiales = materiales;
        this.premium = premium;
        this.imagen = imagen;
    }

    public Usuario(String username, String correo, String contraseña, String nombre) {
        this.username = username;
        this.correo = correo;
        this.contraseña = contraseña;
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getIMC() {
        return IMC;
    }

    public void setIMC(float IMC) {
        this.IMC = IMC;
    }

    public ArrayList<Rutina> getRutinas() {
        return rutinas;
    }

    public void setRutinas(ArrayList<Rutina> rutinas) {
        this.rutinas = rutinas;
    }

    public ArrayList<String> getMateriales() {
        return materiales;
    }

    public void setMateriales(ArrayList<String> materiales) {
        this.materiales = materiales;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public float calcularIMC(float peso, float altura){

        return (float) (peso / Math.pow(altura/100, 2));
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
