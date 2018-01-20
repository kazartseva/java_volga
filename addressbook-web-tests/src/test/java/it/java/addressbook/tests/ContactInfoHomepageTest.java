package it.java.addressbook.tests;

import it.java.addressbook.models.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoHomepageTest extends TestBase {

  @BeforeMethod
  private void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().getContactCount() == 0) {
      app.contact().createAContact(new ContactData().withFirstname("Alessandro").withLastname("Mariani").withCompany("Cinema")
              .withHomeNumber("777").withMobileNumber("").withWorkNumber("999")
              .withEmail1("").withEmail2("email2@test.com").withEmail3("email3@test.com")
              .withHomepage("www.cinema.it").withGroup("[none]")
              .withAddress("address1").withAddress2("address2"), true);
    }

  }


  @Test
  public void testContactAddress() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getMainAddress(), equalTo(cleanedAddress(contactInfoFromEditForm.getAddress())));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

  }


  public static String cleanedAddress(String field) {

    return field.replaceAll("^\\s+|\\s+$", "")
            .replaceAll(" +\\n", "\n")
            .replaceAll("\\n +", "\n")
            .replaceAll("\\s{2,}", " ");
  }


  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));

  }


  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomeNumber(), contact.getMobileNumber(), contact.getWorkNumber())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactInfoHomepageTest::cleaned)
            .collect(Collectors.joining("\n"));

  }


  public static String cleaned (String field) {

    return field.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

}
