<%-- 
    Document   : VistaResultados
    Created on : 14-ene-2019, 19:24:50
    Author     : alexandermunguiaclemente
--%>
<link rel="stylesheet" href="./css/estilos.css"/>
<%@ include file="/ComponenteHeader.jsp" %>
    <div class="contenedor">
       
        <form class="formulario" action="ControladorCompra" method="post">
            <div class="form-group"> 
                    <%
                        ArrayList<Vuelo> listaVueloIda = (ArrayList) session.getAttribute("vuelos-Ida");
                        out.println("<h2>Billetes de ida</h2>");
                        if (listaVueloIda.size() > 0) {
                            out.println("<table class=\"table table-bordered\"><tr>");
                            out.println("<tr><th>Origen</th>"+
                                        "<th>Destino</th>"+
                                        "<th>Fecha</th>"+
                                        "<th>Precio</th>"+
                                        "<th>Elección</th></tr>" );
                            
                            for(Vuelo vuelo : listaVueloIda){
                                if(vuelo != null){
                                     out.println("<tr><td>"+vuelo.getOrigen()+"</td>"+
                                        "<td>"+vuelo.getDestino()+"</td>"+
                                        "<td>"+vuelo.getFecha()+"</td>"+
                                        "<td>"+vuelo.getPrecio()+"</td>"+
                                        "<td align=\"center\"><input required type=\"radio\" name=\"eleccionIda\" value=\""+vuelo.getId_vuelo()+"\"></td></tr>");
                                        
                                }
                            }
                            out.println("</table>");
                        } else {
                            out.println("<h3>No se han encontrado vuelos de ida</h3>");
                        }
                         %> 
            </div>
            <div class="form-group">
                <%
                        ArrayList<Vuelo> listaVueloVuelta = (ArrayList) session.getAttribute("vuelos-Vuelta");
                        out.println("<h2>Billetes de ida</h2>");
                        if (listaVueloVuelta.size() > 0) {
                            out.println("<table class=\"table table-bordered\"><tr>");
                            out.println("<tr><th>Origen</th>"+
                                        "<th>Destino</th>"+
                                        "<th>Fecha</th>"+
                                        "<th>Precio</th>"+
                                        "<th>Elección</th></tr>" );
                            
                            for(Vuelo vuelo : listaVueloVuelta){
                                if(vuelo != null){
                                     out.println("<tr><td>"+vuelo.getOrigen()+"</td>"+
                                        "<td>"+vuelo.getDestino()+"</td>"+
                                        "<td>"+vuelo.getFecha()+"</td>"+
                                        "<td>"+vuelo.getPrecio()+"</td>"+
                                        "<td align=\"center\"><input required type=\"radio\" name=\"eleccionVuelta\" value=\""+vuelo.getId_vuelo()+"\"></td></tr>");
                                        
                                }
                            }
                            out.println("</table>");
                        } else {
                            out.println("<h3>No se han encontrado vuelos de ida</h3>");
                        }
                         %>
            </div>
            
            <div class="form-group">
                    <div class="col-md-12 text-center">
                        <button onclick="myFunction()" type="submit" class="btn btn-primary btn-lg">Comprar</button>
                    </div>
            </div>
        </form>
    </div>
    <script>
        function myFunction() {
            confirm("Pulsa boton para confirmar");
        
        }
    </script>
    <%@ include file="/ComponenteFooter.jsp" %>
</html>
