package it.java.addressbook.tests;

import it.java.addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase{

  @Test
  public void testGroupModification(){

    app.getNavigationHelper().goToGroupPage();
    //Проверка и обеспечение предусловий для теста (наличие хотя бы одной группы)
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Test1", "Test2", "Test3"));
    }

    //Список групп до модифицирования
    List<GroupData> before = app.getGroupHelper().getGroupList();
    //Модификация группы
    app.getGroupHelper().selectGroup(0);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get(0).getId(),"Giulio", "header", "footer");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().SubmitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    //Список групп после модифицирования
    List<GroupData> after = app.getGroupHelper().getGroupList();

    //Проверка совпадения размеров списков
    Assert.assertEquals(after.size(), before.size());

    //Упорядочение списков и проверка совпадения элементов списков
    before.remove(0);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}
