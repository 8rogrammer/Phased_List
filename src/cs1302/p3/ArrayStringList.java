package cs1302.p3;

import cs1302.adt.StringList;
import cs1302.adt.FancyStringList;

/**
 * creates new String array and extends from BaseStringList.
 *
 */
public class ArrayStringList extends BaseStringList {

    private String[] items;

    /**
     * creates new empty string.
     */
    public ArrayStringList() {
        size = 0;
        items = new String[0];
    }

    /**
     * Copies Stringlist type into array.
     *
     * @param other
     */
    public ArrayStringList(StringList other) {
        this();
        forEach(other, this::append);
        /* ForEach loop that accepts parameter and copies items from other to a new ArrayStringList
         *  using append method.
         */
    }


    /**
     * add method adds a string to an array by creating new array that is longer.
     *
     * @param index
     * @param item
     * @return status
     */
    public boolean add(int index, String item) {
        boolean status = false;
        if (item == null) {
            throw new NullPointerException("item is null");
        } else if (item == "") {
            throw new IllegalArgumentException("item is empty");
        } else if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException(index +  " index is out of bounds");
        } else {
            String[] list = new String[size() + 1];
            int insert = 0;
            for (int i = 0; i < index; i++) {
                list[i] = items[i];
                insert++;
            }
            list[insert] = item;
            for (int j = insert; j < items.length; j++) {
                list[++insert] = items[j];
            }
            items = list;
            status = true;
        }
        size++;
        System.out.println("array to string: " + toString());
        return status;
    }


    /**
     * clears the array.
     */
    public void clear() {
        this.size = 0;
        for (String s: items) {
            s = "";
        }
    }

    /**
     * get method that gets the item of a specified index.
     *
     * @param index
     * @return string
     */
    public String get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index is out of bounds: " + index);
        }
        return items[index];
    }

    /**
     * remove method that removes an item from an array of strings.
     *
     * @param index
     * @return remove
     */
    @Override
    public String remove(int index) {
        String[] list = null;
        String del = "";
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index is out of bounds");
        } else {
            list = new String[size - 1];
            del = items[index];
            for (int i = 0, j = 0; i < size; i++) {
                if (i != index) {
                    list[j++] = items[i];
                }
            }
            size--;
            items = list;
            return del;
        }
    }


    /**
     * slices the array from where the start and stop is.
     *
     * @param start
     * @param stop
     * @return list
     */
    public StringList slice(int start, int stop) {
        StringList slice = new ArrayStringList();
        if (start < 0 || stop > size() || start > stop) {
            throw new IndexOutOfBoundsException("index is out of bounds");
        } else if (start == stop) {
            slice.clear();
        } else {
            for (int i = start, j = 0; i < stop; i++) {
                slice.add(j,items[i]);
                j++;
            }
            return slice;
        }
        return slice;
    }

    /**
     * slices array from where the start and stop is with step variable that steps over by input.
     *
     * @param start
     * @param stop
     * @param step
     * @return FancyStringList
     */
    public FancyStringList slice(int start, int stop, int step) {
        FancyStringList sliced = new ArrayStringList();
        if (start < 0 || stop > size() || start > stop || step < 1) {
            throw new IndexOutOfBoundsException("index is out of bounds");
        } else {
            for (int i = start, j = 0; i < stop; i += step) {
                sliced.add(j++, items[i]);
            }
        }
        return sliced;
    }

    /**
     * reverses an array.
     *
     * @return FancyStringList
     */
    public FancyStringList reverse() {
        FancyStringList reverse = new ArrayStringList();
        reverseForEach(this, reverse::append);
        //forEach loop that appends all the elements that are called with this method.
        return reverse;
    }
}
