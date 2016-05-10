package nexfi.cn.nexfi_android_sdk.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import nexfi.cn.nexfi_android_sdk.bean.BaseMessage;
import nexfi.cn.nexfi_android_sdk.bean.UserMessage;
import nexfi.cn.nexfi_android_sdk.helper.DBHelper;


/**
 * @author gengbaolong.
 */


public class DBDao {
    private Context context;
    private DBHelper helper;

    public DBDao(Context context) {
        this.context = context;
        helper = new DBHelper(context);
    }

    /**
     * 保存用户数据
     *
     * @param userMessage
     * @param baseMessage
     */
    public void add(BaseMessage baseMessage, UserMessage userMessage) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("messageType", baseMessage.messageType);
        values.put("sendTime", baseMessage.sendTime);
        values.put("chat_id", baseMessage.chat_id);
        values.put("uuid", baseMessage.uuid);
        baseMessage.userMessage = userMessage;
        values.put("nodeId", userMessage.nodeId);
        values.put("userId", userMessage.userId);
        values.put("userNick", userMessage.userNick);
        values.put("userAge", userMessage.userAge);
        values.put("userGender", userMessage.userGender);
        values.put("userAvatar", userMessage.userAvatar);
        db.insert("userInformation", null, values);
        db.close();
        //有新用户上线
        context.getContentResolver().notifyChange(
                Uri.parse("content://www.nexfi.cn"), null);
    }


    /**
     * 根据userId修改数据库中原有用户信息
     *
     * @param userMessage
     * @param userId
     */
    public void updateUserInfoByUserId(UserMessage userMessage, String userId) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nodeId", userMessage.nodeId);
        values.put("userId", userMessage.userId);
        values.put("userNick", userMessage.userNick);
        values.put("userAge", userMessage.userAge);
        values.put("userGender", userMessage.userGender);
        values.put("userAvatar", userMessage.userAvatar);
        db.update("userInformation", values, "userId=?", new String[]{userId});
        db.close();
        context.getContentResolver().notifyChange(
                Uri.parse("content://www.nexfi.cn"), null);
    }


    /**
     * 查找所有用户(把用户自身排除)
     *
     * @param userId
     * @return
     */
    public List<UserMessage> findAllUsers(String userId) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("userInformation", null, null, null, null, null, null);
        List<UserMessage> mDatas = new ArrayList<UserMessage>();
        while (cursor.moveToNext()) {
            UserMessage user = new UserMessage();
            user.nodeId = cursor.getLong(cursor.getColumnIndex("nodeId"));
            user.userId = cursor.getString(cursor.getColumnIndex("userId"));
            user.userNick = cursor.getString(cursor.getColumnIndex("userNick"));
            user.userAge = cursor.getInt(cursor.getColumnIndex("userAge"));
            user.userGender = cursor.getString(cursor.getColumnIndex("userGender"));
            user.userAvatar = cursor.getInt(cursor.getColumnIndex("userAvatar"));
            if (!userId.equals(user.userId)) {
                mDatas.add(user);
            }
        }
        cursor.close();
        db.close();
        return mDatas;
    }

    /**
     * 根据用户id查找用户
     *
     * @param userId
     * @return
     */
    public UserMessage findUserByUserId(String userId) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("userInformation", null, "userId=?", new String[]{userId}, null, null, null);
        if (cursor.moveToNext()) {
            UserMessage user = new UserMessage();
            user.nodeId = cursor.getLong(cursor.getColumnIndex("nodeId"));
            user.userId = cursor.getString(cursor.getColumnIndex("userId"));
            user.userNick = cursor.getString(cursor.getColumnIndex("userNick"));
            user.userAge = cursor.getInt(cursor.getColumnIndex("userAge"));
            user.userGender = cursor.getString(cursor.getColumnIndex("userGender"));
            user.userAvatar = cursor.getInt(cursor.getColumnIndex("userAvatar"));
            return user;
        }
        return null;
    }


    /**
     * 根据nodeId查找用户
     *
     * @param nodeId
     * @return
     */
    public UserMessage findUserByNodeId(long nodeId) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("userInformation", null, "nodeId=?", new String[]{nodeId + ""}, null, null, null);
        if (cursor.moveToNext()) {
            UserMessage user = new UserMessage();
            user.nodeId = cursor.getLong(cursor.getColumnIndex("nodeId"));
            user.userId = cursor.getString(cursor.getColumnIndex("userId"));
            user.userNick = cursor.getString(cursor.getColumnIndex("userNick"));
            user.userAge = cursor.getInt(cursor.getColumnIndex("userAge"));
            user.userGender = cursor.getString(cursor.getColumnIndex("userGender"));
            user.userAvatar = cursor.getInt(cursor.getColumnIndex("userAvatar"));
            return user;
        }
        return null;
    }


    /**
     * 根据用户id查找是否有相同数据
     *
     * @param userId
     * @return
     */
    public boolean findSameUserByUserId(String userId) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("userInformation", null, "userId=?", new String[]{userId}, null, null, null);
        if (cursor.moveToNext()) {
            return true;
        }
        return false;
    }


    /**
     * 根据userId删除用户信息
     */
    public void deleteUserByNodeId(long nodeId) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int row = db.delete("userInformation", "nodeId = ?",
                new String[]{nodeId + ""});
        db.close();
        //有用户下线
        context.getContentResolver().notifyChange(
                Uri.parse("content://www.nexfi.cn"), null);
    }


}
