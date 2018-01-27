package it.java.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import it.java.addressbook.models.ContactData;
import it.java.addressbook.models.Contacts;
import it.java.addressbook.models.GroupData;
import it.java.addressbook.models.Groups;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> contacts() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }
  }

  @BeforeMethod
  private void ensurePreconditions() {
    //Проверка и обеспечение предусловий для теста (наличие хотя бы одной группы)
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test1").withHeader("Test2").withFooter("Test3"));
    }
  }

  @Test(dataProvider = "contacts")
  public void contactCreationTests(ContactData contact) {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    Groups groups = app.db().groups();
    File photo = new File("src/test/resources/avatar.png");
    app.contact().createAContact(contact.inGroup(groups.iterator().next())
            .withPhoto(photo), true);
    assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
