/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedido;

import articulo.Articulo;
import articulo.GestorArticulo;
import venta.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import proveedor.GestorProveedor;
import proveedor.Proveedor;
import regaleria.GestorLogin;

/**
 *
 * @author yohan
 */
public class ControladorPedido implements ActionListener{

    private FrmPedido vista;
    private Pedido pedido;
    private Proveedor proveedor;
    private Articulo articulo;
    private GestorPedido gestor;
    private GestorProveedor gestorProveedor;
    private GestorArticulo gestorArticulo;

    public ControladorPedido(JFrame padre) {
        vista=new FrmPedido(padre, true, this);
        pedido=new Pedido();
        proveedor= new Proveedor();
        articulo= new Articulo();
        gestor=new GestorPedido();
        gestorProveedor= new GestorProveedor();
        gestorArticulo= new GestorArticulo();
    }
    
    public void ejecutar(){
        mostrarlineas();
        vista.limpiarArticulo();
        vista.limpiarProveedor();
        vista.setEmpleado(GestorLogin.nombre);
        vista.setLegEmpleado(GestorLogin.leg);
        vista.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()){
            case FrmPedido.Pedido_buscarProv:
                buscarProveedor();
                break;
            case FrmPedido.Pedido_buscarArt:
                buscarArticulo();
                break;
            case FrmPedido.Pedido_AgregarArt:
                agregarArticulo();
                break;
            case FrmPedido.Pedido_guardar:
                guardarPedido();
                break;
            case FrmPedido.Pedido_salir:
                salir();
                break;
            case FrmPedido.Pedido_CAncelar:
                cancelar();
                break;
            
        }
    
    }

    private void mostrarlineas() {
       vista.mostrarlinea(pedido.getLista());
       pedido.calcular();
       vista.setTotal(pedido.getTotal());
    }

    private void buscarProveedor() {
        int cuit=vista.getCuit();
        proveedor=gestorProveedor.buscarXCUIT(cuit);
        if (proveedor==null)JOptionPane.showMessageDialog(vista, "proveedor no hallado");
        else {
            vista.setCuit(cuit);
            vista.setRazonSocial(proveedor.getRazonSocial());
            vista.setTel(proveedor.getTel());
            vista.setCelu(proveedor.getCelular());
            vista.setCorreo(proveedor.getCorreo());
        }
    }
    
     private void buscarArticulo() {
        int codigo=vista.getCodigoArt();
        articulo=gestorArticulo.buscarXCODIGO(codigo);
        if (articulo==null)JOptionPane.showMessageDialog(vista, "Articulo no hallado");
        else {
            vista.setCodigoArt(codigo);
            vista.setDescripcion(articulo.getDescripcionArticulo());
            vista.setPrecioCompra(articulo.getPrecioCompra());
            vista.setStock(articulo.getStock());
        }
    }

    private void agregarArticulo() {
       
        DetallePedido dp= new DetallePedido();
        dp.setArticulo(articulo);
        dp.setCantidad(vista.getCantidadArt());
        pedido.agregar(dp);
        mostrarlineas();
        vista.limpiarArticulo();
        
    }

    private void guardarPedido() {
    
        if(proveedor==null)
             JOptionPane.showMessageDialog(vista, "Agregar Proveedor");
        else
            pedido.getProveedor().setIdProveedor(proveedor.getIdProveedor());
            pedido.getEmpleado().setIdEmpleado(GestorLogin.idEmpleado);
            pedido.setFecha(new java.util.Date());
            pedido.setHora(new Time(pedido.getFecha().getTime()));
            pedido.setEstado(vista.getEstado());
            gestor.agregar(pedido);
            vista.limpiarProveedor();
            vista.limpiarArticulo();
            JOptionPane.showMessageDialog(vista, "Pedido Registrado");
            pedido.getLista().clear();
            mostrarlineas();
            
    }
    
     private void salir() {
        vista.limpiarArticulo();
        vista.limpiarProveedor();
        vista.setVisible(false);
    }

    private void cancelar() {
        vista.limpiarArticulo();
        vista.limpiarProveedor();
        pedido.getLista().clear();
        mostrarlineas();
    
    }
}
