package nexfi.cn.nexfi_android_sdk.application;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import nexfi.cn.nexfi_android_sdk.uncaught.CrashHandler;

/**
 * @author Mark, gengbaolong.
 */

public class BleApplication extends Application {
    private static Context mContext;
    private static String uuid = UUID.randomUUID().toString();
    private static CrashHandler crashHandler;
    private static List<Throwable> exceptionLists = new ArrayList<Throwable>();
    private static List<String> logLists = new ArrayList<String>();

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }

    public static Context getContext() {
        return mContext;
    }

    public static String getUUID() {
        return uuid;
    }


    public static CrashHandler getCrashHandler() {
        return crashHandler;
    }

    public static List<Throwable> getExceptionLists() {
        return exceptionLists;
    }


    public static List<String> getLogLists() {
        return logLists;
    }

}
