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
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("Test1"));
    }

  }

  @Test
  public void testGroupModification() {

    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    GroupData group = new GroupData().
            withId(before.get(index).getId()).withName("TestWW").withHeader("TestB").withFooter("TestC");
    app.group().modify(index, group);
    List<GroupData> after = app.group().list();

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
