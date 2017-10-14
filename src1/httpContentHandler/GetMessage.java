package httpContentHandler;


import java.io.IOException;

import api.HttpConnect;
import api.XMLparser.ReadXML;


public class GetMessage extends HttpConnect{

	private static String url = ReadXML.readFile("URL", "update");
	private String output = null;
	
	public GetMessage() 
	{
		super(url, "GET");
	}
	
	/**
	 * Just return the last line of the chat session
	 * 
	 * @author patjing
	 * @exception IOException
	 * @return String the last message
	 * @since 13-10-2017
	 * @version 0.0.1.0
	 */
	
	public String getLastMessage()
	{
		String input = null;
		try 
		{
			input = br.readLine();		
			while(input!=null)
			{
				output = input;
				input = br.readLine();	
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		return output;
	}
	
	
	//set the global String url
	protected static void setURL(String link)
	{
		url = link;
		ReadXML.modifyFile("URL", "update", link);
	}
	
	//get the global String url
	public static String getURL()
	{
		return url;
	}
}
