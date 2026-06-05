package com.selenium.framework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class FunctionalTest {

    private WebDriver driver;
    private CarbohydrateCalculator page;

    @BeforeEach
	public void setup() {
        System.out.println("Starting Chrome...");
		driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        page = new CarbohydrateCalculator(driver);
        System.out.println("Chrome started");
	}

    @Test
    public void FunctionalTestAllValidMetricInputs(){
        page.open();

        //---------------------------- INPUTS ----------------------------//
        // Enter age and check if entered correctly
        page.enterAge("30");
        assertEquals("30", page.getAgeValue());
        // Enter hieght and check if entered correctly
        page.enterHeight("181.4");
        assertEquals("181.4", page.getHeightValue());
        // Enter wieght and check if entered correctly
        page.enterWeight("78.5");
        assertEquals("78.5", page.getWeightValue());

        //---------------------------- RESULT TABLE ----------------------------//
        page.clickCalcBtn();
        // Check if Result Table pops up
        assertEquals("Result", page.getResultTableHeader());
    }

    @AfterEach
	public final void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
