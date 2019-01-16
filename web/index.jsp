<%-- 
    Document   : index
    Created on : 31-dic-2018, 15:13:29
    Author     : lynch
--%>
<html>
<%@ include file="/ComponenteHeader.jsp" %>
    <body>
    <div class="contenedor">
         <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10">
            <div  class="grid-block" style="background-image: url('https://memoriasdeunviajesinretorno.files.wordpress.com/2014/12/airplane-taking-off-road-sky.jpg?w=1200')">

                    <form class="formulario-inicio" action="ControladorBusquedaVuelos" method="post">                    
                        <div>        
                            <label for="origen" class="control-label">Origen: </label>
                            <select name="origen" required> type="text" id="origen" >
                                <%
                                    GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
                                    Connection conexion = gestionDB.establecerConexion();
                                    CRUDAeropuerto aero = new CRUDAeropuerto(conexion);
                                    ArrayList<Aeropuerto>listaAeropuertos = (ArrayList)aero.obtenerTodos();
                                    out.println("<option value=\"inicio-origen\">Elegir origen</option>");

                                    for(Aeropuerto aerop :listaAeropuertos){
                                        out.println("<option value=\""+aerop.getLugar()+"\">"+aerop.getNombre()+" - "+ aerop.getLugar()+"</option>");
                                        }
                                %>                                                
                            </select>
                        </div>
                        <div>
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
                            <label for="fecha-ida">Fecha ida: </label>
                            <input type="date" name="fecha_ida" required/> 
                        </div>
                        <div>
                            <label for="fecha-vuelta">Fecha vuelta: </label>
                            <input type="date" name="fecha_vuelta" required/> 
                        </div>
                        <div>
                            <label for="num_billetes">N&deg; viajeros: </label>
                            <input type="number" name="num_billetes" min ="1" required/>
                        </div>
                            <div>
                                <button type="submit" class="btn btn-default btn-sm">Buscar vuelas disponibles</button>
                            </div>
                                
                           
                        
                    </form>
                </div>
            </div>
           <div class="col-md-1"></div>             
         </div> <%--Cierre fila busqueda de viaje --%>
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <p class = "text-center">
                <h1>M&aacute;s que vuelos</h1>
                <font size="3"> 
                Encuentra los mejores precios para los lugares m&aacute;s paradisiacos, de ensue&ntilde;o al alcance de tu bolsillo.
                En M&aacute;s que viajes dispones de un amplio cat&aacute;lo de destinos y gran disponibilidad de plazas. Con la seguridad
                de una empresa de confianza. Estamos a tu disposici&oacute;n  para resolver tus dudas, consultarnos destinos de pel&iacute;culas
                que no sab&iacute;s que existen.
		Echa un vistazo a nuestros viajes y llevate a los tuyos a unas vacaciones inolvidales. </br>
                Haz tu reserva y no te arrepentiras de un viaje fant&aacute;stico, adem&aacutes se te aplciar&aacute;n descuentos de forma 
                autom&aacutetica, solo tendr&aacute;s que preocuparte de elegir el mejor destino. </br>	
               </font> 
		</p>
                <marquee direction="LEFT">  <br />      
                    <img height="259" border="0" width="500" alt="" src="https://miviaje.com/wp-content/uploads/2018/07/caribe.jpg" />  
                    <img height="259" border="0" width="500" alt="" src="https://www.pullmantur.es/media/pull/responsive/images/pagina-producto/581x354px/mini-caribe.jpg" />
                    <img height="259" border="0" width="500" alt="" src="https://fundacioncompartir.org/sites/default/files/styles/slick_600x320/public/una-iniciativa-que-fortalece-al-caribe-colombiano.jpg?itok=YpNp2G1Q" /> 
                    <img height="259" border="0" width="500" alt="" src="https://ep01.epimg.net/elpais/imagenes/2017/03/29/icon/1490808308_116494_1490808782_noticia_normal.jpg" />
                    <img height="259" border="0" width="500" alt="" src="https://www.iatiseguros.com/wp-content/uploads/2017/09/itinerario-viaje-a-filipinas-4.jpg" />
                    <img height="259" border="0" width="500" alt="" src="https://static1lonelyplanetes.cdnstatics.com/sites/default/files/styles/max_1300x1300/public/fotos/tailandia_krabi_gettyrf_465966551_copyright_anek_getty_images.jpg?itok=DG0Ry5B5" />
                    <img height="259" border="0" width="500" alt="" src="https://cdn.shopify.com/s/files/1/1381/2063/products/nueva-york-nocturno-w_grande.jpg?v=1488325655" />
                    <img height="259" border="0" width="500" alt="" src="https://mundenea.proyetravel.es/wp-content/uploads/sites/5/2018/09/costa-rica.jpg" />
                    <img height="259" border="0" width="500" alt="" src="https://www.travel-xperience.com/sites/default/files/portada_noruega_accesible.jpg" />
                </marquee> <br />
            </div>
            <div class="col-md-1"></div>
        </div>
        <%@ include file="/ComponenteFooter.jsp" %>
        </div>   <%-- cierre contenedor--%>      
    </body>
</html>
