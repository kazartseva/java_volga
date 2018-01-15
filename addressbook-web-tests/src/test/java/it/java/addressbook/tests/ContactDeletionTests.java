package it.java.addressbook.tests;

import it.java.addressbook.models.ContactData;
import it.java.addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testDeletionContact() {

    app.getNavigationHelper().goToHomePage();

    //Проверка и обеспечивание предусловий (наличие контакта)
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createAContact(new ContactData("Roberto",
              "Benigni", "Cinema", "+392365478123",
              "email@test.com", "www.cinema.it", "[none]"), true);
    }

    //создание списка контактов до удаления
    List<ContactData> before = app.getContactHelper().getContactList();

    //Удаляю контакт
    app.getContactHelper().selectContact(2);
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().confirmSelectedContactsDeletion();
    app.getNavigationHelper().goToHomePage();

    //создание списка контактов после удаления
    List<ContactData> after = app.getContactHelper().getContactList();

    //сравниваем размеры списков до и после удаления контакта
    Assert.assertEquals(before.size(), after.size() + 1);

    before.remove(2);

    //сравниваем списки по элементам
    Assert.assertEquals(before, after);


  }

}


