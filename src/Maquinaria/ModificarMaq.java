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
public class ModificarMaq extends javax.swing.JDialog {
    Connection c = ConexionSQLServer.Conectar();
    String[] datosMaquinaria = new String[6];
    public ModificarMaq(String[] datosMaquinaria,java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        initComponents();
        setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/Miscelaneo/logo2.png")).getImage());
        check2.setVisible(true);
        check3.setVisible(true);
        check4.setVisible(true);
        check6.setVisible(true);
        cross1.setVisible(false);
        cross2.setVisible(false);
        cross4.setVisible(false);
        ad2.setVisible(false);
        ad4.setVisible(false);
        this.datosMaquinaria = datosMaquinaria;
        txtSerie.setText(datosMaquinaria[0]);
        txtMarca.setText(datosMaquinaria[1]);
        txtModelo.setText(datosMaquinaria[2]);
        txtAnio.setText(datosMaquinaria[3]);
        txtPlacas.setText(datosMaquinaria[4]);
    }

    private void insertarValor(){
        Maquinaria objCliente = new Maquinaria(txtSerie.getText(),txtMarca.getText(),txtModelo.getText(),txtAnio.getText(),
            txtPlacas.getText());
        ResultSet resultado = buscar(objCliente);
        try{
            if(resultado.next()){
                JOptionPane.showMessageDialog(this, "La Matricula ya existe","Aviso",JOptionPane.INFORMATION_MESSAGE);
            }else{
                insertar(objCliente);
                JOptionPane.showMessageDialog(this, "Se ha insertado correctamente el registro","Exito",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public void insertar(Maquinaria objCliente){
        try{
           Statement ST = c.createStatement();
           String sql = "Insert into MAQUINARIAS values('"+objCliente.getSerie()
                    +"','"+objCliente.getMarca()+"','"+objCliente.getModelo()
                    +"','"+objCliente.getAnio()+"','"+objCliente.getPlacas()+"')";
           ST.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println(e);
        }
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
    public void actualizar(Maquinaria objCliente){
        try{
           Statement ST = c.createStatement();
           String sql = "Update MAQUINARIAS set Marca='"+objCliente.getMarca()
                   +"', Modelo='"+objCliente.getModelo()
                   +"', Año='"+objCliente.getAnio()
                   +"', Placas='"+objCliente.getPlacas()
                   +"' where IDMaquinaria ='"+objCliente.getSerie()+"'";
           ST.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public void eliminar(Maquinaria objCliente){
        try{
           Statement ST = c.createStatement();
           String sql = "Delete from MAQUINARIAS where IDMaquinaria='"+ objCliente.getSerie()+"'";
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
        txtMarca = new javax.swing.JTextField();
        Modelo = new javax.swing.JLabel();
        telefono = new javax.swing.JLabel();
        direccion = new javax.swing.JLabel();
        txtSerie = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        txtAnio = new javax.swing.JTextField();
        txtPlacas = new javax.swing.JTextField();
        Aceptar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        Limpiar = new javax.swing.JButton();
        Titulo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        check6 = new javax.swing.JLabel();
        check4 = new javax.swing.JLabel();
        check3 = new javax.swing.JLabel();
        check2 = new javax.swing.JLabel();
        cross1 = new javax.swing.JLabel();
        cross2 = new javax.swing.JLabel();
        cross4 = new javax.swing.JLabel();
        ad2 = new javax.swing.JLabel();
        ad4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Modificar maquinaria");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(239, 240, 240));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        idCliente.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente.setText("Serie:");

        nombre.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        nombre.setText("Marca:");

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

        Modelo.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        Modelo.setText("Modelo:");

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

        txtModelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtModeloKeyReleased(evt);
            }
        });

        txtAnio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnioKeyReleased(evt);
            }
        });

        txtPlacas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlacasKeyReleased(evt);
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

        Limpiar.setText("Limpiar campos");
        Limpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });

        Titulo.setFont(new java.awt.Font("Cambria", 3, 24)); // NOI18N
        Titulo.setText("Modificar maquinaria");

        jPanel2.setBackground(new java.awt.Color(239, 240, 240));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        check6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        jPanel2.add(check6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        check4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        jPanel2.add(check4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        check3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        jPanel2.add(check3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        check2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        jPanel2.add(check2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        cross1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/cross.png"))); // NOI18N
        jPanel2.add(cross1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        cross2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/cross.png"))); // NOI18N
        jPanel2.add(cross2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        cross4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/cross.png"))); // NOI18N
        jPanel2.add(cross4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        ad2.setForeground(new java.awt.Color(102, 102, 102));
        ad2.setText("Sólo letras");
        jPanel2.add(ad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        ad4.setForeground(new java.awt.Color(102, 102, 102));
        ad4.setText("Sólo números");
        jPanel2.add(ad4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("*");

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("*");

        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("*");

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("*");

        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("* Campos obligatorios");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombre)
                            .addComponent(idCliente)
                            .addComponent(Modelo)
                            .addComponent(telefono)
                            .addComponent(direccion))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Titulo)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(Cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)))
                .addContainerGap())
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
                            .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombre)
                            .addComponent(jLabel2))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Modelo)
                            .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefono)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(direccion)
                            .addComponent(jLabel5)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
        if(!(txtMarca.getText().isEmpty() || txtModelo.getText().isEmpty() || txtAnio.getText().isEmpty() || txtPlacas.getText().isEmpty()) && (check6.isVisible() && check3.isVisible() && check4.isVisible() && check2.isVisible())){
            int res = JOptionPane.showConfirmDialog(null, "¿Confirmar registro?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if(res==0){
                Maquinaria objCliente = new Maquinaria(txtSerie.getText(),txtMarca.getText(),txtModelo.getText(),txtAnio.getText(),
                    txtPlacas.getText());
                actualizar(objCliente);
                JOptionPane.showMessageDialog(null, "Registro de maquinaria actualizado correctamente", "Registro actualizado", JOptionPane.DEFAULT_OPTION);
                Utilidades uti = new Utilidades();
                uti.ClickEnMaquinaria();
                this.dispose();  
            }   
        }
        else{
            if(txtSerie.getText().isEmpty() && txtMarca.getText().isEmpty() && txtModelo.getText().isEmpty() && txtAnio.getText().isEmpty() && txtPlacas.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Hay campos vacíos. Favor de llenar los campos faltantes y vuelva a intentarlo.", "Campos vacíos", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Hay campos no válidos. Favor de revisar la información y vuelva a intentarlo", "Campos inválidos", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
    }//GEN-LAST:event_AceptarActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        txtSerie.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtAnio.setText("");
        txtPlacas.setText("");
        check2.setVisible(false);
        check3.setVisible(false);
        check4.setVisible(false);
        check6.setVisible(false);
        cross1.setVisible(false);
        cross2.setVisible(false);
        cross4.setVisible(false);
        ad2.setVisible(false);
        ad4.setVisible(false);
    }//GEN-LAST:event_LimpiarActionPerformed

