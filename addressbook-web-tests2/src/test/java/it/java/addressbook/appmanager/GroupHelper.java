package it.java.addressbook.appmanager;

import it.java.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void createGroup(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  //Находим все элементы (findElements), потом среди этих элементов выбираем
  // нужный по индексу (get(index)), и потом по этому элементу выполняем клик
  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  //Считаем количество групп: находим все элементы "selected[]"
  //и определяем размер списка (size())
  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();

  }

  public boolean isThereAGroup() {
   return isElementPresent(By.name("selected[]"));

  }

}
