package it.java.addressbook.tests;

import it.java.addressbook.models.ContactData;
import it.java.addressbook.models.Contacts;
import it.java.addressbook.models.GroupData;
import it.java.addressbook.models.Groups;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class ContactAddToGroupTest extends TestBase {

  private void ensurePreconditions() {
    //Проверяем наличие хотя бы одной группы, и если ее нет - создаем
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test1").withHeader("").withFooter(""));
    }

    //Проверяем наличие хотя бы одного контакта, и если его нет - создаем
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().createAContact(new ContactData()
              .withFirstname("Alessandro")
              .withLastname("Mariani")
              .withCompany("Cinema")
              .withHomeNumber("777")
              .withMobileNumber("888")
              .withWorkNumber("")
              .withEmail1("email1@test.com")
              .withEmail2("")
              .withEmail3("email3@test.com")
              .withHomepage("www.cinema.it")
              .withAddress("address")
              .withAddress2("address2")
              .withPhoto(new File("src/test/resources/avatar.PNG")), true);
    }

  }

  @Test
  public void testContactAddToGroup() {
    ensurePreconditions();
    Contacts contactsAll = app.db().contacts();
    Groups groupsAll = app.db().groups();
    Contacts contactsOK = new Contacts(); //контакты, подходящие для теста

    //проверяем у каждого контакта во все ли группы он добавлен.
    //Если нет, добавляем его в список контактов, подходящих для теста
    for (ContactData contact : contactsAll ) {
      if (contact.getGroups().size() < groupsAll.size()) {
        contactsOK = contactsOK.withAdded(contact);
      }
    }

    //Если все контакты добавлены во все группы, создаем новую группу
    if (contactsOK.size() == 0) {
      GroupData newGroup = new GroupData();
      app.goTo().groupPage();
      app.group().create(newGroup.withName("TestGroup"));
      //и теперь все контакты подходят для теста
      contactsOK = contactsAll;
    }

    //Выбираем подходящий контакт и создаем список групп, в которые он еще не добавлен
    ContactData modifiedContact = contactsOK.iterator().next();

    Groups groupsOK = groupsAll;
    for (GroupData group : modifiedContact.getGroups() ) {
      groupsOK = groupsOK.without(group);
    }

    //Выбираем группу, в которую можно добавить контакт
    GroupData selectedGroup = groupsOK.iterator().next();

    //удостоверяемся, что в выбранной группе нет выбранного контакта
    app.goTo().homePage();
    app.goTo().selectedGroupPage(selectedGroup);
    Assert.assertFalse(app.contact().isThereAContact(modifiedContact));

    //Добавляем контакт в группу
    app.goTo().homePage();
    app.contact().addToGroup(modifiedContact, selectedGroup);

    //идем на страницу группы и проверяем наличие добавленного контакта
    app.goTo().selectedGroupPage(selectedGroup);
    Assert.assertTrue(app.contact().isThereAContact(modifiedContact));

  }

}



