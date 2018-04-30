/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Otros.ConexionSQLServer;
import Principal.Home;
import static Principal.Home.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marco
 */
public class Utilidades {
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
    Connection c = ConexionSQLServer.Conectar();
    int cont = 0, rowNomina = 0, rowPD = 0, rowCompraP, rowCombustible = 0;
    String[] fechasCombustible = new String[999], foliosCombustible = new String[999];
    public void clickEnObra() {
        //Tabla de obras
        DTMO = new DefaultTableModel(titulosObras, 0);
        tablaObras.setModel(DTMO);
        try {
            Statement smt = c.createStatement();
            ResultSet res = smt.executeQuery("select * from OBRAS");
            if (res.next()) {
                do {
                    Object fila[] = {String.valueOf(Integer.parseInt(res.getString("IDObra"))), res.getString("NombreObra"), res.getString("Cliente"), res.getString("FechaIni"), res.getString("FechaFin"), res.getString("Presupuesto"), res.getString("Ubicacion"), res.getString("TipoDeObra"), res.getString("Encargado"), res.getString("Estatus"), res.getString("Descripcion")};
                    DTMO.addRow(fila);
                } while (res.next());
                tablaClientes.setModel(DTMO);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        //Tabla de Obras/Seguimientos
        String titulosObraS[] = {"Matricula", "Nombre", "Cliente"};
        DTMC = new DefaultTableModel(titulosObraS, 0);
        tablaObras1.setModel(DTMC);
        try {
            Statement smt = c.createStatement();
            ResultSet res = smt.executeQuery("select IDObra,NombreObra,cliente from OBRAS");
            if (res.next()) {
                do {
                    Object fila[] = {String.valueOf(Integer.parseInt(res.getString("IDObra"))), res.getString("NombreObra"), res.getString("Cliente")};
                    DTMC.addRow(fila);
                } while (res.next());
                tablaObras1.setModel(DTMC);
            }
        } catch (SQLException e) {
            System.out.println("Error");
        }
        DTMC = new DefaultTableModel(titulosObraS, 0);
        tablaObras3.setModel(DTMC);
        try {
            Statement smt = c.createStatement();
            ResultSet res = smt.executeQuery("select IDObra,NombreObra,cliente from OBRAS");
            if (res.next()) {
                do {
                    Object fila[] = {String.valueOf(Integer.parseInt(res.getString("IDObra"))), res.getString("NombreObra"), res.getString("Cliente")};
                    DTMC.addRow(fila);
                } while (res.next());
                tablaObras3.setModel(DTMC);
            }
        } catch (SQLException e) {
            System.out.println("Error");
        }
        DTMC = new DefaultTableModel(titulosObraS, 0);
        tablaObras5.setModel(DTMC);
        try {
            Statement smt = c.createStatement();
            ResultSet res = smt.executeQuery("select IDObra,NombreObra,cliente from OBRAS");
            if (res.next()) {
                do {
                    Object fila[] = {String.valueOf(Integer.parseInt(res.getString("IDObra"))), res.getString("NombreObra"), res.getString("Cliente")};
                    DTMC.addRow(fila);
                } while (res.next());
                tablaObras5.setModel(DTMC);
            }
        } catch (SQLException e) {
            System.out.println("Error");
        }
        panelHome.setVisible(false);
        panelClientes.setVisible(false);
        panelInventario.setVisible(false);
        panelObras.setVisible(true);
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
    }
    
    public void ClickEnClientes() {
        //Tabla de clientes
        DTMC = new DefaultTableModel(titulosClientes, 0);
        tablaClientes.setModel(DTMC);
        try {
            Statement smt = c.createStatement();
            ResultSet res = smt.executeQuery("select * from CLIENTES");
            if (res.next()) {
                do {
                    Object fila[] = {String.valueOf(Integer.parseInt(res.getString("IDCliente"))), res.getString("Nombre"), res.getString("Apellido"), res.getString("Telefono"), res.getString("Direccion"), res.getString("Empresa"), res.getString("E_Mail")};
                    DTMC.addRow(fila);
                } while (res.next());
                tablaClientes.setModel(DTMC);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        panelHome.setVisible(false);
        panelClientes.setVisible(true);
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
    }
    
    public void ClickEnCotizaciones() {
        panelHome.setVisible(false);
        panelClientes.setVisible(false);
        panelInventario.setVisible(false);
        panelObras.setVisible(false);
        panelMaquinaria.setVisible(false);
        panelCotizacion.setVisible(true);
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
    }
    
    public void ClickEnInventario() {
        //Tabla de inventario
        DTMP = new DefaultTableModel(titulosInventario, 0);
        tablaInventario.setModel(DTMP);
        try {
            Statement smt = c.createStatement();
            ResultSet res = smt.executeQuery("select * from PRODUCTOS");
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
        panelHome.setVisible(false);
        panelClientes.setVisible(false);
        panelInventario.setVisible(true);
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
    }
    
    public void ClickEnMaquinaria() {
        //Tabla de maquinaria
        DTMM = new DefaultTableModel(titulosMaquinaria, 0);
        tablaMaquinaria.setModel(DTMM);
        try {
            Statement smt = c.createStatement();
            ResultSet res = smt.executeQuery("select * from MAQUINARIAS");
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
        //Tabla control de combustible
        DTMCONTROL = new DefaultTableModel(titulosControl, 0);
        tablaRendimientoC.setModel(DTMCONTROL);
        try {
            Statement smt = c.createStatement();
            ResultSet res = smt.executeQuery("select * from CONTROL_DE_COMBUSTIBLE");
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("Fecha"), res.getString("FolioOrden"), res.getString("Vehiculo"), res.getString("Trabajador"), res.getString("Odometro"), res.getString("Kilometraje"), res.getString("Combustible"), res.getString("Litros"), res.getString("Moneda"), res.getString("kmLitro"), res.getString("kmMoneda"), res.getString("FolioPago")};
                    DTMCONTROL.addRow(fila);
                } while (res.next());
                tablaRendimientoC.setModel(DTMCONTROL);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        String titulosMaquinaria2[] = {"Serie", "Marca", "Modelo"};
        DTMM = new DefaultTableModel(titulosMaquinaria2, 0);
        tablaObras7.setModel(DTMM);
        try {
            Statement smt = c.createStatement();
            ResultSet res = smt.executeQuery("select * from MAQUINARIAS");
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
        panelHome.setVisible(false);
        panelClientes.setVisible(false);
        panelInventario.setVisible(false);
        panelObras.setVisible(false);
        panelMaquinaria.setVisible(true);
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
//        String dia = fechahoy.getText().charAt(0);
        System.out.println(lblFechaHoy.getText().charAt(1));
//        cbf1.setSelectedIndex(fechahoy.getText().charAt(0)+1);
        //inicializando los contenisdos de la tabla de combustibles        
        rowCombustible = tablaRendimientoC.getRowCount();
        for (int i = 0; i < rowCombustible; i++) {
            fechasCombustible[i] = String.valueOf(tablaRendimientoC.getValueAt(i, 0));
            foliosCombustible[i] = String.valueOf(tablaRendimientoC.getValueAt(i, 1));
        }
    }
    
    public void ClickEnContaduria() {
        panelHome.setVisible(false);
        panelClientes.setVisible(false);
        panelInventario.setVisible(false);
        panelObras.setVisible(false);
        panelMaquinaria.setVisible(false);
        panelCotizacion.setVisible(false);
        panelProveedor.setVisible(false);
        panelEmpleado.setVisible(false);
        panelReporte.setVisible(true);
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
    }
    
    public void ClickEnProveedores() {
        int i = 0;
        try {
            Statement smt = c.createStatement();
            ResultSet res = smt.executeQuery("select nombre from PROVEEDORES");
            if (res.next()) {
                do {
                    i++;
                } while (res.next());
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        String cbString[] = new String[i];
        i = 0;
        try {
            Statement smt = c.createStatement();
            ResultSet res = smt.executeQuery("select nombre from PROVEEDORES");
            if (res.next()) {
                do {
                    cbString[i] = res.getString("Nombre");
                    i++;
                } while (res.next());
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        DefaultComboBoxModel cbModel = new DefaultComboBoxModel(cbString);
        comboProveedores.setModel(cbModel);
        //Tabla de proveedores
        DTMPRO = new DefaultTableModel(titulosProveedor, 0);
        tablaProveedores.setModel(DTMPRO);
        try {
            Statement smt = c.createStatement();
            ResultSet res = smt.executeQuery("select * from PROVEEDORES");
            if (res.next()) {
                do {
                    Object fila[] = {res.getString("IDProveedor"), res.getString("Nombre"), res.getString("Telefono"), res.getString("Direccion"), res.getString("E_Mail")};
                    DTMPRO.addRow(fila);
                } while (res.next());
                tablaProveedores.setModel(DTMPRO);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
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
        panelHome.setVisible(false);
        panelClientes.setVisible(false);
        panelInventario.setVisible(false);
        panelObras.setVisible(false);
        panelMaquinaria.setVisible(false);
        panelCotizacion.setVisible(false);
        panelProveedor.setVisible(true);
        panelEmpleado.setVisible(false);
        panelReporte.setVisible(false);
        txtBuscarClientes.setText("Buscar por nombre o matricula de cliente...");
        txtBuscarObras1.setText("Buscar por cliente, nombre, o matricula de obra...");
        txtBuscarObras.setText("Buscar por cliente, nombre, estatus o matricula de obra...");
        txtBuscarEmpleado.setText("Buscar por nombre o matricula de empleado...");
        txtBuscarProveedor.setText("Buscar por nombre o matricula de proveedor...");
        txtBuscarOrden.setText("Buscar por folio de orden....");
        txtBuscar1.setText("Buscar por folio orden...");
        txtBuscarMaquinaria.setText("Buscar por serie, marca, modelo o placas...");
        txtBuscarProducto.setText("Buscar por descripción o código de producto...");
        txtBuscarOrden.setText("Buscar por folio de orden....");
    }
    
    public void ClickEnEmpleados() {
        //Tabla de empleados
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
                tablaClientes.setModel(DTME);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        panelHome.setVisible(false);
        panelClientes.setVisible(false);
        panelInventario.setVisible(false);
        panelObras.setVisible(false);
        panelMaquinaria.setVisible(false);
        panelCotizacion.setVisible(false);
        panelProveedor.setVisible(false);
        panelEmpleado.setVisible(true);
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
    }
}
