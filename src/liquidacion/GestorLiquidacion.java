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
public class GestorLiquidacion {

    void agregar(Liquidacion li) {
        String sql="insert into liquidacion(Empleado_idEmpleado,fecha_desde,fecha_hasta,sueldo_neto,aporte,retenciones,periodo_liq) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,li.getEmpleado().getIdEmpleado());
            p.setDate(2,  new java.sql.Date(li.getFecha_desde().getTime()));
            p.setDate(3,  new java.sql.Date(li.getFecha_hasta().getTime()));
            p.setDouble(4, li.getSueldo_neto());
            p.setDouble(5, li.getAportes());
            p.setDouble(6, li.getRetenciones());
            p.setString(7,li.getPeriodo_liq());
//            p.setInt(8, li.getIdLiquidacion());
             p.execute();
             sql="select max(idLiquidacion)as ultimo from liquidacion";
             ResultSet r=Conexion.con.createStatement().executeQuery(sql);
             if(r.next()){
                 li.setIdLiquidacion(r.getInt("ultimo"));
             }
             sql="insert into detalle_liquidacion (Liquidacion_idLiquidacion, concepto_idConcepto, cantidad, suma, resto,valor) values (?,?,?,?,?,?)";
            p = Conexion.con.prepareStatement(sql);
             for(DetalleLiquidacion d:li.getLista()){
             p.setInt(1, li.getIdLiquidacion());
             p.setInt(2, d.getConcepto().getIdConcepto());
             p.setInt(3, d.getCantidad());
             p.setDouble(4, d.getSuma());
             p.setDouble(5, d.getResto());
             p.setDouble(6, d.getValor());
             p.execute();
            }
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
      
    }

    void modificar(Liquidacion li) {
        String sql="update concepto set "
                + "Empleado_idEmpleado=?,"
                + "Fecha_desde=?,"
                + "Fecha_hasta=? , "
                + "sueldo_neto=?,"
                + "aporte=?,"
                + "retenciones=?,"
                + "periodo_liq=?"
                + "where idLiquidacion=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(0, li.getIdLiquidacion());
            p.setInt(1,li.getEmpleado().getIdEmpleado());
            p.setDate(2,  new java.sql.Date(li.getFecha_desde().getTime()));
            p.setDate(3,  new java.sql.Date(li.getFecha_hasta().getTime()));
            p.setDouble(4, li.getSueldo_neto());
            p.setDouble(5, li.getAportes());
            p.setDouble(6, li.getRetenciones());
            p.setString(7,li.getPeriodo_liq());
            
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    
    }

    void eliminar(Liquidacion li) {
        String sql="delete from liquidacion where idLiquidacion=?";
        try {
            PreparedStatement p = Conexion.con.prepareStatement(sql);
            p.setInt(1,li.getIdLiquidacion());
            p.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    
    }

    public ArrayList<Liquidacion> getLista() {
        String sql="select * from liquidacion";
      ArrayList<Liquidacion> lista=new ArrayList<>();
        try {
            ResultSet rs=Conexion.con.createStatement().executeQuery(sql);
            while(rs.next()){
                Liquidacion li=new Liquidacion();
                li.setIdLiquidacion(rs.getInt("idLiquidacion"));
                li.setFecha_desde(rs.getDate("fecha_desde"));
                li.setFecha_hasta(rs.getDate("fecha_hasta"));
                li.setSueldo_neto(rs.getDouble("saldo_neto"));
                li.setAportes(rs.getDouble("aporte"));
                li.setRetenciones(rs.getDouble("retenciones"));
                li.setPeriodo_liq(rs.getString("periodo_liq"));
                lista.add(li);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(GestorLiquidacion.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return lista;
    
    }
    
}
