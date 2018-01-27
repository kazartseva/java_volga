package it.java.addressbook.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "lastname")
  private String lastname;

  @Column(name = "company")
  private String company;

  @Column(name = "home")
  @Type(type = "text")
  private String homenumber;

  @Column(name = "mobile")
  @Type(type = "text")
  private String mobile;

  @Column(name = "work")
  @Type(type = "text")
  private String work;

  @Transient
  private String allPhones;

  @Column(name = "email")
  @Type(type = "text")
  private String email1;

  @Column(name = "email2")
  @Type(type = "text")
  private String email2;

  @Column(name = "email3")
  @Type(type = "text")
  private String email3;

  @Transient
  private String allEmails;

  @Column(name = "homepage")
  @Type(type = "text")
  private String homepage;

  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Column(name = "address2")
  @Type(type = "text")
  private String address2;

  @Transient
  private String mainAddress;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups"
          , joinColumns = @JoinColumn(name = "id")
          , inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  @Column(name = "photo")
  @Type(type = "text")
  private String photo;


  public ContactData withId(int id) {
    this.id = id;

    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withHomeNumber(String homenumber) {
    this.homenumber = homenumber;
    return this;
  }

  public ContactData withMobileNumber(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactData withWorkNumber(String work) {
    this.work = work;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withEmail1(String email1) {
    this.email1 = email1;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withMainAddress(String mainAddress) {
    this.mainAddress = mainAddress;
    return this;
  }


  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();

    return this;
  }

  public ContactData withHomepage(String homepage) {
    this.homepage = homepage;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withAddress2(String address2) {
    this.address2 = address2;
    return this;
  }

  //добавляет контакту какую-нибудь группу
  public ContactData inGroup(GroupData group) {
    if (groups == null) {
      groups = new HashSet<>();
    }
    groups.add(group);
    return this;
  }

  public Groups getGroups() {
    if (groups == null) {
      groups = new HashSet<>();
    }
    return new Groups(groups);
  }


  public int getId() {
    return id;
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

  public String getHomeNumber() {
    return homenumber;
  }

  public String getMobileNumber() {
    return mobile;
  }

  public String getWorkNumber() {
    return work;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getEmail1() {
    return email1;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public String getHomepage() {
    return homepage;
  }

  public String getAddress() {
    return address;
  }

  public String getAddress2() {
    return address2;
  }

  public String getMainAddress() {
    return mainAddress;
  }

  public File getPhoto() {
    return new File(photo);
  }


  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", company='" + company + '\'' +
            ", homenumber='" + homenumber + '\'' +
            ", mobile='" + mobile + '\'' +
            ", work='" + work + '\'' +
            ", email1='" + email1 + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", homepage='" + homepage + '\'' +
            ", address='" + address + '\'' +
            ", address2='" + address2 + '\'' +
            ", groups=" + groups +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (company != null ? !company.equals(that.company) : that.company != null) return false;
    if (homenumber != null ? !homenumber.equals(that.homenumber) : that.homenumber != null) return false;
    if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
    if (work != null ? !work.equals(that.work) : that.work != null) return false;
    if (email1 != null ? !email1.equals(that.email1) : that.email1 != null) return false;
    if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
    if (email3 != null ? !email3.equals(that.email3) : that.email3 != null) return false;
    if (homepage != null ? !homepage.equals(that.homepage) : that.homepage != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (address2 != null ? !address2.equals(that.address2) : that.address2 != null) return false;
    return groups != null ? groups.equals(that.groups) : that.groups == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (company != null ? company.hashCode() : 0);
    result = 31 * result + (homenumber != null ? homenumber.hashCode() : 0);
    result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
    result = 31 * result + (work != null ? work.hashCode() : 0);
    result = 31 * result + (email1 != null ? email1.hashCode() : 0);
    result = 31 * result + (email2 != null ? email2.hashCode() : 0);
    result = 31 * result + (email3 != null ? email3.hashCode() : 0);
    result = 31 * result + (homepage != null ? homepage.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (address2 != null ? address2.hashCode() : 0);
    result = 31 * result + (groups != null ? groups.hashCode() : 0);
    return result;
  }


}
