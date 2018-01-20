package it.java.addressbook.tests;

import it.java.addressbook.models.ContactData;
import it.java.addressbook.models.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  private void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().getContactCount() == 0) {
      app.contact().createAContact(new ContactData().withFirstname("Alessandro").withLastname("Mariani").withCompany("Cinema")
              .withHomeNumber("777").withMobileNumber("888").withWorkNumber("")
              .withEmail1("email1@test.com").withEmail2("").withEmail3("email3@test.com")
              .withHomepage("www.cinema.it").withGroup("[none]")
              .withAddress("address").withAddress2("address2")
              .withGroup("[none]"), true);
    }
  }


  @Test(enabled = false)
  public void testContactModification() {
    ensurePreconditions();
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Alessandro").withLastname("Mariani").withCompany("Cinema")
            .withHomeNumber("777").withMobileNumber("888").withWorkNumber("999")
            .withEmail1("email1@test.com").withEmail2("email2@test.com").withEmail3("email3@test.com")
            .withHomepage("www.cinema.it").withGroup("[none]")
            .withAddress("address1").withAddress2("address2");
    app.contact().modify(contact);
    assertThat(app.contact().getContactCount(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}
