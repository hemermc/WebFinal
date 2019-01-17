<%-- 
    Document   : index
    Created on : 31-dic-2018, 15:13:29
    Author     : lynch
--%>
<html>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <%@ include file="/ComponenteHeader.jsp" %>  
    <body>


    <div class="contenedor">
         <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10">

                <div>
                    <div id="myCarousel" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                          <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                          <li data-target="#myCarousel" data-slide-to="1"></li>
                          <li data-target="#myCarousel" data-slide-to="2"></li>
                        </ol>


                        <!-- Wrapper for slides -->
                        <div class="carousel-inner">
                          <div class="item active">
                            <img src="https://miviaje.com/wp-content/uploads/2018/07/caribe.jpg" alt="Los Angeles" style="width:1000px; height: 300px;">
                          </div>

                          <div class="item">
                            <img src="https://www.pullmantur.es/media/pull/responsive/images/pagina-producto/581x354px/mini-caribe.jpg" alt="Chicago" style="width:1000px; height: 300px;">
                          </div>

                          <div class="item">
                            <img src="https://fundacioncompartir.org/sites/default/files/styles/slick_600x320/public/una-iniciativa-que-fortalece-al-caribe-colombiano.jpg?itok=YpNp2G1Q" alt="New york" style="width:1000px; height: 300px;">
                          </div>
                        </div>

                        <!-- Left and right controls -->
                        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                          <span class="glyphicon glyphicon-chevron-left"></span>
                          <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#myCarousel" data-slide="next">
                          <span class="glyphicon glyphicon-chevron-right"></span>
                          <span class="sr-only">Next</span>
                        </a>
                      </div><%--se cierra el carrusel--%>
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
                                        out.println("<option value=\""+aerop.getLugar()+"\">"+aerop.getNombre()+" - "+ aerop.getLugar()+"</option>");
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
                                            out.println("<option value=\""+aerop.getLugar()+"\">"+aerop.getNombre()+" - "+ aerop.getLugar()+" </option>");   
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
                            <label for="num_billetes">N&deg; viajeros: </label>
                            <input type="number" name="num_billetes" min ="1"/>
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
               
                <p class = "text-monospace text-center">
                <h3 class="display-3">M&aacute;s que vuelos </br></br>
                    <small class="text-info">Encuentra los mejores precios para los lugares m&aacute;s paradisiacos, tendr&aacute;s unos lugares
                        de ensue&ntilde;o al alcanse de t&uacute; bolsillo con la seguridad de una empresa de confianza. En M&aacutes que viajes
                        disponemos de un amplio cat&aacute;logo de viajes y con gran capasidad de plazas. </br>
                        Echa un vistazo a nuestra p&aacute;gina y llevate a los tuyos a unas vacaciones magn&iacute;ficas, para cualquier duda 
                        puedes ponerte en contacto con nosotros, estamos siempre a t&uacute; disposici&oacute;n
                        Haz tu reserva y no te arrepentiras de un viaje fantastico, adem&aacutes accederas a nuestras ofertas de forma 
                        autom&aacutetica.</small></h3> </br>			
		</p>
                
                    </div>


                    <marquee direction="LEFT">  <br />      
                        <img height="259" border="0" width="500" alt="" src="https://miviaje.com/wp-content/uploads/2018/07/caribe.jpg" />  
                        <img height="259" border="0" width="500" alt="" src="https://www.pullmantur.es/media/pull/responsive/images/pagina-producto/581x354px/mini-caribe.jpg" />
                        <img height="259" border="0" width="500" alt="" src="https://fundacioncompartir.org/sites/default/files/styles/slick_600x320/public/una-iniciativa-que-fortalece-al-caribe-colombiano.jpg?itok=YpNp2G1Q" /> 
                        <img height="259" border="0" width="500" alt="" src="https://static9lonelyplanetes.cdnstatics.com/sites/default/files/styles/max_1300x1300/public/fotos/Filipinas_Palawan_IslaCoron_500px_74647277_Kevin%20Boutwell_500px.jpg?itok=6U_u6wFD" />  
                        <img height="259" border="0" width="500" alt="" src="https://mejorepocapara.net/wp-content/uploads/2018/09/cual-es-la-mejor-epoca-para-viajar-a-filipinas.jpg" /> 
                        <img height="259" border="0" width="500" alt="" src="https://i.blogs.es/488204/istock-484915982/450_1000.jpg" /> 
                        <img height="259" border="0" width="500" alt="" src="https://www.ef.com.es/sitecore/__~/media/efcom/2017/ils/destinations/China/Country-Stage/CP_CN_desktop.jpg" /> 
                        <img height="259" border="0" width="500" alt="" src="https://www.eldiario.es/viajes/Peru-Machu-Picchu_EDIIMA20180910_0180_4.jpg" /> 
                        <img height="259" border="0" width="500" alt="" src="https://img.elcomercio.pe/files/ec_article_multimedia_gallery/uploads/2018/09/13/5b9aa200b8f03.jpeg" /> 
                        <img height="259" border="0" width="500" alt="" src="https://www.viajarsolo.com/thumbnails/gallery_image_full/components/kcfinder/kcfinder-3.12/upload/images/0B2HVR1Fb-AMqbHNqNGdFQklLSEU.jpg?itok=xsos5xMC" /> 
                    </marquee> <br />
                    
                <%@ include file="/ComponenteFooter.jsp" %>
                </div>
            <div class="col-md-1"></div>       
        </div>         
    </body>
</html>
