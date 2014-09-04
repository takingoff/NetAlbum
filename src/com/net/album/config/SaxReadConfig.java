package com.net.album.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxReadConfig extends DefaultHandler
{
	
	StringBuffer sb;
	String albumRoot = null;
	
	private void beginParse()
	{
		InputStream input;
		try
		{
			input = new FileInputStream(new File(StaticClass.getCurrentClassPath(StaticClass.class)+"/config.xml"));
			SAXParserFactory factory = SAXParserFactory.newInstance();  
			SAXParser parser = factory.newSAXParser();  
			
			parser.parse(input, this); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public String getAlbumRoot()
	{
		if(albumRoot == null)
			beginParse();
		return albumRoot;
	}
	
	
	
	
	@Override
	public void startDocument() throws SAXException
	{
		
		sb = new StringBuffer();
		super.startDocument();
	}
	
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		 sb.append(new String(ch,start,length));  
		super.characters(ch,start,length);
	}

	@Override
	public void endElement(String arg0, String arg1, String arg2)
			throws SAXException
	{
		
		if(arg2.equals("albumRoot"))
			albumRoot = sb.toString().trim();
		
		sb.setLength(0);
		super.endElement(arg0, arg1, arg2);
	}


	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		super.startElement(uri,localName,qName, attributes);
	}
	
	
}
