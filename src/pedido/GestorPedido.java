/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedido;

import venta.*;
import articulo.*;
import direccion.*;
import bd.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author YOHANA HERNANDEZ
 */
public class GestorPedido {
    
    public void agregar(Pedido ped){
        String sql="insert into pedido (Proveedor_idProveedor,Empleado_idEmpleado,fecha_recepcion,hora,estado, total) values(?,?,?,?,?,?)";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,ped.getProveedor().getIdProveedor());
            p.setInt(2,ped.getEmpleado().getIdEmpleado());
            p.setDate(3,new java.sql.Date(ped.getFecha().getTime()));
            p.setTime(4,ped.getHora());
            p.setString(5, ped.getEstado());
            p.setDouble(6, ped.getTotal());
            p.execute();
            sql="select max(idPedido) as ultimo from Pedido";
            ResultSet r=Conexion.con.createStatement().executeQuery(sql);
            if(r.next()){
                ped.setIdPedido(r.getInt("ultimo"));
                sql="insert into detalle_pedido(Articulo_idArticulo,Pedido_idPedido,cantidad,subtotal) values(?,?,?,?)";
                p = Conexion.con.prepareStatement(sql);
                for(DetallePedido linea:ped.getLista()){
                    p.setInt(1, linea.getArticulo().getIdArticulo());
                    p.setInt(2, ped.getIdPedido());
                    p.setInt(3, linea.getCantidad());
                    p.setDouble(4, linea.getSubtotal());
                    p.execute();
                    aumentar(linea.getArticulo().getIdArticulo(),linea.getCantidad());
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void agregarArticulo(Pedido ped, Articulo a, int cantidad){
        DetallePedido linea=new DetallePedido();
        linea.getArticulo().setCategoria(a.getCategoria());
        linea.getArticulo().setCodigo(a.getCodigo());
        linea.getArticulo().setDescripcionArticulo(a.getDescripcionArticulo());
        linea.getArticulo().setIdArticulo(a.getIdArticulo());
        linea.getArticulo().setPrecioCompra(a.getPrecioCompra());
        linea.getArticulo().setPrecioVenta(a.getPrecioVenta());
        linea.getArticulo().setStock(a.getStock());
        linea.setCantidad(cantidad);
        linea.setSubtotal(a.getPrecioVenta()*cantidad);
        ped.agregar(linea);
    }
    
    public void quitarLinea(Venta v,int index){
        v.getLista().remove(index);
    }

    private void aumentar(int idArticulo, int cantidad) {
         String sql="update articulo set "
                + "stock = stock+? "
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
    