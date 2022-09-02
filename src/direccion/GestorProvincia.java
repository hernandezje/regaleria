/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package direccion;

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
public class GestorProvincia {
    
    public void agregar(Provincia prov){
        String sql="insert into Provincia (nombre) values(?)";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setString(1,prov.getNombreProv());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    
     public void modificar(Provincia prov){
        String sql="update Provincia set "
                + "nombre=? "
                + "where idProvincia=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setString(1,prov.getNombreProv());
            p.setInt(2, prov.getIdProv());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
     
     public void eliminar(Provincia prov){
        String sql="delete from Provincia where idProvincia=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,prov.getIdProv());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

   public ArrayList<Provincia> getLista() {
      String sql="select * from provincia";
      ArrayList<Provincia> lista=new ArrayList<>();
        try {
            ResultSet rs=Conexion.con.createStatement().executeQuery(sql);
            while(rs.next()){
                Provincia pro=new Provincia();
                pro.setIdProv(rs.getInt("idProvincia"));
                pro.setNombreProv(rs.getString("nombre"));
                lista.add(pro);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorProvincia.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return lista;
    }
     
    
}
