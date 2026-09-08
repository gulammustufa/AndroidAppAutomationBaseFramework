package utility.locators;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public interface ApiDemosLocators {
    //    By textLocator = AppiumBy.accessibilityId("Text");
    AppiumBy.ByAndroidUIAutomator textLocator = new AppiumBy.ByAndroidUIAutomator("new UiSelector().description(\"Text\")");
}
