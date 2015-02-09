import com.nikgraph.social.GNode;
import com.nikgraph.social.NikGraph;
import com.nikgraph.social.UserNode;
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
        NikGraph<Integer, Integer, GNode<Integer, Integer>> nikGraph = new NikGraph<>();

        List<Integer> contactsList = null;
        Integer userId = 0;
        GNode node = new UserNode(contactsList, userId);
        nikGraph.addNode(node);

        nikGraph.prettyPrintNode(node);
    }

    @Test
    public void basicGenerateGraphTestsSingleNodeWithValues() {
        NikGraph<Integer, Integer, GNode<Integer, Integer>> nikGraph = new NikGraph<>();

        List<Integer> contactsList = null;
        Integer userId = 0;

        contactsList = populateContactsList(0, new Random().nextInt(50));
        GNode node = new UserNode(contactsList, userId);
        nikGraph.addNode(node);

        nikGraph.prettyPrintNode(node);
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
