/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controlador;

import com.crud.CRUDAdministrador;
import com.crud.CRUDAeropuerto;
import com.crud.CRUDCliente;
import com.crud.CRUDCompra;
import com.crud.CRUDVuelo;
import com.modelo.Administrador;
import com.modelo.Aeropuerto;
import com.modelo.Cliente;
import com.modelo.Compra;
import com.modelo.GestionBBDDLocalhost;
import com.modelo.Usuario;
import com.modelo.Vuelo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Robert
 */
@WebServlet(name = "ControladorUsuario", urlPatterns = {"/ControladorUsuario"})
public class ControladorPago extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
        Connection conexion = gestionDB.establecerConexion();

        HttpSession session = request.getSession();
        this.insertarCompra(request, response);
    }

    /*
    Funcion que se debe ejecutar cuando se pulsa el boton de confirmar
     */
    protected void insertarCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        GestionBBDDLocalhost gestionDB = GestionBBDDLocalhost.getInstance();
        Connection conexion = gestionDB.establecerConexion();
        HttpSession session = request.getSession();

        String id_vueloIda = (String) session.getAttribute("vueloElegidoIda");
        String id_vueloVuelta = (String) session.getAttribute("vueloElegidoVuelta");

       
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("./VistaInicioSesion.jsp");
        } else {

            String nombreUsuario = usuario.getNombre_usuario();
            CRUDCliente crudCliente = new CRUDCliente(conexion);
            if (crudCliente.inicioSesionValido(usuario)) {
                Cliente cliente = crudCliente.obtenerEspecifico(nombreUsuario);
                session.setAttribute("datosCliente", cliente);        // se establecen en la sesion los datos del cliente

                //CALCULAR PRECIO
                CRUDVuelo vuelo = new CRUDVuelo(conexion);
                CRUDAeropuerto aeropuerto = new CRUDAeropuerto(conexion);

                Vuelo vueloIda = vuelo.obtenerEspecifico(id_vueloIda);

                Aeropuerto AeropuertoIdaOrigen = aeropuerto.obtenerEspecifico(vueloIda.getOrigen());
                Aeropuerto AeropuertoIdaDestino = aeropuerto.obtenerEspecifico(vueloIda.getDestino());

                float tasaAeropuertoIdaOrigen = AeropuertoIdaOrigen.getTasa();
                float tasaAeropuertoIdaDestino = AeropuertoIdaDestino.getTasa();
                float precioIda = vueloIda.getPrecio() + tasaAeropuertoIdaOrigen + tasaAeropuertoIdaDestino;
                
                float ivaIda = (21 *100)/precioIda;
                
                precioIda = precioIda +ivaIda;
                

                Vuelo vueloVuelta = vuelo.obtenerEspecifico(id_vueloVuelta);

                Aeropuerto AeropuertoVueltaOrigen = aeropuerto.obtenerEspecifico(vueloVuelta.getOrigen());
                Aeropuerto AeropuertoVueltaDestino = aeropuerto.obtenerEspecifico(vueloVuelta.getDestino());

                float tasaAeropuertoVueltaOrigen = AeropuertoVueltaOrigen.getTasa();
                float tasaAeropuertoVueltaDestino = AeropuertoVueltaDestino.getTasa();
                float precioVuelta = vueloVuelta.getPrecio() + tasaAeropuertoVueltaOrigen + tasaAeropuertoVueltaDestino;
                
                float ivaVuelta = (21 *100)/precioVuelta;
                
                precioVuelta = precioIda +ivaVuelta;

                //->COMPROBAR SI SE APLICA REBAJA 
                CRUDCompra compra = new CRUDCompra(conexion);
                ArrayList<Compra> comprasUsuario = new ArrayList<>();
                comprasUsuario = compra.obtenerComprasUsuario(cliente.getDni());
                if (comprasUsuario.size() % 3 == 0) {
                    precioIda = precioIda / 2;
                } else {
                    if ((comprasUsuario.size() + 1) % 3 == 0) {
                        precioVuelta = precioVuelta / 2;
                    }
                }
                
                float precioTotal = precioIda + precioVuelta;
                
                float iva = (21 *100)/precioTotal;
                
                precioTotal = precioTotal +iva;
                
                session.setAttribute("precioTotal", precioTotal);

                String dni = cliente.getDni();

                int asientoIda = (int) (Math.random() * 75) + 1;
                int asientoVuelta = (int) (Math.random() * 75) + 1;

                int numeroCompraIda = (int) (Math.random() * 1000) + 1;
                int numeroCompraVuelta = (int) (Math.random() * 1000) + 1;

                //String numeroCompra = java.util.UUID.randomUUID().toString(); // GENERAR IDENTIFICADOR UNICO DE COMPRA
                Compra compraUsuarioIda = new Compra(dni, asientoIda,id_vueloIda,precioIda);
                Compra compraUsuarioVuelta = new Compra(dni, asientoVuelta,id_vueloVuelta,precioVuelta);

                compra.insertar(compraUsuarioIda);
                compra.insertar(compraUsuarioVuelta);

                session.setAttribute("compraRealizadaIda", compraUsuarioIda);
                session.setAttribute("compraRealizadaVuelta", compraUsuarioVuelta);  // se establecen los datos de la compra realizada

                response.sendRedirect("./VistaUsuarioDetalles.jsp");  //Redireccion a la vista donde se muestran los valores de sesion compraRealizada y datosCliente
                //COMPROBAR NOMBRE DE LA VISTA, NO SE COMO SE LA VAS A LLAMAR Y HE PUESTO ESO DE EJEMPlo

            }

        }

    }
}
