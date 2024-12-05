package adivinarnumero;

public class AdivinarNumero {
    /*Un hilo debe generar un número al azar entre cero y cien, que deben intentar adivinar otros diez hilos.
     Si un hilo acierta el número, debe terminar su ejecución inmediatamente. Y el resto de los hilos deben
     también terminar su ejecución en cuanto propongan un número y se les avise de que otro hilo ya ha acertado
     el número. Se propone utilizar una clase NumeroOculto con un método int propuestaNumero(int num) que devuelva
     los siguientes valores: a) -1 si el juego ya ha terminado porque un hilo ha adivinado el número. b) 1 si el número
     propuesto (num) es el número oculto. c) 0 en otro caso. No hace falta crear una clase para el hilo que genera el número
     al azar. Es el hilo inicial (principal), que ejecuta el método main, y que crea el resto de los hilos.*/
    private final static int NUM_HILOS_CONCURSANTES = 10;
    // Número constante de hilos concursantes.

    public static void main(String[] args) {
        final NumeroOculto numOculto = new NumeroOculto();
        // Se crea una instancia de la clase NumeroOculto que representa el número a adivinar.

        Thread[] hilosConcursantes = new Thread[NUM_HILOS_CONCURSANTES];
        // Arreglo que almacenará los hilos concursantes.

        for (int i = 0; i < NUM_HILOS_CONCURSANTES; i++) {
            hilosConcursantes[i] = new Thread(new HiloConcursante(numOculto, "" + i));
            // Se crean los hilos concursantes y se les asigna una instancia de NumeroOculto.
        }

        for (Thread t : hilosConcursantes) {
            t.start();
            // Se inicia cada hilo concursante.
        }
    }

}
