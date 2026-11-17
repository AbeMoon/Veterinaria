public class Gato extends Mascota {

    public Gato(String nombre, int edad, double peso) {
        super(nombre, edad, peso, Especie.GATO);
    }
    
    @Override 

    public String sonido(){
        return "Miau";
    }

}
