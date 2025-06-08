package com.example.demo.lestioners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(MyListeners .class)
public class MyTest {
     WebDriver driver=null;	 
     
     By username = By.id("username");

 	By password = By.id("password");
 	By clickButton = By.className("radius");
 	
 	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/login");

	}
	
	@Test
    public void test1() {
		
        System.out.println("Running test1...");
        driver.findElement(username).sendKeys("ajay");;
        driver.findElement(password).sendKeys("rajput");
        driver.findElement(clickButton).click();
	}

    @Test
    public void test2() {
        assert false : "Intentional failure";
    }
}
