package it.java.addressbook.appmanager;

import it.java.addressbook.models.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactHelper extends BaseHelper{

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("company"), contactData.getCompany());
    type(By.name("home"), contactData.getHomenumber());
    type(By.name("email"), contactData.getEmail());
    type(By.name("homepage"), contactData.getHomepage());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void confirmSelectedContactsDeletion() {
    wd.switchTo().alert().accept();
  }
}
