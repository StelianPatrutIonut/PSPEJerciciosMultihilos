package adivinarnumerorep;

public class AdivinarNumeroRep {
/*Igual que la actividad propuesta anterior pero se juega partida tras partida. A cada partida se le asigna un identificador
 distinto, que es un número que empieza con 1 y se va incrementando. Si un hilo quiere jugar, pero no sabe cuál es la partida
  en curso, puede obtener su identificador llamando a un método (para implementar) de la clase NumeroOculto (getNumPartida()).
  Cuando los hilos proponen un número con el método propuestaNumero, indican además el identificador de la partida, porque puede
  haber hilos que, al menos una vez (no debería ser más de una), intenten adivinar el número de una partida que ya ha adivinado otro
   hilo. Si ese es el caso, el valor devuelto por el método propuestaNumero indica que la partida ya ha terminado, pero que puede jugar
   a la partida en curso, después de obtener su identificador. */
    private final static int NUM_HILOS_CONCURSANTES = 5;

    public static void main(String[] args) {
        final NumeroOculto numOculto = new NumeroOculto();
        Thread[] hilosConcursantes = new Thread[NUM_HILOS_CONCURSANTES];

        for (int i = 0; i < NUM_HILOS_CONCURSANTES; i++) {
            hilosConcursantes[i] = new Thread(new HiloConcursante(numOculto, "" + i));
        }
        for (Thread t : hilosConcursantes) {
            t.start();
        }
    }

}
