package filosofosV1;

public class CincoFilosofos {
/*Ejercicio 5 Implementar una solución sencilla al problema de la comida de los 5 filósofos de Dijkstra que evite el interbloqueo.
  Cinco filósofos pasan la vida pensando en una mesa redonda y solo dejan de pensar de vez en cuando para comer de un cuenco de espaguetis.
   Para ello deben tomar los palillos que tienen a ambos lados, primero uno y luego otro.  Por supuesto, no pueden coger un palillo si lo está
    utilizando otro filósofo. Entonces deben esperar a que termine y lo suelte. Una vez tomado un palillo, no lo sueltan hasta que no consiguen el
     otro palillo. Entonces comen y, cuando han terminado de comer, dejan ambos palillos de nuevo en la mesa.  Se recomienda numerar los filósofos de 0 a 4.
      El filósofo i come con los palillos i (a su izquierda) e (i+1) mod 5 (a su derecha). Ejercicio 5.1 Realiza la misma funcionalidad pero utilizando semáforos. */
    final static int NUM_FILOSOFOS = 5;

    final static int MAX_TIEMPO_SIMUL = 20000;

    public static void main(String[] args) {

        Palillo[] palillos = new Palillo[NUM_FILOSOFOS];
        Thread[] filosofos = new Thread[NUM_FILOSOFOS];
        // Arreglos para almacenar los objetos Palillo y los hilos Filosofo.

        // Inicialización de los palillos.
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            palillos[i] = new Palillo(Integer.toString(i));
        }

        // Creación de los hilos Filosofo con asignación de palillos.
        for (int numFilosofo = 0; numFilosofo < NUM_FILOSOFOS; numFilosofo++) {
            Palillo primerPalillo, segundoPalillo;
            int numSiguienteFilosofo = numFilosofo + 1;
            if (numFilosofo < NUM_FILOSOFOS - 1) {
                primerPalillo = palillos[numFilosofo];
                segundoPalillo = palillos[numSiguienteFilosofo % NUM_FILOSOFOS];
            } else {
                primerPalillo = palillos[numSiguienteFilosofo % NUM_FILOSOFOS];
                segundoPalillo = palillos[numFilosofo];
            }
            filosofos[numFilosofo] = new Thread(new Filosofo(Integer.toString(numFilosofo), primerPalillo, segundoPalillo)
            );
        }

        // Inicio de la ejecución de los hilos Filosofo.
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            filosofos[i].start();
        }

        try {
            // Pausa del programa principal para la simulación.
            Thread.sleep(MAX_TIEMPO_SIMUL);

            // Interrupción de los hilos Filosofo para terminar la simulación.
            for (int i = 0; i < NUM_FILOSOFOS; i++) {
                filosofos[i].interrupt();
            }

            System.out.printf("**** Terminada la simulación tras %d milisegundos.\n", MAX_TIEMPO_SIMUL);

        } catch (InterruptedException ex) {
            System.out.println("ERROR: interrrumpido programa principal de los cinco filósofos.");
            ex.printStackTrace();
        }
    }

}
