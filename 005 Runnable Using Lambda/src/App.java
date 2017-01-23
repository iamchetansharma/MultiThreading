//MultiThreading Runnable and Lambda
public class App {

	public static void main(String[] args) {
		
		Runnable runnable = () -> {
			System.out.println("This is Thead :"+Thread.currentThread().getName());
		};
		
		Thread t = new Thread(runnable);
		t.setName("One");
		t.start();

	}

}
