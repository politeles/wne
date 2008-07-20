/**
 * 
 */
package com.google.android.wne;





import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Menu.Item;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * @author Jose Enrique
 *
 */
public class UserTagList extends ListActivity {
	/* Static int define to ACTIVITY_CREATE */
	private static final int ACTIVITY_CREATE =0;
	/* Static int define to ACTIVITY_EDIT */
	private static final int ACTIVITY_EDIT = 1;
	/* Static int define to INSERT_ID */
	private static final int INSERT_ID=Menu.FIRST;
	/* Static int define to DELETE_ID */
	private static final int DELETE_ID = Menu.FIRST+1;
	/* Static int to define ACTIVE_ID */
	private static final int ACTIVE_ID = Menu.FIRST+2;
	/* Database access */
	private DataAccess mDb;
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
	/* activeTag for an user (if active any) */
	private long activeTag;
	
	/* (non-Javadoc)
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
	
		super.onListItemClick(l, v, position, id);
        Intent i = new Intent(this, UserTag.class);
        i.putExtra(ROW_ID, id);
        startSubActivity(i, ACTIVITY_EDIT);
	}



	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, java.lang.String, android.os.Bundle)
	 *
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			String data, Bundle extras) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data, extras);
		this.fillUserTagList();
	}
*/


	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);
		setContentView(R.layout.usertag_list);
		mDb = new DataAccess(this);
		mDb.setMDbCreate(mDbCreate);
		mDb.setMDbName(mDbN);
		mDb.open();
		activeTag = 0;
		fillUserTagList();
		
		
	}



	/* (non-Javadoc)
	 * @see android.app.Activity#onMenuItemSelected(int, android.view.Menu.Item)
	 */
	@Override
	public boolean onMenuItemSelected(int featureId, Item item) {
		// TODO Auto-generated method stub
		switch(item.getId()) {
        case INSERT_ID:
            createTag();
            return true;
        case DELETE_ID:
            deleteTag(getListView().getSelectedItemId());
            fillUserTagList();
            return true;
        case ACTIVE_ID:
        	activeTag = getListView().getSelectedItemId();
        	return true;
        }
		return super.onMenuItemSelected(featureId, item);
	}

	/**
	 * Create a new tag.
	 */
	private void createTag(){
		Intent i = new Intent(this,UserTag.class);
		startSubActivity(i,ACTIVITY_CREATE);
		
	}
	/**
	 * Delete a Tag by rowId
	 */
	private void deleteTag(long rowId){
		mDb.delete(TABLE_NAME,	ROW_ID + "=" + rowId);
	}
	/**
	 * Method to fill the user tag list.
	*/
	private void fillUserTagList(){
		// Get all of the rows from the database and create the item list
    	Cursor tagsCursor;
    	
        tagsCursor = fetchAllTags();
        startManagingCursor(tagsCursor);
        
        // Create an array to specify the fields we want to display in the list (only TITLE)
        String[] from = new String[]{ROW_TITLE};
        
        // and an array of the fields we want to bind those fields to (in this case just text1)
        int[] to = new int[]{R.id.text1};
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter tags = 
        	    new SimpleCursorAdapter(this, R.layout.usertag_row, tagsCursor, from, to);
        setListAdapter(tags);
	}
	/**
	 * Method that return a Cursor pointing all tags.
 	 * @return A Cursor containing all tags.
	 */
	private Cursor fetchAllTags(){
		return mDb.query( new String[] {
                ROW_ID, ROW_TITLE, ROW_CHEAP,ROW_MED,ROW_EXP,ROW_DNEAR,ROW_DMED,ROW_DFAR},null,TABLE_NAME);
		
		
	}



	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		
		menu.add(0, INSERT_ID, R.string.newTag);
        menu.add(0, DELETE_ID, R.string.delete);
        menu.add(0, ACTIVE_ID, R.string.userTagActivate);
		return true;
	}



	/* (non-Javadoc)
	 * @see android.app.Activity#onFreeze(android.os.Bundle)
	 */
	@Override
	protected void onFreeze(Bundle outState) {
		// TODO Auto-generated method stub
		super.onFreeze(outState);
		outState.putLong(ROW_ID, activeTag);
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
		fillUserTagList();
	}
	
	
	
}
