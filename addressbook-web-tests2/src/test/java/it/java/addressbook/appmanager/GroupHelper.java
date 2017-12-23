package it.java.addressbook.appmanager;

import it.java.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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

  public List<GroupData> getGroupList() {
    //Создаем список типа GroupData с названием groups
    List<GroupData> groups = new ArrayList<GroupData>();
    //Получаем список объектов типа WebElement которые имеют tag span и css класс group
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    //Пройдем по этим элементам в цикле.
    //(WebElement element : elements) element пробегает по списку elements
    for (WebElement element : elements) {
      //и из каждого элемента получаем текст --> name
      String name = element.getText();
      //Создаем объект типа GroupData
      GroupData group = new GroupData(name, null, null);
      //Добавляем созданный объект в список
      groups.add(group);
    }
    return groups;
  }
}
