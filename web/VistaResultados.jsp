<%-- 
    Document   : VistaResultados
    Created on : 14-ene-2019, 19:24:50
    Author     : alexandermunguiaclemente
--%>

<%@ include file="/ComponenteHeader.jsp" %>
    <div class="contenedor">
        <form class="formulario" action="ControladorPago" method="post">
                    <%
                        ArrayList<Vuelo> listaVueloIda = (ArrayList) session.getAttribute("vuelosIda");
                        ArrayList<Vuelo> listaVueloVuelta = (ArrayList) session.getAttribute("vuelosVuelta");
                        out.println("<h2>Billetes de ida</h2>");
                        if (listaVueloIda != null) {
 
                            for(Vuelo vuelo : listaVueloIda){
                    %>      
                                <div class="formulario-billete"> 
                                    <div class="row">
                                        <div class="col-md-1"></div>
                                        <div class="col-md-5">
                                            <p>Id vuelo:  <% out.println(vuelo.getId_vuelo());%> </p>
                                            <p>Origen: <% out.println(vuelo.getOrigen());%> </p>
                                            <p>Destino:  <% out.println(vuelo.getDestino());%> </p>
                                            <p>fecha:  <% out.println(vuelo.getFecha());%> </p>
                                            <p>Precio:  <% out.println(vuelo.getPrecio());%> </p>
                                            <input type="radio" name="vuelo-ida" value=<% out.println(vuelo.getId_vuelo());%>> 
                                        </div>
                                        <div class="col-md-1"></div>
                                    </div>
                                </div>
                    <%  
                            }
                        } else {
                            out.println("<h3>No se han encontrado vuelos de ida</h3>");
                        }
                        out.println("<h2>Billetes de vuelta</h2>");
                        if (listaVueloVuelta!= null) {
 
                            for(Vuelo vuelo : listaVueloVuelta){
                    %>      
                                <div class="formulario-billete"> 
                                    <div class="row">
                                        <div class="col-md-1"></div>
                                        <div class="col-md-5">
                                            <p>Id vuelo:  <% out.println(vuelo.getId_vuelo());%> </p>
                                            <p>Origen: <% out.println(vuelo.getOrigen());%> </p>
                                            <p>Destino:  <% out.println(vuelo.getDestino());%> </p>
                                            <p>fecha:  <% out.println(vuelo.getFecha());%> </p>
                                            <p>Precio:  <% out.println(vuelo.getPrecio());%> </p>
                                            <input type="radio" name="vuelo-vuelta" value=<% out.println(vuelo.getId_vuelo());%>> 
                                        </div>
                                        <div class="col-md-1"></div>
                                    </div>
                                </div>
                     <%  
                            }
                        } else {
                            out.println("<h3>No se han encontrado vuelos de vuelta</h3>");
                        }
                    %> 
                    <div class="form-group">
                            <div class="col-md-12 text-center">
                                <button type="submit" class="btn btn-primary btn-lg">Comprar</button>
                            </div>
                    </div>
        </form>
    </div>
    <%@ include file="/ComponenteFooter.jsp" %>
</html>
