package it.java.addressbook.tests;

import it.java.addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void contactCreationTests() {
    app.goTo().homePage();

    //создаем список контактов до добавления нового контакта
    Set<ContactData> before = app.contact().all();

    //добавляем новый контакт
    ContactData contact = new ContactData().withFirstname("Alessandro").withLastname("Mariani").withCompany("Cinema").withHomenumber("+392365478956").
            withEmail("email@test.com").withHomepage("www.cinema.it").withGroup("[none]");
    app.contact().createAContact(contact, true);


    //создаем список после добавления контакта
    Set<ContactData> after = app.contact().all();

    //сравниваем размеры списков до и после добавления контакта
    Assert.assertEquals(after.size(), before.size() + 1);

    //Упорядочение списков и проверка совпадения элементов списков
    before.add(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()));
    Assert.assertEquals(before, after);
  }

}
