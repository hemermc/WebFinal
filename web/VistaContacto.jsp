<%-- 
    Document   : VistaContacto
    Created on : 12-ene-2019, 12:18:25
    Author     : amunguia
--%>


<%@ include file="/ComponenteHeader.jsp" %>

<div class="contenedor">
    <div  class="grid-block" style="background-image: url('https://www.nationalgeographic.com.es/medio/2018/03/14/playa-bonita-republica-dominicana_01c0e228_1280x720.jpg')">
    <div class="row">
        <div class="col-md-12">
           <!-- <div class="well well-sm"> !-->            
                <form class="formulario-contacto" action="ControladorContacto" method="post">
                    <fieldset>
                        <legend class="text-center header">Contactanos</legend>

                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-user bigicon"></i></span>
                            <div class="col-md-8">
                                <input id="fname" name="nombre" type="text" placeholder="Nombre" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-envelope-o bigicon"></i></span>
                            <div class="col-md-8">
                                <input id="email" name="correo" type="text" placeholder="Email" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-phone-square bigicon"></i></span>
                            <div class="col-md-8">
                                <input id="phone" name="telefono" type="text" placeholder="Telefono" class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-pencil-square-o bigicon"></i></span>
                            <div class="col-md-8">
                                <textarea class="form-control" id="message" name="mensaje" placeholder="Introduzca su mensaje. Nos pondremos en contacto con la mayor brevedad posible" rows="7"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-12 text-center">
                                <button type="submit" class="btn btn-primary btn-lg">Enviar</button>
                            </div>
                        </div>
                    </fieldset>
                </form>
           <!-- </div>!-->
        </div>
    </div>
</div>
 
    <%@ include file="/ComponenteFooter.jsp" %>
</html>
