package tareasSemaforo;

import java.util.concurrent.Semaphore;

class Destornillador extends Semaphore {
    public Destornillador() {
        super(1);
    }
}
