package testing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import thread.database.DatabaseThread;
import thread.message.MessageThread;

public class Main 
{

	public static void main(String args[])
	{
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new DatabaseThread());
		executor.execute(new MessageThread());
		executor.shutdown();
	}
	
}
