/**
 * 
 */
package com.google.android.wne;







import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/** Clase para almacenar los perfiles de los usuarios.
 *  
 * @author Jose Enrique
 *
 */
public class UserTag extends Activity{
	
	private Long mRowId;
	private EditText mUserTagName;
	private EditText mUserMonetaryValueCheap;
	private EditText mUserMonetaryValueMedium;
	private EditText mUserMonetaryValueExpensive;
	private EditText mUserDistanceValueNear;
	private EditText mUserDistanceValueMedium;
	private EditText mUserDistanceValueFar;
	private EditText mUserPreference;
	private DataAccess mUserTagDb;
	private static final String mDbCreate = "create table userTag (_id integer primary key autoincrement, "
            + "userTagName text not null, userPrefference text, userDistanceNear real,userDistanceMedium real,userDistanceFar real,uCheap real,uMed real, uExp real);";
	private static final String mDbN = "USER_TAG";
	private static final String ROW_ID = "_id";
	private static final String ROW_TITLE = "userTagName";
	private static final String ROW_PREFFERENCE = "userPrefference";
	private static final String ROW_DNEAR = "userDistanceNear";
	private static final String ROW_DMED = "userDistanceMedium";
	private static final String ROW_DFAR = "userDistanceFar";
	private static final String ROW_CHEAP = "uCheap";
	private static final String ROW_MED = "uMed";
	private static final String ROW_EXP = "uExp";
	private static final String TABLE_NAME = "userTag";
	
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		mUserTagDb = new DataAccess(this);
		mUserTagDb.setMDbCreate(mDbCreate);
		mUserTagDb.setMDbName(mDbN);
		mUserTagDb.open();
		setContentView(R.layout.usertag_edit);
		
		mUserTagName = (EditText) findViewById(R.id.title);
		mUserMonetaryValueCheap = (EditText) findViewById(R.id.userTagM);
		mUserMonetaryValueMedium = (EditText) findViewById(R.id.userTagM1);
		mUserMonetaryValueExpensive = (EditText) findViewById(R.id.userTagM2);   
		mUserDistanceValueNear = (EditText) findViewById(R.id.userTagD1);
		mUserDistanceValueMedium = (EditText) findViewById(R.id.userTagD2);
		mUserDistanceValueFar = (EditText) findViewById(R.id.userTagD3);
		mUserPreference = (EditText) findViewById(R.id.userTagM2);  
		
