package com.selenium.framework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StressTest {

    private WebDriver driver;
    private CarbohydrateCalculator page;
    private int stressTestDuration;

    @BeforeEach
	public void setup() {
        // Tester input (for how many loops the stress test will perform)
        String loops = System.getProperty("stressLoops", "10");
        stressTestDuration = Integer.parseInt(loops);
        
        System.out.println("Starting Chrome...");
		driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        page = new CarbohydrateCalculator(driver);
        System.out.println("Chrome started");
	}

    @Test
    public void StressTestAllValidMetricInputs(){
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

        //---------------------------- STRESS TEST INITIALIZE ----------------------------//
        page.clickCalcBtn();
        // Check if Result Table pops up
        assertEquals("Result", page.getResultTableHeader());
        // Get one value from Result Table for future comparison
        String ResultTableValue = page.getResultTableValue();


        //---------------------------- STRESS TEST CHECK ----------------------------//
        for(int i=0; i<stressTestDuration; i+=1){
            //driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
            page.clickCalcBtn();
            // Check if Result Table pops up
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2.h2result")));
            assertEquals("Result", page.getResultTableHeader());
            //Compare if old result table value equals new value
            assertEquals(ResultTableValue, page.getResultTableValue());
            // Check all inputs are unchanged
            assertEquals("30", page.getAgeValue());
            assertTrue(page.isGenderFemale());
            assertEquals("181.4", page.getHeightValue());
            assertEquals("78.5", page.getWeightValue());
            assertEquals("Active: daily exercise or intense exercise 3-4 times/week", page.getActivity());
        }
    }

    @AfterEach
	public final void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
