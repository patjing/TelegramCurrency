package messageProcessor;

import java.io.IOException;
import java.text.DecimalFormat;

import api.XMLparser.ReadXML;
import httpContentHandler.GetCurrency;
import httpContentHandler.GetMessage;
import httpContentHandler.SendMessage;

public class ProcessMessage
{
	private GetMessage gm;

	private static int messageID = 0;
	private static int fileContentID = 0;
	private static String notice = ReadXML.readFile("MessageProcessor", "remindMessage");
	
	/**
	 * It initialize the object
	 * 
	 * @author patjing
	 * @since	21-09-2017
	 */
	public void init() 
	{

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
			gm = new GetMessage();
			processMessage(gm.getLastMessage());
		}
	}
		
	
	/**
	 * split the web content
	 * And using SendMessage to send the message to url
	 * 
	 * 
	 * @author patjing
	 * @param	BuffereReader web content
	 * @return	boolean
	 * @throws	IOException
	 * @throws ArrayIndexOutOfBoundsException no content
	 * @throws NumberFormatException
	 * @since	25-09-2017
	 * @versions 0.0.0.2
	 */	
	private boolean processMessage(String lastLine)
	{
		double hkdInput = 0;
		
		double usdresult;
		double twdresult;
		double jpyresult;
		double krwresult;
		double cnyresult;
		int postcode = 0;
		
		DecimalFormat formatter = new DecimalFormat("#0.000");
		
		//SimpleDateFormat date = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");
		//SimpleDateFormat minute = new SimpleDateFormat("mmss");
			
		String receiveMessage;
		String userID = null;
		

		try
		{
			userID = lastLine.split("\"id")[2].split("\"")[1].split(",")[0].split(":")[1];
		}
		catch(ArrayIndexOutOfBoundsException ex)
		{
			
		}
				
				
		if(lastLine.contains("text"))
		{										
			receiveMessage = lastLine.split("text")[1].split("\"")[2];
			receiveMessage = receiveMessage.toLowerCase();

			if(messageID == Integer.parseInt(lastLine.split("id")[1].split("\"")[1].split(":")[1].split(",")[0]))
			{

			}
			else
			{
				messageID = Integer.parseInt(lastLine.split("id")[1].split("\"")[1].split(":")[1].split(",")[0]);
				//read last message ID 
				fileContentID = Integer.parseInt(ReadXML.readFile("MessageProcessor", "LastMessageID"));
				//fileContentID = Integer.parseInt(fe.readFile());
				if(messageID == fileContentID )
				{
					
				}
				else
				{
					ReadXML.modifyFile("MessageProcessor", "LastMessageID", messageID);
					//fe.writeFile(Integer.toString(messageID));
					//System.out.println(messageID);
					if(receiveMessage.contains("hkd "))
					{
						try
						{
							hkdInput = Double.parseDouble(receiveMessage.split("hkd ")[1]);
						}
						catch(Exception ex)
						{
							
						}
						
						usdresult = Calculator.calAmount(hkdInput,new GetCurrency(GetCurrency.usdLink).getCurrency());
						twdresult = Calculator.calAmount(hkdInput,new GetCurrency(GetCurrency.twdLink).getCurrency());
						jpyresult = Calculator.calAmount(hkdInput,new GetCurrency(GetCurrency.jpyLink).getCurrency());
						krwresult = Calculator.calAmount(hkdInput,new GetCurrency(GetCurrency.krwLink).getCurrency());
						cnyresult = Calculator.calAmount(hkdInput,new GetCurrency(GetCurrency.cnyLink).getCurrency());
								
						//System.out.println(formatter.format(usd)+"  "+formatter.format(twd)+"  "+formatter.format(jpy)+"  "+cny);
						String text = "USD: "+formatter.format(usdresult)+"\nTWD: "+formatter.format(twdresult)+"\nJPY: "+formatter.format(jpyresult)+"\nKRW: "+formatter.format(krwresult)+"\nCNY: "+formatter.format(cnyresult);
						postcode = SendMessage.sendPost(userID,text);
					}
					else if(receiveMessage.contains("currency"))
					{
						usdresult = new GetCurrency(GetCurrency.usdLink).getCurrency();
						twdresult = new GetCurrency(GetCurrency.twdLink).getCurrency();;
						jpyresult = new GetCurrency(GetCurrency.jpyLink).getCurrency();;
						krwresult = new GetCurrency(GetCurrency.krwLink).getCurrency();;
						cnyresult = new GetCurrency(GetCurrency.cnyLink).getCurrency();;
								
						String text = "USD currency: "+formatter.format(usdresult)+"\nTWD currency: "+formatter.format(twdresult)+"\nJPY currency: "+formatter.format(jpyresult)+"\nKRW currency: "+formatter.format(krwresult)+"\nCNY currency: "+formatter.format(cnyresult);
						postcode = SendMessage.sendPost(userID,text);
					}
					else if(receiveMessage.contains("twd "))
					{
						try
						{
							hkdInput = Double.parseDouble(receiveMessage.split("twd ")[1]);
						}
						catch(NumberFormatException ex)
						{
							
						}
						
						twdresult = Calculator.calAmount(hkdInput,new GetCurrency(GetCurrency.twdHKLink).getCurrency());
					
						
						String text = "Change Back to HKD is: "+formatter.format(twdresult);
				
						postcode = SendMessage.sendPost(userID,text);
					}
					else if(receiveMessage.contains("jpy "))
					{
						try
						{
							hkdInput = Double.parseDouble(receiveMessage.split("jpy ")[1]);
						}
						catch(NumberFormatException ex)
						{
							
						}
						
						jpyresult = Calculator.calAmount(hkdInput,new GetCurrency(GetCurrency.jpyHKLink).getCurrency());
					
						
						String text = "Change Back to HKD is: "+formatter.format(jpyresult);
					
						postcode = SendMessage.sendPost(userID,text);
					}
					else if(receiveMessage.contains("cny "))
					{
						try
						{
							hkdInput = Double.parseDouble(receiveMessage.split("cny ")[1]);
						}
						catch(NumberFormatException ex)
						{
							
						}
						
						cnyresult = Calculator.calAmount(hkdInput,new GetCurrency(GetCurrency.cnyHKLink).getCurrency());
					
						
						String text = "Change Back to HKD is: "+formatter.format(cnyresult);
					
						postcode = SendMessage.sendPost(userID,text);
					}
					else if(receiveMessage.contains("usd "))
					{
						try
						{
							hkdInput = Double.parseDouble(receiveMessage.split("usd ")[1]);
						}
						catch(NumberFormatException ex)
						{
							
						}
						
						usdresult = Calculator.calAmount(hkdInput,new GetCurrency(GetCurrency.usdHKLink).getCurrency());
					
						
						String text = "Change Back to HKD is: "+formatter.format(usdresult);
						postcode = SendMessage.sendPost(userID,text);
					}
					else if(receiveMessage.contains("krw "))
					{
						try
						{
							hkdInput = Double.parseDouble(receiveMessage.split("krw ")[1]);
						}
						catch(NumberFormatException ex)
						{
							
						}
						
						krwresult = Calculator.calAmount(hkdInput,new GetCurrency(GetCurrency.krwHKLink).getCurrency());
					
						
						String text = "Change Back to HKD is: "+formatter.format(krwresult);
						postcode = SendMessage.sendPost(userID,text);
					}
					else
					{
						postcode = SendMessage.sendPost(userID,notice);
					}
				}
			}
		}
		
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
