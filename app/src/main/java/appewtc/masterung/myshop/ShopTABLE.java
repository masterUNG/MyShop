package appewtc.masterung.myshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 3/16/15 AD.
 */
public class ShopTABLE {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeDatabase, readDatabase;

    public static final String SHOP_TABLE = "shopTABLE";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SHOP = "Shop";
    public static final String COLUMN_DETAIL = "Detail";
    public static final String COLUMN_BLOCK = "Block";
    public static final String COLUMN_FLOOR = "Floor";
    public static final String COLUMN_ICON = "Icon";

    public ShopTABLE(Context context) {

        //Connected Database
        objMyOpenHelper = new MyOpenHelper(context);
        writeDatabase = objMyOpenHelper.getWritableDatabase();
        readDatabase = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

    //Read All Data
    public Cursor readAllData() {

        Cursor objCursor = readDatabase.query(SHOP_TABLE, new String[]{COLUMN_ID, COLUMN_SHOP, COLUMN_DETAIL, COLUMN_BLOCK, COLUMN_FLOOR, COLUMN_ICON}, null, null, null, null, null);

        if (objCursor != null) {
            objCursor.moveToFirst();
        }


        return objCursor;
    }


    //Search Shop
    public String[] searchShop(String strShop) {

        try {

            String strResult[] = null;
            Cursor objCursor = readDatabase.query(SHOP_TABLE,
                    new String[] {COLUMN_ID, COLUMN_SHOP, COLUMN_DETAIL, COLUMN_BLOCK, COLUMN_FLOOR, COLUMN_ICON},
                    COLUMN_SHOP + "=?",
                    new String[] {String.valueOf(strShop)},
                    null, null, null, null);

            if (objCursor != null) {
                if (objCursor.moveToFirst()) {

                    strResult = new String[objCursor.getColumnCount()];
                    strResult[0] = objCursor.getString(0);
                    strResult[1] = objCursor.getString(1);
                    strResult[2] = objCursor.getString(2);
                    strResult[3] = objCursor.getString(3);
                    strResult[4] = objCursor.getString(4);
                    strResult[5] = objCursor.getString(5);

                }   // if2
            }   // if1

            objCursor.close();
            return strResult;

        } catch (Exception e) {
            return null;
        }

       // return new String[0];
    }


    //Add Value to SQLite
    public long addValueToSQLite(String strShop, String strDetail, String strBlock, String strFloor, String strIcon) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_SHOP, strShop);
        objContentValues.put(COLUMN_DETAIL, strDetail);
        objContentValues.put(COLUMN_BLOCK, strBlock);
        objContentValues.put(COLUMN_FLOOR, strFloor);
        objContentValues.put(COLUMN_ICON, strIcon);

        return writeDatabase.insert(SHOP_TABLE, null, objContentValues);
    }

}   // Main Class
