package nexfi.cn.nexfi_android_sdk.operation;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.underdark.transport.Link;
import nexfi.cn.nexfi_android_sdk.application.BleApplication;
import nexfi.cn.nexfi_android_sdk.bean.BaseMessage;
import nexfi.cn.nexfi_android_sdk.bean.MessageType;
import nexfi.cn.nexfi_android_sdk.bean.UserMessage;
import nexfi.cn.nexfi_android_sdk.dao.DBDao;
import nexfi.cn.nexfi_android_sdk.util.Debug;
import nexfi.cn.nexfi_android_sdk.util.ObjectBytesUtils;
import nexfi.cn.nexfi_android_sdk.util.TimeUtils;

/**
 * @author Mark .
 */

public class Login {

    /**
     * 用户登陆。
     *
     * @param userNick
     * @param userAge
     * @param userAvatar
     * @param userId
     * @param userGender
     */
    public static void userLogin(String userNick, int userAvatar, int userAge, String userGender, String userId) {
        DBDao DBDao = new DBDao(BleApplication.getContext());
        BaseMessage baseMessage = new BaseMessage();
        baseMessage.messageType = MessageType.REQUEST_USER_INFO;
        UserMessage userMessage = new UserMessage();
        userMessage.userNick = userNick;
        userMessage.userAvatar = userAvatar;
        userMessage.userAge = userAge;
        userMessage.userGender = userGender;
        userMessage.userId = userId;//每个用户第一次登录的时候生成一个用户id
        baseMessage.userMessage = userMessage;
        DBDao.add(baseMessage, userMessage);
    }


    /**
     * 开发者登陆。
     *
     * @param key
     */

    public static void validateAccessToken(String key) {

    }

    /**
     * 获得唯一的用户ID。
     */
    public static String getUserIdOfFirstLogin() {
        return UUID.randomUUID().toString();
    }


    /**
     * 蓝牙连接配对时，搜索到设备后就发送请求，并将自己的信息携带过去。
     *
     * @param link
     * @param links 从Node中的getLinks()方法获得。
     */
    public static byte[] LinkConnected(Link link, ArrayList<Link> links, DBDao DBDao, String userId) {
        if (null != links && links.size() > 0) {
            BaseMessage baseMessage = new BaseMessage();
            baseMessage.messageType = MessageType.REQUEST_USER_INFO;
            baseMessage.sendTime = TimeUtils.getNowTime();
            UserMessage userMessage = DBDao.findUserByUserId(userId);
            if (Debug.DEBUG) {
                Log.e("TAG", userMessage + "------连接的nodeId----------------------------link---" + link);
            }
            userMessage.nodeId = link.getNodeId();
            if (Debug.DEBUG) {
                Log.e("TAG", userMessage.nodeId + "------连接的nodeId-------------------------------");//7608227584490209377
            }
            baseMessage.userMessage = userMessage;
            DBDao.updateUserInfoByUserId(userMessage, userId);
            byte[] data = ObjectBytesUtils.ObjectToByte(baseMessage);
            return data;
        }
        return null;
    }


    /**
     * 获取用户列表
     *
     * @param DBDao
     * @param userId
     * @return
     */

    public static List<UserMessage> getUserlist(DBDao DBDao, String userId) {
        return DBDao.findAllUsers(userId);
    }

}
