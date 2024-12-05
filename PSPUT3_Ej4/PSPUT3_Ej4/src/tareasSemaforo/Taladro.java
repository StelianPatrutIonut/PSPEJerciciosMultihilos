package tareasSemaforo;

import java.util.concurrent.Semaphore;

class Taladro extends Semaphore {
    public Taladro() {
        super(1);
    }
}
