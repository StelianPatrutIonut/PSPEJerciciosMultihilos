package tareasSemaforo;

public class RealizacionTareas {
/*
*LO MISMO PERO CON SEMAFORO */
  public static void main(String[] args) {

    int tiempoEjecucion = 20000;

    Taladro taladro = new Taladro();
    Destornillador destornillador = new Destornillador();
    Alicates alicates = new Alicates();

    Thread a1 = new Thread(new ActividadA1(destornillador, taladro));
    Thread a2 = new Thread(new ActividadA2(destornillador, alicates));
    Thread a3 = new Thread(new ActividadA3(taladro, destornillador, alicates));

    a1.start();
    a2.start();
    a3.start();

  }

}
