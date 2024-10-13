/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campaniaasangre.ventanas;

import com.mycompany.campaniaasangre.ventanas.Persona;

public class PersonalEnfermero extends Persona {
    private String identificacionMedica;


    public PersonalEnfermero(String nombre, int edad, String rut, String genero, String direccion, String telefono, String email, String identificacionMedica) {
        super(nombre, edad, rut, genero, direccion, telefono, email);  // Llamamos al constructor de la clase padre
        this.identificacionMedica = identificacionMedica;
    }

    // Getters y setters...
    
    public String getIdentificacionMedica() {
        return identificacionMedica;
    }

    public void setIdentificacionMedica(String identificacionMedica) {
        this.identificacionMedica = identificacionMedica;
    }
    
    // Sobrescribimos el método getDetalles para incluir los detalles específicos del personal enfermero
    @Override
    public String getDetalles() {
        return super.getDetalles() + " - Identificación Médica: " + identificacionMedica;
    }
}

