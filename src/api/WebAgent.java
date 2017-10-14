package api;

import java.net.HttpURLConnection;

public abstract class WebAgent 
{
	protected final static String USER_AGENT = "Chrome/55.0.0.0";
	protected final String USER_AGENT2 = "Mozilla/5.0";
	
	public abstract String GetURL();
	public abstract HttpURLConnection HttpInit(String method);
	public abstract int HttpConnected(HttpURLConnection con);
}
