package api.databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MysqlConnector{
	public Connection con;
	
	private String user;
	private String password;
	private String ip;
	private int port = 3306;
	public String instance;
	
	protected MysqlConnector()
	{

	}
	
	public MysqlConnector(String iip,int iport,String iinstance, String iuser,String ipassword)
	{
		this.user = iuser;
		this.password = ipassword;
		this.ip = iip;
		this.port = iport;
		this.instance = iinstance;
		Connect();
	}
	
	/**
	 * for the mysql connection to connect to the database
	 * 
	 * @author patjing
	 * @exception ClassNotFoundException
	 * @exception SQLException
	 * @since	28/09/2017 
	 * @version 0.0.0.1
	 */
	private void Connect()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+instance,user,password);	
			
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
	}
	
	/**
	 * Check the database connection is fine. If fine return the connection object
	 * 
	 * @author patjing
	 * @exception SQLException
	 * @return Connection
	 * @since	28/09/2017 
	 * @version 0.0.0.1
	 */
	
	protected Connection getConnection()
	{
		boolean isclosed = true;
		try
		{
			isclosed = con.isClosed();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		if(isclosed == false)
		{
			return con;
		}
		return null;
	}
		
	
}
