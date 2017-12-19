package it.java.addressbook.tests;

import it.java.addressbook.models.ContactData;
import it.java.addressbook.models.GroupData;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testDeletionContact() {

    //Иду на страницу с контактами
    app.getNavigationHelper().goToHomePage();
    //Проверяю, есть ли хоть один контакт, который можно удалить
    if (!app.getContactHelper().isThereAContact()) {

      //Нет ни одного контакта, который можно было бы удалить, поэтому создаю новый контакт
      app.getContactHelper().createAContact(new ContactData("Roberto",
              "Benigni", "Cinema", "+392365478123",
              "email@test.com", "www.cinema.it", "[none]"), true);

    }
    //Есть контакт, который можно удалить
    //Удаляю контакт
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().confirmSelectedContactsDeletion();
    app.getNavigationHelper().goToHomePage();
  }

}


