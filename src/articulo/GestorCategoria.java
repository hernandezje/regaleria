/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package articulo;

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
public class GestorCategoria {
    
    public void agregar(Categoria cat){
        String sql="insert into categoria (descripcion_cat) values(?)";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setString(1,cat.getDescripcion());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    
     public void modificar(Categoria cat){
        String sql="update categoria set "
                + "descripcion_cat=? "
                + "where idCategoria=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setString(1,cat.getDescripcion());
            p.setInt(2, cat.getIdCategoria());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
     
     public void eliminar(Categoria cat){
        String sql="delete from categoria where idCategoria=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,cat.getIdCategoria());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

   public ArrayList<Categoria> getLista() {
      String sql="select * from categoria";
      ArrayList<Categoria> lista=new ArrayList<>();
        try {
            ResultSet rs=Conexion.con.createStatement().executeQuery(sql);
            while(rs.next()){
                Categoria cat=new Categoria();
                cat.setIdCategoria(rs.getInt("idCategoria"));
                cat.setDescripcion(rs.getString("descripcion_cat"));
                lista.add(cat);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorCategoria.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return lista;
    }
     
    
}
