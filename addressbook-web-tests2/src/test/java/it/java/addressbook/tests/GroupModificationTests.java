package it.java.addressbook.tests;

import it.java.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Test1", "Test2", "Test3"));
    }

  }

  @Test
  public void testGroupModification() {

    //Список групп до модифицирования
    List<GroupData> before = app.getGroupHelper().getGroupList();
    //Модификация группы
    int index = before.size() - 1;
    GroupData group = new GroupData(before.get(index).getId(),"TestWW", "TestB", "TestC");
    app.getGroupHelper().modifyGroup(index, group);
    //Список групп после модифицирования
    List<GroupData> after = app.getGroupHelper().getGroupList();

    //Проверка совпадения размеров списков
    Assert.assertEquals(after.size(), before.size());

    //Упорядочение списков и проверка совпадения элементов списко
    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byID = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byID);
    after.sort(byID);
    //Сравниваем списки
    Assert.assertEquals(before, after);

  }


}
