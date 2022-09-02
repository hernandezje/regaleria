/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liquidacion;

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
public class GestorConcepto {

    void agregar(Concepto conc) {
        String sql="insert into concepto (tipo,valor,suma,porcentual) values(?,?,?,?)";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setString(1,conc.getTipo());
            p.setDouble(2, conc.getValor());
            p.setBoolean(3, conc.isSuma());
            p.setBoolean(4, conc.isPorcentual());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
      
    }

    void modificar(Concepto conc) {
        String sql="update concepto set "
                + "tipo=?,"
                + "valor=?,"
                + "suma=? , "
                + "porcentual=? "
                + "where idConcepto=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setString(1,conc.getTipo());
            p.setDouble(2,conc.getValor());
            p.setBoolean(3,conc.isSuma());
            p.setBoolean(4,conc.isPorcentual());
            p.setInt(5,conc.getIdConcepto());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    
    }

    void eliminar(Concepto concepto) {
        String sql="delete from concepto where idConcepto=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,concepto.getIdConcepto());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    
    }

    public ArrayList<Concepto> getLista() {
        String sql="select * from concepto";
      ArrayList<Concepto> lista=new ArrayList<>();
        try {
            ResultSet rs=Conexion.con.createStatement().executeQuery(sql);
            while(rs.next()){
                Concepto conc=new Concepto();
                conc.setIdConcepto(rs.getInt("idConcepto"));
                conc.setTipo(rs.getString("tipo"));
                conc.setValor(rs.getDouble("valor"));
                conc.setSuma(rs.getBoolean("suma"));
                conc.setPorcentual(rs.getBoolean("porcentual"));
                lista.add(conc);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorConcepto.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return lista;
    
    }
    
}
