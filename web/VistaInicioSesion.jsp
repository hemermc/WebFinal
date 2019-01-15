<%-- 
    Document   : VistaInicioSesion
    Created on : 12-ene-2019, 12:07:00
    Author     : amunguia
--%>

<%@ include file="/ComponenteHeader.jsp" %>
    <div class="contenedor">
    	<div class="row">
            <div class="col-md-6 col-md-offset-3">
		<div class="panel panel-login">
		    <div class="panel-body">
			<div class="row">
                            <div class="col-lg-12">
                                <form id="login-form" action="ControladorInicio" method="post" role="form" style="display: block;">
                                    <h1>Inicio de Sesión</h1>
                                    <div class="form-group">
                                        <input type="hidden" name="llamada" value="acceso">
                                    </div>
                                    <div class="form-group">
                                        <input type="text" name="nombre_usuario"  placeholder="Nombre de usuario">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" name="clave" placeholder="Contraseña">
                                    </div>
                                    <div class="form-group text-center">
                                        <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
					<label for="remember"> Recordarme</label>
                                    </div>
                                    <div class="form-group">
					<div class="row">
                                            <div class="col-sm-6 col-sm-offset-3">
                                                <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Iniciar sesión">
                                            </div>
					</div>
                                    </div>
				</form>
				<form id="register-form" action="http://phpoll.com/register/process" method="post" role="form" style="display: none;">
                                    <div class="form-group">
                                        <input type="text" name="nombre_usuario" id="username" tabindex="1" class="form-control" placeholder="Usuario" value="">
                                    </div>
                                    <div class="form-group">
                                        <input type="email" name="correo" id="email" tabindex="1" class="form-control" placeholder="Correo electronico" value="">
                                    </div>
				    <div class="form-group">
                                        <input type="password" name="clave" id="password" tabindex="2" class="form-control" placeholder="Contraseña">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" name="confirm-password" id="confirm-password" tabindex="2" class="form-control" placeholder="Confirmar contraseña">
                                    </div>
                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-sm-6 col-sm-offset-3">
                                                <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Crear cuenta">
                                            </div>
					</div>
                                    </div>
				</form>
                            </div>
			</div>
                    </div>
		</div>
            </div>
	</div>
    </div>


