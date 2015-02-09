import com.nikgraph.social.GNode;
import com.nikgraph.social.NikGraph;
import com.nikgraph.social.UserNode;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * NikGraph basic functionality Tests
 */
public class BasicGraphTests {

    @Test
    public void basicGenerateGraphTestsSingleNodeWithoutValues() {
        NikGraph<Integer, GNode<Integer, Integer>> nikGraph = new NikGraph<>();

        List<Integer> contactsList = null;
        Integer userId = 0;
        GNode node = new UserNode(contactsList, userId);
        nikGraph.addNode(node);

        nikGraph.prettyPrintNode(node);
    }

    @Test
    public void basicGenerateGraphTestsSingleNodeWithValues() {
        NikGraph<Integer, GNode<Integer, Integer>> nikGraph = new NikGraph<>();

        List<Integer> contactsList = null;
        Integer userId = 0;

        contactsList = populateContactsList(0, new Random().nextInt(50));
        GNode node = new UserNode(contactsList, userId);
        nikGraph.addNode(node);

        nikGraph.prettyPrintNode(node);
    }

    @Test
    public void basicGenerateGraphTestsSeveralWithValues() {
        NikGraph<Integer, GNode<Integer, Integer>> nikGraph = new NikGraph<>();

        List<Integer> contactsList = null;
        int userId = 0;

        for(userId = 0; userId < 100; userId++) {
            contactsList = populateContactsList(0, new Random().nextInt(50));
            GNode node = new UserNode(contactsList, userId);
            nikGraph.addNode(node);

            System.out.println(node.getValuesList().size());
        }
    }

    @Test
    public void addContactTest() {
        NikGraph<Integer, GNode<Integer, Integer>> nikGraph = new NikGraph<>();

        Integer userId = 0;
        addNewContacts(nikGraph, userId, 1);
        Assert.assertEquals(nikGraph.getNode(0).getValuesList().size(), nikGraph.getNode(1).getValuesList().size());
    }

    @Test
    public void socialDistanceTestsImmediateContact() {
        NikGraph<Integer, GNode<Integer, Integer>> nikGraph = new NikGraph<>();

        Integer userId = 0;
        int contactId = 1;
        addNewContacts(nikGraph, userId, contactId);

        userId = 1;
        contactId = 2;
        addNewContacts(nikGraph, userId, contactId);

        userId = 2;
        contactId = 3;
        addNewContacts(nikGraph, userId, contactId);

        userId = 3;
        contactId = 4;
        addNewContacts(nikGraph, userId, contactId);

        int socialDistance = nikGraph.findSocialDistance(nikGraph.getNode(0), nikGraph.getNode(4));
        System.out.println(socialDistance);

        userId = 4;
        contactId = 0;
        addNewContacts(nikGraph, userId, contactId);

        socialDistance = nikGraph.findSocialDistance(nikGraph.getNode(0), nikGraph.getNode(4));
        System.out.println(socialDistance);
    }

    private void addNewContacts(NikGraph<Integer, GNode<Integer, Integer>> nikGraph, Integer userId, int contactId) {
        UserNode node = (UserNode) nikGraph.getNode(userId);

        if(node == null) {
            node = new UserNode(null, userId);
        }
        node.addContact(contactId);

        GNode<Integer, Integer> contactsNode = nikGraph.getNode(contactId);
        List<Integer> contactsListOfContact = null;
        if(contactsNode != null) {
            contactsNode.getValuesList();
        }

        if(contactsListOfContact == null || (!contactsListOfContact.contains(userId))) {
            if(contactsListOfContact == null) {
                contactsListOfContact = new ArrayList<>();
            }
            contactsListOfContact.add(userId);
            nikGraph.addNode(new UserNode(contactsListOfContact, contactId));
        }

        nikGraph.addNode(node);
    }

    private List<Integer> populateContactsList(int exclude, int size) {
        List<Integer> contactsList;
        contactsList = new ArrayList<>();

        while (contactsList.size() < size) {
            int nextInt = 0;
            do {
                nextInt = new Random().nextInt(size);
            } while(nextInt == exclude);
            contactsList.add(nextInt);
        }
        return contactsList;
    }
}
