/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.generictree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bence
 */
public class Tree<T> {
 
    private TreeNode<T> root;
     
 
    public Tree() {
        super();
    }
 

    public TreeNode<T> getRootElement() {
        return this.root;
    }
 

    public void setRootElement(TreeNode<T> rootElement) {
        this.root = rootElement;
    }
     

    public List<TreeNode<T>> toList() {
        List<TreeNode<T>> list = new ArrayList<TreeNode<T>>();
        walkPreOrder(root, list);
        return list;
    }
     

    public String toString() {
        return toList().toString();
    }
     

    private void walkPreOrder(TreeNode<T> element, List<TreeNode<T>> list) {
        list.add(element);
        for (TreeNode<T> data : element.getChildren()) {
            walkPreOrder(data, list);
        }
    }
    
    
    public void listPreOrder() {
        root.listNode();
        
    }
}