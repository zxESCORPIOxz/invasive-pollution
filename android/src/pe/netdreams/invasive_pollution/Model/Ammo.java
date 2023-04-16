package pe.netdreams.invasive_pollution.Model;

public class Ammo {
    public int id;
    public String nombre;
    public int recurso;
    public int damage;
    public int precio;
    public int estado;

    public Ammo(int id, String nombre, int recurso, int damage, int precio, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.recurso = recurso;
        this.damage = damage;
        this.precio = precio;
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
