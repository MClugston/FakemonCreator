package com.example.fakemoncreator;

import java.util.ArrayList;
import java.util.Iterator;

public class BST<E extends Comparable<E>> implements Tree<E> {
    protected TreeNode<E> root;
    protected int size = 0;

    public BST(){
    }

    public BST(E[] c){
        for(E o: c){
            insert(o);
        }
    }

    @Override
    public boolean search(E e) {
        TreeNode<E> current = root;
        while(current!=null){
            if(e.compareTo(current.element) < 0){
                current = current.left;
            } else if(e.compareTo(current.element) > 0){
                current = current.right;
            } else{
                return true;
            }
        }
        return false;
    }

    public TreeNode<E> createNewNode(E e){
        return new TreeNode<>(e);
    }

    @Override
    public boolean insert(E e) {
        if(root == null){
            root = createNewNode(e);
        } else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) { // Go down correct path
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else { // Duplicate node
                    return false;
                }
            }
            if(e.compareTo(parent.element) < 0){
                parent.left = createNewNode(e);
            } else{
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true; // Successful
    }

    @Override
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) { // Go down correct path
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else { // Located node
                break;
            }
        }

        if(current == null){
            return false;
        }

        if(current.left == null){ // Current has no left child
            if(parent == null){
                root = current.right;
            }
            else{
                if(e.compareTo(parent.element) < 0){
                    parent.left = current.right;
                } else{
                    parent.right = current.right;
                }
            }
        }
        else{ // Current has a left child
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while(rightMost.right != null){
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }

            current.element = rightMost.element;

            if(parentOfRightMost.right == rightMost){
                parentOfRightMost.right = rightMost.left;
            } else{
                parentOfRightMost.left = rightMost.left;
            }
        }

        size--;
        return true;
    }

    @Override
    public int getSize() {
        return size;
    }

    public TreeNode<E> getRoot(){
        return root;
    }

    @Override
    public void inorder() {
        inorder(root);
    }

    public void inorder(TreeNode<E> root){
        if(root == null){
            return;
        }
        inorder(root.left);
        System.out.println(root.element + " ");
        inorder(root.right);
    }

    @Override
    public void postorder() {
        postorder(root);
    }

    public void postorder(TreeNode<E> root){
        if(root == null){
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.println(root.element + " ");
    }

    @Override
    public void preorder() {
        preorder(root);
    }

    public void preorder(TreeNode<E> root){
        if(root == null){
            return;
        }
        System.out.println(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public ArrayList<TreeNode<E>> path(E e){
        ArrayList<TreeNode<E>> list = new ArrayList<>();
        TreeNode<E> current = root;

        while(current != null){
            list.add(current);
            if(e.compareTo(current.element) < 0){
                current = current.left;
            } else if(e.compareTo(current.element) > 0){
                current = current.right;
            } else{
                break;
            }
        }

        return list;
    }

    @Override
    public Iterator<E> iterator() {
        return new InorderIterator();
    }

    @Override
    public void clear() {
        root = null;
    }

    public ArrayList<E> inorderArrayList(){
        ArrayList<E> list = new ArrayList<>();

        for(E e: this){
            list.add(e);
        }

        return list;
    }

    public boolean equals(BST<E> b){
        return(this.inorderArrayList().equals(b.inorderArrayList()));
    }

    @Override
    public BST clone(){
        ArrayList<E> list = inorderArrayList();

        BST<E> output = new BST<>();
        for(E e: list){
            output.insert(e);
        }

        return output;
    }

    @Override
    public Object[] toArray(){
        return inorderArrayList().toArray();
    }

    @Override
    public <T> T[] toArray(T[] array){
        return inorderArrayList().toArray(array);
    }

    public static class TreeNode<E> {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    protected class InorderIterator implements Iterator<E>{

        public ArrayList<E> list = new ArrayList<>();
        private int current = 0;

        public InorderIterator(){
            inorder();
        }

        private void inorder(){
            inorder(root);
        }

        private void inorder(TreeNode<E> root){
            if (root == null){
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override
        public boolean hasNext() {
            if (current < list.size()){
                return true;
            }
            return false;
        }

        @Override
        public E next() {
            return list.get(current++);
        }
    }
}