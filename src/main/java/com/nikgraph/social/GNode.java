package com.nikgraph.social;

import java.util.List;

/**
 * GNode interface specifies the constraints of a node in NikGraph Datastructure ...
 *
 * Every node should have a valid Primary ID
 *  which uniquely identifies the node and a values list
 */

public interface GNode<T, U> {

    T getPrimaryId();
    List<U> getValuesList();
}
