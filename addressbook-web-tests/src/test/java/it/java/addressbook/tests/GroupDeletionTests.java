package it.java.addressbook.tests;

import it.java.addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletion() {
    //Проверка выполнения предусловий (наличие групп)
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Test1", "Test2", "Test3"));
    }

    //Список групп до удаления
    List<GroupData> before = app.getGroupHelper().getGroupList();
    //Удаление группы
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    //Список групп после удаления
    List<GroupData> after = app.getGroupHelper().getGroupList();

    //Сравниваем размер списков
    Assert.assertEquals(after.size(), before.size() - 1);

    //Проверяем совпадение элементов списков
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);


  }

}
