package Objetos;
public class Cliente {
    private String matricula, nombre, apellido, telefono, direccion, empresa, email;
    public Cliente(){
        
    }
    
    public Cliente(String matricula, String nombre, String apellido, String telefono, String direccion, String empresa, String email){
        this.matricula=matricula;
        this.nombre=nombre;
        this.apellido=apellido;
        this.telefono=telefono;
        this.direccion=direccion;
        this.empresa=empresa;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}