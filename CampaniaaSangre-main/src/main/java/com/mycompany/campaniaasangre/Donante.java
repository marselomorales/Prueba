package com.mycompany.campaniaasangre;

public class Donante extends Persona{
    private String tipoSangre;
    private String factorRH;
    private double cantDonada;
    
    public Donante(String nombre, int edad, String rut, String genero, String direccion, String telefono, String email, String tipoSangre, String factorRH, double cantDonada) {
        super(nombre, edad, rut, genero, direccion, telefono, email);
        this.tipoSangre = tipoSangre;
        this.factorRH = factorRH;
        this.cantDonada = cantDonada;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    @SuppressWarnings("StringEquality")
    public void setTipoSangre(String tipoSangre) {
        if (tipoSangre == "A"|| tipoSangre == "B"|| tipoSangre == "AB"|| tipoSangre == "O") {
            this.tipoSangre = tipoSangre;
        }
    }

    public String getFactorRH() {
        return factorRH;
    }

    @SuppressWarnings("StringEquality")
    public void setFactorRH(String factorRH) {
        if (factorRH == "+" || factorRH == "-") {
            this.factorRH = factorRH;
        }
    }

    public double getCantDonada() {
        return cantDonada;
    }

    public void setCantDonada(double cantDonada) {
        this.cantDonada = cantDonada;
    }
    
    @Override
     public String getDetalles() {
        return super.getDetalles() + " - Tipo de Sangre: " + tipoSangre + factorRH + " - Cantidad Donada: " + cantDonada + "mL";
    }
}
