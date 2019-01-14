<%-- 
    Document   : VistaContacto
    Created on : 12-ene-2019, 12:18:25
    Author     : amunguia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Más que vuelos</title>
        <link rel="stylesheet" href="css/estilos.css">
	<link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <jsp:include page="ComponenteHeader.jsp"/>
        <div class = "contenedor">
            <div  class="grid-block" style="background-image: url('https://memoriasdeunviajesinretorno.files.wordpress.com/2014/12/airplane-taking-off-road-sky.jpg?w=1200')">
                <form class="formulario-contacto" action="ControladorContacto" method="post">
                    <div>
                        <label for="nombre">nombre: </label>
                        <!-- hay que cambiarlo a combobox -->
                        <input type="text" name="nombre"/>
                    </div>
                    <div>
                        <label for="correo">Correo: </label>
                        <!-- hay que cambiarlo a combobox -->
                        <input type="email" name="correo"/>
                    </div>
                    <div>
                        <label for="telefono">Telefono: </label>
                        <input type="text" name="telefono"/> 
                    </div>
                    <div>
                        <label for="comentario">Mensaje: </label>
                        <textarea id="msg" name="mensaje"></textarea>
                    </div>
                    <div class="boton-enviar">
                        <button type="submit">Enviar</button>
                    </div>
                </form>
            </div>
            <footer>
			
		<p>
		<br>© 2018 - 2019 Más que vuelos, S.L. - Todos los derechos reservados.</br>
                <span class ="icon-paypal"></span>
                <span class ="icon-applepay"></span>
		</p>
	    </footer>
            
        </div>
    </body>
</html>
