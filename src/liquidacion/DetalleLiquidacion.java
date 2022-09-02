/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liquidacion;

/**
 *
 * @author yohan
 */
public class DetalleLiquidacion {
    private int idDetalle_Liquidacion;
    private Liquidacion  liquidacion = new Liquidacion();
    private Concepto concepto=new Concepto();
    private int cantidad;
    private double suma;
    private double resto;
    private double valor;

    public int getIdDetalle_Liquidacion() {
        return idDetalle_Liquidacion;
    }

    public void setIdDetalle_Liquidacion(int idDetalle_Liquidacion) {
        this.idDetalle_Liquidacion = idDetalle_Liquidacion;
    }

    public Liquidacion getLiquidacion() {
        return liquidacion;
    }

    public void setLiquidacion(Liquidacion liquidacion) {
        this.liquidacion = liquidacion;
    }

    public Concepto getConcepto() {
        return concepto;
    }

    public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public double getResto() {
        return resto;
    }

    public void setResto(double resto) {
        this.resto = resto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
   
}
