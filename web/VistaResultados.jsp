<%-- 
    Document   : VistaResultados
    Created on : 14-ene-2019, 19:24:50
    Author     : alexandermunguiaclemente
--%>

<%@page import="java.time.LocalDate"%>
<%@page import="com.modelo.Vuelo"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="ComponenteHeader.jsp"/>
        <div class="contenedor-pujas">
                    <%
                        ArrayList<Vuelo> listaVueloIda = (ArrayList) session.getAttribute("vuelosIda");
                        ArrayList<Vuelo> listaVueloVuelta = (ArrayList) session.getAttribute("vuelosVuelta");
                        out.println("<h2>Billetes de ida</h2>");
                        if (listaVueloIda != null) {
 
                            for(Vuelo vuelo : listaVueloIda){
                                out.println("<div>"
                                        + "<p>Id vuelo: " + vuelo.getId_vuelo() + "</p>"
                                        + "<p>origen: " + vuelo.getOrigen() + "</p>"
                                        + "<p>destino: " + vuelo.getDestino() + "</p>"
                                        + "<p>fecha: " + vuelo.getFecha() + "</p>"
                                        + "<p>Id avión: " + vuelo.getId_avion() + "</p>"
                                        + "<p>Precio: " + vuelo.getPrecio()+ "</p>"
                                        + "</div>");
                           
                            }
                        } else {
                            out.println("<h3>No se han encontrado vuelos de ida</h3>");
                        }
                        out.println("<h2>Billetes de vuelta</h2>");
                        if (listaVueloVuelta!= null) {
 
                            for(Vuelo vuelo : listaVueloVuelta){
                                out.println("<div>"
                                        + "<p>Id vuelo: " + vuelo.getId_vuelo() + "</p>"
                                        + "<p>origen: " + vuelo.getOrigen() + "</p>"
                                        + "<p>destino: " + vuelo.getDestino() + "</p>"
                                        + "<p>fecha: " + vuelo.getFecha() + "</p>"
                                        + "<p>Id avión: " + vuelo.getId_avion() + "</p>"
                                        + "<p>Precio: " + vuelo.getPrecio()+ "</p>"
                                        + "</div>");
                           
                            }
                        } else {
                            out.println("<h3>No se han encontrado vuelos de vuelta</h3>");
                        }
                    %> 
        </div>
    </body>
</html>
