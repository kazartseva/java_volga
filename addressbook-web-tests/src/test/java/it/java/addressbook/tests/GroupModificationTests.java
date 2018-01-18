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

public class GroupModificationTests extends TestBase{

  @BeforeMethod
  private void ensurePreconditions() {
    app.goTo().groupPage();
    //Проверка и обеспечение предусловий для теста (наличие хотя бы одной группы)
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("Test1").withHeader("Test2").withFooter("Test3"));
    }
  }

  @Test
  public void testGroupModification(){
    ensurePreconditions();
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().
            withId(modifiedGroup.getId()).withName("Giulio").withHeader("header").withFooter("footer");
    app.group().modify(group);
    Groups after = app.group().all();

    //Проверка совпадения размеров множеств
    Assert.assertEquals(after.size(), before.size());

    //Проверка совпадения элементов множеств
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

  }


}
