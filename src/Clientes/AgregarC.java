/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes;

import Objetos.Cliente;
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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marco
 */
public class AgregarC extends javax.swing.JDialog {
    Connection c = ConexionSQLServer.Conectar();
    String titulosClientes[]={"Matricula","Nombre","Apellido","Teléfono","Dirección","Empresa","e-mail"};
    DefaultTableModel DTMC;
    public AgregarC(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
        initComponents();
        setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/Miscelaneo/logo2.png")).getImage());
        check1.setVisible(false);
        check2.setVisible(false);
        check3.setVisible(false);
        check4.setVisible(false);
        check5.setVisible(false);
        check6.setVisible(false);
        cross1.setVisible(false);
        cross2.setVisible(false);
        cross3.setVisible(false);
        cross4.setVisible(false);
        cross5.setVisible(false);
        cross6.setVisible(false);
        cross7.setVisible(false);
        check7.setVisible(false);
        ad1.setVisible(false);
        ad2.setVisible(false);
        ad3.setVisible(false);
        ad4.setVisible(false);
        ad5.setVisible(false);
        ad6.setVisible(false);
    }
    private void insertarValor(){
        Cliente objCliente = new Cliente(txtMatricula.getText(),txtNombre.getText(),txtApellido.getText(),txtTelefono.getText(),
            txtDireccion.getText(),txtEmpresa.getText(),txtEmail.getText());
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
    
    public void insertar(Cliente objCliente){
        try{
           Statement ST = c.createStatement();
           String sql = "Insert into CLIENTES values('"+objCliente.getMatricula()
                    +"','"+objCliente.getNombre()+"','"+objCliente.getApellido()
                    +"','"+objCliente.getTelefono()+"','"+objCliente.getDireccion()
                    +"','"+objCliente.getEmpresa()+"','"+objCliente.getEmail()+"')";
           ST.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public ResultSet buscar(Cliente objCliente){
        try{
           Statement ST = c.createStatement();
           String sql = "Select * from CLIENTES where IDCliente='"+objCliente.getMatricula()+"'";
           return ST.executeQuery(sql);
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public void actualizar(Cliente objCliente){
        try{
           Statement ST = c.createStatement();
           String sql = "Update CLIENTES set nombre='"+objCliente.getNombre()
                   +"', Apellido='"+objCliente.getApellido()
                   +"', Telefono='"+objCliente.getTelefono()
                   +"', E_Mail='"+objCliente.getDireccion()
                   +"', Direccion='"+objCliente.getEmpresa()
                   +"', Empresa='"+objCliente.getEmail()
                   +"' where IDCliente ='"+objCliente.getMatricula()+"'";
           ST.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public void eliminar(Cliente objCliente){
        try{
           Statement ST = c.createStatement();
           String sql = "Delete from CLIENTES where IDCliente='"+ objCliente.getMatricula() +"'";
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
        txtNombre = new javax.swing.JTextField();
        apellido = new javax.swing.JLabel();
        telefono = new javax.swing.JLabel();
        direccion = new javax.swing.JLabel();
        empresa = new javax.swing.JLabel();
        txtMatricula = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtEmpresa = new javax.swing.JTextField();
        Aceptar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        Limpiar = new javax.swing.JButton();
        Titulo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        check5 = new javax.swing.JLabel();
        check6 = new javax.swing.JLabel();
        check4 = new javax.swing.JLabel();
        check3 = new javax.swing.JLabel();
        check2 = new javax.swing.JLabel();
        cross1 = new javax.swing.JLabel();
        cross2 = new javax.swing.JLabel();
        cross3 = new javax.swing.JLabel();
        cross4 = new javax.swing.JLabel();
        cross5 = new javax.swing.JLabel();
        cross6 = new javax.swing.JLabel();
        check1 = new javax.swing.JLabel();
        cross7 = new javax.swing.JLabel();
        check7 = new javax.swing.JLabel();
        ad1 = new javax.swing.JLabel();
        ad2 = new javax.swing.JLabel();
        ad3 = new javax.swing.JLabel();
        ad4 = new javax.swing.JLabel();
        ad5 = new javax.swing.JLabel();
        ad6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        empresa1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Agregar cliente");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(239, 240, 240));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        idCliente.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente.setText("Matricula:");

        nombre.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        nombre.setText("Nombre:");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        apellido.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        apellido.setText("Apellido:");

        telefono.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        telefono.setText("Teléfono:");

        direccion.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        direccion.setText("Dirección:");

        empresa.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        empresa.setText("Empresa:");

        txtMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatriculaActionPerformed(evt);
            }
        });
        txtMatricula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMatriculaKeyReleased(evt);
            }
        });

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidoKeyReleased(evt);
            }
        });

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
        });

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
        });

        txtEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmpresaKeyReleased(evt);
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
        Titulo.setText("Agregar cliente");

        jPanel2.setBackground(new java.awt.Color(239, 240, 240));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        check5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        jPanel2.add(check5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

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

        cross3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/cross.png"))); // NOI18N
        jPanel2.add(cross3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        cross4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/cross.png"))); // NOI18N
        jPanel2.add(cross4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        cross5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/cross.png"))); // NOI18N
        jPanel2.add(cross5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        cross6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/cross.png"))); // NOI18N
        jPanel2.add(cross6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        check1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        jPanel2.add(check1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        cross7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/cross.png"))); // NOI18N
        jPanel2.add(cross7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        check7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        jPanel2.add(check7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        ad1.setForeground(new java.awt.Color(102, 102, 102));
        ad1.setText("Sólo números");
        jPanel2.add(ad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, -1));

        ad2.setForeground(new java.awt.Color(102, 102, 102));
        ad2.setText("Sólo letras");
        jPanel2.add(ad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        ad3.setForeground(new java.awt.Color(102, 102, 102));
        ad3.setText("Sólo letras");
        jPanel2.add(ad3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        ad4.setForeground(new java.awt.Color(102, 102, 102));
        ad4.setText("Sólo números");
        jPanel2.add(ad4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        ad5.setForeground(new java.awt.Color(102, 102, 102));
        ad5.setText("e-mail no válido");
        jPanel2.add(ad5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        ad6.setForeground(new java.awt.Color(102, 102, 102));
        ad6.setText("Teléfono inválido");
        jPanel2.add(ad6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        txtEmail.setText("email@example.com");
        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmailFocusGained(evt);
            }
        });
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        empresa1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        empresa1.setText("e-mail:");

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("*");

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("*");

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("*");

        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("*");

        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("* Campos obligatorios");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombre)
                            .addComponent(idCliente)
                            .addComponent(apellido)
                            .addComponent(telefono)
                            .addComponent(direccion)
                            .addComponent(empresa)
                            .addComponent(empresa1))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(Cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(Titulo)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 42, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(Titulo)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idCliente)
                            .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombre)
                            .addComponent(jLabel2))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(apellido)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefono)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(direccion))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(empresa))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(empresa1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
        if(check3.isVisible() && check4.isVisible() && check5.isVisible() && check6.isVisible()){
            int res = JOptionPane.showConfirmDialog(null, "¿Confirmar registro?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if(res==0){
                Cliente objCliente = new Cliente(txtMatricula.getText(),txtNombre.getText(),txtApellido.getText(),txtTelefono.getText(),
                txtDireccion.getText(),txtEmpresa.getText(),txtEmail.getText());
                ResultSet resultado = buscar(objCliente);
                try{
                    if(resultado.next()){
                        JOptionPane.showMessageDialog(this, "La Matricula ya existe","Aviso",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        insertar(objCliente);
                        JOptionPane.showMessageDialog(null, "Registro de cliente completado correctamente", "Registro exitoso", JOptionPane.DEFAULT_OPTION);
                        Utilidades uti = new Utilidades();
                        uti.ClickEnClientes();
                        this.dispose();
                    }
                }catch(SQLException e){
                    System.out.println(e);
                } 
            }   
        }
        else{
            if(txtMatricula.getText().isEmpty() || txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtTelefono.getText().isEmpty() || txtDireccion.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Hay campos vacíos. Favor de llenar los campos faltantes y vuelva a intentarlo.", "Campos vacíos", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Hay campos no válidos. Favor de revisar la información y vuelva a intentarlo", "Campos inválidos", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_AceptarActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        txtMatricula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtEmpresa.setText("");
        txtEmail.setText("");
        check1.setVisible(false);
        check2.setVisible(false);
        check3.setVisible(false);
        check4.setVisible(false);
        check5.setVisible(false);
        check6.setVisible(false);
        cross1.setVisible(false);
        cross2.setVisible(false);
        cross3.setVisible(false);
        cross4.setVisible(false);
        cross5.setVisible(false);
        cross6.setVisible(false);
        cross7.setVisible(false);
        check7.setVisible(false);
        ad1.setVisible(false);
        ad2.setVisible(false);
        ad3.setVisible(false);
        ad4.setVisible(false);
        ad5.setVisible(false);
    }//GEN-LAST:event_LimpiarActionPerformed

    private void txtMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatriculaActionPerformed
        
    }//GEN-LAST:event_txtMatriculaActionPerformed

    private void txtMatriculaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatriculaKeyReleased
        if(txtMatricula.getText().matches("[0-9]+")){
            check5.setVisible(true);
            cross5.setVisible(false);
            ad1.setVisible(false);
        }
        else if(txtMatricula.getText().equals("")){
            cross5.setVisible(false);
            check5.setVisible(false);
            ad1.setVisible(false);
        }
        else{
            cross5.setVisible(true);
            check5.setVisible(false);
            ad1.setVisible(true);
        }
    }//GEN-LAST:event_txtMatriculaKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        if(txtNombre.getText().matches("[a-zA-Z ]+")){
            cross4.setVisible(false);
            check6.setVisible(true);
            ad2.setVisible(false);
        }
        else if(txtNombre.getText().equals("")){
            cross4.setVisible(false);
            check6.setVisible(false);
            ad2.setVisible(false);
        }
        else{
            check6.setVisible(false);
            cross4.setVisible(true);
            ad2.setVisible(true);
        }
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyReleased
        if(txtApellido.getText().matches("[a-zA-Z ]+")){
            cross3.setVisible(false);
            check3.setVisible(true);
            ad3.setVisible(false);
        }
        else if(txtApellido.getText().equals("")){
            cross3.setVisible(false);
            check3.setVisible(false);
            ad3.setVisible(false);
        }
        else{
            check3.setVisible(false);
            cross3.setVisible(true);
            ad3.setVisible(true);
        }
    }//GEN-LAST:event_txtApellidoKeyReleased
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
    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusGained
        txtEmail.setText("");
    }//GEN-LAST:event_txtEmailFocusGained

    private void txtEmpresaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpresaKeyReleased
        if(!txtEmpresa.getText().equals("")){
            check7.setVisible(true);
        }
        else{
            check7.setVisible(false);
        }
    }//GEN-LAST:event_txtEmpresaKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        if(isValidEmail(txtEmail.getText())){
            check1.setVisible(true);
            cross6.setVisible(false);
            ad5.setVisible(false);
        }
        else if(txtEmail.getText().equals("")){
            cross6.setVisible(false);
            check1.setVisible(false);
            ad5.setVisible(false);
        }
        else{
            cross6.setVisible(true);
            check1.setVisible(false);
            ad5.setVisible(true);
        }
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
        if(!txtDireccion.getText().equals("")){
            check2.setVisible(true);
        }
        else{
            check2.setVisible(false);
        }
    }//GEN-LAST:event_txtDireccionKeyReleased

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        if(txtTelefono.getText().matches("[0-9]+")){
            if(txtTelefono.getText().length()==10 || txtTelefono.getText().length()==7){
                check4.setVisible(true);
                cross2.setVisible(false);
                ad4.setVisible(false);
                ad6.setVisible(false);   
            }
            else{
                ad6.setVisible(true);
                cross2.setVisible(true);
                check4.setVisible(false);
                ad4.setVisible(false);
            }
        }
        else if(txtTelefono.getText().equals("")){
            cross2.setVisible(false);
            check4.setVisible(false);
            ad4.setVisible(false);
            ad6.setVisible(false);
        }
        else{
            cross2.setVisible(true);
            check4.setVisible(false);
            ad4.setVisible(true);
            ad6.setVisible(false);
        }
    }//GEN-LAST:event_txtTelefonoKeyReleased

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
            java.util.logging.Logger.getLogger(AgregarC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            AgregarC dialog = new AgregarC(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel ad1;
    private javax.swing.JLabel ad2;
    private javax.swing.JLabel ad3;
    private javax.swing.JLabel ad4;
    private javax.swing.JLabel ad5;
    private javax.swing.JLabel ad6;
    private javax.swing.JLabel apellido;
    private javax.swing.JLabel check1;
    private javax.swing.JLabel check2;
    private javax.swing.JLabel check3;
    private javax.swing.JLabel check4;
    private javax.swing.JLabel check5;
    private javax.swing.JLabel check6;
    private javax.swing.JLabel check7;
    private javax.swing.JLabel cross1;
    private javax.swing.JLabel cross2;
    private javax.swing.JLabel cross3;
    private javax.swing.JLabel cross4;
    private javax.swing.JLabel cross5;
    private javax.swing.JLabel cross6;
    private javax.swing.JLabel cross7;
    private javax.swing.JLabel direccion;
    private javax.swing.JLabel empresa;
    private javax.swing.JLabel empresa1;
    private javax.swing.JLabel idCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel telefono;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmpresa;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
