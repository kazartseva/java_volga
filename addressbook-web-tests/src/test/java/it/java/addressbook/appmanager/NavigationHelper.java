package it.java.addressbook.appmanager;

import it.java.addressbook.models.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

  public NavigationHelper(WebDriver wd) {

    super(wd);
  }

  public void groupPage() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    }
      click(By.linkText("groups"));

  }

  public void homePage() {
    //if (isElementPresent(By.id("maintable"))) {
      //return;
    //}
    click(By.linkText("home"));
  }

  public void selectedGroupPage(GroupData group) {
    click(By.cssSelector("select[name='group'] option[value='" + group.getId() + "']"));
  }

  }

