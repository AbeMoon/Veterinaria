public abstract class Mascota{

    private String nombre;
    private int edad;
    private double peso;
    private Especie especie;

    public Mascota(String nombre, int edad, double peso, Especie especie) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.especie = especie;
    }

    public abstract String sonido();

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Especie getEspecie() {
        return especie;
    }

}