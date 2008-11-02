/**
 * 
 */
package com.google.android.wne;
import android.util.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderAdapter;

import com.google.android.wne.dataAccess.DataAccess;

/**
 * @author Jose Enrique
 * 
 */
public class XmlProcessor extends XMLReaderAdapter{
	/* database SQLite access */
	private DataAccess mdb;
	
	
	/*
	 * Constructor
	 * 
	 */
	public XmlProcessor () throws SAXException{
			super();
			
		/*	mdb = new DataAccess(this);
			mdb.setMDbCreate(mDbCreate);
			mdb.setMDbName(mDbN);
			mdb.open(); */
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLReaderAdapter#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		Log.v("XmlProcessor","endDocument");
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLReaderAdapter#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, name);
		Log.v("XmlProcessor","endElement");
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLReaderAdapter#startDocument()
	 */
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		Log.v("XmlProcessor","startDocument");
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.XMLReaderAdapter#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String name,
			Attributes atts) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, name, atts);
		Log.v("XmlProcessor","startElement");
	}
	
	
	
}
