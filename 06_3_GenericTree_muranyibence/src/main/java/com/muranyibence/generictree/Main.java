/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.generictree;

/**
 *
 * @author Bence
 */
public class Main {

    public static void main(String[] args) {
        Tree<String> tree = new Tree<>();

        TreeNode<String> root = new TreeNode<>("0");
        TreeNode<String> node1 = new TreeNode<>("1");
        TreeNode<String> node2 = new TreeNode<>("2");
        TreeNode<String> node3 = new TreeNode<>("3");
        TreeNode<String> node1_1 = new TreeNode<>("1_1");
        TreeNode<String> node1_2 = new TreeNode<>("1_2");
        TreeNode<String> node2_1 = new TreeNode<>("2_1");
        TreeNode<String> node3_1 = new TreeNode<>("3_1");
        TreeNode<String> node3_2 = new TreeNode<>("3_2");
        TreeNode<String> node3_3 = new TreeNode<>("3_3");
        TreeNode<String> node3_3_1 = new TreeNode<>("3_3_1");

        tree.setRootElement(root);
        root.addChild(node1);
        root.addChild(node2);
        root.addChild(node3);
        node1.addChild(node1_1);
        node1.addChild(node1_2);
        node2.addChild(node2_1);
        node3.addChild(node3_1);
        node3.addChild(node3_2);
        node3.addChild(node3_3);
        node3_3.addChild(node3_3_1);
        
        tree.listPreOrder();
    }
}
