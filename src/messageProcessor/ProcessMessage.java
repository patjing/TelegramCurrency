package messageProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DecimalFormat;

import api.XMLparser.ReadXML;
import api.file.FileExecutor;
import httpContentHandler.GetCurrency;
import httpContentHandler.GetMessage;
import httpContentHandler.SendMessage;

public class ProcessMessage
{
	private SendMessage sm;
	private GetMessage gm;
	private FileExecutor fe;
	
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
		sm = new SendMessage();
		gm = new GetMessage();
		fe = new FileExecutor("LastMessageID");
	}
	
	/**
	 * It execute the method
	 * 
	 * @author patjing
	 * @throws	Exception
	 * @since	21-09-2017
	 * @version 0.0.1.1
	 */	
	public void execute()
	{
		while(true)
		{
			processMessage(gm.getContent());
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
	private boolean processMessage(BufferedReader in)
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
			
		String receiveMessage;
		String userID = null;
		String inputLine = null;
		try 
		{
			inputLine = in.readLine();
		}
		catch (IOException e) 
		{

			e.printStackTrace();
		}

		
		do
		{
			String temp = inputLine;
			try
			{
				inputLine = in.readLine();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}

			//search for the last line
			if(inputLine==null)
			{	
				//for web site no content
				try
				{
					userID = temp.split("\"id")[1].split("\"")[1].split(",")[0].split(":")[1];
				}
				catch(ArrayIndexOutOfBoundsException ex)
				{
					
				}
				
				
				if(temp.contains("text"))
				{										
					receiveMessage = temp.split("text")[1].split("\"")[2];
					receiveMessage = receiveMessage.toLowerCase();

					if(messageID == Integer.parseInt(temp.split("id")[1].split("\"")[1].split(":")[1].split(",")[0]))
					{

					}
					else
					{
						messageID = Integer.parseInt(temp.split("id")[1].split("\"")[1].split(":")[1].split(",")[0]);
						//read last message ID 
						fileContentID = Integer.parseInt(ReadXML.readFile("MessageProcessor", "LastMessageID"));
						//fileContentID = Integer.parseInt(fe.readFile());
						if(messageID == fileContentID )
						{
							continue;
						}
						ReadXML.modifyFile("MessageProcessor", "LastMessageID", messageID);
						//fe.writeFile(Integer.toString(messageID));
						//System.out.println(messageID);
						
						if(receiveMessage.contains("hkt "))
						{
							try
							{
								hkdInput = Double.parseDouble(receiveMessage.split("hkt ")[1]);
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
							catch(NumberFormatException ex)
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
							catch(NumberFormatException ex)
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
							catch(NumberFormatException ex)
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
							catch(NumberFormatException ex)
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
		
		try 
		{
			in.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
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
