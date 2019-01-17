<%-- 
    Document   : VistaUsuarioDetalles
    Created on : 13-ene-2019, 16:27:53
    Author     : Robert
--%>

<%@ include file="/ComponenteHeader.jsp" %>
<body>
    <div class="contenedor">
        <H1>Datos Cliente: </H1>
         <%
            Cliente cliente =(Cliente)session.getAttribute("usuario");
            String nombre = cliente.getNombre();
            String apellidos = cliente.getApellidos();
            String dni = cliente.getDni();
            String direccion_entrega = cliente.getDireccion_entrega();
            int telefono = cliente.getTelefono();
            String email = cliente.getEmail(); 
                    
                    out.println("<table class=\"table table-bordered\"><tr><td> Nombre   : </td><td>"+nombre                 +" </td></tr>"+
                                "<tr><td> Apellidos: </td><td> "+apellidos         +" </td></tr>"+
                                "<tr><td> D.N.I: </td><td>"+dni                    +" </td></tr>"+     
                                "<tr><td> Direción: </td><td>"+direccion_entrega      +" </td></tr>"+     
                                "<tr><td> Teléfono: </td><td>"+telefono               +" </td></tr>"+     
                                "<tr><td> eMail: </td><td>"+email                  +" </td></tr>"+     
                                "</table>");
                    %>
       
        <h2>Compras realizadas: </h2> 
         <%
            GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
            Connection conexion = gestionDB.establecerConexion();
            CRUDVuelo cRUDVuelo = new CRUDVuelo(conexion);
            ArrayList<Compra> listCompra = (ArrayList)session.getAttribute("listaCompras");
            //Comprobamos si la session es nueva.
          %>
        <table class="table table-striped">
            <form action="ControladorGestionCompra" method="post">
                    <thead>
                        <tr>
                            <th>id_compra</th>
                            <th>Asiento</th>
                            <th>Vuelo</th>
                            <th>Origen</th>
                            <th>Destino</th>
                            <th>Fecha</th>
                            <th>Precio</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
        
                <%
                  try {

                if (listCompra != null) {
                    for (Compra c : listCompra) {
                        if (c != null) {
                            Vuelo v = cRUDVuelo.obtenerEspecifico(c.getId_vuelo());
                            if (v != null) {
                                ;
                                out.println("<tr><td><label>" + c.getId_compra() + "</label></td>");
                                out.println("<td><label>" + c.getAsiento()+ "</label></td>");
                                out.println("<td><label>" + c.getId_vuelo() + "</label></td>");
                                out.println("<td><label>" + v.getOrigen() + "</label></td>");
                                out.println("<td><label>" + v.getDestino()+ "</label></td>");
                                out.println("<td><label>" + v.getFecha() + "</label></td>");
                                out.println("<td><label>" + v.getPrecio() + "</label></td>");
                                out.println("</tr>");

                            }
                        }
                    }
                } else {
                    out.println("<h3>No hay ninguna compra registrada</h3>");
                }
            } catch (ExceptionManager e) {
                out.println("<h3>" + e + " </h3>");
            }
                        %>  
                                            </tr>
                </tbody>
            </table>
         <%@ include file="/ComponenteFooter.jsp" %>
    </div>
    
</body>
</html>