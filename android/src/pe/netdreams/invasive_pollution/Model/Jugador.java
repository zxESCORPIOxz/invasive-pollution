package pe.netdreams.invasive_pollution.Model;

public class Jugador {

    String nombre;
    int score;
    int total_coins;
    int max_lvl;

    String Token;

    public Jugador(int score, int total_coins, int max_lvl) {
        this.score = score;
        this.total_coins = total_coins;
        this.max_lvl = max_lvl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotal_coins() {
        return total_coins;
    }

    public void setTotal_coins(int total_coins) {
        this.total_coins = total_coins;
    }

    public int getMax_lvl() {
        return max_lvl;
    }

    public void setMax_lvl(int max_lvl) {
        this.max_lvl = max_lvl;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
