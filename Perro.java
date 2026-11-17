public class Perro extends Mascota{

    public Perro(String nombre, int edad, double peso) {
        super(nombre, edad, peso, Especie.PERRO);
    }

    @Override 

    public String sonido(){
        return "Guau guau";
    }
    
}
