<%-- 
    Document   : index
    Created on : 31-dic-2018, 15:13:29
    Author     : lynch
--%>

<%@ include file="/ComponenteHeader.jsp" %>
    <div class="contenedor">
         <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10">
            <div  class="grid-block" style="background-image: url('https://memoriasdeunviajesinretorno.files.wordpress.com/2014/12/airplane-taking-off-road-sky.jpg?w=1200')">

                    <form class="formulario-inicio" action="ControladorBusquedaVuelos" method="post">                    
                        <div>        
                            <label for="origen" class="control-label">Origen: </label>
                            <select name="origen" > type="text" id="origen" >
                                <%
                                    GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
                                    Connection conexion = gestionDB.establecerConexion();
                                    CRUDAeropuerto aero = new CRUDAeropuerto(conexion);
                                    ArrayList<Aeropuerto>listaAeropuertos = (ArrayList)aero.obtenerTodos();
                                    out.println("<option value=\"inicio-origen\">Elegir origen</option>");

                                    for(Aeropuerto aerop :listaAeropuertos){
                                        out.println("<option value=\""+aerop.getNombre()+"\">"+aerop.getNombre()+" </option>");
                                        }
                                %>                                                
                            </select>
                        </div>
                        <div>
                            <label for="destino">Destino: </label>
                            <select name="destino" > type="text" id="destino" >
                                <%
                                        out.println("<option value=\"inicio-origen\">Elegir destino</option>");
                                        for(Aeropuerto aerop :listaAeropuertos){
                                            out.println("<option value=\""+aerop.getNombre()+"\">"+aerop.getNombre()+" </option>");   
                                        }
                                %>
                            </select>
                        </div>
                        <div>
                            <label for="fecha-ida">Fecha ida: </label>
                            <input type="date" name="fecha_ida"/> 
                        </div>
                        <div>
                            <label for="fecha-vuelta">Fecha vuelta: </label>
                            <input type="date" name="fecha_vuelta"/> 
                        </div>
                        <div>
                            <label for="num_billetes">Nº viajeros: </label>
                            <input type="number" id="num_billetes" min ="1"/>
                        </div>
                            <div>
                                <button type="submit" class="btn btn-default btn-sm">Buscar vuelas disponibles</button>
                            </div>
                                
                           
                        
                    </form>
                </div>
            </div>
           <div class="col-md-1"></div>             
        </div>
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <p class = "text-center">
		<h1>Más que vuelos</h1>
		Encuentra los mejores precios y los destinos m&aacutes paradisiacos a los mejores precios, 
		tanto viajes nacionales como internacionales, presume de unas vacaciones de lujo al mejor precio.</br> 
		Echa un vistazo a nuestros viajes y llevate a los tuyos a pasar unos dias de ensue&ntildeos. </br>
		Haz tu reserva y no te arrepentiras de un viaje fantastico, adem&aacutes accederas a nuestras ofertas de forma 
		autom&aacutetica. </br>			
		</p>
                <marquee direction="LEFT">  <br />      
            <img height="259" border="0" width="500" alt="" src="https://miviaje.com/wp-content/uploads/2018/07/caribe.jpg" />  
            <img height="259" border="0" width="500" alt="" src="https://www.pullmantur.es/media/pull/responsive/images/pagina-producto/581x354px/mini-caribe.jpg" />
	    <img height="259" border="0" width="500" alt="" src="https://fundacioncompartir.org/sites/default/files/styles/slick_600x320/public/una-iniciativa-que-fortalece-al-caribe-colombiano.jpg?itok=YpNp2G1Q" /> 
            </marquee> <br />
            </div>
            <div class="col-md-1"></div>
        </div>
    </div>         
    <%@ include file="/ComponenteFooter.jsp" %>
</html>
