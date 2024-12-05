package estudiantesylibros;

import java.util.Random;





public class EstudiantesYLibros {
/*Hay cuatro estudiantes que comparten nueve libros. Los libros se pueden almacenar en un array Libro[] libros =
 Los estudiantes seleccionan dos libros al azar y, una vez obtenidos (para lo que podrían tener que esperar si, en ese momento,
  hay otro estudiante utilizando alguno de ellos), los utilizan durante un tiempo aleatorio de entre una y tres horas.
  Pasado este tiempo, los devuelven, de manera que puedan utilizarlos otros estudiantes, y descansan un tiempo aleatorio
  de entre una y dos horas. Entonces, vuelven a seleccionar otros dos libros, y continúa el ciclo.  La clase Libro puede
   no tener ninguna funcionalidad especial, más allá de tener un identificador, que puede utilizarse en los mensajes en
    los que se haga referencia al libro.  La simulación no tiene por qué ser en tiempo real. Se pueden seleccionar los
     tiempos al azar en minutos en lugar de horas, y esperar un milisegundo por cada minuto. Se entiende que los alumnos
      primero intentar coger un libro, y si lo consiguen ya cogen el otro. */
  private static final int NUM_LIBROS = 3;
  private static final int NUM_ESTUDIANTES = 6;

  public static void main(String[] args) {
    // Método principal que inicia la simulación.

    Libro[] libros = new Libro[NUM_LIBROS];
    // Arreglo que representa los libros disponibles.
    for (int i = 0; i < NUM_LIBROS; i++) {
      libros[i] = new Libro("Libro " + i);
      // Inicializa cada libro con un identificador único.
    }

    Thread[] estudiantes = new Thread[NUM_ESTUDIANTES];
    // Arreglo que representa a los estudiantes como hilos.
    for (int i = 0; i < NUM_ESTUDIANTES; i++) {
      estudiantes[i] = new Thread(new Estudiante(libros, "estudiante " + i));
      // Inicializa cada estudiante como un hilo y les asigna libros para compartir.
    }

    for (Thread e : estudiantes) {
      e.start();
      // Inicia cada hilo de estudiante.
    }
  }

}
