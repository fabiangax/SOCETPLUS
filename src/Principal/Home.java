package Principal;

import Maquinaria.AgregarMaq;
import Maquinaria.EliminarMaq;
import Maquinaria.ModificarMaq;
import Maquinaria.SeleccionarVehiculo;
import Objetos.Empleado;
import Objetos.Maquinaria;
import Objetos.Producto;
import Objetos.Utilidades;
import Obras.SeleccionarEncargado;
import Otros.ConexionSQLServer;
import Proveedores.AgregarPago;
import Usuarios.Opciones;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Marco
 */
public class Home extends javax.swing.JFrame {
    Color TX1;
    Color TX2;
    Color BG1;
    Color BG2;
    String folio;
    Connection c = ConexionSQLServer.Conectar();
    String titulosObras[] = {"Matricula", "Nombre", "Cliente", "Fecha de inicio", "Fecha de terminación", "Presupuesto", "Ubicación", "Tipo de obra", "Encargado", "Estatus", "Descripción"};
    String titulosClientes[] = {"Matricula", "Nombre", "Apellido", "Teléfono", "Dirección", "Empresa", "e-mail"};
    String titulosEmpleados[] = {"Matricula", "Nombre", "Apellido", "Teléfono", "Direccion", "Puesto"};
    String titulosInventario[] = {"Código", "Descripción", "Existencia", "Precio"};
    String titulosMaquinaria[] = {"Serie", "Marca", "Modelo", "Año", "Placas"};
    String titulosPagoProveedor[] = {"Fecha de orden", "Folio", "Cantidad", "Estado", "Obra", "Fecha de pago"};
    String titulosProveedor[] = {"Matricula", "Nombre", "Telefono", "Direccion", "e-mail"};
    String titulosCotizacion[] = {"#", "Código", "Cantidad", "Descripción", "Valor unitario", "Valor total"};
    String titulosControl[] = {"Fecha", "Folio orden", "Empleado", "Vehículo", "Odómetro", "Kilometraje", "Combustible", "Litros", "Moneda", "Km/Litro", "Km/Moneda", "Folio pago"};
    DefaultTableModel DTMC, DTME, DTMM, DTMO, DTMP, DTMCONTROL, DTMPRO, DTMPO, DTMPRO2;
    String desc, empleadoVehiculo;
    double TAC = 0, TAF = 0, TAD = 0;
    double TPC = 0, TPF = 0, TC = 0;
    double subtotal = 0, total, iva;
    public static int rowAsignaE, rowMaquinaAsig;
    int cont = 0, rowNomina = 0, rowPD = 0, rowCompraP, rowCombustible = 0;
    JDateChooser fechaInicio = null, fn1, fn2, fn3, fn4;
    String lmao;
    int conk = 1, fechaMaqui;
    public static Producto productoD;
    public static Maquinaria vehiculoD;
    public static Empleado empleadoD;
    String[] datosCliente = new String[7], datosEmpleado = new String[6], datosProducto = new String[4], datosMaquinaria = new String[5], datosObra = new String[11], datosPD = new String[12], datosProveedor = new String[5], fechasCombustible = new String[999], foliosCombustible = new String[999];
    int eliminarP;
    public static String out;

    public Home() {
        //ver los colores que pusimos
        try {
            File file = new File("C:\\Users\\mario\\OneDrive\\Documentos\\NetBeansProjects\\SOCET+\\config.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st=br.readLine();
            br.close();
            String[] rgbs = st.split(",");
            TX1=new Color(Integer.parseInt(rgbs[0]),Integer.parseInt(rgbs[1]),Integer.parseInt(rgbs[2]));
            TX2=new Color(Integer.parseInt(rgbs[3]),Integer.parseInt(rgbs[4]),Integer.parseInt(rgbs[5]));
            BG1=new Color(Integer.parseInt(rgbs[6]),Integer.parseInt(rgbs[7]),Integer.parseInt(rgbs[8]));
            BG2=new Color(Integer.parseInt(rgbs[9]),Integer.parseInt(rgbs[10]),Integer.parseInt(rgbs[11]));
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        //ver en que folio estamos
        try {
            File file = new File("C:\\Users\\mario\\OneDrive\\Documentos\\NetBeansProjects\\SOCET+\\config1.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st=br.readLine();
            br.close();
            folio = st;
        } catch (IOException e) {
            System.out.println("Error al leer el archivo 2.");
        }
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        initComponents();
        jTextField1.setText(folio);
        this.setIconImage(new ImageIcon(getClass().getResource("/Miscelaneo/logo2.png")).getImage());
        panelHome.setVisible(true);
        panelClientes.setVisible(false);
        panelInventario.setVisible(false);
        panelObras.setVisible(false);
        panelMaquinaria.setVisible(false);
        panelCotizacion.setVisible(false);
        panelProveedor.setVisible(false);
        panelEmpleado.setVisible(false);
        panelReporte.setVisible(false);
        setLocationRelativeTo(null);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.
        String output = sdf.format(c.getTime());
        out = output;
        lblFechaHoy.setText("Hoy es: " + output);
        txtCotizacion1.setText(output);
        txtCotizacion.setText(output);
        fechaInicio = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        fechaInicio.setBounds(105, 10, 152, 33);
        rendimientoPanel.add(fechaInicio);

        //Deducciones y percepciones
        fn1 = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        fn1.setBounds(660, 10, 100, 25);
        nominaPanel.add(fn1);
        fn2 = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        fn2.setBounds(810, 10, 100, 25);
        nominaPanel.add(fn2);

        //lista de nomina
        fn3 = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        fn3.setBounds(680, 5, 100, 25);
        listaNomina.add(fn3);
        fn4 = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        fn4.setBounds(830, 5, 100, 25);
        listaNomina.add(fn4);
        String titulosNominaPD[] = {"N°", "Nombre", "Puesto", "Sueldo Base", "Horas extras", "Bono", "Total percepciones", "Faltas", "Préstamos", "Descuentos", "Total deducciones", "Sueldo neto"};
        DefaultTableModel DTMNPD = new DefaultTableModel(titulosNominaPD, 0);
        tablaPD.setModel(DTMNPD);
        try {
            Statement ST = this.c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "select Nombre, Apellido, Puesto from TRABAJADORES";
            ResultSet res = ST.executeQuery(sql);
            int i = 1;
            double cero = 0.0;
            if (res.next()) {
                do {
                    Object fila[] = {String.valueOf(i), res.getString("Nombre") + " " + res.getString("Apellido"), res.getString("Puesto"), cero, cero, cero, cero, cero, cero, cero, cero, cero};
                    DTMNPD.addRow(fila);
                    i++;
                } while (res.next());
                tablaPD.setModel(DTMNPD);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        check5.setVisible(false);
        check6.setVisible(false);
        check7.setVisible(false);
        check8.setVisible(false);
        check9.setVisible(false);
        check10.setVisible(false);
        check11.setVisible(false);
        check12.setVisible(false);
        check13.setVisible(false);
        check14.setVisible(false);
        check15.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listabg = new javax.swing.ButtonGroup();
        izqPanel = new javax.swing.JPanel();
        panelListas = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblPrincipal = new javax.swing.JLabel();
        lblObras = new javax.swing.JLabel();
        lblClientes = new javax.swing.JLabel();
        lblCotizaciones = new javax.swing.JLabel();
        lblInventarios = new javax.swing.JLabel();
        lblMaquinarias = new javax.swing.JLabel();
        lblContaduria = new javax.swing.JLabel();
        lblProveedores = new javax.swing.JLabel();
        lblEmpleados = new javax.swing.JLabel();
        derPanel = new javax.swing.JPanel();
        panelHome = new javax.swing.JPanel();
        btnCamaras = new javax.swing.JButton();
        lblBienvenido = new javax.swing.JLabel();
        lblFechaHoy = new javax.swing.JLabel();
        btnCerrarS = new javax.swing.JButton();
        btnAñadirUsuario = new javax.swing.JButton();
        jScrollPane38 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        fondo = new javax.swing.JLabel();
        panelClientes = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tabbedClientes = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        infoClientes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtBuscarClientes = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        panelObras = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        tabbedObras = new javax.swing.JTabbedPane();
        jPanel15 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        tabbedObras1 = new javax.swing.JTabbedPane();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        verProductosObra = new javax.swing.JPanel();
        jScrollPane32 = new javax.swing.JScrollPane();
        tablaObras5 = new javax.swing.JTable();
        txtBuscarObras3 = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jScrollPane35 = new javax.swing.JScrollPane();
        tablaObras6 = new javax.swing.JTable();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        asignarEmpleadosP = new javax.swing.JPanel();
        jScrollPane28 = new javax.swing.JScrollPane();
        tablaObras3 = new javax.swing.JTable();
        jButton25 = new javax.swing.JButton();
        txtBuscarObras2 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jScrollPane29 = new javax.swing.JScrollPane();
        tablaObras4 = new javax.swing.JTable();
        jLabel50 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        seguimientoPanel = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tablaObras1 = new javax.swing.JTable();
        jButton17 = new javax.swing.JButton();
        txtBuscarObras1 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tablaObras2 = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jButton31 = new javax.swing.JButton();
        infoObras = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaObras = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        btnModificar1 = new javax.swing.JButton();
        txtBuscarObras = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        panelEmpleado = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        tabbedEmpleados = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        infoPanelEmp = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtBuscarEmpleado = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        btnModificar2 = new javax.swing.JButton();
        btnEliminar2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaEmpleados = new javax.swing.JTable();
        nominaPanel = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tablaPD = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        listaNomina = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tablaListaNomina = new javax.swing.JTable();
        jLabel41 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        panelCotizacion = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        fechaCotizacion = new javax.swing.JLabel();
        txtCotizacion = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        fechaCotizacion2 = new javax.swing.JLabel();
        nuevacotPanel = new javax.swing.JPanel();
        datosPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaCC = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        agregarPanel = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaCotizacionP = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton8 = new javax.swing.JButton();
        lblIVA = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        btnEliminarP = new javax.swing.JButton();
        spCantidad = new javax.swing.JSpinner();
        agregarP = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablaDescripcionP = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        panelProveedor = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        tabbedProveedores = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        panelCompra = new javax.swing.JPanel();
        datosObraP = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        tablaCC1 = new javax.swing.JTable();
        jButton18 = new javax.swing.JButton();
        jScrollPane19 = new javax.swing.JScrollPane();
        tablaDP = new javax.swing.JTable();
        jLabel53 = new javax.swing.JLabel();
        jButton21 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tablaProveedores2 = new javax.swing.JTable();
        jButton20 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        subtotalCompra = new javax.swing.JLabel();
        ivaCompra = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        totalCompra = new javax.swing.JLabel();
        infoProveedores = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tablaProveedores = new javax.swing.JTable();
        btnModificarP = new javax.swing.JButton();
        btnAgregarP = new javax.swing.JButton();
        btnEliminarPro = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtBuscarProveedor = new javax.swing.JTextField();
        panelPago = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tablaProveedores1 = new javax.swing.JTable();
        btnAgregarP1 = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        txtBuscarOrden = new javax.swing.JTextField();
        comboProveedores = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        panelMaquinaria = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        tabbedMaquinaria = new javax.swing.JTabbedPane();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        tabbedMaquinaria1 = new javax.swing.JTabbedPane();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        rendimientoPanel = new javax.swing.JPanel();
        idCliente = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaRendimientoC = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        idCliente3 = new javax.swing.JLabel();
        idCliente4 = new javax.swing.JLabel();
        idCliente5 = new javax.swing.JLabel();
        idCliente6 = new javax.swing.JLabel();
        idCliente7 = new javax.swing.JLabel();
        txtKMR = new javax.swing.JTextField();
        txtOdometroR = new javax.swing.JTextField();
        txtVehiculoR = new javax.swing.JTextField();
        idCliente8 = new javax.swing.JLabel();
        idCliente9 = new javax.swing.JLabel();
        txtCombustibleR = new javax.swing.JTextField();
        txtLitrosR = new javax.swing.JTextField();
        txtMonedaR = new javax.swing.JTextField();
        txtKMLR = new javax.swing.JTextField();
        idCliente10 = new javax.swing.JLabel();
        idCliente11 = new javax.swing.JLabel();
        txtKMMR = new javax.swing.JTextField();
        txtFolioPago = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        txtFolioOrden = new javax.swing.JTextField();
        idCliente12 = new javax.swing.JLabel();
        txtBuscar1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        check5 = new javax.swing.JLabel();
        check6 = new javax.swing.JLabel();
        check7 = new javax.swing.JLabel();
        check8 = new javax.swing.JLabel();
        check9 = new javax.swing.JLabel();
        check10 = new javax.swing.JLabel();
        check11 = new javax.swing.JLabel();
        check12 = new javax.swing.JLabel();
        check13 = new javax.swing.JLabel();
        check14 = new javax.swing.JLabel();
        idCliente13 = new javax.swing.JLabel();
        txtVehiculoR1 = new javax.swing.JTextField();
        jButton24 = new javax.swing.JButton();
        check15 = new javax.swing.JLabel();
        cbf2 = new javax.swing.JComboBox<>();
        cbf3 = new javax.swing.JComboBox<>();
        cbf1 = new javax.swing.JComboBox<>();
        idCliente14 = new javax.swing.JLabel();
        idCliente15 = new javax.swing.JLabel();
        cbf4 = new javax.swing.JComboBox<>();
        cbf5 = new javax.swing.JComboBox<>();
        cbf6 = new javax.swing.JComboBox<>();
        infoPanel = new javax.swing.JPanel();
        txtBuscarMaquinaria = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaMaquinaria = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        btnModificar4 = new javax.swing.JButton();
        btnEliminar4 = new javax.swing.JButton();
        asignarMaquinaria = new javax.swing.JPanel();
        jScrollPane36 = new javax.swing.JScrollPane();
        tablaObras7 = new javax.swing.JTable();
        jButton30 = new javax.swing.JButton();
        txtBuscarObras4 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jScrollPane37 = new javax.swing.JScrollPane();
        tablaObras8 = new javax.swing.JTable();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        panelInventario = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel21 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        panelInveObra = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        fechaCotizacion1 = new javax.swing.JLabel();
        txtCotizacion1 = new javax.swing.JTextField();
        nuevacotPanel1 = new javax.swing.JPanel();
        datosPanel1 = new javax.swing.JPanel();
        jScrollPane30 = new javax.swing.JScrollPane();
        tablaCC2 = new javax.swing.JTable();
        jButton26 = new javax.swing.JButton();
        agregarPanel1 = new javax.swing.JPanel();
        jScrollPane31 = new javax.swing.JScrollPane();
        tablaCotizacionP1 = new javax.swing.JTable();
        jButton27 = new javax.swing.JButton();
        btnEliminarP1 = new javax.swing.JButton();
        spCantidad1 = new javax.swing.JSpinner();
        agregarP1 = new javax.swing.JButton();
        jLabel60 = new javax.swing.JLabel();
        jScrollPane33 = new javax.swing.JScrollPane();
        tablaDescripcionP1 = new javax.swing.JTable();
        jButton28 = new javax.swing.JButton();
        jLabel61 = new javax.swing.JLabel();
        jButton29 = new javax.swing.JButton();
        jLabel62 = new javax.swing.JLabel();
        jScrollPane34 = new javax.swing.JScrollPane();
        tablaDescripcionP2 = new javax.swing.JTable();
        jLabel63 = new javax.swing.JLabel();
        infoInventario = new javax.swing.JPanel();
        txtBuscarProducto = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        btnModificar5 = new javax.swing.JButton();
        btnEliminar5 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        panelReporte = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTabbedPane8 = new javax.swing.JTabbedPane();
        jPanel14 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        Tabla1 = new javax.swing.JTable();
        jScrollPane21 = new javax.swing.JScrollPane();
        Tabla6 = new javax.swing.JTable();
        jScrollPane22 = new javax.swing.JScrollPane();
        Tabla2 = new javax.swing.JTable();
        jScrollPane23 = new javax.swing.JScrollPane();
        Tabla3 = new javax.swing.JTable();
        jScrollPane24 = new javax.swing.JScrollPane();
        Tabla4 = new javax.swing.JTable();
        jScrollPane25 = new javax.swing.JScrollPane();
        Tabla5 = new javax.swing.JTable();
        jScrollPane26 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jScrollPane27 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        TotalActivo = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        TotalCapital = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        TotalPasivo = new javax.swing.JTextField();
        TotalPC = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("SOCET+");
        setResizable(false);

        izqPanel.setBackground(new java.awt.Color(19, 99, 168));

        panelListas.setBackground(new java.awt.Color(32, 34, 37));
        panelListas.setLayout(new java.awt.GridLayout(10, 0, 0, 7));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/socetrans.png"))); // NOI18N
        panelListas.add(lblLogo);

        lblPrincipal.setBackground(BG1);
        lblPrincipal.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblPrincipal.setForeground(TX1);
        lblPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/homeb.png"))); // NOI18N
        lblPrincipal.setText("Principal");
        lblPrincipal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblPrincipal.setDisabledIcon(null);
        lblPrincipal.setOpaque(true);
        lblPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPrincipalMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblPrincipalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblPrincipalMouseExited(evt);
            }
        });
        panelListas.add(lblPrincipal);

        lblObras.setBackground(BG2);
        lblObras.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblObras.setForeground(TX2);
        lblObras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblObras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/obra.png"))); // NOI18N
        lblObras.setText("Obras");
        lblObras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblObras.setDisabledIcon(null);
        lblObras.setOpaque(true);
        lblObras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblObrasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblObrasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblObrasMouseExited(evt);
            }
        });
        panelListas.add(lblObras);

