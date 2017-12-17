package it.java.addressbook.models;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String company;
  private final String homenumber;
  private final String email;
  private final String homepage;
  private String group;

  public ContactData(String firstname, String lastname, String company, String homenumber, String email, String homepage, String group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.company = company;
    this.homenumber = homenumber;
    this.email = email;
    this.homepage = homepage;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getCompany() {
    return company;
  }

  public String getHomenumber() {
    return homenumber;
  }

  public String getEmail() {
    return email;
  }

  public String getHomepage() {
    return homepage;
  }

  public String getGroup() {
    return group;
  }
}
