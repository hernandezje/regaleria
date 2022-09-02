/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liquidacion;

import bd.Conexion;
import empleado.Empleado;
import empleado.Familiar;
import empleado.GestorEmpleado;
import empleado.GestorFamiliar;
import static groovy.xml.Entity.para;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import regaleria.Regaleria;

/**
 *
 * @author yohan
 */
public class ControladorLiquidacion implements ActionListener{
private FrmLiquidacion vista;
private GestorConcepto gestorConcepto;
private Concepto concepto;
private Liquidacion liquidacion;
private GestorEmpleado gestorEmpleado;
private GestorFamiliar gestorFamiliar;
private GestorLiquidacion gestorLiquidacion;
private Empleado empleado;
private ArrayList<Concepto> listConcepto;

    public ControladorLiquidacion(JFrame padre) {
        vista=new FrmLiquidacion(padre, true);
        gestorConcepto=new GestorConcepto();
        concepto=new Concepto();
        liquidacion=new Liquidacion();
        gestorEmpleado=new GestorEmpleado();
        gestorFamiliar=new GestorFamiliar();
        empleado= new Empleado();
        vista.pasarControlador(this);
        gestorLiquidacion=new GestorLiquidacion();
    }
    
    public void ejecutar(){
        listConcepto=gestorConcepto.getLista();
        vista.ponerConceptos(listConcepto);
        vista.limpiarEmp();
        vista.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()){
            case FrmLiquidacion.BTN_BuscarEmpl:
                buscarEmpleado();
                break;
            case FrmLiquidacion.Btn_Agregar:
                agregar();
                break;
            case FrmLiquidacion.Btn_Grabar:
                guardar();
                break;
            case FrmLiquidacion.Btn_Salir:
                salir();
                break; 
            case FrmLiquidacion.Btn_Quitar:
                quitarLinea();
                break;
        }
    
    }

    private void buscarEmpleado() {
    int legajo=vista.getLegajo();
    empleado= gestorEmpleado.buscarXLegajo(legajo);
    if(empleado==null)JOptionPane.showMessageDialog(vista, "legajo no hallado");
    else{
        vista.setNombre(empleado.getNombre());
        vista.setCargo(empleado.getCargo().getDescripcionCargo());
        vista.setBasico("$"+empleado.getCargo().getSalario());
        vista.setIngreso(""+Fecha.yyyymmddAddmmyyyy(empleado.getFecha_ingreso()));
        vista.setAntig(Fecha.antiguedad(empleado.getFecha_ingreso())+" años");
        mostrarFamilia();
    }
    }

    private void mostrarFamilia() {
         ArrayList<Familiar>lista=gestorFamiliar.getListaFamiliar(empleado);
        DefaultTableModel matriz=new DefaultTableModel();
        matriz.addColumn("idFamiliar");
        matriz.addColumn("Parentesco");
        matriz.addColumn("Nombre");
        matriz.addColumn("DNI");
        matriz.addColumn("Telefono");
        matriz.addColumn("Fec Nac");
        for(Familiar f:lista){
            matriz.addRow(new Object[]{
                f.getIdFamiliar(),f.getParentesco(),f.getNombre(),f.getDni(),f.getTelFamiliar(),f.getFechaNac()
            });
        }
        
        vista.setTablaFamilia(matriz);
    }

//    private void agregarConcepto() {
//        Concepto tem=listConcepto.get(vista.getConceptoSel());

