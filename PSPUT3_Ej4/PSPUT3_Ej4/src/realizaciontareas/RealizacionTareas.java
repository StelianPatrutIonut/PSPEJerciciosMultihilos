package realizaciontareas;

public class RealizacionTareas {
  /*
   * Existen varias actividades de reparación que necesitan determinadas herramientas para llevarse a cabo, a saber: un taladro
   * , un destornillador y unos alicates. De estas herramientas solo hay una de cada tipo. La actividad A1 necesita un destornillador
   *  y un taladro. La actividad A2 necesita un destornillador y unos alicates. La actividad A3 necesita un taladro, un destornillador
   *  y unos alicates. Las actividades las realizan, continuamente, un conjunto de agentes. Existen dos agentes que realizan la actividad A1,
   *  un agente que realiza la actividad A2 y un agente que realiza la actividad A3. Cada actividad tarda en realizarse un tiempo de entre 50 y 200 ms.
   *   Realiza una simulación de estas actividades. Las actividades se realizan de forma indefinida. */
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