    private void txtSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSerieActionPerformed
        
    }//GEN-LAST:event_txtSerieActionPerformed

    private void txtSerieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSerieKeyReleased

    }//GEN-LAST:event_txtSerieKeyReleased

    private void txtMarcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyReleased
        if(txtMarca.getText().matches("[a-zA-Z ]+")){
            cross4.setVisible(false);
            check6.setVisible(true);
            ad2.setVisible(false);
        }
        else if(txtMarca.getText().equals("")){
            cross4.setVisible(false);
            check6.setVisible(false);
            ad2.setVisible(false);
        }
        else{
            check6.setVisible(false);
            cross4.setVisible(true);
            ad2.setVisible(true);
        }
    }//GEN-LAST:event_txtMarcaKeyReleased

    private void txtModeloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModeloKeyReleased
        if(!txtModelo.getText().isEmpty()){
            check3.setVisible(true);
        }
        else{
            check3.setVisible(false);
        }
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
        if(!txtPlacas.getText().equals("")){
            check2.setVisible(true);
        }
        else{
            check2.setVisible(false);
        }
    }//GEN-LAST:event_txtPlacasKeyReleased

    private void txtAnioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnioKeyReleased
        if(txtAnio.getText().matches("[0-9]+")){
            check4.setVisible(true);
            cross2.setVisible(false);
            ad4.setVisible(false);
        }
        else if(txtAnio.getText().equals("")){
            cross2.setVisible(false);
            check4.setVisible(false);
            ad4.setVisible(false);
        }
        else{
            cross2.setVisible(true);
            check4.setVisible(false);
            ad4.setVisible(true);
        }
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
            java.util.logging.Logger.getLogger(ModificarMaq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificarMaq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificarMaq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificarMaq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            ModificarMaq dialog = new ModificarMaq(null,new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel Modelo;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel ad2;
    private javax.swing.JLabel ad4;
    private javax.swing.JLabel check2;
    private javax.swing.JLabel check3;
    private javax.swing.JLabel check4;
    private javax.swing.JLabel check6;
    private javax.swing.JLabel cross1;
    private javax.swing.JLabel cross2;
    private javax.swing.JLabel cross4;
    private javax.swing.JLabel direccion;
    private javax.swing.JLabel idCliente;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel telefono;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtPlacas;
    private javax.swing.JTextField txtSerie;
    // End of variables declaration//GEN-END:variables
}
