package app;

import dao.ReclamoDAO;
import dao.UsuarioDAO;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import modelo.Empresa;
import modelo.Persona;
import modelo.Reclamo;

public class Main {

    public static void main(String[] args) {
        // Inicialización de la Interfaz y los DAOs
        Scanner scanner = new Scanner(System.in);
        
        UsuarioDAO usuarioDAO = new UsuarioDAO(); 
        ReclamoDAO reclamoDAO = new ReclamoDAO();

        int opcion = 0;

        do {
            mostrarMenu();
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcion) {
                    case 1:
                        seleccionarTipoUsuario(scanner, usuarioDAO); 
                        break;
                    case 2:
                        eliminarReclamo(scanner, reclamoDAO);       
                        break;
                    case 3:
                        listarReclamos(scanner, reclamoDAO);         
                        break;
                    case 4:
                        System.out.println("Saliendo del programa. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine(); 
                opcion = 0;
            }
        } while (opcion != 4);

        scanner.close();
    }
    

    private static void mostrarMenu() {
        System.out.println("\n - Menú de Gestión de Reclamos -");
        System.out.println("1. Insertar un Usuario (Persona/Empresa)");
        System.out.println("2. Eliminar un Reclamo");
        System.out.println("3. Listar Reclamos con Rellamados de un Usuario");
        System.out.println("4. Salir");
        System.out.print("Elija una opción: ");
    }
    
    //inserciones
    
    private static void seleccionarTipoUsuario(Scanner sc, UsuarioDAO dao) {
        System.out.println("\n - 1. Insertar Usuario -");
        System.out.println("1) Insertar Persona"); 
        System.out.println("2) Insertar Empresa"); 
        System.out.print("Elija el tipo de usuario (1/2): ");
        
        try {
            int tipo = Integer.parseInt(sc.nextLine().trim());

            if (tipo == 1) {
                insertarPersona(sc, dao);
            } else if (tipo == 2) {
                insertarEmpresa(sc, dao);
            } else {
                System.err.println("Opción de tipo de usuario no válida.");
            }
        // Captura las excepciones lanzadas por los métodos de inserción (SQLException, NumberFormat, DateParse)
        } catch (SQLException e) { 
            System.err.println("Error de Insercion: " + e.getMessage()); // Muestra el mensaje detallado de la DB
        } catch (DateTimeParseException e) {
            System.err.println("error: solo se admite el formato yyyy-mm-dd.");
        } catch (NumberFormatException e) {
            System.err.println("ERROR: Ingrese solo números en la opción, DNI, CUIT o Capacidad Instalada.");
        }
    }

    private static void insertarPersona(Scanner sc, UsuarioDAO dao) throws SQLException, DateTimeParseException, NumberFormatException {
        System.out.print("Dirección: ");
        String direccion = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("DNI: ");
        int dni = Integer.parseInt(sc.nextLine());
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Fecha Nac. (yyyy-MM-dd): ");
        String fechaNacStr = sc.nextLine();
        
        
        // Crea el objeto Modelo
        Persona nuevaPersona = new Persona(direccion, telefono, dni, nombre, fechaNacStr);
        
        
        // Llama al DAO polimórfico
        dao.insertarUsuario(nuevaPersona);
    }
    
    private static void insertarEmpresa(Scanner sc, UsuarioDAO dao) throws SQLException, NumberFormatException {
        System.out.print("Dirección: ");
        String direccion = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();
        
        System.out.print("CUIT: "); 
        long cuit = Long.parseLong(sc.nextLine());
        System.out.print("Capacidad Instalada (Kw): ");
        int capacidadInstalada = Integer.parseInt(sc.nextLine());

        // crea el objeto modelo
        Empresa nuevaEmpresa = new Empresa(direccion, telefono, cuit, capacidadInstalada);
        dao.insertarUsuario(nuevaEmpresa); 
    }
    
    private static void eliminarReclamo(Scanner sc, ReclamoDAO dao) {
        try {
            System.out.println("\n--- 2. Eliminar Reclamo ---");
            System.out.print("Ingrese el número de reclamo a eliminar: ");
            int nroReclamo = Integer.parseInt(sc.nextLine());

            int filasAfectadas = dao.eliminarReclamo(nroReclamo);

            if (filasAfectadas > 0) {
                System.out.println("Reclamo " + nroReclamo + " eliminado "); 
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

            List<Reclamo> lista = dao.listarReclamosConRellamados(idUsuario);

            if (lista.isEmpty()) {
                System.out.println("El usuario " + idUsuario + " no tiene reclamos registrados.");
                return;
            }

            System.out.println("\nResultados para Usuario ID: " + idUsuario);
            System.out.println("----------------------------------------");
            for (Reclamo r : lista) {
                System.out.println(r); 
            }
            System.out.println("----------------------------------------");

        } catch (SQLException e) {
            System.err.println("Fallo al listar los Reclamos: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error: El ID de Usuario debe ser un valor numérico.");
        }
    }
}