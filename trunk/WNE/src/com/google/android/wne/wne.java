/**
 * 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 2008 Jose Enrique Pons Frias
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * 
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 */


package com.google.android.wne;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;



import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentReceiver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayController;
import com.google.android.maps.Point;
import com.google.android.maps.Overlay.PixelCalculator;
import com.google.android.wne.dataAccess.MotorConsultas;
import com.google.android.wne.view.UserProfileView;
public class wne extends Activity {
	/**	A reference to MotorConsultas */
	protected MotorConsultas motorConsultas;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.wne_main);
        Button confirmButton = (Button) findViewById(R. id.userTagList);
        
        confirmButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
                setResult(RESULT_OK);
                userTagList();
               // finish();
            }
        } );
        
       Button restaurant = (Button) findViewById(R.id.restauranttag);
       restaurant.setOnClickListener(new View.OnClickListener(){
    	  public void onClick(View view){
    		  restaurant();
    		  
    	  }
       });
       
       Button mapLocator = (Button) findViewById(R.id.maptag);
       mapLocator.setOnClickListener(new View.OnClickListener(){
    	   public void onClick(View view){
    		   maps();
    		  
    	   }
    	   
       });
       
       
       Button readFile = (Button) findViewById(R.id.putilla);
       readFile.setOnClickListener(new View.OnClickListener (){
    	   public void onClick(View view){
    		   readXMLFile();
    		  // tijuana();
    	   }
       });
    	   Button motorC = (Button) findViewById(R.id.motorC);
           motorC.setOnClickListener(new View.OnClickListener (){
        	   public void onClick(View view){
        		  // readXMLFile();
        		   motorConsultas();
        	   }   
       });
 /*      Button tj = (Button) findViewById(R. id.teta);
       
       tj.setOnClickListener(new View.OnClickListener() {
       	public void onClick(View view) {
               //setResult(RESULT_OK);
              
              // finish();
           }
       } );*/
       //probamos el gps
           motorConsultas = new MotorConsultas(this);
          // readXMLFile();
      
       
        
    }
    
    
    private void motorConsultas(){
    //	motorConsultas = new MotorConsultas(this);
    //	List<Location> listaloc = new ArrayList<Location>();
    	Location t = new Location();
    	t.setLatitude(37.173111);
    	t.setLongitude(-3.591531);
    	//listaloc.add(t);
    	
    	motorConsultas.findPlaces(t);
    }
    
    private void tijuana(){
    	Intent tj = new Intent(this,SimMapa.class);
    	startSubActivity(tj,0);
    }
    
    
     private void restaurant(){
    	 Intent i = new Intent(this,Restaurant.class);
    	 startSubActivity(i,0);
     }
    

    
    protected void onActivityResult(int requestCode, int resultCode, 
            String data, Bundle extras) {
super.onActivityResult(requestCode, resultCode, data, extras);

}
    
    /**
     * 
     */  
    private void userTagList(){
    	Intent i = new Intent(this, UserProfileView.class);
        startSubActivity(i,0);
    }

/**
 * 
 * 
 */
private void maps(){
	//Intent i = new Intent(this,MapLocator.class);
//	startSubActivity(i,0);
	 Intent i = new Intent(this,WneMapView.class);
	   startSubActivity(i, 0);
}


	/* (non-Javadoc)
	 * @see android.app.Activity#onFreeze(android.os.Bundle)
	 */
	@Override
	protected void onFreeze(Bundle outState) {
		// TODO Auto-generated method stub
		super.onFreeze(outState);
	}





	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}





	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
    
	private void readXMLFile(){
    	InputStream istream = null;
    	try {
    	FileInputStream tj = openFileInput("proyectoGPS.xml");
    	
    	istream = this.getResources().openRawResource(R.raw.proyecto1000);

    	/* Get a SAXParser from the SAXPArserFactory. */
    	SAXParserFactory spf = SAXParserFactory.newInstance();
    	SAXParser sp = spf.newSAXParser();
    	/* Get the XMLReader of the SAXParser we created. */
    	XMLReader xr = sp.getXMLReader();
    	/* Create a new ContentHandler and apply it to the XML-Reader*/
    	DataHandler myExampleHandler = new DataHandler();
    	myExampleHandler.setMotorConsultas(this.motorConsultas);
    	xr.setContentHandler(myExampleHandler);
    	Log.v("Calling parse() in ReadTourFromLocal: ","perra");
    	/* Parse the xml-data from our URL. */

    	xr.parse(new InputSource(tj));

    	Log.v("Returned from Parse: ","perra");
    	/* Parsing has finished. */
    	//int xcount = myExampleHandler.getNumber();

    	} catch (FileNotFoundException ex){
    		ex.printStackTrace();
    	Log.v("EX","Archivo no encontrado");
    	}catch(ParserConfigurationException pe){
    		Log.v("EX","Parse configuration exception");
    	}catch(SAXParseException exception){
    		Log.v("EX","SAXParseException");
    	}catch(SAXException exception){
    		Log.v("EX","SAXException");
    	}catch(IOException exception){
    		Log.v("EX","IOException");
    	}
    	
    }

}