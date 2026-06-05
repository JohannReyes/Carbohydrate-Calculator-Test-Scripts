package com.selenium.framework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        // Enter all valid inputs and check if entered correctly
        page.enterAge("30");
        assertEquals("30", page.getAgeValue());
        page.selectGenderFemale();
        assertTrue(page.isGenderFemale());
        page.enterHeight("181.4");
        assertEquals("181.4", page.getHeightValue());
        page.enterWeight("78.5");
        assertEquals("78.5", page.getWeightValue());
        page.selectActivity("1.55");
        assertEquals("Active: daily exercise or intense exercise 3-4 times/week", page.getActivity());

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
