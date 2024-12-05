package estudiantesylibros;
// Paquete que organiza el código.

import java.util.Random;
// Importa la clase Random para generar números aleatorios.

public class Estudiante implements Runnable {
    // Clase que implementa la interfaz Runnable para representar a un estudiante.

    private final static int MIN_USO = 6000;  // equiv. a 1h=60m
    private final static int MAX_USO = 18000;  // equiv. a 3h=180m
    private final static int MIN_ESPERA = 6000;  // equiv. a 1h=60m
    private final static int MAX_ESPERA = 12000;  // equiv. a 3h=180m
    // Constantes que definen límites de tiempo para el uso y espera de libros.

    private final Libro[] libros;
    // Arreglo que representa los libros disponibles.
    String id;
    // Identificador único del estudiante.

    public Estudiante(Libro[] libros, String id) {
        this.libros = libros;
        this.id = id;
        // Constructor que inicializa el arreglo de libros y el identificador del estudiante.
    }

    @Override
    public void run() {
        // Método run() que contiene la lógica principal del estudiante.

        Random r = new Random();
        // Instancia de Random para generar números aleatorios.

        while (true) {
            // Bucle infinito que representa la actividad constante del estudiante.

            try {
                Thread.sleep(1000);
                // Pausa el hilo durante un segundo para simular el tiempo entre actividades.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
                // Captura excepciones de interrupción y lanza una RuntimeException.
            }

            int indiceAleatorio1 = r.nextInt(this.libros.length);
            int indiceAleatorio2;
            // Se generan índices aleatorios para dos libros diferentes.
            while ((indiceAleatorio2 = r.nextInt(this.libros.length)) == indiceAleatorio1) {
                // Se asegura de que el segundo índice sea diferente al primero.
            }

            int indiceMin, indiceMax;
            if (indiceAleatorio1 < indiceAleatorio2) {
                indiceMin = indiceAleatorio1;
                indiceMax = indiceAleatorio2;
            } else {
                indiceMin = indiceAleatorio2;
                indiceMax = indiceAleatorio1;
            }
            // Determina el orden de los índices para evitar posibles bloqueos mutuos.

            System.out.printf("|Estudiante %s intenta conseguir libro %d\n", this.id, indiceMin);
            // Muestra un mensaje indicando el intento del estudiante por conseguir el primer libro.

            Libro libro1 = libros[indiceMin];

            synchronized (libro1) {
                // Bloquea el primer libro para evitar que otros estudiantes lo usen simultáneamente.
                System.out.printf("|Estudiante %s consigue libro %d, intenta conseguir libro %d\n", this.id, indiceMin, indiceMax);
                Libro libro2 = libros[indiceMax];

                synchronized (libro2) {
                    // Bloquea el segundo libro para evitar que otros estudiantes lo usen simultáneamente.
                    System.out.printf("|Estudiante %s consigue libros %d y %d y se pone a leer ... \n", this.id, indiceMin, indiceMax);
                    // Muestra un mensaje indicando que el estudiante ha conseguido ambos libros y está leyendo.
                    int tUso = MIN_USO + r.nextInt(MAX_USO - MIN_USO) + 1;
                    // Genera un tiempo de uso aleatorio entre los límites establecidos.
                    try {
                        Thread.sleep(tUso);
                        // Simula el tiempo que el estudiante pasa leyendo.
                    } catch (InterruptedException ex) {
                        // Maneja excepciones de interrupción.
                    }
                }

                System.out.printf("|Estudiante %s deja libros %d y %d y descansa \n", this.id, indiceMin, indiceMax);
                // Muestra un mensaje indicando que el estudiante ha dejado los libros y está descansando.
            }

            int tUso = MIN_ESPERA + r.nextInt(MAX_ESPERA - MIN_ESPERA) + 1;
            // Genera un tiempo de espera aleatorio entre los límites establecidos.
            try {
                Thread.sleep(tUso);
                // Simula el tiempo que el estudiante pasa esperando antes de intentar de nuevo.
            } catch (InterruptedException ex) {
                // Maneja excepciones de interrupción.
            }

        }
    }
}
