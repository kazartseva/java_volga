package it.java.addressbook.tests;

import it.java.addressbook.models.ContactData;
import it.java.addressbook.models.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  private void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().createAContact(new ContactData().withFirstname("Alessandro").withLastname("Mariani").withCompany("Cinema")
              .withHomeNumber("777").withMobileNumber("888").withWorkNumber("")
              .withEmail1("email1@test.com").withEmail2("").withEmail3("email3@test.com")
              .withHomepage("www.cinema.it")
              .withAddress("address").withAddress2("address2").withPhoto(new File("src/test/resources/avatar.png")), true);
    }
  }

  @Test(enabled = true)
  public void testDeletionContact() {
    ensurePreconditions();
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().getContactCount(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));

  }


}


