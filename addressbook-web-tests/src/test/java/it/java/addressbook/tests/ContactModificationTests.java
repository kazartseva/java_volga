package it.java.addressbook.tests;

import it.java.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

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

    //Получение списка контактов до модификации
    Set<ContactData> before = app.contact().all();

    //Модификация контакта
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Veronica").withLastname("Paoli").
            withCompany("Cinema").withHomenumber("+39236547459").withEmail("email@test.com").withHomepage("www.cinema.it");

    app.contact().modify(contact);

    //Получение списка контактов после модификации
    Set<ContactData> after = app.contact().all();

    //сравниваем размеры списков до и после удаления контакта
    Assert.assertEquals(before.size(), after.size());

    before.remove(modifiedContact);
    before.add(contact);

    //сортируем и сравниваем списки по элементам
    Assert.assertEquals(before, after);

  }


}
