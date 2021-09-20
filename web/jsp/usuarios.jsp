<%-- 
    Document   : usuarios
    Created on : 6/09/2021, 8:44:17 p. m.
    Author     : Joan
 * PWTiendagenerica
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de Usuarios</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" crossorigin="anonymous">
    </head>
    <body>
        <div class="d-flex"> 
            <div class="card col-sm-4">
                <div class="card-body">
                    <form class="needs-validation" action="Controlador?menu=Usuarios" method="POST" novalidate>
                        <div class="form-group" >
                            <label>ID</label>
                            <c:if test="${usuarioEdit.id!=0}">
                                <input  type="text" name="txtId" value="${usuarioEdit.id}" readonly="" 
                                        class="form-control">
                            </c:if>
                            <c:if test="${usuarioEdit.id==0}">
                                <input  type="text" name="txtId" class="form-control" required="">
                                <div class="valid-feedback">Campo OK</div>
                                <div class="invalid-feedback">Faltan datos del usuario</div>
                            </c:if>
                        </div>
                        <div class="form-group" >
                            <label>Nombre</label>
                            <input  type="text" name="txtNombre" value="${usuarioEdit.nombre}" 
                                    class="form-control" required="">
                            <div class="valid-feedback">Campo OK</div>
                            <div class="invalid-feedback">Faltan datos del usuario</div>
                        </div>
                        <div class="form-group" >
                            <label>Email</label>
                            <input  type="text" name="txtEmail" value="${usuarioEdit.email}" 
                                    class="form-control" required="">
                            <div class="valid-feedback">Campo OK</div>
                            <div class="invalid-feedback">Faltan datos del usuario</div>
                        </div>            
                        <div class="form-group" >
                            <label>Clave</label>
                            <input  type="password" name="txtPassword" value="${usuarioEdit.password}" 
                                    class="form-control" required="">
                            <div class="valid-feedback">Campo OK</div>
                            <div class="invalid-feedback">Faltan datos del usuario</div>
                        </div>
              <!--          <div class="form-group" >
                            <label>Correo</label>
                            <input  type="text" name="txtCorreo" value="${usuarioEdit.email}" 
                                    class="form-control" required="">
                            <div class="valid-feedback">Campo OK</div>
                            <div class="invalid-feedback">Complete los datos</div>
                        </div>   -->
                        <div class="form-group" >
                            <label>Tipo Usuario</label>
                            <select class="form-control" name="txtTipo" required="">
                                <c:if test="${empty categorias}">
                                    <option selected="" disabled=" " value="">Por favor Seleccione..</option>
                                    <option value="Administrador">Administrador</option>
                                    <option value="Cliente">Cliente</option>
                                </c:if>
                                <c:forEach items="${categorias}" var="cate">
                                    <option value="${cate}" ${cate == usuarioEdit.tipo_usuario ? 'selected' :''}>
                                        <c:out value="${cate}" />
                                    </option>
                                </c:forEach>
                            </select>
                            <div class="valid-feedback">Campo OK</div>
                            <div class="invalid-feedback">Faltan datos del usuario</div>
                        </div>
                        <input type="submit" name="accion" value="Agregar" class="btn btn-success" >
                        
                       
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-success" >
            <c:if test="${mensaje!=null}">
            <div class="container-fluid">
            <br>
            <div class=" row">
                <div class="col-md-2">
                    <div class="alert alert-success">
                        <button class="close" data-dismiss="alert"><span>&timesb;</span></button>
                        Usuario agregado</div>
                </div>
            </div>
        </div>
            </c:if>
            
                     </div>
            </div>
            <div class="col-sm-8">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>NOMBRE</th>
                            <th>CORREO</th>
                            <th>CLAVE</th>
                            <th>TIPO USUARIO</th>
                            <th>ACCIONES</th>
                    </thead>   
                      
                    
                    <tbody>
                        <c:forEach var="e" items="${usuarios}">
                            <tr>
                                <td>${e.id}</td>
                                <td>${e.nombre}</td>
                                <td>${e.email}</td>
                                <td>${e.password}</td>
                                <td>${e.tipo_usuario}</td>
                                <td>
                                <a href="Controlador?menu=Usuarios&accion=Editar&id=${e.id}" 
                                   class="btn btn-warning btn-sm"><i class="fa fa-edit"></i></a>
                                
                                   
                        
                                   <input type="hidden" id="codigo" value="${e.id}">
                                   <a id="deleteUser" href="<c:url value="Controlador?menu=Usuarios&accion=Eliminar">
                                     
                                          <c:param name="accion" value="Eliminar" />
                                          <c:param name="id" value="${e.id}" />
                                      </c:url>"><button type="button" class="btn btn-danger" data-toggle="tooltip"  title="Eliminar" data-original-title="Eliminar">
                                           <i class="fa fa-trash"></i></button></a>

                                </td>
                            </tr>                                                    
                                    </c:forEach>   
                    </tbody>
                </table>
            </div>
        </div>
        <script
  src="https://code.jquery.com/jquery-3.6.0.min.js"
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>                            
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" 
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" 
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" 
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" 
        crossorigin="anonymous"></script>
        <script>
        // Example // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function () {
        'use strict'

        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.querySelectorAll('.needs-validation')

        // Loop over them and prevent submission
        Array.prototype.slice.call(forms)
        .forEach(function (form) {
        form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
        }
        form.classList.add('was-validated')
        }, false)
        })
        })()
    
    </script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="js/funcionesusurio.js" type="text/javascript"></script>
    <link href="styles.css" rel="stylesheet" type="text/css"/>
    <link href="swetalert/sweetalert.css" rel="stylesheet" type="text/css"/>
    <script src= "swetalert/sweetalert.js" type="text/javascript"></script>
    </body>
</html>
