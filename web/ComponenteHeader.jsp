<%-- 
    Document   : ComponenteHeader
    Created on : 12-ene-2019, 12:11:53
    Author     : amunguia
--%>

<%@page import="com.crud.*"%>
<%@page import="java.sql.*"%>
<%@page import="com.modelo.*"%>
<%@page import="com.Controlador.*"%>
<%@page import="com.common.*"%>
<%@page import="java.util.*"%>
<%@page import="java.time.LocalDate"%>
<%@page import="com.modelo.Vuelo"%>
<%@page import="java.util.ArrayList"%>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Más que vuelos</title>
        <link rel="stylesheet" href="css/estilos.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        
    </head>
        <nav class="navbar navbar-inverse sombra">
            <div class="container">
                <% //Comprueba si ha iniciado sesión
                    Usuario usuario = (Usuario) session.getAttribute(Constantes.USUARIO);
                    Boolean administrador = (Boolean) session.getAttribute(Constantes.ADMINISTRADOR);
                %>
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="index.jsp"  class="navbar-brand" >Más que vuelos</a>
       
                </div>
               
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    
                    <%
                    if (usuario == null) {
                        out.println("<div class=\"form-group\"><table align=\"right\"><tr><td><form name=\"login\" action=\"ControladorInicio\" method=\"post\" class=\"navbar-form navbar-right\">"+
                        
                            "<input hidden name=\"llamada\" type=\"text\" value=\"acceso\" >"+
                            "<input name=\"nombre_usuario\" type=\"text\" class=\"form-control\" placeholder=\"Nombre de usuario\" required=\"\">"+
                            "<input name=\"clave\" type=\"password\" class=\"form-control\" placeholder=\"Contrase&ntilde;a\" required=\"\">"+
                            "<input class=\"btn btn-primary\" type=\"submit\" value=\"Acceder\"></form><td>"+
                            "<td><a href=\"VistaRegistroCliente.jsp\" class=\"btn btn-primary\">Registrar</a></td>");
                       
                        
                    } else {
                        out.println("<a style=\"color:#fff;\" class=\"navbar-brand\" href=\"#\">Hola, " + usuario.getNombre_usuario() + " </a>" + "<form class=\"navbar-form navbar-left\">");
                        if (!administrador){                        
                        out.println("<a href=\"VistaOfertas.jsp\" class=\"btn btn-success\">Ofertas</a>"
                                    + " <a href=\"VistaUsuarioDetalles.jsp\" class=\"btn btn-success\">Mi Perfil</a> "
                                    + " <a href=\"VistaContacto.jsp\" class=\"btn btn-success\">Contacto</a> ");
                        
                        }else {
                            out.println("<a href=\"VistaGestionAeropuerto.jsp\" class=\"btn btn-success\">Aeropuertos</a>"
                                    + " <a href=\"VistaGestionAvion.jsp\" class=\"btn btn-success\">Aviones</a> "
                                     + " <a href=\"VistaGestionVuelos.jsp\" class=\"btn btn-success\">Vuelos</a> "
                                    + " <a href=\"VistaGestionCompra.jsp\" class=\"btn btn-success\">Estadisticas</a> ");
                            
                        }
                        out.println("<td align=\"right\"><a href=\"" + request.getContextPath() + "/ControladorLogout\" class=\"btn btn-danger btn-sm\" >Cerrar Sesión</a></td>" + "    </form> ");
                    }
                    out.println("</tr></table></div>");
                    %>    
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
                    

                
