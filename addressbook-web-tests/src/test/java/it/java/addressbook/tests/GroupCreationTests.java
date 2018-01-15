package it.java.addressbook.tests;

import it.java.addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void groupCreationTests() {
    app.getNavigationHelper().goToGroupPage();

    //создаем список групп до добавления новой группы
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData(before.get(before.size() - 1).getId(),"Giuseppe", "header", "footer");
    app.getGroupHelper().createGroup(group);

    //создаем список групп после добавления группы
    List<GroupData> after = app.getGroupHelper().getGroupList();

    //сравниваем размеры списков до и после добавления группы
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(group);

    //Сортируем и сравниваем списки по элементам
    Comparator<? super GroupData> byID = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before, after);
  }


}
