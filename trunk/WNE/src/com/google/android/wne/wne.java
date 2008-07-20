package com.google.android.wne;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.*;
public class wne extends Activity {
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
       
       
       
       
       
       try{
        XmlProcessor tj = new XmlProcessor();
       
        tj.parse("res/raw/proyecto1000.xml");
       }catch(Exception tj1){
    	   Log.v("WNE","TJ");
       }
       
        
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
    	Intent i = new Intent(this, UserTagList.class);
        startSubActivity(i,0);
    }

/**
 * 
 * 
 */
private void maps(){
	Intent i = new Intent(this,MapLocator.class);
	startSubActivity(i,0);
	
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
    
}