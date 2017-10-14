package api;


public abstract class WebAgent
{
	protected final static String USER_AGENT = "Chrome/55.0.0.0";
	protected final String USER_AGENT2 = "Mozilla/5.0";
	
	protected abstract HttpConnect HttpInit();
	public abstract int getResponseCode();	
}
