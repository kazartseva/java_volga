package it.java.addressbook.tests;

import it.java.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    //Получим список групп перед созданием
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().createGroup(new GroupData("Test1", "Test2", "Test3"));
    //Получим список групп после создания
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //Проверка по количеству (size) элеметов списка
    Assert.assertEquals(after.size(), before.size() + 1);
  }

}
