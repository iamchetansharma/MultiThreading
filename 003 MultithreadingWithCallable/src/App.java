import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class Adder implements Callable<Integer> {
	private String inFile;

	public Adder(String inFile) {
		this.inFile = inFile;
		
	}

	public int doAdd() {
		int total = 0;
		String line = null;
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(inFile))) {
			while ((line = reader.readLine()) != null) {
				total += Integer.parseInt(line);
			}
		} catch (IOException e) {
			e.getMessage();
		}
		
		return total;

	}

	@Override
	public Integer call(){
		return doAdd();

	}

}

public class App {

	public static void main(String[] args) throws InterruptedException {
		String[] inFile = { "File1.txt", "File1.txt", "File1.txt", "File1.txt", "File1.txt", };
		
		ExecutorService executorService= Executors.newFixedThreadPool(3);
		
		Future<Integer>[] future = new Future[inFile.length];
		for (int i = 0; i < inFile.length; i++) {
			Adder adder = new Adder(inFile[i]);
			
			future[i] = executorService.submit(adder);
		}
		
		for(Future result: future){
			try{
				int value = (int) result.get();
				System.out.println(value);
			}catch(ExecutionException e){
				Throwable adderFx = e.getCause();
			}
		}
		
		
		executorService.shutdown();
		executorService.awaitTermination(60, TimeUnit.SECONDS);
	}

}
