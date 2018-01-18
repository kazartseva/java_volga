package it.java.addressbook.tests;

import it.java.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  private void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().createAContact(new ContactData().withFirstname("Patrizia").withLastname("Fannucci").
              withCompany("LUDEC").withHomenumber("333666").withEmail("email"), true);
    }
  }


  @Test
  public void testContactModification() {

    ensurePreconditions();

    //Получение списка контактов до модификации
    List<ContactData> before = app.contact().list();

    //Модификация контакта
    ContactData contact = new ContactData().withFirstname("Luca").withLastname("Ivanov").
            withCompany("Bar").withHomenumber("+392365478123").withEmail("email@test.com").withHomepage("www.cinema.it");
    app.contact().initContactModification(before.size() - 1);
    app.contact().fillContactForm(contact, false);
    app.contact().submitContactModification();
    app.goTo().homePage();

    //Получение списка контактов после модификации
    List<ContactData> after = app.contact().list();

    //сравниваем размеры списков до и после удаления контакта
    Assert.assertEquals(before.size(), after.size());

    before.remove(before.size() - 1);
    before.add(contact);

    //сортируем и сравниваем списки по элементам
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

  }

}
