package tareasSemaforo;

import java.util.concurrent.Semaphore;

class Alicates extends Semaphore{
    public Alicates() {
        super(1);
    }
}
