/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedido;

import articulo.Articulo;
import empleado.Empleado;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import proveedor.Proveedor;

/**
 *
 * @author yohan
 */
public class Pedido {
    
    private int idPedido;
    private Empleado empleado= new Empleado();
    private Proveedor proveedor= new Proveedor();
    private java.util.Date fecha;
    private Time hora;
    private String estado;
    private double total;
    private ArrayList<DetallePedido> lista=new ArrayList<DetallePedido>();

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<DetallePedido> getLista() {
        return lista;
    }

    public void setLista(ArrayList<DetallePedido> lista) {
        this.lista = lista;
    }

    void calcular() {
    total=0;
        for (DetallePedido d : lista) {
            total=total+d.getArticulo().getPrecioCompra()*d.getCantidad();
        }
    }

    void agregar(DetallePedido linea) {
        lista.add(linea);
    }
    
}

