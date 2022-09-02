
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package articulo;


import direccion.FrmLocalidad;
import direccion.Provincia;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author yohan
 */
public class ControladorArticulo implements ActionListener,MouseListener,ItemListener{
    private FrmArticulo vistaArticulo;
    private GestorArticulo gestorArticulo;
    private GestorCategoria gestorCategoria;
    
    ArrayList<Categoria> listaCat;
    ArrayList<Articulo> listaArt;
    
    private Articulo articulo=new Articulo();

    public ControladorArticulo(Frame padre) {
        vistaArticulo=new FrmArticulo(padre,true);
        vistaArticulo.pasarControlador(this);
        gestorArticulo=new GestorArticulo();
        gestorCategoria=new GestorCategoria();
        listaCat=gestorCategoria.getLista();
    }
    
    public void ejecutar(){
        vistaArticulo.limpiar();
        listaArt=gestorArticulo.getListaArticulo();
        vistaArticulo.pasarCategoria(listaCat);
       // listaArt=gestorArticulo.getListaArticulo(listaCat.get(0));
        vistaArticulo.mostrarArticulo(listaArt);
        vistaArticulo.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch(ae.getActionCommand()){
            case FrmArticulo.Art_aceptar:
                agregar();
                break;
            case FrmArticulo.Art_eliminar:
                eliminar();
                break;
            case FrmArticulo.Art_modificar:
                modificar();
                break;
            case FrmArticulo.Art_cancelar:
                cancelar();
                break;
            case FrmArticulo.Art_salir:
                salir();
                break;
//            case FrmArticulo.Art_todos:
//                todos();
//                break;
            
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
        articulo.setIdArticulo(Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString()));
        gestorArticulo.recuperar(articulo);
        vistaArticulo.setCategoria(getPosicion(articulo.getCategoria().getIdCategoria()));
        vistaArticulo.setCodigo(articulo.getCodigo());
        vistaArticulo.setDescripcion(articulo.getDescripcionArticulo());
        vistaArticulo.setPrecioCompra(articulo.getPrecioCompra());
        vistaArticulo.setStock(articulo.getStock());
        vistaArticulo.setPrecioVenta(articulo.getPrecioVenta());
        
        
        vistaArticulo.editar(true);
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    
    }

    @Override
    public void mouseExited(MouseEvent me) {
     
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        if(vistaArticulo.getCategoria()>-1){
            vistaArticulo.mostrarArticulo(gestorArticulo.getLista(listaCat.get(vistaArticulo.getCategoria())));
        }
    }

    private void agregar() {
        Articulo art=new Articulo();
        art.setCodigo(vistaArticulo.getCodigo());
        art.setDescripcionArticulo(vistaArticulo.getDescripcion());
        art.setPrecioCompra(vistaArticulo.getPrecioCompra());
        art.setPrecioVenta(vistaArticulo.getPrecioVenta());
        art.setStock(vistaArticulo.getStock());
        art.getCategoria().setIdCategoria(listaCat.get(vistaArticulo.getCategoria()).getIdCategoria());
        gestorArticulo.agregar(art);
        JOptionPane.showMessageDialog(vistaArticulo, "Articulo Creado");
        vistaArticulo.limpiar();
        vistaArticulo.mostrarArticulo(gestorArticulo.getListaArticulo());
    
    }

    private void eliminar() {
        int r=JOptionPane.showConfirmDialog(vistaArticulo, "Decea eliminar este artículo","Por favor confirme",JOptionPane.YES_OPTION);
        if(r==JOptionPane.YES_OPTION){
            gestorArticulo.eliminar(articulo);
            vistaArticulo.limpiar();
            itemStateChanged(null);
            articulo.setCategoria(listaCat.get(vistaArticulo.getCategoria()));
            
        }
    }

    private void modificar() {
        articulo.setCodigo(vistaArticulo.getCodigo());
        articulo.setDescripcionArticulo(vistaArticulo.getDescripcion());
        articulo.setPrecioCompra(vistaArticulo.getPrecioCompra());
        articulo.setPrecioVenta(vistaArticulo.getPrecioVenta());
        articulo.setStock(vistaArticulo.getStock());
        articulo.getCategoria().setIdCategoria(listaCat.get(vistaArticulo.getCategoria()).getIdCategoria());
        gestorArticulo.modificar(articulo);
        JOptionPane.showMessageDialog(vistaArticulo, "Articulo Modificado");
        vistaArticulo.limpiar();
        itemStateChanged(null);
        articulo.setCategoria(listaCat.get(vistaArticulo.getCategoria()));
        vistaArticulo.mostrarArticulo(gestorArticulo.getListaArticulo());
        
    }

    private void cancelar() {
        vistaArticulo.limpiar();
        vistaArticulo.editar(false);
    }

    private void salir() {
        vistaArticulo.limpiar();
        vistaArticulo.setVisible(false);
    
    }

    private int getPosicion(int idcat) {
         int p=-1;
        for(Categoria cat:listaCat){
            p++;
            if(idcat==cat.getIdCategoria())
                return p;
        }
        return p;
    }

    private void todos() {
    vistaArticulo.mostrarArticulo(listaArt);   
    
    }
    
    
    
    
}
