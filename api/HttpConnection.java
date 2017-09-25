package api;

import java.net.HttpURLConnection;

public interface HttpConnection 
{
	public HttpURLConnection HttpInit(String url);
	public int HttpConnected(HttpURLConnection con);
}
