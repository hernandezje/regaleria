/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liquidacion;

import javax.swing.JDialog;
import net.sf.jasperreports.view.JasperViewer;
import regaleria.FrmMenu;

/**
 *
 * @author salazarwalter
 */
public class JDialogImpresion extends javax.swing.JDialog {

    /**
     * Creates new form JDialogImpresion
     */
    public JDialogImpresion(java.awt.Frame parent, JasperViewer jr) {
        super(parent, true);
        initComponents();
    }

    public JDialogImpresion(FrmMenu vistaBase, JasperViewer jrv) {
        super(vistaBase,true);
       this.getContentPane().add(jrv);
       this.setSize(950,700);
       this.setVisible(true);
        
    }
    public JDialogImpresion(JDialog vistaBase, JasperViewer jrv) {
        super(vistaBase,true);
       this.getContentPane().add(jrv);
       this.setSize(950,700);
      
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 934, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 551, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(944, 581));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
