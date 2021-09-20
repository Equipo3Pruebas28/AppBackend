package persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * @author Joan
 * PWTiendagenerica
 */
public class Conexion {
    private Connection con;
    Statement consulta;
//    private static final String URL = "jdbc:mysql://localhost:3306/prueba";
//    private static final String URL = "jdbc:mysql://localhost:3306/proyectoweb";
    private static final String URL = "jdbc:mysql://localhost:3306/tiendagenerica2?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8"; // URL Reto5 ciclo2
//    private static final String URL = "jdbc:mysql://localhost:3306/prueba?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "Anita1514";
    public Connection Conexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
            consulta = con.createStatement();
            if(con != null){
                System.out.println("Conectados a la Base de Datos");
            }
        }catch(ClassNotFoundException | SQLException e){
                System.out.println("Error en Conexion package Modelo: "+e.getMessage());
    }
    return con;
    }   
}
