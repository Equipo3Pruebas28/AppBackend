<%-- 
    Document   : vistaAdmin
    Created on : 09-05-2021, 04:53:15 PM
    Author     : Joan
 * PWTiendagenerica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../font/css/font-awesome.min.css">
        <link rel='stylesheet prefetch' href='../bootstrap/css/bootstrap.min.css'>
        <title>Admin</title>
    </head>
    <%
        String user = "";
        HttpSession objSesion = request.getSession();
        String usuario;
        if (objSesion.getAttribute("usuario") != null && objSesion.getAttribute("nivel") == "Administrador") {
            usuario = objSesion.getAttribute("usuario").toString();
            user = "<label>" + usuario + "</label>";
        } else if (objSesion.getAttribute("usuario") != null && objSesion.getAttribute("nivel") == "Cliente") {
            out.print("<script>location.replace('vistaCliente.jsp');</script>");
        } else {
            out.print("<script>location.replace('../index.jsp');</script>");
        }
    %>
    <body>
    <center>
        <h1>Vista Administrador</h1>
        <h1>Bienvenido <% out.print(user);%></h1>
        <h3>El administrador tiene acceso total a todo </h3>
        <a href="vistaCliente.jsp" class="btn btn-success"><span class="fa fa-eye"></span> Vista Cliente</a>
        <a href="cerrarSesion.jsp" class="btn btn-success"><span class="fa fa-sign-out"></span> Cerrar sesion</a>
    </center>
    </body>
</html>
