package appewtc.masterung.myshop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;


public class AddNewShopActivity extends ActionBarActivity {

    //Explicit
    private EditText edtNameShop, edtDetail, edtBlock;
    private Spinner floorSpinner;
    private ImageView imvIcon;
    private String strNameShop, strDetail, strBlock, strFloor, strIcon;
    private int intShowIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_shop);

        //InitialWidget
        initialWidget();

        //SetUp Floor
        setUpFloor();

        imvIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert();

            }   // event

        });

    }   // onCreate

    private void showAlert() {
        CharSequence mySequence[] = {"Build1", "Build2",
                "Build3", "Build4", "Build5", "Build6",
                "Build7", "Build8", "Build9", "Build10"};

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.icon_question);
        objBuilder.setTitle("Choose Item ?");
        objBuilder.setCancelable(false);
        objBuilder.setSingleChoiceItems(mySequence, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case 0:
                        intShowIcon = R.drawable.build1;
                        break;
                    case 1:
                        intShowIcon = R.drawable.build2;
                        break;
                    case 2:
                        intShowIcon = R.drawable.build3;
                        break;
                    case 3:
                        intShowIcon = R.drawable.build4;
                        break;
                    case 4:
                        intShowIcon = R.drawable.build5;
                        break;
                    case 5:
                        intShowIcon = R.drawable.build6;
                        break;
                    case 6:
                        intShowIcon = R.drawable.build7;
                        break;
                    case 7:
                        intShowIcon = R.drawable.build8;
                        break;
                    case 8:
                        intShowIcon = R.drawable.build9;
                        break;
                    case 9:
                        intShowIcon = R.drawable.build10;
                        break;

                }   // swich
                imvIcon.setImageResource(intShowIcon);
                dialog.dismiss();
            }
        });
        AlertDialog objAlertDialog = objBuilder.create();
        objAlertDialog.show();
    }

    private void setUpFloor() {
        final String strShowFloor[] = new String[]{"Ground", "floor 2", "floor 3", "floor 4"};
        ArrayAdapter<String> objArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strShowFloor);
        floorSpinner.setAdapter(objArrayAdapter);

        floorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strFloor = strShowFloor[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strFloor = strShowFloor[0];
            }
        });
    }

    private void initialWidget() {
        edtNameShop = (EditText) findViewById(R.id.edtNameShop);
        edtDetail = (EditText) findViewById(R.id.edtDetail);
        edtBlock = (EditText) findViewById(R.id.edtBlock);
        floorSpinner = (Spinner) findViewById(R.id.spinner);
        imvIcon = (ImageView) findViewById(R.id.imvIcon);
    }

    public void clickSaveShop(View view) {

        strNameShop = edtNameShop.getText().toString().trim();
        strDetail = edtDetail.getText().toString().trim();
        strBlock = edtBlock.getText().toString().trim();

        //Check Space
        if (strNameShop.equals("") || strDetail.equals("") || strBlock.equals("")) {

            //Have Space
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.errorDialog(AddNewShopActivity.this, "Have Space", "Please Fill All Every Blank");

        } else {

        }   // if

    }   // clickSaveShop


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_shop, menu);
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
