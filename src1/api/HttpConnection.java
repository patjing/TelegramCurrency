package api;



public interface HttpConnection 
{
	//configure request method and agent
	public HttpConnect HttpInit();
	//execute connect
	public int getResponseCode();
}
