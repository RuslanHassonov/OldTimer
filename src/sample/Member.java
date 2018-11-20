package sample;

import java.util.ArrayList;
//Basic Member class

public class Member {
    private String firstName;
    private String lastName;
    private String street;
    private int houseNumber;
    private int zipCode;
    private String city;
    private String country;
    private String phoneNumber;
    private String mobileNumber;
    private String emailAddress;

    //constructors

    public Member() {
    }

    public Member(String lastName, String firstName, String street, int houseNumber, int zipCode, String city, String country, String phoneNumber, String mobileNumber, String emailAddress) {
        setFirstName(firstName);
        setLastName(lastName);
        setStreet(street);
        setHouseNumber(houseNumber);
        setZipCode(zipCode);
        setCity(city);
        setCountry(country);
        setPhoneNumber(phoneNumber);
        setMobileNumber(mobileNumber);
        setEmailAddress(emailAddress);
    }

    //getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString()
    {return String.format("First Name: %s%nLast Name : %s",getFirstName(),getLastName());}
}
