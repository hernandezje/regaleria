/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regaleria;

import bd.Conexion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author YOHANA HERNANDEZ
 */
public class Regaleria {

    public static String REPORTE_LIQUI="D:\\NetBeansProjects\\Regaleria\\src\\liquidacion\\Liquidacion.jrxml";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            new Conexion().conectar();
            new Controlador().ejecutar();
        } catch (ClassNotFoundException ex) {
           JOptionPane.showMessageDialog(null, "Debe Agregar las Librerias");
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
}
