package api;

import java.net.HttpURLConnection;

public interface HttpConnection 
{
	//configure request method and agent
	public HttpURLConnection HttpInit(String method);
	//execute connect
	public int HttpConnected(HttpURLConnection con);
}
