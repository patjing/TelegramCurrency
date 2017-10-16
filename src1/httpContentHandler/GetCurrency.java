package httpContentHandler;

import java.io.IOException;


import api.HttpConnect;
import api.XMLparser.ReadXML;

public class GetCurrency extends HttpConnect{
	public static String usdLink = ReadXML.readFile("URL", "usdLink");
	public static String twdLink = ReadXML.readFile("URL", "twdLink");
	public static String cnyLink = ReadXML.readFile("URL", "rmbLink");
	public static String krwLink = ReadXML.readFile("URL", "krwLink");
	public static String jpyLink = ReadXML.readFile("URL", "jpyLink");
	public static String twdHKLink = ReadXML.readFile("URL", "twdHKLink");
	public static String jpyHKLink = ReadXML.readFile("URL", "jpyHKLink");
	public static String usdHKLink = ReadXML.readFile("URL", "usdHKLink");
	public static String krwHKLink = ReadXML.readFile("URL", "krwHKLink");
	public static String cnyHKLink = ReadXML.readFile("URL", "cnyHKLink");
	
	private static String methods = "GET";
	
	
	public GetCurrency(String url)
	{
		super(url,methods);
	}
	
	/**
	 * Returns request currency
	 * 
	 * 
	 * @author patjing
	 * @return	double currency 
	 * @exception IOException
	 * @since	25-09-2017
	 * @version 0.0.2.0
	 */
	
	public double getCurrency()
	{
		String line;
		double currency = 0;
		
		try
		{			
    
	        while ((line = this.br.readLine()) != null)
	        {
	            //System.out.println(line);
	            if(line.contains("<div id=\"cc-ratebox\" name=\"cc-ratebox\" aria-labelledby=\"elb\" tabindex=\"4\">"))
	            {
	            	currency =  Double.parseDouble(line.split("<div id=\"cc-ratebox\" name=\"cc-ratebox\" aria-labelledby=\"elb\" tabindex=\"4\">")[1].split("= ")[1].split("</div>")[0]);
	            }
	        }
		}
		catch(IOException ex)
		{
			
		}

		return currency;
	}

}

