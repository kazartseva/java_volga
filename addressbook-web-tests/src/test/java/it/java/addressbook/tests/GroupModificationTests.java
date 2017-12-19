package it.java.addressbook.tests;

import it.java.addressbook.models.GroupData;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase{

  @Test
  public void testGroupModification(){
    //Иду на страницу Groups
    app.getNavigationHelper().goToGroupPage();
    //Проверяю, есть ли хотя бы одна группа, которую можно модифицировать
    if (! app.getGroupHelper().isThereAGroup()) {
      //Странице Groups пустая, поэтому создаю новую группу
      app.getGroupHelper().createGroup(new GroupData("Test1", "Test2", "Test3"));
    }
    //На странице Groups есть группы
    //Модифицирую одну группу
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("Утро", "Туманное", "Седое"));
    app.getGroupHelper().SubmitGroupModification();

  }
}
