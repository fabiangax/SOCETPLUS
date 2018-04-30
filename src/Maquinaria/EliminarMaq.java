/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maquinaria;

import Objetos.Maquinaria;
import Objetos.Utilidades;
import Otros.ConexionSQLServer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Marco
 */
public class EliminarMaq extends javax.swing.JDialog {
    Connection c = ConexionSQLServer.Conectar();
    String[] datosMaquinaria = new String[6];
    boolean valido=true;
    public EliminarMaq(String[] datosMaquinaria,java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        initComponents();
        setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/Miscelaneo/logo2.png")).getImage());
        this.datosMaquinaria = datosMaquinaria;
        txtSerie.setText(datosMaquinaria[0]);
        txtMarca.setText(datosMaquinaria[1]);
        txtModelo.setText(datosMaquinaria[2]);
        txtAnio.setText(datosMaquinaria[3]);
        txtPlacas.setText(datosMaquinaria[4]);
    }
    
    public ResultSet buscar(Maquinaria objCliente){
        try{
           Statement ST = c.createStatement();
           String sql = "Select * from MAQUINARIAS where IDMaquinaria='"+objCliente.getSerie()+"'";
           return ST.executeQuery(sql);
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public void eliminar(Maquinaria objCliente){
        try{
           Statement ST = c.createStatement();
           String sql = "Delete from MAQUINARIAS where IDMaquinaria='"+ objCliente.getSerie()+"'";
           ST.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println(e);
            valido=false;
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        idCliente = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        apellido = new javax.swing.JLabel();
        telefono = new javax.swing.JLabel();
        direccion = new javax.swing.JLabel();
        txtSerie = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        txtAnio = new javax.swing.JTextField();
        txtPlacas = new javax.swing.JTextField();
        Aceptar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        Titulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Eliminar maquinaria");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(239, 240, 240));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        idCliente.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente.setText("Serie:");

        nombre.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        nombre.setText("Marca:");

        txtMarca.setEnabled(false);
        txtMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMarcaActionPerformed(evt);
            }
        });
        txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMarcaKeyReleased(evt);
            }
        });

        apellido.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        apellido.setText("Modelo:");

        telefono.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        telefono.setText("Año:");

        direccion.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        direccion.setText("Placas:");

        txtSerie.setEnabled(false);
        txtSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSerieActionPerformed(evt);
            }
        });
        txtSerie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSerieKeyReleased(evt);
            }
        });

        txtModelo.setEnabled(false);
        txtModelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtModeloKeyReleased(evt);
            }
        });

        txtAnio.setEnabled(false);
        txtAnio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnioKeyReleased(evt);
            }
        });

        txtPlacas.setEnabled(false);
        txtPlacas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlacasKeyReleased(evt);
            }
        });

        Aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/trash.png"))); // NOI18N
        Aceptar.setText("Eliminar");
        Aceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarActionPerformed(evt);
            }
        });

        Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/error.png"))); // NOI18N
        Cancelar.setText("Cancelar");
        Cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });

        Titulo.setFont(new java.awt.Font("Cambria", 3, 24)); // NOI18N
        Titulo.setText("Eliminar maquinaria");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombre)
                    .addComponent(idCliente)
                    .addComponent(apellido)
                    .addComponent(telefono)
                    .addComponent(direccion))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Titulo)
                        .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(162, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(Titulo)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idCliente)
                    .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombre))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellido)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefono))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(direccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_CancelarActionPerformed

    private void AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarActionPerformed
        int res = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este vehículo?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
         if(res==0){
            Maquinaria objCliente = new Maquinaria(txtSerie.getText(),txtMarca.getText(),txtModelo.getText(),txtAnio.getText(),
                txtPlacas.getText());
            eliminar(objCliente);
            if(valido){
                JOptionPane.showMessageDialog(null, "El registro de maquinaria se ha eliminado correctamente", "Registro eliminado", JOptionPane.DEFAULT_OPTION);
            }
            else{
                JOptionPane.showMessageDialog(null, "Este vehículo se está utilizando en otro registro, no se puede eliminar.", "Aviso", JOptionPane.DEFAULT_OPTION);
            }
            valido=true;
            Utilidades uti = new Utilidades();
            uti.ClickEnMaquinaria();
            this.dispose();   
        }  
    }//GEN-LAST:event_AceptarActionPerformed

    private void txtSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSerieActionPerformed
        
    }//GEN-LAST:event_txtSerieActionPerformed

    private void txtSerieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSerieKeyReleased
//        if(txtID.getText().matches("[0-9]+")){
//            check5.setVisible(true);
//            cross5.setVisible(false);
//            ad1.setVisible(false);
//        }
//        else if(txtID.getText().equals("")){
//            cross5.setVisible(false);
//            check5.setVisible(false);
//            ad1.setVisible(false);
//        }
//        else{
//            cross5.setVisible(true);
//            check5.setVisible(false);
//            ad1.setVisible(true);
//        }
    }//GEN-LAST:event_txtSerieKeyReleased

    private void txtMarcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyReleased

    }//GEN-LAST:event_txtMarcaKeyReleased

    private void txtModeloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModeloKeyReleased

    }//GEN-LAST:event_txtModeloKeyReleased
    public static boolean isValidEmail(String email) {
        boolean result = true;
        try{
        InternetAddress emailAddr = new InternetAddress(email);
        emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    private void txtMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMarcaActionPerformed

    private void txtPlacasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacasKeyReleased
        
    }//GEN-LAST:event_txtPlacasKeyReleased

    private void txtAnioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnioKeyReleased
    
    }//GEN-LAST:event_txtAnioKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EliminarMaq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EliminarMaq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EliminarMaq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EliminarMaq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            EliminarMaq dialog = new EliminarMaq(null,new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aceptar;
    private javax.swing.JButton Cancelar;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel apellido;
    private javax.swing.JLabel direccion;
    private javax.swing.JLabel idCliente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel telefono;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtPlacas;
    private javax.swing.JTextField txtSerie;
    // End of variables declaration//GEN-END:variables
}