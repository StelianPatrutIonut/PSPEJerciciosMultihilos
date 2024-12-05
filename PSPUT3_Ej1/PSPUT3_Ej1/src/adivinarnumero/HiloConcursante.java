package adivinarnumero;
// Paquete que organiza el código.

import java.util.Random;
// Importa la clase Random para generar números aleatorios.

public class HiloConcursante implements Runnable {
    // Clase que implementa la interfaz Runnable para representar los hilos concursantes.

    private final NumeroOculto numOculto;
    // Instancia de NumeroOculto que representa el número a adivinar.

    private final String id;
    // Identificador único del hilo concursante.

    private Random r = new Random();
    // Instancia de Random para generar números aleatorios.

    HiloConcursante(NumeroOculto numOculto, String id) {
        this.numOculto = numOculto;
        this.id = id;
        // Constructor que inicializa la instancia de NumeroOculto y el identificador del hilo.
    }

    @Override
    public void run() {
        // Método run() que contiene la lógica principal del hilo concursante.

        System.out.printf(">> Hilo %s comienza.\n", this.id);
        // Imprime un mensaje indicando que el hilo concursante ha comenzado.

        boolean juegoTerminado = false;
        // Variable que indica si el juego ha terminado.

        while (!juegoTerminado) {
            // Bucle mientras el juego no haya terminado.

            int num = r.nextInt(101);
            // Genera un número aleatorio entre 0 y 100.
            System.out.printf("Hilo %s propone número %d.\n", this.id, num);
            // Imprime el número propuesto por el hilo concursante.

            int resultado = numOculto.propuestaNumero(num);
            // Llama al método propuestaNumero de la instancia de NumeroOculto.

            if (resultado == -1) {
                juegoTerminado = true;
                System.out.printf("Hilo %s: otro hilo ha acertado.\n", this.id);
                // Si otro hilo ha acertado, termina el juego.
            } else if (resultado == 1) {
                juegoTerminado = true;
                System.out.printf("¡¡¡ Hilo %s acierta con el número: %d !!!\n", this.id, num);
                // Si el hilo concursante acierta, termina el juego y muestra un mensaje de éxito.
            }
        }
        System.out.printf("## Hilo %s termina.\n", this.id);
        // Imprime un mensaje indicando que el hilo concursante ha terminado.
    }
}
