package com.projects.storage.DriveCloudStorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageCredentials {

    @FindBy(id="nav-credentials-tab")
    private WebElement navCredetialsTab;

    @FindBy(id="newCredentialButton")
    private WebElement newCredentialButton;

    @FindBy(id="credential-url")
    private WebElement credentialUrl;
    @FindBy(id="credential-username")
    private WebElement credentialUsername;
    @FindBy(id="credential-password")
    private WebElement credentialPassword;
    @FindBy(id="saveCredential")
    private WebElement saveCredential;

    @FindBy(css="#nav-credentials .table-striped .btn-success")
    private WebElement editButton;

    @FindBy(css="#nav-credentials .table-striped .btn-danger")
    private WebElement deleteButton;

    @FindBy(css="#logoutDiv .float-right")
    private WebElement logout;

    private WebDriverWait wait;

    public HomePageCredentials(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.wait = new WebDriverWait(webDriver, 100);
    }

    public void createCredential(String url, String username, String password) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace( );
        }
        wait.until(ExpectedConditions.elementToBeClickable(navCredetialsTab));
        this.navCredetialsTab.click();

        wait.until(ExpectedConditions.visibilityOf(newCredentialButton));
        this.newCredentialButton.click();
        wait.until(ExpectedConditions.visibilityOf(credentialUrl));
        this.credentialUrl.sendKeys(url);
        this.credentialUsername.sendKeys(username);
        this.credentialPassword.sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(saveCredential));
        this.saveCredential.click();
        wait.until(ExpectedConditions.elementToBeClickable(navCredetialsTab));
        this.navCredetialsTab.click();
    }

    public void editNote(String url, String username, String password) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace( );
        }
        wait.until(ExpectedConditions.elementToBeClickable(navCredetialsTab));
        this.navCredetialsTab.click();
        wait.until(ExpectedConditions.visibilityOf(editButton));
        this.editButton.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace( );
        }
        wait.until(ExpectedConditions.visibilityOf(credentialUrl));
        this.credentialUrl.clear();
        this.credentialUrl.sendKeys(url);
        this.credentialUsername.clear();
        this.credentialUsername.sendKeys(username);
        this.credentialPassword.clear();
        this.credentialPassword.sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(saveCredential));
        this.saveCredential.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace( );
        }
        wait.until(ExpectedConditions.elementToBeClickable(navCredetialsTab));
        this.navCredetialsTab.click();
    }

    public void deleteNote() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace( );
        }
        wait.until(ExpectedConditions.elementToBeClickable(navCredetialsTab));
        this.navCredetialsTab.click();
        wait.until(ExpectedConditions.visibilityOf(deleteButton));
        this.deleteButton.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace( );
        }
        wait.until(ExpectedConditions.elementToBeClickable(navCredetialsTab));
        this.navCredetialsTab.click();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(logout));
        this.logout.click();
    }

}
