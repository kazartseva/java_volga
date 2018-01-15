package it.java.addressbook.tests;

import it.java.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test(enabled = false)
  public void testContactModification() {

    app.getNavigationHelper().goToHomePage();
    //Проверка и обеспечение предусловий (наличие контакта для модификации)
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createAContact(new ContactData("Patrizia",
              "Fannucci", "LUDEC", "333666", "email",
              "www", "[none]"),true);
    }

    //Получение списка контактов до модификации
    List<ContactData> before = app.getContactHelper().getContactList();

    //Модификация контакта
    app.getContactHelper().initContactModification(3);
    ContactData contact = new ContactData("Luca", "Ivanov",
            "Bar", "+392365478123", "email@test.com",
            "www.cinema.it", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();

    //Получение списка контактов после модификации
    List<ContactData> after = app.getContactHelper().getContactList();

    //сравниваем размеры списков до и после удаления контакта
    Assert.assertEquals(before.size(), after.size() );

    before.remove(3);
    before.add(contact);

    //сортируем и сравниваем списки по элементам
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

  }
}
