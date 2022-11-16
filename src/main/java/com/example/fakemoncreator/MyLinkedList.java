package com.example.fakemoncreator;

import java.util.Collection;
import java.util.Iterator;

// Linked List structure of my creation

public class MyLinkedList<E> implements Collection<E> {
    private Node<E> head, tail;
    private int size = 0;

    // Default constructor
    public MyLinkedList(){
    }

    // Return head if it exists
    public E getFirst(){
        if(size==0){
            return null;
        }
        return head.element;
    }

    // Return tail if it exists
    public E getLast(){
        if(size==0){
            return null;
        }
        return tail.element;
    }

    // Return the item at the index
    public E get(int index){
        if(index<0 || index>size){
            return null;
        }
        Node<E> current = head;
        for(int i=0; i<index; i++){
            current = current.next;
        }
        return current.element;
    }

    // Add an item to the front
    public void addFirst(E item){
        Node<E> node = new Node<>(item);
        node.next = head;
        head = node;
        size++;
        if(tail==null){
            tail = head;
        }
    }

    // Add an item to the end
    public void addLast(E item){
        Node<E> node = new Node<>(item);
        if(tail==null){
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    // Add an item at an index
    public boolean add(int index, E item){
        if(index==0){
            addFirst(item);
        } else if(index >= size){
            addLast(item);
        } else{
            Node<E> current = head;
            for(int i=0; i<index; i++){
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<>(item);
            (current.next).next = temp;
            size++;
        }
        return true;
    }

    // Return size
    @Override
    public int size() {
        return size;
    }

    // Check size to determine if the list is empty
    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }

    // Iterate through each item, and check if the element is the same as o.
    @Override
    public boolean contains(Object o) {
        for(E item: this){
            if(item.equals(o)){
                return true;
            }
        }
        return false;
    }

    // Return custom Iterator
    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    // Necessary for Collection, not used in this project
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    // Necessary for Collection, not used in this project
    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    // Default add calls addLast
    @Override
    public boolean add(E item){
        addLast(item);
        return true;
    }

    // Remove an object by Object, not index
    @Override
    public boolean remove(Object o) {
        Node<E> current = head;
        if(current.element.equals(o)){ // Check if the first item is o, removeFirst if so
            removeFirst();
            return true;
        }
        // Go through list
        for(int i=0; i<size; i++){
            if((current.next != null) && (current.next.element.equals(o))){ // Check if next element is o
                if(current.next.equals(tail)){ // If o is the last item, removeLast
                    removeLast();
                    return true;
                }
                current.next = (current.next).next; // remove o by replacing current.next with the item after it
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Necessary for Collection, not used in this project
    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    // Add all items from a Collection
    @Override
    public boolean addAll(Collection<? extends E> c) {
        for(E item: c){
            add(item);
        }
        return true;
    }

    // Necessary for Collection, not used in this project
    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    // Necessary for Collection, not used in this project
    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    // Clear by seting head and tail to null
    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    // Remove and return the first item
    public E removeFirst(){
        if(size==0){
            return null;
        }
        E output = head.element;
        head = head.next;
        size--;
        if (head == null){
            tail = null;
        }
        return output;
    }

    // Remove and return the last item
    public E removeLast(){
        if (size==0){
            return null;
        }
        Node<E> current = head;
        for(int i=0; i<size-2; i++){
            current = current.next;
        }

        E output = tail.element;

        tail = current;
        tail.next = null;
        size--;

        return output;
    }

    // Remove and return an indexed item
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        }
        Node<E> previous = head;

        for (int i = 1; i < index; i++) {
            previous = previous.next;
        }

        Node<E> current = previous.next;
        previous.next = current.next;
        size--;

        return current.element;
    }

    // Custom Iterator Class
    private class LinkedListIterator implements Iterator<E>{
        private Node<E> current = head;

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public E next() {
            E item = current.element;
            current = current.next;
            return item;
        }
    }

    // Node Class
    private static class Node<E>{
        protected E element;
        protected Node<E> next;

        public Node(E element){
            this.element = element;
        }
    }
}
