import java.time.LocalDate;

public class Consulta {
    
    private int id;
    private Duenio duenio;
    private Mascota mascota;
    private LocalDate fecha;
    private String diagnostico;
    private  double dosis;

    public Consulta(int id, Duenio duenio, Mascota mascota, LocalDate fecha,
        String diagnostico, double dosis) throws DosisInvalidaException{

        if(dosis < 0.1 || dosis > 10.0){

            throw new DosisInvalidaException(
                "La dosis debe ser entre 0.1 y 10.0 mL"
            );

        }

        this.id = id;
        this.duenio = duenio;
        this.mascota = mascota;
        this.fecha = fecha;
        this.diagnostico = diagnostico;
        this.dosis = dosis;
    }

    public int getId() {
        return id;
    }

    public Duenio getDuenio() {
        return duenio;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public double getDosis() {
        return dosis;
    }

    @Override 
    public String toString(){

        return "Consulta: " + id +
               "\nDueño: " + duenio.getNombre() +
               "\nMascota: " + mascota.getNombre() +
               "\nEspecie: " + mascota.getEspecie() +
               "\nFecha: " + fecha +
               "\nDiagnostico: " + diagnostico +
               "\nDosis: " + dosis + " mL";

    }

}
