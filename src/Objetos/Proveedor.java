package Objetos;
public class Proveedor {
    private String matricula, nombre, telefono, direccion, email;
    public Proveedor(){
        
    }

    public Proveedor(String matricula, String nombre, String telefono, String direccion, String email){
        this.matricula=matricula;
        this.nombre=nombre;
        this.telefono=telefono;
        this.direccion=direccion;
        this.email=email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}