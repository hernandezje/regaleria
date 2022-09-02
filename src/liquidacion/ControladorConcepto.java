/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liquidacion;

import articulo.Categoria;
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
public class ControladorConcepto implements ActionListener,MouseListener{

    private GestorConcepto gestorConcepto;
    private Concepto concepto;
    private FrmConcepto vistaConcepto;
    
    public ControladorConcepto(Frame padre){
        vistaConcepto=new FrmConcepto(padre, true);
        vistaConcepto.pasarControlar(this);
        
        concepto=new Concepto();
        gestorConcepto=new GestorConcepto();
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        switch(ae.getActionCommand()){
            case FrmConcepto.Con_aceptar:
                agregar();
                break;
            case FrmConcepto.Con_modificar:
                modificar();
                break;
            case FrmConcepto.Con_eliminar:
                eliminar();
                break;
            case FrmConcepto.Con_cancelar:
                cancelar();
                break;
            case FrmConcepto.Con_salir:
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
        concepto.setIdConcepto(Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString()));
        concepto.setTipo(tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
        concepto.setValor(Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow(), 2).toString()));
        concepto.setSuma(Boolean.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 3).toString()));
        concepto.setPorcentual(Boolean.parseBoolean(tabla.getValueAt(tabla.getSelectedRow(), 4).toString()));
        
        vistaConcepto.setTipo(concepto.getTipo());
        vistaConcepto.setValor(concepto.getValor());
        vistaConcepto.setSuma(concepto.isSuma());
        vistaConcepto.setPorcentual(concepto.isPorcentual());
        
        vistaConcepto.editar(true);
    }
    

    @Override
    public void mouseEntered(MouseEvent me) {
    
    }

    @Override
    public void mouseExited(MouseEvent me) {
    
    }
   
    
    private void agregar() {
        concepto.setTipo(vistaConcepto.getTipo());
        concepto.setValor(vistaConcepto.getValor());
        concepto.setSuma(vistaConcepto.getSuma());
        concepto.setPorcentual(vistaConcepto.getPorcentual());
        gestorConcepto.agregar(concepto);
        JOptionPane.showMessageDialog(vistaConcepto, "Concepto Agregado");
        vistaConcepto.limpiar();
        mostrar();
        
    
    }

    private void modificar() {
        concepto.setTipo(vistaConcepto.getTipo());
        concepto.setValor(vistaConcepto.getValor());
        concepto.setSuma(vistaConcepto.getSuma());
        concepto.setPorcentual(vistaConcepto.getPorcentual());
        
        gestorConcepto.modificar(concepto);
        JOptionPane.showMessageDialog(vistaConcepto, "Concepto Modificado");
        vistaConcepto.limpiar();
        mostrar();
    
    }

    private void eliminar() {
        int r=JOptionPane.showConfirmDialog(vistaConcepto, "Decea Eliminar el Concepto?","Por Favor Confirme",JOptionPane.YES_OPTION);
        if (r==JOptionPane.YES_OPTION) {
            gestorConcepto.eliminar(concepto);
            vistaConcepto.limpiar();
            mostrar();
        }
    }

    private void cancelar() {
       vistaConcepto.limpiar();
       vistaConcepto.editar(false);
    }

    private void salir() {
        vistaConcepto.limpiar();
        vistaConcepto.setVisible(false);
    }

    private void mostrar() {
        ArrayList<Concepto>lista=gestorConcepto.getLista();
        DefaultTableModel matriz=new DefaultTableModel();
        matriz.addColumn("idConcepto");
        matriz.addColumn("Tipo");
        matriz.addColumn("Valor");
        matriz.addColumn("Suma");
        matriz.addColumn("Porcentual");
        
        for(Concepto c:lista){
            matriz.addRow(new Object[]{
                c.getIdConcepto(),c.getTipo(),c.getValor(),c.isSuma(),c.isPorcentual()
            });
        }
        
        vistaConcepto.setTabla(matriz);
    }
    
    public void ejecutar(){
        vistaConcepto.limpiar();
        mostrar();
        vistaConcepto.setVisible(true);
    }
    
    
}