	    Button confirmButton = (Button) findViewById(R. id.confirm);
	    mRowId = null;
        mRowId = icicle != null ? icicle.getLong(ROW_ID) : null;
        if (mRowId == null) {
            Bundle extras = getIntent().getExtras();            
            mRowId = extras != null ? extras.getLong(ROW_ID) : null;
        }
        populateFields();
        confirmButton.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View view) {
                    setResult(RESULT_OK);
                    finish();
                }
            }
          
        );   
		
	}
	
	 private void populateFields() {
	    	if (mRowId != null) {
	            String pCheap,pMed,pExp,dN,dMed,dFar;
	    		Cursor tag = fetchTag(mRowId);
	            startManagingCursor(tag);
	            mUserTagName.setText(tag.getString(
	    	            tag.getColumnIndex(ROW_TITLE)));
	            pCheap = tag.getColumnIndex(ROW_CHEAP) !=0?( tag.getString(tag.getColumnIndex(ROW_CHEAP))) :"";
	            pMed = tag.getColumnIndex(ROW_MED) != 0 ? (tag.getString(tag.getColumnIndex(ROW_MED))) : "";
	            pExp = tag.getColumnIndex(ROW_EXP) != 0 ? (tag.getString(tag.getColumnIndex(ROW_EXP))) : "";
	            dN =	tag.getColumnIndex(ROW_DNEAR) != 0 ? tag.getString(tag.getColumnIndex(ROW_DNEAR)) : "";
	            dMed = tag.getColumnIndex(ROW_DMED) !=0?( tag.getString(tag.getColumnIndex(ROW_DMED))) :"";
	            dFar = 	tag.getColumnIndex(ROW_DFAR) !=0?( tag.getString(tag.getColumnIndex(ROW_DFAR))) :"";
	            		
	            mUserMonetaryValueCheap.setText(pCheap);
	            mUserMonetaryValueMedium.setText(pMed);
	            mUserMonetaryValueExpensive.setText(pExp);
	        	mUserDistanceValueNear.setText(dN);
	        	mUserDistanceValueMedium.setText(dMed);
	        	mUserDistanceValueFar.setText(dFar);
	        	//mUserPreference.setText(pCheap);
	            //mBodyText.setText(note.getString(
	              //      note.getColumnIndex(NotesDbAdapter.KEY_BODY)));
	    	}
	    }	
	
	 /**
	     * Return a Cursor positioned at the userTag that matches the given rowId
	     * @param rowId id of userTag to retrieve
	     * @return Cursor positioned to matching userTag, if found
	     * @throws SQLException if note could not be found/retrieved
	     */
	    public Cursor fetchTag(long rowId)  {
	    	Cursor result = null;
	    	try{
	        result = mUserTagDb.query(new String[] {
	                ROW_ID, ROW_TITLE, ROW_CHEAP,ROW_MED,ROW_EXP,ROW_DNEAR,ROW_DMED,ROW_DFAR}, ROW_ID + "=" + rowId,TABLE_NAME);
	        
	    	}catch(SQLException ex){
	    		System.err.println("Me cago en todo");
	    	}
	    	return result;
	    }	
	    @Override
	    protected void onFreeze(Bundle outState) {
	        super.onFreeze(outState);
	        outState.putLong(ROW_ID, mRowId);
	    }
	    @Override
	    protected void onPause() {
	        super.onPause();
	        saveState();
	    }
	    @Override
	    protected void onResume() {
	        super.onResume();
	        populateFields();
	    }
	    private void saveState() {
	       
	        if (mRowId == null) {
	            long id = createTag();
	            if (id > 0) {
	                mRowId = id;
	            }
	        } else {
	            updateTag();
	        }
	    }
	    
	    /**
	     * Return a long with the id of the new item on database.
	     * @return
	     */
	    
	    public long createTag(){
	    	ContentValues initialValues = new ContentValues();
	        initialValues.put(ROW_TITLE,mUserTagName.getText().toString());
	        initialValues.put(ROW_CHEAP,mUserMonetaryValueCheap.getText().toString() );
	        initialValues.put(ROW_MED,mUserMonetaryValueMedium.getText().toString() );
	        initialValues.put(ROW_EXP,mUserMonetaryValueExpensive.getText().toString() );
	        initialValues.put(ROW_DNEAR,mUserMonetaryValueCheap.getText().toString() );
	        initialValues.put(ROW_DMED,mUserMonetaryValueMedium.getText().toString() );
	        initialValues.put(ROW_DFAR,mUserMonetaryValueExpensive.getText().toString() );

	        
	        return mUserTagDb.insert(TABLE_NAME, initialValues);
	    }
	    
	    public boolean updateTag(){
	    	ContentValues args = new ContentValues();
	        args.put(ROW_TITLE,mUserTagName.getText().toString());
	        args.put(ROW_CHEAP, mUserMonetaryValueCheap.getText().toString() );
	        args.put(ROW_MED,mUserMonetaryValueMedium.getText().toString());
	        args.put(ROW_EXP, mUserMonetaryValueExpensive.getText().toString());
	        args.put(ROW_DNEAR, mUserDistanceValueNear.getText().toString() );
	        args.put(ROW_DMED,mUserDistanceValueMedium.getText().toString());
	        args.put(ROW_DFAR, mUserDistanceValueFar.getText().toString());
	        return mUserTagDb.update(TABLE_NAME, args, ROW_ID + "=" + mRowId);
	    }
}
