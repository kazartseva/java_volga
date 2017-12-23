package it.java.addressbook.tests;

import it.java.addressbook.models.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void groupCreationTests() {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData("Test1", "Test2", "Test3"));

  }


}