        lblClientes.setBackground(BG2);
        lblClientes.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblClientes.setForeground(TX2);
        lblClientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cliente.png"))); // NOI18N
        lblClientes.setText("Clientes");
        lblClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblClientes.setDisabledIcon(null);
        lblClientes.setOpaque(true);
        lblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblClientesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblClientesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblClientesMouseExited(evt);
            }
        });
        panelListas.add(lblClientes);

        lblCotizaciones.setBackground(BG2);
        lblCotizaciones.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblCotizaciones.setForeground(TX2);
        lblCotizaciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCotizaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cotizacion.png"))); // NOI18N
        lblCotizaciones.setText("Cotizaciones");
        lblCotizaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCotizaciones.setDisabledIcon(null);
        lblCotizaciones.setOpaque(true);
        lblCotizaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCotizacionesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCotizacionesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCotizacionesMouseExited(evt);
            }
        });
        panelListas.add(lblCotizaciones);

        lblInventarios.setBackground(BG2);
        lblInventarios.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblInventarios.setForeground(TX2);
        lblInventarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInventarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/inventario.png"))); // NOI18N
        lblInventarios.setText("Inventario");
        lblInventarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblInventarios.setDisabledIcon(null);
        lblInventarios.setOpaque(true);
        lblInventarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblInventariosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblInventariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblInventariosMouseExited(evt);
            }
        });
        panelListas.add(lblInventarios);

        lblMaquinarias.setBackground(BG2);
        lblMaquinarias.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblMaquinarias.setForeground(TX2);
        lblMaquinarias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaquinarias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/maquinaria.png"))); // NOI18N
        lblMaquinarias.setText("Maquinaria");
        lblMaquinarias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMaquinarias.setDisabledIcon(null);
        lblMaquinarias.setOpaque(true);
        lblMaquinarias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMaquinariasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblMaquinariasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblMaquinariasMouseExited(evt);
            }
        });
        panelListas.add(lblMaquinarias);

        lblContaduria.setBackground(BG2);
        lblContaduria.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblContaduria.setForeground(TX2);
        lblContaduria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContaduria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/contrato.png"))); // NOI18N
        lblContaduria.setText("Contaduría");
        lblContaduria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblContaduria.setDisabledIcon(null);
        lblContaduria.setOpaque(true);
        lblContaduria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblContaduriaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblContaduriaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblContaduriaMouseExited(evt);
            }
        });
        panelListas.add(lblContaduria);

        lblProveedores.setBackground(BG2);
        lblProveedores.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblProveedores.setForeground(TX2);
        lblProveedores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/proveedor.png"))); // NOI18N
        lblProveedores.setText("Proveedores");
        lblProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblProveedores.setDisabledIcon(null);
        lblProveedores.setOpaque(true);
        lblProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblProveedoresMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblProveedoresMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblProveedoresMouseExited(evt);
            }
        });
        panelListas.add(lblProveedores);

        lblEmpleados.setBackground(BG2);
        lblEmpleados.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblEmpleados.setForeground(TX2);
        lblEmpleados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/empleado.png"))); // NOI18N
        lblEmpleados.setText("Empleados");
        lblEmpleados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblEmpleados.setDisabledIcon(null);
        lblEmpleados.setOpaque(true);
        lblEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEmpleadosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblEmpleadosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblEmpleadosMouseExited(evt);
            }
        });
        panelListas.add(lblEmpleados);

        javax.swing.GroupLayout izqPanelLayout = new javax.swing.GroupLayout(izqPanel);
        izqPanel.setLayout(izqPanelLayout);
        izqPanelLayout.setHorizontalGroup(
            izqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelListas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        izqPanelLayout.setVerticalGroup(
            izqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(izqPanelLayout.createSequentialGroup()
                .addComponent(panelListas, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        derPanel.setLayout(new java.awt.CardLayout());

        panelHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCamaras.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        btnCamaras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/video-camera.png"))); // NOI18N
        btnCamaras.setText("Acceso a cámaras");
        btnCamaras.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCamaras.setEnabled(false);
        btnCamaras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCamarasActionPerformed(evt);
            }
        });
        panelHome.add(btnCamaras, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 20, 170, 40));

        lblBienvenido.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        lblBienvenido.setForeground(new java.awt.Color(255, 255, 255));
        lblBienvenido.setText("Bienvenido");
        panelHome.add(lblBienvenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        lblFechaHoy.setFont(new java.awt.Font("Segoe UI Light", 2, 24)); // NOI18N
        lblFechaHoy.setForeground(new java.awt.Color(255, 255, 255));
        lblFechaHoy.setText("Hoy es:");
        lblFechaHoy.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelHome.add(lblFechaHoy, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        btnCerrarS.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        btnCerrarS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/logout.png"))); // NOI18N
        btnCerrarS.setText("Cerrar sesión");
        btnCerrarS.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCerrarS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSActionPerformed(evt);
            }
        });
        panelHome.add(btnCerrarS, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 590, 140, 40));

        btnAñadirUsuario.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        btnAñadirUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/settings.png"))); // NOI18N
        btnAñadirUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAñadirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirUsuarioActionPerformed(evt);
            }
        });
        panelHome.add(btnAñadirUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 20, 40, 40));

        jScrollPane38.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane38.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea2.setBackground(new java.awt.Color(47, 49, 54));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jTextArea2.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea2.setRows(5);
        jTextArea2.setText("SOCET+ Ver 1.01\n©2017 Universo7Dev");
        jTextArea2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane38.setViewportView(jTextArea2);

        panelHome.add(jScrollPane38, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 120, 40));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/bgnew.png"))); // NOI18N
        panelHome.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 650));

        derPanel.add(panelHome, "card6");

        panelClientes.setBackground(new java.awt.Color(239, 240, 240));
        panelClientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel1.setText("Clientes");
        panelClientes.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 30, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/clienteb.png"))); // NOI18N
        panelClientes.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        tabbedClientes.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        tabbedClientes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedClientesStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabbedClientes.addTab("Información", jPanel1);

        panelClientes.add(tabbedClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 24));

        tablaClientes.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Matricula", "Nombre", "Apellido", "Telefono", "Dirección", "Empresa", "e-mail"
            }
        ));
        tablaClientes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaClientesFocusLost(evt);
            }
        });
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaClientes);

        jButton1.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/plus.png"))); // NOI18N
        jButton1.setText("Agregar cliente");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/photoshop-fix.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/trash (1).png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txtBuscarClientes.setFont(new java.awt.Font("Segoe UI Light", 2, 14)); // NOI18N
        txtBuscarClientes.setText("Buscar por nombre o matricula de cliente...");
        txtBuscarClientes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarClientesFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarClientesFocusLost(evt);
            }
        });
        txtBuscarClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarClientesKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClientesKeyReleased(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/lupa.png"))); // NOI18N

        javax.swing.GroupLayout infoClientesLayout = new javax.swing.GroupLayout(infoClientes);
        infoClientes.setLayout(infoClientesLayout);
        infoClientesLayout.setHorizontalGroup(
            infoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoClientesLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(infoClientesLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(7, 7, 7)
                        .addComponent(txtBuscarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addGap(17, 17, 17))))
        );
        infoClientesLayout.setVerticalGroup(
            infoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelClientes.add(infoClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 940, 560));

        derPanel.add(panelClientes, "card4");

        panelObras.setBackground(new java.awt.Color(239, 240, 240));
        panelObras.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/obrab.png"))); // NOI18N
        panelObras.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        tabbedObras.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabbedObras.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        tabbedObras.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedObrasStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 965, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabbedObras.addTab("Información", jPanel15);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 965, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabbedObras.addTab("Seguimiento", jPanel18);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 965, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabbedObras.addTab("Asignar empleados", jPanel20);

        tabbedObras1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        tabbedObras1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedObras1StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabbedObras1.addTab("Información", jPanel22);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabbedObras1.addTab("Seguimiento", jPanel23);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabbedObras1.addTab("Asignar empleados", jPanel24);

        tabbedObras.addTab("Productos en obra", tabbedObras1);

        panelObras.add(tabbedObras, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 24));

        tablaObras5.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        tablaObras5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricula", "Nombre", "Cliente"
            }
        ));
        tablaObras5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaObras5FocusLost(evt);
            }
        });
        tablaObras5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaObras5MouseClicked(evt);
            }
        });
        jScrollPane32.setViewportView(tablaObras5);

        txtBuscarObras3.setFont(new java.awt.Font("Segoe UI Light", 2, 14)); // NOI18N
        txtBuscarObras3.setText("Buscar por cliente, nombre, o matricula de obra...");
        txtBuscarObras3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarObras3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarObras3FocusLost(evt);
            }
        });
        txtBuscarObras3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarObras3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarObras3KeyReleased(evt);
            }
        });

        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/lupa.png"))); // NOI18N

        tablaObras6.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        tablaObras6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricula Obra", "Matricula Trabajador", "Codigo Producto", "Piezas", "Descripcion", "Fecha"
            }
        ));
        tablaObras6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaObras6FocusLost(evt);
            }
        });
        tablaObras6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaObras6MouseClicked(evt);
            }
        });
        jScrollPane35.setViewportView(tablaObras6);

        jLabel58.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel58.setText("Obras");

        jLabel59.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel59.setText("Productos utilizados en la obra");

        javax.swing.GroupLayout verProductosObraLayout = new javax.swing.GroupLayout(verProductosObra);
        verProductosObra.setLayout(verProductosObraLayout);
        verProductosObraLayout.setHorizontalGroup(
            verProductosObraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(verProductosObraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(verProductosObraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(verProductosObraLayout.createSequentialGroup()
                        .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane35, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE))
                    .addGroup(verProductosObraLayout.createSequentialGroup()
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarObras3, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(verProductosObraLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel59)
                .addGap(223, 223, 223))
        );
        verProductosObraLayout.setVerticalGroup(
            verProductosObraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, verProductosObraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(verProductosObraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscarObras3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(verProductosObraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(jLabel59))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(verProductosObraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane35, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                    .addComponent(jScrollPane32))
                .addContainerGap())
        );

        panelObras.add(verProductosObra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 970, 560));

        tablaObras3.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        tablaObras3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricula", "Nombre", "Cliente"
            }
        ));
        tablaObras3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaObras3FocusLost(evt);
            }
        });
        tablaObras3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaObras3MouseClicked(evt);
            }
        });
        jScrollPane28.setViewportView(tablaObras3);

        jButton25.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jButton25.setText("Asignar empleado");
        jButton25.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        txtBuscarObras2.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        txtBuscarObras2.setText("Buscar por cliente, nombre, o matricula de obra...");
        txtBuscarObras2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarObras2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarObras2FocusLost(evt);
            }
        });
        txtBuscarObras2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarObras2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarObras2KeyReleased(evt);
            }
        });

        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/lupa.png"))); // NOI18N

        tablaObras4.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        tablaObras4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricula", "Empleado"
            }
        ));
        tablaObras4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaObras4FocusLost(evt);
            }
        });
        tablaObras4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaObras4MouseClicked(evt);
            }
        });
        jScrollPane29.setViewportView(tablaObras4);

        jLabel50.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel50.setText("Obras");

        jLabel52.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel52.setText("Empleados en la obra");

        javax.swing.GroupLayout asignarEmpleadosPLayout = new javax.swing.GroupLayout(asignarEmpleadosP);
        asignarEmpleadosP.setLayout(asignarEmpleadosPLayout);
        asignarEmpleadosPLayout.setHorizontalGroup(
            asignarEmpleadosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(asignarEmpleadosPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(asignarEmpleadosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(asignarEmpleadosPLayout.createSequentialGroup()
                        .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane29, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE))
                    .addGroup(asignarEmpleadosPLayout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarObras2, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton25)))
                .addContainerGap())
            .addGroup(asignarEmpleadosPLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel52)
                .addGap(280, 280, 280))
        );
        asignarEmpleadosPLayout.setVerticalGroup(
            asignarEmpleadosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, asignarEmpleadosPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(asignarEmpleadosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(asignarEmpleadosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscarObras2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel49))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(asignarEmpleadosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(jLabel52))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(asignarEmpleadosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane29, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                    .addComponent(jScrollPane28))
                .addContainerGap())
        );

        panelObras.add(asignarEmpleadosP, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 970, 560));

        tablaObras1.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        tablaObras1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricula", "Nombre", "Cliente"
            }
        ));
        tablaObras1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaObras1FocusLost(evt);
            }
        });
        tablaObras1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaObras1MouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tablaObras1);

        jButton17.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jButton17.setText("Agregar seguimiento");
        jButton17.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        txtBuscarObras1.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        txtBuscarObras1.setText("Buscar por cliente, nombre, o matricula de obra...");
        txtBuscarObras1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarObras1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarObras1FocusLost(evt);
            }
        });
        txtBuscarObras1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarObras1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarObras1KeyReleased(evt);
            }
        });

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/lupa.png"))); // NOI18N

        tablaObras2.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        tablaObras2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDSeguimiento", "Fecha", "Pago", "Descripcion"
            }
        ));
        tablaObras2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaObras2FocusLost(evt);
            }
        });
        tablaObras2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaObras2MouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(tablaObras2);

        jLabel22.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel22.setText("Obras");

        jLabel47.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel47.setText("Seguimientos");

        jButton31.setText("Imprimir seguimientos");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout seguimientoPanelLayout = new javax.swing.GroupLayout(seguimientoPanel);
        seguimientoPanel.setLayout(seguimientoPanelLayout);
        seguimientoPanelLayout.setHorizontalGroup(
            seguimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seguimientoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(seguimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(seguimientoPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(seguimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(seguimientoPanelLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE))
                            .addGroup(seguimientoPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(seguimientoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarObras1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton17)))
                .addContainerGap())
            .addGroup(seguimientoPanelLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel47)
                .addGap(280, 280, 280))
        );
        seguimientoPanelLayout.setVerticalGroup(
            seguimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, seguimientoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(seguimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(seguimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscarObras1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(seguimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel47))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(seguimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(seguimientoPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        panelObras.add(seguimientoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 970, 560));

        tablaObras.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        tablaObras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Matricula", "Nombre", "Cliente", "Fecha de inicio", "Fecha de terminación", "Presupuesto", "Ubicación", "Tipo de obra", "Encargado", "Estatus", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaObras.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaObrasFocusLost(evt);
            }
        });
        tablaObras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaObrasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaObras);

        jButton2.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jButton2.setText("Agregar obra");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnModificar1.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        btnModificar1.setText("Modificar");
        btnModificar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnModificar1.setEnabled(false);
        btnModificar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar1ActionPerformed(evt);
            }
        });

        txtBuscarObras.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        txtBuscarObras.setText("Buscar por cliente, nombre, estatus o matricula de obra...");
        txtBuscarObras.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarObrasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarObrasFocusLost(evt);
            }
        });
        txtBuscarObras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarObrasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarObrasKeyReleased(evt);
            }
        });

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/lupa.png"))); // NOI18N

        javax.swing.GroupLayout infoObrasLayout = new javax.swing.GroupLayout(infoObras);
        infoObras.setLayout(infoObrasLayout);
        infoObrasLayout.setHorizontalGroup(
            infoObrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoObrasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoObrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoObrasLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoObrasLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(10, 10, 10)
                        .addComponent(txtBuscarObras, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar1)
                        .addGap(15, 15, 15))))
        );
        infoObrasLayout.setVerticalGroup(
            infoObrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoObrasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoObrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoObrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnModificar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscarObras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelObras.add(infoObras, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 940, 560));

        jLabel34.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel34.setText("Obras");
        panelObras.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 30, -1, -1));

        derPanel.add(panelObras, "card4");

        panelEmpleado.setBackground(new java.awt.Color(239, 240, 240));
        panelEmpleado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/empleadob.png"))); // NOI18N
        panelEmpleado.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        tabbedEmpleados.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabbedEmpleados.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        tabbedEmpleados.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedEmpleadosStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 935, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabbedEmpleados.addTab("Información", jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 935, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabbedEmpleados.addTab("Reporte de nómina", jPanel6);

        jTabbedPane6.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jTabbedPane6.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane6StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane6.addTab("Información", jPanel10);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane6.addTab("Reporte de nómina", jPanel13);

        tabbedEmpleados.addTab("Lista de nómina", jTabbedPane6);

        panelEmpleado.add(tabbedEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 24));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/lupa.png"))); // NOI18N

        txtBuscarEmpleado.setFont(new java.awt.Font("Segoe UI Light", 2, 14)); // NOI18N
        txtBuscarEmpleado.setText("Buscar por nombre o matricula de empleado...");
        txtBuscarEmpleado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarEmpleadoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarEmpleadoFocusLost(evt);
            }
        });
        txtBuscarEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarEmpleadoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarEmpleadoKeyReleased(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/plus.png"))); // NOI18N
        jButton3.setText("Agregar empleado");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnModificar2.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        btnModificar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/photoshop-fix.png"))); // NOI18N
        btnModificar2.setText("Modificar");
        btnModificar2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnModificar2.setEnabled(false);
        btnModificar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar2ActionPerformed(evt);
            }
        });

        btnEliminar2.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        btnEliminar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/trash (1).png"))); // NOI18N
        btnEliminar2.setText("Eliminar");
        btnEliminar2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminar2.setEnabled(false);
        btnEliminar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar2ActionPerformed(evt);
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
                "Matricula", "Nombre", "Apellido", "Teléfono", "Dirección", "Puesto"
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

        javax.swing.GroupLayout infoPanelEmpLayout = new javax.swing.GroupLayout(infoPanelEmp);
        infoPanelEmp.setLayout(infoPanelEmpLayout);
        infoPanelEmpLayout.setHorizontalGroup(
            infoPanelEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelEmpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoPanelEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(infoPanelEmpLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(txtBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar2)
                        .addGap(8, 8, 8)
                        .addComponent(btnEliminar2)))
                .addContainerGap())
        );
        infoPanelEmpLayout.setVerticalGroup(
            infoPanelEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoPanelEmpLayout.createSequentialGroup()
                .addGap(0, 7, Short.MAX_VALUE)
                .addGroup(infoPanelEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnModificar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelEmpleado.add(infoPanelEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 970, 540));

        tablaPD.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        tablaPD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Nombre", "Puesto", "Sueldo Base", "Horas extras", "Bono", "Total percepciones", "Faltas", "Préstamos", "Descuentos", "Total Deducciones", "Sueldo Neto"
            }
        ));
        tablaPD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPDMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tablaPD);

        jLabel17.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel17.setText("Percepciones");

        jLabel31.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel31.setText("Deducciones");

        jLabel32.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel32.setText("Periodo del:");

        jLabel37.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel37.setText("al");

        jButton14.setText("Eliminar fila");
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton16.setText("Imprimir");
        jButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout nominaPanelLayout = new javax.swing.GroupLayout(nominaPanel);
        nominaPanel.setLayout(nominaPanelLayout);
        nominaPanelLayout.setHorizontalGroup(
            nominaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nominaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nominaPanelLayout.createSequentialGroup()
                        .addGap(0, 353, Short.MAX_VALUE)
                        .addGroup(nominaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nominaPanelLayout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addGap(127, 127, 127)
                                .addComponent(jLabel37)
                                .addGap(133, 133, 133))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nominaPanelLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(244, 244, 244)
                                .addComponent(jLabel31)
                                .addGap(157, 157, 157))))
                    .addGroup(nominaPanelLayout.createSequentialGroup()
                        .addGroup(nominaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane12)
                            .addGroup(nominaPanelLayout.createSequentialGroup()
                                .addComponent(jButton14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        nominaPanelLayout.setVerticalGroup(
            nominaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nominaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nominaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel37))
                .addGap(21, 21, 21)
                .addGroup(nominaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel31))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(nominaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelEmpleado.add(nominaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 940, 550));

        tablaListaNomina.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        tablaListaNomina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "N°", "Nombre", "Puesto"
            }
        ));
        tablaListaNomina.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaListaNominaFocusLost(evt);
            }
        });
        tablaListaNomina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaListaNominaMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tablaListaNomina);

        jLabel41.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel41.setText("Periodo del:");

        jLabel29.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel29.setText("Lista de nómina");

        jLabel43.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel43.setText("al");

        jButton10.setText("Imprimir");
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton12.setText("Eliminar empleado");
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout listaNominaLayout = new javax.swing.GroupLayout(listaNomina);
        listaNomina.setLayout(listaNominaLayout);
        listaNominaLayout.setHorizontalGroup(
            listaNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listaNominaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(listaNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane13)
                    .addGroup(listaNominaLayout.createSequentialGroup()
                        .addGap(306, 306, 306)
                        .addComponent(jLabel29)
                        .addGap(77, 77, 77)
                        .addComponent(jLabel41)
                        .addGap(0, 117, Short.MAX_VALUE)))
                .addGap(6, 6, 6)
                .addGroup(listaNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel43)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
                .addContainerGap())
        );
        listaNominaLayout.setVerticalGroup(
            listaNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listaNominaLayout.createSequentialGroup()
                .addGroup(listaNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listaNominaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(listaNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(jLabel43))))
                .addGap(24, 24, 24)
                .addGroup(listaNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(listaNominaLayout.createSequentialGroup()
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton12)))
                .addContainerGap())
        );

        panelEmpleado.add(listaNomina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 940, 550));

        jLabel30.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel30.setText("Empleados");
        panelEmpleado.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 30, -1, -1));

        derPanel.add(panelEmpleado, "card4");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cotizacionb.png"))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel13.setText("Cotizaciones");

        jTabbedPane4.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 975, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("Nueva cotización", jPanel8);

        fechaCotizacion.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        fechaCotizacion.setText("Fecha:");

        txtCotizacion.setEnabled(false);

        jTextField1.setEnabled(false);

        fechaCotizacion2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        fechaCotizacion2.setText("Cotización:");

        datosPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 14))); // NOI18N
        datosPanel.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N

        tablaCC.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        tablaCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricula", "Nombre", "Apellido", "Teléfono", "Dirección", "Empresa", "e-mail"
            }
        ));
        tablaCC.setShowHorizontalLines(false);
        jScrollPane5.setViewportView(tablaCC);

        jButton6.setText("...");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout datosPanelLayout = new javax.swing.GroupLayout(datosPanel);
        datosPanel.setLayout(datosPanelLayout);
        datosPanelLayout.setHorizontalGroup(
            datosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
        );
        datosPanelLayout.setVerticalGroup(
            datosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        tablaCotizacionP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Código", "Cantidad", "Descripción", "Valor unitario", "Valor total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaCotizacionP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tablaCotizacionPFocusGained(evt);
            }
        });
        tablaCotizacionP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCotizacionPMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tablaCotizacionP);

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel7.setText("Subtotal:");

        jLabel27.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel27.setText("I.V.A.");

        jLabel28.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel28.setText("TOTAL:");

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane8.setViewportView(jTextArea1);

        jButton8.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/print.png"))); // NOI18N
        jButton8.setText("Imprimir");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        lblIVA.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        lblIVA.setText("$0.0");

        lblSubtotal.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        lblSubtotal.setText("$0.0");

        lblTotal.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        lblTotal.setText("$0.0");

        jLabel33.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel33.setText("Observaciones:");

        btnEliminarP.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        btnEliminarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/trash (1).png"))); // NOI18N
        btnEliminarP.setText("Eliminar producto");
        btnEliminarP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminarP.setEnabled(false);
        btnEliminarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPActionPerformed(evt);
            }
        });

        spCantidad.setEnabled(false);
        spCantidad.setValue(1
        );

        agregarP.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        agregarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/plus.png"))); // NOI18N
        agregarP.setText("<html>\n<center>Agregar<br>producto</center>\n</html>");
        agregarP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        agregarP.setEnabled(false);
        agregarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel35.setText("Descripción:");

        tablaDescripcionP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descripción", "Existencia", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaDescripcionP.setShowHorizontalLines(false);
        jScrollPane9.setViewportView(tablaDescripcionP);

        jButton11.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/search.png"))); // NOI18N
        jButton11.setText("<html>\n<center>Buscar<br>producto</center>\n</html>");
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel36.setText("Cantidad:");

        javax.swing.GroupLayout agregarPanelLayout = new javax.swing.GroupLayout(agregarPanel);
        agregarPanel.setLayout(agregarPanelLayout);
        agregarPanelLayout.setHorizontalGroup(
            agregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(agregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(agregarPanelLayout.createSequentialGroup()
                        .addGroup(agregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(agregarPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(agregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel36)
                                    .addComponent(spCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11))
                            .addGroup(agregarPanelLayout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addComponent(agregarP, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(agregarPanelLayout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarP))
                    .addGroup(agregarPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane8)
                        .addGap(18, 18, 18)
                        .addGroup(agregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(agregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel27)
                                .addComponent(jLabel7)))
                        .addGap(18, 18, 18)
                        .addGroup(agregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSubtotal)
                            .addComponent(lblIVA)
                            .addGroup(agregarPanelLayout.createSequentialGroup()
                                .addComponent(lblTotal)
                                .addGap(52, 52, 52)
                                .addComponent(jButton8)))))
                .addContainerGap())
        );
        agregarPanelLayout.setVerticalGroup(
            agregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, agregarPanelLayout.createSequentialGroup()
                .addGroup(agregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(agregarPanelLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(agregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(agregarPanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(agregarP, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, agregarPanelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(agregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel33)
                    .addComponent(btnEliminarP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(agregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(agregarPanelLayout.createSequentialGroup()
                        .addGroup(agregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblSubtotal))
                        .addGap(29, 29, 29)
                        .addGroup(agregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(lblIVA))
                        .addGap(36, 36, 36)
                        .addGroup(agregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(lblTotal))
                        .addGap(0, 25, Short.MAX_VALUE))
                    .addComponent(jScrollPane8)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, agregarPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout nuevacotPanelLayout = new javax.swing.GroupLayout(nuevacotPanel);
        nuevacotPanel.setLayout(nuevacotPanelLayout);
        nuevacotPanelLayout.setHorizontalGroup(
            nuevacotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 968, Short.MAX_VALUE)
            .addGroup(nuevacotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(datosPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nuevacotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(agregarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        nuevacotPanelLayout.setVerticalGroup(
            nuevacotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
            .addGroup(nuevacotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(nuevacotPanelLayout.createSequentialGroup()
                    .addComponent(datosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 472, Short.MAX_VALUE)))
            .addGroup(nuevacotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nuevacotPanelLayout.createSequentialGroup()
                    .addGap(0, 67, Short.MAX_VALUE)
                    .addComponent(agregarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout panelCotizacionLayout = new javax.swing.GroupLayout(panelCotizacion);
        panelCotizacion.setLayout(panelCotizacionLayout);
        panelCotizacionLayout.setHorizontalGroup(
            panelCotizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
            .addGroup(panelCotizacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCotizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCotizacionLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fechaCotizacion2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fechaCotizacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCotizacionLayout.createSequentialGroup()
                        .addComponent(nuevacotPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        panelCotizacionLayout.setVerticalGroup(
            panelCotizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCotizacionLayout.createSequentialGroup()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(panelCotizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCotizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(fechaCotizacion)
                        .addComponent(txtCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fechaCotizacion2))
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addComponent(nuevacotPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        derPanel.add(panelCotizacion, "card7");

        panelProveedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/proveedorb.png"))); // NOI18N
        panelProveedor.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 29, -1, -1));

        jLabel45.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel45.setText("Proveedores");
        panelProveedor.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 29, -1, -1));

        tabbedProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabbedProveedores.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        tabbedProveedores.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedProveedoresStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabbedProveedores.addTab("Infiormación", jPanel11);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabbedProveedores.addTab("Reporte de pagos", jPanel19);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabbedProveedores.addTab("Orden de compra", jPanel12);

        panelProveedor.add(tabbedProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 25));

        datosObraP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de obra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 14))); // NOI18N

        tablaCC1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Matricula", "Nombre", "Cliente"
            }
        ));
        tablaCC1.setShowHorizontalLines(false);
        jScrollPane17.setViewportView(tablaCC1);

        jButton18.setText("...");
        jButton18.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        tablaDP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Código", "Proveedor", "Telefono", "Direccion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaDP.setShowHorizontalLines(false);
        jScrollPane19.setViewportView(tablaDP);

        jLabel53.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel53.setText("Datos de proveedor:");

        jButton21.setText("...");
        jButton21.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout datosObraPLayout = new javax.swing.GroupLayout(datosObraP);
        datosObraP.setLayout(datosObraPLayout);
        datosObraPLayout.setHorizontalGroup(
            datosObraPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosObraPLayout.createSequentialGroup()
                .addComponent(jLabel53)
                .addContainerGap())
            .addGroup(datosObraPLayout.createSequentialGroup()
                .addGroup(datosObraPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane19)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosObraPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        datosObraPLayout.setVerticalGroup(
            datosObraPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosObraPLayout.createSequentialGroup()
                .addGroup(datosObraPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosObraPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        tablaProveedores2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "0", "0", "0"}
            },
            new String [] {
                "Descripción", "Cantidad", "Valor unitario", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaProveedores2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProveedores2MouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(tablaProveedores2);

        jButton20.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/print.png"))); // NOI18N
        jButton20.setText("Imprimir");
        jButton20.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton22.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/plus.png"))); // NOI18N
        jButton22.setText("Agregar fila");
        jButton22.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/trash (1).png"))); // NOI18N
        jButton23.setText("Eliminar fila");
        jButton23.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel48.setText("Subtotal:");

        jLabel51.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel51.setText("I.V.A.");

        subtotalCompra.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        subtotalCompra.setText("0.0");

        ivaCompra.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        ivaCompra.setText("0.0");

        jLabel55.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel55.setText("TOTAL:");

        totalCompra.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        totalCompra.setText("0.0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel51)
                            .addComponent(jLabel55)
                            .addComponent(jLabel48))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(totalCompra)
                            .addComponent(ivaCompra, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(subtotalCompra, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(59, 59, 59))
                    .addComponent(jButton20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(subtotalCompra)
                            .addComponent(jLabel48))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ivaCompra)
                            .addComponent(jLabel51))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalCompra)
                            .addComponent(jLabel55))
                        .addGap(18, 18, 18)
                        .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelCompraLayout = new javax.swing.GroupLayout(panelCompra);
        panelCompra.setLayout(panelCompraLayout);
        panelCompraLayout.setHorizontalGroup(
            panelCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(datosObraP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelCompraLayout.setVerticalGroup(
            panelCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCompraLayout.createSequentialGroup()
                .addComponent(datosObraP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelProveedor.add(panelCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 940, 560));

        tablaProveedores.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        tablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricula", "Nombre", "Telefono", "Dirección", "e-mail"
            }
        ));
        tablaProveedores.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaProveedoresFocusLost(evt);
            }
        });
        tablaProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProveedoresMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tablaProveedores);

        btnModificarP.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        btnModificarP.setText("Modificar");
        btnModificarP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnModificarP.setEnabled(false);
        btnModificarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarPActionPerformed(evt);
            }
        });

        btnAgregarP.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        btnAgregarP.setText("Agregar proveedor");
        btnAgregarP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAgregarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPActionPerformed(evt);
            }
        });

        btnEliminarPro.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        btnEliminarPro.setText("Eliminar");
        btnEliminarPro.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminarPro.setEnabled(false);
        btnEliminarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProActionPerformed(evt);
            }
        });

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/lupa.png"))); // NOI18N

        txtBuscarProveedor.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        txtBuscarProveedor.setText("Buscar por nombre o matricula de proveedor...");
        txtBuscarProveedor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarProveedorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarProveedorFocusLost(evt);
            }
        });
        txtBuscarProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarProveedorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProveedorKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout infoProveedoresLayout = new javax.swing.GroupLayout(infoProveedores);
        infoProveedores.setLayout(infoProveedoresLayout);
        infoProveedoresLayout.setHorizontalGroup(
            infoProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoProveedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoProveedoresLayout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoProveedoresLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregarP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarPro)
                        .addGap(17, 17, 17))))
        );
        infoProveedoresLayout.setVerticalGroup(
            infoProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoProveedoresLayout.createSequentialGroup()
                .addGroup(infoProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoProveedoresLayout.createSequentialGroup()
                        .addContainerGap(16, Short.MAX_VALUE)
                        .addGroup(infoProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregarP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnModificarP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminarPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBuscarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(infoProveedoresLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelProveedor.add(infoProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 940, 560));

        tablaProveedores1.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        tablaProveedores1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha de orden", "Folio", "Cantidad", "Estado", "Obra", "Fecha de pago"
            }
        ));
        tablaProveedores1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaProveedores1FocusLost(evt);
            }
        });
        tablaProveedores1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProveedores1MouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tablaProveedores1);

        btnAgregarP1.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        btnAgregarP1.setText("Agregar pago");
        btnAgregarP1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAgregarP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarP1ActionPerformed(evt);
            }
        });

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/lupa.png"))); // NOI18N

        txtBuscarOrden.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        txtBuscarOrden.setText("Buscar por folio de orden....");
        txtBuscarOrden.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarOrdenFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarOrdenFocusLost(evt);
            }
        });
        txtBuscarOrden.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarOrdenKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarOrdenKeyReleased(evt);
            }
        });

        comboProveedores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboProveedores.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboProveedoresItemStateChanged(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel15.setText("Proveedor:");

        javax.swing.GroupLayout panelPagoLayout = new javax.swing.GroupLayout(panelPago);
        panelPago.setLayout(panelPagoLayout);
        panelPagoLayout.setHorizontalGroup(
            panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPagoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14)
                    .addGroup(panelPagoLayout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(btnAgregarP1)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        panelPagoLayout.setVerticalGroup(
            panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPagoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPagoLayout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPagoLayout.createSequentialGroup()
                        .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboProveedores)
                            .addComponent(jLabel15)
                            .addComponent(btnAgregarP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15)))
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelProveedor.add(panelPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 940, 560));

        derPanel.add(panelProveedor, "card7");

        panelMaquinaria.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/maquinariab.png"))); // NOI18N
        panelMaquinaria.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 29, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel19.setText("Maquinaria");
        panelMaquinaria.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 29, -1, -1));

        tabbedMaquinaria.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabbedMaquinaria.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        tabbedMaquinaria.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedMaquinariaStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 915, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabbedMaquinaria.addTab("Infiormación", jPanel16);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 915, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabbedMaquinaria.addTab("Rendimiento de combustible", jPanel17);

        tabbedMaquinaria1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        tabbedMaquinaria1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedMaquinaria1StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabbedMaquinaria1.addTab("Infiormación", jPanel25);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabbedMaquinaria1.addTab("Rendimiento de combustible", jPanel26);

        tabbedMaquinaria.addTab("Asignar maquinaria a trabajador", tabbedMaquinaria1);

        panelMaquinaria.add(tabbedMaquinaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 25));

        idCliente.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente.setText("Fecha:");

        tablaRendimientoC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Folio orden", "Empleado", "Vehículo", "Odómetro", "Kilometraje", "Combustible", "Litros", "Moneda", "Km/Litro", "Km/Moneda", "Folio pago"
            }
        ));
        tablaRendimientoC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaRendimientoCMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaRendimientoC);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/print.png"))); // NOI18N
        jButton4.setText("Imprimir");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        idCliente3.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente3.setText("Vehículo:");

        idCliente4.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente4.setText("Odómetro:");

        idCliente5.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente5.setText("Kilometraje:");

        idCliente6.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente6.setText("Combustible:");

        idCliente7.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente7.setText("Litros:");

        txtKMR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKMRActionPerformed(evt);
            }
        });
        txtKMR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtKMRKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKMRKeyReleased(evt);
            }
        });

        txtOdometroR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOdometroRActionPerformed(evt);
            }
        });
        txtOdometroR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtOdometroRKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOdometroRKeyReleased(evt);
            }
        });

        txtVehiculoR.setEnabled(false);
        txtVehiculoR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVehiculoRActionPerformed(evt);
            }
        });
        txtVehiculoR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVehiculoRKeyReleased(evt);
            }
        });

        idCliente8.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente8.setText("Moneda:");

        idCliente9.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente9.setText("Km/Litro:");

        txtCombustibleR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCombustibleRActionPerformed(evt);
            }
        });
        txtCombustibleR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCombustibleRKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCombustibleRKeyReleased(evt);
            }
        });

        txtLitrosR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLitrosRActionPerformed(evt);
            }
        });
        txtLitrosR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLitrosRKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLitrosRKeyReleased(evt);
            }
        });

        txtMonedaR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMonedaRActionPerformed(evt);
            }
        });
        txtMonedaR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMonedaRKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMonedaRKeyReleased(evt);
            }
        });

        txtKMLR.setEnabled(false);
        txtKMLR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKMLRActionPerformed(evt);
            }
        });
        txtKMLR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKMLRKeyReleased(evt);
            }
        });

        idCliente10.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente10.setText("Km/Moneda:");

        idCliente11.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente11.setText("Folio pago:");

        txtKMMR.setEnabled(false);
        txtKMMR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKMMRActionPerformed(evt);
            }
        });
        txtKMMR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKMMRKeyReleased(evt);
            }
        });

        txtFolioPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFolioPagoActionPerformed(evt);
            }
        });
        txtFolioPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFolioPagoKeyReleased(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/plus.png"))); // NOI18N
        jButton5.setText("Añadir");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        txtFolioOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFolioOrdenActionPerformed(evt);
            }
        });
        txtFolioOrden.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFolioOrdenKeyReleased(evt);
            }
        });

        idCliente12.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente12.setText("Folio orden:");

        txtBuscar1.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        txtBuscar1.setText("Buscar por folio orden o vehículo...");
        txtBuscar1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscar1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscar1FocusLost(evt);
            }
        });
        txtBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscar1ActionPerformed(evt);
            }
        });
        txtBuscar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscar1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscar1KeyReleased(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/lupa.png"))); // NOI18N

        jButton9.setText("...");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        check5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N

        check6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N

        check7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N

        check8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N

        check9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N

        check10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N

        check11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N

        check12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N

        check13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N

        check14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N

        idCliente13.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente13.setText("Empleado:");

        txtVehiculoR1.setEnabled(false);
        txtVehiculoR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVehiculoR1ActionPerformed(evt);
            }
        });
        txtVehiculoR1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVehiculoR1KeyReleased(evt);
            }
        });

        jButton24.setText("...");
        jButton24.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        check15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/correct.png"))); // NOI18N

        cbf2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cbf2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbf2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbf2ItemStateChanged(evt);
            }
        });

        cbf3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));
        cbf3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbf3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbf3ItemStateChanged(evt);
            }
        });

        cbf1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cbf1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbf1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbf1ItemStateChanged(evt);
            }
        });
        cbf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbf1ActionPerformed(evt);
            }
        });

        idCliente14.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente14.setText("Entre:");

        idCliente15.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        idCliente15.setText("y");

        cbf4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cbf4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbf4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbf4ItemStateChanged(evt);
            }
        });

        cbf5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cbf5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbf5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbf5ItemStateChanged(evt);
            }
        });

        cbf6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));
        cbf6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbf6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbf6ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout rendimientoPanelLayout = new javax.swing.GroupLayout(rendimientoPanel);
        rendimientoPanel.setLayout(rendimientoPanelLayout);
        rendimientoPanelLayout.setHorizontalGroup(
            rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rendimientoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(rendimientoPanelLayout.createSequentialGroup()
                        .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(rendimientoPanelLayout.createSequentialGroup()
                                .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(rendimientoPanelLayout.createSequentialGroup()
                                        .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(idCliente4)
                                            .addComponent(idCliente3))
                                        .addGap(21, 21, 21))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rendimientoPanelLayout.createSequentialGroup()
                                        .addComponent(idCliente13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtOdometroR, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(rendimientoPanelLayout.createSequentialGroup()
                                        .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtVehiculoR1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtVehiculoR, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(rendimientoPanelLayout.createSequentialGroup()
                                .addComponent(idCliente5)
                                .addGap(10, 10, 10)
                                .addComponent(txtKMR, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(check6)
                            .addGroup(rendimientoPanelLayout.createSequentialGroup()
                                .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(check5)
                                    .addComponent(check7)
                                    .addComponent(check15))
                                .addGap(47, 47, 47)
                                .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(idCliente7)
                                    .addComponent(idCliente6)
                                    .addComponent(idCliente8)
                                    .addComponent(idCliente9))
                                .addGap(9, 9, 9)
                                .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(rendimientoPanelLayout.createSequentialGroup()
                                        .addComponent(txtCombustibleR, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(check8))
                                    .addGroup(rendimientoPanelLayout.createSequentialGroup()
                                        .addComponent(txtMonedaR, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(check10))
                                    .addGroup(rendimientoPanelLayout.createSequentialGroup()
                                        .addComponent(txtKMLR, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(check11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(rendimientoPanelLayout.createSequentialGroup()
                                        .addComponent(txtLitrosR, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(check9))
                                    .addGroup(rendimientoPanelLayout.createSequentialGroup()
                                        .addComponent(idCliente14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbf1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbf2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbf3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(16, 16, Short.MAX_VALUE)
                        .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rendimientoPanelLayout.createSequentialGroup()
                                .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(rendimientoPanelLayout.createSequentialGroup()
                                        .addComponent(idCliente11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtFolioPago, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(rendimientoPanelLayout.createSequentialGroup()
                                        .addComponent(idCliente10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtKMMR, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(check12)
                                    .addComponent(check13))
                                .addGap(31, 31, 31))
                            .addGroup(rendimientoPanelLayout.createSequentialGroup()
                                .addComponent(idCliente12)
                                .addGap(18, 18, 18)
                                .addComponent(txtFolioOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(check14))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rendimientoPanelLayout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9))))
                    .addGroup(rendimientoPanelLayout.createSequentialGroup()
                        .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idCliente)
                            .addGroup(rendimientoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(303, 303, 303)
                                .addComponent(idCliente15)
                                .addGap(18, 18, 18)
                                .addComponent(cbf4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbf5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbf6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rendimientoPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        rendimientoPanelLayout.setVerticalGroup(
            rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rendimientoPanelLayout.createSequentialGroup()
                .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rendimientoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(idCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtVehiculoR1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(idCliente13))
                            .addComponent(check15, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(check5)
                            .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtVehiculoR, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(idCliente3))))
                    .addGroup(rendimientoPanelLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(check9)
                            .addGroup(rendimientoPanelLayout.createSequentialGroup()
                                .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(check8)
                                    .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(idCliente6)
                                        .addComponent(txtCombustibleR, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtLitrosR, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(idCliente7)))
                            .addGroup(rendimientoPanelLayout.createSequentialGroup()
                                .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtKMMR, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(idCliente10))
                                    .addComponent(check12))
                                .addGap(18, 18, 18)
                                .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(check13)
                                    .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtFolioPago, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(idCliente11)))))))
                .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rendimientoPanelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtOdometroR, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(idCliente4)
                                .addComponent(txtFolioOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(idCliente12))
                            .addComponent(check6)
                            .addComponent(check10)
                            .addComponent(check14)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rendimientoPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMonedaR, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idCliente8))))
                .addGap(18, 18, 18)
                .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtKMR, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(idCliente5)
                        .addComponent(idCliente9)
                        .addComponent(txtKMLR, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(check7)
                    .addComponent(check11))
                .addGap(18, 18, 18)
                .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addGroup(rendimientoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbf2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbf3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(idCliente14)
                        .addComponent(idCliente15)
                        .addComponent(cbf4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbf5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbf6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelMaquinaria.add(rendimientoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 85, 970, 560));

        infoPanel.setBackground(new java.awt.Color(239, 240, 240));

        txtBuscarMaquinaria.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        txtBuscarMaquinaria.setText("Buscar por serie, marca, modelo o placas...");
        txtBuscarMaquinaria.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarMaquinariaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarMaquinariaFocusLost(evt);
            }
        });
        txtBuscarMaquinaria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarMaquinariaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarMaquinariaKeyReleased(evt);
            }
        });

        tablaMaquinaria.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        tablaMaquinaria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Serie", "Marca", "Modelo", "Año", "Placas"
            }
        ));
        tablaMaquinaria.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaMaquinariaFocusLost(evt);
            }
        });
        tablaMaquinaria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMaquinariaMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tablaMaquinaria);

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/lupa.png"))); // NOI18N

        jButton7.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jButton7.setText("Agregar maquinaria");
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        btnModificar4.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        btnModificar4.setText("Modificar");
        btnModificar4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnModificar4.setEnabled(false);
        btnModificar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar4ActionPerformed(evt);
            }
        });

        btnEliminar4.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        btnEliminar4.setText("Eliminar");
        btnEliminar4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminar4.setEnabled(false);
        btnEliminar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscarMaquinaria, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar4)
                        .addGap(10, 10, 10)
                        .addComponent(btnEliminar4)))
                .addContainerGap())
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jLabel26))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscarMaquinaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnModificar4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelMaquinaria.add(infoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, -1));

        tablaObras7.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        tablaObras7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Serie", "Marca", "Modelo"
            }
        ));
        tablaObras7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaObras7FocusLost(evt);
            }
        });
        tablaObras7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaObras7MouseClicked(evt);
            }
        });
        jScrollPane36.setViewportView(tablaObras7);

        jButton30.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jButton30.setText("Asignar maquinaria");
        jButton30.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        txtBuscarObras4.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        txtBuscarObras4.setText("Buscar por serie, marca, modelo...");
        txtBuscarObras4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarObras4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarObras4FocusLost(evt);
            }
        });
        txtBuscarObras4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarObras4KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarObras4KeyReleased(evt);
            }
        });

        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/lupa.png"))); // NOI18N

        tablaObras8.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        tablaObras8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre empleado", "Fecha"
            }
        ));
        tablaObras8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaObras8FocusLost(evt);
            }
        });
        tablaObras8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaObras8MouseClicked(evt);
            }
        });
        jScrollPane37.setViewportView(tablaObras8);

        jLabel65.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel65.setText("Maquinaria");

        jLabel66.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel66.setText("Empleados asignados");

        javax.swing.GroupLayout asignarMaquinariaLayout = new javax.swing.GroupLayout(asignarMaquinaria);
        asignarMaquinaria.setLayout(asignarMaquinariaLayout);
        asignarMaquinariaLayout.setHorizontalGroup(
            asignarMaquinariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(asignarMaquinariaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(asignarMaquinariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(asignarMaquinariaLayout.createSequentialGroup()
                        .addComponent(jScrollPane36, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane37, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE))
                    .addGroup(asignarMaquinariaLayout.createSequentialGroup()
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarObras4, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton30)))
                .addContainerGap())
            .addGroup(asignarMaquinariaLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jLabel65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel66)
                .addGap(250, 250, 250))
        );
        asignarMaquinariaLayout.setVerticalGroup(
            asignarMaquinariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, asignarMaquinariaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(asignarMaquinariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(asignarMaquinariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscarObras4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel64))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(asignarMaquinariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel66, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel65))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(asignarMaquinariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane37, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                    .addComponent(jScrollPane36))
                .addContainerGap())
        );

        panelMaquinaria.add(asignarMaquinaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 970, 560));

        derPanel.add(panelMaquinaria, "card7");

        panelInventario.setBackground(new java.awt.Color(239, 240, 240));
        panelInventario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jTabbedPane3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane3StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 967, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Información", jPanel21);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 967, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Asignar productos a obra", jPanel7);

        panelInventario.add(jTabbedPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 24));

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/inventariob.png"))); // NOI18N

        jLabel56.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel56.setText("Inventario");

        fechaCotizacion1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        fechaCotizacion1.setText("Fecha:");

        txtCotizacion1.setEnabled(false);
        txtCotizacion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCotizacion1ActionPerformed(evt);
            }
        });

        datosPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de obra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 14))); // NOI18N

        tablaCC2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricula", "Nombre", "Cliente", "Encargado"
            }
        ));
        tablaCC2.setShowHorizontalLines(false);
        jScrollPane30.setViewportView(tablaCC2);

        jButton26.setText("...");
        jButton26.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout datosPanel1Layout = new javax.swing.GroupLayout(datosPanel1);
        datosPanel1.setLayout(datosPanel1Layout);
        datosPanel1Layout.setHorizontalGroup(
            datosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
        );
        datosPanel1Layout.setVerticalGroup(
            datosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        tablaCotizacionP1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Código", "Cantidad", "Descripción"
            }
        ));
        tablaCotizacionP1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tablaCotizacionP1FocusGained(evt);
            }
        });
        tablaCotizacionP1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCotizacionP1MouseClicked(evt);
            }
        });
        jScrollPane31.setViewportView(tablaCotizacionP1);

        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/plus.png"))); // NOI18N
        jButton27.setText("<html><center>Asignar productos<br>a obra</center> </html>");
        jButton27.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        btnEliminarP1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/trash (1).png"))); // NOI18N
        btnEliminarP1.setText("Eliminar producto");
        btnEliminarP1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminarP1.setEnabled(false);
        btnEliminarP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarP1ActionPerformed(evt);
            }
        });

        spCantidad1.setEnabled(false);
        spCantidad1.setValue(1
        );

        agregarP1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/plus.png"))); // NOI18N
        agregarP1.setText("<html> <center>Agregar<br>producto</center> </html>");
        agregarP1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        agregarP1.setEnabled(false);
        agregarP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarP1ActionPerformed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel60.setText("Datos de empleado:");

        tablaDescripcionP1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricula", "Nombre"
            }
        ));
        tablaDescripcionP1.setShowHorizontalLines(false);
        jScrollPane33.setViewportView(tablaDescripcionP1);

        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/search.png"))); // NOI18N
        jButton28.setText("<html> <center>Buscar<br>producto</center> </html>");
        jButton28.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel61.setText("Cantidad:");

        jButton29.setText("...");
        jButton29.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel62.setText("Producto:");

        tablaDescripcionP2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Existencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaDescripcionP2.setShowHorizontalLines(false);
        jScrollPane34.setViewportView(tablaDescripcionP2);

        jLabel63.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel63.setText("Lista de productos:");

        javax.swing.GroupLayout agregarPanel1Layout = new javax.swing.GroupLayout(agregarPanel1);
        agregarPanel1.setLayout(agregarPanel1Layout);
        agregarPanel1Layout.setHorizontalGroup(
            agregarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregarPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(agregarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, agregarPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(agregarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(agregarPanel1Layout.createSequentialGroup()
                                .addComponent(btnEliminarP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10))
                            .addGroup(agregarPanel1Layout.createSequentialGroup()
                                .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(agregarPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel63)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(agregarPanel1Layout.createSequentialGroup()
                        .addGroup(agregarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(agregarPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane33, javax.swing.GroupLayout.PREFERRED_SIZE, 823, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                            .addGroup(agregarPanel1Layout.createSequentialGroup()
                                .addGroup(agregarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel60)
                                    .addComponent(jLabel62))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, agregarPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane34, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(agregarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spCantidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel61))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(agregarP1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        agregarPanel1Layout.setVerticalGroup(
            agregarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, agregarPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(agregarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane33, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(agregarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(agregarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane34, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(agregarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(agregarP1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(agregarPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spCantidad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(agregarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(agregarPanel1Layout.createSequentialGroup()
                        .addComponent(btnEliminarP1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane31, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout nuevacotPanel1Layout = new javax.swing.GroupLayout(nuevacotPanel1);
        nuevacotPanel1.setLayout(nuevacotPanel1Layout);
        nuevacotPanel1Layout.setHorizontalGroup(
            nuevacotPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 965, Short.MAX_VALUE)
            .addGroup(nuevacotPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(datosPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(nuevacotPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(nuevacotPanel1Layout.createSequentialGroup()
                    .addComponent(agregarPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        nuevacotPanel1Layout.setVerticalGroup(
            nuevacotPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 542, Short.MAX_VALUE)
            .addGroup(nuevacotPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(nuevacotPanel1Layout.createSequentialGroup()
                    .addComponent(datosPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 472, Short.MAX_VALUE)))
            .addGroup(nuevacotPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nuevacotPanel1Layout.createSequentialGroup()
                    .addGap(0, 74, Short.MAX_VALUE)
                    .addComponent(agregarPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout panelInveObraLayout = new javax.swing.GroupLayout(panelInveObra);
        panelInveObra.setLayout(panelInveObraLayout);
        panelInveObraLayout.setHorizontalGroup(
            panelInveObraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInveObraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInveObraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInveObraLayout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fechaCotizacion1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCotizacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInveObraLayout.createSequentialGroup()
                        .addComponent(nuevacotPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        panelInveObraLayout.setVerticalGroup(
            panelInveObraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInveObraLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panelInveObraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInveObraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel56)
                        .addComponent(fechaCotizacion1)
                        .addComponent(txtCotizacion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel54))
                .addGap(18, 18, 18)
                .addComponent(nuevacotPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelInventario.add(panelInveObra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, -1));

        txtBuscarProducto.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        txtBuscarProducto.setText("Buscar por descripción o código de producto...");
        txtBuscarProducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarProductoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarProductoFocusLost(evt);
            }
        });
        txtBuscarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarProductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProductoKeyReleased(evt);
            }
        });

        tablaInventario.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Descripción", "Existencia", "Precio"
            }
        ));
        tablaInventario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaInventarioFocusLost(evt);
            }
        });
        tablaInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaInventarioMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tablaInventario);

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Miscelaneo/lupa.png"))); // NOI18N

        jButton13.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jButton13.setText("Agregar producto");
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        btnModificar5.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        btnModificar5.setText("Modificar");
        btnModificar5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnModificar5.setEnabled(false);
        btnModificar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar5ActionPerformed(evt);
            }
        });

        btnEliminar5.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        btnEliminar5.setText("Eliminar");
        btnEliminar5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminar5.setEnabled(false);
        btnEliminar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout infoInventarioLayout = new javax.swing.GroupLayout(infoInventario);
        infoInventario.setLayout(infoInventarioLayout);
        infoInventarioLayout.setHorizontalGroup(
            infoInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoInventarioLayout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addGap(13, 13, 13)
                        .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155)
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar5)
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addComponent(jScrollPane11))
                .addContainerGap())
        );
        infoInventarioLayout.setVerticalGroup(
            infoInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnModificar5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminar5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel39))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelInventario.add(infoInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, 86, 980, 560));

        jLabel38.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel38.setText("Inventario");
        panelInventario.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 30, -1, -1));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/inventariob.png"))); // NOI18N
        panelInventario.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        derPanel.add(panelInventario, "card4");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/reporteb.png"))); // NOI18N

        jLabel21.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel21.setText("Contaduría");

        jTabbedPane8.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 978, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane8.addTab("Balance general", jPanel14);

        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3formMouseClicked(evt);
            }
        });

        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Circulante", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla1.setSelectionBackground(new java.awt.Color(255, 255, 255));
        Tabla1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Tabla1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Tabla1FocusLost(evt);
            }
        });
        Tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla1MouseClicked(evt);
            }
        });
        Tabla1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tabla1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Tabla1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Tabla1KeyTyped(evt);
            }
        });
        jScrollPane20.setViewportView(Tabla1);

        Tabla6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Capital", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla6.setSelectionBackground(new java.awt.Color(255, 255, 255));
        Tabla6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Tabla6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Tabla6FocusLost(evt);
            }
        });
        Tabla6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla6MouseClicked(evt);
            }
        });
        Tabla6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Tabla6KeyTyped(evt);
            }
        });
        jScrollPane21.setViewportView(Tabla6);

        Tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Fijo", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla2.setSelectionBackground(new java.awt.Color(255, 255, 255));
        Tabla2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Tabla2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Tabla2FocusLost(evt);
            }
        });
        Tabla2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla2MouseClicked(evt);
            }
        });
        Tabla2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tabla2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Tabla2KeyTyped(evt);
            }
        });
        jScrollPane22.setViewportView(Tabla2);

        Tabla3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Diferido", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla3.setSelectionBackground(new java.awt.Color(255, 255, 255));
        Tabla3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Tabla3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Tabla3FocusLost(evt);
            }
        });
        Tabla3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla3MouseClicked(evt);
            }
        });
        Tabla3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tabla3KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Tabla3KeyTyped(evt);
            }
        });
        jScrollPane23.setViewportView(Tabla3);

        Tabla4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Corto Plazo", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla4.setSelectionBackground(new java.awt.Color(255, 255, 255));
        Tabla4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Tabla4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Tabla4FocusLost(evt);
            }
        });
        Tabla4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla4MouseClicked(evt);
            }
        });
        Tabla4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tabla4KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Tabla4KeyTyped(evt);
            }
        });
        jScrollPane24.setViewportView(Tabla4);

        Tabla5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Largo Plazo", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla5.setSelectionBackground(new java.awt.Color(255, 255, 255));
        Tabla5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Tabla5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Tabla5FocusLost(evt);
            }
        });
        Tabla5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla5MouseClicked(evt);
            }
        });
        Tabla5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tabla5KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Tabla5KeyTyped(evt);
            }
        });
        jScrollPane25.setViewportView(Tabla5);

        jTable8.setForeground(new java.awt.Color(255, 51, 153));
        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Activo", "", ""
            }
        ));
        jScrollPane26.setViewportView(jTable8);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pasivo", "", ""
            }
        ));
        jScrollPane27.setViewportView(jTable5);

        TotalActivo.setEditable(false);

        jTextField2.setEditable(false);
        jTextField2.setText("Total Activo");

        jTextField3.setEditable(false);

        jTextField5.setEditable(false);

        TotalCapital.setEditable(false);

        jTextField7.setEditable(false);
        jTextField7.setText("Total Capital");
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jTextField8.setEditable(false);
        jTextField8.setText("Total Pasivo");
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jTextField9.setEditable(false);

        TotalPasivo.setEditable(false);

        TotalPC.setEditable(false);

        jTextField6.setEditable(false);

        jTextField10.setEditable(false);
        jTextField10.setText("Total P + C");
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(TotalPasivo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(TotalCapital, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(TotalActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(TotalPC, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TotalPasivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TotalCapital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TotalActivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TotalPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelReporteLayout = new javax.swing.GroupLayout(panelReporte);
        panelReporte.setLayout(panelReporteLayout);
        panelReporteLayout.setHorizontalGroup(
            panelReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(panelReporteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelReporteLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelReporteLayout.setVerticalGroup(
            panelReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReporteLayout.createSequentialGroup()
                .addComponent(jTabbedPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(panelReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        derPanel.add(panelReporte, "card7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(izqPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(derPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(derPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(izqPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Clientes.AgregarC nv = new Clientes.AgregarC(this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Clientes.ModificarC nv = new Clientes.ModificarC(datosCliente,this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Clientes.EliminarC nv = new Clientes.EliminarC(datosCliente,this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBuscarClientesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarClientesFocusGained
        if (txtBuscarClientes.getText().equals("Buscar por nombre o matricula de cliente...")) {
            txtBuscarClientes.setText("");
        }
    }//GEN-LAST:event_txtBuscarClientesFocusGained

    private void txtBuscarClientesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarClientesFocusLost
        if (txtBuscarClientes.getText().isEmpty()) {
            txtBuscarClientes.setText("Buscar por nombre o matricula de cliente...");
        }
    }//GEN-LAST:event_txtBuscarClientesFocusLost

    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseClicked
        //tabla clientes evento
        int row = tablaClientes.getSelectedRow();
        for (int i = 0; i < 7; i++) {
            datosCliente[i] = String.valueOf(tablaClientes.getValueAt(row, i));
        }
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }//GEN-LAST:event_tablaClientesMouseClicked

    private void tablaClientesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaClientesFocusLost
        tablaClientes.getSelectionModel().clearSelection();
//        btnModificar.setEnabled(false);
//        btnEliminar.setEnabled(false);
    }//GEN-LAST:event_tablaClientesFocusLost

    private void txtBuscarObrasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarObrasFocusGained
        if (txtBuscarObras.getText().equals("Buscar por cliente, nombre, estatus o matricula de obra...")) {
            txtBuscarObras.setText("");
        }
    }//GEN-LAST:event_txtBuscarObrasFocusGained

    private void txtBuscarObrasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarObrasFocusLost
        if (txtBuscarObras.getText().isEmpty()) {
            txtBuscarObras.setText("Buscar por cliente, nombre, estatus o matricula de obra...");
        }
    }//GEN-LAST:event_txtBuscarObrasFocusLost

    private void tablaObrasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaObrasFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaObrasFocusLost

    private void tablaObrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaObrasMouseClicked
        //evento tabla Obras
        int row = tablaObras.getSelectedRow();
        for (int i = 0; i < 11; i++) {
            datosObra[i] = String.valueOf(tablaObras.getValueAt(row, i));
        }
        btnModificar1.setEnabled(true);
    }//GEN-LAST:event_tablaObrasMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Obras.AgregarO nv = new Obras.AgregarO(this, true);
        nv.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnModificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar1ActionPerformed
        Obras.ModificarO nv = new Obras.ModificarO(datosObra,this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_btnModificar1ActionPerformed

    private void txtBuscarEmpleadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarEmpleadoFocusGained
        if (txtBuscarEmpleado.getText().equals("Buscar por nombre o matricula de empleado...")) {
            txtBuscarEmpleado.setText("");
        }
    }//GEN-LAST:event_txtBuscarEmpleadoFocusGained

    private void txtBuscarEmpleadoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarEmpleadoFocusLost
        if (txtBuscarEmpleado.getText().isEmpty()) {
            txtBuscarEmpleado.setText("Buscar por nombre o matricula de empleado...");
        }
    }//GEN-LAST:event_txtBuscarEmpleadoFocusLost

    private void tablaEmpleadosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaEmpleadosFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaEmpleadosFocusLost

    private void tablaEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEmpleadosMouseClicked
        int row = tablaEmpleados.getSelectedRow();
        for (int i = 0; i < 6; i++) {
            datosEmpleado[i] = String.valueOf(tablaEmpleados.getValueAt(row, i));
        }
        btnModificar2.setEnabled(true);
        btnEliminar2.setEnabled(true);
    }//GEN-LAST:event_tablaEmpleadosMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Empleados.AgregarE nv = new Empleados.AgregarE(this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnModificar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar2ActionPerformed
//modificar Empleados
        Empleados.ModificarE nv = new Empleados.ModificarE(datosEmpleado,this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_btnModificar2ActionPerformed

    private void btnEliminar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar2ActionPerformed
        //Eliminar empleados
        Empleados.EliminarE nv = new Empleados.EliminarE(datosEmpleado,this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_btnEliminar2ActionPerformed

    private void btnEliminarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPActionPerformed
        if (tablaCotizacionP.getRowCount() != 0) {
            int row = tablaCotizacionP.getSelectedRow();
            subtotal = Double.valueOf(lblSubtotal.getText().substring(1)) - Double.parseDouble(String.valueOf(tablaCotizacionP.getValueAt(row, 5)));
            iva = Double.valueOf(lblIVA.getText().substring(1)) - (Double.parseDouble(String.valueOf(tablaCotizacionP.getValueAt(row, 5))) * 0.16);
            total = subtotal + iva;
            lblSubtotal.setText("$" + String.valueOf(subtotal));
            lblIVA.setText("$" + String.valueOf(iva));
            lblTotal.setText("$" + String.valueOf(total));
            btnEliminarP.setEnabled(false);
            ((DefaultTableModel) tablaCotizacionP.getModel()).removeRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No hay productos en la tabla", "Aviso", JOptionPane.ERROR);
        }
    }//GEN-LAST:event_btnEliminarPActionPerformed

    private void txtBuscarProductoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarProductoFocusGained
        if (txtBuscarProducto.getText().equals("Buscar por descripción o código de producto...")) {
            txtBuscarProducto.setText("");
        }
    }//GEN-LAST:event_txtBuscarProductoFocusGained

    private void txtBuscarProductoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarProductoFocusLost
        if (txtBuscarProducto.getText().isEmpty()) {
            txtBuscarProducto.setText("Buscar por descripción o código de producto...");
        }
    }//GEN-LAST:event_txtBuscarProductoFocusLost

    private void tablaInventarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaInventarioFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaInventarioFocusLost

    private void tablaInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaInventarioMouseClicked
        //tabla Inventario
        int row = tablaInventario.getSelectedRow();
        for (int i = 0; i < 4; i++) {
            datosProducto[i] = String.valueOf(tablaInventario.getValueAt(row, i));
        }
        btnModificar5.setEnabled(true);
        btnEliminar5.setEnabled(true);
    }//GEN-LAST:event_tablaInventarioMouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        Inventario.AgregarP nv = new Inventario.AgregarP(this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void btnModificar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar5ActionPerformed
        Inventario.ModificarP nv = new Inventario.ModificarP(datosProducto,this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_btnModificar5ActionPerformed

    private void btnEliminar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar5ActionPerformed
        Inventario.EliminarP nv = new Inventario.EliminarP(datosProducto,this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_btnEliminar5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Obras.SeleccionarCliente nv = new Obras.SeleccionarCliente(2,null,true);
        nv.setVisible(true);
        nv.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if (!(jTextField1.getText().isEmpty() || tablaCC.getRowCount() == 0 || tablaDescripcionP.getRowCount() == 0 || tablaCotizacionP.getRowCount() == 0)) {
            try {
                String choosertitle = "";
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int option = chooser.showSaveDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(null, "Se generó correctamente el PDF", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    File file = chooser.getSelectedFile();
                    choosertitle = file.getAbsolutePath();
                    System.out.println("Ruta:" + choosertitle + ".pdf");
                    choosertitle = choosertitle + ".pdf";
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(choosertitle));
                    document.open();
                    //addMetaData(document);
                    document.addTitle("Cotizacion SOCET");
                    document.addSubject("Digitalizada");
                    document.addKeywords("Cotizacion,SOCET");
                    document.addAuthor("Fabian Gaxiola");
                    document.addCreator("Fabian Gaxiola");
                    //addTitlePage(document);
                    Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
                    Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
                    Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
                    Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
                    Font titleArial = new Font(Font.FontFamily.HELVETICA, 13);
                    Font bodyArial = new Font(Font.FontFamily.HELVETICA, 11);
                    Image imagen = Image.getInstance("src/Miscelaneo/1.png");
                    //imagen.setAlignment(Element.ALIGN_CENTER);
                    imagen.setAbsolutePosition(60f, 715f);
                    imagen.scaleAbsolute(90, 90);
                    document.add(imagen);
                    Paragraph par = new Paragraph();
                    //document.add(Chunk.NEWLINE);
                    par.setIndentationLeft(240);
                    par.add(new Paragraph("SOCET S.A. de C.V.", titleArial));
                    document.add(par);
                    //document.add(Chunk.NEWLINE);
                    Paragraph par1 = new Paragraph();
                    par1.setIndentationLeft(120);
                    par1.add(new Paragraph("San José Obrero #2325 Ote. Col. Hacienda San José Cd. Obregón, Sonora.", bodyArial));
                    document.add(par1);
                    Paragraph par2 = new Paragraph();
                    par2.setIndentationLeft(250);
                    par2.add(new Paragraph("Tel: (644)1.69.76.86", bodyArial));
                    document.add(par2);
                    Paragraph par3 = new Paragraph();
                    par3.setIndentationLeft(210);
                    par3.add(new Paragraph("e-Mail: socetsadecv@yahoo.com.mx", bodyArial));
                    document.add(par3);
                    document.add(Chunk.NEWLINE);
                    Paragraph par4 = new Paragraph();
                    par4.setIndentationLeft(230);
                    par4.add(new Paragraph("Cotizacion.", titleArial));
                    document.add(par4);
                    document.add(Chunk.NEWLINE);
                    PdfPTable tableemp = new PdfPTable(2);
                    tableemp.setWidthPercentage(50);
                    tableemp.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    PdfPCell id = new PdfPCell(new Phrase("No. Cotizacion"));
                    id.setColspan(1);
                    id.setRowspan(1);
                    id.setBackgroundColor(new BaseColor(250, 191, 0));
                    tableemp.addCell(id);
                    PdfPCell fecha = new PdfPCell(new Phrase("Fecha"));
                    fecha.setColspan(1);
                    fecha.setRowspan(1);
                    fecha.setBackgroundColor(new BaseColor(250, 191, 0));
                    tableemp.addCell(fecha);
                    PdfPCell a7 = new PdfPCell(new Phrase(jTextField1.getText()));
                    a7.setBackgroundColor(BaseColor.WHITE);
                    a7.setColspan(1);
                    tableemp.addCell(a7);
                    PdfPCell a8 = new PdfPCell(new Phrase(txtCotizacion.getText()));
                    a8.setBackgroundColor(BaseColor.WHITE);
                    a8.setColspan(1);
                    tableemp.addCell(a8);
                    document.add(tableemp);
                    document.add(Chunk.NEWLINE);
                    PdfPTable table = new PdfPTable(12);
                    PdfPCell idcli = new PdfPCell(new Phrase("Mat. Cliente"));
                    idcli.setColspan(4);
                    idcli.setRowspan(1);
                    idcli.setBackgroundColor(new BaseColor(250, 191, 0));
                    table.addCell(idcli);
                    PdfPCell nombre = new PdfPCell(new Phrase("Nombre"));
                    nombre.setColspan(4);
                    nombre.setBackgroundColor(new BaseColor(250, 191, 0));
                    table.addCell(nombre);
                    PdfPCell empresa = new PdfPCell(new Phrase("Empresa"));
                    empresa.setColspan(4);
                    empresa.setRowspan(1);
                    empresa.setBackgroundColor(new BaseColor(250, 191, 0));
                    table.addCell(empresa);
                    PdfPCell a1 = new PdfPCell(new Phrase(tablaCC.getModel().getValueAt(0, 0).toString()));
                    a1.setColspan(4);
                    a1.setBackgroundColor(BaseColor.WHITE);
                    table.addCell(a1);
                    PdfPCell a2 = new PdfPCell(new Phrase(tablaCC.getModel().getValueAt(0, 1).toString() + " " + tablaCC.getModel().getValueAt(0, 2).toString()));
                    a2.setBackgroundColor(BaseColor.WHITE);
                    a2.setColspan(4);
                    table.addCell(a2);
                    PdfPCell a3 = new PdfPCell(new Phrase(tablaCC.getModel().getValueAt(0, 5).toString()));
                    a3.setBackgroundColor(BaseColor.WHITE);
                    a3.setColspan(4);
                    table.addCell(a3);
                    PdfPCell ciudad = new PdfPCell(new Phrase("Telefono"));
                    ciudad.setBackgroundColor(new BaseColor(250, 191, 0));
                    ciudad.setColspan(4);
                    table.addCell(ciudad);
                    PdfPCell dir = new PdfPCell(new Phrase("Direccion"));
                    dir.setColspan(4);
                    dir.setBackgroundColor(new BaseColor(250, 191, 0));
                    table.addCell(dir);
                    PdfPCell email = new PdfPCell(new Phrase("e-mail"));
                    email.setColspan(4);
                    email.setBackgroundColor(new BaseColor(250, 191, 0));
                    table.addCell(email);
                    PdfPCell a4 = new PdfPCell(new Phrase(tablaCC.getModel().getValueAt(0, 3).toString()));
                    a4.setBackgroundColor(BaseColor.WHITE);
                    a4.setColspan(4);
                    table.addCell(a4);
                    PdfPCell a5 = new PdfPCell(new Phrase(tablaCC.getModel().getValueAt(0, 4).toString()));
                    a5.setBackgroundColor(BaseColor.WHITE);
                    a5.setColspan(4);
                    table.addCell(a5);
                    PdfPCell a6 = new PdfPCell(new Phrase(tablaCC.getModel().getValueAt(0, 6).toString()));
                    a6.setBackgroundColor(BaseColor.WHITE);
                    a6.setColspan(4);
                    table.addCell(a6);
                    document.add(table);
                    document.add(Chunk.NEWLINE);
                    PdfPTable table2 = new PdfPTable(12);
                    PdfPCell gato = new PdfPCell(new Phrase("#"));
                    gato.setBackgroundColor(new BaseColor(250, 191, 0));
                    gato.setColspan(1);
                    table2.addCell(gato);
                    PdfPCell idpro = new PdfPCell(new Phrase("ID Producto"));
                    idpro.setColspan(2);
                    idpro.setBackgroundColor(new BaseColor(250, 191, 0));
                    table2.addCell(idpro);
                    PdfPCell cant = new PdfPCell(new Phrase("Cant"));
                    cant.setBackgroundColor(new BaseColor(250, 191, 0));
                    cant.setColspan(1);
                    table2.addCell(cant);
                    PdfPCell desc = new PdfPCell(new Phrase("Descripcion"));
                    desc.setBackgroundColor(new BaseColor(250, 191, 0));
                    desc.setColspan(4);
                    table2.addCell(desc);
                    PdfPCell valoru = new PdfPCell(new Phrase("Valor Unitario"));
                    valoru.setBackgroundColor(new BaseColor(250, 191, 0));
                    valoru.setColspan(2);
                    table2.addCell(valoru);
                    PdfPCell valort = new PdfPCell(new Phrase("Valor Total"));
                    valort.setBackgroundColor(new BaseColor(250, 191, 0));
                    valort.setColspan(2);
                    table2.addCell(valort);
                    for (int i = 0; i < tablaCotizacionP.getRowCount(); i++) {
                        if (i % 2 == 0) {
                            PdfPCell v1 = new PdfPCell(new Phrase(tablaCotizacionP.getModel().getValueAt(i, 0).toString()));
                            v1.setBackgroundColor(BaseColor.WHITE);
                            v1.setColspan(1);
                            table2.addCell(v1);
                            PdfPCell v2 = new PdfPCell(new Phrase(tablaCotizacionP.getModel().getValueAt(i, 1).toString()));
                            v2.setBackgroundColor(BaseColor.WHITE);
                            v2.setColspan(2);
                            table2.addCell(v2);
                            PdfPCell v3 = new PdfPCell(new Phrase(tablaCotizacionP.getModel().getValueAt(i, 2).toString()));
                            v3.setBackgroundColor(BaseColor.WHITE);
                            v3.setColspan(1);
                            table2.addCell(v3);
                            PdfPCell v4 = new PdfPCell(new Phrase(tablaCotizacionP.getModel().getValueAt(i, 3).toString()));
                            v4.setBackgroundColor(BaseColor.WHITE);
                            v4.setColspan(4);
                            table2.addCell(v4);
                            PdfPCell v5 = new PdfPCell(new Phrase(tablaCotizacionP.getModel().getValueAt(i, 4).toString()));
                            v5.setBackgroundColor(BaseColor.WHITE);
                            v5.setColspan(2);
                            table2.addCell(v5);
                            PdfPCell v6 = new PdfPCell(new Phrase(tablaCotizacionP.getModel().getValueAt(i, 5).toString()));
                            v6.setBackgroundColor(BaseColor.WHITE);
                            v6.setColspan(2);
                            table2.addCell(v6);
                        } else {
                            PdfPCell v1 = new PdfPCell(new Phrase(tablaCotizacionP.getModel().getValueAt(i, 0).toString()));
                            v1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            v1.setColspan(1);
                            table2.addCell(v1);
                            PdfPCell v2 = new PdfPCell(new Phrase(tablaCotizacionP.getModel().getValueAt(i, 1).toString()));
                            v2.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            v2.setColspan(2);
                            table2.addCell(v2);
                            PdfPCell v3 = new PdfPCell(new Phrase(tablaCotizacionP.getModel().getValueAt(i, 2).toString()));
                            v3.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            v3.setColspan(1);
                            table2.addCell(v3);
                            PdfPCell v4 = new PdfPCell(new Phrase(tablaCotizacionP.getModel().getValueAt(i, 3).toString()));
                            v4.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            v4.setColspan(4);
                            table2.addCell(v4);
                            PdfPCell v5 = new PdfPCell(new Phrase(tablaCotizacionP.getModel().getValueAt(i, 4).toString()));
                            v5.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            v5.setColspan(2);
                            table2.addCell(v5);
                            PdfPCell v6 = new PdfPCell(new Phrase(tablaCotizacionP.getModel().getValueAt(i, 5).toString()));
                            v6.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            v6.setColspan(2);
                            table2.addCell(v6);
                        }
                    }
                    PdfPCell c1 = new PdfPCell(new Phrase("Observaciones: " + jTextArea1.getText()));
                    c1.setBackgroundColor(BaseColor.WHITE);
                    c1.setColspan(8);
                    c1.setRowspan(4);
                    table2.addCell(c1);
                    PdfPCell c2 = new PdfPCell(new Phrase("Subtotal:"));
                    c2.setBackgroundColor(BaseColor.WHITE);
                    c2.setColspan(2);
                    table2.addCell(c2);
                    PdfPCell c3 = new PdfPCell(new Phrase(lblSubtotal.getText()));
                    c3.setBackgroundColor(BaseColor.WHITE);
                    c3.setColspan(2);
                    table2.addCell(c3);
                    PdfPCell c6 = new PdfPCell(new Phrase("I.V.A:"));
                    c6.setBackgroundColor(BaseColor.WHITE);
                    c6.setColspan(2);
                    table2.addCell(c6);
                    PdfPCell c7 = new PdfPCell(new Phrase(lblIVA.getText()));
                    c7.setBackgroundColor(BaseColor.WHITE);
                    c7.setColspan(2);
                    table2.addCell(c7);
                    PdfPCell c8 = new PdfPCell(new Phrase("Total:"));
                    c8.setBackgroundColor(BaseColor.WHITE);
                    c8.setColspan(2);
                    table2.addCell(c8);
                    PdfPCell c9 = new PdfPCell(new Phrase(lblTotal.getText()));
                    c9.setBackgroundColor(BaseColor.WHITE);
                    c9.setColspan(2);
                    table2.addCell(c9);
                    document.add(table2);
                    document.add(Chunk.NEWLINE);
                    document.add(Chunk.NEWLINE);
                    Paragraph line = new Paragraph();
                    line.setIndentationLeft(160);
                    line.add(new Paragraph("_____________________________", titleArial));
                    document.add(line);
                    Paragraph firma = new Paragraph();
                    firma.setIndentationLeft(250);
                    firma.add(new Paragraph("Firma", titleArial));
                    document.add(firma);
                    document.add(Chunk.NEWLINE);
                    Paragraph nota = new Paragraph();
                    nota.setIndentationLeft(300);
                    nota.add(new Paragraph("NOTA: Cotización válida por 60 días.", bodyArial));
                    document.add(nota);
                    document.close();
                }
            } catch (DocumentException | IOException e) {
                System.out.println(e);
            }
            BufferedWriter writer1 = null;
            try {
                //create a temporary file
                File logFile1 = new File("config1.txt");

                // This will output the full path where the file will be written to...
                //System.out.println(logFile.getCanonicalPath());

                writer1 = new BufferedWriter(new FileWriter(logFile1));
                String folioxd = jTextField1.getText();
                int nfolio=Integer.parseInt(folioxd);
                nfolio++;
                String foliofin=Integer.toString(nfolio);
                writer1.write(foliofin);
                jTextField1.setText(foliofin);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    // Close the writer regardless of what happens...
                    writer1.close();
                } catch (Exception e) {
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hay campos vacíos. Favor de llenar los campos faltantes y vuelva a intentarlo.", "Campos vacíos", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButton8ActionPerformed
    public void insertarControl(String fecha, String FolioOrden, String vehiculo, String odometro, String KMR, String combustible, String litros, String moneda, String KMLR, String KMMR, String FolioPago) {
        try {
            Statement ST = c.createStatement();
            String sql = "Insert into CONTROL_DE_COMBUSTIBLE values('" + fecha
                    + "','" + FolioOrden + "','" + vehiculo
                    + "','" + odometro + "','" + KMR
                    + "','" + combustible + "','" + litros
                    + "','" + moneda + "','" + KMLR
                    + "','" + KMMR + "','" + FolioPago + "')";
            ST.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ResultSet buscarControl(int FolioOrden) {
        try {
            Statement ST = c.createStatement();
            String sql = "Select * from CONTROL_DE_COMBUSTIBLE where FolioOrden='" + FolioOrden + "'";
            return ST.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        Cotizacion.SeleccionarProducto nv = new Cotizacion.SeleccionarProducto(1,this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void tablaCotizacionPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCotizacionPMouseClicked
        eliminarP = tablaCotizacionP.getSelectedRow();
    }//GEN-LAST:event_tablaCotizacionPMouseClicked

    private void tablaCotizacionPFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaCotizacionPFocusGained
        btnEliminarP.setEnabled(true);
    }//GEN-LAST:event_tablaCotizacionPFocusGained

    private void agregarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPActionPerformed

        iva = 0;
        total = 0;
        subtotal = 0;
        DefaultTableModel model = (DefaultTableModel) tablaCotizacionP.getModel();
        tablaCotizacionP.setModel(model);
        System.out.println(Double.parseDouble(String.valueOf(tablaDescripcionP.getValueAt(0, 3))));
        Object fila[] = {cont, Integer.valueOf(String.valueOf(tablaDescripcionP.getValueAt(0, 0))), Integer.valueOf(String.valueOf(spCantidad.getValue())), String.valueOf(tablaDescripcionP.getValueAt(0, 1)), Double.parseDouble(String.valueOf(tablaDescripcionP.getValueAt(0, 3))), ((Double.parseDouble(String.valueOf(tablaDescripcionP.getValueAt(0, 3)))) * (Integer.parseInt(String.valueOf(spCantidad.getValue()))))};
        model.addRow(fila);
        cont++;
        for (int i = 0; i < tablaCotizacionP.getRowCount(); i++) {
            subtotal += Double.parseDouble(String.valueOf(tablaCotizacionP.getValueAt(i, 5)));
        }
        iva = subtotal * 0.16;
        total = subtotal + iva;
        lblSubtotal.setText("$" + String.valueOf(subtotal));
        lblIVA.setText("$" + String.valueOf(iva));
        lblTotal.setText("$" + String.valueOf(total));
        spCantidad.setValue(1);
    }//GEN-LAST:event_agregarPActionPerformed

    private void tablaProveedoresFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaProveedoresFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaProveedoresFocusLost

    private void tablaProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProveedoresMouseClicked
        int row = tablaProveedores.getSelectedRow();
        for (int i = 0; i < 5; i++) {
            datosProveedor[i] = String.valueOf(tablaProveedores.getValueAt(row, i));
        }
        btnModificarP.setEnabled(true);
        btnEliminarPro.setEnabled(true);
    }//GEN-LAST:event_tablaProveedoresMouseClicked

    private void txtBuscarProveedorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarProveedorFocusGained
        if (txtBuscarProveedor.getText().equals("Buscar por nombre o matricula de proveedor...")) {
            txtBuscarProveedor.setText("");
        }
    }//GEN-LAST:event_txtBuscarProveedorFocusGained

    private void txtBuscarProveedorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarProveedorFocusLost
        if (txtBuscarProveedor.getText().isEmpty()) {
            txtBuscarProveedor.setText("Buscar por nombre o matricula de proveedor...");
        }
    }//GEN-LAST:event_txtBuscarProveedorFocusLost

    private void btnEliminarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProActionPerformed
        Proveedores.EliminarPVD nv = new Proveedores.EliminarPVD(datosProveedor,this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_btnEliminarProActionPerformed

    private void btnModificarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarPActionPerformed
        Proveedores.ModificarPVD nv = new Proveedores.ModificarPVD(datosProveedor,this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_btnModificarPActionPerformed

    private void btnAgregarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPActionPerformed
        Proveedores.AgregarPVD nv = new Proveedores.AgregarPVD(this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_btnAgregarPActionPerformed

    private void txtBuscarClientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClientesKeyPressed
        DTMC = new DefaultTableModel(titulosClientes, 0);
        tablaClientes.setModel(DTMC);
        ResultSet res = mostrar(txtBuscarClientes.getText(), 2);
        try {
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("IDCliente"), res.getString("Nombre"), res.getString("Apellido"), res.getString("Telefono"), res.getString("Direccion"), res.getString("Empresa"), res.getString("E_Mail")};
                    DTMC.addRow(fila);
                } while (res.next());
                tablaObras.setModel(DTMC);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscarClientesKeyPressed

    private void txtBuscarClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClientesKeyReleased
        DTMC = new DefaultTableModel(titulosClientes, 0);
        tablaClientes.setModel(DTMC);
        ResultSet res = mostrar(txtBuscarClientes.getText(), 2);
        try {
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("IDCliente"), res.getString("Nombre"), res.getString("Apellido"), res.getString("Telefono"), res.getString("Direccion"), res.getString("Empresa"), res.getString("E_Mail")};
                    DTMC.addRow(fila);
                } while (res.next());
                tablaObras.setModel(DTMC);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscarClientesKeyReleased

    private void txtBuscarEmpleadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEmpleadoKeyPressed
        DTME = new DefaultTableModel(titulosEmpleados, 0);
        tablaEmpleados.setModel(DTME);
        ResultSet res = mostrar(txtBuscarEmpleado.getText(), 3);
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
    }//GEN-LAST:event_txtBuscarEmpleadoKeyPressed

    private void txtBuscarEmpleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEmpleadoKeyReleased
        DTME = new DefaultTableModel(titulosEmpleados, 0);
        tablaEmpleados.setModel(DTME);
        ResultSet res = mostrar(txtBuscarEmpleado.getText(), 3);
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
    }//GEN-LAST:event_txtBuscarEmpleadoKeyReleased

    private void txtBuscarObrasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarObrasKeyPressed
        DTMO = new DefaultTableModel(titulosObras, 0);
        tablaObras.setModel(DTMO);
        ResultSet res = mostrar(txtBuscarObras.getText(), 1);
        try {
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("IDObra"), res.getString("NombreObra"), res.getString("Cliente"), res.getString("FechaIni"), res.getString("FechaFin"), res.getString("Presupuesto"), res.getString("Ubicacion"), res.getString("TipoDeObra"), res.getString("Encargado"), res.getString("Estatus"), res.getString("Descripcion")};
                    DTMO.addRow(fila);
                } while (res.next());
                tablaObras.setModel(DTMO);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscarObrasKeyPressed

    private void txtBuscarObrasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarObrasKeyReleased
        DTMO = new DefaultTableModel(titulosObras, 0);
        tablaObras.setModel(DTMO);
        ResultSet res = mostrar(txtBuscarObras.getText(), 1);
        try {
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("IDObra"), res.getString("NombreObra"), res.getString("Cliente"), res.getString("FechaIni"), res.getString("FechaFin"), res.getString("Presupuesto"), res.getString("Ubicacion"), res.getString("TipoDeObra"), res.getString("Encargado"), res.getString("Estatus"), res.getString("Descripcion")};
                    DTMO.addRow(fila);
                } while (res.next());
                tablaObras.setModel(DTMO);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscarObrasKeyReleased

    private void txtBuscarProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoKeyPressed
        DTMP = new DefaultTableModel(titulosInventario, 0);
        tablaInventario.setModel(DTMP);
        ResultSet res = mostrar(txtBuscarProducto.getText(), 5);
        try {
            if (res.next()) {
                do {
                    Object fila[] = {String.valueOf(Integer.parseInt(res.getString("IDProducto"))), res.getString("Descripcion"), res.getString("Existencia"), res.getString("Precio")};
                    DTMP.addRow(fila);
                } while (res.next());
                tablaClientes.setModel(DTMP);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscarProductoKeyPressed

    private void txtBuscarProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoKeyReleased
        DTMP = new DefaultTableModel(titulosInventario, 0);
        tablaInventario.setModel(DTMP);
        ResultSet res = mostrar(txtBuscarProducto.getText(), 5);
        try {
            if (res.next()) {
                do {
                    Object fila[] = {String.valueOf(Integer.parseInt(res.getString("IDProducto"))), res.getString("Descripcion"), res.getString("Existencia"), res.getString("Precio")};
                    DTMP.addRow(fila);
                } while (res.next());
                tablaClientes.setModel(DTMP);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscarProductoKeyReleased

    private void txtBuscarProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProveedorKeyPressed
        DTMPRO2 = new DefaultTableModel(titulosProveedor, 0);
        tablaClientes.setModel(DTMPRO2);
        ResultSet res = mostrar(txtBuscarProveedor.getText(), 7);
        try {
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("IDProveedor"), res.getString("Nombre"), res.getString("Telefono"), res.getString("Direccion"), res.getString("E_Mail")};
                    DTMPRO2.addRow(fila);
                } while (res.next());
                tablaProveedores.setModel(DTMPRO2);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscarProveedorKeyPressed

    private void txtBuscarProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProveedorKeyReleased
        DTMPRO2 = new DefaultTableModel(titulosProveedor, 0);
        tablaClientes.setModel(DTMPRO2);
        ResultSet res = mostrar(txtBuscarProveedor.getText(), 7);
        try {
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("IDProveedor"), res.getString("Nombre"), res.getString("Telefono"), res.getString("Direccion"), res.getString("E_Mail")};
                    DTMPRO2.addRow(fila);
                } while (res.next());
                tablaProveedores.setModel(DTMPRO2);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscarProveedorKeyReleased

    private void tabbedEmpleadosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedEmpleadosStateChanged
        switch (tabbedEmpleados.getSelectedIndex()) {
            case 0:
                infoPanelEmp.setVisible(true);
                nominaPanel.setVisible(false);
                listaNomina.setVisible(false);
                break;
            case 1:
                nominaPanel.setVisible(true);
                infoPanelEmp.setVisible(false);
                listaNomina.setVisible(false);

                break;
            case 2:
                listaNomina.setVisible(true);
                infoPanelEmp.setVisible(false);
                nominaPanel.setVisible(false);
                String titulosNomina[] = {"N°", "Nombre", "Puesto"};
                DefaultTableModel DTMN = new DefaultTableModel(titulosNomina, 0);
                tablaInventario.setModel(DTMN);
                try {
                    Statement ST = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    String sql = "select Nombre, Apellido, Puesto from TRABAJADORES";
                    ResultSet res = ST.executeQuery(sql);
                    int i = 1;
                    if (res.next()) {
                        do {
                            Object fila[] = {String.valueOf(i), res.getString("Nombre") + " " + res.getString("Apellido"), res.getString("Puesto")};
                            DTMN.addRow(fila);
                            i++;
                        } while (res.next());
                        tablaListaNomina.setModel(DTMN);
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }
                break;

        }
    }//GEN-LAST:event_tabbedEmpleadosStateChanged

    private void tablaPDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPDMouseClicked
        rowPD = tablaPD.getSelectedRow();
        for (int i = 0; i < 12; i++) {
            datosPD[i] = String.valueOf(tablaPD.getValueAt(rowPD, i));
        }
        tablaPD.setValueAt(Double.valueOf(datosPD[3]) + Double.valueOf(datosPD[4]) + Double.valueOf(datosPD[5]), rowPD, 6);
        tablaPD.setValueAt(Double.valueOf(datosPD[7]) + Double.valueOf(datosPD[8]) + Double.valueOf(datosPD[9]), rowPD, 10);
        tablaPD.setValueAt(Double.valueOf(datosPD[6]) - Double.valueOf(datosPD[10]), rowPD, 11);
    }//GEN-LAST:event_tablaPDMouseClicked

    private void jTabbedPane6StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane6StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane6StateChanged

    private void tablaListaNominaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaListaNominaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaListaNominaFocusLost

    private void tablaListaNominaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaListaNominaMouseClicked
        rowNomina = tablaListaNomina.getSelectedRow();
    }//GEN-LAST:event_tablaListaNominaMouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        ((DefaultTableModel) tablaListaNomina.getModel()).removeRow(rowNomina);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
