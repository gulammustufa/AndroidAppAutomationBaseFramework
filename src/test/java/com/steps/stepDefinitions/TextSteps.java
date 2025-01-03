package com.steps.stepDefinitions;

import com.steps.cucumber.AbstractSteps;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.locators.ApiDemosLocators;
import utility.locators.TextLocators;
import utility.locators.TextLogTextBoxLocators;

import static org.assertj.core.api.Assertions.assertThat;

public class TextSteps extends AbstractSteps implements ApiDemosLocators, TextLocators, TextLogTextBoxLocators {
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

    @And("API Demos > Text > LogTextBox: User clicks on Add button")
    public void apiDemosTextLogTextBoxUserClicksOnAddButton() {
        driver.findElement(addButtonLocator).click();
    }

    @Then("API Demos > Text > LogTextBox: Text should be visible as {string}")
    public void apiDemosTextLogTextBoxTextShouldBeVisibleAs(String expectedText) {
        String actualText = driver.findElement(addedTextLocator).getText().replace("\n","");
        assertThat(actualText).isEqualTo(expectedText);
    }
}
