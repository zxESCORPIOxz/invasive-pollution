package pe.netdreams.invasive_pollution.Utils;

public class Idioma {
    private String name;
    private int image;

    public Idioma() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Idioma(String name, int image) {
        this.name = name;
        this.image = image;
    }
}
