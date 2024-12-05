package adivinarnumerorep;
// Paquete que organiza el código.

import java.util.Random;
// Importa la clase Random para generar números aleatorios.

public class HiloConcursante implements Runnable {
    // Clase que implementa la interfaz Runnable para representar los hilos concursantes.

    private final NumeroOculto numOculto;
    // Instancia de NumeroOculto que representa el número a adivinar y mantiene información sobre las partidas.

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

        int numPartida = numOculto.getNumPartida();
        // Obtiene el número de partida actual.

        while (true) {
            // Bucle infinito que representa el intento constante del hilo por adivinar el número.

            try {
                Thread.sleep(1000);
                // Pausa el hilo durante un segundo para simular el tiempo entre propuestas de números.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
                // Captura excepciones de interrupción y lanza una RuntimeException.
            }

            int num = r.nextInt(101);
            // Genera un número aleatorio entre 0 y 100.
            System.out.printf("Hilo %s propone número %d para partida %d.\n", this.id, num, numPartida);
            // Imprime el número propuesto por el hilo concursante y la partida actual.

            int resultado = numOculto.propuestaNumero(numPartida, num);
            // Llama al método propuestaNumero de la instancia de NumeroOculto, proporcionando el número de partida y la propuesta.

            if (resultado == -1) {
                int numPartidaAnt = numPartida;
                numPartida = numOculto.getNumPartida();
                System.out.printf("Hilo %s: otro hilo ha acertado en partida %d, nueva partida: %d.\n",
                        this.id, numPartidaAnt, numPartida);
                // Si otro hilo ha acertado, actualiza el número de partida y muestra un mensaje.
            } else if (resultado == 1) {
                System.out.printf("¡¡¡ Hilo %s acierta con el número: %d en partida: %d !!!\n",
                        this.id, num, numPartida);
                numPartida = this.numOculto.getNumPartida();
                // Si el hilo concursante acierta, muestra un mensaje de éxito y actualiza el número de partida.
            }
        }
    }
}
