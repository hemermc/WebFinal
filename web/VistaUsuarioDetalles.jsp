<%-- 
    Document   : VistaUsuarioDetalles
    Created on : 13-ene-2019, 16:27:53
    Author     : Robert
--%>
<%@ include file="/ComponenteHeader.jsp" %>
    <div class="contenedor">
        
                   <%
                       Cliente cliente =(Cliente)session.getAttribute("usuario");
                    String nombre = cliente.getNombre();
                    String apellidos = cliente.getApellidos();
                    String dni = cliente.getDni();
                    String direccion_entrega = cliente.getDireccion_entrega();
                    int telefono = cliente.getTelefono();
                    String email = cliente.getEmail(); 
                    
                    out.println("<tr>" +
                            "<td> "+nombre                 +"      </td>"+
                            "<td> "+apellidos              +"      </td>"+
                            "<td> "+dni                    +"      </td>"+
                            "<td> "+direccion_entrega      +"      </td>"+
                            "<td> "+telefono               +"      </td>"+
                            "<td> "+email                  +"      </td>"+
                          "</tr>");
                    %>


        
                <% ArrayList<Compra> comprasUsuario = new ArrayList<>();
                       comprasUsuario = (ArrayList)session.getAttribute("listaCompras");
                        String noValue3 = " No Data";
                       if(comprasUsuario == null || comprasUsuario.size() == 0){
                        out.println("<tr>" +
                                "<td> "+noValue3         +"      </td>"+
                                "<td> "+noValue3               +"    </td>"+
                                "<td> "+noValue3           +"    </td>"+
                                "<td> "+noValue3          +"      </td>"+
                              "</tr>");
                              out.println("<h1>El usuario no tiene compras!</h1>");
                              
                       }else{
                        
                       for (Compra e : comprasUsuario) 
                       {
                         out.println("<tr>" +
                                      "<td> "+e.getId_compra()         +"      </td>"+
                                      "<td> "+e.getDni()               +"    </td>"+
                                      "<td> "+e.getAsiento()           +"    </td>"+
                                      "<td> "+e.getId_vuelo()          +"      </td>"+
                                    "</tr>");
                       }
                    }
                       
                        %>

      
                <% ArrayList<Vuelo> listaVuelos = new ArrayList<>();
                           listaVuelos = (ArrayList)session.getAttribute("listaVuelos");
                           String noValue2 = " No Data";
                           if(listaVuelos == null || listaVuelos.size() == 0){
                            out.println("<tr>" +
                                    "<td> "+noValue2         +"      </td>"+
                                    "<td> "+noValue2               +"    </td>"+
                                    "<td> "+noValue2           +"    </td>"+
                                    "<td> "+noValue2          +"      </td>"+
                                  "</tr>");
                                  out.println("<h1>El usuario no ha volado aun!</h1>");
                                  
                           }else{
                           
                           for (Vuelo e : listaVuelos) 
                           {
                             out.println("<tr>" +
                                          "<td> "+e.getId_vuelo()             +"      </td>"+
                                          "<td> "+e.getOrigen()               +"      </td>"+
                                          "<td> "+e.getDestino()              +"      </td>"+
                                          "<td> "+e.getFecha()                +"      </td>"+
                                          "<td> "+e.getId_avion()             +"      </td>"+
                                          "<td> "+e.getPrecio()               +"      </td>"+
                                        "</tr>");
                           }
                        }
                           
                            %>

         
    </div>
    <%@ include file="/ComponenteFooter.jsp" %>
</html>