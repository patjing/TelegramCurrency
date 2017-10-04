package databaseConnection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import api.databaseConnection.MysqlConnector;
import api.databaseConnection.StatementGenerator;

public class StatementCreate extends StatementGenerator{
	protected MysqlConnector mc;
	
	private Statement st;
	private ResultSet rs;
		
	public static final boolean ALL = true;
	public static final boolean PARTICAL = false;
	
	public StatementCreate()
	{
		super("record_table");
		mc = new MysqlConnector("54.238.249.141",3306,"Testing","root","123456");
	}
	
	public StatementCreate(String tablename,String ip,int port,String instance, String user,String password)
	{
		super(tablename);
		mc = new MysqlConnector(ip,port,instance,user,password);
	}
	
	
	/**
	 * get all database columns name in to its String[]
	 * 
	 * @author patjing
	 * @exception SQLException
	 * @since	29/09/2017 
	 * @version 0.0.0.3
	 */
	public String[] getAllColumnName()
	{
		String getColumns = "Select * from "+tablename;
		ResultSetMetaData rsmd;
		String[] columnname = null;
		try
		{
			st = mc.con.createStatement();
			rs = st.executeQuery(getColumns);
			rsmd = rs.getMetaData();
			columnname = new String[rsmd.getColumnCount()];
			for(int i=0;i<rsmd.getColumnCount();i++)
			{
				columnname[i] = rsmd.getColumnName(i+1);
			}

		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}

		return columnname;
	}
		
	/**
	 * generate the select statement and return the result
	 * 
	 * @author patjing
	 * @param	boolean the select method
	 * @param	String the specify columns
	 * @exception SQLException
	 * @exception NullPointerException
	 * @return ArrayList<ArrayList<Object>>
	 * @since	29/09/2017 
	 * @version 0.0.0.3
	 */
	public ArrayList<ArrayList<Object>> selectStatement(boolean method,String... cols)
	{
		String query = null;
		ArrayList<ArrayList<Object>> datas = new ArrayList<ArrayList<Object>>();
		
		if(method)
		{
			setAllColumns();
			query = generateSelectAllStatement();
		}
		else
		{
			columns = cols;
			query = generateSelectStatement();
		}
			
		try
		{	
			st = mc.con.createStatement();
			rs = st.executeQuery(query);
	
			while(rs.next())
			{
				ArrayList<Object> row = new ArrayList<Object>();
				for(int i=0;i<columns.length;i++)
				{
					
					String tempcol = rs.getString(i+1);
					row.add(tempcol);
				}
				datas.add(row);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		catch(NullPointerException ex)
		{
			ex.printStackTrace();
		}
		return datas;
	}

	/**
	 * execute the insert statement
	 * 
	 * @author patjing
	 * @return	String	insert statement
	 * @throws Exception if the values is null
	 * @since	28/09/2017 
	 * @version 0.0.0.2
	 */
	public boolean insertStatement(Object... values) throws Exception
	{
		String statement = null;
		if(values.length != 0)
		{
			statement = generateInsertStatement(values); 
		}
		else if(isMismatch(values))
		{
			throw new Exception("The Vaules Parameter is over on method insertStatement()");
		}
		else
		{
			throw new Exception("The Vaules Parameter is NULL on method insertStatement()");
		}
		
		try 
		{
			if(!mc.con.isClosed())
			{
				st = mc.con.createStatement();
			}
			st.executeUpdate(statement);
			return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * check the input is over or not
	 * 
	 * @author patjing
	 * @param	Object[]
	 * @return	boolean
	 * @since	03/10/2017 
	 * @version 0.0.0.1
	 */
	private boolean isMismatch(Object[] list)
	{
		if(list.length == columns.length-1)
		{
			return false;
		}
		return true;
	}
	

	
	/**
	 * set the all column into object columns collection
	 * 
	 * @author patjing
	 * @since	03/10/2017 
	 * @version 0.0.0.1
	 */
	public void setAllColumns()
	{
		columns = getAllColumnName();
	}
			

}
