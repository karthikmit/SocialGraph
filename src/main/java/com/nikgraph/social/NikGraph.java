package com.nikgraph.social;

import java.util.*;

/**
 * Class for NikGraph data structure.
 * @param <T>
 */
public class NikGraph<U, T extends GNode<U, U>> {

    Map<U, T> keysMap = new HashMap<>();

    public NikGraph() {

    }

    public void addNode(T node) {
        U primaryId = node.getPrimaryId();
        keysMap.put(primaryId, node);
    }

    public int findSocialDistance(T node1, T node2) {
        if(node1.equals(node2)) {
            return 0;
        } else {
            return doBFS(node1, node2);
        }
    }

    private int doBFS(T node1, T node2) {
        Queue<U> fifoQueue = new ArrayDeque<>();
        Map<U, U> visitedMap = new HashMap<>();

        fifoQueue.add(node1.getPrimaryId());
        visitedMap.put(node1.getPrimaryId(), null);

        while (fifoQueue.size() > 0) {
            U element = fifoQueue.element();
            fifoQueue.remove();
            if(element != null) {
                T node = this.getNode(element);
                if(node != null) {
                    List<U> valuesList = node.getValuesList();
                    for(U value : valuesList) {
                        if(visitedMap.containsKey(value)) {
                            continue;
                        } else {
                            if(value.equals(node2.getPrimaryId())) {
                                return 1 + reachLength(element, visitedMap);
                            } else {
                                fifoQueue.add(value);
                                visitedMap.put(value, element);
                            }
                        }
                    }
                }
            }
        }

        return 0;
    }

    private int reachLength(U element, Map<U, U> visitedMap) {
        U prevElement = visitedMap.get(element);
        if(prevElement == null) {
            return 0;
        }
        return 1 + reachLength(prevElement, visitedMap);
    }

    public void prettyPrintNode(T node) {
        List<U> values = node.getValuesList();

        // To save from Null pointer Exception.
        if(values == null) {
            values = new ArrayList<>();
        }

        System.out.println("Node contents of " + node.getPrimaryId() + ": ");
        for(U value : values) {
            System.out.print(value.toString() + " -> ") ;
        }
        System.out.print("END");
    }

    public T getNode(U key) {
        if(keysMap.containsKey(key)) {
            return keysMap.get(key);
        }
        return null;
    }
}
