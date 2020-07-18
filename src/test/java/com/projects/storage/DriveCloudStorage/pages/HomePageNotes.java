package com.projects.storage.DriveCloudStorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HomePageNotes {

    @FindBy(css="#nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(css="#newNotesButton")
    private WebElement newNotesButton;

    @FindBy(css="#note-title")
    private WebElement noteTitle;
    @FindBy(css="#note-description")
    private WebElement noteDescription;
    @FindBy(css="#saveNote")
    private WebElement noteSubmit;

    @FindBy(css="#nav-notes .table-striped .btn-success")
    private WebElement editButton;

    @FindBy(css="#nav-notes .table-striped .btn-danger")
    private WebElement deleteButton;

    @FindBy(css="#logoutDiv .float-right")
    private WebElement logout;

    private WebDriverWait wait;

    public HomePageNotes(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.wait = new WebDriverWait(webDriver, 10);
    }

    public void createNote(String title, String description) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace( );
        }
        this.navNotesTab.click();
        wait.until(ExpectedConditions.visibilityOf(newNotesButton));
        this.newNotesButton.click();
        wait.until(ExpectedConditions.visibilityOf(noteTitle));
        this.noteTitle.sendKeys(title);
        this.noteDescription.sendKeys(description);
        this.noteSubmit.click();

        this.navNotesTab = wait.until(ExpectedConditions.elementToBeClickable(navNotesTab));
        this.navNotesTab.click();
    }

    public void editNote(String title, String description) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace( );
        }
        this.navNotesTab.click();
        this.editButton = wait.until(ExpectedConditions.elementToBeClickable(editButton));
        this.editButton.click();
        wait.until(ExpectedConditions.visibilityOf(noteTitle));
        this.noteTitle.clear();
        this.noteTitle.sendKeys(title);
        this.noteDescription.clear();
        this.noteDescription.sendKeys(description);
        this.noteSubmit.click();
        this.navNotesTab = wait.until(ExpectedConditions.elementToBeClickable(navNotesTab));
        this.navNotesTab.click();
    }

    public void deleteNote() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace( );
        }
        wait.until(ExpectedConditions.elementToBeClickable(navNotesTab));
        this.navNotesTab.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace( );
        }
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        this.deleteButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(navNotesTab));
        this.navNotesTab.click();
    }
    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(logout));
        this.logout.click();
    }

}
