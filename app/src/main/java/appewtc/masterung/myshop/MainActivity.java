package appewtc.masterung.myshop;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends ActionBarActivity {

    //Explicit
    private ShopTABLE objShopTABLE;
    private EditText edtSearch;
    private String strSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        edtSearch = (EditText) findViewById(R.id.edtSearchShop);

        //Connected Database
        objShopTABLE = new ShopTABLE(this);

        //Test Add value to SQLite OK ?
       // objShopTABLE.addValueToSQLite("shop", "detail", "block", "floor", "Icon");

        //Delete All data
        deleteAllData();

        //Synchronize JSON to SQLite
        synJSONtoSQLite();




    }   // onCreate

    private void deleteAllData() {
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase("My_Shop.db", MODE_PRIVATE, null);
        objSqLiteDatabase.delete("shopTABLE", null, null);
    }

    private void synJSONtoSQLite() {

        //Setup Policy
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy objPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(objPolicy);
        }   // if

        //Create InputStream
        InputStream objInputStream = null;
        String strJSON = "";

        try {

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://swiftcodingthai.com/shop/get_data_master.php");
            HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
            HttpEntity objHttpEntity = objHttpResponse.getEntity();
            objInputStream = objHttpEntity.getContent();

        } catch (Exception e) {
            Log.d("shop", "InputStream ==> " + e.toString());
        }

        // Create strJSON
        try {

            BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
            StringBuilder objStringBuilder = new StringBuilder();
            String strLine = null;

            while ((strLine = objBufferedReader.readLine()) != null) {
                objStringBuilder.append(strLine);
            }   // while

            objInputStream.close();
            strJSON = objStringBuilder.toString();


        } catch (Exception e) {
            Log.d("shop", "strJSON ==> " + e.toString());
        }


        //Update JSON to SQLite
        try {

            final JSONArray objJsonArray = new JSONArray(strJSON);
            for (int i = 0; i < objJsonArray.length(); i++) {

                JSONObject objJSONObject = objJsonArray.getJSONObject(i);
                String strShop = objJSONObject.getString("Shop");
                String strDetail = objJSONObject.getString("Detail");
                String strBlock = objJSONObject.getString("Block");
                String strFloor = objJSONObject.getString("Floor");
                String strIcon = objJSONObject.getString("Icon");

                objShopTABLE.addValueToSQLite(strShop, strDetail, strBlock, strFloor, strIcon);

            }   // for

        } catch (Exception e) {
            Log.d("shop", "Updata SQLite ==> " + e.toString());
        }




    }   // synJSONtoSQLite

    public void clickSearch(View view) {

        strSearch = edtSearch.getText().toString().trim();

        if (strSearch.equals("")) {
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.errorDialog(MainActivity.this, "Have Space", "Please Fill Search Shop on the Blank");
        } else {

            searchShop();

        }

    }   //clickSearch

    private void searchShop() {
        try {

            String strMyResult[] = objShopTABLE.searchShop(strSearch);
            String strDetail = strMyResult[2];
            String strBlock = strMyResult[3];
            String strFloor = strMyResult[4];
            String strIcon = strMyResult[5];

            Intent objIntent = new Intent(MainActivity.this, ResultActivity.class);
            objIntent.putExtra("Shop", strSearch);
            objIntent.putExtra("Detail", strDetail);
            objIntent.putExtra("Block", strBlock);
            objIntent.putExtra("Floor", strFloor);
            objIntent.putExtra("Icon", strIcon);
            startActivity(objIntent);

        } catch (Exception e) {
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.errorDialog(MainActivity.this, "No Shop", "No This " + strSearch + " in my Database");
        }
    }

    public void clickListAllShop(View view) {

        Intent objIntent = new Intent(MainActivity.this, ListAllShopActivity.class);
        startActivity(objIntent);

    }

    public void clickAddNewShop(View view) {
        Intent objIntent = new Intent(MainActivity.this, AddNewShopActivity.class);
        startActivity(objIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
