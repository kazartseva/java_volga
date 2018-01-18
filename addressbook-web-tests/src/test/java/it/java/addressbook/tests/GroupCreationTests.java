package it.java.addressbook.tests;

import it.java.addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void groupCreationTests() {
    app.goTo().groupPage();

    //создаем множество групп до добавления новой группы
    Set<GroupData> before = app.group().all();

    GroupData group = new GroupData().withName("Test1").withHeader("Test2").withFooter("Test3");
    app.group().create(group);

    //создаем множество групп после добавления группы
    Set<GroupData> after = app.group().all();

    //сравниваем размеры списков до и после добавления группы
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()));
    Assert.assertEquals(before, after);
  }


}
