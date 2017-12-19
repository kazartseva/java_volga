package it.java.addressbook.tests;

import it.java.addressbook.models.GroupData;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletion() {
    //Иду на страницу Groups
    app.getNavigationHelper().goToGroupPage();
    //Проверяю, есть ли хотя бы одна группа, которую можно удалить
    if (! app.getGroupHelper().isThereAGroup()) {
      //Странице Groups пустая, поэтому создаю новую группу
      app.getGroupHelper().createGroup(new GroupData("Test1", "Test2", "Test3"));
    }
    //На странице Groups есть группы
    //Удаляю одну группу
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();

  }
}
