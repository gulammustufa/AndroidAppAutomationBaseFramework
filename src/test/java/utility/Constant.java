package utility;

import utility.enums.DeviceOwners;
import utility.enums.ENV;

public class Constant {
    public static final ENV TEST_ENV = ENV.STAGE;
    public static final DeviceOwners deviceOwner = DeviceOwners.Gulammustufa;
    public static final String APP_ACTIVITY = "io.appium.android.apis.ApiDemos";

    public static String getAppPackage() {
        if (TEST_ENV == ENV.STAGE) {
            return "io.appium.android.apis";
        } else if (TEST_ENV == ENV.LIVE) {
            return "io.appium.android.apis";
        }
        return null;
    }
}
