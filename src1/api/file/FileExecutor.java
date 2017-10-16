package api.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FileExecutor implements IFile{
	private File file;
	
	public FileExecutor(String filename)
	{
		file = new File(filename);
	}

	
	
	/**
	 * read the file object
	 * 
	 * @author patjing
	 * @return	String
	 * @throws	FileNotFoundException
	 * @throws IOException
	 * @since	06-10-2017
	 * @versions 0.0.0.1
	 */
	@Override
	public String readFile() 
	{
		FileInputStream fis = null;
		BufferedReader buf = null;
		String line = null;
		String returnLine = null;
		try
		{
			fis = new FileInputStream(file);
			buf = new BufferedReader(new InputStreamReader(fis));
			
			line = buf.readLine();
			while(line!=null)
			{
				returnLine = line;
				line = buf.readLine();
			}
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				fis.close();
				buf.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		return returnLine;
	}

	
	
	/**
	 * write string into the file object
	 * 
	 * @author patjing
	 * @throws	FileNotFoundException
	 * @since	06-10-2017
	 * @versions 0.0.0.1
	 */
	@Override
	public void writeFile(String message)
	{
		PrintWriter writer = null;
		try 
		{
			writer = new PrintWriter(file);
			writer.println(message);
			writer.flush();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			writer.close();
		}
		
	}

	
	/**
	 * change the content of the file object
	 * 
	 * @author patjing
	 * @throws	FileNotFoundException
	 * @trhows  Exception
	 * @since	06-10-2017
	 * @versions 0.0.0.1
	 */
	@Override
	public void changeFile(String message) throws Exception
	{
		if(file.exists())
		{
			throw new Exception("The file is not exist");
		}
		PrintWriter writer = null;
		try 
		{
			writer = new PrintWriter(file);
			writer.println(message);
			writer.flush();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			writer.close();
		}
		
	}

}
