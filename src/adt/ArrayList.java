package adt;

/**
 *
 * Adapted from Chapter 4 Practical
 *
 * @param <T>
 *
 */
public class ArrayList<T> implements ListInterface<T> {

    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        numberOfEntries = 0;
        array = (T[]) new Object[initialCapacity];
    }

    @Override
    public boolean add(T newEntry) {
        return add(numberOfEntries + 1, newEntry);
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = true;
        if (isFull()) {
            expandArray();
        }
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            makeRoom(newPosition);
            array[newPosition - 1] = newEntry;
            numberOfEntries++;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public T remove(int givenPosition) {
        T result = null;
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = array[givenPosition - 1];
            if (givenPosition < numberOfEntries) {
                removeGap(givenPosition);
            }
            numberOfEntries--;
        }
        return result;
    }

    @Override
    public void clear() {
        for(int i=0; i<numberOfEntries;i++){
            array[i] = null;
        }
        numberOfEntries = 0;
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = true;
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            array[givenPosition - 1] = newEntry;
        } else {
            isSuccessful = false;
        }
        return isSuccessful;
    }

    @Override
    public T getEntry(int givenPosition) {
        T result = null;
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = array[givenPosition - 1];
        }
        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        for (int index = 0; !found && (index < numberOfEntries); index++) {
            if (anEntry.equals(array[index])) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public int searchPosition(T anEntry) {
        int position = -1;
        for (int index = 0; (position == -1) && (index < numberOfEntries); index++) {
            if (anEntry.equals(array[index])) {
                position = index + 1;
                return position;
            }
        }
        return position;
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }
    
    @Override
    public String toString() {
        String outputStr = "";
        for (int index = 0; index < numberOfEntries; ++index) {
            outputStr += (index +1) + ". " + array[index] + "\n";
        }

        return outputStr;
    }
    
    private boolean isFull() {
        return numberOfEntries == array.length;
    }
    
    /**
     * Task: Expand the array by double up the array length
     * Precondition: the oldArray is full
     */
    private void expandArray() {
        T[] oldArray = array;
        array = (T[]) new Object[2 * oldArray.length];
        for (int i = 0; i < numberOfEntries; i++) {
            array[i] = oldArray[i];
        }
        System.arraycopy(oldArray, 0, array, 0, numberOfEntries);
    }


    /**
     * Task: Makes room for a new entry at newPosition. 
     * Precondition:
     * newPosition >= 1 && newPosition <= numberOfEntries + 1 
     * numberOfEntries is the array's numberOfEntries before addition.
     */
    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = numberOfEntries - 1;

        // move each entry to next higher index, starting at end of
        // array and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--) {
            array[index + 1] = array[index];
        }
    }

    /**
     * Task: Shifts entries that are beyond the entry to be removed to the next lower position. 
     * Precondition: array is not empty
     * 1 <= givenPosition < numberOfEntries; 
     * numberOfEntries is array's numberOfEntries before removal.
     */
    private void removeGap(int givenPosition) {
        // move each entry to next lower position starting at entry after the
        // one removed and continuing until end of array
        int removedIndex = givenPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
            array[index] = array[index + 1];
        }
    }
}
