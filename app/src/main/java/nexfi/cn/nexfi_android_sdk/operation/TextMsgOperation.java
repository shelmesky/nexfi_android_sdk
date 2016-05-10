package nexfi.cn.nexfi_android_sdk.operation;

import android.util.Log;
import nexfi.cn.nexfi_android_sdk.listener.ReceiveTextMsgListener;
import nexfi.cn.nexfi_android_sdk.listener.SendTextMsgListener;


/**
 * @author  gengbaolong,Mark.
 */

public class TextMsgOperation {

    SendTextMsgListener mSendTextMsgListener=null;
    ReceiveTextMsgListener mReceiveTextMsgListener=null;


    /**
     * 发送文本消息
     * @param textMsg
     * @param userId
     */
    public void sendTextMessage(String textMsg,String userId){

    }

    /**
     * 接收文本消息
     */
    public void receiveTextMessage(Object obj){
        Log.e("TAG","====接收方法------------------------");
        if(null!=mReceiveTextMsgListener){
            Log.e("TAG","====接口回调方法------------------------");
            mReceiveTextMsgListener.onReceiveTextMsg(obj);
        }
    }


    //设置回调接口(监听器)的方法
    public void setSendTextMsgListener(SendTextMsgListener sendTextMsgListener) {
        mSendTextMsgListener = sendTextMsgListener;
    }

    public void setReceiveTextMsgListener(ReceiveTextMsgListener receiveTextMsgListener) {
        mReceiveTextMsgListener = receiveTextMsgListener;
    }

}
