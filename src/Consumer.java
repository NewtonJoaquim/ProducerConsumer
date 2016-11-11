import java.util.Random;
import java.util.concurrent.Semaphore;
public class Consumer implements Runnable {
	private final Dropbox dropbox;
	private final boolean even;
	private Semaphore semaforo;
	private Semaphore semaforoProdutor;
	
	public Consumer(boolean even, Dropbox dropbox, Semaphore se, Semaphore semaforoProdutor) {
		this.even = even;
		this.dropbox = dropbox;
		this.semaforo = se;
		this.semaforoProdutor = semaforoProdutor;
	}
	public void run() {
		Random random = new Random();
		while (true) {
			try {
				semaforo.acquire();
			} catch (InterruptedException e1) {}
			dropbox.take(even);
			semaforoProdutor.release();
			try {
				Thread.sleep(random.nextInt(100));
			} catch (InterruptedException e) { }
		}
	}
}