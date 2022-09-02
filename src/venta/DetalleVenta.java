/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venta;
import articulo.*;

/**
 *
 * @author yohan
 */
public class DetalleVenta {
    
    private int idDetalleVenta;
    private Articulo articulo=new Articulo();
    private Venta venta=new Venta();
    private int cantidad;
    private double subtotal;

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = new Articulo();
        this.articulo.getCategoria().setDescripcion(articulo.getCategoria().getDescripcion());
        this.articulo.getCategoria().setIdCategoria(articulo.getCategoria().getIdCategoria());
        this.articulo.setCodigo(articulo.getCodigo());
        this.articulo.setDescripcionArticulo(articulo.getDescripcionArticulo());
        this.articulo.setIdArticulo(articulo.getIdArticulo());
        this.articulo.setPrecioCompra(articulo.getPrecioCompra());
        this.articulo.setPrecioVenta(articulo.getPrecioVenta());
        this.articulo.setStock(articulo.getStock());
        
                
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        subtotal = articulo.getPrecioVenta()*cantidad;
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    
}
