package com.selenium.framework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class InputValidationTest {

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
    public void invalidAge0(){
        page.open();
        page.enterAge("0");
        // Checks if value was entered correctly
        assertEquals("0", page.getAgeValue());
        // Checks if Age Err Msg pops up successfully
        assertEquals("positive numbers only", page.getAgeErrorMsg());
    }

    @AfterEach
	public final void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
