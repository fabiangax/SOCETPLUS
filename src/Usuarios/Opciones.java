/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

import Otros.ConexionSQLServer;
import Principal.LogIn;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marco
 */
public class Opciones extends javax.swing.JDialog {
    Color TX1 = new Color(0, 0, 0);
    Color TX2 = new Color(255, 255, 255);
    Color BG1 = new Color(237, 192, 0);
    Color BG2 = new Color(54, 57, 62);
    String titulosUsuarios[] = {"Nombre", "Tipo de usuario", "Fecha en que se agregó"};
    DefaultTableModel DTM;
    Connection c = ConexionSQLServer.Conectar();
    String[] datosUsuarios = new String[3];
    public Opciones(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        initComponents();
        setLocationRelativeTo(null);
        refrescarUsuarios();
        bienPass.setVisible(false);
        malPass.setVisible(false);
        bienUser.setVisible(false);
        avisoPass.setVisible(false);
        malUser.setVisible(false);
        avisoUser.setVisible(false);
        if(LogIn.level==1){
            tabbedConfig.setEnabledAt(1, false);
        }
        try {
            File file = new File("C:\\Users\\mario\\OneDrive\\Documentos\\NetBeansProjects\\SOCET+\\config.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st=br.readLine();
            //System.out.println("ST:"+st);
            br.close();
            String[] rgbs = st.split(",");
            TX1=new Color(Integer.parseInt(rgbs[0]),Integer.parseInt(rgbs[1]),Integer.parseInt(rgbs[2]));
            TX2=new Color(Integer.parseInt(rgbs[3]),Integer.parseInt(rgbs[4]),Integer.parseInt(rgbs[5]));
            BG1=new Color(Integer.parseInt(rgbs[6]),Integer.parseInt(rgbs[7]),Integer.parseInt(rgbs[8]));
            BG2=new Color(Integer.parseInt(rgbs[9]),Integer.parseInt(rgbs[10]),Integer.parseInt(rgbs[11]));
            jLabel2.setForeground(TX1);
            jLabel1.setForeground(TX2);
            jLabel3.setForeground(BG1);
            jLabel4.setForeground(BG2);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        tabbedConfig = new javax.swing.JTabbedPane();
        panelConfiguracion = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        panelUsuarios = new javax.swing.JPanel();
        lblAñadir = new javax.swing.JLabel();
        txtRegisUsuario = new javax.swing.JTextField();
        lblPass = new javax.swing.JLabel();
        txtRegisPass = new javax.swing.JPasswordField();
        lblUser = new javax.swing.JLabel();
        cbAdmin = new javax.swing.JCheckBox();
        btnRegistrar = new javax.swing.JButton();
        lblRegistrados = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        btnEliminarUsuario = new javax.swing.JButton();
        bienPass = new javax.swing.JLabel();
        malPass = new javax.swing.JLabel();
        bienUser = new javax.swing.JLabel();
        avisoPass = new javax.swing.JLabel();
        malUser = new javax.swing.JLabel();
        avisoUser = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Configuración");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tabbedConfig.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabbedConfig.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        tabbedConfig.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedConfigStateChanged(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Color Texto Principal");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Color Texto Secundario");

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Color Fondo Principal");

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setText("Color Fondo Secundario");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/error.png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/save.png"))); // NOI18N
        jButton2.setText("Guardar Cambios");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/artist.png"))); // NOI18N
        jButton3.setText("Seleccionar Color");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        jLabel1.setForeground(TX2);
        jLabel1.setText("■");
        jLabel2.setForeground(TX2);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        jLabel2.setForeground(TX1);
        jLabel2.setText("■");
        jLabel2.setForeground(TX1);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        jLabel3.setForeground(BG1);
        jLabel3.setText("■");
        jLabel3.setForeground(BG1);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        jLabel4.setForeground(BG2);
        jLabel4.setText("■");
        jLabel4.setForeground(BG2);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/settings.png"))); // NOI18N
        jButton4.setText("Colores Predeterminados");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelConfiguracionLayout = new javax.swing.GroupLayout(panelConfiguracion);
        panelConfiguracion.setLayout(panelConfiguracionLayout);
        panelConfiguracionLayout.setHorizontalGroup(
            panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConfiguracionLayout.createSequentialGroup()
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelConfiguracionLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelConfiguracionLayout.createSequentialGroup()
                                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton2)
                                    .addComponent(jRadioButton1)
                                    .addComponent(jRadioButton3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelConfiguracionLayout.createSequentialGroup()
                                .addComponent(jRadioButton4)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panelConfiguracionLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addGap(22, 22, 22))
        );
        panelConfiguracionLayout.setVerticalGroup(
            panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfiguracionLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addGroup(panelConfiguracionLayout.createSequentialGroup()
                        .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton4)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 386, Short.MAX_VALUE)
                .addGroup(panelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addGap(14, 14, 14))
        );

        tabbedConfig.addTab("Configuración", panelConfiguracion);

        panelUsuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAñadir.setFont(new java.awt.Font("Segoe UI Light", 0, 28)); // NOI18N
        lblAñadir.setText("Añadir usuario");
        panelUsuarios.add(lblAñadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        txtRegisUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRegisUsuarioKeyReleased(evt);
            }
        });
        panelUsuarios.add(txtRegisUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 69, 163, 36));

        lblPass.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        lblPass.setText("Contraseña");
        panelUsuarios.add(lblPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 119, 88, -1));

        txtRegisPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRegisPassKeyReleased(evt);
            }
        });
        panelUsuarios.add(txtRegisPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 116, 163, 35));

        lblUser.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        lblUser.setText("Usuario");
        panelUsuarios.add(lblUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 73, -1, -1));

        cbAdmin.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        cbAdmin.setText("Administrador");
        panelUsuarios.add(cbAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 169, -1, -1));

        btnRegistrar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/save.png"))); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        panelUsuarios.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 216, 269, -1));

        lblRegistrados.setFont(new java.awt.Font("Segoe UI Light", 0, 28)); // NOI18N
        lblRegistrados.setText("Usuarios registrados");
        panelUsuarios.add(lblRegistrados, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 276, -1, -1));

        tablaUsuarios.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Usuario", "Tipo de usuario", "Fecha en que se agregó"
            }
        ));
        tablaUsuarios.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaUsuariosFocusLost(evt);
            }
        });
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUsuarios);

        panelUsuarios.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 333, 581, 257));

        btnEliminarUsuario.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        btnEliminarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/trash.png"))); // NOI18N
        btnEliminarUsuario.setText("Eliminar");
        btnEliminarUsuario.setEnabled(false);
        btnEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUsuarioActionPerformed(evt);
            }
        });
        panelUsuarios.add(btnEliminarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(491, 290, 100, -1));

        bienPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        panelUsuarios.add(bienPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, -1, -1));

        malPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/cross.png"))); // NOI18N
        panelUsuarios.add(malPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, -1, -1));

        bienUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        panelUsuarios.add(bienUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, -1, -1));

        avisoPass.setForeground(new java.awt.Color(102, 102, 102));
        avisoPass.setText("<html>La contraseña debe tener al menos 8 caracteres, una letra mayuscula, una letra minúscula y un número <html>");
        panelUsuarios.add(avisoPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 140, 70));

        malUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/cross.png"))); // NOI18N
        panelUsuarios.add(malUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, -1, -1));

        avisoUser.setForeground(new java.awt.Color(102, 102, 102));
        avisoUser.setText("Usuario repetido");
        panelUsuarios.add(avisoUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, -1, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/error.png"))); // NOI18N
        jButton5.setText("Cancelar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        panelUsuarios.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, -1, -1));

        tabbedConfig.addTab("Usuarios", panelUsuarios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedConfig)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedConfig)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if(bienUser.isVisible() && bienPass.isVisible()) {
            int res = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea registrar al usuario " + txtRegisUsuario.getText() + "?", "Confirmar registro", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (res == 0) {
                try {
                    Statement ST = c.createStatement();
                    int nivel;
                    if (cbAdmin.isSelected()) {
                        nivel = 0;
                    } else {
                        nivel = 1;
                    }
                    java.util.Date date = new Date();
                    Object param = new java.sql.Timestamp(date.getTime());
                    String sql = "Insert into USUARIOS values('" + txtRegisUsuario.getText()
                            + "','" + txtRegisPass.getText() + "','" + nivel
                            + "','" + param + "')";
                    ST.executeUpdate(sql);
                    bienPass.setVisible(false);
                    avisoPass.setVisible(false);
                    malPass.setVisible(false);
                    cbAdmin.setSelected(false);
                    avisoUser.setVisible(false);
                    bienUser.setVisible(false);
                    malUser.setVisible(false);
                    txtRegisUsuario.setText("");
                    txtRegisPass.setText("");
                    JOptionPane.showMessageDialog(null, "Se registró correctamente al usuario", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                    refrescarUsuarios();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ha introducido uno o más campos incorrectamente, favor de verificarlos.", "Campos incorrectos", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed
    public void refrescarUsuarios(){
        //Tabla de Usuarios
        DTM = new DefaultTableModel(titulosUsuarios, 0);
        tablaUsuarios.setModel(DTM);
        try {
            Statement smt = c.createStatement();
            ResultSet res = smt.executeQuery("select * from USUARIOS");
            if (res.next()) {
                do {
                    String priv;
                    if(res.getString("Nivel").equals("0")){
                        priv="Administador";
                    }
                    else{
                        priv="Normal";
                    }
                    Object fila[] = {res.getString("Usuario"),priv,res.getString("fecha")};
                    DTM.addRow(fila);
                } while (res.next());
                tablaUsuarios.setModel(DTM);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    private void btnEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUsuarioActionPerformed
        int res = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar al usuario " + datosUsuarios[0] + "?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (res == 0) {
            try {
                Statement smt = c.createStatement();
                String sql = "Delete from USUARIOS where Usuario='" + datosUsuarios[0] + "'";
                smt.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Se eliminó correctamente al usuario.", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
                refrescarUsuarios();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btnEliminarUsuarioActionPerformed

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        int row = tablaUsuarios.getSelectedRow();
        for (int i = 0; i < 3; i++) {
            datosUsuarios[i] = String.valueOf(tablaUsuarios.getValueAt(row, i));
        }
        btnEliminarUsuario.setEnabled(true);
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    private void tablaUsuariosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaUsuariosFocusLost
        tablaUsuarios.getSelectionModel().clearSelection(); 
    }//GEN-LAST:event_tablaUsuariosFocusLost

    private void txtRegisUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRegisUsuarioKeyReleased
        if(txtRegisUsuario.getText().equals("")){
            bienUser.setVisible(false);
            malUser.setVisible(false);
        }
        else{
            try {
                Statement smt = c.createStatement();
                ResultSet res = smt.executeQuery("SELECT 1 FROM USUARIOS WHERE Usuario='" + txtRegisUsuario.getText() + "'");
                if (res.next()) {
                    malUser.setVisible(true);
                    bienUser.setVisible(false);
                    avisoUser.setVisible(true);
                }
                else{
                    malUser.setVisible(false);
                    bienUser.setVisible(true);
                    avisoUser.setVisible(false);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_txtRegisUsuarioKeyReleased

    private void txtRegisPassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRegisPassKeyReleased
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
        if(txtRegisPass.getText().equals("")){
            bienPass.setVisible(false);
            avisoPass.setVisible(false);
            malPass.setVisible(false);
        }
        else{
            if(txtRegisPass.getText().matches(pattern)){
                bienPass.setVisible(true);
                avisoPass.setVisible(false);
                malPass.setVisible(false);
            }
            else{
                bienPass.setVisible(false);
                malPass.setVisible(true);
                avisoPass.setVisible(true);
            }
        }
    }//GEN-LAST:event_txtRegisPassKeyReleased

    private void tabbedConfigStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedConfigStateChanged
//        System.out.println(LogIn.level);
//        switch (tabbedConfig.getSelectedIndex()) {
//            case 0:
//                
//                break;
//            case 1:
//                if(LogIn.level==1){
//                    JOptionPane.showMessageDialog(null, "Usted no posee los derechos para editar Usuarios", "Error de derechos", JOptionPane.WARNING_MESSAGE);
//                }
//                break;
//        }
    }//GEN-LAST:event_tabbedConfigStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton1.isSelected()){
            TX1 = JColorChooser.showDialog(null, "Escoje un color", Color.RED);
            jLabel2.setForeground(TX1);
        }else{
            if(jRadioButton2.isSelected()){
                TX2 = JColorChooser.showDialog(null, "Escoje un color", Color.RED);
                jLabel1.setForeground(TX2);
            }else{
                if(jRadioButton3.isSelected()){
                    BG1 = JColorChooser.showDialog(null, "Escoje un color", Color.RED);
                    jLabel3.setForeground(BG1);
                }else{
                    if(jRadioButton4.isSelected()){
                        BG2 = JColorChooser.showDialog(null, "Escoje un color", Color.RED);
                        jLabel4.setForeground(BG2);
                    }else{
                        JOptionPane.showMessageDialog(null, "No se selecciono un objetivo a cambiar de color.");
                    }
                }
            }
        }
        

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        BufferedWriter writer = null;
        try {
            //create a temporary file
            File logFile = new File("C:\\Users\\mario\\OneDrive\\Documentos\\NetBeansProjects\\SOCET+\\config.txt");

            // This will output the full path where the file will be written to...
            //System.out.println(logFile.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(TX1.getRed()+","+TX1.getGreen()+","+TX1.getBlue()+","+TX2.getRed()+","+TX2.getGreen()+","+TX2.getBlue()+","+BG1.getRed()+","+BG1.getGreen()+","+BG1.getBlue()+","+BG2.getRed()+","+BG2.getGreen()+","+BG2.getBlue());
            JOptionPane.showMessageDialog(null, "Se ha guardado correctamente el color");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        TX1 = new Color(0, 0, 0);
        TX2 = new Color(255, 255, 255);
        BG1 = new Color(237, 192, 0);
        BG2 = new Color(54, 57, 62);
        jLabel2.setForeground(TX1);
        jLabel1.setForeground(TX2);
        jLabel3.setForeground(BG1);
        jLabel4.setForeground(BG2);
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Opciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Opciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Opciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Opciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            Opciones dialog = new Opciones(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel avisoPass;
    private javax.swing.JLabel avisoUser;
    private javax.swing.JLabel bienPass;
    private javax.swing.JLabel bienUser;
    private javax.swing.JButton btnEliminarUsuario;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbAdmin;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAñadir;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblRegistrados;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel malPass;
    private javax.swing.JLabel malUser;
    private javax.swing.JPanel panelConfiguracion;
    private javax.swing.JPanel panelUsuarios;
    private javax.swing.JTabbedPane tabbedConfig;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JPasswordField txtRegisPass;
    private javax.swing.JTextField txtRegisUsuario;
    // End of variables declaration//GEN-END:variables
}
