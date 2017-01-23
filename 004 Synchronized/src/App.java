import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.bankaccount.Bank;
import com.worker.Worker;

public class App {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);

		Bank account = new Bank(100);
		for(int i=0; i<5; i++){
			Worker worker = new Worker(account);
			executorService.submit(worker);
		}
	}

}
