/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venta;

import cliente.Cliente;
import empleado.Empleado;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author yohan
 */
public class Venta {
    
    
    private int idVenta;
    private Cliente cliente= new Cliente();
    private Empleado empleado=new Empleado();
    private java.util.Date fecha;
    private Time hora;
    private double total;
    private ArrayList<DetalleVenta> lista=new ArrayList<DetalleVenta>();

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<DetalleVenta> getLista() {
        return lista;
    }

    public void setLista(ArrayList<DetalleVenta> lista) {
        this.lista = lista;
    }
    
    public void agregar(DetalleVenta linea){
        lista.add(linea);
    }
    public void calcular(){
        total=0;
        for (DetalleVenta linea : lista) {
            total=total + linea.getSubtotal();
        }
    }
}
