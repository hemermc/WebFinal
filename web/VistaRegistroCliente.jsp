<%-- 
    Document   : VistaRegistroCliente
    Created on : 12-ene-2019, 12:06:39
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
                <form action="ControladorInicio" method="post" class ="formulario-inicio">
                    <h1>Nueva Cuenta</h1>
                    <input type="hidden" name="llamada" value="registro" class="registro-input">
                    <input type="text" name="nombre_usuario" class="registro-input" placeholder="Nombre de usuario">
                    <input type="password" name="clave" class="registro-input" placeholder="Contraseña">
                    <input type="email" name="email" class="registro-input" placeholder="Correo electrónico">
                    <input type="text" name="nombre" class="registro-input" placeholder="Nombre">
                    <input type="text" name="apellidos" class="registro-input" placeholder="Apellidos">
                    <input type="text" name="dni" class="registro-input" placeholder="DNI">
                    <input type="text" name="direccion_entrega" class="registro-input" placeholder="Dirección">
                    <input type="number" name="telefono" class="registro-input" placeholder="Teléfono">
                    <input type="submit" value="Registrar" class="btn-input">
                </form>	
            </div>
        </div>
        <div id="footer">
            <p id="links">
                <a href="#">Politica de privacidad</a>
                <a href="#">Terminos de uso</a>
            </p>
            <p>
                <a href="#">Inicio</a>
                <a href="#">Contactanos</a>
                <a href="#">acerca de nosotros</a>

            </p>
            <p>Copyright &copy; 2019 &minus; Mas que&amp;vuelos &middot;</p>
        </div>
    </body>
</html>
