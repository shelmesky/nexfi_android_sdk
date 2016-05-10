package nexfi.cn.nexfi_android_sdk.util;

import android.content.Context;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import nexfi.cn.nexfi_android_sdk.application.BleApplication;

/**
 * @author Mark, gengbaolong.
 */


public class Debug {
    public static boolean DEBUG = false;

    public static void debugLog(String log, Context con) {
        if (DEBUG) {
            Log.e("TAG", log);
            BleApplication.getLogLists().add(log);
            saveDebugLog(log);
        }
    }


    /**
     * 保存日志
     *
     * @param excpMessage
     */
    public static void saveDebugLog(String excpMessage) {
        String errorlog = "errorlog.txt";
        String savePath = "";
        String logFilePath = "";
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            // 判断是否挂载了SD卡
            String storageState = Environment.getExternalStorageState();
            if (storageState.equals(Environment.MEDIA_MOUNTED)) {
                savePath =
                        Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
                                + "Debug" + File.separator + "Log/";
                File file = new File(savePath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                logFilePath = savePath + errorlog;
            }
            // 没有挂载SD卡，无法写文件
            if (logFilePath == "") {
                return;
            }
            File logFile = new File(logFilePath);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            fw = new FileWriter(logFile, true);
            pw = new PrintWriter(fw);
            pw.println("--" + (DateFormat.format("yyyy-MM-dd hh:mm:ss", new Date()))
                    + "--");
            pw.println(excpMessage);
            pw.close();
            fw.close();
        } catch (Exception e) {
            Log.e("AppException", "[Exception]" + e.getLocalizedMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                }
            }
        }

    }

}
