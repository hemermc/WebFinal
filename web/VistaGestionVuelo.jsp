<html>
<%@ include file="/ComponenteHeader.jsp" %>
<%
            GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
            Connection conexion = gestionDB.establecerConexion();
            CRUDVuelo cRUDVuelo = new CRUDVuelo(conexion);
            CRUDAvion cRUDAvion = new CRUDAvion(conexion);
            ArrayList<Vuelo> listVuelos = cRUDVuelo.obtenerTodos();
            session.setAttribute(Constantes.SESSION_VUELOS, listVuelos);
        %>
    <body>
        <div class ="contenedor">
            <div class ="row">
               <div class="col-md-1"></div>
               <div class="col-md-12">
                    <h1>Vuelos: </h1>
                    <form action="ControladorAdminVuelo" method="post">
                        <div class="form-group">
                            <label>Origen: </label>
                            <select name="origen" required> type="text" id="origen" >
                                <%
                                    
                                    CRUDAeropuerto aero = new CRUDAeropuerto(conexion);
                                    ArrayList<Aeropuerto>listaAeropuertos = (ArrayList)aero.obtenerTodos();
                                    out.println("<option value=\"inicio-origen\">Elegir origen</option>");

                                    for(Aeropuerto aerop :listaAeropuertos){
                                        out.println("<option value=\""+aerop.getLugar()+"\">"+aerop.getNombre()+" - "+ aerop.getLugar()+"</option>");
                                        }
                                %>                                                
                            </select>
                        </div>
                            <div class="form-group">
                            <label for="destino">Destino: </label>
                            <select name="destino" requuired > type="text" id="destino" >
                                <%
                                        out.println("<option value=\"inicio-origen\">Elegir destino</option>");
                                        for(Aeropuerto aerop :listaAeropuertos){
                                            out.println("<option value=\""+aerop.getLugar()+"\">"+aerop.getNombre()+" - "+ aerop.getLugar()+" </option>");   
                                        }
                                %>
                            </select>
                        </div>
                        <div>
                            <label for="fecha-ida">Fecha: </label>
                            <input type="date" name="fecha" required/> 
                        </div>
                        <div class="form-group">
                            <label>Precio: </label>
                            <input type="number" name="precio" class="form-control" min ="0" required/>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12 text-center">
                                <button type="submit" name="action" value="add" class="btn btn-primary btn-lg"> Insertar</button>
                                <button type="submit" name="action" value="Filter" class="btn btn-primary btn-lg"> Filtrar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
           <div>
               <table class="table table-striped">
                   <thead>
                        <tr>
                            <th>id_vuelo</th>
                             <th>Origen</th>
                             <th>Destino</th>
                             <th>Fecha</th>
                             <th>id_avion</th>
                             <th>Precio</th>
                        </tr>
                   </thead>
                   <tbody>
                       <tr>
                            <%
                            ArrayList<Vuelo> listaVuelos = (ArrayList) session.getAttribute(Constantes.SESSION_VUELOS);
                            out.println("<h2>Lista de vuelos</h2>");
                            if (listaVuelos != null) {
                                for (Vuelo r : listaVuelos) {
                                    if (r != null) {
                                        out.println("<form action=\"ControladorAdminAeropuerto\" method=\"post\">");
                                        out.println("<tr><td><label>" + r.getId_vuelo()  +"</label></td>");
                                        out.println("<td><input type =\"text\" name=\"origen\" value=\"" + r.getOrigen() +"\"></td>");
                                        out.println("<td><input type =\"text\" name=\"destino\" value=\"" + r.getDestino() +"\"></td>");
                                        out.println("<td><input type =\"date\" name=\"fecha\" value=\"" + r.getFecha()+"\"></td>");
                                        out.println("<td><input type =\"text\" name=\"id_avion\" value=\"" + r.getId_avion()+"\"></td>");
                                        out.println("<td><input type =\"number\" name=\"id_avion\" value=\"" + r.getPrecio()+"\"></td>");
                                        out.println("<td><button type=\"submit\" name=\"action\" value=\"update\" class=\"btn btn-warning btn-xs\">Update</button><td></tr></form>");
                                
                                    } 
                                }
                            }else {
                                    out.println("<h3>No hay ningun vuelo registrado</h3>");
                            }
                            %>
                            </tr>
                   </tbody>
               </table>
            </div>
               <%@ include file="/ComponenteFooter.jsp" %>
        </div>   <%-- cierre contenedor--%>      
    </body>
</html>

