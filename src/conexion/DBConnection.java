package conexion; //me genero un error, tengo que solucionarlo

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; //para manejar la excepcion en caso de que no encuentre el drive (mas formal)

/**
 * Clase que gestiona la conexión a la base de datos MySQL 
 * (con valores estaticos onda teorico 9)
 */
public class DBConnection {

    // 1. Parámetros de conexión fijos (Hardcoded)
    private static final String DRIVER = "org.gjt.mm.mysql.Driver"; 
    private static final String URL = "jdbc:mysql://localhost/prueba"; // URL JDBC para MySQL
    private static final String USER = "tu_user";       // agregar tu user 
    private static final String PASSWORD = "tu_password"; // agregar tu clave

    // SQLException Si hay un error de conexión o driver.
    //Investigando es necesario ya que no admite si no hay una excepcion

    public static Connection getConnection() throws SQLException {
        
        try {
            //carga el driver de la base de datos si no se cargo.
            Class.forName(DRIVER); 

        } catch (ClassNotFoundException e) {
            //error en caso de que no encuentra el drive (verificar carpeta lib en todo caso)
            System.err.println("ERROR: drive NO encontrado :C .");
            throw new SQLException("Fallo al cargar el driver JDBC. Verifique la carpeta 'lib/'.", e);
        }

        // 3. Obtener la conexión
        // DriverManager.getConnection() retorna la Interface Connection (Teórico 9, Pág. 8)
        return DriverManager.getConnection(URL, USER, PASSWORD); 
    }
}