//    }

    private void agregar() {
        int cant=0;
        try {
            cant=vista.getCantidad();
            if(cant<1)
                cant=1;
        } catch (Exception e) {
             cant=1;
        }
        Concepto x=listConcepto.get(vista.getConceptoSel());
          DetalleLiquidacion dl=new DetalleLiquidacion();
       dl.setLiquidacion(liquidacion);
       dl.setConcepto(x);
        switch(x.getIdConcepto()){
            case 1://sueldo basico
                dl.setCantidad(1);
                dl.setResto(0);
                dl.setSuma(empleado.getCargo().getSalario());
                break;
            case 2: //jubilacion
            case 3: //obra social
                 dl.setCantidad(1);
                 dl.setResto(empleado.getCargo().getSalario()*x.getValor());
                 dl.setSuma(0);
                break;
            case 7://inasistencia
                 dl.setCantidad(cant);
                 dl.setResto(cant*x.getValor());
                 dl.setSuma(0);
                 break;
            case 8://horas extras
                 dl.setCantidad(cant);
                 dl.setResto(0);
                 dl.setSuma(cant*x.getValor());
                 break;
            case 9: //antiguedad
                cant=Fecha.antiguedad(empleado.getFecha_ingreso());
                dl.setCantidad(cant);
                dl.setResto(0);
                dl.setSuma(cant*x.getValor());
                break;
            case 10://sueldo anual complementario
                dl.setCantidad(1);
                dl.setSuma(empleado.getCargo().getSalario()/2);
                dl.setResto(0);
                break;
                default:
                     dl.setCantidad(cant);
                    if(x.isSuma()){
                          dl.setResto(0);
                        if(x.isPorcentual()){
                           
                            dl.setSuma(x.getValor()*empleado.getCargo().getSalario());
                          
                        }
                        else{
                            dl.setSuma(x.getValor()*cant);
                            
                        }
                    }else{
                         dl.setSuma(0);
                        if(x.isPorcentual()){
                            dl.setResto(x.getValor()*empleado.getCargo().getSalario());
                           
                            
                        }
                        else{
                            dl.setResto(x.getValor()*cant);
                        }
                    }
        }
     
     liquidacion.agregar(dl);
     mosstrarLineas();
     vista.setCantidad(1);
//       mostrarFamilia();
        
    }
    
    private void guardar() {
       liquidacion.getEmpleado().setIdEmpleado(empleado.getIdEmpleado());
       liquidacion.setFecha_desde(vista.getDesde());
       liquidacion.setFecha_hasta(vista.getHasta());
       liquidacion.setPeriodo_liq(vista.getMes()+"/"+vista.getAño());
       gestorLiquidacion.agregar(liquidacion);
       if(JOptionPane.showConfirmDialog(vista, "decea imprimir esta liquidacion","confirme",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
           imprimir();
       
    
    }

    private void salir() {
        vista.limpiarEmp();
        vista.setVisible(false);
    }

    private void mosstrarLineas() {
       
        DefaultTableModel matriz=new DefaultTableModel();
        matriz.addColumn("Concepto");
        matriz.addColumn("valor");
        matriz.addColumn("Cantidad");
        matriz.addColumn("Aporte");
        matriz.addColumn("Descuento");
       
        for(DetalleLiquidacion f:liquidacion.getLista()){
            matriz.addRow(new Object[]{
                f.getConcepto().getTipo(),
                f.getConcepto().getValor(),
                f.getCantidad(),
                f.getSuma(),
                f.getResto()
            });
        }
        
        vista.setTablaLineas(matriz);   
        liquidacion.calcular();
        vista.setAportes(liquidacion.getAportes());
        vista.setDescuentos(liquidacion.getRetenciones());
        vista.setNeto(liquidacion.getSueldo_neto());
    }

    private void quitarLinea() {
        int fila=vista.getLineLiquid();
        if(fila==-1)
            JOptionPane.showMessageDialog(vista, "debe seleccionar una linea para quitarla");
        else{
            liquidacion.getLista().remove(fila);
            mosstrarLineas();
        }
    }

    private void imprimir() {
        try {
            
         JasperReport jasperReport =JasperCompileManager.compileReport(Regaleria.REPORTE_LIQUI);
                Map para=new HashMap();
            para.put("idLiquidacion",liquidacion.getIdLiquidacion());
          
                JasperPrint jasperPrint   =JasperFillManager.fillReport(jasperReport, para,bd.Conexion.con);
                JasperViewer jrv=new JasperViewer(jasperPrint);
            
            jrv.setVisible(true);
         } catch (JRException e) {
             e.printStackTrace();
        }
    }
        
    
}
