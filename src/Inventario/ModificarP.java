/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventario;

import Objetos.Producto;
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
public class ModificarP extends javax.swing.JDialog {
    Connection c = ConexionSQLServer.Conectar();
    String[] datosProducto = new String[4];
    public ModificarP(String[] datosProducto, java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        initComponents();
        setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/Miscelaneo/logo2.png")).getImage());
        check3.setVisible(true);
        check4.setVisible(true);
        check6.setVisible(true);
        cross2.setVisible(false);
        cross3.setVisible(false);
        cross4.setVisible(false);
        ad2.setVisible(false);
        ad3.setVisible(false);
        ad4.setVisible(false);  
        this.datosProducto = datosProducto;
        txtCodigo.setText(datosProducto[0]);
        txtDescripcion.setText(datosProducto[1]);
        txtExistencia.setText(datosProducto[2]);
        txtPrecio.setText(datosProducto[3]);
    }
    private ModificarP() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void insertar(Producto objCliente){
        try{
           Statement ST = c.createStatement();
           String sql = "Insert into PRODUCTOS values('"+objCliente.getCodigo()
                    +"','"+objCliente.getDescripcion()+"','"+objCliente.getExistencia()
                    +"','"+objCliente.getPrecio()+"')";
           ST.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public ResultSet buscar(Producto objCliente){
        try{
           Statement ST = c.createStatement();
           String sql = "Select * from PRODUCTOS where IDProducto='"+objCliente.getCodigo()+"'";
           return ST.executeQuery(sql);
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    public void actualizar(Producto objCliente){
        try{
           Statement ST = c.createStatement();
           String sql = "Update PRODUCTOS set Descripcion='"+objCliente.getDescripcion()
                   +"', Existencia='"+objCliente.getExistencia()
                   +"', Precio='"+objCliente.getPrecio()
                   +"' where IDProducto ='"+objCliente.getCodigo()+"'";
           ST.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public void eliminar(Producto objCliente){
        try{
           Statement ST = c.createStatement();
           String sql = "Delete from PRODUCTOS where IDProducto='"+ objCliente.getCodigo()+"'";
           ST.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println(e);
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
        txtDescripcion = new javax.swing.JTextField();
        apellido = new javax.swing.JLabel();
        telefono = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtExistencia = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        Aceptar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        Limpiar = new javax.swing.JButton();
        Titulo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        check6 = new javax.swing.JLabel();
        check4 = new javax.swing.JLabel();
        check3 = new javax.swing.JLabel();
        cross2 = new javax.swing.JLabel();
        cross3 = new javax.swing.JLabel();
        cross4 = new javax.swing.JLabel();
        ad2 = new javax.swing.JLabel();
        ad3 = new javax.swing.JLabel();
        ad4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Modificar producto");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(239, 240, 240));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        idCliente.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente.setText("Código:");

        nombre.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        nombre.setText("Descripción:");

        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });

        apellido.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        apellido.setText("Existencia:");

        telefono.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        telefono.setText("Precio:");

        txtCodigo.setEnabled(false);
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        txtExistencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtExistenciaKeyReleased(evt);
            }
        });

        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioKeyReleased(evt);
            }
        });

        Aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/approve-circular-button (1).png"))); // NOI18N
        Aceptar.setText("Aceptar");
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

        Limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/broom.png"))); // NOI18N
        Limpiar.setText("Limpiar campos");
        Limpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });

        Titulo.setFont(new java.awt.Font("Cambria", 3, 24)); // NOI18N
        Titulo.setText("Modificar producto");

        jPanel2.setBackground(new java.awt.Color(239, 240, 240));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        check6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        jPanel2.add(check6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        check4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        jPanel2.add(check4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        check3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        jPanel2.add(check3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        cross2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/cross.png"))); // NOI18N
        jPanel2.add(cross2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        cross3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/cross.png"))); // NOI18N
        jPanel2.add(cross3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        cross4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/cross.png"))); // NOI18N
        jPanel2.add(cross4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        ad2.setForeground(new java.awt.Color(102, 102, 102));
        ad2.setText("Sólo letras");
        jPanel2.add(ad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        ad3.setForeground(new java.awt.Color(102, 102, 102));
        ad3.setText("Sólo números");
        jPanel2.add(ad3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        ad4.setForeground(new java.awt.Color(102, 102, 102));
        ad4.setText("Sólo números");
        jPanel2.add(ad4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("* Campos obligatorios");

        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("*");

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("*");

        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("*");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idCliente)
                            .addComponent(apellido)
                            .addComponent(telefono)
                            .addComponent(nombre))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(Cancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Aceptar))
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Titulo)
                .addGap(218, 218, 218))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(Titulo)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idCliente)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombre)
                            .addComponent(jLabel4))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(apellido)
                            .addComponent(txtExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefono)
                            .addComponent(jLabel6)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Limpiar)
                    .addComponent(jLabel8))
                .addContainerGap())
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
       if(!(txtDescripcion.getText().isEmpty() || txtExistencia.getText().isEmpty() || txtPrecio.getText().isEmpty()) && (check6.isVisible() && check3.isVisible() && check4.isVisible())){
            int res = JOptionPane.showConfirmDialog(null, "¿Confirmar registro?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if(res==0){
                Producto objCliente = new Producto(txtCodigo.getText(),txtDescripcion.getText(),txtExistencia.getText(),txtPrecio.getText());
                actualizar(objCliente);
                JOptionPane.showMessageDialog(null, "Registro de inventario actualizado correctamente", "Registro actualizado", JOptionPane.DEFAULT_OPTION);
                Utilidades uti = new Utilidades();
                uti.ClickEnInventario();
                this.dispose();  
            }   
        }
        else{
            if(txtCodigo.getText().isEmpty() && txtDescripcion.getText().isEmpty() && txtExistencia.getText().isEmpty() && txtPrecio.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Hay campos vacíos. Favor de llenar los campos faltantes y vuelva a intentarlo.", "Campos vacíos", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Hay campos no válidos. Favor de revisar la información y vuelva a intentarlo", "Campos inválidos", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_AceptarActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        txtDescripcion.setText("");
        txtExistencia.setText("");
        txtPrecio.setText("");
        check3.setVisible(false);
        check4.setVisible(false);
        check6.setVisible(false);
        cross2.setVisible(false);
        cross3.setVisible(false);
        cross4.setVisible(false);
        ad2.setVisible(false);
        ad3.setVisible(false);
        ad4.setVisible(false);
    }//GEN-LAST:event_LimpiarActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased

    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        if(txtDescripcion.getText().matches("[a-zA-Z ]+")){
            cross4.setVisible(false);
            check6.setVisible(true);
            ad2.setVisible(false);
        }
        else if(txtDescripcion.getText().equals("")){
            cross4.setVisible(false);
            check6.setVisible(false);
            ad2.setVisible(false);
        }
        else{
            check6.setVisible(false);
            cross4.setVisible(true);
            ad2.setVisible(true);
        }
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void txtExistenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExistenciaKeyReleased
        if(txtExistencia.getText().matches("[0-9]+")){
            cross3.setVisible(false);
            check3.setVisible(true);
            ad3.setVisible(false);
        }
        else if(txtExistencia.getText().equals("")){
            cross3.setVisible(false);
            check3.setVisible(false);
            ad3.setVisible(false);
        }
        else{
            check3.setVisible(false);
            cross3.setVisible(true);
            ad3.setVisible(true);
        }
    }//GEN-LAST:event_txtExistenciaKeyReleased
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
    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void txtPrecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyReleased
        if(txtPrecio.getText().matches("[0-9]+")){
            check4.setVisible(true);
            cross2.setVisible(false);
            ad4.setVisible(false);
        }
        else if(txtPrecio.getText().equals("")){
            cross2.setVisible(false);
            check4.setVisible(false);
            ad4.setVisible(false);
        }
        else{
            cross2.setVisible(true);
            check4.setVisible(false);
            ad4.setVisible(true);
        }
    }//GEN-LAST:event_txtPrecioKeyReleased

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
            java.util.logging.Logger.getLogger(ModificarP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificarP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificarP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificarP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
            ModificarP dialog = new ModificarP(null, new javax.swing.JFrame(), true);
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
    private javax.swing.JButton Limpiar;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel ad2;
    private javax.swing.JLabel ad3;
    private javax.swing.JLabel ad4;
    private javax.swing.JLabel apellido;
    private javax.swing.JLabel check3;
    private javax.swing.JLabel check4;
    private javax.swing.JLabel check6;
    private javax.swing.JLabel cross2;
    private javax.swing.JLabel cross3;
    private javax.swing.JLabel cross4;
    private javax.swing.JLabel idCliente;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel telefono;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtExistencia;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
