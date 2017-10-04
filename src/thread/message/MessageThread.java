package thread.message;

import messageProcessor.ProcessMessage;

public class MessageThread implements Runnable 
{
	@Override
	public void run() 
	{
		ProcessMessage pm = new ProcessMessage();
		pm.init();
		try 
		{
			pm.execute();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
