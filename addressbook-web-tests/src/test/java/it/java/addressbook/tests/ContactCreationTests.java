package it.java.addressbook.tests;

import it.java.addressbook.models.ContactData;
import it.java.addressbook.models.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void contactCreationTests() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Alessandro").withLastname("Mariani").withCompany("Cinema").withHomenumber("+392365478956").
            withEmail("email@test.com").withHomepage("www.cinema.it").withGroup("[none]");
    app.contact().createAContact(contact, true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
