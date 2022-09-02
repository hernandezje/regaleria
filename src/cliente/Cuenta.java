/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

/**
 *
 * @author yohan
 */
public class Cuenta {
    private int idCuenta;
    private Cliente cliente= new Cliente();
    private String num_cuenta;
    private String estado; 
    private String saldo_deuda;

    public Cuenta() {
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNum_cuenta() {
        return num_cuenta;
    }

    public void setNum_cuenta(String num_cuenta) {
        this.num_cuenta = num_cuenta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSaldo_deuda() {
        return saldo_deuda;
    }

    public void setSaldo_deuda(String saldo_deuda) {
        this.saldo_deuda = saldo_deuda;
    }
    
    
}
