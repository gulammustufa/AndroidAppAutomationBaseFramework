package com.steps.cucumber;

import utility.enums.DeviceOwners;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utility.DeviceManager;

public class Hooks extends BaseSteps {
    private DeviceOwners deviceOwner;

    @Before()
    public void setUp(Scenario scenario) {
        testContext().setScenarioLogger(scenario);
        testContext().getScenarioLogger().log("SETUP SCENARIO " + scenario.getName());

        DeviceManager deviceManager = DeviceManager.getInstance();
        while (deviceOwner == null) {
            deviceOwner = deviceManager.acquireDevice();
            if (deviceOwner == null) {
                // No device could be acquired. Distinguish between the two possible causes:
                //  - No registered device is attached at all -> fail the scenario immediately.
                //  - Devices are attached but currently busy   -> wait for one to be released.
                if (!deviceManager.isAnyDeviceConnected()) {
                    throw new IllegalStateException(
                            "No device attached. Please connect a registered device and re-run the test.");
                }
                try {
                    Thread.sleep(1000); // All devices busy - wait for one to become available
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new IllegalStateException("Interrupted while waiting for an available device.", e);
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
