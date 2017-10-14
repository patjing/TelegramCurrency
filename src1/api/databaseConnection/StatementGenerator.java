package api.databaseConnection;

public class StatementGenerator {
	public String[] columns;
	public String tablename;

	
	public StatementGenerator(String table)
	{
		tablename = table;
	}
	
	/**
	 * generate the select statement 
	 * 
	 * @author patjing
	 * @return	String	select statement
	 * @since	29/09/2017 
	 * @version 0.0.0.1
	 */
	protected String generateSelectAllStatement()
	{
		return "select * from "+tablename;
	}
	
	
	/**
	 * generate the select statement with specify column
	 * 
	 * @author patjing
	 * @return	String	select statement
	 * @since	29/09/2017 
	 * @version 0.0.0.3
	 */
	protected String generateSelectStatement()
	{
		String statement = "select ";
		
		for(String s : columns)
		{

			statement += s+",";
		}
		statement = statement.substring(0, statement.length()-1);
		statement += " from "+tablename+";";
		return statement;
	}
	
	
	/**
	 * generate the insert statement base on the global
	 * 
	 * @author patjing
	 * @param	Object values
	 * @return	String	insert statement
	 * @since	29/09/2017 
	 * @version 0.0.0.5
	 */
	protected String generateInsertStatement(Object... values)
	{
		String statement = "insert into "+tablename+" (";
		
		for(String s : columns)
		{
			if(!s.equals("Item"))
			{
				statement += s+",";
			}
		}
		statement = statement.substring(0, statement.length()-1);
		statement += ") values(";
		for(int i=0;i<values.length;i++)
		{
			String v = values[i].toString();
			if((v != null)||(v != " "))
			{
				statement += "\""+v +"\",";
			}
			
		}
		statement = statement.substring(0, statement.length()-1);
		statement += ");";
		return statement;
	}
	
	
	/**
	 * set the column in object
	 * 
	 * @author patjing
	 * @param	String... 
	 * @since	29/09/2017 
	 * @version 0.0.0.2
	 */
	public void setColumns(String... col)
	{
		columns = col;
	}
	
	
	/**
	 * set the table name
	 * 
	 * @author patjing
	 * @param	table tablename
	 * @since	29/09/2017 
	 * @version 0.0.0.2
	 */
	public void setTableName(String table)
	{
		tablename = table;
	}
	
	
	/**
	 * get the column in object
	 * 
	 * @author patjing
	 * @return String the columns name
	 * @since	03/10/2017 
	 * @version 0.0.0.1
	 */
	public String[] getColumns()
	{
		return columns;
	}
	
	/**
	 * get the object table name
	 * 
	 * @author patjing
	 * @return string tablename
	 * @since	03/10/2017 
	 * @version 0.0.0.1
	 */
	public String getTableName()
	{
		return tablename;
	}
	

}
