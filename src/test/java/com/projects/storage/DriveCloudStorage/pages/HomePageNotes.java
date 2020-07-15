package com.projects.storage.DriveCloudStorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    private WebDriverWait wait;

    public HomePageNotes(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.wait = new WebDriverWait(webDriver, 100);
    }

    public void createNote(String title, String description) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace( );
        }
        wait.until(ExpectedConditions.elementToBeClickable(navNotesTab));
        this.navNotesTab.click();
        wait.until(ExpectedConditions.visibilityOf(newNotesButton));
        this.newNotesButton.click();
        wait.until(ExpectedConditions.visibilityOf(noteTitle));
        this.noteTitle.sendKeys(title);
        this.noteDescription.sendKeys(description);
        this.noteSubmit.click();
        wait.until(ExpectedConditions.elementToBeClickable(navNotesTab));
        this.navNotesTab.click();
    }

}
