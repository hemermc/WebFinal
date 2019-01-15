<!DOCTYPE html>
<html>
    <head>
        <title>Más que vuelos ADMINISTRADOR</title>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <link rel="stylesheet" href="css/estilos.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body id="body">
        <jsp:include page="ComponenteHeader.jsp"/>
        <%
            //Comprobamos si la session es nueva.
            if (session == null | session.isNew()) {
                session.invalidate();
                //Redirecciono al login.html para que se loguee.
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/VistaInicioSesion.jsp");
                dispatcher.forward(request, response);
            }
        %>
        <section id="cuerpo">
            <section class="zonaErrores">${mensaje}</section> <          !-- Seccion donde se muestran los posibles errores al realizar alguna gestion. -->
            <a class="accordion-section-title" href="#gestionAvion">Gestión de aviones</a> <!-- Aqui va el titulo del tag -->
            <section id="gestionAvion"> 
                <!-- Aqui va el contenido del tag -->
                <form action="/ControladorAdminVuelo" method="post">
                    <input type="radio" name="eleccionUsuario" class="insertarAvion" id="radioInsertarAvion" value="insertarAvion">
                    <label for="radioInsertarAvion" class="labelInsertarAvion"><b>Insertar Avion</b><br></label>
                    <section id="formInsertarAvion">
                        <label>Identificador del avion</label><input type="text" name="idAvion"><br>
                    </section>

                    <input type="radio" name="eleccionUsuario" class="eliminarAvion" id="radioEliminarAvion" value="eliminarAvion">
                    <label for="radioEliminarAvion" class="labelEliminarAvion"><b>Eliminar avion</b><br></label>
                    <section id="formEliminarAvion">
                        <label id="textoAvionElim">Introduce el identificador del avion que quieres eliminar</label><br>
                        <label><b>Identificador avion</b></label><input type="text" name="identificadorAntiguoAvion"><br>
                    </section>

                    <input type="radio" name="eleccionUsuario" class="consultModificAvion" id="radioConsultModificAvion" value="consultModificAvion">
                    <label for="radioConsultModificAvion" class="labelConsultModificAvion"><b>Consultar avion</b><br></label>
                    <section id="formConsultModificAvion">
                        <label>Introduce el identificador del avion que quieres consultar.</label><br>
                        <label><b>Identificador avion</b></label><input type="text" name="identificadorAvion2"><br><br><br>                                
                    </section> 
                    <input type="submit" class="botonEnvio" id="botonEnvioDatos">
                </form>
            </section>
        </section>
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

