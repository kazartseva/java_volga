package it.java.addressbook.tests;

import it.java.addressbook.models.ContactData;
import it.java.addressbook.models.Contacts;
import it.java.addressbook.models.GroupData;
import it.java.addressbook.models.Groups;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroupTest extends TestBase{

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
  public void testContactRemoveFromGroup() {
    ensurePreconditions();
    Contacts contactsAll = app.db().contacts();
    Groups groupsAll = app.db().groups();
    Contacts contactsOK = new Contacts(); //контакты, подходящие для теста

    //проверяем у каждого контакта добавлен ли он хотя бы в одну группу.
    //Если да, добавляем его в список контактов, подходящих для теста
    for (ContactData contact : contactsAll ) {
      if (contact.getGroups().size() > 0) {
        contactsOK = contactsOK.withAdded(contact);
      }
    }

    //Если ни один контакт не добавлен ни в одну группу, добавляем какой-нибудь контакт в какую-нибудь группу
    if (contactsOK.size() == 0) {
      ContactData contact = contactsAll.iterator().next();
      GroupData group = groupsAll.iterator().next();
      app.contact().addToGroup(contact, group);
      contactsOK = contactsAll.withAdded(contact);
    }

    ContactData selectedContact = contactsOK.iterator().next(); //контакт, подходящий для теста
    GroupData selectedGroup = selectedContact.getGroups().iterator().next(); //одна из его групп

    //удаляем выбранный контакт
    app.goTo().homePage();
    app.goTo().selectedGroupPage(selectedGroup);
    app.contact().removeFromGroup(selectedContact);

    Groups groupsBefore = selectedContact.getGroups();
    Groups groupsAfter = app.db().contactsSelectedById(selectedContact.getId()).iterator().next().getGroups();
    assertThat(groupsAfter, equalTo(groupsBefore.without(selectedGroup)));






  }

}
