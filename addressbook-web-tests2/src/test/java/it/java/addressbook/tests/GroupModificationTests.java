package it.java.addressbook.tests;

import it.java.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("Test1"));
    }

  }

  @Test
  public void testGroupModification() {

    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().
            withId(modifiedGroup.getId()).withName("TestWW").withHeader("TestB").withFooter("TestC");
    app.group().modify(group);
    Set<GroupData> after = app.group().all();

    //Проверка совпадения размеров списков
    Assert.assertEquals(after.size(), before.size());

    //Упорядочение списков и проверка совпадения элементов коллекций
    before.remove(modifiedGroup);
    before.add(group);

    //Сравниваем коллекции
    Assert.assertEquals(before, after);

  }


}
