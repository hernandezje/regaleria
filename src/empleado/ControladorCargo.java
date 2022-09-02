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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yohan
 */
public class ControladorCargo implements ActionListener,MouseListener{
    
    private GestorCargo gestorCargo;
    private Cargo cargo;
    private FrmCargo vistaCargo;
    
    
    public ControladorCargo (Frame padre){
        vistaCargo= new FrmCargo(padre, true);
        vistaCargo.pasarControlador(this);
        
        cargo=new Cargo();
        gestorCargo=new GestorCargo();
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch(ae.getActionCommand()){
            case FrmCargo.Car_aceptar:
                agregar();
                break;
            case FrmCargo.Car_modificar:
                modificar();
                break;
            case FrmCargo.Car_eliminar:
                eliminar();
                break;
            case FrmCargo.Car_cancelar:
                cancelar();
                break;
            case FrmCargo.Car_salir:
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
        cargo.setIdCargo(Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString()));
        cargo.setDescripcionCargo(tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
        cargo.setSalario(Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow(), 2).toString()));
        vistaCargo.setDescripcionCargo(cargo.getDescripcionCargo());
        vistaCargo.setSalario(cargo.getSalario());
        vistaCargo.editar(true);
    
    }

    @Override
    public void mouseEntered(MouseEvent me) {
       
    }

    @Override
    public void mouseExited(MouseEvent me) {
       
    }

    private void agregar() {
     
        Cargo car=new Cargo();
        car.setDescripcionCargo(vistaCargo.getDescripcionCargo());
        car.setSalario(vistaCargo.getSalario());
        gestorCargo.agregar(car);
        JOptionPane.showMessageDialog(vistaCargo, "Cargo Agregado Correctamente");
        vistaCargo.limpiar();
        mostrar();
        
        
    }

    private void modificar() {
        cargo.setDescripcionCargo(vistaCargo.getDescripcionCargo());
        cargo.setSalario(vistaCargo.getSalario());
        gestorCargo.modificar(cargo);
        JOptionPane.showMessageDialog(vistaCargo, "Cargo Modificado Correctamente");
        vistaCargo.limpiar();
        mostrar();
    }

    private void eliminar() {
        int r=JOptionPane.showConfirmDialog(vistaCargo, "Decea Eliminar el Cargo?","Por Favor Confirme",JOptionPane.YES_OPTION);
        if (r==JOptionPane.YES_OPTION) {
            gestorCargo.eliminar(cargo);
            vistaCargo.limpiar();
            mostrar();
        }
    }

    private void mostrar() {
        ArrayList<Cargo>lista=gestorCargo.getListaCargo();
        DefaultTableModel matriz=new DefaultTableModel();
        matriz.addColumn("idCargo");
        matriz.addColumn("descripcion");
        matriz.addColumn("salario");
        for(Cargo c:lista){
            matriz.addRow(new Object[]{
                c.getIdCargo(),c.getDescripcionCargo(),c.getSalario()
            });
        }
        
        vistaCargo.setTabla(matriz);
    }

    private void salir() {
        vistaCargo.setVisible(false);
    }

    private void cancelar() {
       vistaCargo.limpiar();
       vistaCargo.editar(false);
    }
    
    
    public void ejecutar(){
        vistaCargo.limpiar();
        mostrar();
        vistaCargo.setVisible(true);
    }
    
}
