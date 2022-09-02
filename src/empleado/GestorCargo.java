/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleado;

import articulo.GestorCategoria;
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
 * @author yohan
 */
public class GestorCargo {
    
    public void agregar(Cargo car){
        String sql="insert into cargo (descripcion,salario) values(?,?)";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setString(1,car.getDescripcionCargo());
            p.setDouble(2,car.getSalario());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void modificar(Cargo car){
        String sql="update Cargo set "
                + "descripcion=? ,"
                + "salario=? "
                + "where idCargo=? ";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setString(1, car.getDescripcionCargo());
            p.setDouble(2, car.getSalario());
            p.setInt(3, car.getIdCargo());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void eliminar(Cargo car){
        String sql="delete from cargo where idCargo=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,car.getIdCargo());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
     public ArrayList<Cargo> getListaCargo() {
      String sql="select * from cargo";
      ArrayList<Cargo> listaCargo=new ArrayList<>();
        try {
            ResultSet rs=Conexion.con.createStatement().executeQuery(sql);
            while(rs.next()){
                Cargo car=new Cargo();
                car.setIdCargo(rs.getInt("idCargo"));
                car.setDescripcionCargo(rs.getString("descripcion"));
                car.setSalario(rs.getDouble("salario"));
                listaCargo.add(car);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorCategoria.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return listaCargo;
    }
     
}
