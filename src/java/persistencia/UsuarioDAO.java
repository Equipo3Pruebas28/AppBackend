package persistencia;
import persistencia.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
/**
 * @author Joan
 * PWtiendagenericaID
 */
public class UsuarioDAO {

    // Definir los Atributos. Capa de Datos. Se comunica con la BDs
    Connection con = null; // Hacer la conexion a la BDs
    Conexion cn = new Conexion();
    Statement stm = null; // Separa el espacio para construir un comando SQL
    ResultSet res = null; // Guarda el resultado de la consulta
    PreparedStatement ps = null;
    
    public String login(Usuario usu) {
        String estado = "";
        ResultSet rs;
        try 
        {
            con = cn.Conexion();
            String sql = "select tipo_usuario, email from usuario where nombre=? and password=?";
            ps =con.prepareStatement(sql);
            ps.setString(1, usu.getNombre());
            ps.setString(2, usu.getPassword());
            rs= ps.executeQuery();
            if (rs.next()) {
                estado = "true";
            }
            usu.setTipo_usuario(rs.getString("tipo_usuario"));
            usu.setEmail(rs.getString("email"));
        } catch (Exception e) {
            System.err.println("Error USUARIODAO Login :" + e);
        }
        return estado;
    }

    public List<Usuario> getUsuarios() {
        String sql = "SELECT * FROM usuario";

        List<Usuario> usuarios = new ArrayList<>();

        try {
            con = cn.Conexion();
            stm = con.createStatement(); 
            res = stm.executeQuery(sql);
            while (res.next()) { // Recorrer todo el ResultSet
                Usuario usu = new Usuario(); // Instanciamos un objeto tipo Usuario
                usu.setId(res.getInt(1));
             //   usu.setCedula(res.getString(2));
                usu.setNombre(res.getString(2));
                usu.setEmail(res.getString(3));
                usu.setTipo_usuario(res.getString(4));
                usu.setPassword(res.getString(5));
                
             //   usu.setTipoUsuario(res.getString(6));
                usuarios.add(usu); // Agregarlo al ArrayList
            }
            stm.close(); // Cerrar toda la conexión a la BDs
            res.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error Consulta getUsuarios:" + e);
        }
        return usuarios; // Devuelve el ArrayList usuarios
    }
    
    public boolean agregarUsuario(Usuario usuario) {
        boolean registrar = false; // Permite identificar si ya existe el usuario
        boolean encontrado = false; // Encuentra un usuario con el correo Institucional
        String buscar = "SELECT * FROM usuario WHERE id = " // Instrucción sql
                + usuario.getId(); // Para buscar un registro con el mismo id  (cedula para reto)
        encontrado = buscar(buscar); // Ejecutamos el método con la consulta
        if(!encontrado){
            // La instrucción para insertar el registro
            /*INSERT INTO usuario (id,nombre,email,tipo_usuario,password)
              VALUES (1001,'adminicial','admin@floresta.edu.co',"Administrador","123456"),  */
            String sql = "INSERT INTO usuario VALUES (" + usuario.getId() + 
                     ",'" + usuario.getNombre()+ "','" + usuario.getEmail() +
                     "','" + usuario.getTipo_usuario() + "','" + usuario.getPassword() +"');";
            try {
                con = cn.Conexion();
                stm = con.createStatement();
                stm.execute(sql);
                registrar = true;
                stm.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: Clase UsuarioDao, método agregarUsuario");
                e.printStackTrace();
            }
        }

        return registrar;
    }

    public boolean buscar(String sql){
        boolean encontrado = false;
        con = cn.Conexion();
        try {
            stm = con.createStatement();
            res = stm.executeQuery(sql);
            while(res.next()){
                encontrado = true;
            }
        } catch (SQLException e) {
                System.out.println("Mensaje:"+e.getMessage());
                System.out.println("Estado:"+e.getSQLState());
                System.out.println("Codigo del error:"+e.getErrorCode());
                System.out.println("Error: Clase UsuarioDao, método agregarUsuario"+e.getMessage());
            }
        return encontrado;
    }

    public Usuario getUsuarioCedula(int id) {
        String sql = "SELECT * FROM usuario WHERE id =" + id;
        Usuario usu = new Usuario();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            res = ps.executeQuery();
            while (res.next()) {
                usu.setId(res.getInt(1));
             //   usu.setCedula(res.getString(2));
                usu.setNombre(res.getString(2));
                usu.setEmail(res.getString(3));
                usu.setTipo_usuario(res.getString(4));
                usu.setPassword(res.getString(5));
            }
            // cerramos el jdbc
            stm.close();
            res.close();
            con.close();
        } catch (SQLException er) { 
            System.err.println("Error en getUsuarioCedula DAO : " + er);
        }
        return usu;
    }

    public boolean actualizarUsuario(Usuario usuario) {
        boolean encontrado = false;
        boolean actualizar = false;
        //UPDATE usuario SET cedula='321',nombre='Juanito',email='JUAN@',tipo_usuario='Cliente',password='987' WHERE id=2  ;
        //ejemplo de linea para actualizar usuario buscando id
        String sql = "UPDATE usuario SET id='"+ usuario.getId()+ "',nombre='"+ usuario.getNombre()
                + "',email='"+ usuario.getEmail() + "',tipo_usuario='" + usuario.getTipo_usuario()
                + "',password='"+ usuario.getPassword()+ "'" + " WHERE id = " + usuario.getId();
        System.out.println(""+sql);
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate(sql);
            actualizar = true;
        } catch (SQLException e) {
            System.out.println("Error: Clase UsuarioDaoImple, método actualizar");
            e.printStackTrace();
        }
        return actualizar;
    }
 
         
    
    public boolean eliminarUsuario(int id) {
        boolean encontrado = false;
        boolean eliminar = false;
        String buscar = "SELECT * FROM usuario WHERE id =" + id;
        encontrado = buscar(buscar);
        String sql = "DELETE FROM usuario WHERE id = " + id;
        if(encontrado){
            try {
                con = cn.Conexion();
                ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);
               eliminar = true;
            } catch (SQLException e) {
               System.out.println("Error: Clase EstudianteDao, método eliminar");
                e.printStackTrace();
            }
        }
        return eliminar;
    }

    public void Buscar(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buscar(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buscarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
