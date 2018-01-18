package it.java.addressbook.tests;

import it.java.addressbook.models.ContactData;
import it.java.addressbook.models.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  private void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().createAContact(new ContactData().withFirstname("Alessandro").withLastname("Mariani").withCompany("Cinema").withHomenumber("+392365478956").
              withEmail("email@test.com").withHomepage("www.cinema.it").withGroup("[none]"), true);
    }
  }

  @Test
  public void testDeletionContact() {
    ensurePreconditions();
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.contact().all();
    assertThat(before.size(), equalTo(after.size() + 1));
    assertThat(after, equalTo(before.without(deletedContact)));

  }


}


