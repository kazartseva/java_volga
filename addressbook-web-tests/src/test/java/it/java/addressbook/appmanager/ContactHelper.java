package it.java.addressbook.appmanager;

import it.java.addressbook.models.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("company"), contactData.getCompany());
    type(By.name("home"), contactData.getHomenumber());
    type(By.name("email"), contactData.getEmail());
    type(By.name("homepage"), contactData.getHomepage());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void initContactModification(int index) {
    wd.findElements(By.cssSelector("img[title='Edit']")).get(index).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void confirmSelectedContactsDeletion() {
    wd.switchTo().alert().accept();
  }

  public boolean isThereAContact() {
    return (isElementPresent(By.name("selected[]")));
  }

  public void createAContact(ContactData contact, boolean b) {
    initContactCreation();
    fillContactForm(contact,b);
    submitContactCreation();
    new NavigationHelper(wd).homePage();
  }

  public void delete(int index) {
    selectContact(index);
    deleteSelectedContacts();
    confirmSelectedContactsDeletion();
    new NavigationHelper(wd).homePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    confirmSelectedContactsDeletion();
    new NavigationHelper(wd).homePage();

  }


  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.name("entry"));

    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      String firstname = element.findElement(By.cssSelector("tr td:nth-child(3)")).getText();
      String lastname = element.findElement(By.cssSelector("tr td:nth-child(2)")).getText();
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
      contacts.add(contact);
    }
    return contacts;

  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));

    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      String firstname = element.findElement(By.cssSelector("tr td:nth-child(3)")).getText();
      String lastname = element.findElement(By.cssSelector("tr td:nth-child(2)")).getText();
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
      contacts.add(contact);
    }
    return contacts;

  }


}
