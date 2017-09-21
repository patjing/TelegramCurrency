package currency;

import currency.ProcessMessage;

public class Main 
{
	
	public static void main(String args[]) throws Exception
	{
		ProcessMessage pm = new ProcessMessage();
		pm.init();
		pm.execute();
	}
	
}
