package it.java.addressbook.tests;

import it.java.addressbook.models.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  private void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().getContactCount() == 0) {
      app.contact().createAContact(new ContactData().withFirstname("Alessandro").withLastname("Mariani").withCompany("Cinema").
              withHomeNumber("777").withMobileNumber("888").withWorkNumber("999").withEmail("email@test.com").
              withHomepage("www.cinema.it").withGroup("[none]"), true);
    }
  }


  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHomeNumber(), equalTo(cleaned(contactInfoFromEditForm.getHomeNumber())));
    assertThat(contact.getMobileNumber(), equalTo(cleaned(contactInfoFromEditForm.getMobileNumber())));
    assertThat(contact.getWorkNumber(), equalTo(cleaned(contactInfoFromEditForm.getWorkNumber())));

  }

  public String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
