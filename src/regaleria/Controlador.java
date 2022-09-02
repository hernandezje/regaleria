/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regaleria;

import empleado.Cargo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import liquidacion.Concepto;
import liquidacion.ControladorLiquidacion;

/**
 *
 * @author YOHANA HERNANDEZ
 */
public class Controlador implements ActionListener{
private FrmMenu vista;
private FrmLogin login;

    public Controlador() {
       vista =new FrmMenu();
       vista.pasarControlador(this);
       login=new FrmLogin(vista, true);
       login.pasarControlador(this);
    }

    public void ejecutar(){
        vista.setVisible(true);
        login.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch(ae.getActionCommand()){
            case FrmLogin.LOGIN_ACEPTAR:
                GestorLogin gestorLogin=new GestorLogin();
                boolean encontrado=gestorLogin.ingresar(login.getUsuario(), login.getClave());
                if(!encontrado){
                    JOptionPane.showMessageDialog(vista, "Clave Incorrecta");
                }
                else{
                    login.setVisible(false);
                   if(GestorLogin.descripcion.equalsIgnoreCase("adm")){
                       vista.menuAdm();
                   }
                   else  if(GestorLogin.descripcion.equalsIgnoreCase("empleado")){
                      vista.menuEmpleado();
                   }
                }
                break;
            case FrmLogin.LOGIN_CANCELAR:
                System.exit(0);
                break;
            case FrmMenu.PROVINCIA:
                new direccion.ControladorProvincia(vista).ejecutar();
                break;
            case FrmMenu.LOCALIDAD:
                new direccion.ControladorLocalidad(vista).ejecutar();
                break;
            case FrmMenu.CATEGORIA:
                new articulo.ControladorCategoria(vista).ejecutar();
                break;
            case FrmMenu.ARTICULO:
                new articulo.ControladorArticulo(vista).ejecutar();
                break;
            case FrmMenu.CARGO:
                new empleado.ControladorCargo(vista).ejecutar();
                break;
            case FrmMenu.EMPLEADO:
                new empleado.ControladorEmpleado(vista).ejecutar();
                break;
            case FrmMenu.CONCEPTO:
                new liquidacion.ControladorConcepto(vista).ejecutar();
                break;
            case FrmMenu.FAMILIAR:
                new empleado.ControladorFamiliar(vista).ejecutar();
                break;
            case FrmMenu.PROVEEDOR:
                new proveedor.ControladorProveedor(vista).ejecutar();
                break;
            case FrmMenu.CLIENTE:
                new cliente.ControladorCliente(vista).ejecutar();
                break;
            case FrmMenu.VENTA:
                new venta.ControladorVenta(vista).ejecutar();
                break;
            case FrmMenu.PEDIDO:
                new pedido.ControladorPedido(vista).ejecutar();
                break;
            case FrmMenu.LIQUIDACION:
                new ControladorLiquidacion(vista).ejecutar();
                break;
            case FrmMenu.MENU_SALIR:
                salir();
                break;
            case FrmMenu.MENU_CERRAR_SESSION:
                cerrar();
                break;
           
    }
    }

    private void salir() {
            System.exit(0);
            
    }

    private void cerrar() {
            vista.setVisible(false);
            login.limpiar();
            ejecutar();
    }
    
}
