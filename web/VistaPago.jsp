<%-- 
    Document   : VistaPago
    Created on : 15-ene-2019, 17:41:20
    Author     : lynch
--%>

<%@ include file="/ComponenteHeader.jsp" %>
   
        <div class="row">
            <div class="col-md-3" style="padding-right:30px;">
                               
            </div>

            <div class="col-md-3" style="padding-right:30px;">
                <script type="text/javascript">
                    function validate() {

                        if (isNaN(document.getElementById('titular').value)) {
                            return true;
                        } else {
                            alert("Nombre del titular debe de ser letras");
                            return false;
                        }
                    }
                </script>

                <form name="myform" method="post" action="/DWfinal/Ventas?accion=confirmar" onsubmit="return validate();">
                    <div class="form-group">
                        <label>Titular Tarjeta Crédito</label>
                        <input type="text" class="form-control" placeholder="NOMBRE APELLIDOS" id="titular" name="titular" required>
                    </div>

                    <div class="form-group">
                        <label>Número Tarjeta Crédito</label>
                        <input type="number" class="form-control" placeholder="1000000000000000" min="1000000000000000" id="numTarjeta" name="numTarjeta" max="9999999999999999" maxlength="16" minlength="16" required>
                    </div>
                    <div class="form-group">
                        <label>Mes</label>
                        <input type="number" class="form-control" placeholder="00" id="mes" name="mes" min=01 max=12 maxlength="2" minlength=2 required>
                    </div>
                    <div class="form-group">
                        <label>Año</label>
                        <input type="number" class="form-control" placeholder="00" id="ano" name="ano" min=17 max=30 maxlength="2" minlength=2 required>
                    </div>
                    <div class="form-group">
                        <label>CCV</label>
                        <input type="password" class="form-control" placeholder="***" id="ccv" min="100" max="999" name="ccv" maxlength="3" minlength=3 required>
                    </div>

                    <input type="hidden" name="id_pelicula" value="<%=request.getAttribute("id_pelicula")%>">
                    <input type="hidden" name="id_sala" value="<%=request.getAttribute("id_sala")%>">
                    <input type="hidden" name="fecha" value="<%=request.getAttribute("fecha")%>">
                    <input type="hidden" name="hora" value="<%=request.getAttribute("hora")%>">
                    <input type="hidden" id="entradasSeleccionadas" name="entradasSeleccionadas" value="<%=request.getAttribute("entradasSeleccionadas")%>" >
                    <input class="btn btn-success" type="submit" value="Confirmar">
                    <!--<a href="pagado.html" class="btn btn-success">Confirmar</a>-->
                </form>
            </div>
        </div>
     <%@ include file="/ComponenteFooter.jsp" %>
</html>
