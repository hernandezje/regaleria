/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import proveedor.*;
import direccion.GestorLocalidad;
import direccion.GestorProvincia;
import direccion.Direccion;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yohan
 */
public class ControladorCliente implements ActionListener, MouseListener, ItemListener{
    
    private FrmCliente vistaCliente;
    private GestorCliente gestorCliente;
    private Cliente cliente;
    private GestorProvincia gestorProvincia;
    private GestorLocalidad gestorLocalidad;
    private Direccion direccion;
    
    public ControladorCliente(Frame padre){
        
        vistaCliente= new FrmCliente(padre, true);
        gestorCliente=new GestorCliente();
        gestorProvincia=new GestorProvincia();
        gestorLocalidad=new GestorLocalidad();
        cliente=new Cliente();
        direccion=new Direccion();
        
        vistaCliente.pasarControlador(this);
        
    }
    
    public void ejecutar(){
        vistaCliente.limpiar();
        vistaCliente.ponerProvincia(gestorProvincia.getLista());
        mostrar();
        vistaCliente.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch(ae.getActionCommand()){
            
            case FrmCliente.BTN_ADD:
                agregar();
                break;
            case FrmCliente.BTN_CANCEL:
                cancelar();
                break;
            case FrmCliente.BTN_DEL:
                eliminar();
                break;
            case FrmCliente.BTN_EDIT:
                modificar();
                break;
            case FrmCliente.BTN_EXIT:
                salir();
                break;
            
                
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    
    }

    @Override
    public void mousePressed(MouseEvent me) {
    
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        JTable tabla=(JTable)me.getSource();
        cliente.setIdCliente(Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString()));
        gestorCliente.recuperar(cliente);
        vistaCliente.setCalle(cliente.getDireccion().getCalle());
        vistaCliente.setNumero(cliente.getDireccion().getNumero());
        vistaCliente.setProvincia(cliente.getDireccion().getLocalidad().getProvincia().getNombreProv());
        vistaCliente.setLocalidad(cliente.getDireccion().getLocalidad().getNombreLoc());
        vistaCliente.setNombre(cliente.getNombre());
        vistaCliente.setDni(cliente.getDni());
        vistaCliente.setTelefono(cliente.getTelefono());
        vistaCliente.setFechaAlta(cliente.getFecha_alta());
        
        vistaCliente.editar(true);
    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {
    
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        JComboBox combo=(JComboBox)ie.getSource();
        if(combo.getSelectedIndex()>-1){
            vistaCliente.poneLocalidad(gestorLocalidad.getLista(vistaCliente.getProvincia()));
        }
    }

    private void mostrar() {
        ArrayList<Cliente>lista=gestorCliente.getLista();
        DefaultTableModel matriz=new DefaultTableModel();
        matriz.addColumn("idCliente");
        matriz.addColumn("Dni");
        matriz.addColumn("Nombre");
        matriz.addColumn("Telefono");
        matriz.addColumn("Fecha de Alta");
        matriz.addColumn("Direccion");
        
        for(Cliente c:lista){
            matriz.addRow(new Object[]{
                c.getIdCliente(),
                c.getDni(),
                c.getNombre(),
                c.getTelefono(),
                c.getFecha_alta(),
                c.getDireccion().getCalle()+" "+c.getDireccion().getNumero()+", "+c.getDireccion().getLocalidad().getNombreLoc()
                        +", "+c.getDireccion().getLocalidad().getProvincia().getNombreProv()
            });
        }
        vistaCliente.setTabla(matriz);
        
    
    }

    private void agregar() {
        cliente.getDireccion().setCalle(vistaCliente.getCalle());
        cliente.getDireccion().setNumero(vistaCliente.getNumero());
        cliente.getDireccion().setLocalidad(gestorLocalidad.getLocalidad(vistaCliente.getLocalidad()));
        cliente.setNombre(vistaCliente.getNombre());
        cliente.setDni(vistaCliente.getDni());
        cliente.setTelefono(vistaCliente.getTelefono());
        cliente.setFecha_alta(vistaCliente.getFechaAlta());
        
        gestorCliente.agregar(cliente);
        JOptionPane.showMessageDialog(vistaCliente, "Cliente Agregado");
        vistaCliente.limpiar();
        mostrar();
    
    }

    private void cancelar() {
        vistaCliente.limpiar();
        vistaCliente.editar(false);
    }

    private void eliminar() {
        int r=JOptionPane.showConfirmDialog(vistaCliente, "Decea Dar de baja a este Cliente?","Por Favor Confirme",JOptionPane.YES_OPTION);
        if (r==JOptionPane.YES_OPTION) {
            gestorCliente.eliminar(cliente);
            cancelar();
            mostrar();
        }
    
    }

    private void modificar() {
        
        cliente.getDireccion().setCalle(vistaCliente.getCalle());
        cliente.getDireccion().setNumero(vistaCliente.getNumero());
        cliente.getDireccion().setLocalidad(gestorLocalidad.getLocalidad(vistaCliente.getLocalidad()));
        cliente.setNombre(vistaCliente.getNombre());
        cliente.setDni(vistaCliente.getDni());
        cliente.setTelefono(vistaCliente.getTelefono());
        cliente.setFecha_alta(vistaCliente.getFechaAlta());
        gestorCliente.modificar(cliente);
        JOptionPane.showMessageDialog(vistaCliente, "Cliente Modificado");
        cancelar();
        mostrar();
    
    }

    private void salir() {
        vistaCliente.setVisible(false);
    }

    
    
}
