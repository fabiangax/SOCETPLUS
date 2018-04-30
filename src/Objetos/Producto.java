package Objetos;
public class Producto {
    private String codigo, descripcion, existencia, precio;
    public Producto(String codigo, String descripcion, String existencia, String precio){
        this.codigo=codigo;
        this.descripcion=descripcion;
        this.existencia=existencia;
        this.precio=precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getExistencia() {
        return existencia;
    }

    public void setExistencia(String existencia) {
        this.existencia = existencia;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
    
}