//        String f3 = String.valueOf(fn3.getCalendar().get(Calendar.DAY_OF_MONTH)+"/"+(fn3.getCalendar().get(Calendar.MONTH)+1)+"/"+fn3.getCalendar().get(Calendar.YEAR));
//        String f4 = String.valueOf(fn4.getCalendar().get(Calendar.DAY_OF_MONTH)+"/"+(fn4.getCalendar().get(Calendar.MONTH)+1)+"/"+fn4.getCalendar().get(Calendar.YEAR));  
        if (tablaListaNomina.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No hay empleados en la tabla", "Aviso", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String f3 = String.valueOf(fn3.getCalendar().get(Calendar.DAY_OF_MONTH) + "/" + (fn3.getCalendar().get(Calendar.MONTH) + 1) + "/" + fn3.getCalendar().get(Calendar.YEAR));
                String f4 = String.valueOf(fn4.getCalendar().get(Calendar.DAY_OF_MONTH) + "/" + (fn4.getCalendar().get(Calendar.MONTH) + 1) + "/" + fn4.getCalendar().get(Calendar.YEAR));
                if (tablaListaNomina.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "No hay empleados en la tabla", "Aviso", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        String choosertitle = "";
                        JFileChooser chooser = new JFileChooser();
                        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        int option = chooser.showSaveDialog(null);
                        if (option == JFileChooser.APPROVE_OPTION) {
                            JOptionPane.showMessageDialog(null, "Se generó correctamente el PDF", "Exito", JOptionPane.INFORMATION_MESSAGE);
                            File file = chooser.getSelectedFile();
                            choosertitle = file.getAbsolutePath();
                            System.out.println("Ruta:" + choosertitle + ".pdf");
                            choosertitle = choosertitle + ".pdf";
                            Document document = new Document();
                            PdfWriter.getInstance(document, new FileOutputStream(choosertitle));
                            document.open();
                            //addMetaData(document);
                            document.addTitle("Cotizacion SOCET");
                            document.addSubject("Digitalizada");
                            document.addKeywords("Cotizacion,SOCET");
                            document.addAuthor("Fabian Gaxiola");
                            document.addCreator("Fabian Gaxiola");
                            //addTitlePage(document);
                            Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
                            Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
                            Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
                            Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
                            Font titleArial = new Font(Font.FontFamily.HELVETICA, 13);
                            Font bodyArial = new Font(Font.FontFamily.HELVETICA, 11);
                            Image imagen = Image.getInstance("src/Miscelaneo/1.png");
                            //imagen.setAlignment(Element.ALIGN_CENTER);
                            imagen.setAbsolutePosition(60f, 715f);
                            imagen.scaleAbsolute(90, 90);
                            document.add(imagen);
                            Paragraph par = new Paragraph();
                            //document.add(Chunk.NEWLINE);
                            par.setIndentationLeft(240);
                            par.add(new Paragraph("SOCET S.A. de C.V.", titleArial));
                            document.add(par);
                            //document.add(Chunk.NEWLINE);
                            Paragraph par1 = new Paragraph();
                            par1.setIndentationLeft(120);
                            par1.add(new Paragraph("San José Obrero #2325 Ote. Col. Hacienda San José Cd. Obregón, Sonora.", bodyArial));
                            document.add(par1);
                            Paragraph par2 = new Paragraph();
                            par2.setIndentationLeft(250);
                            par2.add(new Paragraph("Tel: (644)1.69.76.86", bodyArial));
                            document.add(par2);
                            Paragraph par3 = new Paragraph();
                            par3.setIndentationLeft(210);
                            par3.add(new Paragraph("e-Mail: socetsadecv@yahoo.com.mx", bodyArial));
                            document.add(par3);
                            document.add(Chunk.NEWLINE);
                            Paragraph par4 = new Paragraph();
                            par4.setIndentationLeft(230);
                            par4.add(new Paragraph("Lista Nomina.", titleArial));
                            document.add(par4);
                            document.add(Chunk.NEWLINE);
                            PdfPTable tableemp = new PdfPTable(2);
                            tableemp.setWidthPercentage(50);
                            tableemp.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            PdfPCell id = new PdfPCell(new Phrase("Inicio Periodo"));
                            id.setColspan(1);
                            id.setRowspan(1);
                            id.setBackgroundColor(new BaseColor(250, 191, 0));
                            tableemp.addCell(id);
                            PdfPCell fecha = new PdfPCell(new Phrase("Final Periodo"));
                            fecha.setColspan(1);
                            fecha.setRowspan(1);
                            fecha.setBackgroundColor(new BaseColor(250, 191, 0));
                            tableemp.addCell(fecha);
                            PdfPCell a7 = new PdfPCell(new Phrase(f3));
                            a7.setBackgroundColor(BaseColor.WHITE);
                            a7.setColspan(1);
                            tableemp.addCell(a7);
                            PdfPCell a8 = new PdfPCell(new Phrase(f4));
                            a8.setBackgroundColor(BaseColor.WHITE);
                            a8.setColspan(1);
                            tableemp.addCell(a8);
                            document.add(tableemp);
                            document.add(Chunk.NEWLINE);
                            PdfPTable table = new PdfPTable(12);
                            PdfPCell idcli = new PdfPCell(new Phrase("No."));
                            idcli.setColspan(1);
                            idcli.setRowspan(1);
                            idcli.setBackgroundColor(new BaseColor(250, 191, 0));
                            table.addCell(idcli);
                            PdfPCell nombre = new PdfPCell(new Phrase("Nombre"));
                            nombre.setColspan(5);
                            nombre.setBackgroundColor(new BaseColor(250, 191, 0));
                            table.addCell(nombre);
                            PdfPCell empresa = new PdfPCell(new Phrase("Puesto"));
                            empresa.setColspan(3);
                            empresa.setRowspan(1);
                            empresa.setBackgroundColor(new BaseColor(250, 191, 0));
                            table.addCell(empresa);
                            PdfPCell empresa1 = new PdfPCell(new Phrase("Firma"));
                            empresa1.setColspan(3);
                            empresa1.setRowspan(1);
                            empresa1.setBackgroundColor(new BaseColor(250, 191, 0));
                            table.addCell(empresa1);
                            for (int i = 0; i < tablaListaNomina.getRowCount(); i++) {
                                if (i % 2 == 0) {
                                    PdfPCell v1 = new PdfPCell(new Phrase(tablaListaNomina.getModel().getValueAt(i, 0).toString()));
                                    v1.setBackgroundColor(BaseColor.WHITE);
                                    v1.setColspan(1);
                                    table.addCell(v1);
                                    PdfPCell v2 = new PdfPCell(new Phrase(tablaListaNomina.getModel().getValueAt(i, 1).toString()));
                                    v2.setBackgroundColor(BaseColor.WHITE);
                                    v2.setColspan(5);
                                    table.addCell(v2);
                                    PdfPCell v3 = new PdfPCell(new Phrase(tablaListaNomina.getModel().getValueAt(i, 2).toString()));
                                    v3.setBackgroundColor(BaseColor.WHITE);
                                    v3.setColspan(3);
                                    table.addCell(v3);
                                    PdfPCell v4 = new PdfPCell();
                                    v4.setBackgroundColor(BaseColor.WHITE);
                                    v4.setColspan(3);
                                    table.addCell(v4);
                                } else {
                                    PdfPCell v1 = new PdfPCell(new Phrase(tablaListaNomina.getModel().getValueAt(i, 0).toString()));
                                    v1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                    v1.setColspan(1);
                                    table.addCell(v1);
                                    PdfPCell v2 = new PdfPCell(new Phrase(tablaListaNomina.getModel().getValueAt(i, 1).toString()));
                                    v2.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                    v2.setColspan(5);
                                    table.addCell(v2);
                                    PdfPCell v3 = new PdfPCell(new Phrase(tablaListaNomina.getModel().getValueAt(i, 2).toString()));
                                    v3.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                    v3.setColspan(3);
                                    table.addCell(v3);
                                    PdfPCell v4 = new PdfPCell();
                                    v4.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                    v4.setColspan(3);
                                    table.addCell(v4);
                                }
                            }
                            document.add(table);
                            document.close();
                        }
                    } catch (DocumentException | IOException e) {
                        System.out.println(e);
                    }
                }
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Fecha inválida", "Aviso", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        ((DefaultTableModel) tablaPD.getModel()).removeRow(rowPD);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void tabbedProveedoresStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedProveedoresStateChanged
        switch (tabbedProveedores.getSelectedIndex()) {
            case 0:
                infoProveedores.setVisible(true);
                panelPago.setVisible(false);
                panelCompra.setVisible(false);
                break;
            case 1:
                panelPago.setVisible(true);
                infoProveedores.setVisible(false);
                panelCompra.setVisible(false);
                break;
            case 2:
                panelCompra.setVisible(true);
                infoProveedores.setVisible(false);
                panelPago.setVisible(false);
                break;
        }
    }//GEN-LAST:event_tabbedProveedoresStateChanged

    private void tabbedObrasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedObrasStateChanged
        switch (tabbedObras.getSelectedIndex()) {
            case 0:
                infoObras.setVisible(true);
                seguimientoPanel.setVisible(false);
                asignarEmpleadosP.setVisible(false);
                verProductosObra.setVisible(false);
                break;
            case 1:
                seguimientoPanel.setVisible(true);
                infoObras.setVisible(false);
                asignarEmpleadosP.setVisible(false);
                verProductosObra.setVisible(false);
                break;
            case 2:
                asignarEmpleadosP.setVisible(true);
                seguimientoPanel.setVisible(false);
                infoObras.setVisible(false);
                verProductosObra.setVisible(false);
                break;
            case 3:
                verProductosObra.setVisible(true);
                asignarEmpleadosP.setVisible(false);
                seguimientoPanel.setVisible(false);
                infoObras.setVisible(false);
                break;
        }
    }//GEN-LAST:event_tabbedObrasStateChanged

    private void tabbedClientesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedClientesStateChanged
        switch (tabbedClientes.getSelectedIndex()) {
            case 0:
                infoClientes.setVisible(true);
                break;
            case 1:
                infoClientes.setVisible(false);
                break;
        }
    }//GEN-LAST:event_tabbedClientesStateChanged

    private void tablaProveedores1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaProveedores1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaProveedores1FocusLost

    private void tablaProveedores1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProveedores1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaProveedores1MouseClicked

    private void btnAgregarP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarP1ActionPerformed
        AgregarPago nv = new AgregarPago(this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_btnAgregarP1ActionPerformed

    private void txtBuscarOrdenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarOrdenFocusGained
        if (txtBuscarOrden.getText().equals("Buscar por folio de orden....")) {
            txtBuscarOrden.setText("");
        }
    }//GEN-LAST:event_txtBuscarOrdenFocusGained

    private void txtBuscarOrdenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarOrdenFocusLost
        if (txtBuscarOrden.getText().isEmpty()) {
            txtBuscarOrden.setText("Buscar por folio de orden....");
        }
    }//GEN-LAST:event_txtBuscarOrdenFocusLost

    private void txtBuscarOrdenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarOrdenKeyPressed
        String proveedorCompra = "";
        try {
            Statement smt = c.createStatement();
            String sql = "Select IDProveedor from PROVEEDORES where Nombre='" + Home.comboProveedores.getItemAt(Home.comboProveedores.getSelectedIndex()) + "'";
            ResultSet res = smt.executeQuery(sql);
            if (res.next()) {
                proveedorCompra = res.getString("IDProveedor");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        DTMPO = new DefaultTableModel(titulosPagoProveedor, 0);
        lmao = proveedorCompra;
        tablaProveedores1.setModel(DTMPO);
        ResultSet res = mostrar(txtBuscarOrden.getText(), 8);
        try {
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("FechaOrden"), res.getString("Folio"), res.getString("Cantidad"), res.getString("Estado"), res.getString("Obra"), res.getString("FechaPago")};
                    DTMPO.addRow(fila);
                } while (res.next());
                tablaProveedores1.setModel(DTMPO);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscarOrdenKeyPressed

    private void txtBuscarOrdenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarOrdenKeyReleased
        String proveedorCompra = "";
        try {
            Statement smt = c.createStatement();
            String sql = "Select IDProveedor from PROVEEDORES where Nombre='" + Home.comboProveedores.getItemAt(Home.comboProveedores.getSelectedIndex()) + "'";
            ResultSet res = smt.executeQuery(sql);
            if (res.next()) {
                proveedorCompra = res.getString("IDProveedor");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        DTMPO = new DefaultTableModel(titulosPagoProveedor, 0);
        lmao = proveedorCompra;
        tablaProveedores1.setModel(DTMPO);
        ResultSet res = mostrar(txtBuscarOrden.getText(), 8);
        try {
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("FechaOrden"), res.getString("Folio"), res.getString("Cantidad"), res.getString("Estado"), res.getString("Obra"), res.getString("FechaPago")};
                    DTMPO.addRow(fila);
                } while (res.next());
                tablaProveedores1.setModel(DTMPO);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscarOrdenKeyReleased

    private void tablaObras1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaObras1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaObras1FocusLost

    private void tablaObras1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaObras1MouseClicked
        int row = tablaObras1.getSelectedRow();
        //Tabla Seguimientos
        String titulosObraS[] = {"IDSeguimiento", "Fecha", "Pago", "Descripcion"};
        DTMC = new DefaultTableModel(titulosObraS, 0);
        tablaObras2.setModel(DTMC);
        try {
            Statement smt = c.createStatement();
            String sql = "select IDSeguimiento,Fecha,Pago,Descripcion from SEGUIMIENTOS where Obra='" + tablaObras1.getValueAt(row, 0) + "'";
            ResultSet res = smt.executeQuery(sql);
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("IDSeguimiento"), res.getString("Fecha"), res.getString("Pago"), res.getString("Descripcion")};
                    DTMC.addRow(fila);
                } while (res.next());
                tablaObras2.setModel(DTMC);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_tablaObras1MouseClicked

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        Obras.AgregarSeguimiento nv = new Obras.AgregarSeguimiento(this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        Proveedores.SeleccionarObra nv = new Proveedores.SeleccionarObra(1,this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        Proveedores.SeleccionarProveedor nv = new Proveedores.SeleccionarProveedor(this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        try {
            ((DefaultTableModel) tablaProveedores2.getModel()).removeRow(rowCompraP);
            double subtotalC = 0, ivaC, totalC = 0;
            for (int i = 0; i < tablaProveedores2.getRowCount(); i++) {
                subtotalC += Double.valueOf(String.valueOf(tablaProveedores2.getValueAt(i, 3)));
            }
            ivaC = subtotalC * 0.16;
            totalC = subtotalC + ivaC;
            subtotalCompra.setText(String.valueOf(subtotalC));
            ivaCompra.setText(String.valueOf(ivaC));
            totalCompra.setText(String.valueOf(totalC));
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "No se pueden eliminar todas las filas", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void tablaProveedores2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProveedores2MouseClicked
        rowCompraP = tablaProveedores2.getSelectedRow();
        tablaProveedores2.setValueAt(Double.parseDouble(String.valueOf(tablaProveedores2.getValueAt(rowCompraP, 1))) * Double.parseDouble(String.valueOf(tablaProveedores2.getValueAt(rowCompraP, 2))), rowCompraP, 3);
        double subtotalC = 0, ivaC, totalC = 0;
        for (int i = 0; i < tablaProveedores2.getRowCount(); i++) {
            subtotalC += Double.valueOf(String.valueOf(tablaProveedores2.getValueAt(i, 3)));
        }
        ivaC = subtotalC * 0.16;
        totalC = subtotalC + ivaC;
        subtotalCompra.setText(String.valueOf(subtotalC));
        ivaCompra.setText(String.valueOf(ivaC));
        totalCompra.setText(String.valueOf(totalC));
    }//GEN-LAST:event_tablaProveedores2MouseClicked

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        ((DefaultTableModel) tablaProveedores2.getModel()).addRow(new Object[]{"", "0", "0", "0"});
    }//GEN-LAST:event_jButton22ActionPerformed

    private void tablaObras2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaObras2FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaObras2FocusLost

    private void tablaObras2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaObras2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaObras2MouseClicked

    private void Tabla1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Tabla1FocusGained
        System.out.println("Columna: " + Tabla1.getSelectedColumn());
        System.out.println("Fila: " + Tabla1.getSelectedRow());
        TAC = 0;

        for (int i = 0; i < 7; i++) {
            if (Tabla1.getValueAt(i, 1) != null) {
                TAC += (double) Tabla1.getValueAt(i, 1);
            } else {

            }

            Tabla1.setValueAt(TAC, 6, 2);
            TotalActivo.setText("" + (TAC + TAF + TAD));
            ActualizarTodo();
        }
    }//GEN-LAST:event_Tabla1FocusGained

    private void Tabla1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Tabla1FocusLost
        System.out.println("Columna: " + Tabla1.getSelectedColumn());
        System.out.println("Fila: " + Tabla1.getSelectedRow());
        TAC = 0;

        for (int i = 0; i < 7; i++) {
            if (Tabla1.getValueAt(i, 1) != null) {
                TAC += (double) Tabla1.getValueAt(i, 1);
            } else {

            }

            Tabla1.setValueAt(TAC, 6, 2);
            TotalActivo.setText("" + (TAC + TAF + TAD));
            ActualizarTodo();
        }
    }//GEN-LAST:event_Tabla1FocusLost

    private void Tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla1MouseClicked
        System.out.println("Columna: " + Tabla1.getSelectedColumn());
        System.out.println("Fila: " + Tabla1.getSelectedRow());
        TAC = 0;

        for (int i = 0; i < 7; i++) {
            if (Tabla1.getValueAt(i, 1) != null) {
                TAC += (double) Tabla1.getValueAt(i, 1);
            } else {

            }
        }
        Tabla1.setValueAt(TAC, 6, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        TotalPasivo.setText("" + (TPC + TPF));

    }//GEN-LAST:event_Tabla1MouseClicked

    private void Tabla1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabla1KeyPressed
        System.out.println("Hola1");

    }//GEN-LAST:event_Tabla1KeyPressed

    private void Tabla1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabla1KeyReleased
        System.out.println("Hola2");
    }//GEN-LAST:event_Tabla1KeyReleased

    private void Tabla1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabla1KeyTyped
        System.out.println("Columna: " + Tabla1.getSelectedColumn());
        System.out.println("Fila: " + Tabla1.getSelectedRow());
        TAC = 0;

        for (int i = 0; i < 7; i++) {
            if (Tabla1.getValueAt(i, 1) != null) {
                TAC += (double) Tabla1.getValueAt(i, 1);
            } else {

            }
        }
        Tabla1.setValueAt(TAC, 6, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        TotalPasivo.setText("" + (TPC + TPF));

    }//GEN-LAST:event_Tabla1KeyTyped

    private void Tabla6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Tabla6FocusGained
        TC = 0;

        for (int i = 0; i < 5; i++) {
            if (Tabla6.getValueAt(i, 1) != null) {
                TC += (double) Tabla6.getValueAt(i, 1);
            } else {

            }
        }
        Tabla6.setValueAt(TC, 4, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        TotalPasivo.setText("" + (TPC + TPF));
        TotalCapital.setText("" + TC);
        TotalPC.setText("" + (TPC + TPF + TC));
    }//GEN-LAST:event_Tabla6FocusGained

    private void Tabla6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Tabla6FocusLost
        TC = 0;

        for (int i = 0; i < 5; i++) {
            if (Tabla6.getValueAt(i, 1) != null) {
                TC += (double) Tabla6.getValueAt(i, 1);
            } else {

            }
        }
        Tabla6.setValueAt(TC, 4, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        TotalPasivo.setText("" + (TPC + TPF));
        TotalCapital.setText("" + TC);
        TotalPC.setText("" + (TPC + TPF + TC));
    }//GEN-LAST:event_Tabla6FocusLost

    private void Tabla6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla6MouseClicked
        TC = 0;

        for (int i = 0; i < 5; i++) {
            if (Tabla6.getValueAt(i, 1) != null) {
                TC += (double) Tabla6.getValueAt(i, 1);
            } else {

            }
        }
        Tabla6.setValueAt(TC, 4, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        TotalPasivo.setText("" + (TPC + TPF));
        TotalCapital.setText("" + TC);
        TotalPC.setText("" + (TPC + TPF + TC));
    }//GEN-LAST:event_Tabla6MouseClicked

    private void Tabla6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabla6KeyTyped
        TC = 0;

        for (int i = 0; i < 5; i++) {
            if (Tabla6.getValueAt(i, 1) != null) {
                TC += (double) Tabla6.getValueAt(i, 1);
            } else {

            }
        }
        Tabla6.setValueAt(TC, 4, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        TotalPasivo.setText("" + (TPC + TPF));
        TotalCapital.setText("" + TC);
        TotalPC.setText("" + (TPC + TPF + TC));
    }//GEN-LAST:event_Tabla6KeyTyped

    private void Tabla2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Tabla2FocusGained

        TAF = 0;

        if (Tabla2.getSelectedColumn() == 1) {
            for (int i = 0; i < 7; i++) {
                if (Tabla2.getValueAt(i, 1) != null) {
                    TAF += (double) Tabla2.getValueAt(i, 1);
                } else {

                }
            }
            Tabla2.setValueAt(TAF, 6, 2);
            TotalActivo.setText("" + (TAC + TAF + TAD));
            ActualizarTodo();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_Tabla2FocusGained

    private void Tabla2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Tabla2FocusLost

        TAF = 0;

        for (int i = 0; i < 7; i++) {
            if (Tabla2.getValueAt(i, 1) != null) {
                TAF += (double) Tabla2.getValueAt(i, 1);
            } else {

            }
        }
        Tabla2.setValueAt(TAF, 6, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        ActualizarTodo();

    }//GEN-LAST:event_Tabla2FocusLost

    private void Tabla2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla2MouseClicked
        TAF = 0;

        for (int i = 0; i < 7; i++) {
            if (Tabla2.getValueAt(i, 1) != null) {
                TAF += (double) Tabla2.getValueAt(i, 1);
            } else {

            }
        }
        Tabla2.setValueAt(TAF, 6, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        TotalPasivo.setText("" + (TPC + TPF));
    }//GEN-LAST:event_Tabla2MouseClicked

    private void Tabla2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabla2KeyPressed

    }//GEN-LAST:event_Tabla2KeyPressed

    private void Tabla2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabla2KeyTyped
        TAF = 0;

        for (int i = 0; i < 7; i++) {
            if (Tabla2.getValueAt(i, 1) != null) {
                TAF += (double) Tabla2.getValueAt(i, 1);
            } else {

            }
        }
        Tabla2.setValueAt(TAF, 6, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        TotalPasivo.setText("" + (TPC + TPF));

    }//GEN-LAST:event_Tabla2KeyTyped

    private void Tabla3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Tabla3FocusGained
        TAD = 0;

        for (int i = 0; i < 7; i++) {
            if (Tabla3.getValueAt(i, 1) != null) {
                TAD += (double) Tabla3.getValueAt(i, 1);
            } else {

            }
        }
        Tabla3.setValueAt(TAD, 6, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        ActualizarTodo();

    }//GEN-LAST:event_Tabla3FocusGained

    private void Tabla3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Tabla3FocusLost
        TAD = 0;

        for (int i = 0; i < 7; i++) {
            if (Tabla3.getValueAt(i, 1) != null) {
                TAD += (double) Tabla3.getValueAt(i, 1);
            } else {

            }
        }
        Tabla3.setValueAt(TAD, 6, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
    }//GEN-LAST:event_Tabla3FocusLost

    private void Tabla3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla3MouseClicked
        TAD = 0;

        for (int i = 0; i < 7; i++) {
            if (Tabla3.getValueAt(i, 1) != null) {
                TAD += (double) Tabla3.getValueAt(i, 1);
            } else {

            }
        }
        Tabla3.setValueAt(TAD, 6, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        TotalPasivo.setText("" + (TPC + TPF));
    }//GEN-LAST:event_Tabla3MouseClicked

    private void Tabla3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabla3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tabla3KeyPressed

    private void Tabla3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabla3KeyTyped
        TAD = 0;

        for (int i = 0; i < 7; i++) {
            if (Tabla3.getValueAt(i, 1) != null) {
                TAD += (double) Tabla3.getValueAt(i, 1);
            } else {

            }
        }
        Tabla3.setValueAt(TAD, 6, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        TotalPasivo.setText("" + (TPC + TPF));
    }//GEN-LAST:event_Tabla3KeyTyped

    private void Tabla4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Tabla4FocusGained
        TPC = 0;

        for (int i = 0; i < 7; i++) {
            if (Tabla4.getValueAt(i, 1) != null) {
                TPC += (double) Tabla4.getValueAt(i, 1);
            } else {

            }
        }
        Tabla4.setValueAt(TPC, 6, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        TotalPasivo.setText("" + (TPC + TPF));
        TotalPC.setText("" + (TPC + TPF + TC));
    }//GEN-LAST:event_Tabla4FocusGained

    private void Tabla4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Tabla4FocusLost
        TPC = 0;

        for (int i = 0; i < 7; i++) {
            if (Tabla4.getValueAt(i, 1) != null) {
                TPC += (double) Tabla4.getValueAt(i, 1);
            } else {

            }
        }
        Tabla4.setValueAt(TPC, 6, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        TotalPasivo.setText("" + (TPC + TPF));
        TotalPC.setText("" + (TPC + TPF + TC));
    }//GEN-LAST:event_Tabla4FocusLost

    private void Tabla4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla4MouseClicked
        TPC = 0;

        for (int i = 0; i < 7; i++) {
            if (Tabla4.getValueAt(i, 1) != null) {
                TPC += (double) Tabla4.getValueAt(i, 1);
            } else {

            }
        }
        Tabla4.setValueAt(TPC, 6, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        TotalPasivo.setText("" + (TPC + TPF));
        TotalPC.setText("" + (TPC + TPF + TC));
    }//GEN-LAST:event_Tabla4MouseClicked

    private void Tabla4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabla4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tabla4KeyPressed

    private void Tabla4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabla4KeyTyped
        TPC = 0;

        for (int i = 0; i < 7; i++) {
            if (Tabla4.getValueAt(i, 1) != null) {
                TPC += (double) Tabla4.getValueAt(i, 1);
            } else {

            }
        }
        Tabla4.setValueAt(TPC, 6, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        TotalPasivo.setText("" + (TPC + TPF));
        TotalPC.setText("" + (TPC + TPF + TC));
    }//GEN-LAST:event_Tabla4KeyTyped

    private void Tabla5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Tabla5FocusGained
        TPF = 0;

        for (int i = 0; i < 5; i++) {
            if (Tabla5.getValueAt(i, 1) != null) {
                TPF += (double) Tabla5.getValueAt(i, 1);
            } else {

            }
        }
        Tabla5.setValueAt(TPF, 4, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        TotalPasivo.setText("" + (TPC + TPF));
        TotalPC.setText("" + (TPC + TPF + TC));
    }//GEN-LAST:event_Tabla5FocusGained

    private void Tabla5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Tabla5FocusLost
        TPF = 0;

        for (int i = 0; i < 5; i++) {
            if (Tabla5.getValueAt(i, 1) != null) {
                TPF += (double) Tabla5.getValueAt(i, 1);
            } else {

            }
        }
        Tabla5.setValueAt(TPF, 4, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        TotalPasivo.setText("" + (TPC + TPF));
        TotalPC.setText("" + (TPC + TPF + TC));
    }//GEN-LAST:event_Tabla5FocusLost

    private void Tabla5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla5MouseClicked
        TPF = 0;

        for (int i = 0; i < 5; i++) {
            if (Tabla5.getValueAt(i, 1) != null) {
                TPF += (double) Tabla5.getValueAt(i, 1);
            } else {

            }
        }
        Tabla5.setValueAt(TPF, 4, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        TotalPasivo.setText("" + (TPC + TPF));
        TotalPC.setText("" + (TPC + TPF + TC));
    }//GEN-LAST:event_Tabla5MouseClicked

    private void Tabla5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabla5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tabla5KeyPressed

    private void Tabla5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabla5KeyTyped
        TPF = 0;

        for (int i = 0; i < 5; i++) {
            if (Tabla5.getValueAt(i, 1) != null) {
                TPF += (double) Tabla5.getValueAt(i, 1);
            } else {

            }
        }
        Tabla5.setValueAt(TPF, 4, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        TotalPasivo.setText("" + (TPC + TPF));
        TotalPC.setText("" + (TPC + TPF + TC));
    }//GEN-LAST:event_Tabla5KeyTyped

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jPanel3formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3formMouseClicked
        ActualizarTodo();
        System.out.println("Hola");
    }//GEN-LAST:event_jPanel3formMouseClicked

    private void comboProveedoresItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboProveedoresItemStateChanged
        String proveedorCompra = "";
        try {
            Statement smt = c.createStatement();
            String sql = "Select IDProveedor from PROVEEDORES where Nombre='" + Home.comboProveedores.getItemAt(Home.comboProveedores.getSelectedIndex()) + "'";
            ResultSet res = smt.executeQuery(sql);
            if (res.next()) {
                proveedorCompra = res.getString("IDProveedor");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        //Tabla de compras
        DTMPRO = new DefaultTableModel(titulosPagoProveedor, 0);
        tablaProveedores1.setModel(DTMPRO);
        try {
            Statement smt = c.createStatement();
            String sql = "Select * from TABLA_PROVEEDOR where Proveedor='" + proveedorCompra + "'";
            ResultSet res = smt.executeQuery(sql);
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("FechaOrden"), res.getString("Folio"), res.getString("Cantidad"), res.getString("Estado"), res.getString("Obra"), res.getString("FechaPago")};
                    DTMPRO.addRow(fila);
                } while (res.next());
                tablaProveedores1.setModel(DTMPRO);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_comboProveedoresItemStateChanged

    private void txtBuscarObras1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarObras1KeyReleased
        String titulosObraS[] = {"Matricula", "Nombre", "Cliente"};
        DTMO = new DefaultTableModel(titulosObraS, 0);
        tablaObras1.setModel(DTMO);
        ResultSet res = mostrar(txtBuscarObras1.getText(), 9);
        try {
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("IDObra"), res.getString("NombreObra"), res.getString("Cliente")};
                    DTMO.addRow(fila);
                } while (res.next());
                tablaObras1.setModel(DTMO);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscarObras1KeyReleased

    private void txtBuscarObras1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarObras1KeyPressed
        String titulosObraS[] = {"Matricula", "Nombre", "Cliente"};
        DTMO = new DefaultTableModel(titulosObraS, 0);
        tablaObras1.setModel(DTMO);
        ResultSet res = mostrar(txtBuscarObras1.getText(), 9);
        try {
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("IDObra"), res.getString("NombreObra"), res.getString("Cliente")};
                    DTMO.addRow(fila);
                } while (res.next());
                tablaObras1.setModel(DTMO);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscarObras1KeyPressed

    private void txtBuscarObras1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarObras1FocusLost
        if (txtBuscarObras1.getText().isEmpty()) {
            txtBuscarObras1.setText("Buscar por cliente, nombre, o matricula de obra...");
        }
    }//GEN-LAST:event_txtBuscarObras1FocusLost

    private void txtBuscarObras1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarObras1FocusGained
        if (txtBuscarObras1.getText().equals("Buscar por cliente, nombre, o matricula de obra...")) {
            txtBuscarObras1.setText("");
        }
    }//GEN-LAST:event_txtBuscarObras1FocusGained

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        System.out.println(tablaCC1.getRowCount());
        System.out.println(tablaDP.getRowCount());
        System.out.println(tablaProveedores2.getRowCount());
        if (!(tablaCC1.getRowCount() == 0 || tablaDP.getRowCount() == 0 || tablaProveedores2.getRowCount() == 0)) {
            try {
                String choosertitle = "Guardar orden de compra";
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int option = chooser.showSaveDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(null, "Se generó correctamente el PDF", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    File file = chooser.getSelectedFile();
                    choosertitle = file.getAbsolutePath();
                    System.out.println("Ruta:" + choosertitle + ".pdf");
                    choosertitle = choosertitle + ".pdf";
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(choosertitle));
                    document.open();
                    //addMetaData(document);
                    document.addTitle("Orden Compra SOCET");
                    document.addSubject("Digitalizada");
                    document.addKeywords("Orden Compra,SOCET");
                    document.addAuthor("Fabian Gaxiola");
                    document.addCreator("Fabian Gaxiola");
                    //addTitlePage(document);
                    Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
                    Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
                    Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
                    Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
                    Font titleArial = new Font(Font.FontFamily.HELVETICA, 13);
                    Font bodyArial = new Font(Font.FontFamily.HELVETICA, 11);
                    Image imagen = Image.getInstance("src/Miscelaneo/1.png");
                    //imagen.setAlignment(Element.ALIGN_CENTER);
                    imagen.setAbsolutePosition(60f, 715f);
                    imagen.scaleAbsolute(90, 90);
                    document.add(imagen);
                    Paragraph par = new Paragraph();
                    //document.add(Chunk.NEWLINE);
                    par.setIndentationLeft(240);
                    par.add(new Paragraph("SOCET S.A. de C.V.", titleArial));
                    document.add(par);
                    //document.add(Chunk.NEWLINE);
                    Paragraph par1 = new Paragraph();
                    par1.setIndentationLeft(120);
                    par1.add(new Paragraph("San José Obrero #2325 Ote. Col. Hacienda San José Cd. Obregón, Sonora.", bodyArial));
                    document.add(par1);
                    Paragraph par2 = new Paragraph();
                    par2.setIndentationLeft(250);
                    par2.add(new Paragraph("Tel: (644)1.69.76.86", bodyArial));
                    document.add(par2);
                    Paragraph par3 = new Paragraph();
                    par3.setIndentationLeft(210);
                    par3.add(new Paragraph("e-Mail: socetsadecv@yahoo.com.mx", bodyArial));
                    document.add(par3);
                    document.add(Chunk.NEWLINE);
                    Paragraph par4 = new Paragraph();
                    par4.setIndentationLeft(230);
                    par4.add(new Paragraph("Orden Compra.", titleArial));
                    document.add(par4);
                    document.add(Chunk.NEWLINE);
                    PdfPTable tableemp = new PdfPTable(4);
                    //tableemp.setWidthPercentage(100);
                    //tableemp.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    PdfPCell id = new PdfPCell(new Phrase("Matricula  Obra"));
                    id.setColspan(1);
                    id.setRowspan(1);
                    id.setBackgroundColor(new BaseColor(250, 191, 0));
                    tableemp.addCell(id);
                    PdfPCell nombre = new PdfPCell(new Phrase("Nombre"));
                    nombre.setColspan(1);
                    nombre.setRowspan(1);
                    nombre.setBackgroundColor(new BaseColor(250, 191, 0));
                    tableemp.addCell(nombre);
                    PdfPCell cliente = new PdfPCell(new Phrase("Cliente"));
                    cliente.setColspan(1);
                    cliente.setRowspan(1);
                    cliente.setBackgroundColor(new BaseColor(250, 191, 0));
                    tableemp.addCell(cliente);
                    PdfPCell fechao = new PdfPCell(new Phrase("Fecha de Orden"));
                    fechao.setColspan(1);
                    fechao.setRowspan(1);
                    fechao.setBackgroundColor(new BaseColor(250, 191, 0));
                    tableemp.addCell(fechao);
                    PdfPCell a7 = new PdfPCell(new Phrase(tablaCC1.getModel().getValueAt(0, 0).toString()));
                    a7.setBackgroundColor(BaseColor.WHITE);
                    a7.setColspan(1);
                    tableemp.addCell(a7);
                    PdfPCell a8 = new PdfPCell(new Phrase(tablaCC1.getModel().getValueAt(0, 1).toString()));
                    a8.setBackgroundColor(BaseColor.WHITE);
                    a8.setColspan(1);
                    tableemp.addCell(a8);
                    PdfPCell a9 = new PdfPCell(new Phrase(tablaCC1.getModel().getValueAt(0, 2).toString()));
                    a9.setBackgroundColor(BaseColor.WHITE);
                    a9.setColspan(1);
                    tableemp.addCell(a9);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate localDate = LocalDate.now();
                    PdfPCell a10 = new PdfPCell(new Phrase(dtf.format(localDate)));
                    a10.setBackgroundColor(BaseColor.WHITE);
                    a10.setColspan(1);
                    tableemp.addCell(a10);
                    document.add(tableemp);
                    document.add(Chunk.NEWLINE);
                    PdfPTable table = new PdfPTable(4);
                    PdfPCell idcli = new PdfPCell(new Phrase("Cod. Proveedor"));
                    idcli.setColspan(1);
                    idcli.setRowspan(1);
                    idcli.setBackgroundColor(new BaseColor(250, 191, 0));
                    table.addCell(idcli);
                    PdfPCell nombre1 = new PdfPCell(new Phrase("Nombre"));
                    nombre1.setColspan(1);
                    nombre1.setBackgroundColor(new BaseColor(250, 191, 0));
                    table.addCell(nombre1);
                    PdfPCell empresa = new PdfPCell(new Phrase("Telefono"));
                    empresa.setColspan(1);
                    empresa.setRowspan(1);
                    empresa.setBackgroundColor(new BaseColor(250, 191, 0));
                    table.addCell(empresa);
                    PdfPCell ciudad = new PdfPCell(new Phrase("Direccion"));
                    ciudad.setBackgroundColor(new BaseColor(250, 191, 0));
                    ciudad.setColspan(1);
                    table.addCell(ciudad);
                    PdfPCell a1 = new PdfPCell(new Phrase(tablaDP.getModel().getValueAt(0, 0).toString()));
                    a1.setColspan(1);
                    a1.setBackgroundColor(BaseColor.WHITE);
                    table.addCell(a1);
                    PdfPCell a2 = new PdfPCell(new Phrase(tablaDP.getModel().getValueAt(0, 1).toString()));
                    a2.setBackgroundColor(BaseColor.WHITE);
                    a2.setColspan(1);
                    table.addCell(a2);
                    PdfPCell a3 = new PdfPCell(new Phrase(tablaDP.getModel().getValueAt(0, 2).toString()));
                    a3.setBackgroundColor(BaseColor.WHITE);
                    a3.setColspan(1);
                    table.addCell(a3);
                    PdfPCell a4 = new PdfPCell(new Phrase(tablaDP.getModel().getValueAt(0, 3).toString()));
                    a4.setBackgroundColor(BaseColor.WHITE);
                    a4.setColspan(1);
                    table.addCell(a4);
                    /*PdfPCell ciudad = new PdfPCell(new Phrase("Telefono"));
                ciudad.setBackgroundColor(new BaseColor(250,191,0));
                ciudad.setColspan(4);
                table.addCell(ciudad);
                PdfPCell dir = new PdfPCell(new Phrase("Direccion"));
                dir.setColspan(4);
                dir.setBackgroundColor(new BaseColor(250,191,0));
                table.addCell(dir);
                PdfPCell email = new PdfPCell(new Phrase("e-mail"));
                email.setColspan(4);
                email.setBackgroundColor(new BaseColor(250,191,0));
                table.addCell(email);
                PdfPCell a4 = new PdfPCell(new Phrase(tablaCC.getModel().getValueAt(0,3).toString()));
                a4.setBackgroundColor(BaseColor.WHITE);
                a4.setColspan(4);
                table.addCell(a4);
                PdfPCell a5 = new PdfPCell(new Phrase(tablaCC.getModel().getValueAt(0,4).toString()));
                a5.setBackgroundColor(BaseColor.WHITE);
                a5.setColspan(4);
                table.addCell(a5);
                PdfPCell a6 = new PdfPCell(new Phrase(tablaCC.getModel().getValueAt(0,6).toString()));
                a6.setBackgroundColor(BaseColor.WHITE);
                a6.setColspan(4);
                table.addCell(a6);*/
                    document.add(table);
                    document.add(Chunk.NEWLINE);
                    PdfPTable table2 = new PdfPTable(4);
                    PdfPCell gato = new PdfPCell(new Phrase("Descripcion"));
                    gato.setBackgroundColor(new BaseColor(250, 191, 0));
                    gato.setColspan(1);
                    table2.addCell(gato);
                    PdfPCell idpro = new PdfPCell(new Phrase("Cantidad"));
                    idpro.setColspan(1);
                    idpro.setBackgroundColor(new BaseColor(250, 191, 0));
                    table2.addCell(idpro);
                    PdfPCell cant = new PdfPCell(new Phrase("Valor Unitario"));
                    cant.setBackgroundColor(new BaseColor(250, 191, 0));
                    cant.setColspan(1);
                    table2.addCell(cant);
                    PdfPCell desc = new PdfPCell(new Phrase("Total"));
                    desc.setBackgroundColor(new BaseColor(250, 191, 0));
                    desc.setColspan(1);
                    table2.addCell(desc);
                    /*PdfPCell valoru = new PdfPCell(new Phrase("Valor Unitario"));
                valoru.setBackgroundColor(new BaseColor(250,191,0));
                valoru.setColspan(2);
                table2.addCell(valoru);
                PdfPCell valort = new PdfPCell(new Phrase("Valor Total"));
                valort.setBackgroundColor(new BaseColor(250,191,0));
                valort.setColspan(2);
                table2.addCell(valort);*/
                    for (int i = 0; i < tablaProveedores2.getRowCount(); i++) {
                        if (i % 2 == 0) {
                            PdfPCell v1 = new PdfPCell(new Phrase(tablaProveedores2.getModel().getValueAt(i, 0).toString()));
                            v1.setBackgroundColor(BaseColor.WHITE);
                            v1.setColspan(1);
                            table2.addCell(v1);
                            PdfPCell v2 = new PdfPCell(new Phrase(tablaProveedores2.getModel().getValueAt(i, 1).toString()));
                            v2.setBackgroundColor(BaseColor.WHITE);
                            v2.setColspan(1);
                            table2.addCell(v2);
                            PdfPCell v3 = new PdfPCell(new Phrase(tablaProveedores2.getModel().getValueAt(i, 2).toString()));
                            v3.setBackgroundColor(BaseColor.WHITE);
                            v3.setColspan(1);
                            table2.addCell(v3);
                            PdfPCell v4 = new PdfPCell(new Phrase(tablaProveedores2.getModel().getValueAt(i, 3).toString()));
                            v4.setBackgroundColor(BaseColor.WHITE);
                            v4.setColspan(1);
                            table2.addCell(v4);
                            /*PdfPCell v5 = new PdfPCell(new Phrase(tablaCotizacionP.getModel().getValueAt(i,4).toString()));
                        v5.setBackgroundColor(BaseColor.WHITE);
                        v5.setColspan(2);
                        table2.addCell(v5);
                        PdfPCell v6 = new PdfPCell(new Phrase(tablaCotizacionP.getModel().getValueAt(i,5).toString()));
                        v6.setBackgroundColor(BaseColor.WHITE);
                        v6.setColspan(2);
                        table2.addCell(v6);*/
                        } else {
                            PdfPCell v1 = new PdfPCell(new Phrase(tablaProveedores2.getModel().getValueAt(i, 0).toString()));
                            v1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            v1.setColspan(1);
                            table2.addCell(v1);
                            PdfPCell v2 = new PdfPCell(new Phrase(tablaProveedores2.getModel().getValueAt(i, 1).toString()));
                            v2.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            v2.setColspan(1);
                            table2.addCell(v2);
                            PdfPCell v3 = new PdfPCell(new Phrase(tablaProveedores2.getModel().getValueAt(i, 2).toString()));
                            v3.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            v3.setColspan(1);
                            table2.addCell(v3);
                            PdfPCell v4 = new PdfPCell(new Phrase(tablaProveedores2.getModel().getValueAt(i, 3).toString()));
                            v4.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            v4.setColspan(1);
                            table2.addCell(v4);
                            /*PdfPCell v5 = new PdfPCell(new Phrase(tablaCotizacionP.getModel().getValueAt(i,4).toString()));
                        v5.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v5.setColspan(2);
                        table2.addCell(v5);
                        PdfPCell v6 = new PdfPCell(new Phrase(tablaCotizacionP.getModel().getValueAt(i,5).toString()));
                        v6.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v6.setColspan(2);
                        table2.addCell(v6);*/
                        }
                    }
                    PdfPCell c1 = new PdfPCell(new Phrase(""));
                    c1.setBackgroundColor(BaseColor.WHITE);
                    c1.setColspan(2);
                    c1.setRowspan(3);
                    table2.addCell(c1);
                    PdfPCell c2 = new PdfPCell(new Phrase("Subtotal:"));
                    c2.setBackgroundColor(BaseColor.WHITE);
                    c2.setColspan(1);
                    table2.addCell(c2);
                    PdfPCell c3 = new PdfPCell(new Phrase(subtotalCompra.getText()));
                    c3.setBackgroundColor(BaseColor.WHITE);
                    c3.setColspan(1);
                    table2.addCell(c3);
                    PdfPCell c6 = new PdfPCell(new Phrase("I.V.A:"));
                    c6.setBackgroundColor(BaseColor.WHITE);
                    c6.setColspan(1);
                    table2.addCell(c6);
                    PdfPCell c7 = new PdfPCell(new Phrase(ivaCompra.getText()));
                    c7.setBackgroundColor(BaseColor.WHITE);
                    c7.setColspan(1);
                    table2.addCell(c7);
                    PdfPCell c8 = new PdfPCell(new Phrase("Total:"));
                    c8.setBackgroundColor(BaseColor.WHITE);
                    c8.setColspan(1);
                    table2.addCell(c8);
                    PdfPCell c9 = new PdfPCell(new Phrase(totalCompra.getText()));
                    c9.setBackgroundColor(BaseColor.WHITE);
                    c9.setColspan(1);
                    table2.addCell(c9);
                    document.add(table2);
                    document.add(Chunk.NEWLINE);
                    document.add(Chunk.NEWLINE);
                    Paragraph line = new Paragraph();
                    line.setIndentationLeft(160);
                    line.add(new Paragraph("_____________________________", titleArial));
                    document.add(line);
                    Paragraph firma = new Paragraph();
                    firma.setIndentationLeft(250);
                    firma.add(new Paragraph("Firma", titleArial));
                    document.add(firma);
                    document.close();
                }
            } catch (DocumentException | IOException e) {
                System.out.println(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hay campos vacíos. Favor de llenar los campos faltantes y vuelva a intentarlo.", "Campos vacíos", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void btnCamarasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCamarasActionPerformed
        try {
            Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start chrome http://socetobregon.wixsite.com/socet"});
        } catch (IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCamarasActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        if (tablaPD.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No hay empleados en la tabla", "Aviso", JOptionPane.ERROR_MESSAGE);
        } else {
//            try{
//                String f1 = String.valueOf(fn1.getCalendar().get(Calendar.DAY_OF_MONTH)+"/"+(fn1.getCalendar().get(Calendar.MONTH)+1)+"/"+fn1.getCalendar().get(Calendar.YEAR));
//                String f2 = String.valueOf(fn2.getCalendar().get(Calendar.DAY_OF_MONTH)+"/"+(fn2.getCalendar().get(Calendar.MONTH)+1)+"/"+fn2.getCalendar().get(Calendar.YEAR));
//            }
//            catch(NullPointerException e){
//                JOptionPane.showMessageDialog(null, "Fecha inválida", "Aviso", JOptionPane.ERROR_MESSAGE);
//            }
            try {
                String f1 = String.valueOf(fn1.getCalendar().get(Calendar.DAY_OF_MONTH) + "/" + (fn1.getCalendar().get(Calendar.MONTH) + 1) + "/" + fn1.getCalendar().get(Calendar.YEAR));
                String f2 = String.valueOf(fn2.getCalendar().get(Calendar.DAY_OF_MONTH) + "/" + (fn2.getCalendar().get(Calendar.MONTH) + 1) + "/" + fn2.getCalendar().get(Calendar.YEAR));
                if (tablaPD.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "No hay empleados en la tabla", "Aviso", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        String choosertitle = "";
                        JFileChooser chooser = new JFileChooser();
                        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        int option = chooser.showSaveDialog(null);
                        if (option == JFileChooser.APPROVE_OPTION) {
                            JOptionPane.showMessageDialog(null, "Se generó correctamente el PDF", "Exito", JOptionPane.INFORMATION_MESSAGE);
                            File file = chooser.getSelectedFile();
                            choosertitle = file.getAbsolutePath();
                            System.out.println("Ruta:" + choosertitle + ".pdf");
                            choosertitle = choosertitle + ".pdf";
                            Document document = new Document(PageSize.A4.rotate());
                            //Document document = new Document();
                            PdfWriter.getInstance(document, new FileOutputStream(choosertitle));
                            document.open();
                            //addMetaData(document);
                            document.addTitle("Formato Nomina SOCET");
                            document.addSubject("Digitalizada");
                            document.addKeywords("Formato Nomina,SOCET");
                            document.addAuthor("Fabian Gaxiola");
                            document.addCreator("Fabian Gaxiola");
                            //addTitlePage(document);
                            Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
                            Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
                            Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
                            Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
                            Font titleArial = new Font(Font.FontFamily.HELVETICA, 13);
                            Font bodyArial = new Font(Font.FontFamily.HELVETICA, 11);
                            Image imagen = Image.getInstance("src/Miscelaneo/1.png");
                            //imagen.setAlignment(Element.ALIGN_CENTER);
                            imagen.setAbsolutePosition(60f, 715f);
                            imagen.scaleAbsolute(90, 90);
                            document.add(imagen);
                            Paragraph par = new Paragraph();
                            //document.add(Chunk.NEWLINE);
                            par.setIndentationLeft(240);
                            par.add(new Paragraph("SOCET S.A. de C.V.", titleArial));
                            document.add(par);
                            //document.add(Chunk.NEWLINE);
                            Paragraph par1 = new Paragraph();
                            par1.setIndentationLeft(120);
                            par1.add(new Paragraph("San José Obrero #2325 Ote. Col. Hacienda San José Cd. Obregón, Sonora.", bodyArial));
                            document.add(par1);
                            Paragraph par2 = new Paragraph();
                            par2.setIndentationLeft(250);
                            par2.add(new Paragraph("Tel: (644)1.69.76.86", bodyArial));
                            document.add(par2);
                            Paragraph par3 = new Paragraph();
                            par3.setIndentationLeft(210);
                            par3.add(new Paragraph("e-Mail: socetsadecv@yahoo.com.mx", bodyArial));
                            document.add(par3);
                            document.add(Chunk.NEWLINE);
                            Paragraph par4 = new Paragraph();
                            par4.setIndentationLeft(230);
                            par4.add(new Paragraph("Formato de Nomina.", titleArial));
                            document.add(par4);
                            document.add(Chunk.NEWLINE);
                            PdfPTable tableemp = new PdfPTable(2);
                            tableemp.setWidthPercentage(50);
                            tableemp.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            PdfPCell id = new PdfPCell(new Phrase("Inicio Periodo"));
                            id.setColspan(1);
                            id.setRowspan(1);
                            id.setBackgroundColor(new BaseColor(250, 191, 0));
                            tableemp.addCell(id);
                            PdfPCell fecha = new PdfPCell(new Phrase("Final Periodo"));
                            fecha.setColspan(1);
                            fecha.setRowspan(1);
                            fecha.setBackgroundColor(new BaseColor(250, 191, 0));
                            tableemp.addCell(fecha);
                            PdfPCell a7 = new PdfPCell(new Phrase(f1));
                            a7.setBackgroundColor(BaseColor.WHITE);
                            a7.setColspan(1);
                            tableemp.addCell(a7);
                            PdfPCell a8 = new PdfPCell(new Phrase(f2));
                            a8.setBackgroundColor(BaseColor.WHITE);
                            a8.setColspan(1);
                            tableemp.addCell(a8);
                            document.add(tableemp);
                            document.add(Chunk.NEWLINE);
                            PdfPTable table = new PdfPTable(12);
                            PdfPCell idcli = new PdfPCell(new Phrase("No."));
                            idcli.setColspan(1);
                            idcli.setRowspan(1);
                            idcli.setBackgroundColor(new BaseColor(250, 191, 0));
                            table.addCell(idcli);
                            PdfPCell nombre = new PdfPCell(new Phrase("Nombre"));
                            nombre.setColspan(1);
                            nombre.setBackgroundColor(new BaseColor(250, 191, 0));
                            table.addCell(nombre);
                            PdfPCell empresa = new PdfPCell(new Phrase("Puesto"));
                            empresa.setColspan(1);
                            empresa.setRowspan(1);
                            empresa.setBackgroundColor(new BaseColor(250, 191, 0));
                            table.addCell(empresa);
                            PdfPCell empresa1 = new PdfPCell(new Phrase("Sueldo Base"));
                            empresa1.setColspan(1);
                            empresa1.setRowspan(1);
                            empresa1.setBackgroundColor(new BaseColor(250, 191, 0));
                            table.addCell(empresa1);
                            PdfPCell empresa2 = new PdfPCell(new Phrase("Horas Extras"));
                            empresa2.setColspan(1);
                            empresa2.setRowspan(1);
                            empresa2.setBackgroundColor(new BaseColor(250, 191, 0));
                            table.addCell(empresa2);
                            PdfPCell empresa3 = new PdfPCell(new Phrase("Bono"));
                            empresa3.setColspan(1);
                            empresa3.setRowspan(1);
                            empresa3.setBackgroundColor(new BaseColor(250, 191, 0));
                            table.addCell(empresa3);
                            PdfPCell empresa4 = new PdfPCell(new Phrase("Total Percepciones"));
                            empresa4.setColspan(1);
                            empresa4.setRowspan(1);
                            empresa4.setBackgroundColor(new BaseColor(250, 191, 0));
                            table.addCell(empresa4);
                            PdfPCell empresa5 = new PdfPCell(new Phrase("Faltas"));
                            empresa5.setColspan(1);
                            empresa5.setRowspan(1);
                            empresa5.setBackgroundColor(new BaseColor(250, 191, 0));
                            table.addCell(empresa5);
                            PdfPCell empresa6 = new PdfPCell(new Phrase("Prestamos"));
                            empresa6.setColspan(1);
                            empresa6.setRowspan(1);
                            empresa6.setBackgroundColor(new BaseColor(250, 191, 0));
                            table.addCell(empresa6);
                            PdfPCell empresa7 = new PdfPCell(new Phrase("Descuentos"));
                            empresa7.setColspan(1);
                            empresa7.setRowspan(1);
                            empresa7.setBackgroundColor(new BaseColor(250, 191, 0));
                            table.addCell(empresa7);
                            PdfPCell empresa8 = new PdfPCell(new Phrase("Total Deducciones"));
                            empresa8.setColspan(1);
                            empresa8.setRowspan(1);
                            empresa8.setBackgroundColor(new BaseColor(250, 191, 0));
                            table.addCell(empresa8);
                            PdfPCell empresa9 = new PdfPCell(new Phrase("Sueldo Neto"));
                            empresa9.setColspan(1);
                            empresa9.setRowspan(1);
                            empresa9.setBackgroundColor(new BaseColor(250, 191, 0));
                            table.addCell(empresa9);
                            for (int i = 0; i < tablaPD.getRowCount(); i++) {
                                if (i % 2 == 0) {
                                    PdfPCell v1 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 0).toString()));
                                    v1.setBackgroundColor(BaseColor.WHITE);
                                    v1.setColspan(1);
                                    table.addCell(v1);
                                    PdfPCell v2 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 1).toString()));
                                    v2.setBackgroundColor(BaseColor.WHITE);
                                    v2.setColspan(1);
                                    table.addCell(v2);
                                    PdfPCell v3 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 2).toString()));
                                    v3.setBackgroundColor(BaseColor.WHITE);
                                    v3.setColspan(1);
                                    table.addCell(v3);
                                    PdfPCell v4 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 3).toString()));
                                    v4.setBackgroundColor(BaseColor.WHITE);
                                    v4.setColspan(1);
                                    table.addCell(v4);
                                    PdfPCell v5 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 4).toString()));
                                    v5.setBackgroundColor(BaseColor.WHITE);
                                    v5.setColspan(1);
                                    table.addCell(v5);
                                    PdfPCell v6 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 5).toString()));
                                    v6.setBackgroundColor(BaseColor.WHITE);
                                    v6.setColspan(1);
                                    table.addCell(v6);
                                    PdfPCell v7 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 6).toString()));
                                    v7.setBackgroundColor(BaseColor.WHITE);
                                    v7.setColspan(1);
                                    table.addCell(v7);
                                    PdfPCell v8 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 7).toString()));
                                    v8.setBackgroundColor(BaseColor.WHITE);
                                    v8.setColspan(1);
                                    table.addCell(v8);
                                    PdfPCell v9 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 8).toString()));
                                    v9.setBackgroundColor(BaseColor.WHITE);
                                    v9.setColspan(1);
                                    table.addCell(v9);
                                    PdfPCell v10 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 9).toString()));
                                    v10.setBackgroundColor(BaseColor.WHITE);
                                    v10.setColspan(1);
                                    table.addCell(v10);
                                    PdfPCell v11 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 10).toString()));
                                    v11.setBackgroundColor(BaseColor.WHITE);
                                    v11.setColspan(1);
                                    table.addCell(v11);
                                    PdfPCell v12 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 11).toString()));
                                    v12.setBackgroundColor(BaseColor.WHITE);
                                    v12.setColspan(1);
                                    table.addCell(v12);
                                } else {
                                    PdfPCell v1 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 0).toString()));
                                    v1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                    v1.setColspan(1);
                                    table.addCell(v1);
                                    PdfPCell v2 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 1).toString()));
                                    v2.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                    v2.setColspan(1);
                                    table.addCell(v2);
                                    PdfPCell v3 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 2).toString()));
                                    v3.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                    v3.setColspan(1);
                                    table.addCell(v3);
                                    PdfPCell v4 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 3).toString()));
                                    v4.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                    v4.setColspan(1);
                                    table.addCell(v4);
                                    PdfPCell v5 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 4).toString()));
                                    v5.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                    v5.setColspan(1);
                                    table.addCell(v5);
                                    PdfPCell v6 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 5).toString()));
                                    v6.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                    v6.setColspan(1);
                                    table.addCell(v6);
                                    PdfPCell v7 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 6).toString()));
                                    v7.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                    v7.setColspan(1);
                                    table.addCell(v7);
                                    PdfPCell v8 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 7).toString()));
                                    v8.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                    v8.setColspan(1);
                                    table.addCell(v8);
                                    PdfPCell v9 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 8).toString()));
                                    v9.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                    v9.setColspan(1);
                                    table.addCell(v9);
                                    PdfPCell v10 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 9).toString()));
                                    v10.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                    v10.setColspan(1);
                                    table.addCell(v10);
                                    PdfPCell v11 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 10).toString()));
                                    v11.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                    v11.setColspan(1);
                                    table.addCell(v11);
                                    PdfPCell v12 = new PdfPCell(new Phrase(tablaPD.getModel().getValueAt(i, 11).toString()));
                                    v12.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                    v12.setColspan(1);
                                    table.addCell(v12);
                                }
                            }
                            document.add(table);
                            document.close();
                        }
                    } catch (DocumentException | IOException e) {
                        System.out.println(e);
                    }
                }
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Fecha inválida", "Aviso", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void btnCerrarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSActionPerformed
        int res = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (res == 0) {
            this.dispose();
            Principal.LogIn nv = new Principal.LogIn();
            nv.setVisible(true);
            nv.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_btnCerrarSActionPerformed

    private void btnEliminar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar4ActionPerformed
        EliminarMaq nv = new EliminarMaq(datosMaquinaria,this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_btnEliminar4ActionPerformed

    private void btnModificar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar4ActionPerformed
        ModificarMaq nv = new ModificarMaq(datosMaquinaria,this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_btnModificar4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        AgregarMaq nv = new AgregarMaq(this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void tablaMaquinariaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMaquinariaMouseClicked
        //eventos tabla Maquinarioa
        int row = tablaMaquinaria.getSelectedRow();
        for (int i = 0; i < 5; i++) {
            datosMaquinaria[i] = String.valueOf(tablaMaquinaria.getValueAt(row, i));
        }
        btnModificar4.setEnabled(true);
        btnEliminar4.setEnabled(true);
    }//GEN-LAST:event_tablaMaquinariaMouseClicked

    private void tablaMaquinariaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaMaquinariaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaMaquinariaFocusLost

    private void txtBuscarMaquinariaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMaquinariaKeyReleased
        DTMM = new DefaultTableModel(titulosMaquinaria, 0);
        tablaMaquinaria.setModel(DTMM);
        ResultSet res = mostrar(txtBuscarMaquinaria.getText(), 4);
        try {
            if (res.next()) {
                do {
                    Object fila[] = {String.valueOf(Integer.parseInt(res.getString("IDMaquinaria"))), res.getString("Marca"), res.getString("Modelo"), res.getString("Año"), res.getString("Placas")};
                    DTMM.addRow(fila);
                } while (res.next());
                tablaMaquinaria.setModel(DTMM);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscarMaquinariaKeyReleased

    private void txtBuscarMaquinariaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMaquinariaKeyPressed
        DTMM = new DefaultTableModel(titulosMaquinaria, 0);
        tablaMaquinaria.setModel(DTMM);
        ResultSet res = mostrar(txtBuscarMaquinaria.getText(), 4);
        try {
            if (res.next()) {
                do {
                    Object fila[] = {String.valueOf(Integer.parseInt(res.getString("IDMaquinaria"))), res.getString("Marca"), res.getString("Modelo"), res.getString("Año"), res.getString("Placas")};
                    DTMM.addRow(fila);
                } while (res.next());
                tablaMaquinaria.setModel(DTMM);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscarMaquinariaKeyPressed

    private void txtBuscarMaquinariaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarMaquinariaFocusLost
        if (txtBuscarMaquinaria.getText().isEmpty()) {
            txtBuscarMaquinaria.setText("Buscar por serie, marca, modelo o placas...");
        }
    }//GEN-LAST:event_txtBuscarMaquinariaFocusLost

    private void txtBuscarMaquinariaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarMaquinariaFocusGained
        if (txtBuscarMaquinaria.getText().equals("Buscar por serie, marca, modelo o placas...")) {
            txtBuscarMaquinaria.setText("");
        }
    }//GEN-LAST:event_txtBuscarMaquinariaFocusGained

    private void tabbedMaquinariaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedMaquinariaStateChanged
        switch (tabbedMaquinaria.getSelectedIndex()) {
            case 0:
                infoPanel.setVisible(true);
                rendimientoPanel.setVisible(false);
                asignarMaquinaria.setVisible(false);
                break;
            case 1:
                rendimientoPanel.setVisible(true);
                infoPanel.setVisible(false);
                asignarMaquinaria.setVisible(false);
                break;
            case 2:
                rendimientoPanel.setVisible(false);
                infoPanel.setVisible(false);
                asignarMaquinaria.setVisible(true);
                break;
        }
    }//GEN-LAST:event_tabbedMaquinariaStateChanged

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        Obras.SeleccionarEncargado nv = new Obras.SeleccionarEncargado(6,this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_jButton24ActionPerformed

    private void txtVehiculoR1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVehiculoR1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVehiculoR1KeyReleased

    private void txtVehiculoR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVehiculoR1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVehiculoR1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        SeleccionarVehiculo nv = new SeleccionarVehiculo(0,this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void txtBuscar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar1KeyReleased
        DTMCONTROL = new DefaultTableModel(titulosControl, 0);
        tablaRendimientoC.setModel(DTMCONTROL);
        try {
            ResultSet res = mostrar(txtBuscar1.getText(), 6);
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("Fecha"), res.getString("FolioOrden"), res.getString("Trabajador"), res.getString("Vehiculo"), res.getString("Odometro"), res.getString("Kilometraje"), res.getString("Combustible"), res.getString("Litros"), res.getString("Moneda"), res.getString("kmLitro"), res.getString("kmMoneda"), res.getString("FolioPago")};
                    DTMCONTROL.addRow(fila);
                } while (res.next());
                tablaRendimientoC.setModel(DTMCONTROL);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscar1KeyReleased

    private void txtBuscar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar1KeyPressed
        DTMCONTROL = new DefaultTableModel(titulosControl, 0);
        tablaRendimientoC.setModel(DTMCONTROL);
        try {
            ResultSet res = mostrar(txtBuscar1.getText(), 6);
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("Fecha"), res.getString("FolioOrden"), res.getString("Trabajador"), res.getString("Vehiculo"), res.getString("Odometro"), res.getString("Kilometraje"), res.getString("Combustible"), res.getString("Litros"), res.getString("Moneda"), res.getString("kmLitro"), res.getString("kmMoneda"), res.getString("FolioPago")};
                    DTMCONTROL.addRow(fila);
                } while (res.next());
                tablaRendimientoC.setModel(DTMCONTROL);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscar1KeyPressed

    private void txtBuscar1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscar1FocusLost
        if (txtBuscar1.getText().isEmpty()) {
            txtBuscar1.setText("Buscar por folio orden o vehículo...");
        }
    }//GEN-LAST:event_txtBuscar1FocusLost

    private void txtBuscar1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscar1FocusGained
        if (txtBuscar1.getText().equals("Buscar por folio orden o vehículo...")) {
            txtBuscar1.setText("");
        }
    }//GEN-LAST:event_txtBuscar1FocusGained

    private void txtFolioOrdenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFolioOrdenKeyReleased
        if (txtFolioOrden.getText().matches("[0-9]+")) {
            check14.setVisible(true);
        } else if (txtFolioOrden.getText().equals("")) {
            check14.setVisible(false);
        } else {
            check14.setVisible(false);
        }
    }//GEN-LAST:event_txtFolioOrdenKeyReleased

    private void txtFolioOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFolioOrdenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFolioOrdenActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (!(txtVehiculoR.getText().isEmpty() || txtVehiculoR1.getText().isEmpty() || txtOdometroR.getText().isEmpty() || txtKMR.getText().isEmpty() || txtCombustibleR.getText().isEmpty() || txtLitrosR.getText().isEmpty() || txtMonedaR.getText().isEmpty() || txtKMLR.getText().isEmpty() || txtKMMR.getText().isEmpty() || txtFolioPago.getText().isEmpty() || txtFolioOrden.getText().isEmpty())) {
            String fecha = String.valueOf(fechaInicio.getCalendar().get(Calendar.DAY_OF_MONTH) + "/" + (fechaInicio.getCalendar().get(Calendar.MONTH) + 1) + "/" + fechaInicio.getCalendar().get(Calendar.YEAR));
            DefaultTableModel model = (DefaultTableModel) tablaRendimientoC.getModel();
            tablaRendimientoC.setModel(model);
            Object fila[] = {fecha, empleadoD.getMatricula(), vehiculoD.getSerie(), txtOdometroR.getText(), txtKMR.getText(), txtCombustibleR.getText(), txtLitrosR.getText(), txtMonedaR.getText(), txtKMLR.getText(), txtKMMR.getText(), txtFolioPago.getText(), txtFolioOrden.getText()};
            model.addRow(fila);
            try {
                Statement ST = c.createStatement();
                String sql = "Insert into CONTROL_DE_COMBUSTIBLE(Fecha, FolioOrden, Vehiculo, Trabajador, Odometro, Kilometraje, Combustible, Litros, Moneda, kmLitro, kmMoneda, FolioPago) values('" + fecha
                        + "'," + txtFolioOrden.getText() + "," + vehiculoD.getSerie()
                        + "," + empleadoD.getMatricula() + "," + txtOdometroR.getText()
                        + "," + txtKMR.getText() + ",'" + txtCombustibleR.getText()
                        + "'," + txtLitrosR.getText() + "," + txtMonedaR.getText()
                        + "," + txtKMLR.getText() + "," + txtKMMR.getText()
                        + "," + txtFolioPago.getText() + ")";
                ST.executeUpdate(sql);
            } catch (SQLException e) {
                System.out.println("boom" + e);
            }
            txtVehiculoR.setText("");
            txtOdometroR.setText("");
            txtKMR.setText("");
            txtCombustibleR.setText("");
            txtLitrosR.setText("");
            txtMonedaR.setText("");
            txtKMLR.setText("");
            txtKMMR.setText("");
            txtFolioPago.setText("");
            txtFolioOrden.setText("");
            txtVehiculoR1.setText("");
            JOptionPane.showMessageDialog(null, "Se ha añadido correctamente el registro", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            check5.setVisible(false);
            check6.setVisible(false);
            check7.setVisible(false);
            check8.setVisible(false);
            check9.setVisible(false);
            check10.setVisible(false);
            check11.setVisible(false);
            check12.setVisible(false);
            check13.setVisible(false);
            check14.setVisible(false);
            check15.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Hay campos vacíos, revise y vuelva a intentarlo", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtFolioPagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFolioPagoKeyReleased
        if (txtFolioPago.getText().matches("[0-9]+")) {
            check13.setVisible(true);
        } else if (txtFolioPago.getText().equals("")) {
            check13.setVisible(false);
        } else {
            check13.setVisible(false);
        }
    }//GEN-LAST:event_txtFolioPagoKeyReleased

    private void txtFolioPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFolioPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFolioPagoActionPerformed

    private void txtKMMRKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKMMRKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKMMRKeyReleased

    private void txtKMMRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKMMRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKMMRActionPerformed

    private void txtKMLRKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKMLRKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKMLRKeyReleased

    private void txtKMLRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKMLRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKMLRActionPerformed

    private void txtMonedaRKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMonedaRKeyReleased
        if (txtMonedaR.getText().isEmpty()) {
            txtKMMR.setText("");
            check10.setVisible(false);
            check12.setVisible(false);
        } else if (!(txtKMR.getText().isEmpty() || txtMonedaR.getText().isEmpty())) {
            try {
                txtKMMR.setText(String.valueOf(Double.valueOf(txtKMR.getText()) / Double.valueOf(txtMonedaR.getText())));
                check10.setVisible(true);
            } catch (NumberFormatException e) {
                check10.setVisible(false);
                check12.setVisible(false);
            }
        }
    }//GEN-LAST:event_txtMonedaRKeyReleased

    private void txtMonedaRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMonedaRKeyPressed

    }//GEN-LAST:event_txtMonedaRKeyPressed

    private void txtMonedaRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMonedaRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMonedaRActionPerformed

    private void txtLitrosRKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLitrosRKeyReleased
        if (txtLitrosR.getText().isEmpty()) {
            txtKMLR.setText("");
            check9.setVisible(false);
            check11.setVisible(false);
        }
        if (!(txtKMR.getText().isEmpty() || txtLitrosR.getText().isEmpty())) {
            try {
                txtKMLR.setText(String.valueOf(Double.valueOf(txtKMR.getText()) / Double.valueOf(txtLitrosR.getText())));
                check9.setVisible(true);
            } catch (NumberFormatException e) {
                check9.setVisible(false);
                check11.setVisible(false);
            }
        }
    }//GEN-LAST:event_txtLitrosRKeyReleased

    private void txtLitrosRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLitrosRKeyPressed

    }//GEN-LAST:event_txtLitrosRKeyPressed

    private void txtLitrosRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLitrosRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLitrosRActionPerformed

    private void txtCombustibleRKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCombustibleRKeyReleased
        if (txtCombustibleR.getText().matches("[a-zA-Z ]+")) {
            check8.setVisible(true);
        } else if (txtCombustibleR.getText().equals("")) {
            check8.setVisible(false);
        } else {
            check8.setVisible(false);
        }
    }//GEN-LAST:event_txtCombustibleRKeyReleased

    private void txtCombustibleRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCombustibleRKeyPressed

    }//GEN-LAST:event_txtCombustibleRKeyPressed

    private void txtCombustibleRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCombustibleRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCombustibleRActionPerformed

    private void txtVehiculoRKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVehiculoRKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVehiculoRKeyReleased

    private void txtVehiculoRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVehiculoRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVehiculoRActionPerformed

    private void txtOdometroRKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOdometroRKeyReleased
        double value;
        try {
            value = Double.parseDouble(txtOdometroR.getText());
            check6.setVisible(true);
        } catch (NumberFormatException e) {
            check6.setVisible(false);
        }
    }//GEN-LAST:event_txtOdometroRKeyReleased

    private void txtOdometroRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOdometroRKeyPressed

    }//GEN-LAST:event_txtOdometroRKeyPressed

    private void txtOdometroRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOdometroRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOdometroRActionPerformed

    private void txtKMRKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKMRKeyReleased
        if (txtKMR.getText().isEmpty()) {
            txtKMMR.setText("");
            txtKMLR.setText("");
            check11.setVisible(false);
            check12.setVisible(false);
            check7.setVisible(false);
        }
        if (!(txtKMR.getText().isEmpty() || txtLitrosR.getText().isEmpty())) {
            try {
                txtKMLR.setText(String.valueOf(Double.valueOf(txtKMR.getText()) / Double.valueOf(txtLitrosR.getText())));
                check11.setVisible(true);
                check7.setVisible(true);
            } catch (NumberFormatException e) {
                check11.setVisible(false);
                check7.setVisible(false);
                txtKMMR.setText("");
                txtKMLR.setText("");
            }
        }
        if (!(txtKMR.getText().isEmpty() || txtMonedaR.getText().isEmpty())) {
            try {
                txtKMMR.setText(String.valueOf(Double.valueOf(txtKMR.getText()) / Double.valueOf(txtMonedaR.getText())));
                check12.setVisible(true);
                check7.setVisible(true);
            } catch (NumberFormatException e) {
                check12.setVisible(false);
                check7.setVisible(false);
                txtKMMR.setText("");
                txtKMLR.setText("");
            }
        }

        double value;
        try {
            value = Double.parseDouble(txtOdometroR.getText());
            check7.setVisible(true);
        } catch (NumberFormatException e) {
            check7.setVisible(false);
        }
    }//GEN-LAST:event_txtKMRKeyReleased

    private void txtKMRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKMRKeyPressed

    }//GEN-LAST:event_txtKMRKeyPressed

    private void txtKMRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKMRActionPerformed

    }//GEN-LAST:event_txtKMRActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            String choosertitle = "";
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int option = chooser.showSaveDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                choosertitle = file.getAbsolutePath();
                System.out.println("Ruta:" + choosertitle + ".pdf");
                choosertitle = choosertitle + ".pdf";
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(choosertitle));
                document.open();
                //addMetaData(document);
                document.addTitle("Rendimiento SOCET");
                document.addSubject("Digitalizada");
                document.addKeywords("Rendimiento, Combustible, SOCET");
                document.addAuthor("Fabian Gaxiola");
                document.addCreator("Fabian Gaxiola");
                //addTitlePage(document);
                Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
                Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
                Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
                Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
                Font titleArial = new Font(Font.FontFamily.HELVETICA, 13);
                Font bodyArial = new Font(Font.FontFamily.HELVETICA, 11);
                com.itextpdf.text.Image imagen = com.itextpdf.text.Image.getInstance("src/Miscelaneo/1.png");
                //imagen.setAlignment(Element.ALIGN_CENTER);
                imagen.setAbsolutePosition(60f, 715f);
                imagen.scaleAbsolute(90, 90);
                document.add(imagen);
                Paragraph par = new Paragraph();
                //document.add(Chunk.NEWLINE);
                par.setIndentationLeft(240);
                par.add(new Paragraph("SOCET S.A. de C.V.", titleArial));
                document.add(par);
                //document.add(Chunk.NEWLINE);
                Paragraph par1 = new Paragraph();
                par1.setIndentationLeft(120);
                par1.add(new Paragraph("San José Obrero #2325 Ote. Col. Hacienda San José Cd. Obregón, Sonora.", bodyArial));
                document.add(par1);
                Paragraph par2 = new Paragraph();
                par2.setIndentationLeft(250);
                par2.add(new Paragraph("Tel: (644)1.69.76.86", bodyArial));
                document.add(par2);
                Paragraph par3 = new Paragraph();
                par3.setIndentationLeft(230);
                par3.add(new Paragraph("e-Mail: socetsadecv@yahoo.com.mx", bodyArial));
                document.add(par3);
                document.add(Chunk.NEWLINE);
                Paragraph par4 = new Paragraph();
                par4.setIndentationLeft(200);
                par4.add(new Paragraph("Rendimiento de Combustible.", titleArial));
                document.add(par4);
                document.add(Chunk.NEWLINE);
                PdfPTable table2 = new PdfPTable(12);
                table2.setWidthPercentage(100);
                PdfPCell fecha = new PdfPCell(new Phrase("Fecha"));
                fecha.setBackgroundColor(new BaseColor(250, 191, 0));
                fecha.setColspan(1);
                table2.addCell(fecha);
                PdfPCell vehiculo = new PdfPCell(new Phrase("Folio Orden"));
                vehiculo.setColspan(1);
                vehiculo.setBackgroundColor(new BaseColor(250, 191, 0));
                table2.addCell(vehiculo);
                PdfPCell odometro = new PdfPCell(new Phrase("Empleado"));
                odometro.setBackgroundColor(new BaseColor(250, 191, 0));
                odometro.setColspan(1);
                table2.addCell(odometro);
                PdfPCell kilometraje = new PdfPCell(new Phrase("Vehiculo"));
                kilometraje.setBackgroundColor(new BaseColor(250, 191, 0));
                kilometraje.setColspan(1);
                table2.addCell(kilometraje);
                PdfPCell combustible = new PdfPCell(new Phrase("Odómetro"));
                combustible.setBackgroundColor(new BaseColor(250, 191, 0));
                combustible.setColspan(1);
                table2.addCell(combustible);
                PdfPCell litros = new PdfPCell(new Phrase("Kilometraje"));
                litros.setBackgroundColor(new BaseColor(250, 191, 0));
                litros.setColspan(1);
                table2.addCell(litros);
                PdfPCell moneda = new PdfPCell(new Phrase("Combustible"));
                moneda.setBackgroundColor(new BaseColor(250, 191, 0));
                moneda.setColspan(1);
                table2.addCell(moneda);
                PdfPCell kmlt = new PdfPCell(new Phrase("Litros"));
                kmlt.setBackgroundColor(new BaseColor(250, 191, 0));
                kmlt.setColspan(1);
                table2.addCell(kmlt);
                PdfPCell kmmn = new PdfPCell(new Phrase("Moneda"));
                kmmn.setBackgroundColor(new BaseColor(250, 191, 0));
                kmmn.setColspan(1);
                table2.addCell(kmmn);
                PdfPCell foliopago = new PdfPCell(new Phrase("Km/Litro"));
                foliopago.setBackgroundColor(new BaseColor(250, 191, 0));
                foliopago.setColspan(1);
                table2.addCell(foliopago);
                PdfPCell folioorden = new PdfPCell(new Phrase("Km/Moneda"));
                folioorden.setBackgroundColor(new BaseColor(250, 191, 0));
                folioorden.setColspan(1);
                table2.addCell(folioorden);
                PdfPCell lul = new PdfPCell(new Phrase("Folio Pago"));
                lul.setBackgroundColor(new BaseColor(250, 191, 0));
                lul.setColspan(1);
                table2.addCell(lul);
                float litrototal = 0;
                float monedatotal = 0;
                float kmlit = 0;
                float kmmon = 0;
                for (int i = 0; i < tablaRendimientoC.getRowCount(); i++) {
                    if (i % 2 == 0) {
                        PdfPCell v1 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 0).toString()));
                        v1.setBackgroundColor(BaseColor.WHITE);
                        v1.setColspan(1);
                        table2.addCell(v1);
                        PdfPCell v2 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 1).toString()));
                        v2.setBackgroundColor(BaseColor.WHITE);
                        v2.setColspan(1);
                        table2.addCell(v2);
                        PdfPCell v3 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 2).toString()));
                        v3.setBackgroundColor(BaseColor.WHITE);
                        v3.setColspan(1);
                        table2.addCell(v3);
                        PdfPCell v4 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 3).toString()));
                        v4.setBackgroundColor(BaseColor.WHITE);
                        v4.setColspan(1);
                        table2.addCell(v4);
                        PdfPCell v5 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 4).toString()));
                        v5.setBackgroundColor(BaseColor.WHITE);
                        v5.setColspan(1);
                        table2.addCell(v5);
                        PdfPCell v6 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 5).toString()));
                        v6.setBackgroundColor(BaseColor.WHITE);
                        v6.setColspan(1);
                        table2.addCell(v6);
                        PdfPCell v7 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 6).toString()));
                        v7.setBackgroundColor(BaseColor.WHITE);
                        v7.setColspan(1);
                        table2.addCell(v7);
                        PdfPCell v8 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 7).toString()));
                        litrototal += Float.parseFloat(tablaRendimientoC.getModel().getValueAt(i, 7).toString());
                        v8.setBackgroundColor(BaseColor.WHITE);
                        v8.setColspan(1);
                        table2.addCell(v8);
                        PdfPCell v9 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 8).toString()));
                        monedatotal += Float.parseFloat(tablaRendimientoC.getModel().getValueAt(i, 8).toString());
                        v9.setBackgroundColor(BaseColor.WHITE);
                        v9.setColspan(1);
                        table2.addCell(v9);
                        PdfPCell v10 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 9).toString()));
                        kmlit += Float.parseFloat(tablaRendimientoC.getModel().getValueAt(i, 9).toString());
                        v10.setBackgroundColor(BaseColor.WHITE);
                        v10.setColspan(1);
                        table2.addCell(v10);
                        PdfPCell v11 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 10).toString()));
                        kmmon += Float.parseFloat(tablaRendimientoC.getModel().getValueAt(i, 10).toString());
                        v11.setBackgroundColor(BaseColor.WHITE);
                        v11.setColspan(1);
                        table2.addCell(v11);
                        PdfPCell v12 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 11).toString()));
                        v12.setBackgroundColor(BaseColor.WHITE);
                        v12.setColspan(1);
                        table2.addCell(v12);
                    } else {
                        PdfPCell v1 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 0).toString()));
                        v1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v1.setColspan(1);
                        table2.addCell(v1);
                        PdfPCell v2 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 1).toString()));
                        v2.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v2.setColspan(1);
                        table2.addCell(v2);
                        PdfPCell v3 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 2).toString()));
                        v3.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v3.setColspan(1);
                        table2.addCell(v3);
                        PdfPCell v4 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 3).toString()));
                        v4.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v4.setColspan(1);
                        table2.addCell(v4);
                        PdfPCell v5 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 4).toString()));
                        v5.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v5.setColspan(1);
                        table2.addCell(v5);
                        PdfPCell v6 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 5).toString()));
                        v6.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v6.setColspan(1);
                        table2.addCell(v6);
                        PdfPCell v7 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 6).toString()));
                        v7.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v7.setColspan(1);
                        table2.addCell(v7);
                        PdfPCell v8 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 7).toString()));
                        litrototal += Float.parseFloat(tablaRendimientoC.getModel().getValueAt(i, 7).toString());
                        v8.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v8.setColspan(1);
                        table2.addCell(v8);
                        PdfPCell v9 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 8).toString()));
                        monedatotal += Float.parseFloat(tablaRendimientoC.getModel().getValueAt(i, 8).toString());
                        v9.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v9.setColspan(1);
                        table2.addCell(v9);
                        PdfPCell v10 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 9).toString()));
                        kmlit += Float.parseFloat(tablaRendimientoC.getModel().getValueAt(i, 9).toString());
                        v10.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v10.setColspan(1);
                        table2.addCell(v10);
                        PdfPCell v11 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 10).toString()));
                        kmmon += Float.parseFloat(tablaRendimientoC.getModel().getValueAt(i, 10).toString());
                        v11.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v11.setColspan(1);
                        table2.addCell(v11);
                        PdfPCell v12 = new PdfPCell(new Phrase(tablaRendimientoC.getModel().getValueAt(i, 11).toString()));
                        v12.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v12.setColspan(1);
                        table2.addCell(v12);
                    }
                }
                System.out.println("Litros: " + litrototal);
                System.out.println("Moneda: " + monedatotal);
                System.out.println("KmLitro: " + kmlit);
                System.out.println("KmMoneda: " + kmmon);
                document.add(table2);
                document.add(Chunk.NEWLINE);
                PdfPTable table3 = new PdfPTable(4);
                table3.setWidthPercentage(100);
                PdfPCell fecha1 = new PdfPCell(new Phrase("Litros"));
                fecha1.setBackgroundColor(new BaseColor(250, 191, 0));
                fecha1.setColspan(1);
                table3.addCell(fecha1);
                PdfPCell vehiculo1 = new PdfPCell(new Phrase("Gasto"));
                vehiculo1.setColspan(1);
                vehiculo1.setBackgroundColor(new BaseColor(250, 191, 0));
                table3.addCell(vehiculo1);
                PdfPCell odometro1 = new PdfPCell(new Phrase("Km/Litro"));
                odometro1.setBackgroundColor(new BaseColor(250, 191, 0));
                odometro1.setColspan(1);
                table3.addCell(odometro1);
                PdfPCell kilometraje1 = new PdfPCell(new Phrase("Km/Moneda"));
                kilometraje1.setBackgroundColor(new BaseColor(250, 191, 0));
                kilometraje1.setColspan(1);
                table3.addCell(kilometraje1);
                PdfPCell v13 = new PdfPCell(new Phrase("Total: " + litrototal + ""));
                v13.setBackgroundColor(BaseColor.LIGHT_GRAY);
                v13.setColspan(1);
                table3.addCell(v13);
                PdfPCell v14 = new PdfPCell(new Phrase("Total: " + monedatotal + ""));
                v14.setBackgroundColor(BaseColor.LIGHT_GRAY);
                v14.setColspan(1);
                table3.addCell(v14);
                PdfPCell v15 = new PdfPCell(new Phrase("Promedio:" + (kmlit / tablaRendimientoC.getRowCount()) + ""));
                v15.setBackgroundColor(BaseColor.LIGHT_GRAY);
                v15.setColspan(1);
                table3.addCell(v15);
                PdfPCell v16 = new PdfPCell(new Phrase("Promedio:" + (kmmon / tablaRendimientoC.getRowCount()) + ""));
                v16.setBackgroundColor(BaseColor.LIGHT_GRAY);
                v16.setColspan(1);
                table3.addCell(v16);
                document.add(table3);
                document.close();
                JOptionPane.showMessageDialog(null, "Se generó correctamente el reporte de rendimiento", "Exito", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (DocumentException | IOException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tablaObras3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaObras3FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaObras3FocusLost

    private void tablaObras3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaObras3MouseClicked
        rowAsignaE = tablaObras3.getSelectedRow();
        int row = tablaObras3.getSelectedRow();
        //Tabla Seguimientos
        String titulosObraS[] = {"Matricula", "Empleado"};
        DTMC = new DefaultTableModel(titulosObraS, 0);
        tablaObras4.setModel(DTMC);
        try {
            Statement smt = c.createStatement();
            String sql = "select * from TRABAJADOR_OBRA where IDObra='" + tablaObras3.getValueAt(row, 0) + "'";
            ResultSet res = smt.executeQuery(sql);
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("IDTrabajador"), res.getString("NombreCompleto")};
                    DTMC.addRow(fila);
                } while (res.next());
                tablaObras4.setModel(DTMC);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_tablaObras3MouseClicked

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        SeleccionarEncargado nv = new SeleccionarEncargado(3,this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void txtBuscarObras2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarObras2FocusGained
        if (txtBuscarObras1.getText().equals("Buscar por cliente, nombre, o matricula de obra...")) {
            txtBuscarObras1.setText("");
        }
    }//GEN-LAST:event_txtBuscarObras2FocusGained

    private void txtBuscarObras2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarObras2FocusLost
        if (txtBuscarObras1.getText().isEmpty()) {
            txtBuscarObras1.setText("Buscar por cliente, nombre, o matricula de obra...");
        }
    }//GEN-LAST:event_txtBuscarObras2FocusLost

    private void txtBuscarObras2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarObras2KeyPressed
        String titulosObraS[] = {"Matricula", "Nombre", "Cliente"};
        DTMO = new DefaultTableModel(titulosObraS, 0);
        tablaObras1.setModel(DTMO);
        ResultSet res = mostrar(txtBuscarObras1.getText(), 9);
        try {
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("IDObra"), res.getString("NombreObra"), res.getString("Cliente")};
                    DTMO.addRow(fila);
                } while (res.next());
                tablaObras1.setModel(DTMO);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscarObras2KeyPressed

    private void txtBuscarObras2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarObras2KeyReleased
        String titulosObraS[] = {"Matricula", "Nombre", "Cliente"};
        DTMO = new DefaultTableModel(titulosObraS, 0);
        tablaObras1.setModel(DTMO);
        ResultSet res = mostrar(txtBuscarObras1.getText(), 9);
        try {
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("IDObra"), res.getString("NombreObra"), res.getString("Cliente")};
                    DTMO.addRow(fila);
                } while (res.next());
                tablaObras1.setModel(DTMO);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscarObras2KeyReleased

    private void tablaObras4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaObras4FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaObras4FocusLost

    private void tablaObras4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaObras4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaObras4MouseClicked

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        Proveedores.SeleccionarObra nv = new Proveedores.SeleccionarObra(3,this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_jButton26ActionPerformed

    private void tablaCotizacionP1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaCotizacionP1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaCotizacionP1FocusGained

    private void tablaCotizacionP1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCotizacionP1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaCotizacionP1MouseClicked

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        try {
            for (int i = 0; i < tablaCotizacionP1.getRowCount(); i++) {
                Statement ST = c.createStatement();
                String sql = "Insert into PRODUCTOS_OBRA values('" + tablaCC2.getValueAt(0, 0)
                        + "','" + tablaDescripcionP1.getValueAt(0, 0) + "','" + tablaDescripcionP2.getValueAt(0, 0)
                        + "','" + tablaCotizacionP1.getValueAt(i, 1) + "','" + tablaCotizacionP1.getValueAt(i, 3)
                        + "','" + txtCotizacion1.getText() + "')";
                ST.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        ResultSet RS;
        String exis;
        for (int j = 0; j < tablaCotizacionP1.getRowCount(); j++) {
            try {
                Statement ST = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String sql = "select existencia from PRODUCTOS where IDProducto='" + tablaCotizacionP1.getValueAt(j, 1) + "'";
                RS = ST.executeQuery(sql);
                if (RS.next()) {
                    exis = RS.getString("existencia");
                    try {
                        Statement ST2 = c.createStatement();
                        String sql2 = "Update PRODUCTOS set existencia='" + String.valueOf(Integer.valueOf(exis) - Integer.valueOf(String.valueOf(tablaCotizacionP1.getValueAt(j, 2))))
                                + "' where IDProducto ='" + tablaCotizacionP1.getValueAt(j, 1) + "'";
                        ST2.executeUpdate(sql2);
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        String titulosObraS[] = {"Matricula", "Nombre"};
        DTME = new DefaultTableModel(titulosObraS, 0);
        tablaDescripcionP1.setModel(DTME);
        String titulosObraS2[] = {"Matricula", "Nombre", "Cliente", "Encargado"};
        DTMC = new DefaultTableModel(titulosObraS2, 0);
        tablaCC2.setModel(DTMC);
        String titulosInventario2[] = {"Código", "Descripción", "Existencia"};
        DTMC = new DefaultTableModel(titulosInventario2, 0);
        Home.tablaDescripcionP2.setModel(DTMC);
        String titulosInventario3[] = {"#", "Codigo", "Cantidad", "Descripcion"};
        DTMC = new DefaultTableModel(titulosInventario3, 0);
        tablaCotizacionP1.setModel(DTMC);
        JOptionPane.showMessageDialog(null, "Se han asignado correctamente los productos a la obra", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton27ActionPerformed

    private void btnEliminarP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarP1ActionPerformed
        if (tablaCotizacionP1.getRowCount() != 0) {
            int row = tablaCotizacionP1.getSelectedRow();
            btnEliminarP1.setEnabled(false);
            ((DefaultTableModel) tablaCotizacionP1.getModel()).removeRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No hay productos en la tabla", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarP1ActionPerformed

    private void agregarP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarP1ActionPerformed
        if (Integer.valueOf(String.valueOf(tablaDescripcionP2.getValueAt(0, 2))) < Integer.valueOf(String.valueOf(spCantidad1.getValue()))) {
            JOptionPane.showMessageDialog(null, "No hay suficiente existencia en el inventario para la cantidad seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            DefaultTableModel model = (DefaultTableModel) tablaCotizacionP1.getModel();
            tablaCotizacionP1.setModel(model);
            Object fila[] = {conk, Integer.valueOf(String.valueOf(tablaDescripcionP2.getValueAt(0, 0))), Integer.valueOf(String.valueOf(spCantidad1.getValue())), String.valueOf(tablaDescripcionP2.getValueAt(0, 1))};
            model.addRow(fila);
            conk++;
            spCantidad1.setValue(1);
        }
    }//GEN-LAST:event_agregarP1ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        Cotizacion.SeleccionarProducto nv = new Cotizacion.SeleccionarProducto(2,this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jTabbedPane3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane3StateChanged
        switch (jTabbedPane3.getSelectedIndex()) {
            case 0:
                infoInventario.setVisible(true);
                panelInveObra.setVisible(false);
                break;
            case 1:
                panelInveObra.setVisible(true);
                infoInventario.setVisible(false);
                break;
        }
    }//GEN-LAST:event_jTabbedPane3StateChanged

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        Obras.SeleccionarEncargado nv = new Obras.SeleccionarEncargado(4,this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_jButton29ActionPerformed

    private void txtCotizacion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCotizacion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCotizacion1ActionPerformed

    private void tabbedObras1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedObras1StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabbedObras1StateChanged

    private void tablaObras5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaObras5FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaObras5FocusLost

    private void tablaObras5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaObras5MouseClicked
        rowAsignaE = tablaObras5.getSelectedRow();
        int row = tablaObras5.getSelectedRow();
        //Tabla Seguimientos
        String titulosObraS[] = {"Matricula Obra", "Matricula trabajador", "Codigo Producto", "Piezas", "Descripcion", "Fecha"};
        DTMC = new DefaultTableModel(titulosObraS, 0);
        tablaObras6.setModel(DTMC);
        try {
            Statement smt = c.createStatement();
            String sql = "select * from PRODUCTOS_OBRA where IDObra='" + tablaObras5.getValueAt(row, 0) + "'";
            ResultSet res = smt.executeQuery(sql);
            if (res.next()) {

                do {
                    ResultSet res2 = mostrar2(res.getString("IDTrabajador"));
                    if (res2.next()) {
                        Object fila[] = {res.getString("IDObra"), res2.getString("Nombre") + " " + res2.getString("Apellido"), res.getString("IDProducto"), res.getString("Cantidad"), res.getString("Descripcion"), res.getString("Fecha")};
                        DTMC.addRow(fila);
                    }
                } while (res.next());

                tablaObras6.setModel(DTMC);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_tablaObras5MouseClicked
    public ResultSet mostrar2(String str) {
        ResultSet RS = null;
        try {
            Statement ST = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "select Nombre,Apellido from TRABAJADORES where IDTrabajador='" + str + "'";
            RS = ST.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return RS;
    }
    private void txtBuscarObras3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarObras3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarObras3FocusGained

    private void txtBuscarObras3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarObras3FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarObras3FocusLost

    private void txtBuscarObras3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarObras3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarObras3KeyPressed

    private void txtBuscarObras3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarObras3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarObras3KeyReleased

    private void tablaObras6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaObras6FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaObras6FocusLost

    private void tablaObras6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaObras6MouseClicked

    }//GEN-LAST:event_tablaObras6MouseClicked

    private void tabbedMaquinaria1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedMaquinaria1StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tabbedMaquinaria1StateChanged

    private void tablaObras7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaObras7FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaObras7FocusLost

    private void tablaObras7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaObras7MouseClicked
        rowMaquinaAsig = tablaObras7.getSelectedRow();
        String titulosObraS[] = {"Nombre empleado", "Fecha"};
        DTMC = new DefaultTableModel(titulosObraS, 0);
        tablaObras8.setModel(DTMC);
        try {
            Statement smt = c.createStatement();
            String sql = "select * from OPERA_MAQUINARIA where IDMaquinaria='" + tablaObras7.getValueAt(rowMaquinaAsig, 0) + "'";
            ResultSet res = smt.executeQuery(sql);
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("NombreCompleto"), res.getString("Fecha")};
                    DTMC.addRow(fila);
                } while (res.next());
                tablaObras8.setModel(DTMC);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_tablaObras7MouseClicked

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        SeleccionarEncargado nv = new SeleccionarEncargado(5,this,true);
        nv.setVisible(true);
    }//GEN-LAST:event_jButton30ActionPerformed

    private void txtBuscarObras4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarObras4FocusGained
        if (txtBuscarObras4.getText().equals("Buscar por serie, marca, modelo...")) {
            txtBuscarObras4.setText("");
        }
    }//GEN-LAST:event_txtBuscarObras4FocusGained

    private void txtBuscarObras4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarObras4FocusLost
        if (txtBuscarObras4.getText().isEmpty()) {
            txtBuscarObras4.setText("Buscar por serie, marca, modelo...");
        }
    }//GEN-LAST:event_txtBuscarObras4FocusLost

    private void txtBuscarObras4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarObras4KeyPressed
        DTMM = new DefaultTableModel(titulosMaquinaria, 0);
        tablaObras7.setModel(DTMM);
        ResultSet res = mostrar(txtBuscarObras4.getText(), 4);
        try {
            if (res.next()) {
                do {
                    Object fila[] = {String.valueOf(Integer.parseInt(res.getString("IDMaquinaria"))), res.getString("Marca"), res.getString("Modelo")};
                    DTMM.addRow(fila);
                } while (res.next());
                tablaObras7.setModel(DTMM);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscarObras4KeyPressed

    private void txtBuscarObras4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarObras4KeyReleased
        DTMM = new DefaultTableModel(titulosMaquinaria, 0);
        tablaObras7.setModel(DTMM);
        ResultSet res = mostrar(txtBuscarObras4.getText(), 4);
        try {
            if (res.next()) {
                do {
                    Object fila[] = {String.valueOf(Integer.parseInt(res.getString("IDMaquinaria"))), res.getString("Marca"), res.getString("Modelo")};
                    DTMM.addRow(fila);
                } while (res.next());
                tablaObras7.setModel(DTMM);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtBuscarObras4KeyReleased

    private void tablaObras8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaObras8FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaObras8FocusLost

    private void tablaObras8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaObras8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaObras8MouseClicked

    private void cbf1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbf1ItemStateChanged
        System.out.println("Evneto dia 1");

        String entreFecha = "";
        for (int i = 0; i < rowCombustible; i++) {
            String fecha = fechasCombustible[i];
            System.out.println("/i == " + i);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date fech = new Date();
            try {
                fech = format.parse(fecha);
                if (fech.after(format.parse((String) cbf1.getSelectedItem() + "/" + (String) cbf2.getSelectedItem() + "/" + (String) cbf3.getSelectedItem()))
                        && fech.before(format.parse((String) cbf4.getSelectedItem() + "/" + (String) cbf5.getSelectedItem() + "/" + (String) cbf6.getSelectedItem()))) {
                    System.out.println("Medio");
                    entreFecha += " or FolioOrden = " + foliosCombustible[i];
                }
            } catch (ParseException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        DTMCONTROL = new DefaultTableModel(titulosControl, 0);
        tablaRendimientoC.setModel(DTMCONTROL);
        try {
            ResultSet res = mostrarCombustibleFecha(entreFecha);
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("Fecha"), res.getString("FolioOrden"), res.getString("Vehiculo"), res.getString("Odometro"), res.getString("Kilometraje"), res.getString("Combustible"), res.getString("Litros"), res.getString("Moneda"), res.getString("kmLitro"), res.getString("kmMoneda"), res.getString("FolioPago")};
                    DTMCONTROL.addRow(fila);
                } while (res.next());
                tablaRendimientoC.setModel(DTMCONTROL);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_cbf1ItemStateChanged

    private void tablaRendimientoCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaRendimientoCMouseClicked
        fechaMaqui = tablaRendimientoC.getSelectedRow();
    }//GEN-LAST:event_tablaRendimientoCMouseClicked

    private void txtBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar1ActionPerformed

    private void cbf2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbf2ItemStateChanged
//        System.out.println("Evneto mes 1");

        String entreFecha = "";
        for (int i = 0; i < rowCombustible; i++) {
            String fecha = fechasCombustible[i];
//            System.out.println("/i == "+i);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date fech = new Date();
            try {
                fech = format.parse(fecha);
                if (fech.after(format.parse((String) cbf1.getSelectedItem() + "/" + (String) cbf2.getSelectedItem() + "/" + (String) cbf3.getSelectedItem()))
                        && fech.before(format.parse((String) cbf4.getSelectedItem() + "/" + (String) cbf5.getSelectedItem() + "/" + (String) cbf6.getSelectedItem()))) {
//                    System.out.println("Medio");
                    entreFecha += " or FolioOrden = " + foliosCombustible[i];
                }
            } catch (ParseException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        DTMCONTROL = new DefaultTableModel(titulosControl, 0);
        tablaRendimientoC.setModel(DTMCONTROL);
        try {
            ResultSet res = mostrarCombustibleFecha(entreFecha);
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("Fecha"), res.getString("FolioOrden"), res.getString("Vehiculo"), res.getString("Odometro"), res.getString("Kilometraje"), res.getString("Combustible"), res.getString("Litros"), res.getString("Moneda"), res.getString("kmLitro"), res.getString("kmMoneda"), res.getString("FolioPago")};
                    DTMCONTROL.addRow(fila);
                } while (res.next());
                tablaRendimientoC.setModel(DTMCONTROL);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_cbf2ItemStateChanged

    private void cbf3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbf3ItemStateChanged
//       System.out.println("Evneto anio 1");

        String entreFecha = "";
        for (int i = 0; i < rowCombustible; i++) {
            String fecha = fechasCombustible[i];
//            System.out.println("/i == "+i);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date fech = new Date();
            try {
                fech = format.parse(fecha);
                if (fech.after(format.parse((String) cbf1.getSelectedItem() + "/" + (String) cbf2.getSelectedItem() + "/" + (String) cbf3.getSelectedItem()))
                        && fech.before(format.parse((String) cbf4.getSelectedItem() + "/" + (String) cbf5.getSelectedItem() + "/" + (String) cbf6.getSelectedItem()))) {
//                    System.out.println("Medio");
                    entreFecha += " or FolioOrden = " + foliosCombustible[i];
                }
            } catch (ParseException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        DTMCONTROL = new DefaultTableModel(titulosControl, 0);
        tablaRendimientoC.setModel(DTMCONTROL);
        try {
            ResultSet res = mostrarCombustibleFecha(entreFecha);
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("Fecha"), res.getString("FolioOrden"), res.getString("Vehiculo"), res.getString("Odometro"), res.getString("Kilometraje"), res.getString("Combustible"), res.getString("Litros"), res.getString("Moneda"), res.getString("kmLitro"), res.getString("kmMoneda"), res.getString("FolioPago")};
                    DTMCONTROL.addRow(fila);
                } while (res.next());
                tablaRendimientoC.setModel(DTMCONTROL);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_cbf3ItemStateChanged

    private void cbf4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbf4ItemStateChanged
        //        System.out.println("Evneto mes 1");

        String entreFecha = "";
        for (int i = 0; i < rowCombustible; i++) {
            String fecha = fechasCombustible[i];
//            System.out.println("/i == "+i);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date fech = new Date();
            try {
                fech = format.parse(fecha);
                if (fech.after(format.parse((String) cbf1.getSelectedItem() + "/" + (String) cbf2.getSelectedItem() + "/" + (String) cbf3.getSelectedItem()))
                        && fech.before(format.parse((String) cbf4.getSelectedItem() + "/" + (String) cbf5.getSelectedItem() + "/" + (String) cbf6.getSelectedItem()))) {
//                    System.out.println("Medio");
                    entreFecha += " or FolioOrden = " + foliosCombustible[i];
                }
            } catch (ParseException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        DTMCONTROL = new DefaultTableModel(titulosControl, 0);
        tablaRendimientoC.setModel(DTMCONTROL);
        try {
            ResultSet res = mostrarCombustibleFecha(entreFecha);
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("Fecha"), res.getString("FolioOrden"), res.getString("Vehiculo"), res.getString("Odometro"), res.getString("Kilometraje"), res.getString("Combustible"), res.getString("Litros"), res.getString("Moneda"), res.getString("kmLitro"), res.getString("kmMoneda"), res.getString("FolioPago")};
                    DTMCONTROL.addRow(fila);
                } while (res.next());
                tablaRendimientoC.setModel(DTMCONTROL);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_cbf4ItemStateChanged

    private void cbf5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbf5ItemStateChanged
        //        System.out.println("Evneto mes 1");

        String entreFecha = "";
        for (int i = 0; i < rowCombustible; i++) {
            String fecha = fechasCombustible[i];
//            System.out.println("/i == "+i);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date fech = new Date();
            try {
                fech = format.parse(fecha);
                if (fech.after(format.parse((String) cbf1.getSelectedItem() + "/" + (String) cbf2.getSelectedItem() + "/" + (String) cbf3.getSelectedItem()))
                        && fech.before(format.parse((String) cbf4.getSelectedItem() + "/" + (String) cbf5.getSelectedItem() + "/" + (String) cbf6.getSelectedItem()))) {
//                    System.out.println("Medio");
                    entreFecha += " or FolioOrden = " + foliosCombustible[i];
                }
            } catch (ParseException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        DTMCONTROL = new DefaultTableModel(titulosControl, 0);
        tablaRendimientoC.setModel(DTMCONTROL);
        try {
            ResultSet res = mostrarCombustibleFecha(entreFecha);
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("Fecha"), res.getString("FolioOrden"), res.getString("Vehiculo"), res.getString("Odometro"), res.getString("Kilometraje"), res.getString("Combustible"), res.getString("Litros"), res.getString("Moneda"), res.getString("kmLitro"), res.getString("kmMoneda"), res.getString("FolioPago")};
                    DTMCONTROL.addRow(fila);
                } while (res.next());
                tablaRendimientoC.setModel(DTMCONTROL);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_cbf5ItemStateChanged

    private void cbf6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbf6ItemStateChanged
        //        System.out.println("Evneto mes 1");

        String entreFecha = "";
        for (int i = 0; i < rowCombustible; i++) {
            String fecha = fechasCombustible[i];
//            System.out.println("/i == "+i);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date fech = new Date();
            try {
                fech = format.parse(fecha);
                if (fech.after(format.parse((String) cbf1.getSelectedItem() + "/" + (String) cbf2.getSelectedItem() + "/" + (String) cbf3.getSelectedItem()))
                        && fech.before(format.parse((String) cbf4.getSelectedItem() + "/" + (String) cbf5.getSelectedItem() + "/" + (String) cbf6.getSelectedItem()))) {
//                    System.out.println("Medio");
                    entreFecha += " or FolioOrden = " + foliosCombustible[i];
                }
            } catch (ParseException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        DTMCONTROL = new DefaultTableModel(titulosControl, 0);
        tablaRendimientoC.setModel(DTMCONTROL);
        try {
            ResultSet res = mostrarCombustibleFecha(entreFecha);
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("Fecha"), res.getString("FolioOrden"), res.getString("Vehiculo"), res.getString("Odometro"), res.getString("Kilometraje"), res.getString("Combustible"), res.getString("Litros"), res.getString("Moneda"), res.getString("kmLitro"), res.getString("kmMoneda"), res.getString("FolioPago")};
                    DTMCONTROL.addRow(fila);
                } while (res.next());
                tablaRendimientoC.setModel(DTMCONTROL);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_cbf6ItemStateChanged

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed

        try {
            
            String choosertitle = "";
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int option = chooser.showSaveDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(null, "Se generó correctamente el PDF", "Exito", JOptionPane.INFORMATION_MESSAGE);
                File file = chooser.getSelectedFile();
                choosertitle = file.getAbsolutePath();
                System.out.println("Ruta:" + choosertitle + ".pdf");
                choosertitle = choosertitle + ".pdf";
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(choosertitle));
                document.open();
                //addMetaData(document);
                document.addTitle("Reporte Obra SOCET");
                document.addSubject("Digitalizada");
                document.addKeywords("Reporte Obra,SOCET");
                document.addAuthor("Fabian Gaxiola");
                document.addCreator("Fabian Gaxiola");
                //addTitlePage(document);
                Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
                Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
                Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
                Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
                Font titleArial = new Font(Font.FontFamily.HELVETICA, 13);
                Font bodyArial = new Font(Font.FontFamily.HELVETICA, 11);
                Image imagen = Image.getInstance("src/Miscelaneo/1.png");
                //imagen.setAlignment(Element.ALIGN_CENTER);
                imagen.setAbsolutePosition(60f, 715f);
                imagen.scaleAbsolute(90, 90);
                document.add(imagen);
                Paragraph par = new Paragraph();
                //document.add(Chunk.NEWLINE);
                par.setIndentationLeft(240);
                par.add(new Paragraph("SOCET S.A. de C.V.", titleArial));
                document.add(par);
                //document.add(Chunk.NEWLINE);
                Paragraph par1 = new Paragraph();
                par1.setIndentationLeft(120);
                par1.add(new Paragraph("San José Obrero #2325 Ote. Col. Hacienda San José Cd. Obregón, Sonora.", bodyArial));
                document.add(par1);
                Paragraph par2 = new Paragraph();
                par2.setIndentationLeft(250);
                par2.add(new Paragraph("Tel: (644)1.69.76.86", bodyArial));
                document.add(par2);
                Paragraph par3 = new Paragraph();
                par3.setIndentationLeft(210);
                par3.add(new Paragraph("e-Mail: socetsadecv@yahoo.com.mx", bodyArial));
                document.add(par3);
                document.add(Chunk.NEWLINE);
                Paragraph par4 = new Paragraph();
                par4.setIndentationLeft(230);
                par4.add(new Paragraph("Reportes de Obra.", titleArial));
                document.add(par4);
                document.add(Chunk.NEWLINE);
                PdfPTable tableemp = new PdfPTable(5);
                tableemp.setWidthPercentage(100);
                tableemp.setHorizontalAlignment(Element.ALIGN_RIGHT);
                PdfPCell id = new PdfPCell(new Phrase("Matricula de Obra"));
                id.setColspan(1);
                id.setRowspan(1);
                id.setBackgroundColor(new BaseColor(250, 191, 0));
                tableemp.addCell(id);
                PdfPCell fecha = new PdfPCell(new Phrase("Nombre Obra"));
                fecha.setColspan(2);
                fecha.setRowspan(1);
                fecha.setBackgroundColor(new BaseColor(250, 191, 0));
                tableemp.addCell(fecha);
                PdfPCell nombrec = new PdfPCell(new Phrase("Nombre Cliente"));
                nombrec.setColspan(2);
                nombrec.setRowspan(1);
                nombrec.setBackgroundColor(new BaseColor(250, 191, 0));
                tableemp.addCell(nombrec);
                int row = tablaObras1.getSelectedRow();
                //String value = tablaObras5.getModel().getValueAt(row, column).toString();
                PdfPCell a7 = new PdfPCell(new Phrase(tablaObras1.getModel().getValueAt(row, 0).toString()));
                a7.setBackgroundColor(BaseColor.WHITE);
                a7.setColspan(1);
                tableemp.addCell(a7);
                PdfPCell a8 = new PdfPCell(new Phrase(tablaObras1.getModel().getValueAt(row, 1).toString()));
                a8.setBackgroundColor(BaseColor.WHITE);
                a8.setColspan(2);
                tableemp.addCell(a8);
                PdfPCell a9 = new PdfPCell(new Phrase(tablaObras1.getModel().getValueAt(row, 2).toString()));
                a9.setBackgroundColor(BaseColor.WHITE);
                a9.setColspan(2);
                tableemp.addCell(a9);
                document.add(tableemp);
                document.add(Chunk.NEWLINE);
                PdfPTable table2 = new PdfPTable(4);
                table2.setWidthPercentage(100);
                PdfPCell gato = new PdfPCell(new Phrase("ID Seguimiento"));
                gato.setBackgroundColor(new BaseColor(250, 191, 0));
                gato.setColspan(1);
                table2.addCell(gato);
                PdfPCell idpro = new PdfPCell(new Phrase("Fecha"));
                idpro.setColspan(1);
                idpro.setBackgroundColor(new BaseColor(250, 191, 0));
                table2.addCell(idpro);
                PdfPCell cant = new PdfPCell(new Phrase("Pago"));
                cant.setBackgroundColor(new BaseColor(250, 191, 0));
                cant.setColspan(1);
                table2.addCell(cant);
                PdfPCell desc = new PdfPCell(new Phrase("Descripcion"));
                desc.setBackgroundColor(new BaseColor(250, 191, 0));
                desc.setColspan(1);
                table2.addCell(desc);
                for (int i = 0; i < tablaObras2.getRowCount(); i++) {
                    if (i % 2 == 0) {
                        PdfPCell v1 = new PdfPCell(new Phrase(tablaObras2.getModel().getValueAt(i, 0).toString()));
                        v1.setBackgroundColor(BaseColor.WHITE);
                        v1.setColspan(1);
                        table2.addCell(v1);
                        PdfPCell v2 = new PdfPCell(new Phrase(tablaObras2.getModel().getValueAt(i, 1).toString()));
                        v2.setBackgroundColor(BaseColor.WHITE);
                        v2.setColspan(1);
                        table2.addCell(v2);
                        PdfPCell v3 = new PdfPCell(new Phrase(tablaObras2.getModel().getValueAt(i, 2).toString()));
                        v3.setBackgroundColor(BaseColor.WHITE);
                        v3.setColspan(1);
                        table2.addCell(v3);
                        PdfPCell v4 = new PdfPCell(new Phrase(tablaObras2.getModel().getValueAt(i, 3).toString()));
                        v4.setBackgroundColor(BaseColor.WHITE);
                        v4.setColspan(1);
                        table2.addCell(v4);
                    } else {
                        PdfPCell v1 = new PdfPCell(new Phrase(tablaObras2.getModel().getValueAt(i, 0).toString()));
                        v1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v1.setColspan(1);
                        table2.addCell(v1);
                        PdfPCell v2 = new PdfPCell(new Phrase(tablaObras2.getModel().getValueAt(i, 1).toString()));
                        v2.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v2.setColspan(1);
                        table2.addCell(v2);
                        PdfPCell v3 = new PdfPCell(new Phrase(tablaObras2.getModel().getValueAt(i, 2).toString()));
                        v3.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v3.setColspan(1);
                        table2.addCell(v3);
                        PdfPCell v4 = new PdfPCell(new Phrase(tablaObras2.getModel().getValueAt(i, 3).toString()));
                        v4.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        v4.setColspan(1);
                        table2.addCell(v4);
                    }
                }
                document.add(table2);
                document.add(Chunk.NEWLINE);
                document.close();
            }
        } catch (DocumentException | IOException e) {
        }
        JOptionPane.showMessageDialog(null, "Se generaron los seguimientos correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton31ActionPerformed

    private void cbf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbf1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbf1ActionPerformed

    private void lblPrincipalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrincipalMouseClicked
        panelHome.setVisible(true);
        panelClientes.setVisible(false);
        panelInventario.setVisible(false);
        panelObras.setVisible(false);
        panelMaquinaria.setVisible(false);
        panelCotizacion.setVisible(false);
        panelProveedor.setVisible(false);
        panelEmpleado.setVisible(false);
        panelReporte.setVisible(false);
        txtBuscarClientes.setText("Buscar por nombre o matricula de cliente...");
        txtBuscarObras1.setText("Buscar por cliente, nombre, o matricula de obra...");
        txtBuscarObras.setText("Buscar por cliente, nombre, estatus o matricula de obra...");
        txtBuscarEmpleado.setText("Buscar por nombre o matricula de empleado...");
        txtBuscarProveedor.setText("Buscar por nombre o matricula de proveedor...");
        txtBuscarOrden.setText("Buscar por folio de orden....");
        txtBuscar1.setText("Buscar por folio orden o vehículo...");
        txtBuscarMaquinaria.setText("Buscar por serie, marca, modelo o placas...");
        txtBuscarProducto.setText("Buscar por descripción o código de producto...");
        txtBuscarOrden.setText("Buscar por folio de orden....");
        Deseleccionar();
        lblPrincipal.setBackground(BG1);
        lblPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/homeb.png")));
        lblPrincipal.setForeground(TX1);
    }//GEN-LAST:event_lblPrincipalMouseClicked
    public void Deseleccionar(){
        lblPrincipal.setBackground(BG2);
        lblPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/home.png")));
        lblPrincipal.setForeground(TX2);
        lblObras.setBackground(BG2);
        lblObras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/obra.png")));
        lblObras.setForeground(TX2);
        lblClientes.setBackground(BG2);
        lblClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cliente.png")));
        lblClientes.setForeground(TX2);
        lblCotizaciones.setBackground(BG2);
        lblCotizaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cotizacion.png")));
        lblCotizaciones.setForeground(TX2);
        lblInventarios.setBackground(BG2);
        lblInventarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/inventario.png")));
        lblInventarios.setForeground(TX2);
        lblMaquinarias.setBackground(BG2);
        lblMaquinarias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/maquinaria.png")));
        lblMaquinarias.setForeground(TX2);
        lblContaduria.setBackground(BG2);
        lblContaduria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/contrato.png")));
        lblContaduria.setForeground(TX2);
        lblProveedores.setBackground(BG2);
        lblProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/proveedor.png")));
        lblProveedores.setForeground(TX2);
        lblEmpleados.setBackground(BG2);
        lblEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/empleado.png")));
        lblEmpleados.setForeground(TX2);
    }
    private void lblPrincipalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrincipalMouseEntered
        lblPrincipal.setBackground(BG1);
        lblPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/homeb.png")));
        lblPrincipal.setForeground(TX1);//primario
    }//GEN-LAST:event_lblPrincipalMouseEntered

    private void lblPrincipalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrincipalMouseExited
        if(!panelHome.isVisible()){
            lblPrincipal.setBackground(BG2);//secundario
            lblPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/home.png")));
            lblPrincipal.setForeground(TX2);//secundario
        } 
    }//GEN-LAST:event_lblPrincipalMouseExited
    

    private void lblObrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblObrasMouseClicked
        Utilidades uti = new Utilidades();
        uti.clickEnObra();
        Deseleccionar();
        lblObras.setBackground(BG1);//primario
        lblObras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/obrab.png")));
        lblObras.setForeground(TX1);
    }//GEN-LAST:event_lblObrasMouseClicked

    private void lblObrasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblObrasMouseEntered
        lblObras.setBackground(BG1);
        lblObras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/obrab.png")));
        lblObras.setForeground(TX1);
    }//GEN-LAST:event_lblObrasMouseEntered

    private void lblObrasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblObrasMouseExited
        if (!panelObras.isVisible()) {
            lblObras.setBackground(BG2);
            lblObras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/obra.png")));
            lblObras.setForeground(TX2);
        }
    }//GEN-LAST:event_lblObrasMouseExited

    private void lblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblClientesMouseClicked
        Utilidades uti = new Utilidades();
        uti.ClickEnClientes();
        Deseleccionar();
        lblClientes.setBackground(BG1);
        lblClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/clienteb.png")));
        lblClientes.setForeground(TX1);
    }//GEN-LAST:event_lblClientesMouseClicked

    private void lblClientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblClientesMouseEntered
        lblClientes.setBackground(BG1);
        lblClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/clienteb.png")));
        lblClientes.setForeground(TX1);
    }//GEN-LAST:event_lblClientesMouseEntered

    private void lblClientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblClientesMouseExited
        if(!panelClientes.isVisible()){
            lblClientes.setBackground(BG2);
            lblClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cliente.png")));
            lblClientes.setForeground(TX2);
        }
    }//GEN-LAST:event_lblClientesMouseExited

    private void lblCotizacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCotizacionesMouseClicked
        Utilidades uti = new Utilidades();
        uti.ClickEnCotizaciones();
        Deseleccionar();
        lblCotizaciones.setBackground(BG1);
        lblCotizaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cotizacionb.png")));
        lblCotizaciones.setForeground(TX1);
    }//GEN-LAST:event_lblCotizacionesMouseClicked

    private void lblCotizacionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCotizacionesMouseEntered
        lblCotizaciones.setBackground(BG1);
        lblCotizaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cotizacionb.png")));
        lblCotizaciones.setForeground(TX1);
    }//GEN-LAST:event_lblCotizacionesMouseEntered

    private void lblCotizacionesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCotizacionesMouseExited
        if(!panelCotizacion.isVisible()){
            lblCotizaciones.setBackground(BG2);
            lblCotizaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cotizacion.png")));
            lblCotizaciones.setForeground(TX2);
        }
    }//GEN-LAST:event_lblCotizacionesMouseExited

    private void lblInventariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInventariosMouseClicked
        Utilidades uti = new Utilidades();
        uti.ClickEnInventario();
        Deseleccionar();
        lblInventarios.setBackground(BG1);
        lblInventarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/inventariob.png")));
        lblInventarios.setForeground(TX1);
    }//GEN-LAST:event_lblInventariosMouseClicked

    private void lblInventariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInventariosMouseEntered
        lblInventarios.setBackground(BG1);
        lblInventarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/inventariob.png")));
        lblInventarios.setForeground(TX1);
    }//GEN-LAST:event_lblInventariosMouseEntered

    private void lblInventariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInventariosMouseExited
        if(!panelInventario.isVisible()){
            lblInventarios.setBackground(BG2);
            lblInventarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/inventario.png")));
            lblInventarios.setForeground(TX2);
        }
    }//GEN-LAST:event_lblInventariosMouseExited

    private void lblMaquinariasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMaquinariasMouseClicked
        Utilidades uti = new Utilidades();
        uti.ClickEnMaquinaria();
        Deseleccionar();
        lblMaquinarias.setBackground(BG1);
        lblMaquinarias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/maquinariab.png")));
        lblMaquinarias.setForeground(TX1);
    }//GEN-LAST:event_lblMaquinariasMouseClicked

    private void lblMaquinariasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMaquinariasMouseEntered
        lblMaquinarias.setBackground(BG1);
        lblMaquinarias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/maquinariab.png")));
        lblMaquinarias.setForeground(TX1);
    }//GEN-LAST:event_lblMaquinariasMouseEntered

    private void lblMaquinariasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMaquinariasMouseExited
        if(!panelMaquinaria.isVisible()){
            lblMaquinarias.setBackground(BG2);
            lblMaquinarias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/maquinaria.png")));
            lblMaquinarias.setForeground(TX2);
        }
    }//GEN-LAST:event_lblMaquinariasMouseExited

    private void lblContaduriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblContaduriaMouseClicked
        Utilidades uti = new Utilidades();
        uti.ClickEnContaduria();
        Deseleccionar();
        lblContaduria.setBackground(BG1);
        lblContaduria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/contratob.png")));
        lblContaduria.setForeground(TX1);
    }//GEN-LAST:event_lblContaduriaMouseClicked

    private void lblContaduriaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblContaduriaMouseEntered
        lblContaduria.setBackground(BG1);
        lblContaduria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/contratob.png")));
        lblContaduria.setForeground(TX1);
    }//GEN-LAST:event_lblContaduriaMouseEntered

    private void lblContaduriaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblContaduriaMouseExited
        if(!panelReporte.isVisible()){
            lblContaduria.setBackground(BG2);
            lblContaduria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/contrato.png")));
            lblContaduria.setForeground(TX2);
        }
    }//GEN-LAST:event_lblContaduriaMouseExited

    private void lblProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProveedoresMouseClicked
        Utilidades uti = new Utilidades();
        uti.ClickEnProveedores();
        Deseleccionar();
        lblProveedores.setBackground(BG1);
        lblProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/proveedorb.png")));
        lblProveedores.setForeground(TX1);
    }//GEN-LAST:event_lblProveedoresMouseClicked

    private void lblProveedoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProveedoresMouseEntered
        lblProveedores.setBackground(BG1);
        lblProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/proveedorb.png")));
        lblProveedores.setForeground(TX1);
    }//GEN-LAST:event_lblProveedoresMouseEntered

    private void lblProveedoresMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProveedoresMouseExited
        if(!panelProveedor.isVisible()){
            lblProveedores.setBackground(BG2);
            lblProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/proveedor.png")));
            lblProveedores.setForeground(TX2);
        }
    }//GEN-LAST:event_lblProveedoresMouseExited

    private void lblEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEmpleadosMouseClicked
        Utilidades uti = new Utilidades();
        uti.ClickEnEmpleados();
        Deseleccionar();
        lblEmpleados.setBackground(BG1);
        lblEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/empleadob.png")));
        lblEmpleados.setForeground(TX1);
    }//GEN-LAST:event_lblEmpleadosMouseClicked

    private void lblEmpleadosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEmpleadosMouseEntered
        lblEmpleados.setBackground(BG1);
        lblEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/empleadob.png")));
        lblEmpleados.setForeground(TX1);
    }//GEN-LAST:event_lblEmpleadosMouseEntered

    private void lblEmpleadosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEmpleadosMouseExited
        if(!panelEmpleado.isVisible()){
            lblEmpleados.setBackground(BG2);
            lblEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/empleado.png")));
            lblEmpleados.setForeground(TX2);
        }
    }//GEN-LAST:event_lblEmpleadosMouseExited

    private void btnAñadirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirUsuarioActionPerformed
        Opciones op = new Opciones(this,true);
        op.setVisible(true);
    }//GEN-LAST:event_btnAñadirUsuarioActionPerformed

    public void ActualizarTodo() {
        System.out.println("Columna: " + Tabla1.getSelectedColumn());
        System.out.println("Fila: " + Tabla1.getSelectedRow());
        TAC = 0;
        TAF = 0;
        TAD = 0;
        TPC = 0;
        TPF = 0;
        TC = 0;

        for (int i = 0; i < 7; i++) {
            if (Tabla1.getValueAt(i, 1) != null) {
                TAC += (double) Tabla1.getValueAt(i, 1);
            } else {

            }
        }

        for (int a = 0; a < 7; a++) {
            if (Tabla2.getValueAt(a, 1) != null) {
                TAF += (double) Tabla2.getValueAt(a, 1);
            } else {

            }
        }
        for (int b = 0; b < 7; b++) {
            if (Tabla3.getValueAt(b, 1) != null) {
                TAD += (double) Tabla3.getValueAt(b, 1);
            } else {

            }
        }
        for (int C = 0; C < 7; C++) {
            if (Tabla4.getValueAt(C, 1) != null) {
                TPC += (double) Tabla4.getValueAt(C, 1);
            } else {

            }
        }
        for (int D = 0; D < 5; D++) {
            if (Tabla5.getValueAt(D, 1) != null) {
                TPF += (double) Tabla5.getValueAt(D, 1);
            } else {

            }
        }
        for (int E = 0; E < 5; E++) {
            if (Tabla6.getValueAt(E, 1) != null) {
                TC += (double) Tabla6.getValueAt(E, 1);
            } else {

            }
        }
        Tabla1.setValueAt(TAC, 6, 2);
        Tabla2.setValueAt(TAF, 6, 2);
        Tabla3.setValueAt(TAD, 6, 2);
        Tabla4.setValueAt(TPC, 6, 2);
        Tabla5.setValueAt(TPF, 4, 2);
        Tabla6.setValueAt(TC, 4, 2);
        TotalActivo.setText("" + (TAC + TAF + TAD));
        TotalPasivo.setText("" + (TPC + TPF));
        TotalCapital.setText("" + TC);
        TotalPC.setText("" + (TPC + TPF + TC));

    }

    public ResultSet mostrarCombustibleFecha(String str) {

        ResultSet RS = null;

        try {
            Statement ST = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "select * from CONTROL_DE_COMBUSTIBLE where FolioOrden = -10000 " + str;
            RS = ST.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }

        return RS;
    }

    public ResultSet mostrar(String str, int num) {
        //1=OBRAS
        //2=CLIENTES
        //3=EMPLEADOS
        //4=MAQUINARIA
        //5=PRODUCTOS
        //6=CONTROL_COMBUSTIBLE
        //7=PROVEEDORES
        //8=PAGO_PROVEEDOR
        //9?Obras seguimientos
        ResultSet RS = null;
        switch (num) {
            case 1:
                try {
                    Statement ST = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    String sql = "select * from OBRAS where IDObra like '" + str + "%'" + " OR NombreObra like '" + str + "%'" + " OR Estatus like '" + str + "%'" + " OR Cliente like '" + str + "%'";
                    RS = ST.executeQuery(sql);
                } catch (SQLException e) {
                    System.out.println(e);
                }
                break;
            case 2:
                try {
                    Statement ST = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    String sql = "select * from CLIENTES where IDCliente like '" + str + "%'" + " OR Nombre like '" + str + "%'" + " OR Apellido like '" + str + "%'";
                    RS = ST.executeQuery(sql);
                } catch (SQLException e) {
                    System.out.println(e);
                }
                break;
            case 3:
                try {
                    Statement ST = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    String sql = "select * from TRABAJADORES where IDTrabajador like '" + str + "%'" + " OR Nombre like '" + str + "%'" + " OR Apellido like '" + str + "%'";
                    RS = ST.executeQuery(sql);
                } catch (SQLException e) {
                    System.out.println(e);
                }
                break;
            case 4:
                try {
                    Statement ST = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    String sql = "select * from MAQUINARIAS where IDMaquinaria like '" + str + "%'" + " OR Marca like '" + str + "%'" + " OR Modelo like '" + str + "%'" + " OR Placas like '" + str + "%'";
                    RS = ST.executeQuery(sql);
                } catch (SQLException e) {
                    System.out.println(e);
                }
                break;
            case 5:
                try {
                    Statement ST = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    String sql = "select * from PRODUCTOS where IDProducto like '" + str + "%'" + " OR Descripcion like '" + str + "%'";
                    RS = ST.executeQuery(sql);
                } catch (SQLException e) {
                    System.out.println(e);
                }
                break;
            case 6:
                try {
                    Statement ST = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    String sql = "select * from CONTROL_DE_COMBUSTIBLE where FolioOrden like '" + str + "%'" + " OR Vehiculo like '" + str + "%'";
                    RS = ST.executeQuery(sql);
                } catch (SQLException e) {
                    System.out.println(e);
                }
                break;
            case 7:
                try {
                    Statement ST = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    String sql = "select * from PROVEEDORES where IDProveedor like '" + str + "%'";
                    RS = ST.executeQuery(sql);
                } catch (SQLException e) {
                    System.out.println(e);
                }
                break;
            case 8:
                try {
                    Statement ST = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    String sql = "select * from TABLA_PROVEEDOR where Folio like '" + str + "%'" + " AND Proveedor='" + lmao + "'";
                    RS = ST.executeQuery(sql);
                } catch (SQLException e) {
                    System.out.println(e);
                }
                break;
            case 9:
                try {
                    Statement ST = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    String sql = "select * from OBRAS where IDObra like '" + str + "%'" + " OR NombreObra like '" + str + "%'" + " OR Cliente like '" + str + "%'";
                    RS = ST.executeQuery(sql);
                } catch (SQLException e) {
                    System.out.println(e);
                }
                break;
        }
        return RS;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Home().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla1;
    private javax.swing.JTable Tabla2;
    private javax.swing.JTable Tabla3;
    private javax.swing.JTable Tabla4;
    private javax.swing.JTable Tabla5;
    private javax.swing.JTable Tabla6;
    private javax.swing.JTextField TotalActivo;
    private javax.swing.JTextField TotalCapital;
    private javax.swing.JTextField TotalPC;
    private javax.swing.JTextField TotalPasivo;
    public static javax.swing.JButton agregarP;
    public static javax.swing.JButton agregarP1;
    private javax.swing.JPanel agregarPanel;
    private javax.swing.JPanel agregarPanel1;
    private javax.swing.JPanel asignarEmpleadosP;
    private javax.swing.JPanel asignarMaquinaria;
    private javax.swing.JButton btnAgregarP;
    private javax.swing.JButton btnAgregarP1;
    private javax.swing.JButton btnAñadirUsuario;
    private javax.swing.JButton btnCamaras;
    private javax.swing.JButton btnCerrarS;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminar2;
    private javax.swing.JButton btnEliminar4;
    private javax.swing.JButton btnEliminar5;
    private javax.swing.JButton btnEliminarP;
    private javax.swing.JButton btnEliminarP1;
    private javax.swing.JButton btnEliminarPro;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnModificar1;
    private javax.swing.JButton btnModificar2;
    private javax.swing.JButton btnModificar4;
    private javax.swing.JButton btnModificar5;
    private javax.swing.JButton btnModificarP;
    private javax.swing.JComboBox<String> cbf1;
    private javax.swing.JComboBox<String> cbf2;
    private javax.swing.JComboBox<String> cbf3;
    private javax.swing.JComboBox<String> cbf4;
    private javax.swing.JComboBox<String> cbf5;
    private javax.swing.JComboBox<String> cbf6;
    private javax.swing.JLabel check10;
    private javax.swing.JLabel check11;
    private javax.swing.JLabel check12;
    private javax.swing.JLabel check13;
    private javax.swing.JLabel check14;
    public static javax.swing.JLabel check15;
    public static javax.swing.JLabel check5;
    private javax.swing.JLabel check6;
    private javax.swing.JLabel check7;
    private javax.swing.JLabel check8;
    private javax.swing.JLabel check9;
    public static javax.swing.JComboBox<String> comboProveedores;
    private javax.swing.JPanel datosObraP;
    private javax.swing.JPanel datosPanel;
    private javax.swing.JPanel datosPanel1;
    private javax.swing.JPanel derPanel;
    private javax.swing.JLabel fechaCotizacion;
    private javax.swing.JLabel fechaCotizacion1;
    private javax.swing.JLabel fechaCotizacion2;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel idCliente;
    private javax.swing.JLabel idCliente10;
    private javax.swing.JLabel idCliente11;
    private javax.swing.JLabel idCliente12;
    private javax.swing.JLabel idCliente13;
    private javax.swing.JLabel idCliente14;
    private javax.swing.JLabel idCliente15;
    private javax.swing.JLabel idCliente3;
    private javax.swing.JLabel idCliente4;
    private javax.swing.JLabel idCliente5;
    private javax.swing.JLabel idCliente6;
    private javax.swing.JLabel idCliente7;
    private javax.swing.JLabel idCliente8;
    private javax.swing.JLabel idCliente9;
    private javax.swing.JPanel infoClientes;
    private javax.swing.JPanel infoInventario;
    private javax.swing.JPanel infoObras;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JPanel infoPanelEmp;
    private javax.swing.JPanel infoProveedores;
    private javax.swing.JLabel ivaCompra;
    private javax.swing.JPanel izqPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane32;
    private javax.swing.JScrollPane jScrollPane33;
    private javax.swing.JScrollPane jScrollPane34;
    private javax.swing.JScrollPane jScrollPane35;
    private javax.swing.JScrollPane jScrollPane36;
    private javax.swing.JScrollPane jScrollPane37;
    private javax.swing.JScrollPane jScrollPane38;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTabbedPane jTabbedPane8;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable8;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblBienvenido;
    public static javax.swing.JLabel lblClientes;
    public static javax.swing.JLabel lblContaduria;
    public static javax.swing.JLabel lblCotizaciones;
    public static javax.swing.JLabel lblEmpleados;
    public static javax.swing.JLabel lblFechaHoy;
    private javax.swing.JLabel lblIVA;
    public static javax.swing.JLabel lblInventarios;
    private javax.swing.JLabel lblLogo;
    public static javax.swing.JLabel lblMaquinarias;
    public static javax.swing.JLabel lblObras;
    private javax.swing.JLabel lblPrincipal;
    public static javax.swing.JLabel lblProveedores;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel listaNomina;
    private javax.swing.ButtonGroup listabg;
    private javax.swing.JPanel nominaPanel;
    private javax.swing.JPanel nuevacotPanel;
    private javax.swing.JPanel nuevacotPanel1;
    public static javax.swing.JPanel panelClientes;
    private javax.swing.JPanel panelCompra;
    public static javax.swing.JPanel panelCotizacion;
    public static javax.swing.JPanel panelEmpleado;
    public static javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelInveObra;
    public static javax.swing.JPanel panelInventario;
    private javax.swing.JPanel panelListas;
    public static javax.swing.JPanel panelMaquinaria;
    public static javax.swing.JPanel panelObras;
    private javax.swing.JPanel panelPago;
    public static javax.swing.JPanel panelProveedor;
    public static javax.swing.JPanel panelReporte;
    private javax.swing.JPanel rendimientoPanel;
    private javax.swing.JPanel seguimientoPanel;
    public static javax.swing.JSpinner spCantidad;
    public static javax.swing.JSpinner spCantidad1;
    private javax.swing.JLabel subtotalCompra;
    private javax.swing.JTabbedPane tabbedClientes;
    private javax.swing.JTabbedPane tabbedEmpleados;
    private javax.swing.JTabbedPane tabbedMaquinaria;
    private javax.swing.JTabbedPane tabbedMaquinaria1;
    private javax.swing.JTabbedPane tabbedObras;
    private javax.swing.JTabbedPane tabbedObras1;
    private javax.swing.JTabbedPane tabbedProveedores;
    public static javax.swing.JTable tablaCC;
    public static javax.swing.JTable tablaCC1;
    public static javax.swing.JTable tablaCC2;
    public static javax.swing.JTable tablaClientes;
    private javax.swing.JTable tablaCotizacionP;
    private javax.swing.JTable tablaCotizacionP1;
    public static javax.swing.JTable tablaDP;
    public static javax.swing.JTable tablaDescripcionP;
    public static javax.swing.JTable tablaDescripcionP1;
    public static javax.swing.JTable tablaDescripcionP2;
    public static javax.swing.JTable tablaEmpleados;
    public static javax.swing.JTable tablaInventario;
    private javax.swing.JTable tablaListaNomina;
    public static javax.swing.JTable tablaMaquinaria;
    public static javax.swing.JTable tablaObras;
    public static javax.swing.JTable tablaObras1;
    private javax.swing.JTable tablaObras2;
    public static javax.swing.JTable tablaObras3;
    private javax.swing.JTable tablaObras4;
    public static javax.swing.JTable tablaObras5;
    private javax.swing.JTable tablaObras6;
    public static javax.swing.JTable tablaObras7;
    private javax.swing.JTable tablaObras8;
    private javax.swing.JTable tablaPD;
    public static javax.swing.JTable tablaProveedores;
    public static javax.swing.JTable tablaProveedores1;
    private javax.swing.JTable tablaProveedores2;
    public static javax.swing.JTable tablaRendimientoC;
    private javax.swing.JLabel totalCompra;
    public static javax.swing.JTextField txtBuscar1;
    public static javax.swing.JTextField txtBuscarClientes;
    public static javax.swing.JTextField txtBuscarEmpleado;
    public static javax.swing.JTextField txtBuscarMaquinaria;
    public static javax.swing.JTextField txtBuscarObras;
    public static javax.swing.JTextField txtBuscarObras1;
    public static javax.swing.JTextField txtBuscarObras2;
    public static javax.swing.JTextField txtBuscarObras3;
    public static javax.swing.JTextField txtBuscarObras4;
    public static javax.swing.JTextField txtBuscarOrden;
    public static javax.swing.JTextField txtBuscarProducto;
    public static javax.swing.JTextField txtBuscarProveedor;
    private javax.swing.JTextField txtCombustibleR;
    private javax.swing.JTextField txtCotizacion;
    private javax.swing.JTextField txtCotizacion1;
    private javax.swing.JTextField txtFolioOrden;
    private javax.swing.JTextField txtFolioPago;
    private javax.swing.JTextField txtKMLR;
    private javax.swing.JTextField txtKMMR;
    private javax.swing.JTextField txtKMR;
    private javax.swing.JTextField txtLitrosR;
    private javax.swing.JTextField txtMonedaR;
    private javax.swing.JTextField txtOdometroR;
    public static javax.swing.JTextField txtVehiculoR;
    public static javax.swing.JTextField txtVehiculoR1;
    private javax.swing.JPanel verProductosObra;
    // End of variables declaration//GEN-END:variables
}
