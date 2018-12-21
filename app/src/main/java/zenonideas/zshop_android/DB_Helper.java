package zenonideas.zshop_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class DB_Helper extends SQLiteOpenHelper {

    public static final String DB_Name = "ZShop.db";
    public static final String Table_Login = "login1";
    public static final String Column_ID = "id";
    public static final String Column_UN = "un";
    public static final String Column_PW = "pw";
    public static final String Column_UID = "uid";
    public static final String Column_RID = "rid";
    public static final String Column_ST = "st";
    private HashMap hp;

    public DB_Helper(Context context) {
        super(context, DB_Name, null, 1);
//        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table 'login1' (id integer primary key, un text,pw text,uid text, rid text,st text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS login1");
        onCreate(sqLiteDatabase);
    }


    public Cursor getData(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from login1 where id=" + id + "", null);
        return res;
    }

    public int signOut() {
        SQLiteDatabase db = getReadableDatabase();
        int res = db.delete("login1",null,null);
        return res;
    }

    public Cursor getDataCount() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from login1", null);
        return res;
    }

    public boolean insertLogin(int id, String un, String pw, String uid, String rid, String st) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("un", un);
        contentValues.put("pw", pw);
        contentValues.put("uid", uid);
        contentValues.put("rid", rid);
        contentValues.put("st", st);
        db.insert("login1", null, contentValues);
        return true;
    }
}
