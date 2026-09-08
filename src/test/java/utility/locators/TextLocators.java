package utility.locators;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public interface TextLocators {
    By logTextBox = AppiumBy.accessibilityId("LogTextBox");
}
