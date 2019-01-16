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
                <% ArrayList<Compra> comprasUsuario = new ArrayList<>();
                       comprasUsuario = (ArrayList)session.getAttribute("listaCompras");
                        String noValue3 = " No Data";
                       if(comprasUsuario == null || comprasUsuario.size() == 0){
                              out.println("<h3>El usuario no tiene compras!</h3>");   
                              
                       }else{                        
                            for (Compra e : comprasUsuario){
                                if(e!= null){
                            
                              out.println("<div><table class=\"table table-bordered\"><tr><th> Id Compra   : </th><td>"+e.getId_compra()         +" </td></tr>"+
                                "<tr><th> D.N.I: </th><td>"+e.getDni()            +" </td></tr>"+     
                                "<tr><th> Asiento: </th><td>"+e.getAsiento()      +" </td></tr>"+     
                                "<tr><th> ID vuelo: </th><td>"+e.getId_vuelo()    +" </td></tr>"+           
                            
                                "</table></div>");
                            }
                            }
                            
                    }
                       
                        %>       
    </div>
    <%@ include file="/ComponenteFooter.jsp" %>
</body>
</html>