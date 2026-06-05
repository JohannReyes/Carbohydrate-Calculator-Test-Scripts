package com.selenium.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CarbohydrateCalculator {

    private WebDriver driver;

    public CarbohydrateCalculator(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.calculator.net/carbohydrate-calculator.html");
    }

    public void enterAge(String age) {
        WebElement ageField = driver.findElement(By.id("cage"));
        ageField.clear();
        ageField.sendKeys(age);
        ageField.sendKeys(Keys.TAB);
    }

    public void enterHeight(String height) {
        WebElement heightField = driver.findElement(By.id("cheightmeter"));
        heightField.clear();
        heightField.sendKeys(height);
        heightField.sendKeys(Keys.TAB);
    }

    public void enterWeight(String weight) {
        WebElement weightField = driver.findElement(By.id("ckg"));
        weightField.clear();
        weightField.sendKeys(weight);
        weightField.sendKeys(Keys.TAB);
    }

    public String getAgeValue() {
        return driver.findElement(By.id("cage")).getDomProperty("value");
    }

    public String getAgeErrorMsg() {
        return driver.findElement(By.id("cageifcErr")).getText();
    }

    public String getHeightValue() {
        return driver.findElement(By.id("cheightmeter")).getDomProperty("value");
    }

    public String getWeightValue() {
        return driver.findElement(By.id("ckg")).getDomProperty("value");
    }

    public void clickCalcBtn() {
        WebElement calcBtn = driver.findElement(By.cssSelector("input[type='submit'][value='Calculate']"));
        calcBtn.click();
    }

    public String getResultTableHeader() {
        return driver.findElement(By.cssSelector("h2.h2result")).getText();
    }

    public String getResultTableValue() {
        return driver.findElement(By.xpath("//td[contains(text(),'Weight Maintenance')]/following-sibling::td")).getText();
    }

}
