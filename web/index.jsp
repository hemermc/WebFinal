<%-- 
    Document   : index
    Created on : 31-dic-2018, 15:13:29
    Author     : lynch
--%>

<%@page import="com.crud.CRUDAeropuerto"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.modelo.GestionBBDDLocalhost"%>
<%@page import="com.modelo.Aeropuerto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Más que vuelos</title>
        <link rel="stylesheet" href="css/estilos.css">
	<link rel="stylesheet" href="css/style.css">
        
    </head>
    <body>
      
            <jsp:include page="ComponenteHeader.jsp"/>
            <div class="contenedor">
                <div  class="grid-block" style="background-image: url('https://memoriasdeunviajesinretorno.files.wordpress.com/2014/12/airplane-taking-off-road-sky.jpg?w=1200')">
                <form class="formulario-inicio" action="ControladorBusquedaVuelos" method="post">
                    
                    <div>
                        
                        <label for="origen">Origen: </label>
                        <!-- hay que cambiarlo a combobox -->
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
                        <!-- hay que cambiarlo a combobox -->
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
                    <div class="boton-inicio">
                        <button type="submit">Buscar vuelos disponibles</button>
                    </div>
                </form>
			</div>
			<p class = "line-height">
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
        <footer>
			
			<p>
			<br>© 2018 - 2019 Más que vuelos, S.L. - Todos los derechos reservados.</br>
                        <span class ="icon-paypal"></span>
                        <span class ="icon-applepay"></span>
			</p>
		</footer>
    </body>
</html>
