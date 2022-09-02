/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleado;

import direccion.Direccion;
import direccion.GestorLocalidad;
import direccion.GestorProvincia;
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
public class ControladorEmpleado implements ActionListener, MouseListener, ItemListener{
    
    private FrmEmpleado vistaEmpleado;
    private GestorEmpleado gestorEmpleado;
    private GestorCargo gestorCargo;
    private GestorProvincia gestorProvincia;
    private GestorLocalidad gestorLocalidad;
    private Empleado empleado;
    private Direccion direccion;
    //private ControladorFamiliar controladorFamiliar;
    
   private ArrayList<Cargo> listaCargo;

    public ControladorEmpleado(Frame padre) {
        
        vistaEmpleado= new FrmEmpleado(padre, true);
        gestorEmpleado= new GestorEmpleado();
        gestorCargo=new GestorCargo();
        gestorLocalidad=new GestorLocalidad();
        gestorProvincia=new GestorProvincia();
        empleado=new Empleado();
        direccion=new Direccion();
       // controladorFamiliar= new ControladorFamiliar(padre);
        vistaEmpleado.pasarControlador(this);

    }
    
    public void ejecutar(){
        listaCargo=gestorCargo.getListaCargo();
        vistaEmpleado.limpiar();
        vistaEmpleado.ponerCargos(listaCargo);
        vistaEmpleado.ponerProvincia(gestorProvincia.getLista());
        mostrar();
        vistaEmpleado.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch(ae.getActionCommand()){
            
            case FrmEmpleado.BTN_ADD:
                agregar();
                break;
            case FrmEmpleado.BTN_CANCEL:
                cancelar();
                break;
            case FrmEmpleado.BTN_DEL:
                eliminar();
                break;
            case FrmEmpleado.BTN_EDIT:
                modificar();
                break;
            case FrmEmpleado.BTN_EXIT:
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
        empleado.setIdEmpleado(Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString()));
        gestorEmpleado.recuperar(empleado);
        vistaEmpleado.setLegajo(empleado.getLegajo());
        vistaEmpleado.setNombre(empleado.getNombre());
        vistaEmpleado.setDni(empleado.getDni());
        vistaEmpleado.setTelefono(empleado.getTelefono());
        vistaEmpleado.setFechaNac(empleado.getFecha_nac());
        vistaEmpleado.setFechaIng(empleado.getFecha_ingreso());
        vistaEmpleado.setCalle(empleado.getDireccion().getCalle());
        vistaEmpleado.setNumero(empleado.getDireccion().getNumero());
        vistaEmpleado.setCargo(empleado.getCargo().getDescripcionCargo());
        vistaEmpleado.setProvincia(empleado.getDireccion().getLocalidad().getProvincia().getNombreProv());
        vistaEmpleado.setLocalidad(empleado.getDireccion().getLocalidad().getNombreLoc());
        
        vistaEmpleado.editar(true);
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
            vistaEmpleado.poneLocalidad(gestorLocalidad.getLista(vistaEmpleado.getProvincia()));
        }
        
    }


    private void agregar() {
        Cargo cargo=buscarCargo(vistaEmpleado.getCargo());
       
        empleado.setCargo(cargo);
        empleado.getDireccion().setCalle(vistaEmpleado.getCalle());
        empleado.getDireccion().setNumero(vistaEmpleado.getNumero());
        empleado.getDireccion().setLocalidad(gestorLocalidad.getLocalidad(vistaEmpleado.getLocalidad()));
        empleado.setDni(vistaEmpleado.getDni());
        empleado.setFecha_ingreso(vistaEmpleado.getFechaIng());
        empleado.setFecha_nac(vistaEmpleado.getFechaNac());
        empleado.setLegajo(vistaEmpleado.getLegajo());
        empleado.setNombre(vistaEmpleado.getNombre());
        empleado.setTelefono(vistaEmpleado.getTelefono());
        
        gestorEmpleado.agregar(empleado);
        vistaEmpleado.limpiar();
        mostrar();
       
    }

    private void cancelar() {
        
        vistaEmpleado.limpiar();
        vistaEmpleado.editar(false);
    }

    private void eliminar() {
        int r=JOptionPane.showConfirmDialog(vistaEmpleado, "Decea Dar de baja a este Empleado?","Por Favor Confirme",JOptionPane.YES_OPTION);
        if (r==JOptionPane.YES_OPTION) {
            gestorEmpleado.eliminar(empleado);
            cancelar();
            mostrar();
        }
    
    }

    private void modificar() {
        Cargo cargo=buscarCargo(vistaEmpleado.getCargo());
       
        empleado.setCargo(cargo);
        empleado.getDireccion().setCalle(vistaEmpleado.getCalle());
        empleado.getDireccion().setNumero(vistaEmpleado.getNumero());
        empleado.getDireccion().setLocalidad(gestorLocalidad.getLocalidad(vistaEmpleado.getLocalidad()));
        empleado.setDni(vistaEmpleado.getDni());
        empleado.setFecha_ingreso(vistaEmpleado.getFechaIng());
        empleado.setFecha_nac(vistaEmpleado.getFechaNac());
        empleado.setLegajo(vistaEmpleado.getLegajo());
        empleado.setNombre(vistaEmpleado.getNombre());
        empleado.setTelefono(vistaEmpleado.getTelefono());
        
        gestorEmpleado.modificar(empleado);
        
        cancelar();
        mostrar();
    
    }

    private void salir() {
        vistaEmpleado.limpiar();
        vistaEmpleado.setVisible(false);
    
    }

    private Cargo buscarCargo(String cargo) {
        for (Cargo c:listaCargo){
            if(c.getDescripcionCargo().equalsIgnoreCase(cargo))
                return c;
        }
        return null;
    }

    private void mostrar() {
        ArrayList<Empleado>lista=gestorEmpleado.getLista();
        DefaultTableModel matriz=new DefaultTableModel();
        matriz.addColumn("idEmpleado");
        matriz.addColumn("Legajo");
        matriz.addColumn("Nombre");
        matriz.addColumn("Dni");
        matriz.addColumn("Nacimiento");
        matriz.addColumn("Ingreso");
        matriz.addColumn("Cargo");
        matriz.addColumn("Telefono");
        matriz.addColumn("Direccion");
        
        for(Empleado e:lista){
            matriz.addRow(new Object[]{
                e.getIdEmpleado(),
                e.getLegajo(),
                e.getNombre(),
                e.getDni(),
                e.getFecha_nac(),
                e.getFecha_ingreso(),
                e.getCargo().getDescripcionCargo(),
                e.getTelefono(),
                e.getDireccion().getCalle()+" "+e.getDireccion().getNumero()+" "+e.getDireccion().getLocalidad().getNombreLoc()
                        +" "+e.getDireccion().getLocalidad().getProvincia().getNombreProv()
            });
        }
       
        vistaEmpleado.setTabla(matriz);
    }
    
    
}
