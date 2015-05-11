package com.crux.demo.model;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: aaron.stone
 * Date: 10/15/13
 * Time: 2:08 PM
 * To change this template use File | Settings | File Templates.
 */

public class User {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;

    protected static ArrayList<User> users;


    public User() {

    }

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
