package nexfi.cn.nexfi_android_sdk.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author  gengbaolong,Mark.
 */

public class TimeUtils {
    /**
     * 获得发送时间
     */
    public static String getNowTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//TODO
        Date date=new Date();
        String dateTime=format.format(date);
        return dateTime;
    }
}
