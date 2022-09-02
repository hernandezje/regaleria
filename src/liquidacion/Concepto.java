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
public class Concepto {
    
    private int idConcepto;
    private String tipo;
    private double valor;
    private boolean suma;
    private boolean porcentual;
    
    public Concepto(){
        
    }

    public int getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(int idConcepto) {
        this.idConcepto = idConcepto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    

    public boolean isSuma() {
        return suma;
    }

    public void setSuma(boolean suma) {
        this.suma = suma;
    }

    public boolean isPorcentual() {
        return porcentual;
    }

    public void setPorcentual(boolean porcentual) {
        this.porcentual = porcentual;
    }
    
    
}
