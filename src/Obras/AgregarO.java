/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obras;

import Objetos.Cliente;
import Objetos.Empleado;
import Objetos.Obra;
import Objetos.Utilidades;
import Otros.ConexionSQLServer;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
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
public class AgregarO extends javax.swing.JDialog{
    Connection c = ConexionSQLServer.Conectar();
    JDateChooser fechaInicio, fechaFin;
    static Cliente clienteO = new Cliente();
    static Empleado empleadoO = new Empleado();
    public AgregarO(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        initComponents();
        setLocationRelativeTo(null);
        fechaInicio = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        fechaInicio.setBounds(133,250,225,33);
        jPanel1.add(fechaInicio);
        fechaFin = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        fechaFin.setBounds(133,300,225,33);    
        jPanel1.add(fechaFin);
        this.setIconImage(new ImageIcon(getClass().getResource("/Miscelaneo/logo2.png")).getImage());
        check3.setVisible(false);
        cross8.setVisible(false);
        check4.setVisible(false);
        check5.setVisible(false);
        check6.setVisible(false);
        cross5.setVisible(false);
        cross10.setVisible(false);
        check9.setVisible(false);
        cross7.setVisible(false);
        check7.setVisible(false);
        check8.setVisible(false);
        check10.setVisible(false);
        check11.setVisible(false);
        check12.setVisible(false);
        ad1.setVisible(false);
        ad4.setVisible(false);
        ad5.setVisible(false);
        ad7.setVisible(false);
        
    }
    
