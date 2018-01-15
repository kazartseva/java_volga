package it.java.addressbook.tests;

import it.java.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase{

  @Test
  public void testGroupModification() {

    app.getNavigationHelper().gotoGroupPage();
    //Проверка и обеспечение предусловий для теста (наличие хотя бы одной группы)
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Test1", "Test2", "Test3"));
    }

    //Список групп до модифицирования
    List<GroupData> before = app.getGroupHelper().getGroupList();
    //Модификация группы
    app.getGroupHelper().selectGroup(0);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get(0).getId(),"TestWW", "TestB", "TestC");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    //Список групп после модифицирования
    List<GroupData> after = app.getGroupHelper().getGroupList();

    //Проверка совпадения размеров списков
    Assert.assertEquals(after.size(), before.size());

    //Упорядочение списков и проверка совпадения элементов списко
    before.remove(0);
    before.add(group);
    Comparator<? super GroupData> byID = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byID);
    after.sort(byID);
    //Сравниваем списки
    Assert.assertEquals(before, after);

  }
}
