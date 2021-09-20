package modelo;

/**
 * @author Joan
 * PWTiendagenericaID
 */
public class Usuario {
    private int id;
   // private String cedula;
    private String nombre;
    private String email;
    private String tipo_usuario;
    private String password;
 //   private String tipo_user;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String email, String tipo_usuario, String password) {
        this.id = id;
      //  this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.tipo_usuario = tipo_usuario;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
/*
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
*/
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
