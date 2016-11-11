import java.util.Random;
import java.util.concurrent.Semaphore;
public class Producer implements Runnable {
	private Dropbox dropbox;
	private Semaphore semaforoProdutor;
	
	public Producer(Dropbox dropbox, Semaphore se) {
		this.dropbox = dropbox;
		this.semaforoProdutor = se;
	}
	public void run() {
		Random random = new Random();
		while (true) {
			int number = random.nextInt(10);
			try {
				Thread.sleep(random.nextInt(100));
				semaforoProdutor.acquire();
				dropbox.put(number);
			} catch (InterruptedException e) { }
		}
	}
}