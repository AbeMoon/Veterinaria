import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        ClinicaVeterinaria clinica = new ClinicaVeterinaria();

        // Registrar dueños
        Duenio d1 = new Duenio(1, "Carlos", "492492492");
        Duenio d2 = new Duenio(2, "Ana", "1111");

        clinica.registrarDuenio(d1);
        clinica.registrarDuenio(d2);

        // Registrar mascotas
        Mascota perro = new Perro("Juam", 3, 15.5);
        Mascota gato = new Gato("Michi", 2, 4.2);

        clinica.registrarMascota(perro);
        clinica.registrarMascota(gato);

        // Mostrar sonidos (polimorfismo)
        System.out.println(perro.sonido());
        System.out.println(gato.sonido());

        // Registrar consultas
        try {

            Consulta c1 = new Consulta(
                    1, d1, perro,
                    LocalDate.now(),
                    "Vacunación",
                    2.5
            );

            Consulta c2 = new Consulta(
                    2, d2, gato,
                    LocalDate.now(),
                    "Revisión general",
                    1.0
            );

            clinica.registrarConsulta(c1);
            clinica.registrarConsulta(c2);

        } catch (DosisInvalidaException e) {
            System.out.println(e.getMessage());
        }

        // Guardar consultas
        clinica.guardarConsultas();

        // Generar reporte
        clinica.generarReporte();

    }
}