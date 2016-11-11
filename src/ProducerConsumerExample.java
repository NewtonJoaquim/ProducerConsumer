import java.util.concurrent.Semaphore;

public class ProducerConsumerExample {
	private static boolean Even = true;
	private static boolean Odd = false;
	
	public static void main(String[] args) {
		Semaphore semaforoPar = new Semaphore(0);
		Semaphore semaforoImpar = new Semaphore(0);
		Semaphore semaforoProdutor = new Semaphore(1);
		Dropbox dropbox = new Dropbox(semaforoPar, semaforoImpar);
		
		(new Thread(new Consumer(Even, dropbox, semaforoPar, semaforoProdutor))).start();
		(new Thread(new Consumer(Odd, dropbox, semaforoImpar, semaforoProdutor))).start();
		(new Thread(new Producer(dropbox, semaforoProdutor))).start();
	}
}