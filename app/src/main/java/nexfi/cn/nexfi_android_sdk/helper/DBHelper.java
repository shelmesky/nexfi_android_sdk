package nexfi.cn.nexfi_android_sdk.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * @author gengbaolong.
 */

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "nexfi.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //用户信息表
        db.execSQL("create table userInformation (_id integer primary key autoincrement,messageType Integer(20),sendTime varchar(20),chat_id varchar(20),nodeId varchar(20),userId varchar(20),userNick varchar(20),userAge Integer(20),userGender varchar(20),userAvatar Integer(20),uuid varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
