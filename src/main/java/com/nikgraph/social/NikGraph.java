package com.nikgraph.social;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for NikGraph data structure.
 * @param <T>
 */
public class NikGraph<U, V, T extends GNode<U, V>> {

    Map<U, List<V>> keysMap = new HashMap<>();

    public NikGraph() {

    }

    public void addNode(T node) {
        U primaryId = node.getPrimaryId();
        keysMap.put(primaryId, node.getValuesList());
    }

    public void prettyPrintNode(T node) {
        List<V> values = node.getValuesList();

        // To save from Null pointer Exception.
        if(values == null) {
            values = new ArrayList<>();
        }

        System.out.println("Node contents of " + node.getPrimaryId() + ": ");
        for(V value : values) {
            System.out.print(value.toString() + " -> ") ;
        }
        System.out.print("END");
    }
}
