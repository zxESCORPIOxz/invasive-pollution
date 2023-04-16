package pe.netdreams.invasive_pollution.Model;

public class Nave {
    public int id;
    public String nombre;
    public int recurso;
    public int blindaje;
    public int vida;
    public int velocidad;
    public int precio;
    public int estado;

    public Nave(int id, String nombre, int recurso, int blindaje, int vida, int velocidad, int precio, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.recurso = recurso;
        this.blindaje = blindaje;
        this.vida = vida;
        this.velocidad = velocidad;
        this.precio = precio;
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRecurso() {
        return recurso;
    }

    public void setRecurso(int recurso) {
        this.recurso = recurso;
    }

    public int getBlindaje() {
        return blindaje;
    }

    public void setBlindaje(int blindaje) {
        this.blindaje = blindaje;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
