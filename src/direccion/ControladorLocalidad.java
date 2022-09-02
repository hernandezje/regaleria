/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package direccion;

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
public class ControladorLocalidad implements ActionListener,MouseListener,ItemListener{
    private FrmLocalidad vistaLoc;
    private GestorLocalidad gestorLocalidad;
    private GestorProvincia gestorProvincia;

    ArrayList<Provincia> listaProv;
    ArrayList<Localidad> listaLoc;
    private Localidad localidad=new Localidad();
    
    
    public ControladorLocalidad(Frame padre) {
        vistaLoc=new FrmLocalidad(padre,true);
        vistaLoc.pasarControlador(this);
        gestorLocalidad=new GestorLocalidad();
        gestorProvincia=new GestorProvincia();
        
    }
    public void ejecutar(){
        vistaLoc.limpiar();
        listaProv=gestorProvincia.getLista();
        vistaLoc.pasarProvincia(listaProv);
        listaLoc=gestorLocalidad.getLista(listaProv.get(0));
        vistaLoc.mostrarLocalidad(listaLoc);
        vistaLoc.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch(ae.getActionCommand()){
            case FrmLocalidad.Loc_agregar:
                agregar();
                break;
            case FrmLocalidad.Loc_todos:
                todos();
                break;
            case FrmLocalidad.Loc_cancelar:
                cancelar();
                break;
            case FrmLocalidad.Loc_modificar:
                modificar();
                break;
            case FrmLocalidad.Loc_eliminar:
                eliminar();
                break;
            case FrmLocalidad.Loc_salir:
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
        localidad.setIdLocalidad(Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString()));
        localidad.setNombreLoc(tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
        
        int idProvincia= Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
        localidad.getProvincia().setIdProv(idProvincia);
        int posprov=getPosicion(idProvincia);
        vistaLoc.setProvincia(posprov);
        
        vistaLoc.setLocalidad(localidad.getNombreLoc());
        vistaLoc.editar(true);

    }

    @Override
    public void mouseEntered(MouseEvent me) {
       
    }

    @Override
    public void mouseExited(MouseEvent me) {
     
    
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
    
    if(vistaLoc.getProvincia()>-1){
        vistaLoc.mostrarLocalidad(gestorLocalidad.getLista(listaProv.get(vistaLoc.getProvincia())));
    }
    }

    private void agregar() {
      localidad.setNombreLoc(vistaLoc.getLocalidad());
      localidad.setProvincia(listaProv.get(vistaLoc.getProvincia()));
      gestorLocalidad.agregar(localidad);
        itemStateChanged(null);
      vistaLoc.limpiar();
    }

    private void todos() {
    vistaLoc.mostrarLocalidad(gestorLocalidad.getLista());
    }
    
    private int getPosicion(int idProvincia){
        int p=-1;
        for(Provincia pro:listaProv){
            p++;
            if(idProvincia==pro.getIdProv())
                return p;
        }
        return p;
    }

    private void cancelar() {
        vistaLoc.limpiar();
        vistaLoc.editar(false);
        
    }

    private void modificar() {
        localidad.setNombreLoc(vistaLoc.getLocalidad());
        gestorLocalidad.modificar(localidad);
        JOptionPane.showMessageDialog(vistaLoc, "Localidad Modificada");
        vistaLoc.limpiar();
        itemStateChanged(null);
        localidad.setProvincia(listaProv.get(vistaLoc.getProvincia()));
    }

    private void eliminar() {
        int r=JOptionPane.showConfirmDialog(vistaLoc, "Decea Eliminar Esta Localidad?","Por Favor Confirme",JOptionPane.YES_OPTION);
        if (r==JOptionPane.YES_OPTION) {
            gestorLocalidad.eliminar(localidad);
            vistaLoc.limpiar();
            itemStateChanged(null);
            localidad.setProvincia((listaProv.get(vistaLoc.getProvincia())));
            
        }
        
    }

    private void salir() {
            vistaLoc.setVisible(false);
    
    
    }
    
}
