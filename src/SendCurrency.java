package currency;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;


public class SendCurrency {
	private final static String USER_AGENT = "Chrome/55.0.0.0";
	private final String USER_AGENT2 = "Mozilla/5.0";
	
	private static int messageID = 0;
	private static boolean currencyLogAction = false;
	

	private double getAmount(String input)
	{
		double hkdInput = 0;
		if(input.contains("text"))
		{
			String receiveMessage = input.split("text")[1].split("\"")[2];
			receiveMessage = receiveMessage.toLowerCase();
			if(receiveMessage.contains("currency "))
			{
				hkdInput = Double.parseDouble(receiveMessage.split("currency ")[1]);

			}
		}
		return hkdInput;
	}
		
}

