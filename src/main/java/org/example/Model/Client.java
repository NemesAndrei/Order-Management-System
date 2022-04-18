package org.example.Model;

/**
 * @author Nemes Mihnea-Andrei
 * Client
 * represents the class that corresponds to the Client table in the database
 * @since 27 Apr, 2021
 */

public class Client {
    int id;
    String name;
    String email;
    int age;

    /**
     * Client
     * constructor for Client will all fields
     * @param id
     * @param name
     * @param email
     * @param age
     */
    public Client(int id, String name, String email, int age) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    /**
     * Client
     * construction for Client without the id
     * @param name
     * @param email
     * @param age
     */
    public Client(String name, String email, int age) {
        super();
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Client() {
    }

    /**
     * getId
     * getter for client id
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * setId
     * setter for client id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getName
     * getter for client name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * setName
     * setter for client name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getEmail
     * getter for client email
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * setEmail
     * setter for client email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getAge
     * getter for client age
     * @return int
     */
    public int getAge() {
        return age;
    }

    /**
     * setAge
     * setter for client age
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }
}
