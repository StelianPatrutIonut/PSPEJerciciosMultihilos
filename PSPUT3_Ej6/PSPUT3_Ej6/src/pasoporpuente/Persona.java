package pasoporpuente;
// Paquete que organiza el código.

import java.util.Random;
// Importa la clase Random para generar números aleatorios.

public class Persona implements Runnable {
    // Clase que representa a una persona y su comportamiento al intentar cruzar el puente.

    private final String idPersona; // Identificador único de la persona.
    private final int peso; // Peso de la persona.
    private final int tMinPaso, tMaxPaso; // Tiempo mínimo y máximo para cruzar el puente.
    private final Puente puente; // Objeto que representa el puente compartido.

    public int getPeso() {
        return peso;
        // Método que devuelve el peso de la persona.
    }

    public String getIdPersona() {
        return idPersona;
        // Método que devuelve el identificador único de la persona.
    }

    Persona(Puente puente, int peso, int tMinPaso, int tMaxPaso, String idP) {
        // Constructor que inicializa los atributos de la persona.
        this.puente = puente;
        this.peso = peso;
        this.tMinPaso = tMinPaso;
        this.tMaxPaso = tMaxPaso;
        this.idPersona = idP;
    }

    @Override
    public void run() {
        // Método run() que define el comportamiento del hilo al ejecutarse.

        System.out.printf(
                "~ %s de %d kg quiere cruzar, en puente %d kg, %d persona%s.\n",
                this.idPersona, this.peso, puente.getPeso(), puente.getNumPersonas(),
                puente.getNumPersonas() != 1 ? "s" : "");
        // Muestra un mensaje indicando la intención de la persona de cruzar el puente.

        System.out.printf(
                "~ %s intentar pasar. ¿Podrá?. \n", this.idPersona);
        // Muestra un mensaje indicando que la persona intentará pasar el puente.

        //Se quedará esperando a pasar
        this.puente.pasarPuente(this);
        // La persona intenta cruzar el puente, esperando si es necesario.

        System.out.printf(
                "> %s con peso %d puede cruzar, peso en puente %d con %d personas. \n",
                this.idPersona, this.getPeso(), this.puente.getPeso(),
                puente.getNumPersonas(), this.puente.getNumPersonas()
        );
        // Muestra un mensaje indicando que la persona ha obtenido la autorización para cruzar.

        Random aleatorios = new Random(); // Pasa al puente, y tarda un tiempo en cruzar
        int tiempoPaso = this.tMinPaso + aleatorios.nextInt(this.tMaxPaso - this.tMinPaso);
        // Genera un tiempo aleatorio de paso por el puente.

        try {
            System.out.printf("%s va a tardar tiempo %d en cruzar.\n",
                    this.idPersona, tiempoPaso);
            Thread.sleep(1000 * tiempoPaso);
            // Simula el tiempo que la persona tarda en cruzar el puente.
        } catch (InterruptedException ex) {
            System.out.printf("Interrupción mientras %s pasa.\n", this.idPersona);
        }

        System.out.printf(
                "~ %s quiere coger la sección crítica para salir. ¿Podrá?. \n", this.idPersona);
        // Muestra un mensaje indicando que la persona intentará salir del puente.

        this.puente.salirPuente(this);
        // La persona sale del puente y notifica a otros que pueden intentar cruzar.
    }
}
