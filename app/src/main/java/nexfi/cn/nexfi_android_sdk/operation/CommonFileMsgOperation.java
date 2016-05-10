package nexfi.cn.nexfi_android_sdk.operation;

import nexfi.cn.nexfi_android_sdk.listener.ReceiveCommonFileListener;
import nexfi.cn.nexfi_android_sdk.listener.SendCommonFileListener;

/**
 * @author  gengbaolong,Mark.
 */

public class CommonFileMsgOperation {

    SendCommonFileListener mSendCommonFileListener=null;
    ReceiveCommonFileListener mReceiveCommonFileListener=null;

    /**
     * 发送文件
     * @param filePath
     * @param userId
     */
    private void sendCommonFileMsg(String filePath,String userId){

    }


    /***
     * 接收文件
     */
    private void receiveCommonFileMsg(){

    }


    /**
     * 发送图片文件
     * @param filePath
     * @param userId
     */
    private void sendImageFileMsg(String filePath,String userId){

    }

    /**
     * 接收图片
     */
    private void receiveImageFileMsg(){

    }


    //设置回调接口(监听器)的方法
    private void setSendCommonFileListener(SendCommonFileListener sendCommonFileListener) {
        mSendCommonFileListener = sendCommonFileListener;
    }

    private void setReceiveCommonFileListener(ReceiveCommonFileListener receiveCommonFileListener) {
        mReceiveCommonFileListener = receiveCommonFileListener;
    }

}
