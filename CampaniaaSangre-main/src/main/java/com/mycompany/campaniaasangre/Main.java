package com.mycompany.campaniaasangre;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BancoSangre BancoDeSangre = new BancoSangre();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int opcionPrincipal;       
        do {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Administrar Donantes");
            System.out.println("2. Administrar Campañas");
            System.out.println("3. Administrar Reservas de Sangre");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcionPrincipal = Integer.parseInt(reader.readLine());

            switch (opcionPrincipal) {
                case 1 -> {
                    int opcionDonantes;
                    do {
                        System.out.println("\n=== Menú de Donantes ===");
                        System.out.println("1. Agregar Donante a Campaña");
                        System.out.println("2. Mostrar Donantes de una Campaña");
                        System.out.println("3. Modificar Donante");
                        System.out.println("4. Eliminar Donante");
                        System.out.println("5. Volver al Menú Principal");
                        System.out.print("Seleccione una opción: ");
                        
                        opcionDonantes = Integer.parseInt(reader.readLine());

                        switch (opcionDonantes) {
                            case 1 -> {
                                // Agregar donante a campaña
                                System.out.print("Ingrese la ubicación: ");
                                String ubicacionCampania = reader.readLine();
                                System.out.print("Ingrese el nombre de la campaña: ");
                                String nombreCampaniaDonante = reader.readLine();
                    
                                System.out.print("Ingrese nombre del donante: ");
                                String nombreDonante = reader.readLine();
                                System.out.print("Ingrese la edad del donante: ");
                                int edad = Integer.parseInt(reader.readLine());
                                System.out.print("Ingrese el RUT del donante: ");
                                String rut = reader.readLine();
                                System.out.print("Ingrese el sexo del donante (M/F): ");
                                String genero = reader.readLine();
                                System.out.print("Ingrese la dirección del donante: ");
                                String direccion = reader.readLine();
                                System.out.print("Ingrese el teléfono del donante: ");
                                String telefono = reader.readLine();
                                System.out.print("Ingrese el email del donante: ");
                                String email = reader.readLine();
                                System.out.print("Ingrese tipo de sangre (Ej: A, O, AB, B): ");
                                String tipoSangre = reader.readLine();
                                System.out.print("Ingrese factor RH (+ o -): ");
                                String factorRH = reader.readLine();
                                System.out.print("Ingrese cantidad donada (en mL): ");
                                double cantidadDonada = Double.parseDouble(reader.readLine());
                    
                                // Crear Donante
                                Donante nuevoDonante = new Donante(nombreDonante, edad, rut, genero, direccion, telefono, email, tipoSangre, factorRH, cantidadDonada);
                    
                                // Registrar donante en la campaña
                                BancoDeSangre.registrarDonanteEnCampaña(nuevoDonante, ubicacionCampania, nombreCampaniaDonante);
                            }
                            
                            case 2 -> {
                                // Mostrar donantes de una campaña
                                System.out.print("Ingrese la ubicación: ");
                                String ubicacionMostrar = reader.readLine();
                                System.out.print("Ingrese el nombre de la campaña: ");
                                String nombreCampaniaMostrar = reader.readLine();
                                BancoDeSangre.mostrarDonantesDeCampania(ubicacionMostrar, nombreCampaniaMostrar);
                            }
                            
                            case 3 -> {
                                // Modificar información de un donante
                                System.out.print("Ingrese el rut del donante que quiere modificar: ");
                                String rutMod = reader.readLine();
                                System.out.print("Ingrese la cantidad de sangre donada (mL): ");
                                double cantDonada = Double.parseDouble(reader.readLine());  
                                System.out.print("Ingrese el tipo de sangre (A, B, AB, O): ");
                                String tipoSangre = reader.readLine();
                                System.out.print("Ingrese el factor RH (+ o -): ");
                                String factRH = reader.readLine();
                                System.out.print("Ingrese la ubicación de la campaña del donante: ");
                                String ubic = reader.readLine();
                                System.out.print("Ingrese el nombre de la campaña: ");
                                String nomCam = reader.readLine();
                                BancoDeSangre.modificarDonanteEnCampania(rutMod, cantDonada, tipoSangre, factRH, ubic, nomCam);
                            }
                            
                            case 4 -> {
                                System.out.print("Ingrese el rut del donante a eliminar: ");
                                String rutMod = reader.readLine();
                                System.out.print("Ingrese la ubicación de la campaña del donante: ");
                                String ubic = reader.readLine();
                                System.out.print("Ingrese el nombre de la campaña: ");
                                String nomCam = reader.readLine();
                                BancoDeSangre.eliminarDonanteEnCampania(rutMod, ubic, nomCam);
                            }
                            
                            case 5 -> System.out.println("Volviendo al Menú Principal...");
                            default -> System.out.println("Opción no válida.");
                        }
                    } 
                    while (opcionDonantes != 5);
                }
                case 2 -> {
                   // Menú secundario para administrar campañas
                    int opcionCampanias;
                    do {
                        System.out.println("\n=== Menú de Campañas ===");
                        System.out.println("1. Agregar Campaña Nueva");
                        System.out.println("2. Asignar Personal Médico a Campaña");
                        System.out.println("3. Eliminar Campaña");
                        System.out.println("4. Listar Campañas Actuales");
                        System.out.println("5. Cargar Datos Iniciales");
                        System.out.println("6. Volver al Menú Principal");
                        System.out.print("Seleccione una opción: ");
                        
                        opcionCampanias = Integer.parseInt(reader.readLine());

                        switch (opcionCampanias) {
                            case 1 -> {
                                System.out.print("Ingrese la ubicación de la campaña (Por ejemplo: Hospital Van Buren): ");
                                String ubicacion = reader.readLine();
                                System.out.print("Ingrese el nombre de la campaña (Por ejemplo: Campaña Vecinos Solidarios): ");
                                String nombreCampania = reader.readLine();
                                System.out.print("Ingrese la fecha de la campaña (DD-MM-AAAA): ");
                                String fecha = reader.readLine();
                   
                                // Crear nueva campaña
                                Campania nuevaCampania = new Campania(nombreCampania, ubicacion, fecha);
                                BancoDeSangre.agregarCampaña(nuevaCampania);
                                System.out.println("Campaña agregada correctamente.");
                            }
                            
                            case 2 -> {
                                System.out.print("Ingrese la ubicación de la campaña: ");
                                String ubicacion = reader.readLine();

                                System.out.print("Ingrese el nombre de la campaña: ");
                                String nombreCampania = reader.readLine();

                                // Llamar al método para buscar la campaña
                                Optional<Campania> resultado = BancoDeSangre.buscarOCrearCampania(nombreCampania);

                                if (resultado.isPresent()) {
                                    Campania campania = resultado.get();
        
                                    System.out.print("Ingrese la cantidad de personal a asignar a la campaña: ");
                                    int cantPers = Integer.parseInt(reader.readLine());

                                    for (int i = 0; i < cantPers; i++) {
                                        System.out.println("Ingrese los datos del personal " + (i + 1) + ":");
                                        // Aquí solicita los datos del personal enfermero
                                        System.out.print("Nombre del personal: ");
                                        String nombreEnfermero = reader.readLine();
                                        System.out.print("Ingrese la edad del personal: ");
                                        int edadEnf = Integer.parseInt(reader.readLine());
                                        System.out.print("Ingrese el RUT del personal: ");
                                        String rut = reader.readLine();
                                        System.out.print("Ingrese el sexo del personal (M/F): ");
                                        String genero = reader.readLine();
                                        System.out.print("Ingrese la dirección del personal: ");
                                        String direccion = reader.readLine();
                                        System.out.print("Ingrese el teléfono del personal: ");
                                        String telefono = reader.readLine();
                                        System.out.print("Ingrese el email del personal: ");
                                        String email = reader.readLine();
                                        System.out.print("Ingrese la identificación del personal(Formato: XXX### | Ejemplo: ABC756): ");
                                        String identificacion = reader.readLine();
                        
                                        // Crear una instancia de PersonalEnfermero
                                        PersonalEnfermero enfermero = new PersonalEnfermero(nombreEnfermero, edadEnf, rut, genero, direccion,  telefono,  email, identificacion);

                                        // Agregar el enfermero a la campaña
                                        campania.agregarPersonalEnfermero(enfermero);
                                        System.out.println("Personal enfermero agregado a la campaña: " + campania.getNombre());
                                    }
                                } else {
                                    System.out.println("No se pudo encontrar la campaña asociada a la ubicación y nombre dados.");
                                }   
                            }
                            
                            case 3 -> {
                                System.out.print("Ingrese la ubicación de la campaña del donante: ");
                                String ubic = reader.readLine();
                                System.out.print("Ingrese el nombre de la campaña: ");
                                String nomCam = reader.readLine();
                                BancoDeSangre.eliminarCampania(ubic, nomCam);
                            }
                            case 4 -> {
                                BancoDeSangre.listarCampanias();
                            }
                            case 5 -> {
                                BancoDeSangre.cargarDatosIniciales();
                                System.out.println("Datos cargados correctamente.");
                            }
                            case 6 -> System.out.println("Volviendo al Menú Principal...");
                            default -> System.out.println("Opción no válida.");
                        }
                    } while (opcionCampanias != 6); 
                }
                case 3 -> {
                    // Menú secundario para administrar reservas de sangre
                    int opcionReservas;
                    do {
                        System.out.println("\n=== Menú de Reservas de Sangre ===");
                        System.out.println("1. Mostrar Inventario de Sangre");
                        System.out.println("2. Retirar Sangre");
                        System.out.println("3. Filtrar Donantes por Tipo de Sangre");
                        System.out.println("4. Volver al Menú Principal");
                        System.out.print("Seleccione una opción: ");
                        
                        opcionReservas = Integer.parseInt(reader.readLine());

                        switch (opcionReservas) {
                            case 1 -> {
                                // Mostrar inventario de sangre
                                BancoDeSangre.mostrarInventarioSangre();
                            }
                            case 2 -> {
                                System.out.print("Ingrese el tipo de sangre: ");
                                String tipoSangre = reader.readLine();
                                System.out.print("Ingrese la cantidad a retirar (unidades): ");
                                int cantidad = Integer.parseInt(reader.readLine());
                                BancoDeSangre.retirarSangre(tipoSangre, cantidad);
                            }
                            case 3 -> {
                                System.out.print("Ingrese el tipo de sangre: ");
                                String tipoSangre = reader.readLine();
                                BancoDeSangre.filtrarDonantesPorTipoSangreEnTodasLasCampanias(tipoSangre);
                            }
                            case 4 -> System.out.println("Volviendo al Menú Principal...");
                            default -> System.out.println("Opción no válida.");
                        }
                    } while (opcionReservas != 4);
                }
                case 4 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcionPrincipal != 4);
    }
}