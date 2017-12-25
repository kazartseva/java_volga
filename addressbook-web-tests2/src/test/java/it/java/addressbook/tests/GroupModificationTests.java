package it.java.addressbook.tests;

import it.java.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase{

  @Test
  public void testGroupModification () {

    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Test1", "Test2", "Test3"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initGroupModification();
    //Добавим в параметры группы идентификационный номер, который оставляем прежним
    GroupData group = new GroupData(before.get(before.size() - 1).getId(),"TestWW", "TestB", "TestC");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(before.size(), after.size());

    //Модифицируем старый список, чтобы можно было предсказать ожидаемый результат
    //Удалим элемент, который модифицировать, и добавим модифицированный элемент
    before.remove(before.size() - 1);
    before.add(group);
    //Преобразуем список before во множество
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }
}
