package pasoporpuente;

import java.util.Random;

public class PasoPorPuente {
/*Ejercicio 6 Queremos hacer un sistema que controla el paso de personas por un puente, siempre en la misma dirección,
para que se cumplen las siguientes restricciones. No pueden pasar más de 3 personas a la vez, y no puede haber más de 200kg
de peso en ningún momento. Para realizar la simulación, se modelan las personas como hilos. El tiempo entre la llegada de dos
 personas es aleatorio entre 1 y 30s, y para atravesar el puente, entre 10 y 50s de tiempo aleatorio. Las personas tienen un peso
  aleatorio entre 40 y 120kg. En un objeto de la clase Puente se guarda el estado compartido por todos los hilos: el número de personas
   que están cruzando el puente, y su peso total. Este objeto se pasa a todos los hilos de la clase Persona, en su constructor. Estos
    lo utilizan como objeto de bloqueo.  PISTA: Cuando una persona intenta cruzar el puente, necesita exclusividad para comprobar si
    efectivamente está autorizado para pasar (por su peso y por el nº de personas), y si no, se tendrá que quedar esperando hasta que le dejen.
    Por otro lado, una persona que intenta salir del puente, necesita exclusividad para operar con las variables de nº de personas y peso,
    y cuando salga, notificar a los demás para que despierten. */
public static void main(String[] args) {
    // Método principal que inicia la simulación.

    final Puente puente = new Puente();
    // Instancia del objeto Puente que comparte el estado entre los hilos.

    int tMinParaLlegadaPersona = 1;
    int tMaxParaLlegadaPersona = 30;
    int tMinPaso = 10;
    int tMaxPaso = 50;
    int minPesoPersona = 40;
    int maxPesoPersona = 120;
    // Parámetros de tiempo y peso para la simulación.

    System.out.println(">>>>>>>>>>>> Comienza simulación.");
    // Mensaje que indica el inicio de la simulación.
    Random aleatorios = new Random();
    // Instancia de la clase Random para generar valores aleatorios.
    int idPersona = 1;
    // Identificador para cada persona.

    while (true) {
        // Bucle infinito que simula la llegada continua de personas.

        int tParaLlegadaPersona = tMinParaLlegadaPersona + aleatorios.nextInt(
                tMaxParaLlegadaPersona - tMinParaLlegadaPersona + 1);
        // Calcula el tiempo hasta que llega la próxima persona de forma aleatoria.
        int pesoPersona = minPesoPersona + aleatorios.nextInt(
                maxPesoPersona - minPesoPersona + 1);
        // Asigna un peso aleatorio a la persona.

        System.out.printf("Siguiente persona llega en %d segundos.\n",
                tParaLlegadaPersona);
        // Muestra el mensaje de llegada de la próxima persona.

        try {
            Thread.sleep(1000 * tParaLlegadaPersona);
            // Pausa el hilo principal para simular la llegada de la próxima persona.
        } catch (InterruptedException ex) {
            System.out.printf("Interrumpido proceso principal");
        }

        Thread hiloPersona = new Thread(new Persona(
                puente, pesoPersona, tMinPaso, tMaxPaso, "P" + idPersona));
        // Crea un nuevo hilo (Persona) con el objeto Puente y los parámetros de la persona.
        hiloPersona.start();
        // Inicia el hilo de la persona.

        idPersona++;
        // Incrementa el identificador de la persona.
    }
}
}
