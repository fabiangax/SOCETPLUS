package Objetos;
public class Maquinaria {
    private String serie, marca, modelo, anio, placas;
    public Maquinaria(String serie, String marca, String modelo, String anio, String placas){
        this.serie=serie;
        this.marca=marca;
        this.modelo=modelo;
        this.anio=anio;
        this.placas=placas;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }
    
}
