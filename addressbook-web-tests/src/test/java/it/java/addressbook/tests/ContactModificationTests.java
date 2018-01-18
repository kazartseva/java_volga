package it.java.addressbook.tests;

import it.java.addressbook.models.ContactData;
import it.java.addressbook.models.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  private void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().createAContact(new ContactData().withFirstname("Patrizia").withLastname("Fannucci").
              withCompany("LUDEC").withHomenumber("333666").withEmail("email"), true);
    }
  }


  @Test
  public void testContactModification() {
    ensurePreconditions();
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Veronica").withLastname("Paoli").
            withCompany("Cinema").withHomenumber("+39236547459").withEmail("email@test.com").withHomepage("www.cinema.it");
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    assertThat(before.size(), equalTo(after.size()));
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}
