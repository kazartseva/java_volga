package it.java.addressbook.tests;

import it.java.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

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

    //создание множества контактов до удаления
    Set<ContactData> before = app.contact().all();

    //Удаляю контакт
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);

    //создание множества контактов после удаления
    Set<ContactData> after = app.contact().all();

    //сравниваем размеры множеств до и после удаления контакта
    Assert.assertEquals(before.size(), after.size() + 1);

    before.remove(deletedContact);

    //сравниваем множества по элементам
    Assert.assertEquals(before, after);


  }



}


