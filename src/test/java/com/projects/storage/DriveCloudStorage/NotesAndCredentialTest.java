package com.projects.storage.DriveCloudStorage;

import com.projects.storage.DriveCloudStorage.pages.HomePageCredentials;
import com.projects.storage.DriveCloudStorage.pages.HomePageNotes;
import com.projects.storage.DriveCloudStorage.pages.LoginPage;
import com.projects.storage.DriveCloudStorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesAndCredentialTest {

    @LocalServerPort
    public int port;

    public static WebDriver driver;
    public String baseURL;
    public String username = "pzastoup";
    public String password = "whatabadpassword";

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
        driver = null;
    }

    @BeforeEach
    public void beforeEach() {
        baseURL  = "http://localhost:" + port;

        driver.get(baseURL + "/signup");

        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("Peter", "Zastoupil", username, password);

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }

    @Test
    public void testAddingNotesAndCredentials() {

        HomePageNotes homePageNotes = new HomePageNotes(driver);
        HomePageCredentials homePageCredentials = new HomePageCredentials(driver);

        homePageNotes.createNote("My first note", "This is my first note, that I am testing");
        assertNotEquals(driver.findElements(By.className("btn-success")).size(), 0);

        homePageNotes.createNote("My second note", "This is my second note, that I am testing");
        assertEquals(driver.findElements(By.className("btn-success")).size(),2);

        homePageCredentials.createCredential("www.udacity.com", "User1", "User1");
        assertNotEquals(driver.findElements(By.cssSelector("#credentialTable .btn-success")).size(), 0);

        homePageCredentials.createCredential("www.google.com", "User2", "User2");
        assertEquals(driver.findElements(By.cssSelector("#credentialTable .btn-success")).size(), 2);
    }


}
