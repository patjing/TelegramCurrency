package api.XMLparser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class ReadXML {
	private static File file = new File("information.xml");
	
	
	private ReadXML()
	{
		
	}
	
	
	/**
	 * set the file name and init it
	 * 
	 * @author patjing
	 * @param	String file path
	 * @since	09/10/2017 
	 * @version 0.0.0.1
	 */
	public static void setFile(String filename)
	{
		file = new File(filename);
	}
	
	
	/**
	 * get the specify element context
	 * 
	 * @author patjing
	 * @param	String the first layer of XML
	 * @param	String the second layer of XML
	 * @exception ParserConfigurationException
	 * @exception SAXException
	 * @exception IOException
	 * @return String
	 * @since	06/10/2017 
	 * @version 0.0.0.1
	 */
	
	public static String readFile(String elementname,String propertyname)
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc = null;
		Node node;
		Element element;
		
		try 
		{
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(file);
		}
		catch (ParserConfigurationException e) 
		{
			e.printStackTrace();
		} 
		catch (SAXException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		// this line is to protect the word in different line
		doc.getDocumentElement().normalize();
		
		
		node = doc.getElementsByTagName(elementname).item(0);
		element = (Element) node;
		return element.getElementsByTagName(propertyname).item(0).getTextContent();		
		
	}
	
	
	
	/**
	 * modify xml with the specify value
	 * 
	 * @author patjing
	 * @param	String the first layer of XML
	 * @param	String the second layer of XML
	 * @param	Object the value it should changed
	 * @exception ParserConfigurationException
	 * @exception SAXException
	 * @exception IOException
	 * @exception TransformerConfigurationException
	 * @exception TransformerException
	 * @since	09/10/2017 
	 * @version 0.0.0.1
	 */
	public static void modifyFile(String elementname,String propertyname,Object value)
	{
		//for read
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc = null;
		Node node;
		Element element;
		//for write
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		DOMSource source = null;
		StreamResult result = new StreamResult(file);
		Transformer transformer = null;
		
		
		try
		{
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(file);
		}
		catch(ParserConfigurationException ex)
		{
			ex.printStackTrace();
		} 
		catch (SAXException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		node = doc.getElementsByTagName(elementname).item(0);
		
		//update content
		element = (Element)node;
		element.getElementsByTagName(propertyname).item(0).setTextContent(String.valueOf(value));
		
		source = new DOMSource(doc);
		
		//write content into XML
		try 
		{
			transformer = transformerFactory.newTransformer();
			transformer.transform(source, result);
		} 
		catch (TransformerConfigurationException e) 
		{
			e.printStackTrace();
		}
		catch (TransformerException e)
		{
			e.printStackTrace();
		}

	}
}
