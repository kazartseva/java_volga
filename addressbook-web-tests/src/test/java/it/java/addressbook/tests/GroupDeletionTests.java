package it.java.addressbook.tests;

import it.java.addressbook.models.GroupData;
import it.java.addressbook.models.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    Groups before = app.group().all();
    //Создаем deletedGroup - первую попавшуюся группу из множества, которую будем удалять.
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);

    //Список групп после удаления
    Groups after = app.group().all();

    //Сравниваем размер списков
    Assert.assertEquals(after.size(), before.size() - 1);

    //Проверяем совпадение элементов списков
    assertThat(after, equalTo(before.without(deletedGroup)));




  }



}
