package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import modelo.Usuario;
import persistencia.UsuarioDAO;

/**
 *PWTiendagenericaID
 * @author Joan
 */
public class Controlador extends HttpServlet {

    Usuario usuario = new Usuario(); 
    UsuarioDAO usuarioDao = new UsuarioDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("Usuarios")) {
            switch (accion) {
                
                    
                case "Listar":
                    String tipos [] = {"Administrador","Cliente"}; 
                    request.setAttribute("usuarios", usuarioDao.getUsuarios());
                    request.setAttribute("tipos", tipos);
                    request.setAttribute("usuarioEdit", new Usuario());
                    break;
                case "Agregar":
                    int id = Integer.parseInt(request.getParameter("txtId"));
                  //  String cedula = request.getParameter("txtCedula");
                    String nombre = request.getParameter("txtNombre");
                    String email = request.getParameter("txtEmail");
                    String tipo_usuario = request.getParameter("txtTipo");
                    String password = request.getParameter("txtPassword");
                    
                    usuario.setId(id);
                  //  usuario.setCedula(cedula);
                    usuario.setNombre(nombre);
                    usuario.setEmail(email);
                    usuario.setTipo_usuario(tipo_usuario);
                    usuario.setPassword(password);
                    
                    usuarioDao.agregarUsuario(usuario);
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    int ideu = Integer.valueOf(request.getParameter("id"));
                    Usuario usu = new Usuario();
                    String [] categorias = {"Administrador","Cliente"};
                    usu = usuarioDao.getUsuarioCedula(ideu);
                    request.setAttribute("usuarioEdit", usu);
                    request.setAttribute("categorias", categorias);
                    break;
                case "Actualizar":
                    int idUsuarioa = Integer.parseInt(request.getParameter("txtId"));
                  //  String cedulaa = request.getParameter("txtCedula");
                    String nombreUsuarioa = request.getParameter("txtNombre");
                    String correoa = request.getParameter("txtEmail");
                    String clavea = request.getParameter("txtPassword");
                    String tipoUsuarioa = request.getParameter("txtTipo");
                    
                    
                    usuario.setId(idUsuarioa);
                 //   usuario.setCedula(cedulaa);
                    usuario.setNombre(nombreUsuarioa);
                    usuario.setEmail(correoa);
                    usuario.setPassword(clavea);
                    usuario.setTipo_usuario(tipoUsuarioa);
                    usuarioDao.actualizarUsuario(usuario);
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;
                    
              case "Buscar":
                    int idUsuarioc = Integer.parseInt(request.getParameter("txtId"));
                  //  String cedulaa = request.getParameter("txtCedula");
                    String nombreUsuarioc = request.getParameter("txtNombre");
                    String correoc = request.getParameter("txtEmail");
                    String clavec = request.getParameter("txtPassword");
                    String tipoUsuarioc = request.getParameter("txtTipo");
                    
                    
                    usuario.setId(idUsuarioc);
                 //   usuario.setCedula(cedulaa);
                    usuario.setNombre(nombreUsuarioc);
                    usuario.setEmail(correoc);
                    usuario.setPassword(clavec);
                    usuario.setTipo_usuario(tipoUsuarioc);
                    usuarioDao.buscarUsuario(usuario);
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Buscar").forward(request, response);
                    break;
                    
                case "Eliminar":
                    
                    int idUsuarioe = Integer.valueOf(request.getParameter("id"));
                    Usuario usus = new Usuario();
                    
                    if (request.getParameter("id")!=null){
                        usus.setId(Integer.parseInt(request.getParameter("id")));
                        try{
                        usuarioDao.eliminarUsuario(idUsuarioe);
                        response.sendRedirect("Controlador?menu=Usuarios&accion=Listar");
                        }catch(Exception e){
                                request.setAttribute("mensaje","no se pudo acceder a la base de datos" + e.getMessage());
                }
                        
            
        }else{
            request.setAttribute("mensaje","no se encontró el usuario");
           
        }
                     break;
                            
                    
    
                    
                   
                    
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("jsp/usuarios.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

