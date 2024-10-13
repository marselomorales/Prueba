/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campaniaasangre.ventanas;

import java.util.*;

public class InventarioSangre {
    @SuppressWarnings("FieldMayBeFinal")
    private Map<String, Integer> reservasSangre;
    
    public InventarioSangre(){
        reservasSangre = new HashMap<>();
        
        reservasSangre.put("A+", 0);
        reservasSangre.put("A-", 0);
        reservasSangre.put("B+", 0);
        reservasSangre.put("B-", 0);
        reservasSangre.put("O+", 0);
        reservasSangre.put("O-", 0);
        reservasSangre.put("AB+", 0);
        reservasSangre.put("AB-", 0);
}
    
    public void agregarSangre(String tipoSangre, String factorRH, int cantidad) {
        String clave = tipoSangre + factorRH;
        if(reservasSangre.containsKey(clave)){
            reservasSangre.put(clave, reservasSangre.get(clave) + cantidad);
            System.out.println("Se han agregado " + cantidad + " unidades de " + clave + " al inventario.");
        }
        else {
            System.out.println("Tipo de sangre no válido");
        }
    }
    
    public boolean retirarSangre(String tipoSangre, int cantidad) {
        if (reservasSangre.containsKey(tipoSangre)) {
            int cantidadDisponible = reservasSangre.get(tipoSangre);
        
            // Verificar si hay suficiente sangre disponible
            if (cantidadDisponible >= cantidad) {
                reservasSangre.put(tipoSangre, cantidadDisponible - cantidad);
                System.out.println("Se han retirado " + cantidad + " unidades de sangre del tipo " + tipoSangre + ".");
                return true;
            } else {
                System.out.println("No hay suficiente sangre disponible del tipo " + tipoSangre + ".");
            }
        } else {
            System.out.println("Tipo de sangre no válido.");
        }
        return false;
    }
    
    public int obtenerCantidadSangre(String tipoSangre) {
        return reservasSangre.getOrDefault(tipoSangre, 0);
    }
    
    public void mostrarInventario() {
        System.out.println("Inventario de reservas de sangre:");
        for(Map.Entry<String, Integer> entry : reservasSangre.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " unidades");
        }
    }
    
    public Map<String, Integer> getReservasSangre() {
        return reservasSangre;
    }
}
