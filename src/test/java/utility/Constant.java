package utility;

import utility.enums.ENV;

public class Constant {
    public static final ENV TEST_ENV = ENV.STAGE;
    public static final String APK_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\mobileApps\\ApiDemos-debug.apk";

    public static final String APP_ACTIVITY = "io.appium.android.apis.ApiDemos";
    public static final boolean USE_CACHE = false;
    public static final boolean INSTALL_APP = false;

    public static String getAppPackage() {
        if (TEST_ENV == ENV.STAGE) {
            return "io.appium.android.apis";
        } else if (TEST_ENV == ENV.LIVE) {
            return "io.appium.android.apis";
        }
        return null;
    }
}
