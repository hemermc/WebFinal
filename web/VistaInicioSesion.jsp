<%-- 
    Document   : VistaInicioSesion
    Created on : 12-ene-2019, 12:07:00
    Author     : amunguia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <body>
        <jsp:include page="ComponenteHeader.jsp"/>
         <div class="contenedor">
            
            <div class="grid-block">
                <div class="acceso">    
                    <form action="ControladorInicio" method="post" class="formulario-inicio">
                        <h1>Inicio de Sesión</h1>
                        <input type="hidden" name="llamada" value="acceso">
                        <input type="text" name="nombre_usuario"  placeholder="Nombre de usuario">
                        <input type="password" name="clave" placeholder="Contraseña">
                        <input type="submit" value="Acceder">
                    </form>	
                </div>
            </div>
         </div>
    </body>
</html>
