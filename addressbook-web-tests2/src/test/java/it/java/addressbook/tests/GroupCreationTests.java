package it.java.addressbook.tests;

import it.java.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();

    //Получим список групп перед созданием
    List<GroupData> before = app.getGroupHelper().getGroupList();

    //Создадим переменную group
    GroupData group = new GroupData(before.size() - 1,"TestNEW", "Test2", "Test3");
    app.getGroupHelper().createGroup(group);

    //Получим список групп после создания
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //Проверка по количеству (size) элеметов списка
    Assert.assertEquals(after.size(), before.size() + 1);


    //Добавляем в старый список ту группу, которую создали
    before.add(group);

    //сортируем списки по id при помощи метода sort (java 8)
    Comparator<? super GroupData> byID = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byID);
    after.sort(byID);
  }

}
