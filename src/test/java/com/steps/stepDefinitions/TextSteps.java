package com.steps.stepDefinitions;

import com.steps.cucumber.BaseSteps;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.locators.ApiDemosLocators;
import utility.locators.TextLocators;
import utility.locators.TextLogTextBoxLocators;

import static org.assertj.core.api.Assertions.assertThat;

public class TextSteps extends BaseSteps implements ApiDemosLocators, TextLocators, TextLogTextBoxLocators {
    AndroidDriver driver = testContext().getDriver();

    @When("API Demos: User clicks on {string} field")
    public void apiDemosUserClicksOnField(String fieldName) {
        if (fieldName.equals("Text")){
            driver.findElement(textLocator).click();
        }
    }

    @And("API Demos > Text: User clicks on {string} field")
    public void apiDemosTextUserClicksOnField(String fieldName) {
        if (fieldName.equals("LogTextBox")){
            driver.findElement(logTextBox).click();
        }
    }

    @And("API Demos > Text > LogTextBox: User clicks on Add button {int} times")
    public void apiDemosTextLogTextBoxUserClicksOnAddButton(int clickCount) {
        for (int i = 0; i < clickCount; i++) {
            driver.findElement(addButtonLocator).click();
        }
    }

    @Then("API Demos > Text > LogTextBox: Text should be visible {int} times")
    public void apiDemosTextLogTextBoxTextShouldBeVisibleTimes(int expectedCount) {
        String text = driver.findElement(addedTextLocator).getText();
        int actualCount = text.split("\n").length;
        assertThat(actualCount).isEqualTo(expectedCount);
    }
}
