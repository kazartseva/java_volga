package it.java.addressbook.tests;

import it.java.addressbook.models.ContactData;
import it.java.addressbook.models.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test(enabled = false)
  public void contactCreationTests() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Alessandro").withLastname("Mariani").withCompany("Cinema")
            .withHomeNumber("777").withMobileNumber("888").withWorkNumber("999")
            .withEmail1("email1@test.com").withEmail2("email2@test.com").withEmail3("email3@test.com")
            .withHomepage("www.cinema.it").withGroup("[none]")
            .withAddress("address1").withAddress2("address2");
    app.contact().createAContact(contact, true);
    assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
