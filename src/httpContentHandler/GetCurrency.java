package httpContentHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import api.HttpConnect;

public class GetCurrency extends HttpConnect{
	public static String usdLink = "https://themoneyconverter.com/HKD/USD.aspx";
	public static String twdLink = "https://themoneyconverter.com/HKD/TWD.aspx";
	public static String rmbLink = "https://themoneyconverter.com/HKD/CNY.aspx";
	public static String krwLink = "https://themoneyconverter.com/HKD/KRW.aspx";
	public static String jpyLink = "https://themoneyconverter.com/HKD/JPY.aspx";
	public static String twdHKLink = "https://themoneyconverter.com/TWD/HKD.aspx";
	public static String jpyHKLink = "https://themoneyconverter.com/JPY/HKD.aspx";
	public static String usdHKLink = "https://themoneyconverter.com/USD/HKD.aspx";
	public static String krwHKLink = "https://themoneyconverter.com/KRW/HKD.aspx";
	public static String cnyHKLink = "https://themoneyconverter.com/CNY/HKD.aspx";
	
	/**
	 * Returns request currency
	 * input the URL string
	 * 
	 * 
	 * @author patjing
	 * @return	double currency 
	 * @exception IOException
	 * @since	25-09-2017
	 * @version 0.0.1.0
	 */
	
	public static double getCurrency(String link)
	{
		String line;
		double currency = 0;
		HttpConnect connect = new HttpConnect(link);
		HttpURLConnection con = connect.HttpInit("GET");
		
		try
		{			
		    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		    
	        while ((line = br.readLine()) != null)
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

