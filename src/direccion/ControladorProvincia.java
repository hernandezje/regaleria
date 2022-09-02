/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package direccion;

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
 * @author YOHANA HERNANDEZ
 */
public class ControladorProvincia implements ActionListener,MouseListener{
    private FrmProvincia vistaProv;
    private GestorProvincia gestorProv;
    private Provincia provincia;
    
    public ControladorProvincia(Frame padre){
        vistaProv = new FrmProvincia(padre,true);
        vistaProv.pasarControlador(this);
        
        gestorProv=new GestorProvincia();
        provincia=new Provincia();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
       switch(ae.getActionCommand()){
           case FrmProvincia.Prov_agregar: 
               agregar();
               break;
           case FrmProvincia.Prov_cancelar:
               cancelar();
               break;
           case FrmProvincia.Prov_eliminar:
               eliminar();
               break;
           case FrmProvincia.Prov_modificar:
               modificar();
               break;
           case FrmProvincia.Prov_salir:
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
        provincia.setIdProv(Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString()));
        provincia.setNombreProv(tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
        vistaProv.setProvincia(provincia.getNombreProv());
        vistaProv.editar(true);
        
       
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
       
    }

    private void agregar() {
       Provincia pro=new Provincia();
       pro.setNombreProv(vistaProv.getProvincia());
       gestorProv.agregar(pro);
        JOptionPane.showMessageDialog(vistaProv, "Provincia Registrada");
        vistaProv.limpiar();
        mostrar();
    }

    private void mostrar() {
        ArrayList<Provincia>lista=gestorProv.getLista();
        DefaultTableModel matriz=new DefaultTableModel();
        matriz.addColumn("idProvincia");
        matriz.addColumn("nombre");
        for (Provincia p : lista) {
            matriz.addRow(new Object[]{
                p.getIdProv(),p.getNombreProv()
            });
        }
        vistaProv.setTabla(matriz);
    }

    private void cancelar() {
       vistaProv.limpiar();
       vistaProv.editar(false);
    }

    private void eliminar() {
       int r=JOptionPane.showConfirmDialog(vistaProv, "Decea Borrar Esta Provincia?","Por Favor Confirme",JOptionPane.YES_NO_OPTION);
       if(r==JOptionPane.YES_OPTION){
           gestorProv.eliminar(provincia);
           cancelar();
           mostrar();
       }
    }

    private void modificar() {
      provincia.setNombreProv(vistaProv.getProvincia());
       gestorProv.modificar(provincia);
        JOptionPane.showMessageDialog(vistaProv, "Provincia Modificada");
        cancelar();
        mostrar();
    }

    public void ejecutar() {
        vistaProv.limpiar();
        mostrar();
        vistaProv.setVisible(true);
    }

    private void salir() {
    
        vistaProv.setVisible(false);
    }
}
