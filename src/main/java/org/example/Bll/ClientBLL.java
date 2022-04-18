package org.example.Bll;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.example.Bll.Validators.EmailValidator;
import org.example.Bll.Validators.ClientAgeValidator;
import org.example.Bll.Validators.NameValidator;
import org.example.Bll.Validators.Validator;
import org.example.Dao.ClientDAO;
import org.example.Dao.OrderDAO;
import org.example.Model.Client;

/**
 * @author Nemes Mihnea-Andrei
 * ClientBLL
 * checks whether the input is valid
 * @since 27 Apr, 2021
 */
public class ClientBLL {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    /**
     * @constructor initialize the clientDAO and the list of validators
     */
    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        validators.add(new ClientAgeValidator());
        validators.add(new NameValidator());
        clientDAO = new ClientDAO();
    }

    /**
     * findById
     * returns the client with the id given as a parameter, from the database
     *
     * @param id
     * @return Client
     */
    public Client findById(int id) {
        Client client = clientDAO.findById(id);
        if (client == null) {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return client;
    }

    /**
     * findAll
     * returns a list containing all the clients in the database
     *
     * @return List<Client>
     */
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        clients = clientDAO.findAll();
        if (clients == null) {
            throw new NoSuchElementException("The operation could not be performed!");
        }
        return clients;
    }

    /**
     * insert
     * it inserts into the database a new client given as a parameter, returns true if the insert was successful and false otherwise
     *
     * @param client
     * @return boolean
     * @throws IllegalAccessException
     */
    public boolean insert(Client client) throws IllegalAccessException {
        for (Validator v : validators) {
            if (!v.validate(client)) {
                System.out.println(v.toString());
                return false;
            }
        }
        clientDAO.insert(client);
        return true;
    }

    /**
     * update
     * it updates the fields of a client with id given as a parameter, with the values specified in the client also given as a parameter, returns true if this was successful and false otherwise
     *
     * @param id
     * @param client
     * @return boolean
     * @throws IllegalAccessException
     */
    public boolean update(int id, Client client) throws IllegalAccessException {
        for (Validator v : validators) {
            if (!v.validate(client)) {
                return false;
            }
        }
        clientDAO.update(client, id);
        return true;
    }

    /**
     * delete
     * it deletes a client with id given as a parameter and also all the orders associated with the specified client
     *
     * @param id
     */
    public void delete(int id) {
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.specialOrderDelete(id, "Client");
        clientDAO.delete(id);
    }

}
