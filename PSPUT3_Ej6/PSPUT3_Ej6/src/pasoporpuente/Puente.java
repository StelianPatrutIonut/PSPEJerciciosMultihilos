package pasoporpuente;

class Puente { // Estado, objeto compartido entre los hilos
    // Clase que representa el estado del puente y proporciona métodos para gestionar el paso de personas.

    private static final int PESO_MAXIMO = 200; // Peso máximo permitido en el puente.
    private static final int MAX_PERSONAS = 3; // Número máximo de personas permitidas en el puente.

    private int peso = 0; // Peso total actual en el puente.
    private int numPersonas = 0; // Número de personas actualmente en el puente.

    synchronized public int getPeso() {
        return peso;
        // Método que devuelve el peso total actual en el puente.
    }

    synchronized public int getNumPersonas() {
        return numPersonas;
        // Método que devuelve el número de personas actualmente en el puente.
    }

    synchronized public void pasarPuente(Persona persona) {

        boolean autorizado = false;
        while (!autorizado) {
            System.out.printf(
                    "~ %s cogió la sección crítica. Intenta pasar. \n", persona.getIdPersona());
            autorizado = this.autorizacionPaso(persona);
            if (!autorizado) {
                System.out.printf("# %s debe esperar.\n", persona.getIdPersona());
                try {
                    this.wait();
                    // La persona espera si no está autorizada para pasar.
                } catch (InterruptedException e) {
                    System.out.printf("Interrupción mientas %s espera. \n", persona.getIdPersona());
                }
            }
        }
    }

    synchronized public void salirPuente(Persona persona) {
        System.out.printf(
                "~ %s cogió la sección crítica. Intenta salir del puente. \n", persona.getIdPersona());
        this.terminaPaso(persona);
        System.out.printf(
                "< %s sale del puente, puente soporta peso %d, %d persona%s.\n",
                persona.getIdPersona(), this.getPeso(), this.getNumPersonas(),
                this.getNumPersonas() != 1 ? "s" : "");
        this.notifyAll();
        // Notifica a todos los hilos que están esperando.
    }

    synchronized private boolean autorizacionPaso(Persona persona) {
        boolean result;
        if (this.peso + persona.getPeso() <= Puente.PESO_MAXIMO &&
                this.numPersonas < Puente.MAX_PERSONAS) {
            this.numPersonas++;
            this.peso += persona.getPeso();
            result = true;
            // Se autoriza el paso si no excede el peso máximo y no hay demasiadas personas.
        } else {
            result = false;
            // No se autoriza el paso.
        }
        return result;
    }

    synchronized private void terminaPaso(Persona persona) {
        this.peso -= persona.getPeso();
        this.numPersonas--;
        // Se reduce el peso y el número de personas después de que alguien haya cruzado.
    }
}
