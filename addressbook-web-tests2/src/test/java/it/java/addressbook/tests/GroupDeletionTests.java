package it.java.addressbook.tests;

import it.java.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    //Проверка и обеспечивание предусловий (наличие групп)
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Test1", "Test2", "Test3"));
    }

    //Получим список групп до удаления
    List<GroupData> before = app.getGroupHelper().getGroupList();
    //Удаление группы
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    //Получим список групп после удаления
    List<GroupData> after = app.getGroupHelper().getGroupList();

    //Сравниваем размер списков
    Assert.assertEquals(after.size(), before.size() - 1);

    //Для сравнения списков групп удалим из списка before ту группу, которую будем удалять в тесте
    before.remove(before.size() - 1);
    //Сравниваем два элемента с одинаковыми индексами
    Assert.assertEquals(before, after);
  }

}