    public void insertar(Obra objCliente){
        try{
           Statement ST = c.createStatement();
           String sql = "Insert into OBRAS values('"+objCliente.getMatricula()
                    +"','"+objCliente.getNombreObra()+"','"+objCliente.getCliente().getMatricula()
                    +"','"+objCliente.getFechaInicio()+"','"+objCliente.getFechaFin()
                    +"','"+objCliente.getPresupuesto()+"','"+objCliente.getUbicacion()
                    +"','"+objCliente.getTipo()+"','"+objCliente.getEncargado().getMatricula()
                    +"','"+objCliente.getEstatus()+"','"+objCliente.getDescripcion()+"')";
           ST.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public ResultSet buscar(Obra objCliente){
        try{
           Statement ST = c.createStatement();
           String sql = "Select * from OBRAS where IDObra='"+objCliente.getMatricula()+"'";
           return ST.executeQuery(sql);
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public void actualizar(Obra objCliente){
        try{
           Statement ST = c.createStatement();
           String sql = "Update OBRAS set NombreObra='"+objCliente.getNombreObra()
                   +"', Cliente='"+objCliente.getCliente().getMatricula()
                   +"', FechaIni='"+objCliente.getFechaInicio()
                   +"', FechaFin='"+objCliente.getFechaFin()
                   +"', Presupuesto='"+objCliente.getPresupuesto()
                   +"', Ubicacion='"+objCliente.getUbicacion()
                   +"', TipoDeObra='"+objCliente.getTipo()
                   +"', Encargado='"+objCliente.getEncargado().getMatricula()
                   +"', Estatus='"+objCliente.getEstatus()
                   +"', Descripcion='"+objCliente.getDescripcion()
                   +"' where IDObra ='"+objCliente.getMatricula()+"'";
           ST.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public void eliminar(Obra objCliente){
        try{
           Statement ST = c.createStatement();
           String sql = "Delete from OBRAS where IDObra='"+ objCliente.getMatricula() +"'";
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
        txtNombreObra = new javax.swing.JTextField();
        apellido = new javax.swing.JLabel();
        telefono = new javax.swing.JLabel();
        direccion = new javax.swing.JLabel();
        empresa = new javax.swing.JLabel();
        txtMatricula = new javax.swing.JTextField();
        txtCliente = new javax.swing.JTextField();
        txtPresupuesto = new javax.swing.JTextField();
        Aceptar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        Limpiar = new javax.swing.JButton();
        Titulo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        check5 = new javax.swing.JLabel();
        check6 = new javax.swing.JLabel();
        check3 = new javax.swing.JLabel();
        cross5 = new javax.swing.JLabel();
        cross7 = new javax.swing.JLabel();
        check7 = new javax.swing.JLabel();
        ad1 = new javax.swing.JLabel();
        ad4 = new javax.swing.JLabel();
        check10 = new javax.swing.JLabel();
        check11 = new javax.swing.JLabel();
        check12 = new javax.swing.JLabel();
        empresa1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtUbicación = new javax.swing.JTextField();
        idCliente1 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        idCliente2 = new javax.swing.JLabel();
        txtEncargado = new javax.swing.JTextField();
        idCliente3 = new javax.swing.JLabel();
        txtEstatus = new javax.swing.JTextField();
        idCliente4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textADescripcion = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        check8 = new javax.swing.JLabel();
        check9 = new javax.swing.JLabel();
        check4 = new javax.swing.JLabel();
        cross8 = new javax.swing.JLabel();
        cross10 = new javax.swing.JLabel();
        ad5 = new javax.swing.JLabel();
        ad7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbEstatus = new javax.swing.JComboBox<>();
        cbObra = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Agregar obra");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(239, 240, 240));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        idCliente.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente.setText("Matricula:");

        nombre.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        nombre.setText("Nombre:");

        txtNombreObra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreObraActionPerformed(evt);
            }
        });
        txtNombreObra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreObraKeyReleased(evt);
            }
        });

        apellido.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        apellido.setText("Cliente:");

        telefono.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        telefono.setText("Fecha inicio:");

        direccion.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        direccion.setText("Fecha fin:");

        empresa.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        empresa.setText("Presupuesto:");

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

        txtCliente.setEnabled(false);
        txtCliente.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtClienteInputMethodTextChanged(evt);
            }
        });
        txtCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteActionPerformed(evt);
            }
        });
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        txtPresupuesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPresupuestoKeyReleased(evt);
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
        Titulo.setText("Agregar obra");

        jPanel2.setBackground(new java.awt.Color(239, 240, 240));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        check5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        jPanel2.add(check5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        check6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        jPanel2.add(check6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        check3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        jPanel2.add(check3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        cross5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/cross.png"))); // NOI18N
        jPanel2.add(cross5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        cross7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/cross.png"))); // NOI18N
        jPanel2.add(cross7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        check7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        jPanel2.add(check7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        ad1.setForeground(new java.awt.Color(102, 102, 102));
        ad1.setText("Sólo números");
        jPanel2.add(ad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        ad4.setForeground(new java.awt.Color(102, 102, 102));
        ad4.setText("Sólo números");
        jPanel2.add(ad4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, -1));

        check10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        jPanel2.add(check10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        check11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        jPanel2.add(check11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        check12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        jPanel2.add(check12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        empresa1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        empresa1.setText("Ubicación:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("* Campos obligatorios");

        txtUbicación.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUbicaciónKeyReleased(evt);
            }
        });

        idCliente1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente1.setText("Tipo de Obra:");

        txtTipo.setEnabled(false);
        txtTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoActionPerformed(evt);
            }
        });
        txtTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTipoKeyReleased(evt);
            }
        });

        idCliente2.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente2.setText("Encargado:");

        txtEncargado.setEnabled(false);
        txtEncargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEncargadoActionPerformed(evt);
            }
        });
        txtEncargado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEncargadoKeyReleased(evt);
            }
        });

        idCliente3.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente3.setText("Estatus:");

        txtEstatus.setEnabled(false);
        txtEstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstatusActionPerformed(evt);
            }
        });
        txtEstatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEstatusKeyReleased(evt);
            }
        });

        idCliente4.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente4.setText("Descripción:");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        textADescripcion.setColumns(20);
        textADescripcion.setLineWrap(true);
        textADescripcion.setRows(5);
        jScrollPane1.setViewportView(textADescripcion);

        jPanel3.setBackground(new java.awt.Color(239, 240, 240));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        check8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        jPanel3.add(check8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        check9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        jPanel3.add(check9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        check4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N
        jPanel3.add(check4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        cross8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/cross.png"))); // NOI18N
        jPanel3.add(cross8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        cross10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/cross.png"))); // NOI18N
        jPanel3.add(cross10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        ad5.setForeground(new java.awt.Color(102, 102, 102));
        ad5.setText("Sólo letras");
        jPanel3.add(ad5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, -1));

        ad7.setForeground(new java.awt.Color(102, 102, 102));
        ad7.setText("Sólo letras");
        jPanel3.add(ad7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jButton1.setText("...");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("...");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("*");

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("*");

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("*");

        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("*");

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("*");

        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("*");

        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("*");

        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("*");

        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("*");

        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("*");

        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("*");

        cbEstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "Pendiente", "En proceso", "Completado", "Otro..." }));
        cbEstatus.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbEstatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbEstatusItemStateChanged(evt);
            }
        });

        cbObra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "Pavimentación", "Construcción", "Encementado", "Otro..." }));
        cbObra.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbObra.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbObraItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(empresa1)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(225, 225, 225)
                                                .addComponent(Cancelar)
                                                .addGap(18, 18, 18)
                                                .addComponent(Aceptar))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(30, 30, 30)
                                                        .addComponent(txtUbicación, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(3, 3, 3)
                                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(idCliente1)
                                                    .addComponent(idCliente2)
                                                    .addComponent(idCliente3)
                                                    .addComponent(idCliente4)))))
                                    .addComponent(telefono)
                                    .addComponent(direccion)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(empresa)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(apellido)
                                                .addComponent(idCliente)
                                                .addComponent(nombre))
                                            .addGap(32, 32, 32)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(txtNombreObra, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(cbObra, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(cbEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(19, 19, 19)))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Titulo)
                        .addGap(354, 354, 354))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(Titulo)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idCliente)
                            .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreObra, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombre)
                            .addComponent(jLabel2))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(apellido)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(telefono)
                            .addComponent(jLabel4))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(direccion)
                            .addComponent(jLabel5))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(empresa)
                            .addComponent(txtPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(empresa1)
                            .addComponent(txtUbicación, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(idCliente1)
                                    .addComponent(jLabel9)
                                    .addComponent(cbObra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(idCliente2)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbEstatus, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(idCliente3)
                                        .addComponent(jLabel11)))
                                .addGap(8, 8, 8))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(idCliente4)
                                    .addComponent(jLabel12))
                                .addGap(163, 163, 163))
                            .addComponent(jScrollPane1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(32, 32, 32))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Limpiar)
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
        String fechaI = String.valueOf(fechaInicio.getCalendar().get(Calendar.DAY_OF_MONTH)+"/"+(fechaInicio.getCalendar().get(Calendar.MONTH)+1)+"/"+fechaInicio.getCalendar().get(Calendar.YEAR));
        String fechaF = String.valueOf(fechaFin.getCalendar().get(Calendar.DAY_OF_MONTH)+"/"+(fechaFin.getCalendar().get(Calendar.MONTH)+1)+"/"+fechaFin.getCalendar().get(Calendar.YEAR));
        if(check3.isVisible() && check4.isVisible() && check5.isVisible() && check6.isVisible() && check10.isVisible() && check11.isVisible() && check8.isVisible() && check9.isVisible()){
            int res = JOptionPane.showConfirmDialog(null, "¿Confirmar registro?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            String tipo, estatus;
            if(txtTipo.getText().isEmpty()){
                tipo=cbObra.getItemAt(cbObra.getSelectedIndex());
            }
            else{
                tipo=txtTipo.getText();
            }
            if(txtEstatus.getText().isEmpty()){
                estatus=cbEstatus.getItemAt(cbEstatus.getSelectedIndex());
            }
            else{
                estatus=txtEstatus.getText();
            }
            if(res==0){
                Obra objCliente = new Obra(txtMatricula.getText(),txtNombreObra.getText(),clienteO,fechaI,
                    fechaF,txtPresupuesto.getText(),txtUbicación.getText(),tipo,empleadoO
                    ,estatus,textADescripcion.getText());
                ResultSet resultado = buscar(objCliente);
                try{
                    if(resultado.next()){
                        JOptionPane.showMessageDialog(this, "La Matricula ya existe","Aviso",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        insertar(objCliente);
                        Utilidades uti = new Utilidades();
                        uti.clickEnObra();
                        JOptionPane.showMessageDialog(null, "Registro de obra completado correctamente", "Registro exitoso", JOptionPane.DEFAULT_OPTION);
                        this.dispose();  
                    }
                }catch(SQLException e){
                    System.out.println(e);
                }
            }   
        }
        else{
            if(txtMatricula.getText().isEmpty() || txtNombreObra.getText().isEmpty() || txtCliente.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Hay campos vacíos. Favor de llenar los campos faltantes y vuelva a intentarlo.", "Campos vacíos", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Hay campos no válidos. Favor de revisar la información y velva a intentarlo", "Campos inválidos", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_AceptarActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        txtMatricula.setText("");
        txtNombreObra.setText("");
        txtCliente.setText("");
        txtPresupuesto.setText("");
        txtUbicación.setText("");
        txtTipo.setText("");
        txtEstatus.setText("");
        txtEncargado.setText("");
        textADescripcion.setText("");
        check3.setVisible(false);
        check4.setVisible(false);
        check5.setVisible(false);
        check6.setVisible(false);
        cross5.setVisible(false);
        cross7.setVisible(false);
        check7.setVisible(false);
        check10.setVisible(false);
        check11.setVisible(false);
        check10.setVisible(false);
        ad1.setVisible(false);
        ad4.setVisible(false);
        ad5.setVisible(false);
    }//GEN-LAST:event_LimpiarActionPerformed

    private void txtMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatriculaActionPerformed
        
    }//GEN-LAST:event_txtMatriculaActionPerformed

    private void txtMatriculaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatriculaKeyReleased
        if(txtMatricula.getText().matches("[0-9]+")){
            check5.setVisible(true);
            cross5.setVisible(false);
            ad4.setVisible(false);
        }
        else if(txtMatricula.getText().equals("")){
            cross5.setVisible(false);
            check5.setVisible(false);
            ad4.setVisible(false);
        }
        else{
            cross5.setVisible(true);
            check5.setVisible(false);
            ad4.setVisible(true);
        }
    }//GEN-LAST:event_txtMatriculaKeyReleased

    private void txtNombreObraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreObraKeyReleased
        if(txtNombreObra.getText().equals("")){
            check6.setVisible(false);
        }
        else{
            check6.setVisible(true);
        }
    }//GEN-LAST:event_txtNombreObraKeyReleased

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        if(txtCliente.getText().matches("[a-zA-Z ]+")){
            check3.setVisible(true);
        }
        else if(txtCliente.getText().equals("")){
            check3.setVisible(false);
        }
        else{
            check3.setVisible(false);
        }
    }//GEN-LAST:event_txtClienteKeyReleased
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
    private void txtNombreObraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreObraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreObraActionPerformed

    private void txtPresupuestoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPresupuestoKeyReleased
        if(txtPresupuesto.getText().matches("[0-9]+")){
            check10.setVisible(true);
            cross7.setVisible(false);
            ad1.setVisible(false);
        }
        else if(txtPresupuesto.getText().equals("")){
            cross7.setVisible(false);
            check10.setVisible(false);
            ad1.setVisible(false);
        }
        else{
            cross7.setVisible(true);
            check10.setVisible(false);
            ad1.setVisible(true);
        }
    }//GEN-LAST:event_txtPresupuestoKeyReleased

    private void txtUbicaciónKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUbicaciónKeyReleased
        if(txtUbicación.getText().equals("")){
            check11.setVisible(false);
        }
        else{
            check11.setVisible(true);
        }
    }//GEN-LAST:event_txtUbicaciónKeyReleased

    private void txtTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoActionPerformed

    private void txtTipoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoKeyReleased
        if(txtTipo.getText().matches("[a-zA-Z ]+")){
            check8.setVisible(true);
            cross10.setVisible(false);
            ad5.setVisible(false);
        }
        else if(txtTipo.getText().equals("")){
            cross10.setVisible(false);
            check8.setVisible(false);
            ad5.setVisible(false);
        }
        else{
            cross10.setVisible(true);
            check8.setVisible(false);
            ad5.setVisible(true);
        }
    }//GEN-LAST:event_txtTipoKeyReleased

    private void txtEncargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEncargadoActionPerformed
        check9.setVisible(true);
    }//GEN-LAST:event_txtEncargadoActionPerformed

    private void txtEncargadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEncargadoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEncargadoKeyReleased

    private void txtEstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstatusActionPerformed

    private void txtEstatusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstatusKeyReleased
        if(txtEstatus.getText().matches("[a-zA-Z ]+")){
            check4.setVisible(true);
            cross8.setVisible(false);
            ad7.setVisible(false);
        }
        else if(txtEstatus.getText().equals("")){
            cross8.setVisible(false);
            check4.setVisible(false);
            ad7.setVisible(false);
        }
        else{
            cross8.setVisible(true);
            check4.setVisible(false);
            ad7.setVisible(true);
        }
    }//GEN-LAST:event_txtEstatusKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        SeleccionarCliente nv= new SeleccionarCliente(0,null,true);
        nv.setVisible(true);
        nv.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        SeleccionarEncargado nv= new SeleccionarEncargado(0,null,true);
        nv.setVisible(true);
        nv.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtClienteInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtClienteInputMethodTextChanged
         check3.setVisible(true);
    }//GEN-LAST:event_txtClienteInputMethodTextChanged

    private void txtClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteActionPerformed
        check3.setVisible(true);
    }//GEN-LAST:event_txtClienteActionPerformed

    private void cbEstatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbEstatusItemStateChanged
        if(evt.getItem() == cbEstatus.getItemAt(0)){
            check4.setVisible(false);
            cross8.setVisible(false);
            ad7.setVisible(false);
        }
        else if(evt.getItem() == cbEstatus.getItemAt(4)){
            check4.setVisible(false);
            txtEstatus.setEnabled(true);
        }
        else{
            check4.setVisible(true);
            txtEstatus.setEnabled(false);
            txtEstatus.setText("");
        }
    }//GEN-LAST:event_cbEstatusItemStateChanged

    private void cbObraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbObraItemStateChanged
        if(evt.getItem() == cbObra.getItemAt(0)){
            check8.setVisible(false);
            cross10.setVisible(false);
            ad5.setVisible(false);
        }
        else if(evt.getItem() == cbObra.getItemAt(4)){
            check8.setVisible(false);
            txtTipo.setEnabled(true);
        }
        else{
            check8.setVisible(true);
            txtTipo.setEnabled(false);
            txtTipo.setText("");
        }
    }//GEN-LAST:event_cbObraItemStateChanged

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
            java.util.logging.Logger.getLogger(AgregarO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            AgregarO dialog = new AgregarO(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel ad4;
    private javax.swing.JLabel ad5;
    private javax.swing.JLabel ad7;
    private javax.swing.JLabel apellido;
    private javax.swing.JComboBox<String> cbEstatus;
    private javax.swing.JComboBox<String> cbObra;
    private javax.swing.JLabel check10;
    private javax.swing.JLabel check11;
    private javax.swing.JLabel check12;
    public static javax.swing.JLabel check3;
    private javax.swing.JLabel check4;
    private javax.swing.JLabel check5;
    private javax.swing.JLabel check6;
    private javax.swing.JLabel check7;
    private javax.swing.JLabel check8;
    public static javax.swing.JLabel check9;
    private javax.swing.JLabel cross10;
    private javax.swing.JLabel cross5;
    private javax.swing.JLabel cross7;
    private javax.swing.JLabel cross8;
    private javax.swing.JLabel direccion;
    private javax.swing.JLabel empresa;
    private javax.swing.JLabel empresa1;
    private javax.swing.JLabel idCliente;
    private javax.swing.JLabel idCliente1;
    private javax.swing.JLabel idCliente2;
    private javax.swing.JLabel idCliente3;
    private javax.swing.JLabel idCliente4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel telefono;
    private javax.swing.JTextArea textADescripcion;
    public static javax.swing.JTextField txtCliente;
    public static javax.swing.JTextField txtEncargado;
    private javax.swing.JTextField txtEstatus;
    public static javax.swing.JTextField txtMatricula;
    public static javax.swing.JTextField txtNombreObra;
    private javax.swing.JTextField txtPresupuesto;
    private javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtUbicación;
    // End of variables declaration//GEN-END:variables
}
