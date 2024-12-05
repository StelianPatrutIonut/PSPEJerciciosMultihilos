package filosofosV1;
// Paquete que organiza el código.

import java.util.Random;
// Importa la clase Random para generar números aleatorios.

public class Filosofo implements Runnable {
    // Clase que representa a un filósofo y su comportamiento.

    final static int MAX_TIEMPO_PENSAR = 500;
    final static int MAX_TIEMPO_COMER = 500;
    // Constantes que definen los límites de tiempo para pensar y comer en milisegundos.

    private final Random rand = new Random();
    // Instancia de la clase Random para generar números aleatorios.

    private final Palillo p1, p2;
    // Palillos asignados al filósofo.
    private final String id;
    // Identificador del filósofo.

    Filosofo(String id, Palillo p1, Palillo p2) {
        this.id = id;
        this.p1 = p1;
        this.p2 = p2;
        // Constructor que inicializa el identificador y los palillos asignados al filósofo.
    }

    @Override
    public void run() {
        // Método run que define el comportamiento del filósofo cuando se ejecuta como hilo.

        try {
            // Bloque try-catch para manejar excepciones.

            while (true) {
                // Bucle infinito que simula la vida continua del filósofo.

                int tiempoPensar = rand.nextInt(MAX_TIEMPO_PENSAR);
                // Genera un tiempo aleatorio para que el filósofo piense.
                System.out.printf("[...] Filósofo %s pensando durante %d milisegundos.\n", this.id, tiempoPensar);
                Thread.sleep(tiempoPensar);
                // Pausa el hilo durante el tiempo de pensamiento.

                synchronized (p1) {
                    // Bloquea el primer palillo.
                    synchronized (p2) {
                        // Bloquea el segundo palillo.
                        int tiempoComer = rand.nextInt(MAX_TIEMPO_COMER);
                        // Genera un tiempo aleatorio para que el filósofo coma.
                        System.out.printf("[O] Filósofo %s comiendo durante %d milisegundos.\n", this.id, tiempoComer);
                        Thread.sleep(tiempoComer);
                        // Pausa el hilo durante el tiempo de comer.
                        System.out.printf("[.] Filósofo %s termina de comer.\n", this.id);
                    }
                }
            }
        } catch (InterruptedException ex) {
            System.out.printf("Filósofo %s interrumpido.\n", this.id);
            // Mensaje que indica que el filósofo ha sido interrumpido.
        }
    }
}
