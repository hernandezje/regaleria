/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venta;

import articulo.Articulo;
import articulo.GestorArticulo;
import cliente.Cliente;
import cliente.Cuenta;
import cliente.GestorCuenta;
import cliente.GestorCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import regaleria.GestorLogin;

/**
 *
 * @author yohan
 */
public class ControladorVenta implements ActionListener{

    private FrmVenta vista;
    private Venta venta;
    private GestorVenta gestor;
    private Cliente cliente;
    private Articulo articulo;
    private GestorCliente gestorCliente;
    private GestorArticulo gestorArticulo;
    private DetalleVenta detalleVenta;
    private Cuenta cuenta;
    private GestorCuenta gestorCuenta;
    
    
    public ControladorVenta(JFrame padre) {
        vista=new FrmVenta(padre, true, this);
        venta=new Venta();
        articulo=new Articulo();
        gestor=new GestorVenta();
        gestorArticulo= new GestorArticulo();
        gestorCliente= new GestorCliente();
        detalleVenta=new DetalleVenta();
    }
    
    public void ejecutar(){
        mostrarlineas();
        vista.limpiarArticulo();
        vista.limpiarCliente();
        vista.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()){
            case FrmVenta.Venta_buscarCli:
                buscarCliente();
                break;
            case FrmVenta.Venta_buscarArt:
                buscarArticulo();
                break;
            case FrmVenta.Venta_agregar:
                agregar();
                break;
            case FrmVenta.Venta_guardar:
                guardar();
                break;
            case FrmVenta.Venta_salir:
                salir();
                break;
            case FrmVenta.Venta_quitar:
                quitar();
                break;
            case FrmVenta.Venta_agregarCuenta:
                agrgarCuenta();
                break;
            
        }
    
    }

    private void mostrarlineas() {
        vista.mostrarlinea(venta.getLista());
        venta.calcular();
        vista.setTotal(venta.getTotal());
    }

    private void buscarCliente() {
        String dni=vista.getDni();
        cliente=gestorCliente.buscarXDNI(dni);
        if (cliente==null)JOptionPane.showMessageDialog(vista, "cliente no hallado");
        else {
            vista.setDni(dni);
            vista.setNombre(cliente.getNombre());
            vista.setTelefono(cliente.getTelefono());
        }
    }

    private void buscarArticulo() {
        int codigo=vista.getCodigo();
        articulo=gestorArticulo.buscarXCODIGO(codigo);
        if (articulo==null)JOptionPane.showMessageDialog(vista, "Articulo no hallado");
        else {
            vista.setCodigo(codigo);
            vista.setDescripcion(articulo.getDescripcionArticulo());
            vista.setPrecio(articulo.getPrecioVenta());
            vista.setStock(articulo.getStock());
        }
    }

    private void agregar() {
        if(vista.getCantidad()>articulo.getStock())
            JOptionPane.showMessageDialog(vista, "Stock insuficiente");
        else{
        DetalleVenta dv= new DetalleVenta();
        dv.setArticulo(articulo);
        dv.setCantidad(vista.getCantidad());
        venta.agregar(dv);
        mostrarlineas();
        vista.limpiarArticulo();
        }
    }

    private void guardar() {
        if(cliente==null)
            venta.getCliente().setIdCliente(1);
        else
        venta.getCliente().setIdCliente(cliente.getIdCliente());
        venta.getEmpleado().setIdEmpleado(GestorLogin.idEmpleado);
        venta.setFecha(new java.util.Date());
        venta.setHora(new Time(venta.getFecha().getTime()));
        gestor.agregar(venta);
        vista.limpiarArticulo();
        vista.limpiarCliente();
        JOptionPane.showMessageDialog(vista, "Venta Registrada");
        venta.getLista().clear();
        mostrarlineas();
    }

    private void salir() {
        vista.limpiarArticulo();
        vista.limpiarCliente();
        vista.setVisible(false);
    }

    private void quitar() {
         int fila=vista.getFila();
        if(fila==-1)
            JOptionPane.showMessageDialog(vista, "debe seleccionar una linea para quitarla");
        else{
            venta.getLista().remove(fila);
            mostrarlineas();
        }
    
    }

    private void agrgarCuenta() {
        cuenta=new Cuenta();
        cuenta.setCliente(cliente);
       
        
    }

   
}
