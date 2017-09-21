package currency;

import java.io.BufferedReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class ProcessMessage
{
	private static SendMessage sm;
	private static GetMessage gm;
	
	private static int messageID = 0;
	private static String notice = "U+1F647";
	
	/**
	 * It initialize the object
	 * 
	 * @author patjing
	 * @since	21-09-2017
	 */
	public void init() 
	{
		sm = new SendMessage();
		gm = new GetMessage();
	}
	
	/**
	 * It execute the method
	 * 
	 * @author patjing
	 * @throws	Exception
	 * @since	21-09-2017
	 */	
	public void execute() throws Exception
	{
		while(true)
		{
			processMessage(gm.getContent());
		}
	}
		
	
	/**
	 * split the web content
	 * 
	 * 
	 * @author patjing
	 * @throws	Exception
	 * @since	21-09-2017
	 */	
	private static boolean processMessage(BufferedReader in) throws Exception 
	{
		double hkdInput = 0;
		
		double usd;
		double twd;
		double jpy;
		double krw;
		double cny;
		int postcode = 0;
		
		DecimalFormat formatter = new DecimalFormat("#0.000");
		
		//SimpleDateFormat date = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");
		//SimpleDateFormat minute = new SimpleDateFormat("mmss");
			
		
		String inputLine = in.readLine();
		String receiveMessage;
		String userID = null;
		
		do
		{
			//System.out.println(inputLine);
			String temp = inputLine;
			inputLine = in.readLine();

			if(inputLine==null)
			{	
				try
				{
					userID = temp.split("\"id")[1].split("\"")[1].split(",")[0].split(":")[1];
				}
				catch(ArrayIndexOutOfBoundsException ex)
				{
					
				}
				
				//messageFile.saveMessageIdLog(GeneraateLog.fileMesssageID,temp.split("id")[1].split("\"")[1].split(":")[1].split(",")[0]);
				
				if(temp.contains("text"))
				{										
					receiveMessage = temp.split("text")[1].split("\"")[2];
					receiveMessage = receiveMessage.toLowerCase();

					if(messageID == Integer.parseInt(temp.split("id")[1].split("\"")[1].split(":")[1].split(",")[0]))
					{
						//System.out.println("YES");
					}
					else
					{
						messageID = Integer.parseInt(temp.split("id")[1].split("\"")[1].split(":")[1].split(",")[0]);
						//System.out.println(messageID);
						
						if(receiveMessage.contains("currency "))
						{
							try
							{
								hkdInput = Double.parseDouble(receiveMessage.split("currency ")[1]);
							}
							catch(Exception ex)
							{
								
							}
							
							usd = Calculator.calAmount(hkdInput,GetCurrency.getCurrency(GetCurrency.usdLink));
							twd = Calculator.calAmount(hkdInput,GetCurrency.getCurrency(GetCurrency.twdLink));
							jpy = Calculator.calAmount(hkdInput,GetCurrency.getCurrency(GetCurrency.jpyLink));
							krw = Calculator.calAmount(hkdInput,GetCurrency.getCurrency(GetCurrency.krwLink));
							cny = Calculator.calAmount(hkdInput,GetCurrency.getCurrency(GetCurrency.rmbLink));
							
							//System.out.println(formatter.format(usd)+"  "+formatter.format(twd)+"  "+formatter.format(jpy)+"  "+cny);
							String text = "USD: "+formatter.format(usd)+"\nTWD: "+formatter.format(twd)+"\nJPY: "+formatter.format(jpy)+"\nKRW: "+formatter.format(krw)+"\nCNY: "+formatter.format(cny);
							postcode = sm.sendPost(userID,text);
							break;
						}
						else if(receiveMessage.contains("currency"))
						{
							usd = GetCurrency.getCurrency(GetCurrency.usdLink);
							twd = GetCurrency.getCurrency(GetCurrency.twdLink);
							jpy = GetCurrency.getCurrency(GetCurrency.jpyLink);
							krw = GetCurrency.getCurrency(GetCurrency.krwLink);
							cny = GetCurrency.getCurrency(GetCurrency.rmbLink);
							
							String text = "USD currency: "+formatter.format(usd)+"\nTWD currency: "+formatter.format(twd)+"\nJPY currency: "+formatter.format(jpy)+"\nKRW currency: "+formatter.format(krw)+"\nCNY currency: "+formatter.format(cny);
							postcode = sm.sendPost(userID,text);
							break;
						}
						else if(receiveMessage.contains("twd "))
						{
							try
							{
								hkdInput = Double.parseDouble(receiveMessage.split("twd ")[1]);
							}
							catch(Exception ex)
							{
								
							}
							
							twd = Calculator.calAmount(hkdInput,GetCurrency.getCurrency(GetCurrency.twdHKLink));
						
							
							String text = "Change Back to HKD is: "+formatter.format(twd);
					
							postcode = sm.sendPost(userID,text);
							break;
						}
						else if(receiveMessage.contains("jpy "))
						{
							try
							{
								hkdInput = Double.parseDouble(receiveMessage.split("jpy ")[1]);
							}
							catch(Exception ex)
							{
								
							}
							
							jpy = Calculator.calAmount(hkdInput,GetCurrency.getCurrency(GetCurrency.jpyHKLink));
						
							
							String text = "Change Back to HKD is: "+formatter.format(jpy);
						
							postcode = sm.sendPost(userID,text);
							break;
						}
						else if(receiveMessage.contains("cny "))
						{
							try
							{
								hkdInput = Double.parseDouble(receiveMessage.split("cny ")[1]);
							}
							catch(Exception ex)
							{
								
							}
							
							cny = Calculator.calAmount(hkdInput,GetCurrency.getCurrency(GetCurrency.cnyHKLink));
						
							
							String text = "Change Back to HKD is: "+formatter.format(cny);
						
							postcode = sm.sendPost(userID,text);
							break;
						}
						else if(receiveMessage.contains("usd "))
						{
							try
							{
								hkdInput = Double.parseDouble(receiveMessage.split("usd ")[1]);
							}
							catch(Exception ex)
							{
								
							}
							
							usd = Calculator.calAmount(hkdInput,GetCurrency.getCurrency(GetCurrency.usdHKLink));
						
							
							String text = "Change Back to HKD is: "+formatter.format(usd);
							postcode = sm.sendPost(userID,text);
							break;
						}
						else
						{
							postcode = sm.sendPost(userID,notice);
						}
	
					}
				}
			}	
		}while (inputLine != null);	
		in.close();
		if(postcode<400)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
