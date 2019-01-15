<%-- 
    Document   : VistaRegistroCliente
    Created on : 12-ene-2019, 12:06:39
    Author     : amunguia
--%>

<%@ include file="/ComponenteHeader.jsp" %>
    <div class="container-fluid">
    <!--<Contenedor centrado>-->
    <div class="container">
        <!--Datos-->

        <div class="row">
            <form action="ControladorInicio" method="post">
                <div  class="col-md-3" style="padding-right:30px;">
                    <input type="hidden" name="llamada" value="registro" class="registro-input">
                    <!-- A partir de aqu? ya van los campos normales del formulario --->
                    <div class="form-group">
                        <label>Usuario</label>
                        <input type="text" name="nombre_usuario" class="form-control" placeholder="Nombre de usuario" required>
                    </div>
                    <div class="form-group">
                        <label>Contrase?a</label>
                        <input name="clave" type="password" class="form-control" placeholder="Contrase?a" required>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" name="email" class="form-control" placeholder="Correo electr?nico" required="">
                    </div>
                    <div class="form-group">
                        <label>Nombre</label>
                        <input name="nombre" type="text" class="form-control" placeholder="Nombre" required>
                    </div>
                    <div class="form-group">
                        <label>Apellidos</label>
                        <input name="apellidos" type="text" class="form-control" placeholder="Apellidos" required>
                    </div>
                    
                    <input class="btn btn-success" type="submit" value="Registrar">


                </div>
                <div class="col-md-3" style="padding-right:30px;">
                    <div class="form-group">
                        <label>Dni</label>
                        <input name="dni" type="text" class="form-control" placeholder="dni" required>
                    </div>
                    <div class="form-group">
                        <label>Direccion</label>
                        <input name="direccion_entrega" type="text" class="form-control" placeholder="Direccion" required>
                    </div>
                    <div class="form-group">
                        <label>Telefono</label>
                        <input name="telefono" type="text" class="form-control" placeholder="telefono" required>
                    </div>
                </div>
             </form>
         </div>
    </div><!--</Contenedor centrado>-->

    <%@ include file="/ComponenteFooter.jsp" %>
</html>
