/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package articulo;

import direccion.*;
import bd.Conexion;
import cliente.Cliente;
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
public class GestorArticulo {
    
    public void agregar(Articulo art){
        String sql="insert into articulo (codigo,descripcion,precio_venta,precio_compra,stock,Categoria_idCategoria) values(?,?,?,?,?,?)";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,art.getCodigo());
            p.setString(2,art.getDescripcionArticulo());
            p.setDouble(3,art.getPrecioVenta());
            p.setDouble(4,art.getPrecioCompra());
            p.setInt(5, art.getStock());
            p.setInt(6, art.getCategoria().getIdCategoria());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    
     public void modificar(Articulo art){
        String sql="update articulo set "
                + "codigo=?,"
                + "descripcion=?,"
                + "precio_venta=? , "
                + "precio_compra=?,"
                + "stock=? ,"
                + "Categoria_idCategoria=? "
                + "where idArticulo=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,art.getCodigo());
            p.setString(2,art.getDescripcionArticulo());
            p.setDouble(3,art.getPrecioVenta());
            p.setDouble(4,art.getPrecioCompra());
            p.setInt(5, art.getStock());
            p.setInt(6, art.getCategoria().getIdCategoria());
            p.setInt(7, art.getIdArticulo());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
     
     public void eliminar(Articulo art){
        String sql="delete from articulo where idArticulo=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,art.getIdArticulo());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

   public ArrayList getLista(Categoria cat) {
      String sql="select * from articulo where idArticulo=?";
      ArrayList lista=new ArrayList<>();
        try {
            PreparedStatement p=Conexion.con.prepareStatement(sql);
            p.setInt(1, cat.getIdCategoria());
            ResultSet rs=p.executeQuery();
            while(rs.next()){
                Articulo art=new Articulo();
                art.setIdArticulo(rs.getInt("idArticulo"));
                art.getCategoria().setIdCategoria(rs.getInt("Categoria_idCategoria"));
                art.setCodigo(rs.getInt("codigo"));
                art.setDescripcionArticulo(rs.getString("descripcion"));
                art.setPrecioVenta(rs.getDouble("precio_venta"));
                art.setPrecioCompra(rs.getDouble("precio_compra"));
                art.setStock(rs.getInt("stock"));
                art.setCategoria(cat);
                lista.add(art);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorLocalidad.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return lista;
    }
   
   public ArrayList getListaArticulo() {
      String sql="select articulo.idArticulo,articulo.codigo,articulo.descripcion,articulo.precio_venta,articulo.precio_compra,"
              + "categoria.idCategoria,categoria.descripcion_cat ,articulo.stock"
              +" from articulo,categoria "
              + "where articulo.Categoria_idCategoria=categoria.idCategoria ";
      ArrayList lista=new ArrayList<>();
        try {
            PreparedStatement p=Conexion.con.prepareStatement(sql);
            ResultSet rs=p.executeQuery();
            while(rs.next()){
                Articulo art=new Articulo();
                art.setIdArticulo(rs.getInt("idArticulo"));
                art.setCodigo(rs.getInt("codigo"));
                art.setDescripcionArticulo(rs.getString("descripcion"));
                art.setPrecioVenta(rs.getDouble("precio_venta"));
                art.setPrecioCompra(rs.getDouble("precio_compra"));
                art.setStock(rs.getInt("stock"));
                art.getCategoria().setIdCategoria(rs.getInt("idCategoria"));
                art.getCategoria().setDescripcion(rs.getString("descripcion_cat"));
                lista.add(art);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorLocalidad.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return lista;
    }

    void recuperar(Articulo articulo) {
        
        String sql="select articulo.idArticulo,articulo.codigo,articulo.descripcion,articulo.precio_venta,articulo.precio_compra,"
              + "categoria.idCategoria,categoria.descripcion_cat as categoria ,articulo.stock"
              + " from articulo,categoria "
              + "where articulo.Categoria_idCategoria=categoria.idCategoria "
              + "and articulo.idArticulo = ?";
      ArrayList lista=new ArrayList<>();
        try {
            PreparedStatement p=Conexion.con.prepareStatement(sql);
            p.setInt(1,articulo.getIdArticulo());
            ResultSet rs=p.executeQuery();
            while(rs.next()){
                articulo.setIdArticulo(rs.getInt("idArticulo"));
                articulo.setCodigo(rs.getInt("codigo"));
                articulo.setDescripcionArticulo(rs.getString("descripcion"));
                articulo.setPrecioVenta(rs.getDouble("precio_venta"));
                articulo.setPrecioCompra(rs.getDouble("precio_compra"));
                articulo.setStock(rs.getInt("stock"));
                articulo.getCategoria().setIdCategoria(rs.getInt("idCategoria"));
                lista.add(articulo);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorLocalidad.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    public Articulo buscarXCODIGO(int codigo) {
    Articulo articulo= null;
        String sql="select idArticulo, codigo, descripcion, precio_venta, precio_compra, stock "
                + "from articulo where codigo=?";
        try {
            PreparedStatement p=Conexion.con.prepareStatement(sql);
            
            p.setInt(1, codigo);
            ResultSet rs=p.executeQuery();
            if(rs.next()){
              articulo=new Articulo();
              articulo.setDescripcionArticulo(rs.getString("descripcion"));
              articulo.setPrecioVenta(rs.getDouble("precio_venta"));
              articulo.setPrecioCompra(rs.getDouble("precio_compra"));
              articulo.setCodigo(rs.getInt("codigo"));
              articulo.setStock(rs.getInt("stock"));
              articulo.setIdArticulo(rs.getInt("idArticulo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articulo;
    
    }
     
    
}
