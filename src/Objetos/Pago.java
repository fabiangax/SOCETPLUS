package Objetos;
public class Pago {
    private String fechaOrden, folio, estado, cantidad, obra, fechaPago, proveedor;
    public Pago(String fechaOrden, String folio, String estado, String cantidad, String obra, String fechaPago, String proveedor){
        this.fechaOrden=fechaOrden;
        this.folio=folio;
        this.estado=estado;
        this.cantidad=cantidad;
        this.obra=obra;
        this.fechaPago=fechaPago;
        this.proveedor=proveedor;
    }

    public String getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getObra() {
        return obra;
    }

    public void setObra(String obra) {
        this.obra = obra;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    
}