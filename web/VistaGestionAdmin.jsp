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
        <div class = "contenedor">
            <!-- BODY DE LA PAGINA INDEX -->
            <table> 
                <tr> 
                    <td class="img-oferta">
                        Gestion de los aviones
                        <a href="VistaGestionAvion.jsp">
                            <img src="src/gestionAvion.jpg" alt ="">
                        </a>
                    </td> 

                    <td class="img-oferta">
                        Gestion de los vuelos
                        <a href="VistaGestionVuelo.jsp">
                            <img src="src/gestionVuelo.jpg"alt ="">
                        </a>
                    </td> 
                    <td class="img-oferta">
                        Gestion de los aeropuertos
                        <a href="VistaGestionAeropuerto.jsp">
                            <img src="src/gestionAeropuerto.jpg" alt ="">
                        </a>
                    </td> 
                    <td class="img-oferta">
                        Gestion de las compras
                        <a href="VistaGestionCompras.jsp">
                            <img src="src/gestionAeropuerto.jpg" alt ="">
                        </a>
                    </td>
                    <td class="img-oferta">
                        Gestion de los clientes
                        <a href="VistaGestionCliente.jsp">
                            <img src="src/gestionCliente.jpg" alt ="">
                        </a>
                    </td>
                </tr>  
            </table>
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

