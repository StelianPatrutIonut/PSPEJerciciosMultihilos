package adivinarnumero;
// Paquete que organiza el código.

import java.util.Random;
// Importa la clase Random para generar números aleatorios.

public class NumeroOculto {
    // Clase que representa el número a adivinar.

    private final int numOculto;
    // Número oculto que los hilos concursantes intentarán adivinar.

    private boolean numAdivinado = false;
    // Bandera que indica si el número ha sido adivinado.

    public NumeroOculto() {
        Random aleatorios = new Random();
        this.numOculto = aleatorios.nextInt(101);
        // Genera un número aleatorio entre 0 y 100 y lo asigna como el número oculto.
        System.out.printf("[[Creado número oculto: %d]]\n", this.numOculto);
        // Imprime un mensaje indicando que se ha creado el número oculto.
    }

    synchronized public int propuestaNumero(int num) {
        // Método sincronizado para evitar problemas de concurrencia.

        int result = 0;   // Por defecto: no adivinado
        // Inicializa el resultado como no adivinado.

        if (numAdivinado) {
            result = -1;
            // Si el número ya fue adivinado por otro hilo, devuelve -1.
        } else if (num == this.numOculto) {
            numAdivinado = true;
            result = 1;
            // Si el número propuesto es igual al número oculto, se ha adivinado y devuelve 1.
        }

        return result;
        // Devuelve el resultado de la propuesta.
    }
}
