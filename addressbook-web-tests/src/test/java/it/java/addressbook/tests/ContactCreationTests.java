package it.java.addressbook.tests;

import it.java.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void contactCreationTests() {
    app.getNavigationHelper().goToHomePage();

    //создаем список контактов до добавления нового контакта
    List<ContactData> before = app.getContactHelper().getContactList();

    //добавляем новый контакт
    app.getNavigationHelper().goToHomePage();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Alessandro", "Mariani", "Cinema",
            "+392365478956", "email@test.com", "www.cinema.it", "[none]");
    app.getContactHelper().createAContact(contact, true);


    //создаем список после добавления контакта
    List<ContactData> after = app.getContactHelper().getContactList();

    //сравниваем размеры списков до и после добавления контакта
    Assert.assertEquals(after.size(), before.size() + 1);

    //Упорядочение списков и проверка совпадения элементов списков
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
