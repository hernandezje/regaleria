/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proveedor;

import direccion.GestorLocalidad;
import direccion.GestorProvincia;
import direccion.Direccion;
import empleado.Empleado;
import empleado.FrmEmpleado;
import empleado.GestorEmpleado;
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
public class ControladorProveedor implements ActionListener, MouseListener, ItemListener{
    
    private FrmProveedor vistaProveedor;
    private GestorProveedor gestorProveedor;
    private Proveedor proveedor;
    private GestorProvincia gestorProvincia;
    private GestorLocalidad gestorLocalidad;
    private Direccion direccion;
    
    public ControladorProveedor(Frame padre){
        vistaProveedor= new FrmProveedor(padre, true);
        gestorProveedor=new GestorProveedor();
        proveedor=new Proveedor();
        gestorProvincia=new GestorProvincia();
        gestorLocalidad=new GestorLocalidad();
        direccion=new Direccion();
        
        vistaProveedor.pasarControlador(this);
        
    }
    
    public void ejecutar(){
        vistaProveedor.limpiar();
        vistaProveedor.ponerProvincia(gestorProvincia.getLista());
        mostrar();
        vistaProveedor.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch(ae.getActionCommand()){
            
            case FrmProveedor.BTN_ADD:
                agregar();
                break;
            case FrmProveedor.BTN_CANCEL:
                cancelar();
                break;
            case FrmProveedor.BTN_DEL:
                eliminar();
                break;
            case FrmProveedor.BTN_EDIT:
                modificar();
                break;
            case FrmProveedor.BTN_EXIT:
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
        proveedor.setIdProveedor(Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString()));
        gestorProveedor.recuperar(proveedor);
        vistaProveedor.setCalle(proveedor.getDireccion().getCalle());
        vistaProveedor.setNumero(proveedor.getDireccion().getNumero());
        vistaProveedor.setProvincia(proveedor.getDireccion().getLocalidad().getProvincia().getNombreProv());
        vistaProveedor.setLocalidad(proveedor.getDireccion().getLocalidad().getNombreLoc());
        vistaProveedor.setCuit(proveedor.getCuit());
        vistaProveedor.setRazonSocial(proveedor.getRazonSocial());
        vistaProveedor.setTelefono(proveedor.getTel());
        vistaProveedor.setCelular(proveedor.getCelular());
        vistaProveedor.setCorreo(proveedor.getCorreo());
        
        vistaProveedor.editar(true);
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
            vistaProveedor.poneLocalidad(gestorLocalidad.getLista(vistaProveedor.getProvincia()));
        }
    }

    private void mostrar() {
        ArrayList<Proveedor>lista=gestorProveedor.getLista();
        DefaultTableModel matriz=new DefaultTableModel();
        matriz.addColumn("idProveedor");
        matriz.addColumn("Cuit");
        matriz.addColumn("Razon Social");
        matriz.addColumn("Telefono");
        matriz.addColumn("Celular");
        matriz.addColumn("Correo");
        matriz.addColumn("Direccion");
        
        for(Proveedor p:lista){
            matriz.addRow(new Object[]{
                p.getIdProveedor(),
                p.getCuit(),
                p.getRazonSocial(),
                p.getTel(),
                p.getCelular(),
                p.getCorreo(),
                p.getDireccion().getCalle()+" "+p.getDireccion().getNumero()+", "+p.getDireccion().getLocalidad().getNombreLoc()
                        +", "+p.getDireccion().getLocalidad().getProvincia().getNombreProv()
            });
        }
        vistaProveedor.setTabla(matriz);
        
    
    }

    private void agregar() {
        proveedor.getDireccion().setCalle(vistaProveedor.getCalle());
        proveedor.getDireccion().setNumero(vistaProveedor.getNumero());
        proveedor.getDireccion().setLocalidad(gestorLocalidad.getLocalidad(vistaProveedor.getLocalidad()));
        proveedor.setCuit(vistaProveedor.getCuit());
        proveedor.setRazonSocial(vistaProveedor.getRazonSocial());
        proveedor.setTel(vistaProveedor.getTelefono());
        proveedor.setCelular(vistaProveedor.getCelular());
        proveedor.setCorreo(vistaProveedor.getCorreo());
        
        gestorProveedor.agregar(proveedor);
        JOptionPane.showMessageDialog(vistaProveedor, "Proveedor Modificado");
        vistaProveedor.limpiar();
        mostrar();
    
    }

    private void cancelar() {
        vistaProveedor.limpiar();
        vistaProveedor.editar(false);
    }

    private void eliminar() {
        int r=JOptionPane.showConfirmDialog(vistaProveedor, "Decea Dar de baja a este Empleado?","Por Favor Confirme",JOptionPane.YES_OPTION);
        if (r==JOptionPane.YES_OPTION) {
            gestorProveedor.eliminar(proveedor);
            cancelar();
            mostrar();
        }
    
    }

    private void modificar() {
        
        proveedor.getDireccion().setCalle(vistaProveedor.getCalle());
        proveedor.getDireccion().setNumero(vistaProveedor.getNumero());
        proveedor.getDireccion().setLocalidad(gestorLocalidad.getLocalidad(vistaProveedor.getLocalidad()));
        proveedor.setCuit(vistaProveedor.getCuit());
        proveedor.setRazonSocial(vistaProveedor.getRazonSocial());
        proveedor.setTel(vistaProveedor.getTelefono());
        proveedor.setCelular(vistaProveedor.getCelular());
        proveedor.setCorreo(vistaProveedor.getCorreo());
        
        gestorProveedor.modificar(proveedor);
        
        JOptionPane.showMessageDialog(vistaProveedor, "Proveedor Modificado");
        cancelar();
        mostrar();
    
    }

    private void salir() {
        vistaProveedor.setVisible(false);
    }
    
}
