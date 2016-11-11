import java.util.concurrent.Semaphore;

public class Dropbox {
	private int number;
	private boolean evenNumber = false;
	private Semaphore semaforoPar;
	private Semaphore semaforoImpar;
	
	Dropbox(Semaphore semaforoPar, Semaphore semaforoImpar){
		this.semaforoPar = semaforoPar;
		this.semaforoImpar = semaforoImpar;
	}
	
	public int take(final boolean even) {
		System.out.format("%s CONSUMIDOR obtem %d.%n", even ? "PAR" : "IMPAR", number);
		return number;
	}
	public void put(int number) {
		this.number = number;
		evenNumber = number % 2 == 0;
		if(evenNumber)
			semaforoPar.release();
		else
			semaforoImpar.release();
		System.out.format("PRODUTOR gera %d.%n", number);
		
	}
}
