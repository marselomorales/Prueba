package com.mycompany.campaniaasangre;
import java.util.*;

public class Campania {
    private String nombre;
    private String ubicacion;
    private String fecha;
    @SuppressWarnings("FieldMayBeFinal")
    private List<Donante> donantes;
    @SuppressWarnings("FieldMayBeFinal")
    private List<PersonalEnfermero> personal;
    
    public Campania(String nombre, String ubicacion, String fecha) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.donantes = new ArrayList<>();
        this.personal = new ArrayList<>();
    }
    
    public void agregarDonante(Donante donante){
        donantes.add(donante);
    }
    
    public void agregarDonante(String nombre, int edad, String rut, String genero, String direccion, String telefono, String email,  String tipoSangre, String factorRH, double cantidadDonada, Persona persona) {
        Donante nuevoDonante = new Donante(nombre, edad, rut, genero, direccion, telefono, email, tipoSangre, factorRH, cantidadDonada);
        donantes.add(nuevoDonante);
    }
    
    public void modificarDonante(String rut, double cantDonada, String tipoSangre, String factorRH) {
        for (Donante donante : donantes) {
            if (donante.getRut().equals(rut)) {
                donante.setTipoSangre(tipoSangre);
                donante.setFactorRH(factorRH);
                donante.setCantDonada(cantDonada);
                System.out.println("Los detalles del donante han sido actualizados.");
                return;
            }
        }
        System.out.println("Donante no encontrado.");
    }

    public void eliminarDonante(String rut) {
        Iterator<Donante> iterador = donantes.iterator();
        while (iterador.hasNext()) {
            Donante donante = iterador.next();
            if (donante.getRut().equals(rut)) {
                iterador.remove(); // Eliminar el donante de la lista
                System.out.println("Donante eliminado correctamente.");
                return;
            }
        }
        System.out.println("Donante no encontrado.");
    }

    
    public List<Donante> getDonantesRegistrados(){
        return donantes;
    }
    
    public List<Donante> filtrarDonantesPorTipoSangre(String tipoSangre) {
        List<Donante> donantesFiltrados = new ArrayList<>();
    
        for (Donante donante : donantes) {
            if (donante.getTipoSangre().equals(tipoSangre)) {
                donantesFiltrados.add(donante);
            }
        }
    
        return donantesFiltrados;
    }

    public void agregarPersonalEnfermero(PersonalEnfermero enfermero) {
        personal.add(enfermero);
    }
    
    public List<PersonalEnfermero> getPersonalEnfermero() {
        return personal;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getUbicacion () {
        return ubicacion;
    }
    
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
