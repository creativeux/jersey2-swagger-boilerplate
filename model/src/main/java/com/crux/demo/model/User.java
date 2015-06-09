package com.crux.demo.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: aaron.stone
 * Date: 10/15/13
 * Time: 2:08 PM
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement(name = "user")
public class User {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String company;
    private String phoneNumber;
    private String address1;
    private String address2;

    protected static ArrayList<User> users;


    public User() {

    }

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(String firstName, String lastName, String email, String company, String phoneNumber, String address1, String address2) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.address1 = address1;
        this.address2 = address2;
    }

    /**
     * Add a user to the system.
     *
     * @param user
     * @return
     */
    public static void addUser(User user) {
        //Assign a UUID when saving.
        user.setId(UUID.randomUUID());
        getUsers().add(user);
    }


    /**
     * Get a list of users in the system.
     *
     * @return
     */
    public static List<User> getUsers() {
        if(users == null) {
            users = new ArrayList<User>();
        }

        return users;
    }


    /**
     * Get a user by his/her unique ID.
     *
     * @param userId
     * @return
     */
    public static User getUserById(String userId) {
        for(User user : getUsers()) {
            if(userId.equals(user.getId().toString())) {
                return user;
            }
        }

        return null;
    }



    /**
     * Getters and Setters
     */
    @XmlElement(name = "id")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @XmlElement(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name = "company")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @XmlElement(name = "phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @XmlElement(name = "address1")
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @XmlElement(name = "address2")
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
}
