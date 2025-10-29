package app;

import dao.PersonaDAO;
import dao.ReclamoDAO;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import modelo.Persona;
import modelo.Reclamo;

public class Main {

    public static void main(String[] args) {
        // Inicialización de la Interfaz y los DAOs
        Scanner scanner = new Scanner(System.in);
        PersonaDAO personaDAO = new PersonaDAO();
        ReclamoDAO reclamoDAO = new ReclamoDAO();

        int opcion = 0;

        do {
            mostrarMenu();
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        insertarUsuarioPersona(scanner, personaDAO); // Tarea 5.1
                        break;
                    case 2:
                        eliminarReclamo(scanner, reclamoDAO);       // Tarea 5.2
                        break;
                    case 3:
                        listarReclamos(scanner, reclamoDAO);         // Tarea 5.3
                        break;
                    case 4:
                        System.out.println("Saliendo del programa. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer
                opcion = 0;
            }
        } while (opcion != 4);

        scanner.close();
    }
    
    // =========================================================================
    // LÓGICA DE PRESENTACIÓN PARA LAS TAREAS
    // =========================================================================

    private static void mostrarMenu() {
        System.out.println("\n--- Menú de Gestión de Reclamos ---");
        System.out.println("1. Insertar un Usuario (Persona)");
        System.out.println("2. Eliminar un Reclamo");
        System.out.println("3. Listar Reclamos con Rellamados de un Usuario");
        System.out.println("4. Salir");
        System.out.print("Elija una opción: ");
    }
    
    // TAREA 5.1: INSERTAR USUARIO (PERSONA)
    private static void insertarUsuarioPersona(Scanner sc, PersonaDAO dao) {
        try {
            System.out.println("\n--- 1. Insertar Persona ---");
            System.out.print("Dirección: ");
            String direccion = sc.nextLine();
            System.out.print("Teléfono: ");
            String telefono = sc.nextLine();
            System.out.print("DNI: ");
            int dni = Integer.parseInt(sc.nextLine());
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Fecha Nac. (yyyy-MM-dd): ");
            String fechaNac = sc.nextLine();
            System.out.print("Edad: ");
            int edad = Integer.parseInt(sc.nextLine());

            // Crea el objeto Modelo
            Persona nuevaPersona = new Persona(direccion, telefono, dni, nombre, fechaNac, edad);
            
            // Llama al DAO para ejecutar la Transacción
            dao.insertarPersona(nuevaPersona);
            
        } catch (SQLException e) {
            // Error manejado por el DAO, solo imprimimos el mensaje de alto nivel
            System.err.println("Fallo la inserción de Persona: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error: El DNI o la Edad deben ser números.");
        }
    }
    
    private static void eliminarReclamo(Scanner sc, ReclamoDAO dao) {
        try {
            System.out.println("\n--- 2. Eliminar Reclamo ---");
            System.out.print("Ingrese el número de reclamo a eliminar: ");
            int nroReclamo = Integer.parseInt(sc.nextLine());

            // Llama al DAO 
            int filasAfectadas = dao.eliminarReclamo(nroReclamo);

            if (filasAfectadas > 0) {
                System.out.println("Reclamo " + nroReclamo + " eliminado con éxito (Trigger de auditoría ejecutado).");
            } else {
                System.out.println("Advertencia: No se encontró el reclamo " + nroReclamo + ".");
            }
        } catch (SQLException e) {
            System.err.println("Fallo la eliminación del Reclamo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error: El número de reclamo debe ser un valor numérico.");
        }
    }
    
    private static void listarReclamos(Scanner sc, ReclamoDAO dao) {
        try {
            System.out.println("\n--- 3. Listar Reclamos y Rellamados ---");
            System.out.print("Ingrese el ID del Usuario (ID_RE): ");
            int idUsuario = Integer.parseInt(sc.nextLine());

            // Llama al DAO 
            List<Reclamo> lista = dao.listarReclamosConRellamados(idUsuario);

            if (lista.isEmpty()) {
                System.out.println("El usuario " + idUsuario + " no tiene reclamos registrados.");
                return;
            }

            System.out.println("\nResultados para Usuario ID: " + idUsuario);
            System.out.println("----------------------------------------");
            for (Reclamo r : lista) {
                System.out.println(r); // Usa el método toString() de Reclamo.java
            }
            System.out.println("----------------------------------------");

        } catch (SQLException e) {
            System.err.println("Fallo al listar los Reclamos: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error: El ID de Usuario debe ser un valor numérico.");
        }
    }
}