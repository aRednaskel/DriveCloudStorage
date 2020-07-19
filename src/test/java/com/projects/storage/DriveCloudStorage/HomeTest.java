package com.projects.storage.DriveCloudStorage;

import com.projects.storage.DriveCloudStorage.errorhandlers.StorageException;
import com.projects.storage.DriveCloudStorage.model.User;
import com.projects.storage.DriveCloudStorage.pages.HomePageCredentials;
import com.projects.storage.DriveCloudStorage.pages.HomePageNotes;
import com.projects.storage.DriveCloudStorage.pages.LoginPage;
import com.projects.storage.DriveCloudStorage.services.interfaces.UserService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeTest{

    @LocalServerPort
    public int port;

    public static WebDriver driver;
    public String baseURL;
    private static int testUser;

    @Autowired
    private UserService userService;

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
        driver.get(baseURL + "/login");
        userService.create(new User(1, "Tom" + testUser, "Tom", "Tom", "Tom", "Tom"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Tom" + testUser, "Tom");

        testUser++;
    }

    @Test
    public void testAddingNotesAndCredentials() {

        HomePageNotes homePageNotes = new HomePageNotes(driver);
        homePageNotes.createNote("My first note", "This is my first note, that I am testing");
        assertEquals(1, driver.findElements(By.xpath("//*[text()='My first note']")).size());
        homePageNotes.logout();
    }

    @Test
    public void testEditingNotes() {
        HomePageNotes homePageNotes = new HomePageNotes(driver);
        homePageNotes.createNote("My first note", "This is my first note, that I am testing");
        homePageNotes.editNote("Second note", "Actually it was the second one");
        assertEquals(driver.findElements(By.xpath("//*[text()='My first note']")).size(), 0);
        assertEquals(1, driver.findElements(By.xpath("//*[text()='Second note']")).size());
        homePageNotes.logout();
    }

    @Test
    public void testDeletingNotes() {
        HomePageNotes homePageNotes = new HomePageNotes(driver);
        homePageNotes.createNote("My first note", "This is my first note, that I am testing");
        homePageNotes.deleteNote();
        assertEquals(0, driver.findElements(By.xpath("//*[text()='My first note']")).size());
        homePageNotes.logout();
    }

    @Test
    public void testAddingCredentials() {
        HomePageCredentials homePageCredentials = new HomePageCredentials(driver);

        homePageCredentials.createCredential("www.udacity.com", "User1", "User1");
        assertEquals(1, driver.findElements(By.xpath("//*[text()='www.udacity.com']")).size());

        homePageCredentials.logout();
    }

    @Test
    public void testEditingCredential() {
        HomePageCredentials homePageCredentials = new HomePageCredentials(driver);
        homePageCredentials.createCredential("www.udacity.com", "User1", "User1");

        homePageCredentials.editNote("www.google.com", "User2", "User2");
        assertEquals(0, driver.findElements(By.xpath("//*[text()='www.udacity.com']")).size());
        assertEquals(1, driver.findElements(By.xpath("//*[text()='www.google.com']")).size());

        homePageCredentials.logout();
    }

    @Test
    public void testDeletingCredential() {
        HomePageCredentials homePageCredentials = new HomePageCredentials(driver);

        homePageCredentials.createCredential("www.udacity.com", "User1", "User1");
        homePageCredentials.deleteNote();
        assertEquals(0, driver.findElements(By.xpath("//*[text()='www.udacity.com']")).size());

        homePageCredentials.logout();
    }


}
