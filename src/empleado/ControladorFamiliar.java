/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleado;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yohan
 */
public class ControladorFamiliar implements ActionListener,MouseListener{
    private FrmFamilar vistaFamiliar;
    private GestorFamiliar gestorFamiliar;
    private Familiar familiar;
    private Empleado empleado;
    private GestorEmpleado gestorEmpleado;
    
    public ControladorFamiliar(Frame padre) {
        vistaFamiliar=new FrmFamilar(padre, true);
        vistaFamiliar.pasarControlador(this);
        gestorFamiliar=new GestorFamiliar();
        familiar=new Familiar();
        gestorEmpleado= new GestorEmpleado();
        
    }
    public void ejecutar(){
        vistaFamiliar.limpiar();
        vistaFamiliar.limpiarEmpleado();
        mostrar();
        vistaFamiliar.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch(ae.getActionCommand()){
            case FrmFamilar.Fam_aceptar:
                agregar();
                break;
            case FrmFamilar.Fam_modificar:
                modificar();
                break;
            case FrmFamilar.Fam_eliminar:
                eliminar();
                break;
            case FrmFamilar.Fam_cancelar:
                cancelar();
                break;
            case FrmFamilar.Fam_salir:
                salir();
                break;
            case FrmFamilar.Fam_buscar:
                buscar();
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
        familiar.setIdFamiliar(Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString()));
        familiar.setParentesco(tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
        familiar.setNombre(tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
        familiar.setDni(tabla.getValueAt(tabla.getSelectedRow(), 3).toString());
        familiar.setTelFamiliar(tabla.getValueAt(tabla.getSelectedRow(), 4).toString());
        familiar.setFechaNac(Date.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 5).toString()));
        
        vistaFamiliar.setParentesco(familiar.getParentesco());
        vistaFamiliar.setNomFam(familiar.getNombre());
        vistaFamiliar.setDni(familiar.getDni());
        vistaFamiliar.setTelFam(familiar.getTelFamiliar());
        vistaFamiliar.setFechaNac(familiar.getFechaNac());
        vistaFamiliar.editar(true);
     
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    
    }

    @Override
    public void mouseExited(MouseEvent me) {
     
    }

    private void agregar() {
       
        familiar.getEmpleado().setIdEmpleado(empleado.getIdEmpleado());
        familiar.setParentesco(vistaFamiliar.getParentesco());
        familiar.setNombre(vistaFamiliar.getNomFam());
        familiar.setDni(vistaFamiliar.getDni());
        familiar.setTelFamiliar(vistaFamiliar.getTelFam());
        familiar.setFechaNac( new java.sql.Date(vistaFamiliar.getFechaNac().getTime()));
        
        gestorFamiliar.agregar(familiar);
        JOptionPane.showMessageDialog(vistaFamiliar, "Familiar Agregado Correctamente");
        vistaFamiliar.limpiar();
        mostrar();
        
    }

    private void modificar() {
        
        familiar.setParentesco(vistaFamiliar.getParentesco());
        familiar.setNombre(vistaFamiliar.getNomFam());
        familiar.setDni(vistaFamiliar.getDni());
        familiar.setTelFamiliar(vistaFamiliar.getTelFam());
        familiar.setFechaNac( new java.sql.Date(vistaFamiliar.getFechaNac().getTime()));
        
        gestorFamiliar.modificar(familiar);
        JOptionPane.showMessageDialog(vistaFamiliar, "Familiar Modificado Correctamente");
        vistaFamiliar.limpiar();
        mostrar();
    }

    private void eliminar() {
        int r=JOptionPane.showConfirmDialog(vistaFamiliar, "Decea Eliminar el Cargo?","Por Favor Confirme",JOptionPane.YES_OPTION);
        if (r==JOptionPane.YES_OPTION) {
            gestorFamiliar.eliminar(familiar);
            vistaFamiliar.limpiar();
            mostrar();
        }
    
    }

    private void cancelar() {
        vistaFamiliar.limpiar();
        vistaFamiliar.editar(false);
    }

    private void salir() {
        vistaFamiliar.limpiar();
        vistaFamiliar.limpiarEmpleado();
        vistaFamiliar.setVisible(false);
    
    }

    private void mostrar() {
        ArrayList<Familiar>lista=gestorFamiliar.getListaFamiliar();
        DefaultTableModel matriz=new DefaultTableModel();
        matriz.addColumn("idFamiliar");
        matriz.addColumn("Empleado");
        matriz.addColumn("Parentesco");
        matriz.addColumn("Nombre");
        matriz.addColumn("DNI");
        matriz.addColumn("Telefono");
        matriz.addColumn("Fec Nac");
        for(Familiar f:lista){
            matriz.addRow(new Object[]{
                f.getIdFamiliar(),f.getEmpleado().getNombre(),f.getParentesco(),f.getNombre(),f.getDni(),f.getTelFamiliar(),f.getFechaNac()
            });
        }
        
        vistaFamiliar.setTabla(matriz);
    }

    private void buscar() {
        empleado=new Empleado();
       int leg=vistaFamiliar.getLegEmpleado();
       
       
        empleado=gestorEmpleado.buscarXLEG(leg);
        if (empleado==null)JOptionPane.showMessageDialog(vistaFamiliar, "Articulo no hallado");
        else {
            vistaFamiliar.setNomEmpl(empleado.getNombre());
            vistaFamiliar.setDniEmpleado(empleado.getDni());
            vistaFamiliar.setTelEmpl(empleado.getTelefono());
        }
    }
    
    
}
