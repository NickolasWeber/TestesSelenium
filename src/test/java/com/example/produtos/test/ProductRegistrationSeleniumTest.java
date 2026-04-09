package com.example.produtos.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRegistrationSeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String BASE_URL = "http://localhost:8080";
    private static final int WAIT_TIMEOUT = 10;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, WAIT_TIMEOUT);
        driver.navigate().to(BASE_URL);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testProductInsertionViaUI() {
        String productName = "Notebook Dell Inspiron";
        String productPrice = "3499.99";

        WebElement nameField = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id("productName"))
        );
        nameField.sendKeys(productName);

        WebElement priceField = driver.findElement(By.id("productPrice"));
        priceField.sendKeys(productPrice);

        WebElement submitBtn = driver.findElement(By.id("submitBtn"));
        submitBtn.click();

        WebElement successMessage = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("message"))
        );
        assertTrue(successMessage.getText().contains("sucesso"), 
            "Success message should appear after product insertion");
        assertTrue(successMessage.getAttribute("class").contains("success"),
            "Message should have success class");

        assertEquals("", nameField.getAttribute("value"), 
            "Product name field should be cleared after successful submission");
        assertEquals("", priceField.getAttribute("value"), 
            "Product price field should be cleared after successful submission");
    }

    @Test
    public void testFormElementsPresence() {
        WebElement heading = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.tagName("h1"))
        );
        assertTrue(heading.getText().contains("Cadastro de Produtos"),
            "Page heading should contain 'Cadastro de Produtos'");

        WebElement nameField = driver.findElement(By.id("productName"));
        assertNotNull(nameField, "Product name field should exist");
        assertEquals("text", nameField.getAttribute("type"), 
            "Name field should be of type text");

        WebElement priceField = driver.findElement(By.id("productPrice"));
        assertNotNull(priceField, "Product price field should exist");
        assertEquals("number", priceField.getAttribute("type"), 
            "Price field should be of type number");

        WebElement submitBtn = driver.findElement(By.id("submitBtn"));
        assertNotNull(submitBtn, "Submit button should exist");
        assertTrue(submitBtn.isDisplayed(), "Submit button should be visible");
        assertTrue(submitBtn.isEnabled(), "Submit button should be enabled");
    }
}