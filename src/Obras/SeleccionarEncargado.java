/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obras;

import Objetos.Empleado;
import Objetos.Utilidades;
import Otros.ConexionSQLServer;
import Principal.Home;
import static Principal.Home.tablaClientes;
import static Principal.Home.tablaDescripcionP1;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marco
 */
public class SeleccionarEncargado extends javax.swing.JDialog {

    String titulosEmpleados[] = {"Matricula", "Nombre", "Apellido", "Dirección", "Teléfono", "Puesto"};
    String titulosObraS[] = {"Matricula", "Nombre"};
    DefaultTableModel DTME;
    Connection c = ConexionSQLServer.Conectar();
    String[] datosEmpleado;
    int num;
    int row;

    public SeleccionarEncargado(int num, java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        initComponents();
        setLocationRelativeTo(null);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.num = num;
        DTME = new DefaultTableModel(titulosEmpleados, 0);
        tablaEmpleados.setModel(DTME);
        try {
            Statement smt = c.createStatement();
            ResultSet res = smt.executeQuery("select * from TRABAJADORES");
            if (res.next()) {
                do {
                    Object fila[] = {String.valueOf(Integer.parseInt(res.getString("IDTrabajador"))), res.getString("Nombre"), res.getString("Apellido"), res.getString("Telefono"), res.getString("Direccion"), res.getString("Puesto")};
                    DTME.addRow(fila);
                } while (res.next());
                tablaEmpleados.setModel(DTME);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        Titulo.requestFocus();
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
        Titulo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaEmpleados = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        txtBuscarEncargado = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Seleccionar encargado");

        Titulo.setFont(new java.awt.Font("Cambria", 3, 24)); // NOI18N
        Titulo.setText("Seleccionar empleado");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/approve-circular-button (1).png"))); // NOI18N
        jButton1.setText("Aceptar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tablaEmpleados.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        tablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Matricula", "Nombre", "Apellido", "Dirección", "Teléfono", "Puesto"
            }
        ));
        tablaEmpleados.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaEmpleadosFocusLost(evt);
            }
        });
        tablaEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaEmpleados);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/error.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtBuscarEncargado.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        txtBuscarEncargado.setText("Buscar por nombre o matricula de empleado...");
        txtBuscarEncargado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarEncargadoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarEncargadoFocusLost(evt);
            }
        });
        txtBuscarEncargado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarEncargadoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarEncargadoKeyReleased(evt);
            }
        });

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/lupa.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 408, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtBuscarEncargado))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(Titulo)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(Titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBuscarEncargado))
                .addGap(349, 349, 349)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(108, 108, 108)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(65, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaEmpleadosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaEmpleadosFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaEmpleadosFocusLost

    private void tablaEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEmpleadosMouseClicked
        datosEmpleado = new String[7];
        row = tablaEmpleados.getSelectedRow();
        for (int i = 0; i < 6; i++) {
            datosEmpleado[i] = String.valueOf(tablaEmpleados.getValueAt(row, i));
            System.out.println("Tomados datos:  " + i);
        }
    }//GEN-LAST:event_tablaEmpleadosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (datosEmpleado == null) {
            JOptionPane.showMessageDialog(this, "No ha seleccionado ningún cliente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (num == 0) {
                Empleado objEmpleado = new Empleado(datosEmpleado[0], datosEmpleado[1], datosEmpleado[2], datosEmpleado[3], datosEmpleado[4], datosEmpleado[5]);
                AgregarO.txtEncargado.setText(datosEmpleado[0] + " - " + datosEmpleado[1] + " " + datosEmpleado[2]);
                AgregarO.check9.setVisible(true);
                AgregarO.empleadoO = objEmpleado;
                this.dispose();
            }
            if (num == 1) {
                Empleado objEmpleado = new Empleado(datosEmpleado[0], datosEmpleado[1], datosEmpleado[2], datosEmpleado[3], datosEmpleado[4], datosEmpleado[5]);
                ModificarO.txtEncargado.setText(datosEmpleado[0] + " - " + datosEmpleado[1] + " " + datosEmpleado[2]);
                ModificarO.check9.setVisible(true);
                ModificarO.empleadoO = objEmpleado;
                this.dispose();
            }
            if (num == 2) {
                Empleado objEmpleado = new Empleado(datosEmpleado[0], datosEmpleado[1], datosEmpleado[2], datosEmpleado[3], datosEmpleado[4], datosEmpleado[5]);
                Home.txtVehiculoR1.setText(datosEmpleado[0] + " - " + datosEmpleado[1] + " " + datosEmpleado[2]);
                Home.check15.setVisible(true);
                Home.empleadoD = objEmpleado;
                this.dispose();
            }
            if (num == 3) {
                int res = JOptionPane.showConfirmDialog(null, "¿Confirmar registro?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (res == 0) {
                    insertar(String.valueOf(Home.tablaObras3.getValueAt(Home.rowAsignaE, 0)), String.valueOf(tablaEmpleados.getValueAt(tablaEmpleados.getSelectedRow(), 0)), String.valueOf(tablaEmpleados.getValueAt(tablaEmpleados.getSelectedRow(), 1)) + " " + String.valueOf(tablaEmpleados.getValueAt(tablaEmpleados.getSelectedRow(), 2)));
                    Utilidades uti = new Utilidades();
                    uti.clickEnObra();
                    JOptionPane.showMessageDialog(null, "El trabajador se ha asignado correctamente a la obra", "Registro exitoso", JOptionPane.DEFAULT_OPTION);
                    this.dispose();
                }
            }
            if (num == 4) {
                DTME = new DefaultTableModel(titulosObraS, 0);
                tablaDescripcionP1.setModel(DTME);
                try {
                    Statement smt = c.createStatement();
                    ResultSet res = smt.executeQuery("select IDTrabajador,Nombre,Apellido from TRABAJADORES where IDTrabajador='" + tablaEmpleados.getValueAt(row, 0) + "'");
                    if (res.next()) {
                        do {
                            Object fila[] = {String.valueOf(Integer.parseInt(res.getString("IDTrabajador"))), res.getString("Nombre") + " " + res.getString("Apellido")};
                            DTME.addRow(fila);
                        } while (res.next());
                        tablaDescripcionP1.setModel(DTME);
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }
                this.dispose();
            }
            if (num == 5) {
                int res = JOptionPane.showConfirmDialog(null, "¿Confirmar registro?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (res == 0) {
                    insertar2(String.valueOf(Home.tablaObras7.getValueAt(Home.rowMaquinaAsig, 0)), String.valueOf(tablaEmpleados.getValueAt(row, 0)), String.valueOf(tablaEmpleados.getValueAt(row, 1)) + " " + String.valueOf(tablaEmpleados.getValueAt(row, 2)), Home.out);
                    JOptionPane.showMessageDialog(null, "El trabajador se ha asignado correctamente a la obra", "Registro exitoso", JOptionPane.DEFAULT_OPTION);
                    this.dispose();
                }
                this.dispose();
            }
            if (num == 6) {
                Empleado objEmpleado = new Empleado(datosEmpleado[0], datosEmpleado[1], datosEmpleado[2], datosEmpleado[3], datosEmpleado[4], datosEmpleado[5]);
                Home.empleadoD = objEmpleado;
                Home.txtVehiculoR1.setText(datosEmpleado[0]);
                this.dispose();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    public void insertar(String obra, String empleado, String nombre) {
        try {
            Statement ST = c.createStatement();
            String sql = "Insert into TRABAJADOR_OBRA values('" + obra
                    + "','" + empleado + "','" + nombre + "')";
            ST.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertar2(String serie, String IDEmpleado, String NombreCompleto, String fecha) {
        try {
            Statement ST = c.createStatement();
            String sql = "Insert into OPERA_MAQUINARIA values('" + serie
                    + "','" + IDEmpleado + "','" + NombreCompleto + "','" + fecha + "')";
            ST.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    private void txtBuscarEncargadoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarEncargadoFocusLost
        if (txtBuscarEncargado.getText().isEmpty()) {
            txtBuscarEncargado.setText("Buscar por nombre o matricula de empleado...");
        }
    }//GEN-LAST:event_txtBuscarEncargadoFocusLost

    private void txtBuscarEncargadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarEncargadoFocusGained
        if (txtBuscarEncargado.getText().equals("Buscar por nombre o matricula de empleado...")) {
            txtBuscarEncargado.setText("");
        }
    }//GEN-LAST:event_txtBuscarEncargadoFocusGained

    private void txtBuscarEncargadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEncargadoKeyPressed
        DTME = new DefaultTableModel(titulosEmpleados, 0);
        tablaEmpleados.setModel(DTME);
        ResultSet res = mostrar(txtBuscarEncargado.getText());
        try {
            if (res.next()) {
                do {
                    Object fila[] = {String.valueOf(Integer.parseInt(res.getString("IDTrabajador"))), res.getString("Nombre"), res.getString("Apellido"), res.getString("Telefono"), res.getString("Direccion"), res.getString("Puesto")};
                    DTME.addRow(fila);
                } while (res.next());
                tablaClientes.setModel(DTME);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscarEncargadoKeyPressed

    private void txtBuscarEncargadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEncargadoKeyReleased
        DTME = new DefaultTableModel(titulosEmpleados, 0);
        tablaEmpleados.setModel(DTME);
        ResultSet res = mostrar(txtBuscarEncargado.getText());
        try {
            if (res.next()) {
                do {
                    Object fila[] = {String.valueOf(Integer.parseInt(res.getString("IDTrabajador"))), res.getString("Nombre"), res.getString("Apellido"), res.getString("Telefono"), res.getString("Direccion"), res.getString("Puesto")};
                    DTME.addRow(fila);
                } while (res.next());
                tablaClientes.setModel(DTME);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscarEncargadoKeyReleased

    public ResultSet mostrar(String str) {
        ResultSet RS = null;
        try {
            Statement ST = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "select * from TRABAJADORES where IDTrabajador like '" + str + "%'" + " OR Nombre like '" + str + "%'" + " OR Apellido like '" + str + "%'";
            RS = ST.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return RS;
    }

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
            java.util.logging.Logger.getLogger(SeleccionarEncargado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeleccionarEncargado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeleccionarEncargado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeleccionarEncargado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            SeleccionarEncargado dialog = new SeleccionarEncargado(0,new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel Titulo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tablaEmpleados;
    private javax.swing.JTextField txtBuscarEncargado;
    // End of variables declaration//GEN-END:variables
}
