package realizaciontareas;
// Paquete que organiza el código.

import java.util.Random;
// Importa la clase Random para generar números aleatorios.

class ActividadA1 implements Runnable {
  // Clase que representa la tarea simulada A1 implementando la interfaz Runnable.

  private final int MAX_ESPERA = 50;
  private final int MAX_DURACION = 200;
  // Constantes que definen los límites de tiempo para la espera y la duración de la tarea.

  private final String nomHilo = "A1";
  // Identificador del hilo, que es "A1".

  Destornillador miDestornillador;
  Taladro miTaladro;
  // Instancias de las clases Destornillador y Taladro que se utilizarán en la tarea.

  ActividadA1(Destornillador destornillador, Taladro taladro) {
    this.miDestornillador = destornillador;
    this.miTaladro = taladro;
    // Constructor que inicializa los recursos (destornillador y taladro) necesarios para la tarea.
  }

  public String getNomHilo() {
    return nomHilo;
    // Método que devuelve el identificador del hilo.
  }

  @Override
  public void run() {
    Random r = new Random();
    // Instancia de la clase Random para generar números aleatorios.
    try {
      while (true) {
        // Bucle infinito que simula la ejecución continua de la tarea.

        int tEsperaInicio = r.nextInt(MAX_ESPERA) + 1;
        // Genera un tiempo de espera aleatorio antes de comenzar la tarea.
        System.out.printf("... Hilo %s tardará %d ms en comenzar.\n", this.getNomHilo(), tEsperaInicio);
        Thread.sleep(tEsperaInicio);
        // Pausa el hilo durante el tiempo de espera antes de comenzar la tarea.

        System.out.printf("> Hilo %s esperando para conseguir el destornillador.\n", this.getNomHilo());
        // Mensaje indicando que el hilo está esperando para adquirir el destornillador.
        synchronized (miDestornillador) {
          // Bloquea el destornillador para evitar que otros hilos lo utilicen simultáneamente.
          System.out.printf("> Hilo %s consigue el destornillador.\n", this.getNomHilo());

          System.out.printf("> Hilo %s esperando para conseguir el taladro.\n", this.getNomHilo());
          // Mensaje indicando que el hilo está esperando para adquirir el taladro.
          synchronized (miTaladro) {
            // Bloquea el taladro para evitar que otros hilos lo utilicen simultáneamente.
            System.out.printf("> Hilo %s consigue el taladro.\n", this.getNomHilo());

            int tDuracion = r.nextInt(MAX_DURACION) + 1;
            // Genera un tiempo de duración aleatorio para la tarea.
            System.out.printf(">> Hilo %s comienza su tarea, que durará %d ms.\n", this.getNomHilo(), tDuracion);
            Thread.sleep(tDuracion);
            // Pausa el hilo durante el tiempo de duración de la tarea simulada.

            System.out.printf("<< Hilo %s finaliza su tarea.\n", this.getNomHilo());
          }
          System.out.printf("< Hilo %s libera el taladro.\n", this.getNomHilo());
        }
        System.out.printf("< Hilo %s libera el destornillador.\n", this.getNomHilo());
        // Mensajes indicando que el hilo ha completado la tarea y ha liberado los recursos.
      }
    } catch (InterruptedException ex) {
      // Captura excepciones de interrupción.
    }
  }
}
