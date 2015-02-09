package com.nikgraph.social;

import java.util.ArrayList;
import java.util.List;

/**
 * User Node which user ID and the list of contacts ...
 */
public class UserNode implements GNode<Integer, Integer> {

    private List<Integer> contactsList;
    private final Integer userId;

    public UserNode(List<Integer> contactsList, Integer userId) {
        this.contactsList = contactsList;
        this.userId = userId;
    }

    public void addContact(Integer contactId) {
        if(contactsList == null) {
            contactsList = new ArrayList<>();
        }

        contactsList.add(contactId);
    }

    public void removeContact(Integer contactId) {
        if(contactsList != null) {
            contactsList.remove(contactId);
        }
    }

    @Override
    public Integer getPrimaryId() {
        return userId;
    }

    @Override
    public List<Integer> getValuesList() {
        return contactsList;
    }
}
