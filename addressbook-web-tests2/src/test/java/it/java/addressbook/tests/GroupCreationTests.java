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
    GroupData group = new GroupData("TestNEW", "Test2", "Test3");
    app.getGroupHelper().createGroup(group);
    //Получим список групп после создания
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //Проверка по количеству (size) элеметов списка
    Assert.assertEquals(after.size(), before.size() + 1);


    //Присвоим идентификатору найденное максимальное значение
    //Превратим список в поток, по этому потоку пробегает сравниватель и находит максимальный элемент, при этом сравниваются
    //объекты типа GroupData путем сравнения их идентификаторов, на выходе получаем группу с максимальным идентификатором
    // и берем id этой группы
    group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

    //Добавляем в старый список ту группу, которую создали
    before.add(group);

    //Преобразуем списки before и after во множества и сравним их
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
