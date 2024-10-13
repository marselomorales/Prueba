package com.mycompany.campaniaasangre.ventanas;

import com.mycompany.campaniaasangre.ventanas.Campania;

public class Persona {
    private String nombre;
    private int edad;
    private String rut;
    private String genero;
    private String direccion;
    private String telefono;
    private String email;
    private Campania campania;

    public Persona(String nombre, int edad, String rut, String genero, String direccion, String telefono, String email) {
        this.nombre = nombre;
        this.edad = edad;
        this.rut = rut;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad >= 18 && edad <= 65){
            this.edad = edad;
        }
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Campania getCampania() {
        return campania;
    }
    
    public void setCampania(Campania campania) {
        this.campania = campania;
    }
    
    public String getDetalles() {
        return "Nombre: " + nombre + ", Edad: " + edad + ", RUT: " + rut;
    }
}
