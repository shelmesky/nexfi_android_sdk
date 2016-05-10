package nexfi.cn.nexfi_android_sdk.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import nexfi.cn.nexfi_android_sdk.application.BleApplication;

/**
 * @author  gengbaolong,Mark.
 */


public class ObjectBytesUtils {
    /**
     *对象转化为字节
     * @param obj
     * @return
     */
    public static byte[] ObjectToByte(Object obj) {
        byte[] bytes = null;
        try {
            // object to bytearray
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);

            bytes = bo.toByteArray();

            bo.close();
            oo.close();
        } catch (Exception e) {
            BleApplication.getExceptionLists().add(e);
            BleApplication.getCrashHandler().saveCrashInfo2File(e);
            e.printStackTrace();
        }
        return bytes;
    }


    /**
     * 字节转化为对象
     * @param bytes
     * @return
     */
    public static Object ByteToObject(byte[] bytes) {
        Object obj = null;
        try {
            // bytearray to object
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);
            obj = oi.readObject();
            bi.close();
            oi.close();
        } catch (Exception e) {
            BleApplication.getExceptionLists().add(e);
            BleApplication.getCrashHandler().saveCrashInfo2File(e);
            e.printStackTrace();
        }
        return obj;
    }
}
