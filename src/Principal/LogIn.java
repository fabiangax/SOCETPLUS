/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Otros.ConexionSQLServer;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LogIn extends javax.swing.JFrame {
    Connection c = ConexionSQLServer.Conectar();
    public static int level;
    public LogIn() {
        try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
    }
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/Miscelaneo/logo2.png")).getImage());
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        lblPass = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        txtUsuario = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        btnEntrar = new javax.swing.JButton();
        jScrollPane38 = new javax.swing.JScrollPane();
        txtADetalles = new javax.swing.JTextArea();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Iniciar sesión");
        setMinimumSize(new java.awt.Dimension(500, 340));
        setResizable(false);

        panelFondo.setMinimumSize(new java.awt.Dimension(500, 340));
        panelFondo.setPreferredSize(new java.awt.Dimension(500, 340));
        panelFondo.setLayout(null);

        lblPass.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblPass.setForeground(new java.awt.Color(255, 255, 255));
        lblPass.setText("Contraseña");
        lblPass.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPassMouseClicked(evt);
            }
        });
        panelFondo.add(lblPass);
        lblPass.setBounds(550, 230, 90, 30);

        lblUsuario.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("Usuario");
        lblUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUsuarioMouseClicked(evt);
            }
        });
        panelFondo.add(lblUsuario);
        lblUsuario.setBounds(580, 180, 60, 30);

        txtPass.setBorder(null);
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
        });
        panelFondo.add(txtPass);
        txtPass.setBounds(650, 230, 190, 30);

        txtUsuario.setBorder(null);
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
        });
        panelFondo.add(txtUsuario);
        txtUsuario.setBounds(650, 180, 190, 30);

        btnSalir.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/logout.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        panelFondo.add(btnSalir);
        btnSalir.setBounds(30, 390, 90, 30);

        btnEntrar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        btnEntrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/login.png"))); // NOI18N
        btnEntrar.setText("Iniciar sesión");
        btnEntrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        panelFondo.add(btnEntrar);
        btnEntrar.setBounds(700, 280, 140, 30);

        jScrollPane38.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane38.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtADetalles.setBackground(new java.awt.Color(24, 84, 144));
        txtADetalles.setColumns(20);
        txtADetalles.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        txtADetalles.setForeground(new java.awt.Color(255, 255, 255));
        txtADetalles.setRows(5);
        txtADetalles.setText("SOCET+ Ver 1.01\n©2017 Universo7Dev");
        txtADetalles.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane38.setViewportView(txtADetalles);

        panelFondo.add(jScrollPane38);
        jScrollPane38.setBounds(740, 390, 120, 40);

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/blueprint.png"))); // NOI18N
        panelFondo.add(bg);
        bg.setBounds(0, 0, 870, 440);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void inicioSesion(){
        if(txtPass.getText().equals("") || txtUsuario.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Ha dejado campos vacíos","Campos vacío",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            try {
                Statement ST = c.createStatement();
                String sql = "Select * from USUARIOS WHERE Usuario='"+txtUsuario.getText()+"'"+" AND Contraseña='"+txtPass.getText()+"'";
                ResultSet res = ST.executeQuery(sql);
                if (res.next()) {
                    if(res.getString("Nivel").equals("0")){
                        level=0;
                    }
                    else{
                        level=1;
                    }
                    Home nc = new Home();
                    nc.setVisible(true);
                    nc.setLocationRelativeTo(null);
                    this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "El usuario y/o contraseña son incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "El sistema no se ha podido conectar a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void lblPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPassMouseClicked
//        String usuario=TextUsuario.getText();
//        String contraseña =jPasswordField1.getText();
//        System.out.println(contraseña);
//        if(!usuario.equals("") && !contraseña.equals("")){
//            if(TextUsuario.getText().equals("User")){
//                Interfaz nc= new Interfaz();
//                nc.setVisible(true);
//                nc.setLocationRelativeTo(null);
//                this.dispose(); 
//            }
//            else{
//                JOptionPane.showMessageDialog(null, "El usuario y/o contraseña son incorrectos","ERROR",JOptionPane.ERROR_MESSAGE);
//                TextUsuario.setText("");
//                jPasswordField1.setText("");
//            }
//        }
//        else{
//        
//            JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
//        }
//            inicioSesion();
    }//GEN-LAST:event_lblPassMouseClicked

    private void lblUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsuarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblUsuarioMouseClicked

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        inicioSesion();
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
           inicioSesion(); 
        }
    }//GEN-LAST:event_txtPassKeyPressed

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
           inicioSesion(); 
        }
    }//GEN-LAST:event_txtUsuarioKeyPressed

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
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JScrollPane jScrollPane38;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JTextArea txtADetalles;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}