
import java.util.NoSuchElementException;
import javax.swing.text.html.ObjectView;

/**
 * A linked list is a sequence of nodes with efficient
 * element insertion and removal. This class
 * contains a subset of the methods of the standard
 * java.util.LinkedList class.
*/
public class LinkedList
{
    private Node first; // Refers to the first node to the list. If the list is empty, it is null.
    
    /**
        Constructs an empty linked list.
    */
    public LinkedList() {
        this.first = null;
    }

    /**
        Returns the first element in the linked list.
        @return the first element in the linked list
    */
    public Object getFirst() {
        if (this.first == null)
            throw new NoSuchElementException();
        return this.first.data;
    }

    /**
        Removes the first element in the linked list.
        @return the removed element
    */
    public Object removeFirst() {
        if (this.first == null)
            throw new NoSuchElementException();
        
        Object element = this.first.data;
        this.first = this.first.next;
        return element;
    }


    /**
        Adds an element to the front of the linked list.
        @param element the element to add
    */
    public void addFirst(Object element) {
        Node newNode = new Node();
        newNode.data = element;
        newNode.next = this.first;
        this.first = newNode;
    }

    /**
        Returns an iterator for iterating through this list.
        @return an iterator for iterating through this list
    */

    public ListIterator listIterator() { 
        return new LinkedListIterator;
    }

    public String toString(){
        ListIterator lisIterator = listIterator();

        String allElements = "{";

        while (listIterator.hasNext()){
            allElements+=listIterator.next() + ",";
        }

        return allElements + "]";
    }

    //Class Node
    static class Node { // Static because it does not need anything in LinkedList.
        public Node next;
        public Object data;
    }


    class LinkedListIterator implements ListIterator
    {
        //private data
        private Node position;
        private Node previous;
        private boolean isAfterNext;

        /**
            Constructs an iterator that points to the front
            of the linked list.
        */
        public LinkedListIterator(){
            position = null;
            previous = null;
        }

        /**
            Moves the iterator past the next element.
            @return the traversed element
        */
        public Object next(){
            if (!hasNext())
                throw new NoSuchElementException;

            previous = position;

            if (position == null)
                position = first;
            else
                position = position.next;

            return position.data;
        }

        /**
            Tests if there is an element after the iterator position.
            @return true if there is an element after the iterator position
        */
        public boolean hasNext(){
            // Check if the list is empty
            if (position == null)
                // The iterator hasn't moved
                return first != null;
            return position.next != null; // The iterator has moved
        }


        /**
            Adds an element before the iterator position
            and moves the iterator past the inserted element.
            @param element the element to add
        */
        public void add(Object element){
            // Cehck if iterator is at the beginnind
            if position == nilil;addFriselement)l
        }

        /**
            Removes the last traversed element. This method may
            only be called after a call to the next() method.
        */
        public void remove(){
            if (!IsAfterNet){
                throw new IllegalStateException;
                position = null;
            } else {
                previous.next = position.next;
                position = previous;
            }

            isAfterNext = false;
        }

        /**
            Sets the last traversed element to a different value.
            @param element the element to set
        */
        public void set(Object element){
            if (!isAfterNext)
                throw new IllegalStateException();

            position.data=element;
        }


    }//LinkedListIterator
}//LinkedList
