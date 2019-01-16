<%-- 
    Document   : VistaOfertasFinal
    Created on : 16-ene-2019, 19:29:00
    Author     : Robert
--%>
<link rel="stylesheet" href="./css/estilos.css"/>
<%@ include file="/ComponenteHeader.jsp" %>
    <div class="contenedor">
       
        <form class="formulario" action="ControladorOfertas" method="post">
            <div class="form-group"> 
                
                    <%
                        ArrayList<Vuelo> listaVueloOferta = new ArrayList<>();
						GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
						Connection conexion = gestionDB.establecerConexion();
                                                CRUDVuelo cRUDVuelo = new CRUDVuelo(conexion);
						listaVueloOferta = cRUDVuelo.obtenerVuelosOferta(Boolean.TRUE);
                        out.println("<h2>Billetes en oferta </h2>");
                        if (listaVueloOferta.size() > 0) {
                            out.println("<table class=\"table table-bordered\"><tr>");
                            out.println("<tr><th>Origen</th>"+
                                        "<th>Destino</th>"+
                                        "<th>Fecha</th>"+
                                        "<th>Precio</th>"+
                                        "<th>Elecci&oacuten</th></tr>" );
                            
                            for(Vuelo vuelo : listaVueloOferta){
                                if(vuelo != null){
                                     out.println("<tr><td>"+vuelo.getOrigen()+"</td>"+
                                        "<td>"+vuelo.getDestino()+"</td>"+
                                        "<td>"+vuelo.getFecha()+"</td>"+
                                        "<td>"+vuelo.getPrecio()+"</td>"+
                                        "<td align=\"center\"><input required type=\"radio\" name=\"eleccion\" value=\""+vuelo.getId_vuelo()+"\"></td></tr>");
                                        
                                }
                            }
                            out.println("</table>");
                        } else {
                            out.println("<h3>No se han encontrado vuelos en oferta</h3>");
                        }
                         %> 
            </div>
            <div class="form-group">
                    <div class="col-md-12 text-center">
                        <button type="submit" class="btn btn-primary btn-lg">Comprar</button>
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
