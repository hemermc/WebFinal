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
            <div  id="myCarousel" class="grid-block" style="background-image: url('https://memoriasdeunviajesinretorno.files.wordpress.com/2014/12/airplane-taking-off-road-sky.jpg?w=1200')">

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
                            <label for="num_billetes">N? viajeros: </label>
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
                <h3 class="display-3">Más que vuelos </br></br>
		<small class="text-muted">Encuentra los mejores precios y los destinos m&aacutes paradisiacos a los mejores precios, 
		tanto viajes nacionales como internacionales, presume de unas vacaciones de lujo al mejor precio.</br> 
		Echa un vistazo a nuestros viajes y llevate a los tuyos a pasar unos dias de ensue&ntildeos. </br>
		Haz tu reserva y no te arrepentiras de un viaje fantastico, adem&aacutes accederas a nuestras ofertas de forma 
                autom&aacutetica.</small></h3> </br>			
		</p>
                <div width="400" height="259" id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div width="400" height="259" class="carousel-inner">
      <div width="400" height="259" class="item active">
          <img width="400" height="300" src="https://miviaje.com/wp-content/uploads/2018/07/caribe.jpg" alt="Los Angeles" style="width:100%;">
      </div>

      <div width="400" height="259" class="item">
        <img width="400" height="259" src="https://www.pullmantur.es/media/pull/responsive/images/pagina-producto/581x354px/mini-caribe.jpg" alt="Chicago" style="width:100%;">
      </div>
    
      <div width="400" height="259" class="item">
        <img width="400" height="259" src="https://fundacioncompartir.org/sites/default/files/styles/slick_600x320/public/una-iniciativa-que-fortalece-al-caribe-colombiano.jpg?itok=YpNp2G1Q" alt="New york" style="width:100%;">
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
  </div>
</div>

                <marquee direction="LEFT">  <br />      
            <img height="259" border="0" width="500" alt="" src="https://miviaje.com/wp-content/uploads/2018/07/caribe.jpg" />  
            <img height="259" border="0" width="500" alt="" src="https://www.pullmantur.es/media/pull/responsive/images/pagina-producto/581x354px/mini-caribe.jpg" />
	    <img height="259" border="0" width="500" alt="" src="https://fundacioncompartir.org/sites/default/files/styles/slick_600x320/public/una-iniciativa-que-fortalece-al-caribe-colombiano.jpg?itok=YpNp2G1Q" /> 
            </marquee> <br />
            </div>
            <div class="col-md-1"></div>
        </div>
        <%@ include file="/ComponenteFooter.jsp" %>
    </div>         
    
    </body>
</html>
