package filosofosV2;

import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {
		Semaphore palillo1= new Semaphore(1);
		Semaphore palillo2= new Semaphore(1);
		Semaphore palillo3= new Semaphore(1);
		Semaphore palillo4= new Semaphore(1);
		Filosofo f1= new Filosofo("Albert", palillo1, palillo2);
		Filosofo f2= new Filosofo("Rosalia", palillo2, palillo3);
		Filosofo f3= new Filosofo("Mariano", palillo3, palillo4);
		Filosofo f4= new Filosofo("Santiago", palillo4, palillo1);
		Thread th1= new Thread(f1);
		Thread th2= new Thread(f2);
		Thread th3= new Thread(f3);
		Thread th4= new Thread(f4);
		th1.start();
		th2.start();
		th3.start();
		th4.start();

	}

}
