package appewtc.masterung.myshop;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class ListAllShopActivity extends ListActivity{

    //Explicit
    private ShopTABLE objShopTABLE;
    private SimpleCursorAdapter objSimpleCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_all_shop);

        objShopTABLE = new ShopTABLE(this);
        Cursor objCursor = objShopTABLE.readAllData();
        String[] from = new String[]{ShopTABLE.COLUMN_SHOP};
        int[] target = new int[]{R.id.txtListShop};
        objSimpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.activity_list_all_shop, objCursor, from, target);
        setListAdapter(objSimpleCursorAdapter);

    }   // onCreate

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Cursor objCursor = (Cursor) l.getItemAtPosition(position);
        String strShop = objCursor.getString(objCursor.getColumnIndex(ShopTABLE.COLUMN_SHOP));
        String strDetail = objCursor.getString(objCursor.getColumnIndex(ShopTABLE.COLUMN_DETAIL));
        String strBlock = objCursor.getString(objCursor.getColumnIndex(ShopTABLE.COLUMN_BLOCK));
        String strFloor = objCursor.getString(objCursor.getColumnIndex(ShopTABLE.COLUMN_FLOOR));
        String strIcon = objCursor.getString(objCursor.getColumnIndex(ShopTABLE.COLUMN_ICON));

        Intent objIntent = new Intent(ListAllShopActivity.this, ResultActivity.class);
        objIntent.putExtra("Shop", strShop);
        objIntent.putExtra("Detail", strDetail);
        objIntent.putExtra("Block", strBlock);
        objIntent.putExtra("Floor", strFloor);
        objIntent.putExtra("Icon", strIcon);
        startActivity(objIntent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_all_shop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class
