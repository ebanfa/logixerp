/**
 * 
 */
package com.qurion.businesslogic.application.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;


/**
 * @author Edward Banfa
 *
 */
public class XMLUtil {
	
	/**
	 * Parse an XML file into a {@link Document} object.
	 * 
	 * @param file the XML file 
	 * @return the document
	 * @throws ApplicationException if an exception is thrown
	 */
	public static Document getW3CDocument(String file) throws ApplicationException
	{
		DocumentBuilderFactory dbFactory = 
				DocumentBuilderFactory.newInstance();
		dbFactory.setValidating(false);
		dbFactory.setIgnoringElementContentWhitespace(true);
		
		Document document = null;
		try {
			DocumentBuilder dBuilder = 
					dbFactory.newDocumentBuilder();
			document = dBuilder.parse(new File(file));
		} catch (Exception e) {
			throw new ApplicationException("");
		}
		document.getDocumentElement().normalize();
		return document;
	}
	
	/**
	 * Parse an XML file into an {@code Object} using the
	 * JAXB API.
	 * 
	 * @param fileName the name of the XML file.
	 * @param clazz the class of the resulting object
	 * @return the parsed object
	 * @throws ApplicationException if we encounter an exception
	 */
	public static Object getJAXBObject(String fileName, Class<?> clazz) throws ApplicationException
	{
	    Object parsedObj = null;
	    try {
	        JAXBContext ctx = JAXBContext.newInstance(clazz);
	        Unmarshaller um = ctx.createUnmarshaller();
	        parsedObj = um.unmarshal(new FileInputStream(fileName));
	    }
	    catch(Exception e) {
			throw new ApplicationException("Exception", e.getMessage());
	    }
	    return parsedObj;
	}

}
