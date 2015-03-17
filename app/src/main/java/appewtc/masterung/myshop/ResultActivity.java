package appewtc.masterung.myshop;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class ResultActivity extends ActionBarActivity {

    //Explicit
    private ImageView imvIcon;
    private TextView txtShop, txtDetail, txtBlock, txtFloor;
    private String strShop, strDetail, strBlock, strFloor, strIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Maping Widget
        mapingWidget();

        //Get Value from Intent
        getValueFromIntent();

        //Setup TextView
        setUpTextView();

        //Setup Icon
        setUPIcon();

    }   // onCreate

    private void setUPIcon() {
        int intIcon = Integer.parseInt(strIcon);
        switch (intIcon) {
            case 2130837556:
                imvIcon.setImageResource(R.drawable.build1);
                break;
            case 2130837558:
                imvIcon.setImageResource(R.drawable.build2);
                break;
            case 2130837559:
                imvIcon.setImageResource(R.drawable.build3);
                break;
            case 2130837560:
                imvIcon.setImageResource(R.drawable.build4);
                break;
            case 2130837561:
                imvIcon.setImageResource(R.drawable.build5);
                break;
            case 2130837562:
                imvIcon.setImageResource(R.drawable.build6);
                break;
            case 2130837563:
                imvIcon.setImageResource(R.drawable.build7);
                break;
            case 2130837564:
                imvIcon.setImageResource(R.drawable.build8);
                break;
            case 2130837565:
                imvIcon.setImageResource(R.drawable.build9);
                break;
            case 2130837557:
                imvIcon.setImageResource(R.drawable.build10);
                break;
            default:
                imvIcon.setImageResource(R.drawable.icon_question);
                break;

        }   // switch
    }

    private void setUpTextView() {
        txtShop.setText("Shop = " + strShop);
        txtDetail.setText("Detail = " + strDetail);
        txtBlock.setText("Block = " + strBlock);
        txtFloor.setText("Floor = " + strFloor);
    }

    private void getValueFromIntent() {
        strShop = getIntent().getExtras().getString("Shop");
        strDetail = getIntent().getExtras().getString("Detail");
        strBlock = getIntent().getExtras().getString("Block");
        strFloor = getIntent().getExtras().getString("Floor");
        strIcon = getIntent().getExtras().getString("Icon");
    }

    private void mapingWidget() {
        imvIcon = (ImageView) findViewById(R.id.imvShowIcon);
        txtShop = (TextView) findViewById(R.id.txtShowShop);
        txtDetail = (TextView) findViewById(R.id.txtShowDetail);
        txtBlock = (TextView) findViewById(R.id.txtShowBlock);
        txtFloor = (TextView) findViewById(R.id.txtShowFloor);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
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
