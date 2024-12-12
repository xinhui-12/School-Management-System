package adt;/** * * Adapted from Chapter 4 Practical * * @param <T> * */public interface ListInterface<T> {    // add a new entry     public boolean add(T newEntry);    // add a new entry at a specified position within the size of the list    public boolean add(int newPosition, T newEntry);    // removes the entry at a given position from the list.    public T remove(int givenPosition);    //  removes all entries from the list. (Modified)    public void clear();    // change the value of the position to the new entry    public boolean replace(int givenPosition, T newEntry);    // get the entry that stores in the position    public T getEntry(int givenPosition);    // see whether the list have the search entry    public boolean contains(T anEntry);        // return the position of the entry found in the list (start with 1)    // if no entry found, -1 will return    // additional function    public int searchPosition(T anEntry);    // return the number of the entries     public int getNumberOfEntries();        // return true when the list is empty    public boolean isEmpty();}