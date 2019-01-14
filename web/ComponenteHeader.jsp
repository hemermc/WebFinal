<%-- 
    Document   : ComponenteHeader
    Created on : 12-ene-2019, 12:11:53
    Author     : amunguia
--%>

<%@page import="com.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Más que vuelos</title>
        <link rel="stylesheet" href="css/estilos.css">
	<link rel="stylesheet" href="css/style.css">
    </head>
    <nav>
        <header>
            <div class="contenedor">
                <!--prueba subid no quiero a-->
                <div class="logo">
                    <img src="http://dusseldorf.com.ar/wp-content/uploads/agencia-viajes2.jpg" width="200" height="60" >
                </div>
                <span class ="login">
                    <% //Comprueba si ha iniciado sesión
                            Usuario usuario = (Usuario) session.getAttribute("usuario");
                            Boolean administrador = (Boolean) session.getAttribute("administrador");
                    if (usuario == null) {
                        out.println("<li><a href=\"VistaInicioSesion.jsp\"><span class =\"icon-key\"></span>Acceder</a></li>");
                        out.println("<li><a href=\"VistaRegistroCliente.jsp\"><span class =\"icon-pen\"></span>Registrarse</a></li>");
                    } else {
                        out.println("<li ><a>" + usuario.getNombre_usuario() + "</a></li>");
                        out.println("<li><a href=\"" + request.getContextPath() + "/ControladorLogout\">Cerrar Sesión</a></li>");
                    }
                %>
                </span>
                <nav class="menu">
                    <ul>
                        <li><a href="index.jsp">Inicio</a></li>
                        <li><a href="VistaPaquetes.jsp">Paquetes de viaje</a></li>
                        <li><a href="VistaOfertas.jsp">Ofertas</a></li>
                         <li><a href="VistaUsuarioDetalles.jsp">Mi perfil</a></li>
                        <li><a href="VistaContacto.jsp">Contacto</a></li>
                    </ul>
                </nav>
            </div>
        </header>
        
    </nav>

