package it.java.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private final GroupHelper groupHelper = new GroupHelper();

  public static boolean isAlertPresent(FirefoxDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void init() {
    groupHelper.wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary("/Applications/Firefox.app/Contents/MacOS/firefox-bin"));
    groupHelper.wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    groupHelper.wd.get("http://localhost/addressbook/index.php");
    login("admin", "secret");
  }

  public void login(String username, String password) {
    groupHelper.wd.findElement(By.name("user")).click();
    groupHelper.wd.findElement(By.name("user")).sendKeys(username);
    groupHelper.wd.findElement(By.name("pass")).click();
    groupHelper.wd.findElement(By.name("pass")).sendKeys(password);
    groupHelper.wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }

  public void gotoGroupPage() {

    groupHelper.wd.findElement(By.linkText("groups")).click();
  }

  public void stop() {
    groupHelper.wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }
}
