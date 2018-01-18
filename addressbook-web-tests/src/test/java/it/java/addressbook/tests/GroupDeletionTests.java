package it.java.addressbook.tests;

import it.java.addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupDeletionTests extends TestBase{

  @BeforeMethod
  private void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("Test1").withHeader("Test2").withFooter("Test3"));
    }
  }

  @Test
  public void testGroupDeletion() {
    ensurePreconditions();

    //Список групп до удаления
    Set<GroupData> before = app.group().all();
    //Создаем deletedGroup - первую попавшуюся группу из множества, которую будем удалять.
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);

    //Список групп после удаления
    Set<GroupData> after = app.group().all();

    //Сравниваем размер списков
    Assert.assertEquals(after.size(), before.size() - 1);

    //Проверяем совпадение элементов списков
    before.remove(deletedGroup);
    Assert.assertEquals(before, after);




  }



}
