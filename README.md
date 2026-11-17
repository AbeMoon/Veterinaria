Mini Clínica Veterinaria
Sistema de consola desarrollado en Java para registrar dueños, mascotas
y consultas veterinarias. Las consultas se almacenan en un archivo CSV
llamado consultas.csv.
Objetivos del proyecto
- Aplicar programación orientada a objetos mediante clases, herencia,
  abstracción y polimorfismo.
- Administrar dueños mediante un Map.
- Almacenar mascotas y consultas mediante listas (List).
- Validar dosis mediante una excepción personalizada.
- Generar reportes utilizando Streams.
- Guardar y cargar consultas desde un archivo CSV con
  try-with-resources.
Estructura del proyecto
MiniClinicaVeterinaria/
├── Main.java
├── Mascota.java
├── Perro.java
├── Gato.java
├── Duenio.java
├── Consulta.java
├── Especie.java
├── DosisInvalidaException.java
├── ClinicaVeterinaria.java
└── consultas.csv
Descripción de las clases
Mascota
Clase abstracta que representa las características generales de una
mascota.

Atributos: - nombre: nombre de la mascota. - edad: edad de la
mascota. - peso: peso de la mascota, declarado como private. -
especie: especie de la mascota.

Declara el método abstracto sonido(), que debe implementarse en cada
subclase.
Perro y Gato
Subclases de Mascota. Cada una implementa sonido() con el sonido
correspondiente.
- Perro: devuelve "Guau guau".
- Gato: devuelve "Miau miau".
Estas clases permiten aplicar herencia y polimorfismo.
Duenio
Representa a la persona responsable de una o varias mascotas.

Atributos: - id: identificador del dueño. - nombre: nombre del
dueño. - telefono: teléfono de contacto.
Especie
Enum que define las especies admitidas:
public enum Especie {
    PERRO,
    GATO
}
Consulta
Representa una consulta veterinaria y relaciona un dueño con una
mascota.

Atributos: - id: identificador de la consulta. - duenio: dueño
asociado a la consulta. - mascota: mascota atendida. - fecha: fecha
de la consulta. - diagnostico: descripción del diagnóstico. - dosis:
dosis registrada.

El constructor valida que la dosis esté dentro del rango de ejemplo
establecido en el programa: de 0.1 a 10.0 mL. Este rango es
únicamente una validación didáctica del ejercicio y no constituye una
recomendación médica o veterinaria.

Si la dosis está fuera del rango, se lanza DosisInvalidaException.
DosisInvalidaException
Excepción personalizada que se utiliza cuando se intenta registrar una
consulta con una dosis fuera del rango permitido por el programa.
ClinicaVeterinaria
Clase encargada de administrar los registros de la clínica.

Colecciones utilizadas:

  Colección                Uso
  Map<Integer, Duenio>   Almacenar dueños identificados por su ID.
List<Mascota>          Almacenar las mascotas registradas.
List<Consulta>         Almacenar las consultas realizadas.

Métodos principales: - registrarDuenio(): registra un dueño. -
buscarDuenio(): busca un dueño por su ID. - registrarMascota():
agrega una mascota. - registrarConsulta(): agrega una consulta. -
guardarConsultas(): escribe las consultas en consultas.csv. -
cargarConsultas(): lee las consultas guardadas en el CSV. -
generarReporte(): muestra estadísticas y el detalle de las consultas
mediante Streams.
Archivo CSV
El archivo consultas.csv almacena los datos de las consultas. Su
encabezado es:
id,duenioId,duenio,mascota,especie,edad,peso,fecha,diagnostico,dosis
Cada fila representa una consulta. El archivo se escribe con
BufferedWriter y se lee con BufferedReader, ambos dentro de bloques
try-with-resources para cerrar los recursos automáticamente.
Nota: la implementación básica sustituye las comas de los campos de
texto por espacios para evitar que se desplacen las columnas. Para
admitir comas, comillas y saltos de línea dentro de los datos, sería
conveniente utilizar un parser CSV.

Streams utilizados en el reporte
El reporte utiliza Streams para:
- Agrupar consultas por especie con Collectors.groupingBy().
- Contar las consultas de cada especie con Collectors.counting().
- Calcular el promedio de dosis con mapToDouble() y average().
- Recorrer y mostrar las consultas con forEach().
Ejecución
1. Crear el proyecto Java y agregar las clases indicadas en la
   estructura.
2. Ejecutar Main.java.
3. Registrar dueños y mascotas.
4. Crear consultas con una dosis válida.
5. Seleccionar la opción de guardar para generar o actualizar
   consultas.csv.
6. Ejecutar la carga del archivo para recuperar las consultas
   almacenadas.
7. Generar el reporte para consultar las estadísticas.
El archivo consultas.csv se crea en el directorio de trabajo desde el
que se ejecuta la aplicación.
Conceptos de Java aplicados
  Concepto                            Aplicación
  Abstracción                         Clase abstracta Mascota.

  Herencia                            Perro y Gato heredan de
Mascota.

  Polimorfismo                        Cada subclase implementa
sonido().

  Encapsulamiento                     Atributo peso privado y acceso
mediante getters/setters.

  Enum                                Enumeración Especie.

  Map                                 Registro de dueños por
identificador.

  List                                Registro de mascotas y consultas.

  Excepciones                         DosisInvalidaException para
validar dosis.

  Streams                             Agrupación, conteo y promedio para
el reporte.

  Persistencia                        Lectura y escritura del archivo
CSV.
try-with-resources                  Cierre automático de los flujos de
archivo.