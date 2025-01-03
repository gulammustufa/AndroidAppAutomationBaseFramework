package utility.locators;

import io.appium.java_client.AppiumBy;

public interface TextLocators {
    AppiumBy logTextBox = (AppiumBy) AppiumBy.accessibilityId("LogTextBox");
}
