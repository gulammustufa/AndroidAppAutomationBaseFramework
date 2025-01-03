package com.steps.cucumber;

import utility.Constant;
import utility.enums.DeviceOwners;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utility.DeviceManager;

import java.io.Console;

public class Hooks extends AbstractSteps {
    private DeviceOwners deviceOwner;

    @Before()
    public void setUp(Scenario scenario) {
        testContext().setScenarioLogger(scenario);
        testContext().getScenarioLogger().log("SETUP SCENARIO " + scenario.getName());

        DeviceManager deviceManager = DeviceManager.getInstance();
        while (deviceOwner == null) {
            deviceOwner = deviceManager.acquireDevice();
            if (deviceOwner == null) {
                try {
                    Thread.sleep(1000); // Wait if no device is available
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        scenario.log("Using device: " + deviceOwner);
        testContext().openApp(deviceOwner);
    }

    @After()
    public void tearDown(Scenario scenario) throws InterruptedException {
        testContext().getScenarioLogger().log("GENERIC TEARDOWN " + scenario.getName());
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) testContext().getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");
        }

        if (testContext().getDriver() != null) {
            Thread.sleep(3000);
            testContext().getDriver().quit();
        }
        testContext().reset();
        DeviceManager.getInstance().releaseDevice(deviceOwner);
        scenario.log("Released device: " + deviceOwner);
        deviceOwner = null;
    }
}
