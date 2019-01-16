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
        <div class = "contenedor">
            <!-- BODY DE LA PAGINA INDEX -->
            <table> 
                <tr> 
                    <td class="img-oferta">
                        <a href="VistaGestionAvion.jsp">
                            <img src="src/gestionAvion.jpg" alt ="" width="100" height="100">
                        </a>
                        Gestion de los aviones
                    </td> 

                    <td class="img-oferta">
                        <a href="VistaGestionVuelo.jsp">
                            <img src="src/gestionVuelo.jpg" alt ="" width="100" height="100">
                        </a>
                        Gestion de los vuelos
                    </td> 
                    <td class="img-oferta">
                        <a href="VistaGestionAeropuerto.jsp">
                            <img src="src/gestionAeropuerto.jpg" alt ="" width="100" height="100">
                        </a>
                        Gestion de los aeropuertos
                    </td> 
                    <td class="img-oferta">
                        <a href="VistaGestionCompra.jsp">
                            <img src="src/gestionAeropuerto.jpg" alt ="" width="100" height="100">
                        </a>
                        Consultar ventas
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

