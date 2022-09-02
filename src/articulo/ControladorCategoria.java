/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package articulo;

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
public class ControladorCategoria implements ActionListener,MouseListener{

    private GestorCategoria gestorCategoria;
    private Categoria categoria;
    private FrmCategoria vistaCategoria;

    public ControladorCategoria(Frame padre) {
        
        vistaCategoria=new FrmCategoria(padre, true);
        vistaCategoria.pasarControlador(this);
        
        categoria=new Categoria();
        gestorCategoria=new GestorCategoria();
        
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        switch(ae.getActionCommand()){
            case FrmCategoria.Cat_aceptar:
                agregar();
                break;
            case FrmCategoria.Cat_modificar:
                modificar();
                break;
            case FrmCategoria.Cat_eliminar:
                eliminar();
                break;
            case FrmCategoria.Cat_cancelar:
                cancelar();
                break;
            case FrmCategoria.Cat_salir:
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
        categoria.setIdCategoria(Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString()));
        categoria.setDescripcion(tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
        vistaCategoria.setCategoria(categoria.getDescripcion());
        vistaCategoria.editar(true);
    }

    @Override
    public void mouseEntered(MouseEvent me) {
       
    }

    @Override
    public void mouseExited(MouseEvent me) {
    
    }

    private void agregar() {
        Categoria cat=new Categoria();
        cat.setDescripcion(vistaCategoria.getCategoria());
        gestorCategoria.agregar(cat);
        JOptionPane.showMessageDialog(vistaCategoria, "Categoría Registrada");
        vistaCategoria.limpiar();
        mostrar();
    }

    private void modificar() {
        categoria.setDescripcion(vistaCategoria.getCategoria());
        gestorCategoria.modificar(categoria);
        JOptionPane.showMessageDialog(vistaCategoria, "Categoría Modificada");
        vistaCategoria.limpiar();
        mostrar();
    
    }

    private void eliminar() {
        int r=JOptionPane.showConfirmDialog(vistaCategoria, "Decea Eliminar la Categoría?","Por Favor Confirme",JOptionPane.YES_OPTION);
        if (r==JOptionPane.YES_OPTION) {
            gestorCategoria.eliminar(categoria);
            vistaCategoria.limpiar();
            mostrar();
        }
    }

    private void mostrar() {
        ArrayList<Categoria>lista=gestorCategoria.getLista();
        DefaultTableModel matriz=new DefaultTableModel();
        matriz.addColumn("idCategoria");
        matriz.addColumn("descripcion");
        for(Categoria c:lista){
            matriz.addRow(new Object[]{
                c.getIdCategoria(),c.getDescripcion()
            });
        }
        
        vistaCategoria.setTabla(matriz);
        
    }

    private void cancelar() {
       vistaCategoria.limpiar();
       vistaCategoria.editar(false);
    }
    
    public void ejecutar(){
        vistaCategoria.limpiar();
        mostrar();
        vistaCategoria.setVisible(true);
    }

    private void salir() {
        vistaCategoria.setVisible(false);
    }
}
