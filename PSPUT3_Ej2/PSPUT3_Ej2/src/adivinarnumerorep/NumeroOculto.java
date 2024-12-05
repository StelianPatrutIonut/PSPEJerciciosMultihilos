package adivinarnumerorep;
// Paquete que organiza el código.

import java.util.Random;
// Importa la clase Random para generar números aleatorios.

public class NumeroOculto {
    // Clase que representa el número a adivinar y mantiene información sobre las partidas.

    private final int MIN_NUM_PARTIDA = 1;
    private final int MAX_NUM_PARTIDA = Integer.MAX_VALUE;
    // Constantes que definen los límites inferior y superior para el número de partida.

    private int numPartida = MIN_NUM_PARTIDA;
    // Número de partida actual, comienza en el límite inferior.

    private int numOculto;
    // Número oculto que los hilos concursantes intentarán adivinar.


    // Instancia de Random para generar números aleatorios.

    public NumeroOculto() {
        this.generaNumeroOculto();
        // Constructor que inicializa la instancia de NumeroOculto generando un nuevo número oculto.
    }

    private void generaNumeroOculto() {
        Random rand = new Random();
        this.numOculto = rand.nextInt(101);
        // Genera un número aleatorio entre 0 y 100 y lo asigna como el número oculto.
        System.out.printf("[[Partida: %d, creado número oculto: %d]]\n",
                numPartida, this.numOculto);
        // Imprime un mensaje indicando la partida actual y el número oculto recién generado.
    }

    private void nuevaPartida() {
        if (this.numPartida < MAX_NUM_PARTIDA) {
            this.numPartida++;
        } else {
            this.numPartida = MIN_NUM_PARTIDA;
        }
        // Incrementa el número de partida o lo reinicia si alcanza el límite superior.
        this.generaNumeroOculto();
        // Genera un nuevo número oculto para la siguiente partida.
    }

    synchronized public int getNumPartida() {
        return this.numPartida;
        // Método sincronizado que devuelve el número de partida actual.
    }

    synchronized public int propuestaNumero(int partida, int num) {
        int result = 0;   // Por defecto: no adivinado
        // Inicializa el resultado como no adivinado.

        if (partida != this.numPartida) {
            result = -1;  // Otro ha adivinado, es decir, ya se está en otra partida.
        } else if (num == this.numOculto) {
            result = 1;
            this.nuevaPartida();
            // Si el número propuesto es igual al número oculto, se ha adivinado y devuelve 1. Luego, inicia una nueva partida.
        }

        return result;
        // Devuelve el resultado de la propuesta.
    }
}
