package it.java.addressbook.generator;

import com.thoughtworks.xstream.XStream;
import it.java.addressbook.models.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  public static void main (String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<ContactData> contacts = generateContacts(count);
    saveAsXml(contacts, file);
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i ++) {
      contacts.add(new ContactData()
              .withFirstname(String.format("Firstname %s", i))
              .withLastname(String.format("Lastname %s", i))
              .withCompany(String.format("Company %s", i))
              .withHomeNumber(String.format("777 7%s", i))
              .withMobileNumber(String.format("888 8%s", i))
              .withWorkNumber(String.format("999 9%s", i))
              .withEmail1(String.format("email1.%s@gmail.com", i))
              .withEmail2(String.format("email2.%s@gmail.com", i))
              .withEmail3(String.format("email3.%s@gmail.com", i))
              .withHomepage(String.format("www.homepage%s.com", i))
              .withGroup("[none]")
              .withAddress(String.format("address %s", i))
              .withAddress2(String.format("address2 %s", i))
      );
    }
    return contacts;
  }


  private static void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }
}
