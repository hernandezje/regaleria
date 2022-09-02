/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liquidacion;

import empleado.Empleado;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author yohan
 */
class Liquidacion {
    private int idLiquidacion;
    private Empleado empleado=new Empleado();
    private Date fecha_desde;
    private Date fecha_hasta;
    private double retenciones;
    private double aportes;
    private double sueldo_neto;
    private String periodo_liq;
    
    private ArrayList<DetalleLiquidacion> lista=new ArrayList<>();

    public int getIdLiquidacion() {
        return idLiquidacion;
    }

    public void setIdLiquidacion(int idLiquidacion) {
        this.idLiquidacion = idLiquidacion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Date getFecha_desde() {
        return fecha_desde;
    }

    public void setFecha_desde(Date fecha_desde) {
        this.fecha_desde = fecha_desde;
    }

    public Date getFecha_hasta() {
        return fecha_hasta;
    }

    public void setFecha_hasta(Date fecha_hasta) {
        this.fecha_hasta = fecha_hasta;
    }

    public double getAportes() {
        return aportes;
    }

    public void setAportes(double aportes) {
        this.aportes = aportes;
    }

    public double getSueldo_neto() {
        return sueldo_neto;
    }

    public void setSueldo_neto(double sueldo_neto) {
        this.sueldo_neto = sueldo_neto;
    }


    public double getRetenciones() {
        return retenciones;
    }

    public void setRetenciones(double retenciones) {
        this.retenciones = retenciones;
    }

    public String getPeriodo_liq() {
        return periodo_liq;
    }

    public void setPeriodo_liq(String periodo_liq) {
        this.periodo_liq = periodo_liq;
    }

    public ArrayList<DetalleLiquidacion> getLista() {
        return lista;
    }

    public void setLista(ArrayList<DetalleLiquidacion> lista) {
        this.lista = lista;
    }
    public void agregar(DetalleLiquidacion linea){
        lista.add(linea);
    }
    public void calcular(){
        aportes=0;
        retenciones=0;
        for (DetalleLiquidacion d : lista) {
            aportes=aportes+d.getSuma();
            retenciones=retenciones+d.getResto();
        }
        sueldo_neto=aportes-retenciones;
    }
}
