package com.example.demo.lestioners;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
        writeToReport("🚀 Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Test Passed: " + result.getName());
        writeToReport("✅ Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test Failed: " + result.getName());

        Object testClass = result.getInstance();
        try {
            WebDriver driver = (WebDriver) result.getTestClass()
                .getRealClass()
                .getDeclaredField("driver")
                .get(testClass);

            String fileName = "screenshots/" + result.getName() + ".png";

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(fileName);
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("📸 Screenshot saved at: " + fileName);

        } catch (Exception e) {
            System.out.println("⚠️ Unable to capture screenshot");
            e.printStackTrace();
        }

        writeToReport("❌ Test Failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠️ Test Skipped: " + result.getName());
        writeToReport("⚠️ Test Skipped: " + result.getName());
    }

    public void writeToReport(String message) {
        try {
            FileWriter writer = new FileWriter("test-report.txt", true);
            writer.write(message + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
