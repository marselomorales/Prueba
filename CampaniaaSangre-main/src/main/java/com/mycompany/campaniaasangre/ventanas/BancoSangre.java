    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campaniaasangre.ventanas;


import com.mycompany.excepciones.CampaniaNoEncontradaException;
import com.mycompany.excepciones.DatosDonanteInvalidosException;
import java.util.*;
import java.io.*;

public class BancoSangre {
    @SuppressWarnings("FieldMayBeFinal")
    private Map<String, List<Campania>> campañas;
    @SuppressWarnings("FieldMayBeFinal")
    private InventarioSangre inventarioDeSangre;
    
    public BancoSangre(){
        campañas = new HashMap<>();
        inventarioDeSangre = new InventarioSangre();
    }
    
    @SuppressWarnings("CallToPrintStackTrace")
    public void cargarDatosIniciales() {
        String cvsSplitBy = ",";
        
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("donantes.csv");
        if (inputStream == null) {
            System.out.println("No se pudo encontrar el archivo donantes.csv");
            return;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            boolean esPrimLine = true;
            while ((line = br.readLine()) != null) {
                if(esPrimLine){
                    esPrimLine = false;
                    continue;
                }
                // Dividir la línea por comas
                String[] datos = line.split(cvsSplitBy);

                // Extraer los datos del donante
                String nombre = datos[0];
                int edad = Integer.parseInt(datos[1]);
                String rut = datos[2];
                String genero = datos[3];
                String direccion = datos[4];
                String telefono = datos[5];
                String email = datos[6];
                String tipoSangre = datos[7];
                String factorRH = datos[8];
                int cantDonada = Integer.parseInt(datos[9]);
                String nombreCampania = datos[10]; // Suponiendo que este dato está en la columna 11
                String ubicacionCampania = datos[11]; // Columna 12
                String fechaCampania = datos[12]; // Columna 13

                // Crear el objeto Donante
                Donante donante = new Donante(nombre, edad, rut, genero, direccion, telefono, email, tipoSangre, factorRH, cantDonada);
            
                // Crear o conseguir la campaña asociada
                Campania campania = new Campania(nombreCampania, ubicacionCampania, fechaCampania);
            
                // Agregar el donante a la campaña
                campania.getDonantesRegistrados().add(donante);

                // Asegurarse de que la campaña esté en el mapa de campañas del BancoSangre
                if (!campañas.containsKey(nombreCampania)) {
                    campañas.put(nombreCampania, new ArrayList<>());
                }
                campañas.get(nombreCampania).add(campania);

                // Agregar la cantidad donada al inventario de sangre
                inventarioDeSangre.agregarSangre(tipoSangre, factorRH, cantDonada); // Asegúrate de que este método exista
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void guardarDatos(String rutaArchivo) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
        writer.write("Nombre,Edad,RUT,Género,Dirección,Teléfono,Email,Tipo de Sangre,Factor RH,Cantidad Donada,Nombre Campaña,Ubicación Campaña,Fecha Campaña");
        writer.newLine();

        Set<String> donantesGuardados = new HashSet<>(); // Para rastrear donantes guardados

        for (List<Campania> listaCamp : campañas.values()) {
            for (Campania campania : listaCamp) {
                for (Donante donante : campania.getDonantesRegistrados()) {
                    // Crea un identificador único para cada donante
                    String donanteId = donante.getRut();

                    // Verifica si el donante ya ha sido guardado
                    if (!donantesGuardados.contains(donanteId)) {
                        writer.write(String.join(",",
                            donante.getNombre(),
                            String.valueOf(donante.getEdad()),
                            donante.getRut(),
                            donante.getGenero(),
                            donante.getDireccion(),
                            donante.getTelefono(),
                            donante.getEmail(),
                            donante.getTipoSangre(),
                            donante.getFactorRH(),
                            String.valueOf(donante.getCantDonada()),
                            campania.getNombre(), // Obtener el nombre de la campaña
                            campania.getUbicacion(), // Asegúrate de tener un método para obtener la ubicación
                            campania.getFecha())); // Asegúrate de tener un método para obtener la fecha
                        writer.newLine();

                        // Agrega el donante al conjunto de donantes guardados
                        donantesGuardados.add(donanteId);
                    }
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    
    public Optional<Campania> buscarOCrearCampania(String nombreCampania) {
        for (List<Campania> listaCampanias : campañas.values()) {
            for (Campania campania : listaCampanias) {
                if (campania.getNombre().equals(nombreCampania)) {
                return Optional.of(campania);
                }
            }
        }
    
        // Si no se encuentra la campaña, retornar un mensaje y un estado
        System.out.println("Campaña no encontrada: " + nombreCampania);
        return Optional.empty(); // Indica que la campaña no existe
    }
    
    private String generarClaveCampania(String hospital, String nombreCampania) {
        return hospital + "-" + nombreCampania;
    }
    
    public void agregarCampaña(Campania campaña) {
        String clave = generarClaveCampania(campaña.getUbicacion(), campaña.getNombre());
        List<Campania> listaCampañas = campañas.getOrDefault(clave, new ArrayList<>());
        
        listaCampañas.add(campaña);
        campañas.put(clave, listaCampañas);
    }
    
    public void agregarPersonalEnfermeroACampania(PersonalEnfermero enfermero, String ubicacion, String nombreCampania) {
        String clave = generarClaveCampania(ubicacion, nombreCampania);
        List<Campania> listaCampanias = campañas.get(clave);

        if (listaCampanias != null && !listaCampanias.isEmpty()) {
            Campania campania = listaCampanias.get(0);
            campania.agregarPersonalEnfermero(enfermero);
            System.out.println("Personal enfermero agregado a la campaña: " + campania.getNombre());
        } else {
            System.out.println("Campaña no encontrada.");
        }
    }

    
    public boolean registrarDonanteEnCampaña(Donante donante, String ubicacion, String nombre) throws DatosDonanteInvalidosException {
        String clave = generarClaveCampania(ubicacion, nombre);
        List<Campania> listaCampañas = campañas.get(clave);
    
        if(listaCampañas != null) {
            // Aquí podrías buscar la campaña adecuada en la lista si hay más de una
            Campania campaña = listaCampañas.get(0); // Esto asume que siempre hay al menos una
            campaña.agregarDonante(donante);
            inventarioDeSangre.agregarSangre(donante.getTipoSangre(), donante.getFactorRH(), (int) donante.getCantDonada());
        } else {
            System.out.println("Campaña no enco1ntrada.");
            return false;
        }
        return true;
    }

    
    public void registrarDonanteEnCampania(Donante donante, Campania campania) throws DatosDonanteInvalidosException {
        campania.agregarDonante(donante);
        System.out.println("Donante registrado en la campaña: " + campania.getNombre());
    }
    
   public void mostrarDonantesDeCampania(String ubicacion, String nombre) {
       String clave = generarClaveCampania(ubicacion, nombre);
        List<Campania> listaCampanias = campañas.get(clave); // Obtener la lista de campañas asociada a la clave
        if (listaCampanias != null && !listaCampanias.isEmpty()) {
            Campania campania = listaCampanias.get(0); // Si solo hay una campaña, tomamos la primera
            // Mostrar los donantes de la campaña
            System.out.println("Donantes de la campaña " + campania.getNombre() + " en el hospital " + ubicacion + ":");
            for (Donante d : campania.getDonantesRegistrados()) {
                System.out.println(d.getDetalles());
            }
        } else {
            System.out.println  ("Campaña no encontrada.");
        }
    }
   
   public List<Donante> obtenerTodosLosDonantes() {
       List<Donante> todos = new ArrayList<>();
       
       for(List<Campania> campanias : campañas.values()){
           for (Campania campania : campanias) {
               todos.addAll(campania.getDonantesRegistrados());
           }
       }
       return todos;
   }
   
    public boolean modificarDonanteEnCampania(String rut, int cantDonada, String tipoSangre, String factorRH, String ubicacion, String nombreCampania) {
        String clave = generarClaveCampania(ubicacion, nombreCampania);
        List<Campania> listaCampanias = campañas.get(clave);
    
        if (listaCampanias != null && !listaCampanias.isEmpty()) {
            Campania campania = listaCampanias.get(0); // Asumiendo que es una sola campaña
            campania.modificarDonante(rut, cantDonada, tipoSangre, factorRH);
        } else {
            System.out.println("Campaña no encontrada.");
            return false;
        }
        return true;
    }
    
    public boolean eliminarDonanteEnCampania(String rut, String ubicacion, String nombreCampania) {
        String clave = generarClaveCampania(ubicacion, nombreCampania);
        List<Campania> listaCampanias = campañas.get(clave);
    
        if (listaCampanias != null && !listaCampanias.isEmpty()) {
            Campania campania = listaCampanias.get(0); 
            campania.eliminarDonante(rut);
        } else {
            System.out.println("Campaña no encontrada.");
            return false;
        }
        return true;
    }
    
    public boolean eliminarCampania(String ubicacion, String nombreCampania) throws CampaniaNoEncontradaException {
        String clave = generarClaveCampania(ubicacion, nombreCampania);
        if (campañas.containsKey(clave)) {
            List<Campania> listaCampanias = campañas.get(clave);
            
            if (listaCampanias == null) {
                throw new CampaniaNoEncontradaException("No se encontró la campaña en la ubicación especificada.");
            }

            for (int i = 0; i < listaCampanias.size(); i++) {
                if (listaCampanias.get(i).getNombre().equals(nombreCampania)) {
                    listaCampanias.remove(i);
                
                    // Si la lista queda vacía, eliminar también la entrada en el mapa
                    if (listaCampanias.isEmpty()) {
                        campañas.remove(clave);
                    }
                
                    System.out.println("Campaña eliminada exitosamente.");
                    return true;
                }
            }
        }
        
        
        System.out.println("Campaña no encontrada.");
        return false;
    }

    
    public void listarCampanias() {
       if (campañas.isEmpty()) {
           System.out.println("No hay campañas registradas.");
           return;
       }
       System.out.println("Listado de campañas:");
       for (Map.Entry<String, List<Campania>> entry : campañas.entrySet()) {
           String clave = entry.getKey();
           List<Campania> listaCampanias = entry.getValue();
           
            for (Campania campania : listaCampanias) {
                System.out.println("Nombre: " + campania.getNombre() + ", Hospital: " + campania.getUbicacion() + ", Fecha: " + campania.getFecha());
            }
       }
    }
    
    public void listarTodosLosDonantes() {
        List<Donante> donantes = obtenerTodosLosDonantes(); // Llama al método existente para obtener todos los donantes

        if (donantes.isEmpty()) {
            System.out.println("No hay donantes registrados.");
        } else {
            System.out.println("Listado de todos los donantes:");
            for (Donante donante : donantes) {
                System.out.println(donante.getDetalles()); // Asegúrate de que el método getDetalles() devuelva la información necesaria
            }
        }
    }
    
    public List<Donante> filtrarDonantesPorTipoSangreEnTodasLasCampanias(String tipoSangre) {
        List<Donante> donantesFiltrados = new ArrayList<>();
    
        for (List<Campania> listaCampanias : campañas.values()) {
            for (Campania campania : listaCampanias) {
                donantesFiltrados.addAll(campania.filtrarDonantesPorTipoSangre(tipoSangre));
            }
        }
    
        return donantesFiltrados;
    }
    
    public Map<String, List<Campania>> getCampanias() {
        return campañas;
    }


    public void mostrarInventarioSangre() {
        inventarioDeSangre.mostrarInventario();
    }
    
    public Map<String, Integer> getReservasSangre() {
        return inventarioDeSangre.getReservasSangre();
    }
    
    public boolean retirarSangre(String tipoSangre, int cantidad) {
        return inventarioDeSangre.retirarSangre(tipoSangre, cantidad);
    }
}
