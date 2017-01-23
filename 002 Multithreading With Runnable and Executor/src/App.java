import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Adder implements Runnable {
	private String inFile, outFile;

	public Adder(String inFile, String outFile) {
		this.inFile = inFile;
		this.outFile = outFile;
	}

	public void doAdd() {
		int total = 0;
		String line = null;
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(inFile))) {
			while ((line = reader.readLine()) != null) {
				total += Integer.parseInt(line);
			}
		} catch (IOException e) {
			e.getMessage();
		}

		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outFile))) {

			writer.write("Total is :" + total);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		doAdd();

	}

}

public class App {

	public static void main(String[] args) throws InterruptedException {
		String[] inFile = { "File1.txt", "File1.txt", "File1.txt", "File1.txt", "File1.txt", };
		String[] outFile = { "File2.txt", "File3.txt", "File4.txt", "File5.txt", "File6.txt", };

		ExecutorService executorService= Executors.newFixedThreadPool(3);
		for (int i = 0; i < inFile.length; i++) {
			Adder adder = new Adder(inFile[i], outFile[i]);
			executorService.submit(adder);
		}
		
		executorService.shutdown();
		executorService.awaitTermination(60, TimeUnit.SECONDS);
	}

}
