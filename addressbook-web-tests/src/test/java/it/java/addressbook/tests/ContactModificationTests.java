package it.java.addressbook.tests;

import it.java.addressbook.models.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {

    //Иду на страницу с контактами
    app.getNavigationHelper().goToHomePage();
    //Проверяю, есть ли на странице хотя бы один контакт, который можно модифицировать
    if (! app.getContactHelper().isThereAContact()) {
      //Страница контактов пустая, поэтому создаю новый контакт
      app.getContactHelper().createAContact(new ContactData("Patrizia",
              "Fannucci", "LUDEC", "333666", "email",
              "www", "[none]"),true);
    }
    //На странице контактов есть хотя бы один контакт
    //Модифицирую контакт
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Franco", "Buselli",
            "Bar", "+392365478123", "email@test.com",
            "www.cinema.it", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();

  }
}
