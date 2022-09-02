/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venta;

import articulo.*;
import bd.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author YOHANA HERNANDEZ
 */
public class GestorVenta {
    
    public void agregar(Venta v){
        String sql="insert into venta (Cliente_idCliente,Empleado_idEmpleado,fecha,hora,total) values(?,?,?,?,?)";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,v.getCliente().getIdCliente());
            p.setInt(2,v.getEmpleado().getIdEmpleado());
            p.setDate(3,new java.sql.Date(v.getFecha().getTime()));
            p.setTime(4,v.getHora());
            p.setDouble(5, v.getTotal());
            p.execute();
            sql="select max(idVenta) as ultimo from venta";
            ResultSet r=Conexion.con.createStatement().executeQuery(sql);
            if(r.next()){
                v.setIdVenta(r.getInt("ultimo"));
                sql="insert into detalle_venta(Articulo_idArticulo,Venta_idVenta,cantidad,subtotal) values(?,?,?,?)";
                p = Conexion.con.prepareStatement(sql);
                for(DetalleVenta linea:v.getLista()){
                    p.setInt(1, linea.getArticulo().getIdArticulo());
                    p.setInt(2, v.getIdVenta());
                    p.setInt(3, linea.getCantidad());
                    p.setDouble(4, linea.getSubtotal());
                    p.execute();
                    descontar(linea.getArticulo().getIdArticulo(),linea.getCantidad());
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void agregarArticulo(Venta v, Articulo a, int cantidad){
        DetalleVenta linea=new DetalleVenta();
        linea.getArticulo().setCategoria(a.getCategoria());
        linea.getArticulo().setCodigo(a.getCodigo());
        linea.getArticulo().setDescripcionArticulo(a.getDescripcionArticulo());
        linea.getArticulo().setIdArticulo(a.getIdArticulo());
        linea.getArticulo().setPrecioCompra(a.getPrecioCompra());
        linea.getArticulo().setPrecioVenta(a.getPrecioVenta());
        linea.getArticulo().setStock(a.getStock());
        linea.setCantidad(cantidad);
        linea.setSubtotal(a.getPrecioVenta()*cantidad);
        v.agregar(linea);
    }
    
    public void quitarLinea(Venta v,int index){
        v.getLista().remove(index);
    }

    private void descontar(int idArticulo, int cantidad) {
        String sql="update articulo set "
                + "stock = stock-? "
                + "where idArticulo=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,cantidad);
            p.setInt(2,idArticulo);
            p.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GestorVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    