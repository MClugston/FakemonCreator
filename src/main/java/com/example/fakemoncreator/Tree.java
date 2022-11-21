package com.example.fakemoncreator;

import java.util.Collection;

public interface Tree<E> extends Collection<E>{
    // Return true if present
    public boolean search(E e);

    // Insert element e; return true if successful
    public boolean insert(E e);

    // Delete element e; return true if successful
    public boolean delete(E e);

    // Return size
    public int getSize();

    // Inorder traversal
    public default void inorder(){
    }

    // Postorder traversal
    public default void postorder(){
    }

    // Preorder traversal
    public default void preorder(){
    }

    @Override
    public default boolean isEmpty(){
        return (size()==0);
    }

    @Override
    public default boolean contains(Object e){
        return search((E) e);
    }

    @Override
    public default boolean add(E e){
        return insert(e);
    }

    @Override
    public default boolean remove(Object e){
        return delete((E) e);
    }

    @Override
    public default int size(){
        return getSize();
    }

    @Override
    public default boolean containsAll(Collection<?> c){
        return false;
    }

    @Override
    public default boolean addAll(Collection<? extends E> c){
        for(E e: c){
            insert(e);
        }
        return true;
    }

    @Override
    public default boolean removeAll(Collection<?> c){
        for(Object e: c){
            delete((E)e);
        }
        return true;
    }

    @Override
    public default boolean retainAll(Collection<?> c){
        return false;
    }

    @Override
    public default Object[] toArray(){
        return null;
    }

    @Override
    public default <T> T[] toArray(T[] array){
        return null;
    }
}