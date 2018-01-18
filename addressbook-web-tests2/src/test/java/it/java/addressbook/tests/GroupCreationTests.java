package it.java.addressbook.tests;

import it.java.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();

    //Получим множество групп перед созданием
    Set<GroupData> before = app.group().all();
    //Создадим переменную group
    GroupData group = new GroupData().withName("TestWW");

    //Создадим новую группу
    app.group().create(group);

    //Получим множество групп после создания
    Set<GroupData> after = app.group().all();
    //Проверка по количеству (size) элеметов списка
    Assert.assertEquals(after.size(), before.size() + 1);

    //Вычисляем идентификатор вновь добавленной группы (максимальный из всех) и добавляем ее в коллекцию before
    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(after, before);
  }

}
