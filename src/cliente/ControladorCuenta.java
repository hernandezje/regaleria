/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author yohan
 */
public class ControladorCuenta implements ActionListener{
    
    private FrmCuenta vistaCuenta;
    private Cuenta cuenta;
    private GestorCuenta gestorCuenta;
    private Cliente cliente;
    private GestorCliente gestorCliente;

    public ControladorCuenta(JFrame padre){
        vistaCuenta=new FrmCuenta(padre, true);
        cuenta=new Cuenta();
        gestorCuenta=new GestorCuenta();
    }
    
    public void ejecutar(){
        vistaCuenta.limpiar();
        vistaCuenta.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()){
            case FrmCuenta.CUENTA_BUSCAR:
                buscar();
                break;
            case FrmCuenta.CUENTA_PAGAR:
                pagar();
                break;
            case FrmCuenta.CUENTA_SALIR:
                salir();
                break;
        }
    }

    private void buscar() {
        String dni=vistaCuenta.getcliente();
        cliente=gestorCliente.buscarClienteXDNI(dni);
        if (cliente==null)JOptionPane.showMessageDialog(vistaCuenta, "cliente no hallado");
        else {
            vistaCuenta.setDni(dni);
            vistaCuenta.setNombreCli(cliente.getNombre());
            vistaCuenta.setTelefono(cliente.getTelefono());
            vistaCuenta.setEstado(cuenta.getEstado());
            vistaCuenta.setNumCuenta(cuenta.getNum_cuenta());
            vistaCuenta.setSaldo(cuenta.getSaldo_deuda());
        }
    }

    private void pagar() {
    
    }

    private void salir() {
    
    }
    
}
