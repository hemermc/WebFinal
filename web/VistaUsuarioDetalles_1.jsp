<%-- 
    Document   : VistaUsuarioDetalles
    Created on : 13-ene-2019, 16:27:53
    Author     : Robert
--%>

<%@page import="com.modelo.Compra"%>
<%@page import="com.modelo.Vuelo"%>
<%@page import="com.modelo.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        table,
        th,
        td {
            border: 1px solid black;
            border-collapse: collapse;
        }

        th,
        td {
            padding: 15px;
            text-align: left;
        }

        table#t01 {
            width: 100%;
            background-color: #f1f1c1;
        }

        table#t02 {
            width: 100%;
            background-color: #f1f1c1;
        }

        table#t03 {
            width: 100%;
            background-color: #f1f1c1;
        }
    </style>
    <title>Perfil usuario</title>
</head>

<body>
    <jsp:include page="ComponenteHeader.jsp" />
    <div class="contenedor">
        <table id="t01">
            <tr>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Dni</th>
                <th>Direccion de entrega</th>
                <th>Telefono</th>
                <th>Email</th>
            </tr>
            <tr>
                <%Cliente cliente =(Cliente)session.getAttribute("usuario");
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

            </tr>

        </table>

        <table id="t02">
            <tr>
                <th>Id Compra</th>
                <th>Dni</th>
                <th>Asiento</th>
                <th>Id del vuelo</th>
            </tr>
            <tr>
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

            </tr>

        </table>

        <table id="t03">
            <tr>
                <th>Id Vuelo</th>
                <th>Origen</th>
                <th>Destino</th>
                <th>Fecha</th>
                <th>Id Avion</th>
                <th>Precio</th>
            </tr>
            <tr>
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

            </tr>

        </table>

        <footer>

            <p>
                <br>© 2018 - 2019 Más que vuelos, S.L. - Todos los derechos reservados.</br>
                <span class="icon-paypal"></span>
                <span class="icon-applepay"></span>
            </p>
        </footer>

    </div>

</body>
</html>