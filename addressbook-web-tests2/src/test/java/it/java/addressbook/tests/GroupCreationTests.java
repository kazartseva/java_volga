package it.java.addressbook.tests;

import it.java.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();

    //Получим список групп перед созданием
    List<GroupData> before = app.getGroupHelper().getGroupList();
    //Создадим переменную group
    GroupData group = new GroupData("Test1", "Test2", "Test3");
    app.getGroupHelper().createGroup(group);
    //Получим список групп после создания
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //Проверка по количеству (size) элеметов списка
    Assert.assertEquals(after.size(), before.size() + 1);

    //Для корректного сравнения двух списков добавим в старый список ту группу, которую создали во время теста
    //Нам необходимо знать id созданной группы. Это будет максимальное id всех групп.
    //Найдем максимальное значение идентификатора Id
    int max = 0;
    //Переменная g пробегает список after, находим максимальный идентификатор
    for (GroupData g : after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }
    //Присвоим идентификатору найденное максимальное значение
    group.setId(max);

    //Добавляем в старый список ту группу, которую создали
    before.add(group);

    //Преобразуем списки before и after во множества и сравним их
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
