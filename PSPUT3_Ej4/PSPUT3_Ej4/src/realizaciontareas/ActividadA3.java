package realizaciontareas;

import java.util.Random;

class ActividadA3 implements Runnable {

    private final int MAX_ESPERA = 50;
    private final int MAX_DURACION = 200;

    private final String nomHilo = "A3";

    Destornillador miDestornillador;
    Taladro miTaladro;
    Alicates misAlicates;


    ActividadA3(Taladro taladro, Destornillador destornillador, Alicates alicates) {
        this.miDestornillador = destornillador;
        this.miTaladro = taladro;
        this.misAlicates = alicates;
    }

    public String getNomHilo() {
        return nomHilo;
    }

    @Override
    public void run() {
        Random r = new Random();
        try {
            while (true) {
                int tEsperaInicio = r.nextInt(MAX_ESPERA) + 1;
                System.out.printf("... Hilo %s tardará %d ms en comenzar.\n", this.getNomHilo(), tEsperaInicio);
                Thread.sleep(tEsperaInicio);
                System.out.printf("> Hilo %s esperando para conseguir el destornillador.\n", this.getNomHilo());
                synchronized (miDestornillador) {
                    System.out.printf("> Hilo %s consigue el destornillador.\n", this.getNomHilo());
                    System.out.printf("> Hilo %s esperando para conseguir el taladro.\n", this.getNomHilo());
                    synchronized (miTaladro) {
                        System.out.printf("> Hilo %s consigue el taladro.\n", this.getNomHilo());
                        System.out.printf("> Hilo %s esperando para conseguir los alicates.\n", this.getNomHilo());
                        synchronized (misAlicates) {
                            System.out.printf("> Hilo %s consigue los alicates.\n", this.getNomHilo());
                            int tDuracion = r.nextInt(MAX_DURACION) + 1;
                            System.out.printf(">> Hilo %s comienza su tarea, que durará %d ms.\n", this.getNomHilo(), tDuracion);
                            Thread.sleep(tEsperaInicio);
                            System.out.printf("<< Hilo %s finaliza su tarea.\n", this.getNomHilo());
                        }
                        System.out.printf("< Hilo %s libera los alicates.\n", this.getNomHilo());
                    }
                    System.out.printf("< Hilo %s libera el taladro.\n", this.getNomHilo());
                }
                System.out.printf("< Hilo %s libera el destornillador .\n", this.getNomHilo());
            }
        } catch (InterruptedException ex) {
        }
    }

}
