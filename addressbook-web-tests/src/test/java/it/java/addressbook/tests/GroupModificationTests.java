package it.java.addressbook.tests;

import it.java.addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupModificationTests extends TestBase{

  @BeforeMethod
  private void ensurePreconditions() {
    app.goTo().groupPage();
    //Проверка и обеспечение предусловий для теста (наличие хотя бы одной группы)
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("Test1").withHeader("Test2").withFooter("Test3"));
    }
  }

  @Test
  public void testGroupModification(){
    ensurePreconditions();

    //Множество групп до модифицирования
    Set<GroupData> before = app.group().all();
    //Модификация группы
    GroupData modifiedGroup = before.iterator().next();

    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("Giulio").withHeader("header").withFooter("footer");

    app.group().modify(group);

    //Множество групп после модифицирования
    Set<GroupData> after = app.group().all();

    //Проверка совпадения размеров множеств
    Assert.assertEquals(after.size(), before.size());

    //Проверка совпадения элементов множеств
    before.remove(modifiedGroup);
    before.add(group);
    Assert.assertEquals(before, after);

  }


}
