import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class ClinicaVeterinaria {
    
    private Map<Integer, Duenio> duenios = new HashMap<>();
    private List<Mascota> mascotas = new ArrayList<>();
    private List<Consulta> consultas = new ArrayList<>();
    
    private final Path ruta = Path.of("consultas.csv");

    //Empezamos 
    //Registro de dueños

    public void registrarDuenio(Duenio duenio){
        duenios.put(duenio.getId(), duenio);
    }

    //Busqueda de dueños
    public Duenio buscaDuenio(int id){
        return duenios.get(id);
    }

    //Registrar mascota
    public void registrarMascota(Mascota mascota){
        mascotas.add(mascota);
    }

    //Registramos consultas
    public void registrarConsulta(Consulta consulta){
        consultas.add(consulta);
    }

    //Obtener mascotas
    public List<Mascota> geMascotas(){
        return mascotas;
    }

    //Aca obtenemos consultas
    public List<Consulta> geConsultas(){
        return consultas;
    }

    //Aca guardamos todo en el CSV
    public void guardarConsultas(){

        try (BufferedWriter bw =
            Files.newBufferedWriter(ruta)
        ){
            bw.write("Id, dueñoId, Dueño, mascota, especie, edad, peso, fecha, diagnostico, dosis");
            bw.newLine();

            for(Consulta c : consultas){

                Mascota m = c.getMascota();
                Duenio d = c.getDuenio();

                String linea = String.join(",",
                    String.valueOf(c.getId()),
                    String.valueOf(d.getId()),
                    limpiar(d.getNombre()),
                    limpiar(m.getNombre()),
                    m.getEspecie().name(),
                    String.valueOf(m.getEdad()),
                    String.valueOf(m.getPeso()),
                    c.getFecha().toString(),
                    limpiar(c.getDiagnostico()),
                    String.valueOf(c.getDosis())
                );

            bw.write(linea);
            bw.newLine();

            }

            System.out.println("Consultas guardadas correctamente.");

        }catch(IOException e){
            System.out.println("Error al guardar: " + e.getMessage());
        }

    }

     private String limpiar(String texto) {
        return texto.replace(",", " ");
    }

    //Aca se cargan las consultas
    public void cargarConsultas() {

        if(!Files.exists(ruta)){
            System.out.print("El archivo no existe");
            return;
        }

        try (BufferedReader br =
                Files.newBufferedReader(ruta)
        ) {

            String linea;

            br.readLine();

            while((linea = br.readLine()) != null){

                String[] datos = linea.split(",", -1);

                int id = Integer.parseInt(datos[0]);
                int idDueno = Integer.parseInt(datos[1]);

                String nombreDuenio = datos[2];
                String nombreMascota = datos[3];

                Especie especie = Especie.valueOf(datos[4]);

                int edad = Integer.parseInt(datos[5]);
                double peso = Double.parseDouble(datos[6]);

                LocalDate fecha = LocalDate.parse(datos[7]);

                String diagnostico = datos[8];
                double dosis = Double.parseDouble(datos[9]);

                Duenio duenio = duenios.get(idDueno);

                if(duenio == null){
                    duenio = new Duenio(
                        idDueno, nombreDuenio, "Sin telefono");
                    registrarDuenio(duenio);
                }

                Mascota mascota;

                if (especie == Especie.PERRO){
                    mascota = new Perro(nombreMascota, edad, peso);
                } else {
                    mascota = new Gato(nombreMascota, edad, peso);
                }

                final String nombre = nombreMascota;

                //Aca registramos una mascota si no existiera

                boolean existe = mascotas.stream()
                    .anyMatch(m -> m.getNombre().equals(nombre)
                    && m.getEspecie() == especie);

                if(!existe){
                    registrarMascota(mascota);
                }

                Consulta consulta = new Consulta(
                        id, duenio, mascota, fecha,
                        diagnostico, dosis
                );

                consultas.add(consulta);

            }

            System.out.println("Consultas cargadas correctamente.");

        } catch (IOException | DosisInvalidaException |
                IllegalArgumentException e){
                    
            System.out.println("Error al cargar: " + e.getMessage());
        }

    }

     // Generamos reportes utilizando Streams
    public void generarReporte() {

        System.out.println("\n===== REPORTE DE LA CLÍNICA =====");

        System.out.println("Total de dueños: " + duenios.size());

        System.out.println("Total de mascotas: " + mascotas.size());

        System.out.println("Total de consultas: " + consultas.size());

        // Contar consultas por especie
        Map<Especie, Long> porEspecie = consultas.stream()
                .collect(Collectors.groupingBy(
                        c -> c.getMascota().getEspecie(),
                        Collectors.counting()
                ));

        System.out.println("\nConsultas por especie:");

        porEspecie.forEach((especie, cantidad) ->
                System.out.println(especie + ": " + cantidad)
        );

        // Promedio de dosis
        double promedio = consultas.stream()
                .mapToDouble(Consulta::getDosis)
                .average()
                .orElse(0.0);

        System.out.println("\nPromedio de dosis: " + promedio + " mL");

        // Mostrar todas las consultas
        System.out.println("\nDetalle de consultas:");

        consultas.stream()
                .forEach(System.out::println);
    }

}
