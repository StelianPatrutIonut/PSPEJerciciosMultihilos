package filosofosV2;

import java.util.concurrent.Semaphore;

public class Filosofo implements Runnable{
	String nombre;
	Semaphore palilloIzda, palilloDcha;
	
	public Filosofo(String nombre, Semaphore palilloIzda, Semaphore palilloDcha) {
		super();
		this.nombre = nombre;
		this.palilloIzda = palilloIzda;
		this.palilloDcha = palilloDcha;
	}

	public void run() {
		while(true) {
			int aleatorioPensar=(int)(Math.random()*9000)+1000;
			System.out.println("El filosofo " + this.nombre + " se pone a pensar " + aleatorioPensar +"ms." );
			try {
				Thread.sleep(aleatorioPensar);
				System.out.println("El filosofo " + this.nombre + " intenta comer ");
				if(palilloIzda.tryAcquire()) {
					if(palilloDcha.tryAcquire()) {
						int aleatorioComer = (int)(Math.random()*9000)+1000;
						System.out.println("El filosofo " + this.nombre + " come durante " + aleatorioComer + "ms.");
						Thread.sleep(aleatorioComer);
						palilloDcha.release();
						System.out.println("El filosofo " + this.nombre + " ha terminado de comer. ");
					}else {
						System.out.println("El filosofo " + this.nombre + " no consigue comer. ");
					}
					palilloIzda.release();
				}else {
					System.out.println("El filosofo " + this.nombre + " no consigue comer. ");
				}
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}

}









