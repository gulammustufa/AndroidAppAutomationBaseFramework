package com.steps.cucumber;

import utility.enums.DeviceOwners;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.Scenario;
import utility.Constant;
import utility.locators.PermissionLocators;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static java.lang.ThreadLocal.withInitial;
import static org.assertj.core.api.Assertions.assertThat;

public enum CucumberTestContext {
    CONTEXT;
    private static final String SCENARIO = "SCENARIO";
    private final ThreadLocal<Map<String, Object>> threadLocal = withInitial(HashMap::new);

    private Map<String, Object> testContextMap() {
        return threadLocal.get();
    }

    public void set(String key, Object value) {
        testContextMap().put(key, value);
    }

    public Object get(String key) {
        return testContextMap().get(key);
    }

    public <T> T get(String key, Class<T> clazz) {
        return clazz.cast(testContextMap().get(key));
    }

    public void openApp(DeviceOwners deviceOwner) {
        AndroidDriver driver = null;
        UiAutomator2Options options = getUiAutomatorOptions(deviceOwner);
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        } catch (Exception e) {
            getScenarioLogger().log("Error while opening the app: " + e.getMessage());
        }

        assertThat(driver).as("App not opened.").isNotNull();
        set("DRIVER", driver);
        getScenarioLogger().log("App opened.");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        driver.findElement(PermissionLocators.continueButtonLocator).click();
        driver.findElement(PermissionLocators.okButtonLocator).click();
    }

    public void setScenarioLogger(Scenario scenario) {
        set(SCENARIO, scenario);
    }

    public Scenario getScenarioLogger() {
        return get(SCENARIO, Scenario.class);
    }

    public AndroidDriver getDriver() {
        return (AndroidDriver) testContextMap().get("DRIVER");
    }

    public void reset() {
        testContextMap().clear();
    }

    public UiAutomator2Options getUiAutomatorOptions(DeviceOwners deviceOwner) {
        UiAutomator2Options options = new UiAutomator2Options();
        switch (deviceOwner) {
            case Gulammustufa -> options.setUdid("AARZCR909C4QF")
                    .setDeviceName("Samsung Galaxy S20 FE 5G")
                    .setPlatformVersion("13");
            case Dhairya -> options.setUdid("AAZD222FSTG7")
                    .setDeviceName("Moto G84 5G")
                    .setPlatformVersion("14");
            case Nokia -> options.setUdid("AAPNXID19051303526")
                    .setDeviceName("Nokia 8.1")
                    .setPlatformVersion("11");
        }
        options.setPlatformName("Android")
                .setAppPackage(Constant.getAppPackage())
                .autoGrantPermissions()
                .setAppActivity(Constant.APP_ACTIVITY);
//        options.setApp(Constant.apkPath) // If we want to install apk from code
//                .setNoReset(Constant.USE_CACHE);
        return options;
    }
}